package home.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Adminpanal implements Initializable {

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

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

@Override
    public void initialize(URL url, ResourceBundle rb){

}

}
