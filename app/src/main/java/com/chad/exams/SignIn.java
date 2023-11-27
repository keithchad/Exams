package com.chad.exams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    public EditText editTextEmail;
    public EditText editTextPassword;
    private FirebaseAuth firebaseAuth;
    public MaterialButton buttonSignIn;

    public String mainName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.signInButton);

        Intent intent = getIntent();
        mainName = intent.getStringExtra("Level");


        firebaseAuth = FirebaseAuth.getInstance();
        buttonSignIn.setOnClickListener(v -> implementFirebase());

    }

    private void implementFirebase() {
        String email = editTextEmail.getText().toString();
        String password = editTextEmail.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                                .child( "Users").child(firebaseAuth.getCurrentUser().getUid());
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(mainName.equals("Admin")) {
                                    Intent intent = new Intent(SignIn.this, HomeActivityAdmin.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } else if(mainName.equals("Lecturer")) {
                                    Intent intent = new Intent(SignIn.this, HomeActivityLec.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } else if(mainName.equals("Student")) {
                                    Intent intent = new Intent(SignIn.this, HomeActivityStudent.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SignIn.this, "Failed", Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(SignIn.this, "Hello" + mainName, Toast.LENGTH_SHORT).show();

//                                Intent intent = new Intent(SignIn.this, HomeActivityAdmin.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(SignIn.this, ""+error, Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                })
                .addOnFailureListener(e -> {

                    Toast.makeText(SignIn.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                });

    }

}