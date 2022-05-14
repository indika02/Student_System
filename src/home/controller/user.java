package home.controller;

import javafx.beans.property.SimpleStringProperty;

public class user {
    private SimpleStringProperty userid;
    private  SimpleStringProperty firstname;
    private  SimpleStringProperty lastname;
    private  SimpleStringProperty bday;
    private  SimpleStringProperty add1;
    private  SimpleStringProperty add2;
    private  SimpleStringProperty add3;
    private  SimpleStringProperty city;
    private  SimpleStringProperty email;
    private  SimpleStringProperty grade;
    private  SimpleStringProperty telno;






    public user(String userid,String firstname,String lastname,String bday,String add1,String add2,String add3,String city,String email,String grade,String telno){
        this.userid=new SimpleStringProperty(userid);
        this.firstname=new SimpleStringProperty(firstname);
        this.lastname=new SimpleStringProperty(lastname);
        this.bday=new SimpleStringProperty(bday);
        this.add1=new SimpleStringProperty(add1);
        this.add2=new SimpleStringProperty(add2);
        this.add3=new SimpleStringProperty(add3);
        this.city=new SimpleStringProperty(city);
        this.email=new SimpleStringProperty(email);
        this.grade=new SimpleStringProperty(grade);
        this.telno=new SimpleStringProperty(telno);

    }
    public String getUserId(){
        return userid.get();
    }
    public String getFirstname(){
        return firstname.get();
    }
    public String getLastname(){
        return lastname.get();
    }
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
    public String getTelNo(){
        return telno.get();
    }


    public  void setUserid(String userid){
        this.userid=new SimpleStringProperty(userid);
    }
    public void setFirstname(String firstname){
        this.firstname=new SimpleStringProperty(firstname);
    }
    public  void setLastname(String lastname){
        this.lastname=new SimpleStringProperty(lastname);
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
    public void setTelno(String telno){
        this.telno=new SimpleStringProperty(telno);
    }



}
