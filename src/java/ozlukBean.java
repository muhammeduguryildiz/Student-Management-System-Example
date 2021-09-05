/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ugur
 */
@ManagedBean
@RequestScoped
public class ozlukBean {

    @ManagedProperty(value = "#{giris.tc_no}")
    private String tc_no;

    public void setTc_no(String tc_no) {
        this.tc_no = tc_no;
    }

    /**
     * Creates a new instance of ozlukBean
     */
    public String getTc_no() {
        return tc_no;
    }

    DataSource dataSource;

    public ozlukBean() {
        try {
            Context ctx = new InitialContext();

            dataSource = (DataSource) ctx.lookup("jdbc/ogrenciOtomasyon");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public String getAd() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {
            ResultSet rs;
            PreparedStatement ps = connection.prepareStatement("select AD from OGRENCIBILGI where TCNO = '" + getTc_no() + "'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if (resultSet1.next()) {
                return resultSet1.getString("AD");
            }
        } finally {
            connection.close();
        }
        return null;

    }

    public String getSoyad() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {
            ResultSet rs;
            PreparedStatement ps = connection.prepareStatement("select SOYAD from OGRENCIBILGI where TCNO = '" + getTc_no() + "'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if (resultSet1.next()) {
                return resultSet1.getString("SOYAD");
            }
        } finally {
            connection.close();
        }
        return null;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getBaba_adi() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {
            ResultSet rs;
            PreparedStatement ps = connection.prepareStatement("select BABADI from OGRENCIBILGI where TCNO = '" + getTc_no() + "'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if (resultSet1.next()) {
                return resultSet1.getString("BABADI");
            }
        } finally {
            connection.close();
        }
        return null;
    }

    public void setBaba_adi(String baba_adi) {
        this.baba_adi = baba_adi;
    }

    public String getAnne_adi() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {
            ResultSet rs;
            PreparedStatement ps = connection.prepareStatement("select ANNEADI from OGRENCIBILGI where TCNO = '" + getTc_no() + "'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if (resultSet1.next()) {
                return resultSet1.getString("ANNEADI");
            }
        } finally {
            connection.close();
        }
        return null;
    }

    public void setAnne_adi(String anne_adi) {
        this.anne_adi = anne_adi;
    }

    public String getCinsiyet() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {
            ResultSet rs;
            PreparedStatement ps = connection.prepareStatement("select CINSIYET from OGRENCIBILGI where TCNO = '" + getTc_no() + "'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if (resultSet1.next()) {
                return resultSet1.getString("CINSIYET");
            }
        } finally {
            connection.close();
        }
        return null;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getTarih() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {
            ResultSet rs;
            PreparedStatement ps = connection.prepareStatement("select TARIH from OGRENCIBILGI where TCNO = '" + getTc_no() + "'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if (resultSet1.next()) {
                return resultSet1.getString("TARIH");
            }
        } finally {
            connection.close();
        }
        return null;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getOgrenciNo() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {
            ResultSet rs;
            PreparedStatement ps = connection.prepareStatement("select OGRENCINO from OGRENCIBILGI where TCNO = '" + getTc_no() + "'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if (resultSet1.next()) {
                return resultSet1.getString("OGRENCINO");
            }
        } finally {
            connection.close();
        }
        return null;
    }

    public void setOgrenciNo(int ogrenciNo) {
        this.ogrenciNo = ogrenciNo;
    }

    private int ogrenciNo;

    public void setAd(String ad) {
        this.ad = ad;
    }
    private String cinsiyet_check;
    private String ad;
    private String soyad;
    private String baba_adi;

    public String getCinsiyet_check() {
        return cinsiyet_check;
    }

    public void setCinsiyet_check(String cinsiyet_check) {
        this.cinsiyet_check = cinsiyet_check;
    }
    private String anne_adi;
    private String cinsiyet;
    private String tarih;
    private String sifre;
    @NotBlank(message = "Şifreniz boş olamaz")
    private String yenisifre;

    public String getYenisifre() {
        return yenisifre;
    }

    public void setYenisifre(String yenisifre) {
        this.yenisifre = yenisifre;
    }

    public String Sifre_guncelle() throws SQLException, InterruptedException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
            PreparedStatement ps = connection.prepareStatement("update OGRENCIGIRIS set SIFRE = '" + getYenisifre() + "' where TCNO = '" + getTc_no() + "'");
            ps.executeUpdate();
            FacesMessage message = new FacesMessage("Şifre başarıyla değiştirildi");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "ozlukBilgileri.xhtml";
        }
    
    public String cinsiyet_check() throws SQLException, InterruptedException {
        
        
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {
            ResultSet rs;
            PreparedStatement ps = connection.prepareStatement("select CINSIYET from OGRENCIBILGI where TCNO = '" + getTc_no() + "'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if (resultSet1.next()) {
                setCinsiyet_check(resultSet1.getString("CINSIYET"));
            }
            if(cinsiyet_check.equalsIgnoreCase("Kadın")){
            return "https://pbs.twimg.com/profile_images/791804442823778304/ezFU0AQB_400x400.jpg";
            }else {
            return "https://pbs.twimg.com/profile_images/1346136945420865536/8raWhUYH.jpg";
            }
        } finally {
            connection.close();
        }
    }
}
