package home.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class gradesub implements Initializable {


    @FXML
    private TableColumn<home.controller.subject, Integer> g_id;

    @FXML
    private TableView<subject_grade> gradesubject;

    @FXML
    private TableColumn<home.controller.subject, Integer> sub_id;

    @FXML
    private TableColumn<home.controller.subject, String> subject;

    @FXML
    private ComboBox<String> sgrade;

    @FXML
    private Button btngradesubadd;

    @FXML
    private Button btngradesubdel;

    @FXML
    private Button btnexit;

    @FXML
    private Label lblsub;

    @FXML
    private TextField searchfield;

    @FXML
    private TextField txtsubno;

    @FXML
    private Label txtsub;



    @FXML
    private ComboBox<String> sub1;





    private Connection connect;
    private PreparedStatement prepare;
    private PreparedStatement prepare2;
    private PreparedStatement prepare5;

    @FXML
    void select(ActionEvent event) {
//        int g_id=Integer.parseInt(sgrade.getSelectionModel().getSelectedItem().toString());

        String sub=sub1.getSelectionModel().getSelectedItem().toString();

        connect=jdbcconnect.getConnection();

        String sql3="SELECT sub_id FROM subject where subject='"+sub+"'";

        try{
            prepare = connect.prepareStatement(sql3);
            ResultSet result = prepare.executeQuery();


            if (result.next()){
                int id=Integer.parseInt(result.getString("sub_id"));
                lblsub.setText(Integer.toString(id));
                System.out.println(id);
            }
        }catch (Exception e){
            System.out.println(e);
        }



    }

    @FXML
    void graadd(ActionEvent event) {
        connect=jdbcconnect.getConnection();




        try{
            String sql4="INSERT INTO subject_grade(g_id,sub_id) VALUES(?,?)";
            prepare = connect.prepareStatement(sql4);
            int g_id=Integer.parseInt(sgrade.getSelectionModel().getSelectedItem().toString());
            int sub=Integer.parseInt(lblsub.getText());

                prepare.setString(1, Integer.toString(g_id));
                prepare.setString(2, Integer.toString(sub));
                prepare.execute();
                display();


        }catch (Exception e){
            System.out.println(e);

        }
    }
    int index=-1;
    @FXML
    void getSelected() {
        index =gradesubject.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtsubno.setText(g_id.getCellData(index).toString());


    }

    @FXML
    void graDelete(ActionEvent event) {
        connect=jdbcconnect.getConnection();

        String sql6="DELETE  FROM subject_grade WHERE g_id=?";
        try {
            assert connect != null;
            prepare5 = connect.prepareStatement(sql6);
            prepare5.setString(1,txtsubno.getText());
            prepare5.execute();
            display();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("OK");
            alert.showAndWait();



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ERROR!");
            alert.showAndWait();
        }
    }
    ObservableList<subject_grade> SubjectSearchObservableList=FXCollections.observableArrayList();
    public void display(){
        connect=jdbcconnect.getConnection();

        String sql="SELECT sg.g_id,s.sub_id,s.subject FROM subject s INNER JOIN subject_grade sg ON s.sub_id=sg.sub_id";
        try {
            prepare=connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()){
                int grade=result.getInt("g_id");
                int Subject_No=result.getInt("sub_id");

                String Subject=result.getString("subject");


                SubjectSearchObservableList.add(new subject_grade(grade,Subject_No,Subject));
            }
            g_id.setCellValueFactory(new PropertyValueFactory<home.controller.subject, Integer>("Grade"));
            sub_id.setCellValueFactory(new PropertyValueFactory<home.controller.subject, Integer>("Subject_no"));
            subject.setCellValueFactory(new PropertyValueFactory<home.controller.subject, String>("Subject"));
            gradesubject.setItems(SubjectSearchObservableList);

            FilteredList<subject_grade> filterdata=new FilteredList<subject_grade>(SubjectSearchObservableList, b -> true);
            searchfield.textProperty().addListener((observable,oldValue,newValue)->{
                filterdata.setPredicate(UserSearchJava->{
                    if(newValue.isEmpty() || newValue==null){
                        return true;
                    }
                    String searchkeyword=newValue.toLowerCase();
                    if(Integer.toString(UserSearchJava.getGrade()).indexOf(searchkeyword)>-1){
                        return true;
                    }else if(UserSearchJava.getSubject().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else {
                        return false;
                    }});
            });
            SortedList<subject_grade> sortData=new SortedList<>(filterdata);
            sortData.comparatorProperty().bind(gradesubject.comparatorProperty());

            gradesubject.setItems(sortData);
        }catch (Exception e){
            System.out.println(e);
        }
        g_id.setCellValueFactory(new PropertyValueFactory<subject,Integer>("Grade"));
        sub_id.setCellValueFactory(new PropertyValueFactory<subject,Integer>("Subject_no"));
        subject.setCellValueFactory(new PropertyValueFactory<subject, String>("Subject"));
        ObservableList<subject_grade> listM;
        listM=jdbcconnect.getsubject();
        gradesubject.setItems(listM);
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        btnexit.getScene().getWindow().hide();
        //move to the next window
        Parent root= FXMLLoader.load(getClass().getResource("../ui/Adminpanal.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.setScene(scene);
        mainstage.show();
    }

    private ObservableList<String> s = FXCollections.observableArrayList();
    private ObservableList<String> g=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {



        connect = jdbcconnect.getConnection();

        String sql = " SELECT g_id FROM grade";
        String sql2="SELECT subject FROM subject";
        try {
            prepare = connect.prepareStatement(sql);
            prepare2=connect.prepareStatement(sql2);
            ResultSet result = prepare.executeQuery();
            ResultSet re=prepare2.executeQuery();

            while (result.next()) {
                s.add(result.getString("g_id"));
                sgrade.setItems(s);
            }
            while(re.next()){
                g.add(re.getString("subject"));
                sub1.setItems(g);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
display();
    }
}
