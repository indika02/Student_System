package home.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class SignUp implements Initializable {


    @FXML
    private DatePicker birthday;

    @FXML
    private Button btnsignup;

    @FXML
    private Button btnback;


    @FXML
    private TextField txtadd1;

    @FXML
    private TextField txtadd2;

    @FXML
    private TextField txtadd3;

    @FXML
    private TextField txtcity;


    @FXML
    private ComboBox<String> comb;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtfname;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtlname;


    @FXML
    private TextField txttel;

    @FXML
    private PasswordField txtpwd;

    @FXML
    private PasswordField txtpwd2;


    @FXML
    private Label txtconfirm;


    @FXML
    void select(ActionEvent event) {

        String g=comb.getSelectionModel().getSelectedItem().toString();

    }

//mysql connection variables
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;


    @FXML
    void signup(ActionEvent event) {
//mysql connection
        connect=jdbcconnect.getConnection();

//variable creating for input data
        String fname=txtfname.getText();
        String lname=txtlname.getText();

        String id = txtid.getText();
        String addl1 = txtadd1.getText();
        String addl2 = txtadd2.getText();
        String addl3 = txtadd3.getText();


        String date=birthday.getEditor().getText();
        String city = txtcity.getText();
        //combobox
        String g=comb.getSelectionModel().getSelectedItem().toString();
        String email = txtemail.getText();
        String telno = txttel.getText();
        String pass = txtpwd.getText();
        String cpass=txtpwd2.getText();

        try {
            //sql input quary
            String sql="insert into users (`UserID`,`First name`, `Last name`,`DOB`,`address line 1`,`address line 2`,`address line 3`,`city`,`Email`,`Grade`,`Tel no.`,`Password`) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            prepare=connect.prepareStatement(sql);
           //database data input
            prepare.setString(1, id);
            prepare.setString(2,fname);
            prepare.setString(3, lname);
            prepare.setString(4,date);
            prepare.setString(5, addl1);
            prepare.setString(6, addl2);
            prepare.setString(7, addl3);
            prepare.setString(8, city);
            prepare.setString(9, email);
            prepare.setString(10,g);
            prepare.setString(11, telno);
           //prepare.executeUpdate();

            if (pass.equals(cpass)){
                prepare.setString(12, pass);
                prepare.executeUpdate();
//              JOptionPane.showMessageDialog(null,"Updated");
                //close current window
                btnsignup.getScene().getWindow().hide();
                //move to the next window
                Parent root= FXMLLoader.load(getClass().getResource("../ui/Login.fxml"));
                Stage mainstage=new Stage();
                Scene scene=new Scene(root);
                mainstage.setScene(scene);
                mainstage.show();
            }else{
                txtpwd.setText("");
                txtpwd2.setText("");
                txtconfirm.setText("Different password.Re-Enter the password again!");

            }






//            if (status==1){
//                JOptionPane.showMessageDialog(null,"updated");
//            }else {
//                JOptionPane.showMessageDialog(null,"not added");
//            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }


    }
    @FXML
    void back(ActionEvent event) throws IOException {
        //close current window
        btnback.getScene().getWindow().hide();
        //move to the next window
        Parent root=FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.setScene(scene);
        mainstage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> list= FXCollections.observableArrayList("None","Grade 06","Grade 07","Grade 08","Grade 09","Grade 10","Grade 11","Grade 12","Grade 13");

        comb.setItems(list);


    }

}
