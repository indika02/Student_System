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
    private Label total;

    @FXML
    private Label lblteaname;

    @FXML
    private Button cal;

    @FXML
    private TableColumn<user, String > Firstname;

    @FXML
    private TableColumn<user, String> Lastname;

    @FXML
    private TableColumn<user, String> Username;

    @FXML
    private TableColumn<user, String> Enrollment_No;

    @FXML
    private TableColumn<user, String> DOB;

    @FXML
    private TableColumn<user, String> addressl1;

    @FXML
    private TableColumn<user, String> addressl2;

    @FXML
    private TableColumn<user, String> addressl3;

    @FXML
    private TableColumn<user, String > city;

    @FXML
    private TableColumn<user, String> Email;

    @FXML
    private TableColumn<user, String> grade;

    @FXML
    private TableColumn<user, String> Clzz;

    @FXML
    private TableColumn<user, String> Telno;

    @FXML
    private TableColumn<user, String> Password;

    @FXML
    private TableColumn<user, String> UserType;

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
    private ComboBox<String> filtsub;

    @FXML
    private TextField txtgrade;



    @FXML
    private TextField txtsub;

    @FXML
    private ComboBox<String> Combmgrade;

    @FXML
    private ComboBox<String> Combmclass;
    @FXML
    private TextField Enrollment;

    @FXML
    private TextField Sinhala;

    @FXML
    private TextField Religious;
    @FXML
    private TextField History;
    @FXML
    private TextField Science;
    @FXML
    private TextField Mathematics;
    @FXML
    private TextField English;
    @FXML
    private TextField firstCategory;
    @FXML
    private TextField secondCategory;
    @FXML
    private TextField thirdCategory;

    @FXML
    private TextField nodtd;








    @FXML
    void select(ActionEvent event) {


        String ggrade=Combmgrade.getSelectionModel().getSelectedItem().toString();
        String gclass=Combmclass.getSelectionModel().getSelectedItem().toString();
    }



    public Adminpanal() {
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
            String gra=txtgrade.getText();
           String sub=txtsub.getText();
            prepare.setString(1,gra);
            prepare.setString(2,sub);
            prepare.execute();
            display();
            txtgrade.setText("");
            txtsub.setText("");

        }catch (Exception e){
            System.out.println(e);
        }

    }
    public void delete(){
        connect=jdbcconnect.getConnection();
        String sql="DELETE FROM subjects where Grade= ?";
        try{
            prepare=connect.prepareStatement(sql);
            prepare.setString(1,Grade.getText());
            prepare.execute();
            JOptionPane.showMessageDialog(null,"Deleted");
            display();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
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
        Firstname.setCellValueFactory(new PropertyValueFactory<user,String>("firstname"));
        Lastname.setCellValueFactory(new PropertyValueFactory<user,String>("lastname"));
        Username.setCellValueFactory(new PropertyValueFactory<user,String>("username"));
        //Enrollment_No.setCellValueFactory(new PropertyValueFactory<user,String>("enrollment_no"));
        DOB.setCellValueFactory(new PropertyValueFactory<user,String>("bday"));
        addressl1.setCellValueFactory(new PropertyValueFactory<user,String>("add1"));
        addressl2.setCellValueFactory(new PropertyValueFactory<user,String>("add2"));
        addressl3.setCellValueFactory(new PropertyValueFactory<user,String>("add3"));
        city.setCellValueFactory(new PropertyValueFactory<user,String >("city"));
        Email.setCellValueFactory(new PropertyValueFactory<user,String>("email"));
        grade.setCellValueFactory(new PropertyValueFactory<user,String >("grade"));
        Clzz.setCellValueFactory(new PropertyValueFactory<user,String >("Clzz"));
        //Telno.setCellValueFactory(new PropertyValueFactory<user,String>("Telno"));
        Password.setCellValueFactory(new PropertyValueFactory<user,String >("password"));
        //UserType.setCellValueFactory(new PropertyValueFactory<user,String>("usertype"));

        listN=jdbcconnect.getinfo();
        tableuser.setItems(listN);

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
@FXML
    void calculate(ActionEvent event){
        String Enrollment_No=Enrollment.getText();
        int sinhala=Integer.parseInt(Sinhala.getText());
        int religious=Integer.parseInt(Religious.getText());
        int history=Integer.parseInt(History.getText());
        int maths=Integer.parseInt(Mathematics.getText());
        int science=Integer.parseInt(Science.getText());
        int english=Integer.parseInt(English.getText());
        int first=Integer.parseInt(firstCategory.getText());
        int second=Integer.parseInt(secondCategory.getText());
        int third=Integer.parseInt(thirdCategory.getText());

        int totalno=Integer.parseInt(nodtd.getText());

        int tot=sinhala+religious+history+maths+science+english+first+second+third;
        total.setText(Integer.toString(tot));


    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        displayinfo();
        display();


//        ObservableList<String> listc=FXCollections.observableArrayList("Grade 10","Grade 11","Grade 12","Grade 13");
//        Combmgrade.setItems(listc);
//        ObservableList<String> lists=FXCollections.observableArrayList("10A","10B","10C","11A","11B","11C","12A","12B","12C","13A","13B","13C");
//        Combmclass.setItems(lists);
    }

}
