package home.controller;


import javafx.application.Platform;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Date;

public class Adminpanal implements Initializable {







    @FXML
    private Label time;

    @FXML
    private TableColumn<user, Integer> Telno;

    @FXML
    private TableColumn<user, String > Firstname;

    @FXML
    private TableColumn<user, String> Lastname;

    @FXML
    private TableColumn<user,Integer> Student_ID;



    @FXML
    private TableColumn<user, String> DOB;

    @FXML
    private TableColumn<user, String> Address;


    @FXML
    private TableColumn<user, String> email;

    @FXML
    private TableColumn<user, Integer> g_id;

    @FXML
    private TableColumn<user,String> clazz;


    @FXML
    private TextField searchfield;


    @FXML
    private Button btncal;

    @FXML
    private Label date;


    @FXML
    private Button btnreset;

    @FXML
    private TableView<user> tableuser;
    @FXML
    private Button btnlogout;

    @FXML
    private TextField subjectsearch;

    @FXML
    private TableColumn<subject, Integer > sub_id;

    @FXML
    private TableColumn<subject, Integer> Grade;

    @FXML
    private TableColumn<subject, String> subject;

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
    private Button btnshow;

    @FXML
    private Button btntime;

    @FXML
    private Button btnup;

    @FXML
    private ComboBox<String> tgrade;

    @FXML
    private ImageView timetable;


    @FXML
    private ComboBox<String> sgrade;

    private FileInputStream fis;

    final FileChooser fc=new FileChooser();

    @FXML
    private Button btnexit;

    @FXML
    void exit(ActionEvent event){
        Stage stage = (Stage) btnexit.getScene().getWindow();
        stage.close();
    }

    @FXML
    void select(ActionEvent event) {
        int gra=Integer.parseInt(tgrade.getSelectionModel().getSelectedItem().toString());
        int g_id=Integer.parseInt(sgrade.getSelectionModel().getSelectedItem().toString());

    }

