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
public class akademisyenOzluk {
       @ManagedProperty(value="#{giris.tc_no}")
        private String tc_no;
        public void setTc_no(String tc_no) {
            this.tc_no = tc_no;
        }
     public String getTc_no() {
        return tc_no;
    }

    
    
    DataSource dataSource;
    public akademisyenOzluk() { 
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
            PreparedStatement ps = connection.prepareStatement("select AD from AKADEMISYENBILGI where TCNO = '"+getTc_no()+"'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if(resultSet1.next()){
            return resultSet1.getString("AD");
            }
        } 
        finally {
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
            PreparedStatement ps = connection.prepareStatement("select SOYAD from AKADEMISYENBILGI where TCNO = '"+getTc_no()+"'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if(resultSet1.next()){
            return resultSet1.getString("SOYAD");
            }
        } 
        finally {
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
            PreparedStatement ps = connection.prepareStatement("select BABADI from AKADEMISYENBILGI where TCNO = '"+getTc_no()+"'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if(resultSet1.next()){
            return resultSet1.getString("BABADI");
            }
        } 
        finally {
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
            PreparedStatement ps = connection.prepareStatement("select ANNEADI from AKADEMISYENBILGI where TCNO = '"+getTc_no()+"'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if(resultSet1.next()){
            return resultSet1.getString("ANNEADI");
            }
        } 
        finally {
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
            PreparedStatement ps = connection.prepareStatement("select CINSIYET from AKADEMISYENBILGI where TCNO = '"+getTc_no()+"'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if(resultSet1.next()){
            return resultSet1.getString("CINSIYET");
            }
        } 
        finally {
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
            PreparedStatement ps = connection.prepareStatement("select TARIH from AKADEMISYENBILGI where TCNO = '"+getTc_no()+"'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if(resultSet1.next()){
            return resultSet1.getString("TARIH");
            }
        } 
        finally {
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
    public String getAkademisyenID() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
      
        Connection connection = dataSource.getConnection();
      
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {
            ResultSet rs;
            PreparedStatement ps = connection.prepareStatement("select AKADEMISYENID from AKADEMISYENBILGI where TCNO = '"+getTc_no()+"'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            if(resultSet1.next()){
            return resultSet1.getString("AKADEMISYENID");
            }
        } 
        finally {
            connection.close();
        }
           return null;
    }

    public void setAkademisyenID(int AkademisyenID) {
        this.AkademisyenID = AkademisyenID;
    }

    private int AkademisyenID;

    public void setAd(String ad) {
        this.ad = ad;
    }
    private String ad;
    private String soyad;
    private String baba_adi;
    private String anne_adi;
    private String cinsiyet;
    private String tarih;
    private String sifre;

    public String getYenisifre() {
        return yenisifre;
    }

    public void setYenisifre(String yenisifre) {
        this.yenisifre = yenisifre;
    }
    @NotBlank(message = "Şifreniz boş olamaz")
    private String yenisifre;
    
    public String Sifre_guncelle() throws SQLException, InterruptedException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
            PreparedStatement ps = connection.prepareStatement("update AKADEMISYENGIRIS set SIFRE = '" + getYenisifre() + "' where TCNO = '" + getTc_no() + "'");
            ps.executeUpdate();
            FacesMessage message = new FacesMessage("Şifre başarıyla değiştirildi");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "akademisyenOzluk.xhtml";
        }
    
}
