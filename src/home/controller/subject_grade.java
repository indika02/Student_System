package home.controller;

public class subject_grade{

    int Subject_no,Grade;
    String Subject;

    public subject_grade(int subject_no, int grade, String subject) {
        Subject_no = subject_no;
        Grade = grade;
        Subject = subject;
    }

    public int getSubject_no() {
        return Subject_no;
    }

    public void setSubject_no(int subject_no) {
        Subject_no = subject_no;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
