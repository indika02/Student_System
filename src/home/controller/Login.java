package home.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {

    @FXML
    private Button btnsignup;
    @FXML
    private Button btnlog;

    @FXML
    private TextField txtuserid;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Label lbltxt;

    @FXML
    void openlink(ActionEvent event) {
    }
    @FXML
    void signupshow(ActionEvent event) throws IOException {
        //close current window
        btnlog.getScene().getWindow().hide();
        //move to the next window
        Parent root=FXMLLoader.load(getClass().getResource("../ui/Sign up.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.setScene(scene);
        mainstage.show();
    }






    //tools for database
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    public void login(ActionEvent event) throws Exception{
        connect=jdbcconnect.getConnection();

//Mysql Statement
        String sql="SELECT * FROM users WHERE UserID=? and Password=?" ;

        //check the user id
        String log=txtuserid.getText();
        char[] charArray=log.toCharArray();
        Character c=charArray[0];

        try{
//mysql database checking
            prepare=connect.prepareStatement(sql);
            prepare.setString(1,txtuserid.getText());
            prepare.setString(2,txtpassword.getText());
            result=prepare.executeQuery();

            if (result.next()){
                //check the user id
                if (c.equals('S') || c.equals('s')){
                    //close current window
                    btnlog.getScene().getWindow().hide();
                    //move to the next window
                    Parent root = FXMLLoader.load(getClass().getResource("../ui/Sign up.fxml"));
                    Stage mainstage = new Stage();
                    Scene scene = new Scene(root);
                    mainstage.setScene(scene);
                    mainstage.show();
                }else {
                    //close current window
                    btnlog.getScene().getWindow().hide();
                    //move to the next window
                    Parent root = FXMLLoader.load(getClass().getResource("../ui/Adminpanal.fxml"));
                    Stage mainstage = new Stage();
                    Scene scene = new Scene(root);
                    mainstage.setScene(scene);
                    mainstage.show();
                }
            }else {
               lbltxt.setText("Incorrect Username or Password!");


            }
        }catch (Exception e){

        }
    }
}

