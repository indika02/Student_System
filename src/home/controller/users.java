package home.controller;

public class users {
    String gr;
    String sub;

    public  void setGr(String gr){
        this.gr=gr;
    }

    public  void setSub(String sub){
        this.sub=sub;
    }

    public String getGr(){
        return gr;
    }
    public String getSub(){
        return sub;
    }

    public users(String gr,String sub){
        this.gr=gr;
        this.sub=sub;
    }

}
