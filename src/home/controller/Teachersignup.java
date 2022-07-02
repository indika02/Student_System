
    package home.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

    public class Teachersignup implements Initializable {

        @FXML
        private Label alt;

        @FXML
        private Button btnsub;

        @FXML
        private TextField txtaddl1;
        @FXML
        private TextField txtaddl2;
        @FXML
        private TextField txtaddl3;

        @FXML
        private TextField txtcity;

        @FXML
        private TextField txtemail;

        @FXML
        private TextField txtfname;

        @FXML
        private TextField txtlname;

        @FXML
        private TextField txtpwd;

        @FXML
        private TextField txtpwd1;

        @FXML
        private TextField txttelno;

        @FXML
        private TextField txtuname;

        @FXML
        private Label txtconfirm;

        private Connection connect;
        private Statement statement;
        private PreparedStatement prepare;
        private ResultSet result;

        @FXML
        void username(KeyEvent event) {
            connect = jdbcconnect.getConnection();
            String uname = txtuname.getText();

            try {
                String sql = "SELECT * FROM users WHERE Username='" + uname + "' ";
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                if (result.next()) {
                    String yes = result.getString("Username");
                    //System.out.println(yes);
                    alt.setVisible(true);

                } else {

                    //System.out.println("no users");
                    alt.setVisible(false);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        }


        @FXML
        void submit(ActionEvent event) {
            connect = jdbcconnect.getConnection();

//variable creating for input data
            String username = txtuname.getText();
            String fname = txtfname.getText();
            String lname = txtlname.getText();
            String addl1 = txtaddl1.getText();
            String addl2 = txtaddl2.getText();
            String addl3 = txtaddl3.getText();
            String city = txtcity.getText();
            String email = txtemail.getText();
            String telno = txttelno.getText();
            String pass = txtpwd1.getText();
            String cpass = txtpwd.getText();
            String type = "Teacher";

            try {

                //sql input quary
                //String sql="insert into users (`Firstname`, `Lastname`,`Username`,`addressl1`,`addressl2`,`addressl3`,`city`,`Email`,Telno`,`Password`,`UserType`) values (?,?,?,?,?,?,?,?,?,?,?)";
                String sql = "insert into users (`Firstname`, `Lastname`,`Username`,`Enrollment_No`,`DOB`,`addressl1`,`addressl2`,`addressl3`,`city`,`Email`,`grade`,`Clzz`,`Telno`,`Password`,`UserType`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                prepare = connect.prepareStatement(sql);
                //database data input

                prepare.setString(1, fname);
                prepare.setString(2, lname);
                prepare.setString(3, username);
                prepare.setString(4, "None");
                prepare.setString(5, "None");
                prepare.setString(6, addl1);
                prepare.setString(7, addl2);
                prepare.setString(8, addl3);
                prepare.setString(9, city);
                prepare.setString(10, email);
                prepare.setString(11, "None");
                prepare.setString(12, "None");
                prepare.setString(13, telno);
                prepare.setString(15, type);

                //prepare.executeUpdate();

                if (pass.equals(cpass)) {
                    prepare.setString(14, pass);
                    prepare.executeUpdate();
//              JOptionPane.showMessageDialog(null,"Updated");
                    //close current window
                    btnsub.getScene().getWindow().hide();
                    //move to the next window
                    Parent root = FXMLLoader.load(getClass().getResource("../ui/Login.fxml"));
                    Stage mainstage = new Stage();
                    Scene scene = new Scene(root);
                    mainstage.setScene(scene);
                    mainstage.show();
                } else {
                    txtpwd1.setText("");
                    txtpwd.setText("");
                    txtconfirm.setText("Different password.Re-Enter the password again!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
        }

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            alt.setVisible(false);
        }
    }
