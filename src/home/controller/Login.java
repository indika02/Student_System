package home.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Login implements Initializable {



    @FXML
    private Button btnsignup;
    @FXML
    private Button btnlog;

    @FXML
    private Button btnfpwod;

    @FXML
    private TextField txtuserid;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Label lbltxt;

    @FXML
    private ComboBox<String> type;



    @FXML
    void select(ActionEvent event) {
        String t=type.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void resetpwd(ActionEvent event) throws IOException {
        btnfpwod.getScene().getWindow().hide();
        //move to the next window
        Parent root= FXMLLoader.load(getClass().getResource("../ui/pwdreset.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.setScene(scene);
        mainstage.show();
    }


    @FXML
    void openlink(ActionEvent event) {
    }
    @FXML
    void signupshow(ActionEvent event) throws IOException {
        //close current window
        btnlog.getScene().getWindow().hide();
        //move to the next window
        Parent root=FXMLLoader.load(getClass().getResource("../ui/log.fxml"));
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
        String sql="SELECT * FROM users WHERE Username=? AND Password=? AND UserType=?" ;

        //check the user id
        String log=txtuserid.getText();
        char[] charArray=log.toCharArray();
        Character c=charArray[0];
        String t=type.getSelectionModel().getSelectedItem().toString();
        try{
//mysql database checking
            prepare=connect.prepareStatement(sql);
            prepare.setString(1,txtuserid.getText());
            prepare.setString(2,txtpassword.getText());
            prepare.setString(3,t);

            result=prepare.executeQuery();

            if (result.next()){
                //check the user id

                if(t.equals("Teacher")){

                    //close current window
                    btnlog.getScene().getWindow().hide();
                    //move to the next window
                    Parent root = FXMLLoader.load(getClass().getResource("../ui/Adminpanal.fxml"));
                    Stage mainstage = new Stage();
                    Scene scene = new Scene(root);
                    mainstage.setScene(scene);
                    mainstage.show();
                }else {
                    //close current window
                    btnlog.getScene().getWindow().hide();
                    //move to the next window
                    Parent root = FXMLLoader.load(getClass().getResource("../ui/studentboard.fxml"));
                    Stage mainstage = new Stage();
                    Scene scene = new Scene(root);
                    mainstage.setScene(scene);
                    mainstage.show();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Incorrect username or passowrd!");
                alert.showAndWait();


            }
        }catch (Exception e){

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

        ObservableList<String> listt= FXCollections.observableArrayList("Teacher","Student");
        type.setItems(listt);




    }
}

