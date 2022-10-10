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
public class DBInv {
    private ModelInv dt = new ModelInv();

    public ModelInv getModelInv() {
        return dt;
    }

    public void setDt(ModelInv dt) {
        this.dt = dt;
    }
    
    public ObservableList<ModelInv> Load(){
        try{
            ObservableList<ModelInv> TableData = FXCollections.observableArrayList();
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from inventory");
            int i = 1;
            while(rs.next()){
                ModelInv d = new ModelInv();
                d.setPlayerId(rs.getString("player_id"));
//                d.addItem(rs.getString("item_id"),rs.getInt("reset_count"));
                d.setItem_id(rs.getString("item_id"));
                d.setItem_count(Integer.parseInt(rs.getString("item_count")));
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
    
    public int validasi(String nomor,String id) {
        int val = 0;
        try {
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from inventory where player_id = '" + nomor + "' and item_id = '"+id+"'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into inventory (player_id, item_id, item_count) values (?,?,?)");
            con.preparedStatement.setString(1, this.getModelInv().getPlayerId());
            con.preparedStatement.setString(2, this.getModelInv().getItem_id());
            con.preparedStatement.setString(3, String.valueOf(this.getModelInv().getItem_count()));
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

     public boolean delete(String nomor, String id) {
        boolean berhasil = false;
        koneksi con = new koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from inventory where player_id = ? and item_id = ?");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update player set item_count = ? where player_id = ? and item_id = ? ;");
            con.preparedStatement.setString(1, String.valueOf(this.getModelInv().getItem_count()));
            con.preparedStatement.setString(2, this.getModelInv().getPlayerId());
            con.preparedStatement.setString(3, this.getModelInv().getItem_id());
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
