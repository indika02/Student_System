package home.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

import javax.swing.*;
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
    private TextField Mathematics;

    @FXML
    private TextField Religious;

    @FXML
    private TextField Sinhala;
    @FXML
    private TextField English;

    @FXML
    private TextField tot;

    @FXML
    private TextField avg;

    @FXML
    private TextField firstCategory;

    @FXML
    private TextField secondCategory;

    @FXML
    private TextField thirdCategory;

    @FXML
    private TextField history;

    @FXML
    private TextField rank;

    @FXML
    private TextField science;

    @FXML
    private TextField nodtd;


    @FXML
    private TextField Enrollment_no;


    @FXML
    private TableColumn<user, String> Telno;

    @FXML
    private TableColumn<user, String> UserType;

    @FXML
    private TableColumn<user, String > Firstname;

    @FXML
    private TableColumn<user, String> Lastname;

    @FXML
    private TableColumn<user,String> Enrollment_No;



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
    private TableColumn<user, String> grde;

    @FXML
    private TableColumn<user, String> Clzz;

    @FXML
    private TextField searchfield;


    @FXML
    private Button btncal;

    @FXML
    private TableView<user> tableuser;

    @FXML
    private Button btnlogout;

    @FXML
    private TextField subjectsearch;

    @FXML
    private TableColumn<subject, Integer > Subject_No;

    @FXML
    private TableColumn<subject, String> Grade;

    @FXML
    private TableColumn<subject, String> Subject;

    @FXML
    private TableView<subject> tablestd;


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



    //mysql connection variables
    private Connection connect;
    private PreparedStatement prepare;


    @FXML
    void add(ActionEvent event) {
     connect=jdbcconnect.getConnection();
        String sql="insert into subjects(Subject_No,Grade,Subject)values(?,?,?)";
        //call method for display data of database to table
        try{
            prepare=connect.prepareStatement(sql);
            String t=txt_subno.getText();
            String gra=txtgrade.getText();
           String sub=txtsub.getText();
           if(gra=="" && sub==""){
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText(null);
               alert.setContentText("ERROR!");
               alert.showAndWait();
           }else {
                prepare.setString(1,t);
               prepare.setString(2, gra);
               prepare.setString(3, sub);
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
            JOptionPane.showMessageDialog(null,e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERROR!");
            alert.showAndWait();
        }

    }

    ObservableList<user> UsersearchJavaObservableList= FXCollections.observableArrayList();
    ObservableList<subject> SubjectSearchObservableList=FXCollections.observableArrayList();
    //method of diasplay data of table
    public void display(){

       connect=jdbcconnect.getConnection();

       String sql="SELECT Subject_No,Grade,Subject from subjects";

        try {
            prepare=connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()){
                int Subject_No=result.getInt("Subject_No");
                String Grade=result.getString("Grade");
                String Subject=result.getString("Subject");


                SubjectSearchObservableList.add(new subject(Subject_No,Grade,Subject));
            }
            Subject_No.setCellValueFactory(new PropertyValueFactory<subject, Integer>("Subject_No"));
            Grade.setCellValueFactory(new PropertyValueFactory<subject, String>("Grade"));
            Subject.setCellValueFactory(new PropertyValueFactory<subject, String>("Subject"));

            tablestd.setItems(SubjectSearchObservableList);

            FilteredList<subject> filterdata=new FilteredList<subject>(SubjectSearchObservableList, b -> true);
            subjectsearch.textProperty().addListener((observable,oldValue,newValue)->{
                filterdata.setPredicate(UserSearchJava->{
                    if(newValue.isEmpty() || newValue==null){
                        return true;
                    }
                    String searchkeyword=newValue.toLowerCase();
                    if(UserSearchJava.getGrade().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else
                        return false;
                });
            });
            SortedList<subject> sortData=new SortedList<subject>(filterdata);
            sortData.comparatorProperty().bind(tablestd.comparatorProperty());

            tablestd.setItems(sortData);
        }catch (Exception e){

        }
        Subject_No.setCellValueFactory(new PropertyValueFactory<subject,Integer>("Subject_No"));
        Grade.setCellValueFactory(new PropertyValueFactory<subject, String>("Grade"));
        Subject.setCellValueFactory(new PropertyValueFactory<subject, String>("Subject"));
        ObservableList<subject> listM;
        listM=jdbcconnect.getDatausers();
        tablestd.setItems(listM);
    }




//display users data into table
    public void displayinfo(){
        connect=jdbcconnect.getConnection();

        String sql="SELECT Firstname,Lastname,Enrollment_No,DOB,addressl1,addressl2,addressl3,city,Email,Grade,Clzz,Telno,UserType FROM users";
        try {
            prepare=connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()){
                String Firstname=result.getString("Firstname");
                String Lastname=result.getString("Lastname");
                String Enrollment_No=result.getString("Enrollment_No");
                String DOB=result.getString("DOB");
                String addressl1=result.getString("addressl1");
                String addressl2=result.getString("addressl2");
                String addressl3=result.getString("addressl3");
                String city=result.getString("city");
                String Email=result.getString("Email");
                String Grde=result.getString("Grade");
                String Clzz=result.getString("Clzz");
                String Telno = result.getString("Telno");
                String UserType=result.getString("UserType");

                UsersearchJavaObservableList.add(new user(Firstname,Lastname,Enrollment_No,DOB,addressl1,addressl2,addressl3,city,Email,Grde,Clzz,Telno,UserType));
            }
            Firstname.setCellValueFactory(new PropertyValueFactory<user, String>("Firstname"));
            Lastname.setCellValueFactory(new PropertyValueFactory<user, String>("Lastname"));
            Enrollment_No.setCellValueFactory(new PropertyValueFactory<user, String>("Enrollment_No"));
            DOB.setCellValueFactory(new PropertyValueFactory<user, String>("DOB"));
            addressl1.setCellValueFactory(new PropertyValueFactory<user,String>("addressl1"));
            addressl2.setCellValueFactory(new PropertyValueFactory<user,String>("addressl2"));
            addressl3.setCellValueFactory(new PropertyValueFactory<user,String>("addressl3"));
            city.setCellValueFactory(new PropertyValueFactory<user,String>("city"));
            Email.setCellValueFactory(new PropertyValueFactory<user,String>("Email"));
            grde.setCellValueFactory(new PropertyValueFactory<user,String>("Grde"));
            Clzz.setCellValueFactory(new PropertyValueFactory<user,String>("Clzz"));
            Telno.setCellValueFactory(new PropertyValueFactory<user,String>("Telno"));
            UserType.setCellValueFactory(new PropertyValueFactory<user, String>("UserType"));

            tableuser.setItems(UsersearchJavaObservableList);

            FilteredList<user> filterdata=new FilteredList<user>(UsersearchJavaObservableList, b -> true);
            searchfield.textProperty().addListener((observable,oldValue,newValue)->{
                filterdata.setPredicate(UserSearchJava->{
                    if(newValue.isEmpty() || newValue==null){
                        return true;
                    }
                    String searchkeyword=newValue.toLowerCase();
                    if(UserSearchJava.getFirstname().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if(UserSearchJava.getLastname().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if (UserSearchJava.getEnrollment_No().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if (UserSearchJava.getDOB().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if (UserSearchJava.getAddressl1().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if (UserSearchJava.getAddressl2().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if (UserSearchJava.getAddressl3().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if (UserSearchJava.getCity().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if (UserSearchJava.getGrde().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if (UserSearchJava.getClzz().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if (UserSearchJava.getUserType().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else
                        return false;
                });
            });
            SortedList<user> sortData=new SortedList<>(filterdata);
            sortData.comparatorProperty().bind(tableuser.comparatorProperty());

            tableuser.setItems(sortData);
        }catch (Exception e){

        }
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

    @FXML
    void btncal(ActionEvent event){
        int sin=Integer.parseInt(Sinhala.getText());
        int reli=Integer.parseInt(Religious.getText());
        int eng=Integer.parseInt(English.getText());
        int math=Integer.parseInt(Mathematics.getText());
        int his=Integer.parseInt(history.getText());
        int sci=Integer.parseInt(science.getText());
        int fact=Integer.parseInt(firstCategory.getText());
        int scat=Integer.parseInt(secondCategory.getText());
        int tcat=Integer.parseInt(secondCategory.getText());

        int total=sin+reli+eng+math+his+sci+fact+scat+tcat;
        tot.setText(Integer.toString(total));
    }
public void calculate(){


}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayinfo();
        display();

    }
}
