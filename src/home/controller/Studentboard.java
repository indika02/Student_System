package home.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class Studentboard {

    @FXML
    private Button btnok;

    @FXML
    private ComboBox comb;

    @FXML
    private Label txtg;

    @FXML
    void select(ActionEvent event) {

        String g=comb.getSelectionModel().getSelectedItem().toString();
    }



}
