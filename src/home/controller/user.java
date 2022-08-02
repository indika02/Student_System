package home.controller;

public class user {

    String Firstname;
    String Lastname;
    Integer Student_ID;
    String DOB;
    String Address;
    String email;
    Integer g_id;
    Integer Telno;
    String Clazz;

//    public user(String firstname, String lastname, Integer student_ID, String DOB, String address, String email,Integer g_id, Integer telno) {
//
//    }

    public user(int student_id, String firstname, String lastname, String dob, String address, String email, int g_id, int telno,String clazz) {
        Firstname = firstname;
        Lastname = lastname;
        Student_ID = student_id;
        this.DOB = dob;
        Address = address;
        this.email = email;
        this.g_id = g_id;
        Telno = telno;
        Clazz=clazz;
    }
    public String getClazz() {
        return Clazz;
    }

    public String getFirstname() {
        return Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public int getStudent_ID() {
        return Student_ID;
    }

    public String getDOB() {
        return DOB;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return email;
    }

    public int getG_id() {
        return g_id;
    }

    public int getTelno() {
        return Telno;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public void setStudent_ID(int student_ID) {
        Student_ID = student_ID;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public void setTelno(int telno) {
        Telno = telno;
    }


    public void setClazz(String clazz) {
        Clazz = clazz;
    }


}