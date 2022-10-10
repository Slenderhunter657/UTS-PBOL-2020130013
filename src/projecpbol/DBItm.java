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
public class DBItm {
    private ModelItm dt = new ModelItm();

    public ModelItm getModelItm() {
        return dt;
    }
    
    public ObservableList<ModelItm> Load(){
        try{
            ObservableList<ModelItm> TableData = FXCollections.observableArrayList();
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from item");
            int i = 1;
            while(rs.next()){
                ModelItm d = new ModelItm();
                d.setId(rs.getString("item_id"));
                d.setName(rs.getString("item_name"));
                d.setType(rs.getInt("item_type"));
                d.setRarity(rs.getFloat("rarity"));
                d.setHunger(rs.getFloat("add_hunger"));
                d.setHappy(rs.getFloat("add_happy"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from item where item_id = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into item (item_id, item_name, item_type, rarity, add_hunger, add_happy) values (?,?,?,?,?,?)");
            con.preparedStatement.setString(1, this.getModelItm().getId());
            con.preparedStatement.setString(2, this.getModelItm().getName());
            con.preparedStatement.setInt(3, this.getModelItm().getType());
            con.preparedStatement.setFloat(4, this.getModelItm().getRarity());
            con.preparedStatement.setFloat(5, this.getModelItm().getHunger());
            con.preparedStatement.setFloat(6, this.getModelItm().getHappy());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from item where item_id = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update item set item_name = ?, item_type = ?, rarity = ?, add_hunger = ?, add_happy = ? where item_id = ? ;");
            con.preparedStatement.setString(6, this.getModelItm().getId());
            con.preparedStatement.setString(1, this.getModelItm().getName());
            con.preparedStatement.setInt(2, this.getModelItm().getType());
            con.preparedStatement.setFloat(3, this.getModelItm().getRarity());
            con.preparedStatement.setFloat(4, this.getModelItm().getHunger());
            con.preparedStatement.setFloat(5, this.getModelItm().getHappy());
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
