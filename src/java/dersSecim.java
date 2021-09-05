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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Ugur
 */
@ManagedBean
@RequestScoped
public class dersSecim {

    @ManagedProperty(value = "#{giris.tc_no}")
    private static String tc_no;

    public void setTc_no(String tc_no) {
        this.tc_no = tc_no;
    }

    public String getTc_no() {
        return tc_no;
    }

    private String dersAdi;
    private int dersID;
    private String tc_kontrol;

    public String getTc_kontrol() {
        return tc_kontrol;
    }

    public void setTc_kontrol(String tc_kontrol) {
        this.tc_kontrol = tc_kontrol;
    }

    public int getDersID() {
        return dersID;
    }

    public void setDersID(int dersID) {
        this.dersID = dersID;
    }

    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    /**
     * Creates a new instance of dersSecim
     */
    DataSource dataSource;

    public dersSecim() {
        try {
            Context ctx = new InitialContext();

            dataSource = (DataSource) ctx.lookup("jdbc/ogrenciOtomasyon");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void IDGETIR() throws SQLException {

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
            ResultSet resultSet = statement.executeQuery("select DERSID from DERSLER where DERSADI='" + dersAdi + "'");
            if (resultSet.next()) {
                setDersID(resultSet.getInt("DERSID"));
            }
        } finally {
            connection.close();
        }

    }

    public String kaydet() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        IDGETIR();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select TCNO from OGRENCIDERSDETAY where DERSID = " + dersID + " and TCNO='" + getTc_no() + "' ");
            if (resultSet.next()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Bu dersi daha önce seçtiniz", null));
                return "dersSecimi.xhtml";
            }
            PreparedStatement ps = connection.prepareStatement("insert into OGRENCIDERSDETAY (TCNO,DERSID) values('" + getTc_no() + "'," + dersID + ")");
            ps.executeUpdate();

        } finally {
            connection.close();

        }
        return null;

    }

}
