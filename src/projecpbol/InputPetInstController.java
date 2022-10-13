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
public class InputPetInstController implements Initializable {

    boolean editdata = false;
    
    @FXML
    private TextField txtplayerid;
    @FXML
    private TextField txtpetid;
    @FXML
    private TextField txtpetbaseid;
    @FXML
    private TextField txtpetname;
    
    @FXML
    private Spinner<Double> spnhealth;
    @FXML
    private Spinner<Double> spnhunger;
    @FXML
    private Spinner<Double> spnhappy;
    @FXML
    private Spinner<Double> spntrn;
    @FXML
    private Spinner<Double> spnage;
    @FXML
    private Spinner<Double> spnexp;
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
        SpinnerValueFactory<Double> hapvf = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,300,0,0.1);
        SpinnerValueFactory<Double> hunvf = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,120,0,0.1);
        SpinnerValueFactory<Double> hthvf = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,100,0,0.1);
        
        SpinnerValueFactory<Double> agevf = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,20,0,0.1);
        SpinnerValueFactory<Double> trnvf = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,100,0,0.1);
        SpinnerValueFactory<Double> expvf = new SpinnerValueFactory.DoubleSpinnerValueFactory(0,20,0,0.1);
        SpinnerValueFactory<Integer> lvlvf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
        
        hapvf.setValue(0d);
        hunvf.setValue(0d);
        hthvf.setValue(0d);
        agevf.setValue(0d);
        trnvf.setValue(0d);
        expvf.setValue(0d);
        lvlvf.setValue(0);
        
        spnhappy.setValueFactory(hapvf);
        spnhunger.setValueFactory(hunvf);
        spnhealth.setValueFactory(hthvf);
        spnage.setValueFactory(agevf);
        spntrn.setValueFactory(trnvf);
        spnexp.setValueFactory(expvf);
        spnlvl.setValueFactory(lvlvf);
    }    
    
    private double convertFtoD(float a){
        return (double)a;
    }
    private float convertDtoF(double a){
        return (float)a;
    }
    
    @FXML
    private void batalKlik(ActionEvent event) {
        
        txtpetid.clear();
        txtplayerid.clear();
        txtpetbaseid.clear();
        txtpetname.clear();
        
        spnhealth.getValueFactory().setValue((double)0);
        spnhunger.getValueFactory().setValue((double)0);
        spnhappy.getValueFactory().setValue((double)0);
        spnage.getValueFactory().setValue((double)0);
        spntrn.getValueFactory().setValue((double)0);
        spnexp.getValueFactory().setValue((double)0);
        spnlvl.getValueFactory().setValue(0);
    }

    @FXML
    private void keluarKlik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void simpanKlik(ActionEvent event) {
        ModelPetInst s = new ModelPetInst();
        s.setId(txtpetid.getText());
        s.setPlayerId(txtplayerid.getText());
        s.setBaseId(txtpetbaseid.getText());
        s.setName(txtpetname.getText());
        s.setHealth(convertDtoF(spnhealth.getValue()));
        s.setHunger(convertDtoF(spnhunger.getValue()));
        s.setHappy(convertDtoF(spnhappy.getValue()));
        s.setAge(convertDtoF(spnage.getValue()));
        s.setTrain(convertDtoF(spntrn.getValue()));
        s.setExp(convertDtoF(spnexp.getValue()));
        s.setLevel(spnlvl.getValue());
        MainController.dtpetinst.setDt(s);
        if (editdata) {
            if (MainController.dtpetinst.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtplayerid.setEditable(true);
                batalKlik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            if (MainController.dtpetinst.validasi(s.getId()) <= 0) {
                if (MainController.dtpetinst.insert()) {
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
    
    
    
    public void execute(ModelPetInst d) {
        if (!d.getId().isEmpty()) {
            editdata = true;
            
            txtpetid.setText(d.getId());
            txtplayerid.setText(d.getPlayerId());
            txtpetbaseid.setText(d.getBaseId());
            txtpetname.setText(d.getName());

            spnhealth.getValueFactory().setValue((double)d.getHealth());
            spnhunger.getValueFactory().setValue((double)d.getHunger());
            spnhappy.getValueFactory().setValue((double)d.getHappy());
            spnage.getValueFactory().setValue((double)d.getAge());
            spntrn.getValueFactory().setValue((double)d.getTrain());
            spnexp.getValueFactory().setValue((double)d.getExp());
            spnlvl.getValueFactory().setValue(d.getLevel());
            txtplayerid.requestFocus();
        }
    }
}
