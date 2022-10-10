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
public class DBPlayer {
    private ModelPlayer dt = new ModelPlayer();

    public ModelPlayer getModelPlayer() {
        return dt;
    }

    public void setDt(ModelPlayer dt) {
        this.dt = dt;
    }
    
    public ObservableList<ModelPlayer> Load(){
        try{
            ObservableList<ModelPlayer> TableData = FXCollections.observableArrayList();
            koneksi con = new koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from player");
            int i = 1;
            while(rs.next()){
                ModelPlayer d = new ModelPlayer();
                d.setId(rs.getString("player_id"));
                d.setName(rs.getString("player_name"));
                d.setReset(rs.getInt("reset_count"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from player where player_id = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into player (player_id, player_name, reset_count) values (?,?,?)");
            con.preparedStatement.setString(1, this.getModelPlayer().getId());
            con.preparedStatement.setString(2, this.getModelPlayer().getName());
            con.preparedStatement.setString(3, String.valueOf(this.getModelPlayer().getReset()));
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from player where player_id = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update player set player_name = ?, reset_count = ? where player_id = ? ;");
            con.preparedStatement.setString(1, getModelPlayer().getName());
            con.preparedStatement.setString(2, String.valueOf(getModelPlayer().getReset()));
            con.preparedStatement.setString(3, getModelPlayer().getId());
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
