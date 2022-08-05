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
import java.util.List;
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
    private Button btnSUBGRADE;

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
    private TextField marksindex;



    @FXML
    private Button btnshow;

    @FXML
    private Button btntime;

    @FXML
    private Button btnup;

    @FXML
    private Button btnok;

    @FXML
    private ComboBox<String> tgrade;

    @FXML
    private ImageView timetable;


    @FXML
    private Button btncal;

    @FXML
    private Button btnclear;

    @FXML
    private Button btnshowmarks;

    @FXML
    private Label sub1;
    @FXML
    private Label sub2;

    @FXML
    private Label sub3;
    @FXML
    private Label sub4;
    @FXML
    private Label sub5;
    @FXML
    private Label sub6;
    @FXML
    private Label sub7;

    @FXML
    private Label sub8;
    @FXML
    private Label sub9;
    @FXML
    private Label sub10;
    @FXML
    private Label sub11;

    @FXML
    private Label sub12;

    @FXML
    private TextField txtsub1;
    @FXML
    private TextField txtsub2;
    @FXML
    private TextField txtsub3;
    @FXML
    private TextField txtsub4;
    @FXML
    private TextField txtsub5;
    @FXML
    private TextField txtsub6;
    @FXML
    private TextField txtsub7;
    @FXML
    private TextField txtsub8;
    @FXML
    private TextField txtsub9;
    @FXML
    private TextField txtsub10;
    @FXML
    private TextField txtsub11;
    @FXML
    private TextField txtsub12;
    @FXML
    private TextField txttotal;

    @FXML
    private TextField txtavg;
    @FXML
    private Label lblsub1;
    @FXML
    private Label lblsub2;
    @FXML
    private Label lblsub3;
    @FXML
    private Label lblsub4;
    @FXML
    private Label lblsub5;
    @FXML
    private Label lblsub6;
    @FXML
    private Label lblsub7;
    @FXML
    private Label lblsub8;
    @FXML
    private Label lblsub9;
    @FXML
    private Label lblsub10;
    @FXML
    private Label lblsub11;
    @FXML
    private Label lblsub12;

    @FXML
    private TextField totalstd;








    @FXML
    private ComboBox<String> marksgrade;




    private FileInputStream fis;

    final FileChooser fc=new FileChooser();


    @FXML
    private Button btnexit;

    @FXML
    void SUBGRADE(ActionEvent event) throws IOException {
        btnSUBGRADE.getScene().getWindow().hide();
        //move to the next window
        Parent root= FXMLLoader.load(getClass().getResource("../ui/gradesub.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.setScene(scene);
        mainstage.show();
    }

    @FXML
    void exit(ActionEvent event){
        Stage stage = (Stage) btnexit.getScene().getWindow();
        stage.close();
    }

    @FXML
    void select(ActionEvent event) {
        int gra=Integer.parseInt(tgrade.getSelectionModel().getSelectedItem().toString());
        int marksg=Integer.parseInt(marksgrade.getSelectionModel().getSelectedItem().toString());

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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERROR!");
            alert.showAndWait();
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
        //String sql2="insert into subject_grade(g_id,sub_id)values(?,?)";
        //call method for display data of database to table
        try{
            prepare=connect.prepareStatement(sql);

            String t=txt_subno.getText();
            //String g_id=sgrade.getSelectionModel().getSelectedItem().toString();
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERROR!");
            alert.showAndWait();
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

        //String sql="SELECT s.sub_id,s.subject,sg.g_id FROM subject s INNER JOIN subject_grade sg ON s.sub_id=sg.sub_id";
        String sql="SELECT sub_id,subject FROM subject";

        try {
            prepare=connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()){
                int Subject_No=result.getInt("sub_id");
                //int grade=result.getInt("g_id");
                String Subject=result.getString("subject");


                SubjectSearchObservableList.add(new subject(Subject_No,Subject));
            }
            sub_id.setCellValueFactory(new PropertyValueFactory<subject, Integer>("Subject_No"));
           // Grade.setCellValueFactory(new PropertyValueFactory<subject,Integer>("grade"));
            subject.setCellValueFactory(new PropertyValueFactory<subject, String>("Subject"));

            tablestd.setItems(SubjectSearchObservableList);

            FilteredList<subject> filterdata1=new FilteredList<subject>(SubjectSearchObservableList, b -> true);
            subjectsearch.textProperty().addListener((observable,oldValue,newValue)->{
                filterdata1.setPredicate(UserSearchJava->{
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
            SortedList<subject> sortData=new SortedList<subject>(filterdata1);
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERROR!");
            alert.showAndWait();
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

    @FXML
    void ok(ActionEvent event){

        connect=jdbcconnect.getConnection();

        String sql="SELECT subject_grade.sub_id,subject.subject from subject_grade,subject WHERE subject.sub_id=subject_grade.sub_id AND g_id='"+marksgrade.getSelectionModel().getSelectedItem().toString()+"'";

        try{
            prepare=connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            Label[] labels={sub1,sub2,sub3,sub4,sub5,sub6,sub7,sub8,sub9,sub10,sub11,sub12};
            Label[] labels2={lblsub1,lblsub2,lblsub3,lblsub4,lblsub5,lblsub6,lblsub7,lblsub8,lblsub9,lblsub10,lblsub11,lblsub12};


                List subjects=new ArrayList();
                ArrayList sub_id=new ArrayList();


            while(result.next()){
                    sub_id.add(result.getString("sub_id"));
                    subjects.add(result.getString("subject"));

                }

                for (int i=0;i<=labels.length;i++){
                    labels[i].setText((String) subjects.get(i));
                    labels2[i].setText((String) sub_id.get(i));
                }


        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERROR!");
            alert.showAndWait();
        }
    }

    @FXML
    void calculate(ActionEvent event) throws SQLException {
        int marksg=Integer.parseInt(marksgrade.getSelectionModel().getSelectedItem().toString());
        int std_id=Integer.parseInt(marksindex.getText().toString());
        int lbl1=Integer.parseInt(lblsub1.getText());
        int lbl2=Integer.parseInt(lblsub2.getText());
        int lbl3=Integer.parseInt(lblsub3.getText());
        int lbl4=Integer.parseInt(lblsub4.getText());
        int lbl5=Integer.parseInt(lblsub5.getText());
        int lbl6=Integer.parseInt(lblsub6.getText());
        int lbl7=Integer.parseInt(lblsub7.getText());
        int lbl8=Integer.parseInt(lblsub8.getText());
        int lbl9=Integer.parseInt(lblsub9.getText());
        int lbl10=Integer.parseInt(lblsub10.getText());
        int lbl11=Integer.parseInt(lblsub11.getText());
        int lbl12=Integer.parseInt(lblsub12.getText());

connect =jdbcconnect.getConnection();
String sql="INSERT INTO student_exam(Student_ID,sub_id,g_id,marks,total,average) VALUES (?,?,?,?,?,?) WHERE subject_grade.g_id=sub_id";


try{
    prepare=connect.prepareStatement(sql);
    ResultSet result=prepare.executeQuery();
    int sb1=Integer.parseInt(txtsub1.getText());
    int sb2=Integer.parseInt(txtsub2.getText());
    int sb3=Integer.parseInt(txtsub3.getText());
    int sb4=Integer.parseInt(txtsub4.getText());
    int sb5=Integer.parseInt(txtsub5.getText());
    int sb6=Integer.parseInt(txtsub6.getText());
    int sb7=Integer.parseInt(txtsub7.getText());
    int sb8=Integer.parseInt(txtsub8.getText());
    int sb9=Integer.parseInt(txtsub9.getText());
    int sb10=Integer.parseInt(txtsub10.getText());
    int sb11=Integer.parseInt(txtsub11.getText());
    int sb12=Integer.parseInt(txtsub12.getText());
    int std=Integer.parseInt(totalstd.getText());

    if (marksg==10 || marksg==11){
        sb10=0;
        sb11=0;
        sb12=0;
        txtsub10.setText("0");
        txtsub11.setText("0");
        txtsub12.setText("0");
        int total=sb1+sb2+sb3+sb4+sb5+sb6+sb7+sb8+sb9+sb10+sb11+sb12;
        txttotal.setText(Integer.toString(total));

        double avg=((double) total/std);
        txtavg.setText(Double.toString(Double.parseDouble(String.format("%.2f",avg))));
    }else if (marksg==12 || marksg==13){
        sb6=0;
        sb7=0;
        sb8=0;
        sb9=0;
        sb10=0;
        sb11=0;
        sb12=0;
        txtsub6.setText("0");
        txtsub7.setText("0");
        txtsub8.setText("0");
        txtsub9.setText("0");
        txtsub10.setText("0");
        txtsub11.setText("0");
        txtsub12.setText("0");
        int total = sb1 + sb2 + sb3 + sb4 + sb5 + sb6 + sb7 + sb8 + sb9 + sb10 + sb11 + sb12;
        txttotal.setText(Integer.toString(total));

        double avg = ((double) total / std);
        txtavg.setText(Double.toString(Double.parseDouble(String.format("%.2f", avg))));

    }else {
        int total = sb1 + sb2 + sb3 + sb4 + sb5 + sb6 + sb7 + sb8 + sb9 + sb10 + sb11 + sb12;
        txttotal.setText(Integer.toString(total));

        double avg = ((double) total / std);
        txtavg.setText(Double.toString(Double.parseDouble(String.format("%.2f", avg))));
    }
}catch (Exception e){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setContentText("ERROR!");
    alert.showAndWait();
}
    }

    @FXML
    void clear(ActionEvent event) {
        sub1.setText("Subject 01");
        sub2.setText("Subject 02");
        sub3.setText("Subject 03");
        sub4.setText("Subject 04");
        sub5.setText("Subject 05");
        sub6.setText("Subject 06");
        sub7.setText("Subject 07");
        sub8.setText("Subject 08");
        sub9.setText("Subject 09");
        sub10.setText("Subject 10");
        sub11.setText("Subject 11");
        sub12.setText("Subject 12");
        lblsub1.setText("");
        lblsub2.setText("");
        lblsub3.setText("");
        lblsub4.setText("");
        lblsub5.setText("");
        lblsub6.setText("");
        lblsub7.setText("");
        lblsub8.setText("");
        lblsub9.setText("");
        lblsub10.setText("");
        lblsub11.setText("");
        lblsub12.setText("");
        txtsub1.setText("0");
        txtsub2.setText("0");
        txtsub3.setText("0");
        txtsub4.setText("0");
        txtsub5.setText("0");
        txtsub6.setText("0");
        txtsub7.setText("0");
        txtsub8.setText("0");
        txtsub9.setText("0");
        txtsub10.setText("0");
        txtsub11.setText("0");
        txtsub12.setText("0");
        txttotal.setText("");
        txtavg.setText("");




    }

    @FXML
    void showresults(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayinfo();
        display();
        Timenow();

        ObservableList<String> list= FXCollections.observableArrayList("06","07","08","09","10","11","12","13");
        tgrade.setItems(list);
        marksgrade.setItems(list);

    }
}