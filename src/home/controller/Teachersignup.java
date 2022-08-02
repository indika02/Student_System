
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

    public class Teachersignup implements Initializable {

        @FXML
        private Label s_id;

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

        @FXML
        private ComboBox<String> grade;



        @FXML
       public void select(ActionEvent event) {
            String ss=grade.getSelectionModel().getSelectedItem().toString();
            connect=jdbcconnect.getConnection();

            String sql2="SELECT class_id FROM clazz where clazz='"+ss+"'";

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


        private Connection connect;
        private Statement statement;
        private PreparedStatement prepare;
        private ResultSet result;


        @FXML
        void submit(ActionEvent event) {
            connect = jdbcconnect.getConnection();
            String sql="INSERT INTO `teacher` (`First name`, `Last name`,`email`, `Telno`, `password`,`status`,`class_id`) VALUES (?,?,?,?,?,?,?)";

//variable creating for input data

            String fname = txtfname.getText();
            String lname = txtlname.getText();
            String email = txtemail.getText();
            String telno = txttelno.getText();
            String pass =pwd1.getText();
            String cpass = pwd2.getText();
            String ii=s_id.getText();
            String type = "Teacher";

            try {


                prepare = connect.prepareStatement(sql);
                //database data input

                prepare.setString(1, fname);
                prepare.setString(2, lname);
                prepare.setString(3, email);
                prepare.setString(4, telno);

                prepare.setString(6,type);
                prepare.setString(7,ii);



                if (pass.equals(cpass)) {
                    prepare.setString(5, cpass);
                    prepare.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Confirm?");
                    alert.showAndWait();

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
            Parent root = FXMLLoader.load(getClass().getResource("../ui/log.fxml"));
            Stage mainstage = new Stage();
            Scene scene = new Scene(root);
            mainstage.setScene(scene);
            mainstage.initStyle(StageStyle.UNDECORATED);
            mainstage.show();
        }

        private ObservableList<String> s = FXCollections.observableArrayList();
        private ObservableList<String> no = FXCollections.observableArrayList();


        @Override
        public void initialize(URL url, ResourceBundle rb) {
            connect = jdbcconnect.getConnection();

            String sql = " SELECT class_id,clazz FROM clazz";
            try {
                prepare = connect.prepareStatement(sql);
                ResultSet result = prepare.executeQuery();

                while (result.next()) {
                    s.add(result.getString("clazz"));
                   grade.setItems(s);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        @FXML
        private Button btnexit;

        @FXML
        void exit(ActionEvent event){
            Stage stage = (Stage) btnexit.getScene().getWindow();
            stage.close();
        }

        }

