package com.chad.exams;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivityStudent extends AppCompatActivity {

    private MaterialCheckBox checkBox1;
    private MaterialCheckBox checkBox2;
    private MaterialCheckBox checkBox3;
    private MaterialCheckBox checkBox4;

    private MaterialCheckBox checkBox5;
    private MaterialCheckBox checkBox6;
    private MaterialCheckBox checkBox7;
    private MaterialCheckBox checkBox8;
    private MaterialCheckBox checkBox9;
    private MaterialCheckBox checkBox10;

    private MaterialButton submitButton;

    private String unit1;
    private String unit2;
    private String unit3;
    private String unit4;
    private String unit5;
    private String unit6;
    private String unit7;
    private String unit8;
    private String unit9;
    private String unit10;
    private TextView unitList;

    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private List<Exams> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_student);
        initialize();
    }

    private void initialize() {
        checkBox1 = findViewById(R.id.checkbox_1);
        checkBox2 = findViewById(R.id.checkbox_2);
        checkBox3 = findViewById(R.id.checkbox_3);
        checkBox4 = findViewById(R.id.checkbox_4);
        checkBox5 = findViewById(R.id.checkbox_5);
        checkBox6 = findViewById(R.id.checkbox_6);
        checkBox7 = findViewById(R.id.checkbox_7);
        checkBox8 = findViewById(R.id.checkbox_8);
        checkBox9 = findViewById(R.id.checkbox_9);
        checkBox10 = findViewById(R.id.checkbox_10);
        submitButton = findViewById(R.id.submitButton);
        unitList = findViewById(R.id.unitList);


        firebaseAuth = FirebaseAuth.getInstance();
        list = new ArrayList<>();


        checkBox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                unit1 = "Unit 1";
            } else {
                unit1 = null;
            }
        });
        checkBox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                unit2 = "Unit 2";
            } else {
                unit2 = null;
            }
        });
        checkBox3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                unit3 = "Unit 3";
            } else {
                unit3 = null;
            }
        });
        checkBox4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                unit4 = "Unit 4";
            } else {
                unit4 = null;
            }
        });
        checkBox5.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                unit5 = "Unit 5";
            } else {
                unit5 = null;
            }
        });
        checkBox6.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                unit6 = "Unit 6";
            } else {
                unit6 = null;
            }
        });
        checkBox7.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                unit7 = "Unit 7";
            } else {
                unit7 = null;
            }
        });
        checkBox8.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                unit8 = "Unit 8";
            } else {
                unit8 = null;
            }
        });
        checkBox9.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                unit9 = "Unit 9";
            } else {
                unit9 = null;
            }
        });
        checkBox10.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                unit10 = "Unit 10";
            } else {
                unit10 = null;
            }
        });

        submitButton.setOnClickListener(v -> {
            implementFirebase();

        });

        readList();


    }

    private void implementFirebase() {

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String userId = null;
        if (firebaseUser != null) {
            userId = firebaseUser.getUid();
        }

        reference = FirebaseDatabase.getInstance().getReference().child("Student").child(userId).child("Units");

        ArrayList list = new ArrayList<>();

        if (unit1 + "   70Marks"!=null || unit2 + "   80Marks"!=null|| unit3 + "   50Marks"!=null|| unit4 + "   92Marks"!=null|| unit5 + "   56Marks"!=null|| unit6 + "   32Marks"!=null|| unit7 + "   78Marks"!=null||
                unit8 + "   71Marks"!=null|| unit9 + "   57Marks"!=null|| unit10 + "   45Marks"!=null) {

            list.add(unit1 + "   70Marks");
            list.add(unit2 + "   80Marks");
            list.add(unit3 + "   50Marks");
            list.add(unit4 + "   92Marks");
            list.add(unit5 + "   56Marks");
            list.add(unit6 + "   32Marks");
            list.add(unit7 + "   78Marks");
            list.add(unit8 + "   71Marks");
            list.add(unit9 + "   57Marks");
            list.add(unit10 + "   45Marks");

            HashMap<String, ArrayList> hashMap = new HashMap<>();
            hashMap.put("list", list);
//            hashMap.put("unit2", unit2);
//            hashMap.put("unit3", unit3);
//            hashMap.put("unit4", unit4);
//            hashMap.put("unit5", unit5);
//            hashMap.put("unit6", unit6);
//            hashMap.put("unit7", unit7);
//            hashMap.put("unit8", unit8);
//            hashMap.put("unit9", unit9);
//            hashMap.put("unit10", unit10);


            reference.setValue(hashMap).addOnCompleteListener(task1 -> {
                Toast.makeText(this, "Successfully Registered Units", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {
                Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
            });
        }

    }

    private void readList() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Student").child(firebaseUser.getUid()).child("Units");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    unitList.setText(dataSnapshot.getValue().toString());
                }

                //notificationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}