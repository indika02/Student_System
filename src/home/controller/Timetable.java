package home.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Timetable implements Initializable {

    @FXML
    private Button exit;

    @FXML
    private Button ok;

    @FXML
    private ComboBox<String> tgrade;

    @FXML
    private ImageView timeimage;

    @FXML
    void exit(ActionEvent event) throws IOException {
        exit.getScene().getWindow().hide();
        //move to the next window
        Parent root= FXMLLoader.load(getClass().getResource("../ui/studentboard.fxml"));
        Stage mainstage=new Stage();
        Scene scene=new Scene(root);
        mainstage.setScene(scene);
        mainstage.initStyle(StageStyle.UNDECORATED);
        mainstage.show();
    }

    private FileInputStream fis;

    private Connection connect;
    private PreparedStatement prepare;

    @FXML
    void select(ActionEvent event) {
        int gra=Integer.parseInt(tgrade.getSelectionModel().getSelectedItem().toString());
    }

    private static Image convertToJavaFXImage(byte[] raw, final int width, final int height) {
        WritableImage image = new WritableImage(width, height);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(raw);
            BufferedImage read = ImageIO.read(bis);
            image = SwingFXUtils.toFXImage(read, null);
        } catch (IOException ex) {
            Logger.getLogger(Timetable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
    @FXML
    void ok(ActionEvent event) {
        connect = jdbcconnect.getConnection();
        String sql = "SELECT timetable FROM timetable WHERE g_id=?";
        try {
            int gra=Integer.parseInt(tgrade.getSelectionModel().getSelectedItem().toString());
            prepare=connect.prepareStatement(sql);
            prepare.setInt(1, gra);
            ResultSet rs = prepare.executeQuery();

            while (rs.next()) {
                InputStream imageFile = rs.getBinaryStream(1);
                Image image = new Image(imageFile);
                timeimage.setImage(image);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }





    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list= FXCollections.observableArrayList("06","07","08","09","10","11","12","13");
        tgrade.setItems(list);

    }

}
