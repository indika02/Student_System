package home.controller;

public class marks {

    String Enrollment_No;
    String grade;
    String Clzz;
    int sinhala;
    int religious;
    int english;
    int mathematics;
    int history;
    int science;
    int fstcategory;
    int sndcategory;
    int trdcategory;
    int total;
    double Average;

    public marks(String enrollment_No, String grade, String clzz, int sinhala, int religious, int english, int mathematics, int history, int science, int fstcategory, int sndcategory, int trdcategory, int total, double average) {
        Enrollment_No = enrollment_No;
        this.grade = grade;
        Clzz = clzz;
        this.sinhala = sinhala;
        this.religious = religious;
        this.english = english;
        this.mathematics = mathematics;
        this.history = history;
        this.science = science;
        this.fstcategory = fstcategory;
        this.sndcategory = sndcategory;
        this.trdcategory = trdcategory;
        this.total = total;
        Average = average;
    }

    public String getEnrollment_No() {
        return Enrollment_No;
    }

    public String getGrade() {
        return grade;
    }

    public String getClzz() {
        return Clzz;
    }

    public int getSinhala() {
        return sinhala;
    }

    public int getReligious() {
        return religious;
    }

    public int getEnglish() {
        return english;
    }

    public int getMathematics() {
        return mathematics;
    }

    public int getHistory() {
        return history;
    }

    public int getScience() {
        return science;
    }

    public int getFstcategory() {
        return fstcategory;
    }

    public int getSndcategory() {
        return sndcategory;
    }

    public int getTrdcategory() {
        return trdcategory;
    }

    public int getTotal() {
        return total;
    }

    public double getAverage() {
        return Average;
    }

    public void setEnrollment_No(String enrollment_No) {
        Enrollment_No = enrollment_No;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setClzz(String clzz) {
        Clzz = clzz;
    }

    public void setSinhala(int sinhala) {
        this.sinhala = sinhala;
    }

    public void setReligious(int religious) {
        this.religious = religious;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public void setMathematics(int mathematics) {
        this.mathematics = mathematics;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public void setFstcategory(int fstcategory) {
        this.fstcategory = fstcategory;
    }

    public void setSndcategory(int sndcategory) {
        this.sndcategory = sndcategory;
    }

    public void setTrdcategory(int trdcategory) {
        this.trdcategory = trdcategory;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setAverage(double average) {
        Average = average;
    }
}
