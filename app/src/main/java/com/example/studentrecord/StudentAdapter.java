package com.example.studentrecord;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.Holder> {

    public interface Listener {
        void onEdit(Student s);
        void onDelete(Student s);
    }

    private final List<Student> list;
    private final Listener listener;

    public StudentAdapter(List<Student> list, Listener l) {
        this.list = list;
        this.listener = l;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_student, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder h, int i) {
        Student s = list.get(i);
        h.tvRegno.setText(s.regno);
        h.tvName.setText(s.fullname);
        h.tvProgram.setText(s.program);
        h.tvCollege.setText(s.college);
        h.tvDate.setText(s.date);

        if (s.gender.equalsIgnoreCase("male")) {
            h.ivGender.setImageResource(R.drawable.male);
        } else if (s.gender.equalsIgnoreCase("female")) {
            h.ivGender.setImageResource(R.drawable.female);
        } else {
            h.ivGender.setImageResource(android.R.drawable.ic_menu_help);
        }

        h.itemView.setOnClickListener(v -> listener.onEdit(s));
        h.itemView.setOnLongClickListener(v -> {
            listener.onDelete(s);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView tvRegno, tvName, tvProgram, tvCollege, tvDate;
        ImageView ivGender;

        public Holder(@NonNull View v) {
            super(v);
            tvRegno = v.findViewById(R.id.tvRegno);
            tvName = v.findViewById(R.id.tvName);
            tvProgram = v.findViewById(R.id.tvProgram);
            tvCollege = v.findViewById(R.id.tvCollege);
            tvDate = v.findViewById(R.id.tvDate);
            ivGender = v.findViewById(R.id.ivGender);
        }
    }
}
