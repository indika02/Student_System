package home.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class Studentboard extends Login{

    @FXML
    private Label txtstdname;


    @FXML
    private Label date;


    @FXML
    private Label time;

    @FXML
    private Button logout;

    @FXML
    private Button btnpupdate;

    @FXML
    private Button btnresults;

    @FXML
    private Button timetable;


    @FXML
    private Button btnexit;

    @FXML
    void exit(ActionEvent event){
        Stage stage = (Stage) btnexit.getScene().getWindow();
        stage.close();
    }



    @FXML
    void timetable(ActionEvent event)throws IOException{
        //close current window
        timetable.getScene().getWindow().hide();
        //move to the next window
        Parent root= FXMLLoader.load(getClass().getResource("../ui/timetable.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.setScene(scene);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.show();
    }

    @FXML
    void results(ActionEvent event) throws IOException {
        //close current window
        btnresults.getScene().getWindow().hide();
        //move to the next window
        Parent root= FXMLLoader.load(getClass().getResource("../ui/show.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.setScene(scene);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.show();
    }


    @FXML
    void pupdate(ActionEvent event) throws IOException {
        //close current window
        btnpupdate.getScene().getWindow().hide();
        //move to the next window
        Parent root= FXMLLoader.load(getClass().getResource("../ui/profileupdate.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.setScene(scene);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.show();
    }


    @FXML
    void logout(ActionEvent event) throws IOException {
        //close current window
        logout.getScene().getWindow().hide();
        //move to the next window
        Parent root= FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.setScene(scene);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.show();
    }

    private volatile boolean stop=false;
    private void Timenow(){
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
Timenow();


    }


}
