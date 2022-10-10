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
public class DBPetBase {
    private ModelPetBase dt = new ModelPetBase();

    public ModelPetBase getModelPetBase() {
        return dt;
    }
    
    public ObservableList<ModelPetBase> Load(){
        try{
            ObservableList<ModelPetBase> TableData = FXCollections.observableArrayList();
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from pet_base");
            int i = 1;
            while(rs.next()){
                ModelPetBase d = new ModelPetBase();
                d.setId(rs.getString("pet_base_id"));
                d.setType(rs.getInt("pet_type"));
                d.setMaxHunger(rs.getFloat("max_hunger"));
                d.setMaxHappy(rs.getFloat("max_happy"));
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
    
    public int validasi(String nomor) {
        int val = 0;
        try {
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from pet_base where pet_base_id = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into pet_base_id (pet_base_id, pet_type, max_hunger, max_happy) values (?,?,?,?)");
            con.preparedStatement.setString(1, this.getModelPetBase().getId());
            con.preparedStatement.setInt(2, this.getModelPetBase().getType());
            con.preparedStatement.setFloat(3, this.getModelPetBase().getMaxHunger());
            con.preparedStatement.setFloat(4, this.getModelPetBase().getMaxHappy());
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

     public boolean delete(String nomor) {
        boolean berhasil = false;
        koneksi con = new koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from pet_base where pet_base_id = ? ");
            con.preparedStatement.setString(1, nomor);
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update pet_base set pet_type = ?, max_hunger = ?, max_happy = ? where pet_base_id = ? ;");
            con.preparedStatement.setString(4, this.getModelPetBase().getId());
            con.preparedStatement.setInt(1, this.getModelPetBase().getType());
            con.preparedStatement.setFloat(2, this.getModelPetBase().getMaxHunger());
            con.preparedStatement.setFloat(3, this.getModelPetBase().getMaxHappy());
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
