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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Adminpanal extends Login implements Initializable {

    @FXML
    private Label lblgrade;

    @FXML
    private Label lblteaname;

    @FXML
    private TableColumn<user, String> DOB;

    @FXML
    private TableColumn<user, String> Email;

    @FXML
    private TableColumn<user, String> grade;

    @FXML
    private TableColumn<user, String> Lastname;

    @FXML
    private TableColumn<user, String> Telno;

    @FXML
    private TableColumn<user, String > UserID;

    @FXML
    private TableColumn<user, String> addressl1;

    @FXML
    private TableColumn<user, String> addressl2;

    @FXML
    private TableColumn<user, String> addressl3;

    @FXML
    private TableColumn<user, String > city;

    @FXML
    private TableColumn<user, String > Firstname;

    @FXML
    private TableView<user> tableuser;

    @FXML
    private Button btnlogout;

    @FXML
    private Button btnadd;

    @FXML
    private Button btnremove;

    @FXML
    private TableColumn<Record, Integer > Subject_No;

    @FXML
    private TableColumn<Record, String> Grade;

    @FXML
    private TableColumn<Record, String> Subject;

    @FXML
    private TableView<Record> tablestd;

    @FXML
    private ComboBox<String> comb;

    @FXML
    private TextField txtsub;

    public Adminpanal() {
    }

    @FXML
    void select(ActionEvent event) {
        String g=comb.getSelectionModel().getSelectedItem().toString();



    }


    ObservableList<Record> listM;
    ObservableList<user> listN;
    //mysql connection variables
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    void add(ActionEvent event) {
     connect=jdbcconnect.getConnection();
        String sql="insert into subjects(Grade,Subject)values(?,?)";
        //call method for display data of database to table
        try{
            prepare=connect.prepareStatement(sql);
            String g=comb.getSelectionModel().getSelectedItem().toString();
           String sub=txtsub.getText();
            prepare.setString(1,g);
            prepare.setString(2,sub);
            prepare.execute();
            display();
            comb.getSelectionModel().select(0);
            txtsub.setText("");

        }catch (Exception e){
            System.out.println(e);
        }

    }
    //method of diasplay data of table
    public void display(){
        Subject_No.setCellValueFactory(new PropertyValueFactory<Record,Integer>("Subject_No"));
        Grade.setCellValueFactory(new PropertyValueFactory<Record, String>("Grade"));
        Subject.setCellValueFactory(new PropertyValueFactory<Record, String>("Subject"));
        listM=jdbcconnect.getDatausers();
        tablestd.setItems(listM);
    }

public void displayinfo(){
        UserID.setCellValueFactory(new PropertyValueFactory<user,String >("userid"));
        Firstname.setCellValueFactory(new PropertyValueFactory<user,String>("firstname"));
        Lastname.setCellValueFactory(new PropertyValueFactory<user,String>("lastname"));
        DOB.setCellValueFactory(new PropertyValueFactory<user,String>("bday"));
        addressl1.setCellValueFactory(new PropertyValueFactory<user,String>("add1"));
        addressl2.setCellValueFactory(new PropertyValueFactory<user,String>("add2"));
        addressl3.setCellValueFactory(new PropertyValueFactory<user,String>("add3"));
        city.setCellValueFactory(new PropertyValueFactory<user,String >("city"));
        Email.setCellValueFactory(new PropertyValueFactory<user,String>("email"));
        grade.setCellValueFactory(new PropertyValueFactory<user,String >("grade"));
        Telno.setCellValueFactory(new PropertyValueFactory<user,String>("telno"));
        listN=jdbcconnect.getinfo();
        tableuser.setItems(listN);

}

@FXML
    public void remove(ActionEvent event){
        connect=jdbcconnect.getConnection();
        String sql="DELETE FROM subjects where subject_No=?";
        try{
            prepare=connect.prepareStatement(sql);
            prepare.setString(1,Subject_No.getText());
            prepare.execute();
            display();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    private ArrayList<String> list;

    @FXML
    void logout(ActionEvent event) throws IOException {
        //close current window
        btnlogout.getScene().getWindow().hide();
        //move to the next window
        Parent root= FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.setScene(scene);
        mainstage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){


        displayinfo();
        display();
        ObservableList<String> list= FXCollections.observableArrayList("None","Grade 06","Grade 07","Grade 08","Grade 09","Grade 10","Grade 11","Grade 12","Grade 13");
        comb.setItems(list);





    }

}
