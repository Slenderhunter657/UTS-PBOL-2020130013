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
public class DisplayPetInstController implements Initializable {
    
    public static DBPetInst dtpetInst = new DBPetInst();
    
    @FXML
    private TableView<ModelPetInst> tbview;
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
        // TODO
        showData();
    }    
    
    private void showData() {
        ObservableList data = data = DisplayPetInstController.dtpetInst.Load();
        if (data != null) {
            tbview.getColumns().clear();
            tbview.getItems().clear();
            
            TableColumn col = col = new TableColumn("Pet Id");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, String>("id"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Player Id");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, String>("playerId"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Pet Base Id");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, String>("baseId"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Pet Name");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, String>("name"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Health");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Float>("health"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Hunger");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Float>("hunger"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Happiness");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Float>("happy"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Training");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Float>("train"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Age");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Float>("age"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Experience Point");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Float>("exp"));
            tbview.getColumns().addAll(col);

            col = new TableColumn("Current Pet Level");
            col.setCellValueFactory(new PropertyValueFactory<ModelPetInst, Integer>("level"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InputPetInst.fxml"));
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
        ModelPetInst s = new ModelPetInst();
        s = tbview.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (MainController.dtpetinst.delete(s.getId())) {
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
        ModelPetInst s = new ModelPetInst();
        s = tbview.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InputPetInst.fxml"));
            Parent root = (Parent) loader.load();
            InputPetInstController isidt = (InputPetInstController) loader.getController();
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
    
    @FXML
    private void keluarKlik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
}
