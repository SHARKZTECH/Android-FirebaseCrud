package com.example.firebasecrud;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Courses> coursesList;
    Context context;
    int lastPos = -1;

    public MyAdapter(ArrayList<Courses> coursesList, Context context) {
        this.coursesList = coursesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Courses course=coursesList.get(position);
        holder.name.setText(course.getName());
        holder.price.setText("$"+course.getPrice());
        Picasso.get().load(course.getImage()).into(holder.imageView);
    }
    private void displayBottomSheet(Courses course) {
        // on below line we are creating our bottom sheet dialog.
        final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(context, R.style.BottomSheetDialogTheme);
        // on below line we are inflating our layout file for our bottom sheet.

        View layout = LayoutInflater.from(context).inflate(R.layout.bottom_layout,null);
        // setting content view for bottom sheet on below line.
        bottomSheetTeachersDialog.setContentView(layout);
        // on below line we are setting a cancelable
        bottomSheetTeachersDialog.setCancelable(false);
        bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);
        // calling a method to display our bottom sheet.
        bottomSheetTeachersDialog.show();
        // on below line we are creating variables for
        // our text view and image view inside bottom sheet
        // and initializing them with their ids.
        TextView name = layout.findViewById(R.id.Name);
        TextView courseDesc = layout.findViewById(R.id.Desc);
        TextView suitedForTV = layout.findViewById(R.id.idTVSuitedFor);
        TextView price = layout.findViewById(R.id.Price);
        ImageView imageView = layout.findViewById(R.id.image);
        // on below line we are setting data to different views on below line.
        name.setText(course.getName());
        courseDesc.setText(course.getDescription());
        suitedForTV.setText("Suited for " + course.getSuite());
        price.setText("$." + course.getPrice());
        Picasso.get().load(course.getImage()).into(imageView);
        Button viewBtn = layout.findViewById(R.id.BtnVIewDetails);
        Button editBtn = layout.findViewById(R.id.BtnEditCourse);

        // adding on click listener for our edit button.
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are opening our EditCourseActivity on below line.
                Intent i = new Intent(context, EditActivity.class);
                // on below line we are passing our course modal
                i.putExtra("course", course);
                context.startActivity(i);
            }
        });
        // adding click listener for our view button on below line.
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are navigating to browser
                // for displaying course details from its url
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(course.getLink()));
                context.startActivity(i);
            }
        });
    }
    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            // on below line we are setting animation.
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }
    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,price;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            imageView=itemView.findViewById(R.id.image);
        }
    }
}
