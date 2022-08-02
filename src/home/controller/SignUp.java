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

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private TextArea address;

    @FXML
    private DatePicker birthday;

    @FXML
    private Button btnback;

    @FXML
    private Button btnsignup;

    @FXML
    private ComboBox<String> comb;

    @FXML
    private ComboBox<String> combclass;

    @FXML
    private Label txtconfirm;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtfname;

    @FXML
    private TextField txtlname;

    @FXML
    private PasswordField txtpwd;

    @FXML
    private PasswordField txtpwd2;

    @FXML
    private TextField txttel;

    @FXML
    private TextField student_id;

    @FXML
    private Label s_id;

    @FXML
    void back(ActionEvent event) throws IOException {
        //close current window
        btnback.getScene().getWindow().hide();
        //move to the next window
        Parent root=FXMLLoader.load(getClass().getResource("../ui/log.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.setScene(scene);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.show();
    }


    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    void select(ActionEvent event) {

        String g=comb.getSelectionModel().getSelectedItem().toString();

        String c=combclass.getSelectionModel().getSelectedItem().toString();

        connect=jdbcconnect.getConnection();

        String sql2="SELECT class_id FROM clazz where clazz='"+c+"'";

        try{
            prepare = connect.prepareStatement(sql2);
            ResultSet result = prepare.executeQuery();

            if (result.next()){
                int id=Integer.parseInt(result.getString("class_id"));
                s_id.setText(Integer.toString(id));
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }

//mysql connection variables


    //check the username already taken


    @FXML
    void signup(ActionEvent event) {
//mysql connection
        connect=jdbcconnect.getConnection();

//variable creating for input data
        int id=Integer.parseInt(student_id.getText());
        String fname=txtfname.getText();
        String lname=txtlname.getText();
        String addr=address.getText();
        String date=birthday.getEditor().getText();
        //combobox
        String g=comb.getSelectionModel().getSelectedItem().toString();
        String email = txtemail.getText();
        int telno = Integer.parseInt(txttel.getText());

        String pass = txtpwd.getText();
        String cpass=txtpwd2.getText();
        String ii=s_id.getText();

        try {

            //sql input quary
            String sql="INSERT INTO `student` (`Student_ID`, `Firstname`, `Lastname`, `DOB`, `Address`, `Telno`, `email`, `password`,`status`,`g_id`,`class_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            prepare=connect.prepareStatement(sql);
           //database data input
            prepare.setString(1,Integer.toString(id));
            prepare.setString(2,fname);
            prepare.setString(3, lname);
            prepare.setString(4,date);
            prepare.setString(5, addr);
            prepare.setString(6, Integer.toString(telno));
            prepare.setString(7, email);
            prepare.setString(10,g);
            prepare.setString(11,ii);
            prepare.setString(9,"student");


           //prepare.executeUpdate();

            if (pass.equals(cpass)){
                prepare.setString(8, pass);
                prepare.executeUpdate();
//              JOptionPane.showMessageDialog(null,"Updated");
                //close current window
                btnsignup.getScene().getWindow().hide();
                //move to the next window
                Parent root= FXMLLoader.load(getClass().getResource("../ui/Login.fxml"));
                Stage mainstage=new Stage();
                Scene scene=new Scene(root);
                mainstage.setScene(scene);
                mainstage.initStyle(StageStyle.UNDECORATED);
                mainstage.show();
            }else{
                txtpwd.setText("");
                txtpwd2.setText("");
                txtconfirm.setText("Different password.Re-Enter the password again!");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    @FXML
    private Button btnexit;

    @FXML
    void exit(ActionEvent event){
        Stage stage = (Stage) btnexit.getScene().getWindow();
        stage.close();
    }
    private ObservableList<String> s = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> list= FXCollections.observableArrayList("06","07","08","09","10","11","12","13");
        comb.setItems(list);
        connect = jdbcconnect.getConnection();

        String sql = " SELECT class_id,clazz FROM clazz";
        try {
            prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                s.add(result.getString("clazz"));
                combclass.setItems(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }





    }

}
