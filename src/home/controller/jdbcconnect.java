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
            String databaseurl = "jdbc:mysql://localhost:3306/student system1";
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

    public static ObservableList<subject> getDatausers() {
        Connection conn = getConnection();
        ObservableList<subject> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from subjects");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new subject(Integer.parseInt(rs.getString("Subject_No")),(rs.getString("Grade")), (rs.getString("Subject"))));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return list;
    }


}
