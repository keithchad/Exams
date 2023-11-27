package com.chad.exams;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class SignUp extends AppCompatActivity {

    public EditText editTextEmail;
    public EditText editTextPassword;

    public TextView textSignIn;
    public TextView textMain;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        textSignIn = findViewById(R.id.textSignIn);
        textMain = findViewById(R.id.textMain);

        textSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, SignIn.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        String mainName = intent.getStringExtra("Level");
        textMain.setText(mainName);

        implementFirebase();
    }

    private void implementFirebase() {
        String email = editTextEmail.getText().toString();
        String password = editTextEmail.getText().toString();
    }

    String email = editTextEmail.getText().toString();
    String password = editTextEmail.getText().toString();

}