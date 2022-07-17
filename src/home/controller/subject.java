package home.controller;

public class subject {
    Integer Subject_No;
    String Grade;
    String Subject;

    public subject(Integer subject_No, String grade, String subject) {
        Subject_No = subject_No;
        Grade = grade;
        Subject = subject;
    }

    public Integer getSubject_No() {
        return Subject_No;
    }

    public String getGrade() {
        return Grade;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject_No(Integer subject_No) {
        Subject_No = subject_No;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
