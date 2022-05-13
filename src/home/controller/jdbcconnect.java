package home.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class jdbcconnect {



    public static Connection getConnection(){
        try {
         String driver="com.mysql.cj.jdbc.Driver";
         String databaseurl="jdbc:mysql://localhost:3306/student system";
         String username="root";
         String password="";
         Class.forName(driver);
         Connection conn=DriverManager.getConnection(databaseurl,username,password);
        //JOptionPane.showMessageDialog(null,"Connected");
         return conn;
        }catch (Exception e){
            System.out.println(e);

        }
        return null;
    }
    //get data from database to the table
    public static ObservableList<Record> getDatausers(){
        Connection conn=getConnection();
        ObservableList<Record> list= FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from subjects");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Record((rs.getString("Grade")),(rs.getString("Subject"))));

            }
        }catch (Exception e){
        }
        return list;
    }

//    public static ObservableList<user> getinfo(){
//        Connection conn=getConnection();
//        ObservableList<user> li=FXCollections.observableArrayList();
//        try{
//            PreparedStatement ps=conn.prepareStatement("SELECT * FROM users");
//            ResultSet rs=ps.executeQuery();
//            while (rs.next()){
//                li.add(new user((rs.getString("firstname")),(rs.getString("lastname")),(rs.getString("idno")),(rs.getString("bday")),(rs.getString("add1")),(rs.getString("add2")),(rs.getString("add3")),(rs.getString("çity")),(rs.getString("email")),(rs.getString("gra")),(rs.getString("telno"))));
//
//            }
//        }catch (Exception e){
//
//        }return li;
    }
