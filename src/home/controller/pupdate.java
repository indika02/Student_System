package home.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class pupdate implements Initializable {
    @FXML
    private TextField Grade;

    @FXML
    private TextField DOB;

    @FXML
    private TextField city;

    @FXML
    private TextField email;

    @FXML
    private TextField Enrollment_No;

    @FXML
    private TextField Firstname;

    @FXML
    private TextField Clazz;

    @FXML
    private TextField Lastname;

    @FXML
    private TextField Telno;

    @FXML
    private TextField Username;

    @FXML
    private TextField addressl1;

    @FXML
    private TextField addressl2;

    @FXML
    private TextField addressl3;

    @FXML
    private Button cancel;


    @FXML
    private Button save;

    @FXML
    private Button search;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {
        connect=jdbcconnect.getConnection();

        String fname=Firstname.getText();
        String lname=Lastname.getText();
        String Enrollment=Enrollment_No.getText();
        //String Dob=DOB.getEditor().getText();
        String addl1=addressl1.getText();
        String addl2=addressl2.getText();
        String addl3=addressl3.getText();
        String City=city.getText();
        String Email=email.getText();
       // String g=Grade.getSelectionModel().getSelectedItem().toString();
        //String c=Clazz.getSelectionModel().getSelectedItem().toString();
        String tel=Telno.getText();

        try{
            String sql="UPDATE users set Firstname=?,Lastname=?, Username";
            prepare=connect.prepareStatement(sql);
        }catch (Exception e){

        }
    }

    @FXML
    private void search(ActionEvent event) {
        connect=jdbcconnect.getConnection();
        try {
            String uname=Username.getText();
            String sql="SELECT * FROM users";
            prepare=connect.prepareStatement(sql);

            ResultSet result = prepare.executeQuery(sql);

            while (result.next()){
                if (uname==result.getString("Username")){
                    Firstname.setText(result.getString("Firstname"));
                    Lastname.setText(result.getString("Lastname"));
                    Enrollment_No.setText(result.getString("Enrollment_No"));
                    DOB.setText(result.getString("DOB"));
                    addressl1.setText(result.getString("addressl1"));
                    addressl2.setText(result.getString("addressl2"));
                    addressl3.setText(result.getString("addressl3"));
                    city.setText(result.getString("city"));
                    email.setText(result.getString("email"));
                    Grade.setText(result.getString("Grade"));
                    Clazz.setText(result.getString("Clazz"));
                    Telno.setText(result.getString("Telno"));
                }
                }


        }catch (Exception e){
            System.out.println(e);
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle rb){






    }

}
