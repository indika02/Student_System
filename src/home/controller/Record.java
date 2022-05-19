package home.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Record {
    private SimpleIntegerProperty Subject_No;
    private SimpleStringProperty Grade;
    private  SimpleStringProperty Subject;



    public Record(int Subject_No,String Grade,String Subject){
        this.Subject_No=new SimpleIntegerProperty(Subject_No);
        this.Grade=new SimpleStringProperty(Grade);
        this.Subject=new SimpleStringProperty(Subject);
    }
    public int getSubject_No(){
        return Subject_No.get();
    }
    public String getGrade(){
        return Grade.get();
    }
    public String getSubject(){
        return Subject.get();
    }
    public void setSubject_No(int Subject_No){
        this.Subject_No=new SimpleIntegerProperty(Subject_No);
    }
    public  void setGrade(String Grade){
        this.Grade=new SimpleStringProperty(Grade);
    }
    public void setSubject(String Subject){
        this.Subject=new SimpleStringProperty(Subject);
    }

}
