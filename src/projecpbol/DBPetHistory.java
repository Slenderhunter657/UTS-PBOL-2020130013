/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecpbol;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class DBPetHistory {
    private ModelPetHistory dt = new ModelPetHistory();

    public ModelPetHistory getModelPetHistory() {
        return dt;
    }

    public void setDt(ModelPetHistory dt) {
        this.dt = dt;
    }
    
    public ObservableList<ModelPetHistory> Load(){
        try{
            ObservableList<ModelPetHistory> TableData = FXCollections.observableArrayList();
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from pet_history");
            int i = 1;
            while(rs.next()){
                ModelPetHistory d = new ModelPetHistory();
                d.setPlayerId(rs.getString("player_id"));
                d.setPetId(rs.getString("pet_id"));
                d.setPetName(rs.getString("pet_name"));
                d.setAge(rs.getFloat("age"));
                d.setTraining(rs.getFloat("training"));
                d.setLevel(rs.getInt("level"));
                TableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return TableData;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public int validasi(String nomor, String id) {
        int val = 0;
        try {
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from pet_history where player_id = '" + nomor + "' and pet_id = '"+id+"'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }        

     public boolean insert() {
        boolean berhasil = false;
        koneksi con = new koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into player (player_id, pet_id, pet_name, age, training, level) values (?,?,?,?,?,?)");
            con.preparedStatement.setString(1, this.getModelPetHistory().getPlayerId());
            con.preparedStatement.setString(2, this.getModelPetHistory().getPetId());
            con.preparedStatement.setString(3, this.getModelPetHistory().getPetName());
            con.preparedStatement.setFloat(4, this.getModelPetHistory().getAge());
            con.preparedStatement.setFloat(5, this.getModelPetHistory().getTraining());
            con.preparedStatement.setInt(6, this.getModelPetHistory().getLevel());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        }catch (Exception e){
            e.printStackTrace();
            berhasil = false;
        }finally{
            con.tutupKoneksi();
            return berhasil;
        }
    }

     public boolean delete(String nomor,String id) {
        boolean berhasil = false;
        koneksi con = new koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from pet_history where player_id = ? and pet_id = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.setString(2, id);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        koneksi con = new koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update pet_history set pet_name = ?, age = ?, training = ?, level = ? where player_id = ? and pet_id = ?;");
            con.preparedStatement.setString(5, this.getModelPetHistory().getPlayerId());
            con.preparedStatement.setString(6, this.getModelPetHistory().getPetId());
            con.preparedStatement.setString(1, this.getModelPetHistory().getPetName());
            con.preparedStatement.setFloat(2, this.getModelPetHistory().getAge());
            con.preparedStatement.setFloat(3, this.getModelPetHistory().getTraining());
            con.preparedStatement.setInt(4, this.getModelPetHistory().getLevel());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
}
