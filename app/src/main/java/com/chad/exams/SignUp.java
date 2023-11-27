package com.chad.exams;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    public EditText editTextEmail;
    public EditText editTextPassword;
    public EditText editTextName;
    public TextView textSignIn;
    public MaterialButton buttonSignUp;
    public TextView textMain;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        textSignIn = findViewById(R.id.textSignIn);
        textMain = findViewById(R.id.textMain);
        buttonSignUp = findViewById(R.id.signUpButton);

        firebaseAuth = FirebaseAuth.getInstance();


        textSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, SignIn.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        String mainName = intent.getStringExtra("Level");
        textMain.setText(mainName);

        buttonSignUp.setOnClickListener(v -> {
            implementFirebase();
        });

    }

    private void implementFirebase() {
        String email = editTextEmail.getText().toString();
        String password = editTextEmail.getText().toString();
        String name = editTextName.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String userId = null;
                        if (firebaseUser != null) {
                            userId = firebaseUser.getUid();
                        }

                        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("Name", name);
                        hashMap.put("Email", email);
                        hashMap.put("UserId", userId);

                        reference.setValue(hashMap).addOnCompleteListener(task1 -> {

                            Intent intent = new Intent(SignUp.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        });
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "You can't register now. Try again later!", Toast.LENGTH_SHORT).show();
                });
    }

}