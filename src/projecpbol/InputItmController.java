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
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InputItmController implements Initializable {

    boolean editdata = false;
    
    @FXML
    private TextField txtplayerid;
    @FXML
    private TextField txtitemid;
    @FXML
    private TextField txtitemcount;
    @FXML
    private Spinner spntype;
    @FXML
    private Spinner spnrare;
    @FXML
    private Spinner spnhappy;
    @FXML
    private Spinner spnhuger;
    
    
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
        ModelItm s = new ModelItm();
        s.setId(txtplayerid.getText());
        s.setName(txtitemid.getText());
        s.setType(Integer.parseInt(spntype.getValue().toString()));
        s.setRarity(Integer.parseInt(spnrare.getValue().toString()));
        s.setHunger(Integer.parseInt(spnhuger.getValue().toString()));
        s.setHappy(Integer.parseInt(spnhappy.getValue().toString()));
        MainController.dtitm.setDt(s);
        if (editdata) {
            if (MainController.dtitm.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtplayerid.setEditable(true);
                batalKlik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            if (MainController.dtitm.validasi(s.getId()) <= 0) {
                if (MainController.dtitm.insert()) {
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
    
    
    //not Done!
    public void execute(ModelItm d) {
        if (!d.getId().isEmpty()) {
            editdata = true;
            txtplayerid.setText(d.getId());
            txtitemid.setText(d.getName());
            txtplayerid.requestFocus();
        }
    }
    
}
