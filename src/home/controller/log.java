package home.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class log {

    @FXML
    private Button student;

    @FXML
    private Button teacher;

    @FXML
    private Button btnback;

    @FXML
    void btnlogst(ActionEvent event) throws IOException {
        //close current window
       student.getScene().getWindow().hide();
        //move to the next window
        Parent root= FXMLLoader.load(getClass().getResource("../ui/Sign up.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.setScene(scene);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.show();
    }

    @FXML
    void btnlogt(ActionEvent event) throws IOException {
        //close current window
        teacher.getScene().getWindow().hide();
        //move to the next window
        Parent root=FXMLLoader.load(getClass().getResource("../ui/teachersignup.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.setScene(scene);
        mainstage.show();
    }
    @FXML
    void back(ActionEvent event) throws IOException {
        //close current window
        btnback.getScene().getWindow().hide();
        //move to the next window
        Parent root=FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.setScene(scene);
        mainstage.show();
    }

    @FXML
    private Button btnexit;

    @FXML
    void exit(ActionEvent event){
        Stage stage = (Stage) btnexit.getScene().getWindow();
        stage.close();
    }

}

