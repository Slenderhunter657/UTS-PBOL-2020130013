/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projecpbol;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DisplayPetBaseController implements Initializable {
    
    public static DBPetBase dtpetBase = new DBPetBase();
    
    @FXML
    private TableView<ModelPetBase> tbview;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnkeluar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
    }    
    
    private void showData() {
        
        ObservableList data = DisplayPetBaseController.dtpetBase.Load();
        if (data != null) {
            tbview.getColumns().clear();
            tbview.getItems().clear();
            
            TableColumn col = col = new TableColumn("Pet Base Id");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetBase, String>("id"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Pet Type");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetBase, Integer>("type"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Max Hunger");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetBase, Float>("maxHunger"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Max Happiness");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetBase, Float>("maxHappy"));
            tbview.getColumns().addAll(col);

            tbview.setItems(data);
                    
        } else {
            Alert al = new Alert(Alert.AlertType.ERROR, "DATA kosong", ButtonType.OK);
            al.showAndWait();
            tbview.getScene().getWindow().hide();
        }
    }
    
    @FXML
    private void awalKlik(ActionEvent event) {
        tbview.getSelectionModel().selectFirst();
        tbview.requestFocus();
    }

    @FXML
    private void sebelumKlik(ActionEvent event) {
        tbview.getSelectionModel().selectAboveCell();
        tbview.requestFocus();
    }

    @FXML
    private void sesudahKlik(ActionEvent event) {
        tbview.getSelectionModel().selectBelowCell();
        tbview.requestFocus();
    }

    @FXML
    private void akhirKlik(ActionEvent event) {
        tbview.getSelectionModel().selectLast();
        tbview.requestFocus();
    }
    
    @FXML
    private void hapusKlik(ActionEvent event) {
        ModelPetBase s = new ModelPetBase();
        s = tbview.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (MainController.dtpetbase.delete(s.getId())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showData();
            awalKlik(event);
        }
    }
    
    @FXML
    private void keluarKlik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
}
