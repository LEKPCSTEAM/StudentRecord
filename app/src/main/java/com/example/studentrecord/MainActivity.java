package com.example.studentrecord;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler;
    FloatingActionButton fabAdd;
    final List<Student> list = new ArrayList<>();
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.recycler);
        fabAdd = findViewById(R.id.fabAdd);
        adapter = new StudentAdapter(list, new StudentAdapter.Listener() {
            @Override public void onEdit(Student s) { showAddEdit(s); }
            @Override public void onDelete(Student s) { confirmDelete(s); }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recycler.setAdapter(adapter);
        fabAdd.setOnClickListener(v->showAddEdit(null));
        fetch();
    }

    void fetch(){
        StringRequest r = new StringRequest(Request.Method.GET, Api.LIST,
                res -> {
                    try{
                        list.clear();
                        JSONArray a = new JSONArray(res);
                        for(int i=0;i<a.length();i++){
                            JSONObject o=a.getJSONObject(i);
                            list.add(new Student(
                                    o.optString("regno"),
                                    o.optString("fullname"),
                                    o.optString("gender"),
                                    o.optString("program"),
                                    o.optString("college"),
                                    o.optString("date")
                            ));
                        }
                        adapter.notifyDataSetChanged();
                    }catch (Exception e){ Snackbar.make(recycler,e.getMessage(),Snackbar.LENGTH_LONG).show(); }
                },
                err -> Snackbar.make(recycler, String.valueOf(err), Snackbar.LENGTH_LONG).show());
        VolleySingleton.get(this).add(r);
    }

    void showAddEdit(Student edit){
        LayoutInflater inf = LayoutInflater.from(this);
        android.view.View v = inf.inflate(R.layout.dialog_add_edit,null,false);
        EditText etRegno=v.findViewById(R.id.etRegno);
        EditText etFullname=v.findViewById(R.id.etFullname);
        EditText etGender=v.findViewById(R.id.etGender);
        EditText etProgram=v.findViewById(R.id.etProgram);
        EditText etCollege=v.findViewById(R.id.etCollege);
        EditText etDate=v.findViewById(R.id.etDate);

        etDate.setFocusable(false);
        etDate.setClickable(true);
        etDate.setOnClickListener(view -> {
            MaterialDatePicker<Long> picker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("เลือกวันที่รับของ")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();
            picker.addOnPositiveButtonClickListener(selection -> {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(selection);
                Date d = cal.getTime();
                String formatted = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(d);
                etDate.setText(formatted);
            });
            picker.show(getSupportFragmentManager(), "pickup_date");
        });

        if(edit!=null){
            etRegno.setText(edit.regno); etRegno.setEnabled(false);
            etFullname.setText(edit.fullname);
            etGender.setText(edit.gender);
            etProgram.setText(edit.program);
            etCollege.setText(edit.college);
            etDate.setText(edit.date);
        }
        new AlertDialog.Builder(this)
                .setTitle(edit==null?"Create":"Edit")
                .setView(v)
                .setPositiveButton("Save",(d,w)->{
                    String regno=etRegno.getText().toString().trim();
                    String fullname=etFullname.getText().toString().trim();
                    String gender=etGender.getText().toString().trim();
                    String program=etProgram.getText().toString().trim();
                    String college=etCollege.getText().toString().trim();
                    String date=etDate.getText().toString().trim();
                    if(edit==null) create(regno,fullname,gender,program,college,date);
                    else update(regno,fullname,gender,program,college,date);
                })
                .setNegativeButton("Cancel",null)
                .show();
    }

    void create(String regno,String fullname,String gender,String program,String college,String date){
        StringRequest r = new StringRequest(Request.Method.POST, Api.CREATE,
                res->{ Snackbar.make(recycler,res,Snackbar.LENGTH_LONG).show(); fetch(); },
                err->Snackbar.make(recycler,String.valueOf(err),Snackbar.LENGTH_LONG).show()){
            @Override protected Map<String, String> getParams(){
                Map<String,String> p=new HashMap<>();
                p.put("regno",regno); p.put("fullname",fullname); p.put("gender",gender);
                p.put("program",program); p.put("college",college); p.put("date",date);
                return p;
            }
        };
        VolleySingleton.get(this).add(r);
    }

    void update(String regno,String fullname,String gender,String program,String college,String date){
        StringRequest r = new StringRequest(Request.Method.POST, Api.UPDATE,
                res->{ Snackbar.make(recycler,res,Snackbar.LENGTH_LONG).show(); fetch(); },
                err->Snackbar.make(recycler,String.valueOf(err),Snackbar.LENGTH_LONG).show()){
            @Override protected Map<String, String> getParams(){
                Map<String,String> p=new HashMap<>();
                p.put("regno",regno); p.put("fullname",fullname); p.put("gender",gender);
                p.put("program",program); p.put("college",college); p.put("date",date);
                return p;
            }
        };
        VolleySingleton.get(this).add(r);
    }

    void confirmDelete(Student s){
        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage(s.regno)
                .setPositiveButton("Delete",(d,w)->delete(s.regno))
                .setNegativeButton("Cancel",null)
                .show();
    }

    void delete(String regno){
        StringRequest r = new StringRequest(Request.Method.POST, Api.DELETE,
                res->{ Snackbar.make(recycler,res,Snackbar.LENGTH_LONG).show(); fetch(); },
                err->Snackbar.make(recycler,String.valueOf(err),Snackbar.LENGTH_LONG).show()){
            @Override protected Map<String, String> getParams(){
                Map<String,String> p=new HashMap<>();
                p.put("regno",regno);
                return p;
            }
        };
        VolleySingleton.get(this).add(r);
    }
}
