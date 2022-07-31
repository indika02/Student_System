package home.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Pwdreset implements Initializable {

    @FXML
    private Button btnreset;

    @FXML
    private Button btnexit;

    @FXML
    private TextField pwd;

    @FXML
    private TextField rpwd;

    @FXML
    private TextField uname;

    @FXML
    private ComboBox<String> selectcomd;

    private Connection connect;

    private Connection connect1;
    private PreparedStatement prepare;
    private PreparedStatement prepare1;

    @FXML
    void exit(ActionEvent event) throws IOException {
        btnexit.getScene().getWindow().hide();
        //move to the next window
        Parent root= FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.setScene(scene);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.show();
    }

    @FXML
    void select(ActionEvent event) {
        String t=selectcomd.getSelectionModel().getSelectedItem().toString();
    }


    @FXML
    void reset(ActionEvent event) {
        try {
            connect = jdbcconnect.getConnection();
            String value1 = uname.getText();
            String value2 = pwd.getText();
            String value3 = rpwd.getText();
            String t = selectcomd.getSelectionModel().getSelectedItem().toString();
            if (t.equals("teacher")) {
                String sql = "update teacher set password= '" + value2 + "'where email= '" + value1 + "' AND status= '" + t + "' ";
                prepare = connect.prepareStatement(sql);
                prepare.execute();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("OK");
                alert.showAndWait();
                btnreset.getScene().getWindow().hide();
                //move to the next window
                Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
                Stage mainstage = new Stage();
                Scene scene = new Scene(root);
                mainstage.setScene(scene);
                mainstage.initStyle(StageStyle.UNDECORATED);
                mainstage.show();
            } else {
                connect1 = jdbcconnect.getConnection();
                String val1 = uname.getText();
                String val2 = pwd.getText();
                String y = selectcomd.getSelectionModel().getSelectedItem().toString();
                String sql2 = "update student set password= '" + val2 + "'where email= '" + val1 + "' AND status= '" + y + "' ";
                prepare1 = connect1.prepareStatement(sql2);
                prepare1.execute();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("OK");
                alert.showAndWait();
                btnreset.getScene().getWindow().hide();
                //move to the next window
                Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
                Stage mainstage = new Stage();
                Scene scene = new Scene(root);
                mainstage.setScene(scene);
                mainstage.initStyle(StageStyle.UNDECORATED);
                mainstage.show();
            }


        } catch (Exception e) {
//            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERROR!");
            alert.showAndWait();
            System.out.println(e);
        }
    }

        @Override
    public void initialize(URL url, ResourceBundle rb){

        ObservableList<String> listt= FXCollections.observableArrayList("Teacher","Student");
        selectcomd.setItems(listt);




    }

}
