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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Show implements Initializable {

    @FXML
    private Button btnexit;

        @FXML
        private TableColumn<marks, Double> Average;

        @FXML
        private TableColumn<marks, String> Clzz;

        @FXML
        private TableColumn<marks, String> Enrollment_No;

        @FXML
        private TableColumn<marks, Integer> english;

        @FXML
        private TableColumn<marks, Integer> fstcategory;

        @FXML
        private TableColumn<marks, String> grade;

        @FXML
        private TableColumn<marks, Integer> history;

        @FXML
        private TableView<marks> markstable;

        @FXML
        private TableColumn<marks, Integer> mathematics;

        @FXML
        private TableColumn<marks, Integer> religious;

        @FXML
        private TableColumn<marks, Integer> science;

        @FXML
        private TableColumn<marks, Integer> sinhala;

        @FXML
        private TableColumn<marks, Integer> sndcategory;

        @FXML
        private TableColumn<marks, Integer> total;

        @FXML
        private TableColumn<marks, Integer> trdcategory;

        @FXML
        private TextField markssearch;

    ObservableList<marks> marksSearchObservableList= FXCollections.observableArrayList();
    private Connection connect;
    private PreparedStatement prepare;

    public void displaymarks(){

        connect=jdbcconnect.getConnection();

        String sql="SELECT Enrollment_No,grade,Clzz,sinhala,religious,english,mathematics,history,science,fstcategory,sndcategory,trdcategory,total,average from sub_marks";
        try {
            prepare=connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()){
                String Enrollment_No=result.getString("Enrollment_No");
                String grade=result.getString("grade");
                String Clzz=result.getString("Clzz");
                int sinhala=result.getInt("sinhala");
                int religious=result.getInt("religious");
                int english=result.getInt("english");
                int mathematics=result.getInt("mathematics");
                int history=result.getInt("history");
                int science=result.getInt("science");
                int fstcategory=result.getInt("fstcategory");
                int sndcategory=result.getInt("sndcategory");
                int trdcategory = result.getInt("trdcategory");
                int total=result.getInt("total");
                double Average=result.getInt("Average");

                marksSearchObservableList.add(new marks(Enrollment_No,grade,Clzz,sinhala,religious,english,mathematics,history,science,fstcategory,sndcategory,trdcategory,total,Average));
            }
            Enrollment_No.setCellValueFactory(new PropertyValueFactory<marks, String>("Enrollment_No"));
            grade.setCellValueFactory(new PropertyValueFactory<marks, String>("grade"));
            Clzz.setCellValueFactory(new PropertyValueFactory<marks, String>("Clzz"));
            sinhala.setCellValueFactory(new PropertyValueFactory<marks,Integer>("sinhala"));
            religious.setCellValueFactory(new PropertyValueFactory<marks,Integer>("religious"));
            english.setCellValueFactory(new PropertyValueFactory<marks,Integer>("english"));
            mathematics.setCellValueFactory(new PropertyValueFactory<marks,Integer>("mathematics"));
            history.setCellValueFactory(new PropertyValueFactory<marks,Integer>("history"));
            science.setCellValueFactory(new PropertyValueFactory<marks,Integer>("science"));
            fstcategory.setCellValueFactory(new PropertyValueFactory<marks,Integer>("fstcategory"));
            sndcategory.setCellValueFactory(new PropertyValueFactory<marks,Integer>("sndcategory"));
            trdcategory.setCellValueFactory(new PropertyValueFactory<marks,Integer>("trdcategory"));
            total.setCellValueFactory(new PropertyValueFactory<marks,Integer>("total"));
            Average.setCellValueFactory(new PropertyValueFactory<marks,Double>("Average"));

            markstable.setItems(marksSearchObservableList);

            FilteredList<marks> filterdata=new FilteredList<marks>(marksSearchObservableList, b -> true);
            markssearch.textProperty().addListener((observable,oldValue,newValue)->{
                filterdata.setPredicate(UserSearchJava->{
                    if(newValue.isEmpty() || newValue==null){
                        return true;
                    }
                    String searchkeyword=newValue.toLowerCase();
                    if(UserSearchJava.Enrollment_No.toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if(UserSearchJava.getGrade().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else if (UserSearchJava.getClzz().toLowerCase().indexOf(searchkeyword)>-1){
                        return true;
                    }else
                        return false;
                });
            });
            SortedList<marks> sortData=new SortedList<>(filterdata);
            sortData.comparatorProperty().bind(markstable.comparatorProperty());

            markstable.setItems(sortData);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        btnexit.getScene().getWindow().hide();
        //move to the next window
        Parent root= FXMLLoader.load(getClass().getResource("../ui/Adminpanal.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.setScene(scene);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displaymarks();


    }



    }


