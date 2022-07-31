package home.controller;

public class subject {
    Integer Subject_No;

    String Subject;

    public subject(Integer subject_No, String subject) {
        Subject_No = subject_No;

        Subject = subject;
    }

    public Integer getSubject_No() {
        return Subject_No;
    }



    public String getSubject() {
        return Subject;
    }

    public void setSubject_No(Integer subject_No) {
        Subject_No = subject_No;
    }


    public void setSubject(String subject) {
        Subject = subject;
    }
}
