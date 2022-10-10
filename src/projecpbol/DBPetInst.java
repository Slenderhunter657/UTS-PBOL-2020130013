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
public class DBPetInst {
    private ModelPetInst dt = new ModelPetInst();

    public ModelPetInst getModelPetInst() {
        return dt;
    }
    
    public ObservableList<ModelPetInst> Load(){
        try{
            ObservableList<ModelPetInst> TableData = FXCollections.observableArrayList();
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from pet_instance");
            int i = 1;
            while(rs.next()){
                ModelPetInst d = new ModelPetInst();
                d.setId(rs.getString("pet_id"));
                d.setPlayerId(rs.getString("player_id"));
                d.setBaseId(rs.getString("pet_base_id"));
                d.setName(rs.getString("pet_name"));
                d.setHealth(rs.getFloat("cur_health"));
                d.setHunger(rs.getFloat("cur_hunger"));
                d.setHappy(rs.getFloat("cur_happy"));
                d.setTrain(rs.getFloat("cur_train"));
                d.setAge(rs.getFloat("cur_age"));
                d.setExp(rs.getFloat("cur_exp"));
                d.setLevel(rs.getInt("cur_level"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from pet_instance where pet_id = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into player (pet_id, player_id, pet_base_id, pet_name, cur_health, cur_hunger, cur_happy, cur_train, cur_age, cur_exp, cur_level) values (?,?,?,?,?,?,?,?,?,?,?)");
            con.preparedStatement.setString(1, this.getModelPetInst().getId());
            con.preparedStatement.setString(2, this.getModelPetInst().getPlayerId());
            con.preparedStatement.setString(3, this.getModelPetInst().getBaseId());
            con.preparedStatement.setString(4, this.getModelPetInst().getName());
            con.preparedStatement.setFloat(5, this.getModelPetInst().getHealth());
            con.preparedStatement.setFloat(6, this.getModelPetInst().getHunger());
            con.preparedStatement.setFloat(7, this.getModelPetInst().getHappy());
            con.preparedStatement.setFloat(8, this.getModelPetInst().getTrain());
            con.preparedStatement.setFloat(9, this.getModelPetInst().getAge());
            con.preparedStatement.setFloat(10, this.getModelPetInst().getExp());
            con.preparedStatement.setInt(11, this.getModelPetInst().getLevel());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from pet_instance where pet_id = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update pet_instance set player_id = ?, pet_base_id = ?, pet_name = ?, cur_health = ?, cur_hunger = ?, cur_happy = ?, cur_train = ?, cur_age = ?, cur_exp = ?, cur_level = ? where pet_id = ? ;");
            con.preparedStatement.setString(11, this.getModelPetInst().getId());
            con.preparedStatement.setString(1, this.getModelPetInst().getPlayerId());
            con.preparedStatement.setString(2, this.getModelPetInst().getBaseId());
            con.preparedStatement.setString(3, this.getModelPetInst().getName());
            con.preparedStatement.setFloat(4, this.getModelPetInst().getHealth());
            con.preparedStatement.setFloat(5, this.getModelPetInst().getHunger());
            con.preparedStatement.setFloat(6, this.getModelPetInst().getHappy());
            con.preparedStatement.setFloat(7, this.getModelPetInst().getTrain());
            con.preparedStatement.setFloat(8, this.getModelPetInst().getAge());
            con.preparedStatement.setFloat(9, this.getModelPetInst().getExp());
            con.preparedStatement.setInt(10, this.getModelPetInst().getLevel());
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
