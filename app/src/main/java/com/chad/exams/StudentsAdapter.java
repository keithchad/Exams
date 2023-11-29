package com.chad.exams;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {

    private final Context context;
    private final List<Student> list;

    public StudentsAdapter(Context context, List<Student> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student students = list.get(position);
        holder.textName.setText(students.getName());
        //holder.textUnit.setText(students.getUnits().toString());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StudentsUnits.class);
            intent.putExtra("userId", students.getUserId());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textName;
        //private TextView textUnit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            //textUnit = itemView.findViewById(R.id.textUnit);

        }
    }

//    private void getUserInfo(ImageView imageView, TextView textView, String publisherId) {
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(publisherId);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                User user = snapshot.getValue(User.class);
//                if (user != null) {
//                    Glide.with(context).load(user.getImageUrl()).into(imageView);
//                    textView.setText(user.getUserName());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}
