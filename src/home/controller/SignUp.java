package home.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    @FXML
    private ComboBox comb;

    @FXML
    void select(ActionEvent event) {
        String g=comb.getSelectionModel().getSelectedItem().toString();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> list= FXCollections.observableArrayList("None","Grade 06","Grade 07","Grade 08","Grade 09","Grade 10","Grade 11","Grade 12","Grade 13");
        comb.setItems(list);
    }

}
