package home.controller;

public class user {

    String Firstname;
    String Lastname;
    String Enrollment_No;
    String DOB;
    String addressl1;
    String addressl2;
    String addressl3;
    String city;
    String email;
    String grde;
    String clzz;
    String Telno;
    String Usertype;

    public user(String firstname, String lastname, String enrollment_No, String DOB, String addressl1, String addressl2, String addressl3, String city, String email, String grde, String clzz, String telno, String usertype) {
        Firstname = firstname;
        Lastname = lastname;
        Enrollment_No = enrollment_No;
        this.DOB = DOB;
        this.addressl1 = addressl1;
        this.addressl2 = addressl2;
        this.addressl3 = addressl3;
        this.city = city;
        this.email = email;
        this.grde = grde;
        this.clzz = clzz;
        Telno = telno;
        Usertype = usertype;
    }

    public user() {

    }

    public String getFirstname() {
        return Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getEnrollment_No() {
        return Enrollment_No;
    }

    public String getDOB() {
        return DOB;
    }

    public String getAddressl1() {
        return addressl1;
    }

    public String getAddressl2() {
        return addressl2;
    }

    public String getAddressl3() {
        return addressl3;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getGrde() {
        return grde;
    }

    public String getClzz() {
        return clzz;
    }

    public String getTelno() {
        return Telno;
    }

    public String getUserType() {
        return Usertype;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public void setEnrollment_No(String enrollment_No) {
        Enrollment_No = enrollment_No;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setAddressl1(String addressl1) {
        this.addressl1 = addressl1;
    }

    public void setAddressl2(String addressl2) {
        this.addressl2 = addressl2;
    }

    public void setAddressl3(String addressl3) {
        this.addressl3 = addressl3;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGrde(String grde) {
        this.grde = grde;
    }

    public void setClzz(String clzz) {
        this.clzz = clzz;
    }

    public void setTelno(String telno) {
        Telno = telno;
    }

    public void setUserType(String usertype) {
        Usertype = usertype;
    }
}