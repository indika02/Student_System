package home.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class SignUp implements Initializable {

    @FXML
    private Button btnsignup;

    @FXML
    private TextField txtadd1;

    @FXML
    private TextField txtadd2;

    @FXML
    private TextField txtadd3;

    @FXML
    private TextField txtcity;


    @FXML
    private ComboBox comb;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtfname;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtlname;

    @FXML
    private DatePicker txtbday;



    @FXML
    private TextField txttel;

    @FXML
    private PasswordField txtpwd;

//    @FXML
//    void select(ActionEvent event) {
//
//        String g=comb.getSelectionModel().getSelectedItem().toString();
//    }

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
        String city = txtcity.getText();

        String email = txtemail.getText();
        String telno = txttel.getText();
        String pass = txtpwd.getText();

        try {
            //sql input quary
            String sql="insert into users (`First name`, `Last name`,`User ID`,`address line 1`,`address line 2`,`address line 3`,`city`,`Email`,`Tel no.`,`Password`) values (?,?,?,?,?,?,?,?,?,?)";
            prepare=connect.prepareStatement(sql);
//database data input
            prepare.setString(1,fname);
            prepare.setString(2, lname);
            prepare.setString(3, id);
            prepare.setString(4, addl1);
            prepare.setString(5, addl2);
            prepare.setString(6, addl3);
            prepare.setString(7, city);
            prepare.setString(8, email);
            prepare.setString(9, telno);
            prepare.setString(10, pass);
            int status=prepare.executeUpdate();

            if (status==1){
                JOptionPane.showMessageDialog(null,"updated");
            }else {
                JOptionPane.showMessageDialog(null,"not added");
            }
        }catch (Exception e){
            System.out.println(e);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
//        ObservableList<String> list= FXCollections.observableArrayList("None","Grade 06","Grade 07","Grade 08","Grade 09","Grade 10","Grade 11","Grade 12","Grade 13");

//        comb.setItems(list);

    }

}
