package home.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.*;

public class Adminpanal {

    @FXML
    private Button btnok;

    @FXML
    private TextField subid;

    @FXML
    private TextField subject;


    //mysql connection variables
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;


    @FXML
    void ok(ActionEvent event) throws SQLException {
        //mysql conncection
        connect=jdbcconnect.getConnection();

        String id=subid.getText();
        String sub=subject.getText();


        try {

            if (subid.getText().isEmpty() || subject.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Enter the data!");
            }else {
                //sql input query

                String sql = "insert into subject(`Subject ID`,`Subject`) values(?,?)";
                prepare = connect.prepareStatement(sql);

                //database data input
                prepare.setString(1,id);
                prepare.setString(2,sub);
                subid.setText("");
                subject.setText("");

                prepare.executeUpdate();
                subid.setText("");
                subject.setText("");
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }
}
