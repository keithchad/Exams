package com.chad.exams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    MaterialButton adminButton;
    MaterialButton lecturerButton;
    MaterialButton studentButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adminButton = findViewById(R.id.adminButton);
        lecturerButton = findViewById(R.id.lecturerButton);
        studentButton = findViewById(R.id.studentButton);

        adminButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            intent.putExtra("Level", "Admin");
            startActivity(intent);
        });
        lecturerButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            intent.putExtra("Level", "Lecturer");
            startActivity(intent);
        });
        studentButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            intent.putExtra("Level", "Student");
            startActivity(intent);
        });

    }
}