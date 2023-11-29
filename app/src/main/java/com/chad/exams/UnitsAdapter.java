package com.chad.exams;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.ViewHolder> {

    private final Context context;
    private final List<Units> list;

    public UnitsAdapter(Context context, List<Units> list) {
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
        Units units = list.get(position);
        holder.textName.setText(units.getList().toString());
//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, StudentsUnits.class);
//            intent.putExtra("userId", students.getUserId());
//            context.startActivity(intent);
//        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);

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