    @FXML
    void uploadtime(ActionEvent event) {
        fc.setTitle("My file Chooser");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png*", "*.jpg*", "*.gif*"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            timetable.setImage(new Image(file.toURI().toString()));
        } else {
            System.out.println("A file is invalid");
        }
        connect = jdbcconnect.getConnection();
        String sql = "insert into timetable(timetable,g_id)values(?,?)";
        try {
            prepare = connect.prepareStatement(sql);

            int gra=Integer.parseInt(tgrade.getSelectionModel().getSelectedItem().toString());

            fis = new FileInputStream(file);
            prepare.setBinaryStream(1, (InputStream) fis);
            prepare.setInt(2, gra);
            prepare.execute();
            txttgra.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void updatetime(ActionEvent event) {
        try{
            connect=jdbcconnect.getConnection();
            int gra=Integer.parseInt(tgrade.getSelectionModel().getSelectedItem().toString());

            String sql = "DELETE timetable FROM timetable WHERE g_id='"+gra+"'";
            prepare=connect.prepareStatement(sql);
            prepare.execute();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("OK");
            alert.showAndWait();
            display();

        }
        catch (Exception e){
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERROR!");
            alert.showAndWait();
        }

    }

    int index=-1;



    //mysql connection variables
    private Connection connect;
    private PreparedStatement prepare;
private PreparedStatement prepare2;

    @FXML
    void add(ActionEvent event) {
        connect=jdbcconnect.getConnection();
        String sql="insert into subject(sub_id,subject)values(?,?)";
        String sql2="insert into subject_grade(g_id,sub_id)values(?,?)";
        //call method for display data of database to table
        try{
            prepare=connect.prepareStatement(sql);
            prepare2=connect.prepareStatement(sql2);
            String t=txt_subno.getText();
            String g_id=sgrade.getSelectionModel().getSelectedItem().toString();
            String sub=txtsub.getText();
            if(t=="" && sub==""){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("ERROR!");
                alert.showAndWait();
            }else {
                prepare.setString(1,t);
                prepare.setString(2, sub);
                prepare.execute();
                prepare2.setString(1,g_id);
                prepare2.setString(2,t);

                prepare2.execute();

                display();
                txt_subno.setText("");
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
        txt_subno.setText(sub_id.getCellData(index).toString());
        txtsub.setText(subject.getCellData(index).toString());

    }





    public void Delete(){

        connect=jdbcconnect.getConnection();
        String sql = "delete from subject where sub_id = ?";
        String sql2="DELETE FROM subject_grade where sub_id=?";
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, txt_subno.getText());
            prepare.execute();
            prepare2=connect.prepareStatement(sql2);
            prepare2.setString(1,txt_subno.getText());
            prepare2.execute();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("OK");
            alert.showAndWait();
            display();
            txt_subno.setText("");
            txtsub.setText("");

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

        String sql="SELECT s.sub_id,s.subject,sg.g_id FROM subject s INNER JOIN subject_grade sg ON s.sub_id=sg.sub_id";

        try {
            prepare=connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()){
                int Subject_No=result.getInt("sub_id");
                int grade=result.getInt("g_id");
                String Subject=result.getString("subject");


                SubjectSearchObservableList.add(new subject(Subject_No,grade,Subject));
            }
            sub_id.setCellValueFactory(new PropertyValueFactory<subject, Integer>("Subject_No"));
            Grade.setCellValueFactory(new PropertyValueFactory<subject,Integer>("grade"));
            subject.setCellValueFactory(new PropertyValueFactory<subject, String>("Subject"));

            tablestd.setItems(SubjectSearchObservableList);

            FilteredList<subject> filterdata=new FilteredList<subject>(SubjectSearchObservableList, b -> true);
            subjectsearch.textProperty().addListener((observable,oldValue,newValue)->{
                filterdata.setPredicate(UserSearchJava->{
                    if(newValue.isEmpty() || newValue==null){
                        return true;
                    }
                    String searchkeyword=newValue.toLowerCase();
                    if(UserSearchJava.getSubject().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else {
                        return false;
                    }});
            });
            SortedList<subject> sortData=new SortedList<subject>(filterdata);
            sortData.comparatorProperty().bind(tablestd.comparatorProperty());

            tablestd.setItems(sortData);
        }catch (Exception e){
            System.out.println(e);
        }
        sub_id.setCellValueFactory(new PropertyValueFactory<subject,Integer>("Subject_No"));
        subject.setCellValueFactory(new PropertyValueFactory<subject, String>("Subject"));
        ObservableList<subject> listM;
        listM=jdbcconnect.getDatausers();
        tablestd.setItems(listM);
    }




    //display users data into table
    public void displayinfo(){
        connect=jdbcconnect.getConnection();

        String sql="SELECT Student_ID,Firstname,Lastname,DOB,Address,Telno,email,g_id,class.clazz FROM student,class where student.class_id=class.class_id";
        try {
            prepare=connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()){

                String Firstname=result.getString("Firstname");
                String Lastname=result.getString("Lastname");
                int Student_ID=result.getInt("Student_ID");
                String DOB=result.getString("DOB");
                String Address=result.getString("Address");
                String email=result.getString("email");
                int g_id=result.getInt("g_id");
                int Telno = result.getInt("Telno");
                String clazz=result.getString("clazz");


                UsersearchJavaObservableList.add(new user(Student_ID,Firstname,Lastname,DOB,Address,email,g_id,Telno,clazz));
            }
            Firstname.setCellValueFactory(new PropertyValueFactory<user, String>("Firstname"));
            Lastname.setCellValueFactory(new PropertyValueFactory<user, String>("Lastname"));
            Student_ID.setCellValueFactory(new PropertyValueFactory<user, Integer>("Student_ID"));
            DOB.setCellValueFactory(new PropertyValueFactory<user, String>("DOB"));
            Address.setCellValueFactory(new PropertyValueFactory<user,String>("address"));
            email.setCellValueFactory(new PropertyValueFactory<user,String>("email"));
            g_id.setCellValueFactory(new PropertyValueFactory<user,Integer>("g_id"));
            clazz.setCellValueFactory(new PropertyValueFactory<user,String>("Clazz"));

            Telno.setCellValueFactory(new PropertyValueFactory<user,Integer>("Telno"));

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
                    }else if (Integer.toString(UserSearchJava.getStudent_ID()).indexOf(searchkeyword)>-1){
                        return true;
                    }else if (UserSearchJava.getAddress().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if (Integer.toString(UserSearchJava.getG_id()).indexOf(searchkeyword)>-1){
                        return true;
                    }else if (UserSearchJava.getClazz().toLowerCase().indexOf(searchkeyword)>-1) {
                        return true;
                    }else {
                        return false;
                }});
            });
            SortedList<user> sortData=new SortedList<>(filterdata);
            sortData.comparatorProperty().bind(tableuser.comparatorProperty());

            tableuser.setItems(sortData);
        }catch (Exception e){
            System.out.println(e);
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
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.setScene(scene);
        mainstage.show();
    }




        @FXML
        void show(ActionEvent event) throws IOException {
            btnshow.getScene().getWindow().hide();
            //move to the next window
            Parent root= FXMLLoader.load(getClass().getResource("../ui/Show.fxml"));
            Stage mainstage=new Stage();
            Scene scene=new Scene(root);
            mainstage.initStyle(StageStyle.UNDECORATED);
            mainstage.setScene(scene);
            mainstage.show();
        }
private volatile boolean stop=false;
    public void Timenow(){
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            while(!stop){
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() -> {
                    time.setText(timenow);
                    date.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd")));// This is the label
                });
            }
        });
        thread.start();
    }
    private ObservableList<String> s = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        connect = jdbcconnect.getConnection();

        String sql = " SELECT g_id FROM grade";
        try {
            prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                s.add(result.getString("g_id"));
                sgrade.setItems(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        displayinfo();
        display();
        Timenow();

        ObservableList<String> list= FXCollections.observableArrayList("06","07","08","09","10","11","12","13");
        tgrade.setItems(list);

    }
}