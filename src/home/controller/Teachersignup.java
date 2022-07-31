
    package home.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

    public class Teachersignup implements Initializable {

        @FXML
        private Label alt;

        @FXML
        private Button btnsub;

        @FXML
        private Button btnback;

        @FXML
        private TextField address;



        @FXML
        private TextField txtemail;

        @FXML
        private TextField txtfname;

        @FXML
        private TextField txtlname;

        @FXML
        private TextField txttelno;

        @FXML
        private PasswordField pwd1;

        @FXML
        private PasswordField pwd2;

        @FXML
        private Label txtconfirm;

        private Connection connect;
        private Statement statement;
        private PreparedStatement prepare;
        private ResultSet result;




        @FXML
        void submit(ActionEvent event) {
            connect = jdbcconnect.getConnection();

//variable creating for input data

            String fname = txtfname.getText();
            String lname = txtlname.getText();
            String add = address.getText();
            String email = txtemail.getText();
            String telno = txttelno.getText();
            String pass =pwd1.getText();
            String cpass = pwd2.getText();
            String type = "Teacher";

            try {


                //prepare = connect.prepareStatement();
                //database data input

                prepare.setString(1, fname);
                prepare.setString(2, lname);
                prepare.setString(6, add);
                prepare.setString(10, email);
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
                    mainstage.initStyle(StageStyle.UNDECORATED);
                    mainstage.show();
                } else {
                    pwd1.setText("");
                    pwd2.setText("");
                    txtconfirm.setText("Different password.Re-Enter the password again!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
        }

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
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            alt.setVisible(false);
        }
    }
