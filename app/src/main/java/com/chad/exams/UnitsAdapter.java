package com.chad.exams;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.ViewHolder> {

    private final Context context;
    private final List<String> list;

    public UnitsAdapter(Context context, List<String> list) {
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
        String string = list.get(position);
        holder.textName.setText(string);
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
            //showCustomDialog();

        });

    }

    private void showCustomDialog() {

        final Dialog dialog = new Dialog(context.getApplicationContext());
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.custom_dialog);

        //Initializing the views of the dialog.
        final EditText marks = dialog.findViewById(R.id.name_et);
        Button submitButton = dialog.findViewById(R.id.submit_button);


        submitButton.setOnClickListener(v -> {
            String mark = marks.getText().toString();
            implementDatabase(mark);
            dialog.dismiss();
        });

        dialog.show();
    }

    private void implementDatabase(String mark) {

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
