/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projecpbol;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DisplayInvController implements Initializable {
    
    @FXML
    private TableView<ModelInv> tbview;
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
        showData();
    }    
    
    private void showData() {
        ObservableList data = MainController.dtinv.Load();
        if (data != null) {
            tbview.getColumns().clear();
            tbview.getItems().clear();
            
            TableColumn col = new TableColumn("Player Id");
            col.setCellValueFactory(new PropertyValueFactory<ModelInv, String>("playerId"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Item Id");
            col.setCellValueFactory(new PropertyValueFactory<ModelInv, String>("item_id"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Item Count");
            col.setCellValueFactory(new PropertyValueFactory<ModelInv, Integer>("item_count"));
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
    private void tambahKlik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InputInv.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showData();
        awalKlik(event);
    }

    @FXML
    private void hapusKlik(ActionEvent event) {
        ModelInv s = new ModelInv();
        s = tbview.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (MainController.dtinv.delete(s.getPlayerId(),s.getItem_id())) {
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
    private void ubahKlik(ActionEvent event) {
        ModelInv s = new ModelInv();
        s = tbview.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InputInv.fxml"));
            Parent root = (Parent) loader.load();
            InputInvController isidt = (InputInvController) loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showData();
        awalKlik(event);
    }
}
