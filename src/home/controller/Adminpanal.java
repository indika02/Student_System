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
    private TextField Mathematics;

    @FXML
    private TextField Religious;

    @FXML
    private TextField Sinhala;
    @FXML
    private TextField English;

    @FXML
    private Label tot;

    @FXML
    private Label time;

    @FXML
    private Label avg;

    @FXML
    private TextField firstCategory;

    @FXML
    private TextField secondCategory;

    @FXML
    private TextField thirdCategory;

    @FXML
    private TextField history;



    @FXML
    private TextField science;

    @FXML
    private TextField nodtd;


    @FXML
    private TextField Enrollment_no;


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
    private TableColumn<subject, String> Grade;

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
    private TextField g;

    @FXML
    private TextField c;

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
    void updatetime(ActionEvent event) {

    }
    private FileInputStream fis;

    final FileChooser fc=new FileChooser();

    @FXML
    void select(ActionEvent event) {
        int gra=Integer.parseInt(tgrade.getSelectionModel().getSelectedItem().toString());

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
        String sql="insert into subject(sub_id,subject)values(?,?)";
        //call method for display data of database to table
        try{
            prepare=connect.prepareStatement(sql);
            String t=txt_subno.getText();

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


    public void Edit(){
        try{
            connect=jdbcconnect.getConnection();
            String value1=txt_subno.getText();

            String value3=txtsub.getText();

            String sql = "update subject set sub_id= '"+value1+"',subject= '"+
                    value3+"' where sub_id= '"+value1+"' ";
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
        String sql = "delete from subject where sub_id = ?";
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, txt_subno.getText());
            prepare.execute();
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

        String sql="SELECT sub_id,subject from subject";

        try {
            prepare=connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()){
                int Subject_No=result.getInt("sub_id");

                String Subject=result.getString("subject");


                SubjectSearchObservableList.add(new subject(Subject_No,Subject));
            }
            sub_id.setCellValueFactory(new PropertyValueFactory<subject, Integer>("Subject_No"));
            subject.setCellValueFactory(new PropertyValueFactory<subject, String>("Subject"));

            tablestd.setItems(SubjectSearchObservableList);

            FilteredList<subject> filterdata=new FilteredList<subject>(SubjectSearchObservableList, b -> true);
            subjectsearch.textProperty().addListener((observable,oldValue,newValue)->{
                filterdata.setPredicate(UserSearchJava->{
                    if(newValue.isEmpty() || newValue==null){
                        return true;
                    }
                    String searchkeyword=newValue.toLowerCase();
                    if(UserSearchJava.getSubject_No().toString().indexOf(searchkeyword)>-1){
                        return true;
                    }else
                        return false;
                });
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

        String sql="SELECT Student_ID,Firstname,Lastname,DOB,Address,Telno,email,g_id FROM student";
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

                UsersearchJavaObservableList.add(new user(Student_ID,Firstname,Lastname,DOB,Address,email,g_id,Telno));
            }
            Firstname.setCellValueFactory(new PropertyValueFactory<user, String>("Firstname"));
            Lastname.setCellValueFactory(new PropertyValueFactory<user, String>("Lastname"));
            Student_ID.setCellValueFactory(new PropertyValueFactory<user, Integer>("Student_ID"));
            DOB.setCellValueFactory(new PropertyValueFactory<user, String>("DOB"));
            Address.setCellValueFactory(new PropertyValueFactory<user,String>("address"));
            email.setCellValueFactory(new PropertyValueFactory<user,String>("email"));
            g_id.setCellValueFactory(new PropertyValueFactory<user,Integer>("g_id"));

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
                    }
                    else if (UserSearchJava.getDOB().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if (UserSearchJava.getAddress().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if (Integer.toString(UserSearchJava.getG_id()).indexOf(searchkeyword)>-1){
                        return true;
                    }else
                        return false;
                });
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
    void btncal(ActionEvent event){
        connect=jdbcconnect.getConnection();
        String sql="insert into sub_marks(Enrollment_No,grade,Clzz,sinhala,religious,english,mathematics,history,science,fstcategory,sndcategory,trdcategory,total,average)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            prepare=connect.prepareStatement(sql);

            String Enro=Enrollment_no.getText();
            String gr=g.getText();
            String cl=c.getText();
            int notstd = Integer.parseInt(nodtd.getText());
            int sin = Integer.parseInt(Sinhala.getText());
            int reli = Integer.parseInt(Religious.getText());
            int eng = Integer.parseInt(English.getText());
            int math = Integer.parseInt(Mathematics.getText());
            int his = Integer.parseInt(history.getText());
            int sci = Integer.parseInt(science.getText());
            int fact = Integer.parseInt(firstCategory.getText());
            int scat = Integer.parseInt(secondCategory.getText());
            int tcat = Integer.parseInt(thirdCategory.getText());

            int total = sin + reli + eng + math + his + sci + fact + scat + tcat;
            tot.setText(Integer.toString(total));
            double average = ((double) total / notstd);


            avg.setText(Double.toString(Double.parseDouble(String.format("%.2f", average))));

            prepare.setString(1,Enro);
            prepare.setString(2,gr);
            prepare.setString(3,cl);
            prepare.setInt(4,sin);
            prepare.setInt(5,reli);
            prepare.setInt(6,eng);
            prepare.setInt(7,math);
            prepare.setInt(8,his);
            prepare.setInt(9,sci);
            prepare.setInt(10,fact);
            prepare.setInt(11,scat);
            prepare.setInt(12,tcat);
            prepare.setInt(13,total);
            prepare.setDouble(14,average);

            prepare.execute();



        }catch (Exception e){
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Incorrect Details.Enter Your details correctly!");
            alert.showAndWait();
        }
    }
    @FXML
    void reset(ActionEvent event){
        Enrollment_no.setText("");
        nodtd.setText("");
        g.setText("");
        c.setText("");
        Sinhala.setText("");
        Religious.setText("");
        English.setText("");
        Mathematics.setText("");
        history.setText("");
        science.setText("");
        firstCategory.setText("");
        secondCategory.setText("");
        thirdCategory.setText("");
        tot.setText("");
        avg.setText("");



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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayinfo();
        display();
        Timenow();

        ObservableList<String> list= FXCollections.observableArrayList("06","07","08","09","10","11","12","13");
        tgrade.setItems(list);

    }
}