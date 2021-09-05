/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Ugur
 */
@ManagedBean
@RequestScoped
public class notGorDataTable {

    public int getFinal_Notu() {
        return final_Notu;
    }

    public void setFinal_Notu(int final_Notu) {
        this.final_Notu = final_Notu;
    }

    public int getVize_Notu() {
        return vize_Notu;
    }

    public void setVize_Notu(int vize_Notu) {
        this.vize_Notu = vize_Notu;
    }

    public String getTcno() {
        return tcno;
    }

    public void setTcno(String tcno) {
        this.tcno = tcno;
    }

    public String getDers_adi() {
        return ders_adi;
    }

    public void setDers_adi(String ders_adi) {
        this.ders_adi = ders_adi;
    }

    private int ders_id;

    public int getDers_id() {
        return ders_id;
    }

    public void setDers_id(int ders_id) {
        this.ders_id = ders_id;
    }
    private int final_Notu;
    private int vize_Notu;
    private String tcno;
    private String ders_adi;

    public notGorDataTable() {

        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("jdbc/ogrenciOtomasyon");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    CachedRowSet rowSet = null;
    DataSource dataSource;

    public ResultSet bul() throws SQLException {
        // check whether dataSource was injected by the server
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        // obtain a connection from the connection pool
        Connection connection = dataSource.getConnection();

        // check whether connection was successful
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {

             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select DERSID from DERSLER where DERSADI = '"+ders_adi+"' ");
            
            if (resultSet.next()) {
                setDers_id(resultSet.getInt("DERSID"));
            }

            PreparedStatement ps = connection.prepareStatement("select TCNO,VIZENOTU,FINALNOTU from OGRENCIDERSDETAY where DERSID = " + ders_id + " ");
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(ps.executeQuery());
            return rowSet;
        } // end try
        finally {
            connection.close(); // return this connection to pool
        } // end finally
    }

}
