package com.example.studentrecord;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//create a class name it "adapter"

public class Adapter extends RecyclerView.Adapter<Adapter.allstudents> {

    //then, create objects like this

    Context context;
    List<Model> modelList;

    //then create adapter constructor

    public Adapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    //then, here you link the contents file that has views

    @NonNull
    @Override
    public allstudents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contents, parent, false);
        allstudents allstudents = new allstudents(view);
        return allstudents;
    }

    //then Bind your content with Model class

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull allstudents holder, int position) {
        Model model = modelList.get(position);
        holder.regno.setText("Registration Number: " + model.getRegno());
        holder.fullname.setText("Name: " + model.getFullname());
        holder.gender.setText("Gender: " + model.getGender());
        holder.college.setText("College: " + model.getCollege());
        holder.program.setText("Program: " + model.getProgram());
        holder.date.setText("Registered: " + model.getDate());

        //now check the gender and put there appropiate image

        if (model.getGender().equals("male")) {
            holder.avatar.setImageResource(R.drawable.male);
        } else if (model.getGender().equals("female")) {
            holder.avatar.setImageResource(R.drawable.female);
        }
    }

    //now, get number of items at a specific time

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    //now find your views

    public static class allstudents extends RecyclerView.ViewHolder {
        TextView regno, fullname, gender, program, date, college;
        ImageView avatar;

        public allstudents(@NonNull View itemView) {
            super(itemView);
            regno = itemView.findViewById(R.id.regno);
            fullname = itemView.findViewById(R.id.fullname);
            gender = itemView.findViewById(R.id.gender);
            program = itemView.findViewById(R.id.program);
            date = itemView.findViewById(R.id.date);
            college = itemView.findViewById(R.id.college);
            avatar = itemView.findViewById(R.id.avatar);
        }
    }
}
