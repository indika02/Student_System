package home.controller;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Adminpanal implements Initializable {

    @FXML
    private TableColumn<user, String > Firstname;

    @FXML
    private TableColumn<user, String> Lastname;

    @FXML
    private TableColumn<user, String> Username;

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
    private TableColumn<user, String> Password;

    @FXML
    private TableView<user> tableuser;

    @FXML
    private Button btnlogout;

    @FXML
    private TableColumn<Record, Integer > Subject_No;

    @FXML
    private TableColumn<Record, String> Grade;

    @FXML
    private TableColumn<Record, String> Subject;

    @FXML
    private TableView<Record> tablestd;


    @FXML
    private TextField txtgrade;

    @FXML
    private TextField txt_subno;

    @FXML
    private TextField txtsub;

    @FXML
    private TextField txttgra;

    @FXML
    private ImageView ivFiles;

    private FileInputStream fis;

    final FileChooser fc=new FileChooser();

    @FXML
    void handlebtnOpenImgFile(ActionEvent event) {
        fc.setTitle("My file Chooser");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png*", "*.jpg*", "*.gif*"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            ivFiles.setImage(new Image(file.toURI().toString()));
        } else {
            System.out.println("A file is invalid");
        }
        connect = jdbcconnect.getConnection();
        String sql = "insert into timetable(Grade,timetable)values(?,?)";
        try {
            prepare = connect.prepareStatement(sql);

            String gra = txttgra.getText();

            prepare.setString(1, gra);
            fis = new FileInputStream(file);
            prepare.setBinaryStream(2, (InputStream) fis);
            prepare.execute();
            //ivFiles.setImage(new Image("D:\\Student System\\Student System\\src\\home\\images\\vector-school-timetable-weekly-curriculum-design-template-J5XEHE.jpg"));
            txttgra.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    int index=-1;

    ObservableList<Record> listM;
    ObservableList<user> listN;
    //mysql connection variables
    private Connection connect;
    private PreparedStatement prepare;


    @FXML
    void add(ActionEvent event) {
     connect=jdbcconnect.getConnection();
        String sql="insert into subjects(Grade,Subject)values(?,?)";
        //call method for display data of database to table
        try{
            prepare=connect.prepareStatement(sql);
            String gra=txtgrade.getText();
           String sub=txtsub.getText();
           if(gra=="" && sub==""){
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText(null);
               alert.setContentText("ERROR!");
               alert.showAndWait();
           }else {
               prepare.setString(1, gra);
               prepare.setString(2, sub);
               prepare.execute();
               display();
               txt_subno.setText("");
               txtgrade.setText("");
               txtsub.setText("");
           }
        }catch (Exception e){
            System.out.println(e);
        }

    }
    @FXML
    void getSelected() {
        index = tablestd.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txt_subno.setText(Subject_No.getCellData(index).toString());
        txtgrade.setText(Grade.getCellData(index).toString());
        txtsub.setText(Subject.getCellData(index).toString());

    }


    public void Edit(){
        try{
            connect=jdbcconnect.getConnection();
            String value1=txt_subno.getText();
            String value2=txtgrade.getText();
            String value3=txtsub.getText();

            String sql = "update subjects set Subject_No= '"+value1+"',Grade= '"+value2+"',Subject= '"+
                    value3+"' where Subject_No= '"+value1+"' ";
            prepare=connect.prepareStatement(sql);
            prepare.execute();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("OK");
            alert.showAndWait();
            display();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERROR!");
            alert.showAndWait();
        }
    }


    public void Delete(){
        connect=jdbcconnect.getConnection();
        String sql = "delete from subjects where Subject_No = ?";
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, txt_subno.getText());
            prepare.execute();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("OK");
            alert.showAndWait();
            display();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERROR!");
            alert.showAndWait();
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
//display users data into table
    public void displayinfo(){
        Firstname.setCellValueFactory(new PropertyValueFactory<user,String>("firstname"));
        Lastname.setCellValueFactory(new PropertyValueFactory<user,String>("lastname"));
        Username.setCellValueFactory(new PropertyValueFactory<user,String>("username"));
        DOB.setCellValueFactory(new PropertyValueFactory<user,String>("bday"));
        addressl1.setCellValueFactory(new PropertyValueFactory<user,String>("add1"));
        addressl2.setCellValueFactory(new PropertyValueFactory<user,String>("add2"));
        addressl3.setCellValueFactory(new PropertyValueFactory<user,String>("add3"));
        city.setCellValueFactory(new PropertyValueFactory<user,String >("city"));
        Email.setCellValueFactory(new PropertyValueFactory<user,String>("email"));
        grade.setCellValueFactory(new PropertyValueFactory<user,String >("grade"));
        Clzz.setCellValueFactory(new PropertyValueFactory<user,String >("Clzz"));
        Password.setCellValueFactory(new PropertyValueFactory<user,String >("password"));
        listN=jdbcconnect.getinfo();
        tableuser.setItems(listN);

}



    private ArrayList<String> list;
//logout button
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
    public void initialize(URL url, ResourceBundle rb) {
        displayinfo();
        display();
    }
}
