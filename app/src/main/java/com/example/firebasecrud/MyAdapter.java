package com.example.firebasecrud;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Courses> coursesList;
    Context context;
    int lastPos = -1;

    public MyAdapter(List<Courses> coursesList, Context context) {
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
