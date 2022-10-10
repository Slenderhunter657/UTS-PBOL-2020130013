/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projecpbol;

import java.net.URL;
import java.sql.Date;
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
public class InputInvController implements Initializable {
    boolean editdata = false;
    
    @FXML
    private TextField txtplayerid;
    @FXML
    private TextField txtitemid;
    @FXML
    private TextField txtitemcount;
    
    
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
        txtitemid.clear();
        txtitemcount.clear();
    }

    @FXML
    private void keluarKlik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void simpanKlik(ActionEvent event) {
        ModelInv s = new ModelInv();
        s.setPlayerId(txtplayerid.getText());
        s.setItem_id(txtitemid.getText());
        s.setItem_count(Integer.parseInt(txtitemcount.getText()));
        MainController.dtinv.setDt(s);
        if (editdata) {
            if (MainController.dtinv.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtplayerid.setEditable(true);
                batalKlik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            if (MainController.dtinv.validasi(s.getPlayerId(), s.getItem_id()) <= 0) {
                if (MainController.dtinv.insert()) {
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
    
    
    
    public void execute(ModelInv d) {
        if (!d.getPlayerId().isEmpty()&&!d.getItem_id().isEmpty()) {
            editdata = true;
            txtplayerid.setText(d.getPlayerId());
            txtitemid.setText(d.getItem_id());
            txtitemcount.setText(String.valueOf(d.getItem_count()));
            txtplayerid.requestFocus();
        }
    }
}
