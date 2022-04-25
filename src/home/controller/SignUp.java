package home.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    @FXML
    private Button btnsignup;


    @FXML
    private ComboBox comb;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtfname;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtlname;

    @FXML
    private PasswordField txtpwd;

    @FXML
    private TextField txttel;

    @FXML
    void select(ActionEvent event) {

        String g=comb.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void signup(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> list= FXCollections.observableArrayList("None","Grade 06","Grade 07","Grade 08","Grade 09","Grade 10","Grade 11","Grade 12","Grade 13");

        comb.setItems(list);

    }

}
