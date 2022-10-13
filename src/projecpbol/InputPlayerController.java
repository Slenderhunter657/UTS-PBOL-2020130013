/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projecpbol;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InputPlayerController implements Initializable {

    boolean editdata = false;
    
    @FXML
    private TextField txtplayerid;
    @FXML
    private TextField txtplayername;
    @FXML
    private TextField txtresetcount;
    
    
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnkeluar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void batalKlik(ActionEvent event) {
        txtplayerid.clear();
        txtplayername.clear();
        txtresetcount.clear();
    }

    @FXML
    private void keluarKlik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void simpanKlik(ActionEvent event) {
        ModelPlayer s = new ModelPlayer();
        s.setId(txtplayerid.getText());
        s.setName(txtplayername.getText());
        s.setReset(Integer.parseInt(txtresetcount.getText()));
        MainController.dtplayer.setDt(s);
        if (editdata) {
            if (MainController.dtplayer.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtplayerid.setEditable(true);
                batalKlik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            if (MainController.dtplayer.validasi(s.getId()) <= 0) {
                if (MainController.dtplayer.insert()) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                    a.showAndWait();
                    batalKlik(event);
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                    a.showAndWait();
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
                a.showAndWait();
                txtplayerid.requestFocus();
            }
        }
    }
    
    
    
    public void execute(ModelPlayer d) {
        if (!d.getId().isEmpty()) {
            editdata = true;
            txtplayerid.setText(d.getId());
            txtplayername.setText(d.getName());
            txtresetcount.setText(String.valueOf(d.getReset()));
            txtplayerid.requestFocus();
        }
    }
}
