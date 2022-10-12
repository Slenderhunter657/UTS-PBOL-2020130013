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
public class DisplayItmController implements Initializable {
    
    public static DBItm dtitm = new DBItm();
    
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
       showData();
    }
    
    private void showData() {
        
        ObservableList data = DisplayItmController.dtitm.Load();
        if (data != null) {
            tbview.getColumns().clear();
            tbview.getItems().clear();
            
            TableColumn col = new TableColumn("Item Id");
            col.setCellValueFactory(new PropertyValueFactory<ModelItm, String>("id"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Item Name");
            col.setCellValueFactory(new PropertyValueFactory<ModelItm, String>("name"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Item Type");
            col.setCellValueFactory(new PropertyValueFactory<ModelItm, Integer>("type"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Rarity");
            col.setCellValueFactory(new PropertyValueFactory<ModelItm, Float>("rarity"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Hunger Added");
            col.setCellValueFactory(new PropertyValueFactory<ModelItm, Float>("hunger"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Happiness Added");
            col.setCellValueFactory(new PropertyValueFactory<ModelItm, Float>("happy"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InputItm.fxml"));
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

    
    //modifing...
//    @FXML
//    private void hapusKlik(ActionEvent event) {
//        ModelItm s = new ModelItm();
//        s = tbview.getSelectionModel().getSelectedItem();
//        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
//        a.showAndWait();
//        if (a.getResult() == ButtonType.YES) {
//            if (MainController.dtitm.delete(s.getId())) {
//                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
//                b.showAndWait();
//            } else {
//                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
//                b.showAndWait();
//            }
//            showData();
//            awalKlik(event);
//        }
//    }
//
//    @FXML
//    private void ubahKlik(ActionEvent event) {
//        ModelItm s = new ModelItm();
//        s = tbview.getSelectionModel().getSelectedItem();
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("InputItm.fxml"));
//            Parent root = (Parent) loader.load();
//            InputItmController isidt = (InputItmController) loader.getController();
//            isidt.execute(s);
//            Scene scene = new Scene(root);
//            Stage stg = new Stage();
//            stg.initModality(Modality.APPLICATION_MODAL);
//            stg.setResizable(false);
//            stg.setIconified(false);
//            stg.setScene(scene);
//            stg.showAndWait();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        showData();
//        awalKlik(event);
//    }
}
