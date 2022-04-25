package home.controller;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class jdbcconnect {



    public static Connection getConnection(){
        try {
         String driver="com.mysql.cj.jdbc.Driver";
         String databaseurl="jdbc:mysql://localhost:3306/student system";
         String username="root";
         String password="";
         Class.forName(driver);
         Connection conn=DriverManager.getConnection(databaseurl,username,password);
         JOptionPane.showMessageDialog(null,"Connected");
         return conn;
        }catch (Exception e){
            System.out.println(e);

        }
        return null;
    }
}