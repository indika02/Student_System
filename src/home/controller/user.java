package home.controller;

import javafx.beans.property.SimpleStringProperty;

public class user {
    private SimpleStringProperty firstname;
    private SimpleStringProperty lastname;
    private SimpleStringProperty idno;
    private SimpleStringProperty bday;
    private SimpleStringProperty add1;
    private SimpleStringProperty add2;
    private SimpleStringProperty add3;
    private SimpleStringProperty city;
    private SimpleStringProperty mail;
    private SimpleStringProperty gfff;
    private SimpleStringProperty tel;




    public user(String Firstname, String Lastname, String Idno, String Bday, String Add1, String Add2, String Add3, String City, String Email, String Gra, String Telno){
        this.firstname=new SimpleStringProperty(Firstname);
        this.lastname=new SimpleStringProperty(Lastname);
        this.idno=new SimpleStringProperty(Idno);
        this.bday=new SimpleStringProperty(Bday);
        this.add1=new SimpleStringProperty(Add1);
        this.add2=new SimpleStringProperty(Add2);
        this.add3=new SimpleStringProperty(Add3);
        this.city=new SimpleStringProperty(City);
        this.mail=new SimpleStringProperty(Email);
        this.gfff=new SimpleStringProperty(Gra);
        this.tel=new SimpleStringProperty(Telno);
    }
    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String Firstname){
        this.firstname=new SimpleStringProperty(Firstname);
    }
    public String getLastname(){
        return lastname.get();
    }
    public void setLastname(String Lastname){
        this.lastname=new SimpleStringProperty(Lastname);
    }

    public String getIdno(){
        return idno.get();
    }
    public void setIdno(String Idno){
        this.idno=new SimpleStringProperty(Idno);
    }

    public String getBday(){
        return bday.get();
    }
    public void setBday(String Bday){
        this.bday=new SimpleStringProperty(Bday);
    }
    public String getAdd1(){
        return add1.get();
    }
    public void setAdd1(String Add1){
        this.add1=new SimpleStringProperty(Add1);
    }
    public String getAdd2(){
        return add2.get();
    }
    public void setAdd2(String Add2){
        this.add2=new SimpleStringProperty(Add2);
    }

    public String getAdd3(){
        return add3.get();
    }
    public void setAdd3(String Add3){
        this.add3=new SimpleStringProperty(Add3);
    }

    public String getCity(){
        return city.get();
    }
    public void setCity(String City){
        this.city=new SimpleStringProperty(City);
    }

    public String getEmail(){
        return mail.get();
    }
    public void setEmail(String Email){
        this.mail=new SimpleStringProperty(Email);
    }

    public String getGra(){
        return gfff.get();
    }
    public void setGra(String Gra){
        this.gfff=new SimpleStringProperty(Gra);
    }
    public String getTelNo(){
        return tel.get();
    }
    public void setTelNo(String TelNo){
        this.tel=new SimpleStringProperty(TelNo);
    }



}
