package com.example.studentrecord;

public class Model {

    String regno;
    String fullname;
    String gender;
    String program;
    String college;
    String date;

    public Model(String regno, String fullname, String gender, String program, String college, String date) {
        this.regno = regno;
        this.fullname = fullname;
        this.gender = gender;
        this.program = program;
        this.college = college;
        this.date = date;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
