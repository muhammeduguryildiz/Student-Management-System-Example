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
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
public class alinanDersler {

    public int getDersID() {
        return dersID;
    }

    public void setDersID(int dersID) {
        this.dersID = dersID;
    }

    @ManagedProperty(value = "#{giris.tc_no}")
    private String tc_no;

    public void setTc_no(String tc_no) {
        this.tc_no = tc_no;
    }

    public String getTc_no() {
        return tc_no;
    }

    private int dersID;
    DataSource dataSource;
    CachedRowSet rowSet = null;

    public String getSilinecek_ders() {
        return silinecek_ders;
    }

    public void setSilinecek_ders(String silinecek_ders) {
        this.silinecek_ders = silinecek_ders;
    }

    public int getSilinecekDersId() {
        return silinecekDersId;
    }

    public void setSilinecekDersId(int silinecekDersId) {
        this.silinecekDersId = silinecekDersId;
    }
    private String silinecek_ders;
    private int silinecekDersId;

    public alinanDersler() throws SQLException {

        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("jdbc/ogrenciOtomasyon");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void Ders_Sil() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select DERSID from DERSLER where DERSADI = '" + getSilinecek_ders() + "' ");
            if (resultSet.next()) {
                setSilinecekDersId(resultSet.getInt("DERSID"));
                PreparedStatement ps = connection.prepareStatement("DELETE FROM OGRENCIDERSDETAY WHERE TCNO='" + getTc_no() + "' and DERSID=" + silinecekDersId + "");
                ps.executeUpdate();
                FacesMessage message = new FacesMessage("Ders Silme Başarılı");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } finally {
            connection.close();
        }

    }

    public ResultSet alinmis_dersler() throws SQLException {
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

            PreparedStatement ps = connection.prepareStatement("select DERSADI from OGRENCIDERSDETAY,OGRENCIBILGI,DERSLER \n"
                    + "where OGRENCIBILGI.TCNO = OGRENCIDERSDETAY.TCNO AND OGRENCIDERSDETAY.DERSID=DERSLER.DERSID and OGRENCIBILGI.TCNO='" + getTc_no() + "'\n"
                    + "order by DERSADI asc");
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(ps.executeQuery());
            return rowSet;
        } // end try
        finally {
            connection.close(); // return this connection to pool
        } // end finally
    }
}
