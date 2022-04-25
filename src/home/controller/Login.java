package home.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class Login {

    @FXML
    private Button btnlog;

    @FXML
    private TextField txtemail;

    @FXML
    private PasswordField txtpassword;



    //tools for database
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    public void login(){
        connect=jdbcconnect.getConnection();

        String sql="SELECT * FROM student WHERE Email=? and Password=?" ;

        try{

            prepare=connect.prepareStatement(sql);
            prepare.setString(1,txtemail.getText());
            prepare.setString(2,txtpassword.getText());
            result=prepare.executeQuery();

            if (result.next()){
                JOptionPane.showMessageDialog(null,"Logged!");
            }else {
                Alert alert=new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username/Password");
                alert.showAndWait();

            }
        }catch (Exception e){

        }
    }
}

