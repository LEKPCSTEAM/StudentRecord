package com.example.studentrecord;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.VH> {
    public interface Listener { void onEdit(Student s); void onDelete(Student s); }
    private final List<Student> data; private final Listener listener;
    public StudentAdapter(List<Student> data, Listener l){ this.data=data; this.listener=l; }
    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup p, int v){ return new VH(LayoutInflater.from(p.getContext()).inflate(R.layout.item_student,p,false)); }
    @Override public void onBindViewHolder(@NonNull VH h, int i){
        Student s=data.get(i);
        h.tvFullname.setText(s.fullname);
        h.tvRegno.setText(s.regno);
        h.tvMeta.setText(s.gender+" • "+s.program+" • "+s.college+" • "+s.date);
        h.itemView.setOnClickListener(v->listener.onEdit(s));
        h.itemView.setOnLongClickListener(v->{ listener.onDelete(s); return true; });
    }
    @Override public int getItemCount(){ return data.size(); }
    static class VH extends RecyclerView.ViewHolder{
        TextView tvFullname,tvRegno,tvMeta;
        VH(@NonNull View v){ super(v); tvFullname=v.findViewById(R.id.tvFullname); tvRegno=v.findViewById(R.id.tvRegno); tvMeta=v.findViewById(R.id.tvMeta);}
    }
}
