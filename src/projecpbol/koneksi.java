/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecpbol;

import java.sql.*;

/**
 *
 * @author ASUS
 */
public class koneksi {
    public Connection dbKoneksi;
    public Statement statement;
    public PreparedStatement preparedStatement;
    public koneksi() {this.dbKoneksi = null;}
    public void bukaKoneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbKoneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_simulasi_pet?user=root&password=1234");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void tutupKoneksi() {
        try {
            if (statement != null){statement.close();}
            if (preparedStatement != null){preparedStatement.close();}
            if (dbKoneksi != null){dbKoneksi.close();}
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
