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
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InputPetHistController implements Initializable {
    boolean editdata = false;
    
    @FXML
    private TextField txtplayerid;
    @FXML
    private TextField txtpetid;
    @FXML
    private TextField txtpetname;
    
    @FXML
    private Spinner<Double> spnage;
    @FXML
    private Spinner<Double> spntrn;
    @FXML
    private Spinner<Integer> spnlvl;
    
    
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
        SpinnerValueFactory<Double> agevf = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,20,0,0.1);
        SpinnerValueFactory<Double> trnvf = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,100,0,0.1);
        SpinnerValueFactory<Integer> lvlvf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
        
        agevf.setValue(0d);
        trnvf.setValue(0d);
        lvlvf.setValue(0);
        
        spnage.setValueFactory(agevf);
        spntrn.setValueFactory(trnvf);
        spnlvl.setValueFactory(lvlvf);
        
    }    
    
    private double convertFtoD(float a){
        return (double)a;
    }
    private double convertDtoF(double a){
        return (float)a;
    }
    
    @FXML
    private void batalKlik(ActionEvent event) {
        txtplayerid.clear();
        txtpetid.clear();
        txtpetname.clear();
        spnage.getValueFactory().setValue(0d);
        spntrn.getValueFactory().setValue(0d);
        spnlvl.getValueFactory().setValue(0);
    }

    @FXML
    private void keluarKlik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void simpanKlik(ActionEvent event) {
        ModelPetHistory s = new ModelPetHistory();
        s.setPlayerId(txtplayerid.getText());
        s.setPetId(txtpetid.getText());
        s.setPetName(txtpetname.getText());
        s.setAge(spnage.getValue().floatValue());
        s.setTraining(spntrn.getValue().floatValue());
        s.setLevel(spnlvl.getValue());
        MainController.dtpethist.setDt(s);
        if (editdata) {
            if (MainController.dtpethist.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtplayerid.setEditable(true);
                batalKlik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            if (MainController.dtpethist.validasi(s.getPlayerId(), s.getPetId()) <= 0) {
                if (MainController.dtpethist.insert()) {
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
    
    
    
    public void execute(ModelPetHistory d) {
        if (!d.getPlayerId().isEmpty()&&!d.getPetId().isEmpty()) {
            editdata = true;
            txtplayerid.setText(d.getPlayerId());
            txtpetid.setText(d.getPetId());
            txtpetname.setText(d.getPetName());
            spnage.getValueFactory().setValue(convertFtoD(d.getAge()));
            spntrn.getValueFactory().setValue(convertFtoD(d.getTraining()));
            spnlvl.getValueFactory().setValue(d.getLevel());
            txtplayerid.requestFocus();
        }
    }
}
