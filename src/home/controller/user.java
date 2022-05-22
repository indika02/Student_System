package home.controller;

import javafx.beans.property.SimpleStringProperty;

public class user {
    private  SimpleStringProperty firstname;
    private  SimpleStringProperty lastname;
    private  SimpleStringProperty username;
    private  SimpleStringProperty enrollment_no;
    private  SimpleStringProperty bday;
    private  SimpleStringProperty add1;
    private  SimpleStringProperty add2;
    private  SimpleStringProperty add3;
    private  SimpleStringProperty city;
    private  SimpleStringProperty email;
    private  SimpleStringProperty grade;
    private  SimpleStringProperty Clzz;
    private  SimpleStringProperty Telno;
    private  SimpleStringProperty password;
    private  SimpleStringProperty usertype;






    public user(String firstname,String lastname,String username,String enrollment_no,String bday,String add1,String add2,String add3,String city,String email,String grade,String Clzz,String Telno,String password,String usertype){
        this.firstname=new SimpleStringProperty(firstname);
        this.lastname=new SimpleStringProperty(lastname);
        this.username=new SimpleStringProperty(username);
        this.enrollment_no=new SimpleStringProperty(enrollment_no);
        this.bday=new SimpleStringProperty(bday);
        this.add1=new SimpleStringProperty(add1);
        this.add2=new SimpleStringProperty(add2);
        this.add3=new SimpleStringProperty(add3);
        this.city=new SimpleStringProperty(city);
        this.email=new SimpleStringProperty(email);
        this.grade=new SimpleStringProperty(grade);
        this.Clzz=new SimpleStringProperty(Clzz);
        this.Telno=new SimpleStringProperty(Telno);
        this.password=new SimpleStringProperty(password);
        this.usertype=new SimpleStringProperty(usertype);

    }

    public String getFirstname(){
        return firstname.get();
    }
    public String getLastname(){
        return lastname.get();
    }
    public String getUsername(){ return username.get();}
    public String getEnrollmentNo(){ return enrollment_no.get();}
    public String getBday(){
        return bday.get();
    }
    public String getAdd1(){
        return add1.get();
    }
    public String getAdd2(){
        return add2.get();
    }
    public String getAdd3(){
        return add3.get();
    }
    public String getCity(){
        return city.get();
    }
    public String getEmail(){
        return email.get();
    }
    public String getGrade(){
        return grade.get();
    }
    public String getClzz(){ return Clzz.get();}
    public String getTelNo(){
        return Telno.get();
    }
    public String getPassword(){ return password.get();}
    public String getUserType(){ return usertype.get();}


    public void setFirstname(String firstname){
        this.firstname=new SimpleStringProperty(firstname);
    }
    public  void setLastname(String lastname){
        this.lastname=new SimpleStringProperty(lastname);
    }
    public  void setUsername(String username){
        this.username=new SimpleStringProperty(username);
    }
    public  void setEnrollment_no(String enrollment_no){
        this.enrollment_no=new SimpleStringProperty(enrollment_no);
    }
    public void setBday(String bday){
        this.bday=new SimpleStringProperty(bday);
    }
    public  void setAdd1(String add1){
        this.add1=new SimpleStringProperty(add1);
    }
    public  void setAdd2(String add2){
        this.add2=new SimpleStringProperty(add2);
    }
    public  void setAdd3(String add3){
        this.add3=new SimpleStringProperty(add3);
    }
    public void setCity(String city){
        this.city=new SimpleStringProperty(city);
    }
    public  void setEmail(String email){
        this.email=new SimpleStringProperty(email);
    }
    public void setGrade(String grade){
        this.grade=new SimpleStringProperty(grade);
    }
    public  void setClzz(String Clzz){
        this.Clzz=new SimpleStringProperty(Clzz);
    }
    public void setTelno(String Telno){
        this.Telno=new SimpleStringProperty(Telno);
    }
    public  void setPassword(String password){
        this.password=new SimpleStringProperty(password);
    }
    public  void setUsertype(String usertype){
        this.usertype=new SimpleStringProperty(usertype);
    }



}
