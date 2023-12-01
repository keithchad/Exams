package com.chad.exams;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentsUnits extends AppCompatActivity {

    String userId;
    private RecyclerView recyclerView;
    private UnitsAdapter unitsAdapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_units);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        recyclerView = findViewById(R.id.unitsRecyclerView);

        list = new ArrayList<>();
        unitsAdapter = new UnitsAdapter(getApplicationContext(), list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(unitsAdapter);

        readUnits();
    }

    private void readUnits() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Student").child(userId).child("Units").child("list");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String name = dataSnapshot.getValue(String.class);
                    list.add(name);
                }
                unitsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}