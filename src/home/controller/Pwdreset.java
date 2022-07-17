package home.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Pwdreset {

    @FXML
    private Button btnreset;

    @FXML
    private TextField pwd;

    @FXML
    private TextField rpwd;

    @FXML
    private TextField uname;

    private Connection connect;
    private PreparedStatement prepare;

    @FXML
    void reset(ActionEvent event) {
        try{
            connect=jdbcconnect.getConnection();
            String value1=uname.getText();
            String value2=pwd.getText();
            String value3=rpwd.getText();
//            if(value2==value3){
                String sql = "update users set Password= '"+value2+"'where Username= '"+value1+"' ";
                prepare=connect.prepareStatement(sql);
                prepare.execute();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("OK");
                alert.showAndWait();
                btnreset.getScene().getWindow().hide();
                //move to the next window
                Parent root= FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
                Stage mainstage=new Stage();
                Scene scene=new Scene(root);
                mainstage.setScene(scene);
                mainstage.show();
//            }else{
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setContentText("Doesn't Match the Password!");
//                alert.showAndWait();
//            }



        }catch (Exception e){
//            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERROR!");
            alert.showAndWait();
        }
    }

}
