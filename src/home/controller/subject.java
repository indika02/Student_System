package home.controller;

public class subject {
    Integer Subject_No;
    //Integer grade;
    String Subject;

    public subject(Integer subject_No, String subject) {
        Subject_No = subject_No;
        //this.grade = grade;
        Subject = subject;
    }

    public subject(int sub_id) {
        Subject_No=sub_id;
    }

    public Integer getSubject_No() {
        return Subject_No;
    }

//    public Integer getGrade() {
//        return grade;
//    }

    public void setSubject_No(Integer subject_No) {
        Subject_No = subject_No;
    }

//    public void setGrade(Integer grade) {
//        this.grade = grade;
//    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getSubject() {
        return Subject;
    }
}
