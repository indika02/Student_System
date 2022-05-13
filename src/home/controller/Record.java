package home.controller;

import javafx.beans.property.SimpleStringProperty;

public class Record {

    private SimpleStringProperty Grade;
    private  SimpleStringProperty Subject;



    public Record(String Grade,String Subject){
        this.Grade=new SimpleStringProperty(Grade);
        this.Subject=new SimpleStringProperty(Subject);
    }
    public String getGrade(){
        return Grade.get();
    }
    public String getSubject(){
        return Subject.get();
    }

    public  void setGrade(String Grade){
        this.Grade=new SimpleStringProperty(Grade);
    }
    public void setSubject(String Subject){
        this.Subject=new SimpleStringProperty(Subject);
    }

}
