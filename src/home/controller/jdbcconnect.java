package home.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class jdbcconnect {


    public static Connection getConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String databaseurl = "jdbc:mysql://localhost:3306/student system";
            String username = "root";
            String password = "";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, password);
            //JOptionPane.showMessageDialog(null,"Connected");
            return conn;
        } catch (Exception e) {
            System.out.println(e);

        }
        return null;
    }

    //get data from database to the table
    public static ObservableList<Record> getDatausers() {
        Connection conn = getConnection();
        ObservableList<Record> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from subjects");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Record(Integer.parseInt(rs.getString("Subject_No")),(rs.getString("Grade")), (rs.getString("Subject"))));

            }
        } catch (Exception e) {
        }
        return list;
    }

    public static ObservableList<user> getinfo() {
        Connection conn = getConnection();
        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new user((rs.getString("UserID")),(rs.getString("Firstname")),(rs.getString("Lastname")),(rs.getString("DOB")),(rs.getString("addressl1")),(rs.getString("addressl2")),(rs.getString("addressl3")),(rs.getString("city")),(rs.getString("Email")),(rs.getString("Grade")),(rs.getString("Telno"))));

            }
        } catch (Exception e) {
JOptionPane.showMessageDialog(null,e);
        }
        return list;
    }
}
