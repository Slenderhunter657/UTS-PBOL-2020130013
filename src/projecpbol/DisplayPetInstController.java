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
public class DisplayPetInstController implements Initializable {
    
    public static DBPetInst dtpetInst = new DBPetInst();
    
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
        ObservableList data = data = DisplayPetInstController.dtpetInst.Load();
        if (data != null) {
            tbview.getColumns().clear();
            tbview.getItems().clear();
            
            TableColumn col = col = new TableColumn("Pet Id");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, String>("pet_id"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Player Id");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, String>("player_id"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Pet Base Id");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, String>("pet_base_id"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Pet Name");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, String>("pet_name"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Health");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Float>("cur_health"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Hunger");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Float>("cur_hunger"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Happiness");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Float>("cur_happy"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Training");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Float>("cur_train"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Age");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Float>("cur_age"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Experience Point");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Float>("cur_exp"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Level");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Integer>("cur_level"));
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
