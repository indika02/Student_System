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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class pupdate{

    @FXML
    private Button btnexit;

    @FXML
    private Button btnsearch;

    @FXML
    private Button btnupdate;

    @FXML
    private TextField txtDoB;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtfname;

    @FXML
    private TextField txtgrade;

    @FXML
    private TextField txtindex;

    @FXML
    private TextField txtlname;

    @FXML
    private TextField txtsearch;

    @FXML
    private TextField txttel;


    private Connection connect;
    private PreparedStatement prepare;

    @FXML
    void exit(ActionEvent event) throws IOException {
        btnexit.getScene().getWindow().hide();
        //move to the next window
        Parent root= FXMLLoader.load(getClass().getResource("../ui/studentboard.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.setScene(scene);
        mainstage.show();
    }

    @FXML
    void search(ActionEvent event) {
        connect= jdbcconnect.getConnection();
        try {
            String sql = "SELECT Student_ID,Firstname,Lastname,DOB,Address,Telno,email,password,g_id FROM student WHERE Student_ID='" + txtsearch.getText() + "'";
            prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            if(result.next()){
                txtindex.setText(result.getString("Student_ID"));
                txtfname.setText(result.getString("Firstname"));
                txtlname.setText(result.getString("Lastname"));
                txtDoB.setText(result.getString("DOB"));
                txtaddress.setText(result.getString("Address"));
                txttel.setText(result.getString("Telno"));
                txtemail.setText(result.getString("email"));
                txtgrade.setText(result.getString("g_id"));

                txtsearch.setText("");
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Invalid Index Number.Please Enter Your Correct Index Number!");
                alert.showAndWait();
                txtsearch.setText("");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @FXML
    void update(ActionEvent event) {
        connect= jdbcconnect.getConnection();

        int value1=Integer.parseInt(txtindex.getText());
        String value2=txtfname.getText();
        String value3=txtlname.getText();
        String value4=txtDoB.getText();
        String value5=txtaddress.getText();
        int value6=Integer.parseInt(txttel.getText());
        String value7=txtemail.getText();
        int value8=Integer.parseInt(txtgrade.getText());
        try{
            String sql = "update student set Student_ID= '"+value1+"',Firstname= '"+
                    value2+"',Lastname='"+value3+"',DOB='"+value4+"',Address='"+value5+"',Telno='"+value6+"',email='"+value7+"',g_id='"+value8+"' where Student_ID= '"+value1+"' ";
            prepare=connect.prepareStatement(sql);
            prepare.execute();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("OK?");
            alert.showAndWait();
            txtsearch.setText("");
            txtfname.setText("");
            txtlname.setText("");
            txtDoB.setText("");
            txtaddress.setText("");
            txttel.setText("");
            txtemail.setText("");
            txtgrade.setText("");
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
