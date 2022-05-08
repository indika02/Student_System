package home.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Adminpanal {

    @FXML
    private Button btnlogout;

    @FXML
    private Button btnadd;

    @FXML
    private Button btnremove;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<users, String> grade;

    @FXML
    private TableColumn<users, String> subject;

    @FXML
    private TableView<users> table;

    @FXML
    private TextField txtgrade;

    @FXML
    private TextField txtsub;

    //mysql connection variables
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    void add(ActionEvent event) {
        connect=jdbcconnect.getConnection();

        String grade=txtgrade.getText();
        String sub=txtsub.getText();

        try{
            String sql="insert into subject('Grade','Subject')values(?,?)";
            prepare=connect.prepareStatement(sql);
            prepare.setString(1,txtgrade.getText());
            prepare.setString(2,txtsub.getText());
            result=prepare.executeQuery();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    @FXML
    void remove(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

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
}
