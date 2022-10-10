/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package projecpbol;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class MainController implements Initializable {
    
    public static DBInv dtinv = new DBInv();
    public static DBItm dtitm = new DBItm();
    public static DBPetBase dtpetbase = new DBPetBase();
    public static DBPetHistory dtpethist = new DBPetHistory();
    public static DBPetInst dtpetinst = new DBPetInst();
    public static DBPlayer dtplayer = new DBPlayer();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void dispInv(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("DisplayInv.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void dispItm(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("DisplayItm.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void dispPetBase(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("DisplayPetBase.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void dispIPetHist(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("DisplayPetHistory.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void dispPetInst(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("DisplayPetInst.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void dispPlayer(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("DisplayPlayer.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void keluarClick(ActionEvent event) {
        System.exit(0);
    }
    
}
