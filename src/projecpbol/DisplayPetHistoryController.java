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
public class DisplayPetHistoryController implements Initializable {
    
    public static DBPetHistory dtpetHistory = new DBPetHistory();
    
    @FXML
    private TableView tbview;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showData();
    }    
    
    private void showData() {
        ObservableList data = data = DisplayPetHistoryController.dtpetHistory.Load();
        if (data != null) {
            tbview.getColumns().clear();
            tbview.getItems().clear();
            
            TableColumn col = col = new TableColumn("Player Id");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetHistory, String>("player_id"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Pet Id");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetHistory, String>("pet_id"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Pet Name");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetHistory, String>("pet_name"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Pet Age");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetHistory, Float>("age"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Pet Training");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetHistory, Float>("training"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Pet Level");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetHistory, Integer>("level"));
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
}
