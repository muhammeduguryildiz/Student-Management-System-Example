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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Ugur
 */
@ManagedBean
@SessionScoped
public class ogrenciGorDataTable {
    
     @ManagedProperty(value="#{giris.tc_no}")
        private String akademisyen_tc_no;

    public String getAkademisyen_tc_no() {
        return akademisyen_tc_no;
    }

    public void setAkademisyen_tc_no(String akademisyen_tc_no) {
        this.akademisyen_tc_no = akademisyen_tc_no;
    }

    public int getAltsinir() {
        return altsinir;
    }

    public void setAltsinir(int altsinir) {
        this.altsinir = altsinir;
    }

    public int getUstsinir() {
        return ustsinir;
    }

    public void setUstsinir(int ustsinir) {
        this.ustsinir = ustsinir;
    }
        

    private int altsinir;
    private int ustsinir;

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

    public String getOgrenci_tc_no() {
        return ogrenci_tc_no;
    }

    public void setOgrenci_tc_no(String ogrenci_tc_no) {
        this.ogrenci_tc_no = ogrenci_tc_no;
    }
    
    private int final_Notu;
    private int vize_Notu;
    private String tcno;
    private String dersi_alan_ogrenci_sayisi;
    public int getFinal_Notu() {
        return final_Notu;
    }

    public void setFinal_Notu(int final_Notu) {
        this.final_Notu = final_Notu;
    }

    public int getVize_Notu() {
        return vize_Notu;
    }
    
        public String getDersi_alan_ogrenci_sayisi() {
        return dersi_alan_ogrenci_sayisi;
    }

    public void setDersi_alan_ogrenci_sayisi(String dersi_alan_ogrenci_sayisi) {
        this.dersi_alan_ogrenci_sayisi = dersi_alan_ogrenci_sayisi;
    }

    public void setVize_Notu(int vize_Notu) {
        this.vize_Notu = vize_Notu;
    }
    private String ders_adi;
    private String ogrenci_tc_no;

    public ogrenciGorDataTable() {

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

             

            PreparedStatement ps = connection.prepareStatement("select DERSADI from DERSLER where AKADEMISYENTCNO = '" +akademisyen_tc_no+ "' ");
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(ps.executeQuery());
            return rowSet;
        } // end try
        finally {
            connection.close(); // return this connection to pool
        } // end finally
    }
    
    public ResultSet not_bul() throws SQLException {
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

            PreparedStatement ps = connection.prepareStatement("select AD,SOYAD,OGRENCIDERSDETAY.TCNO,VIZENOTU,FINALNOTU from OGRENCIDERSDETAY,OGRENCIBILGI \n" +
"where OGRENCIBILGI.TCNO = OGRENCIDERSDETAY.TCNO and DERSID="+ders_id+"\n" +
"order by AD asc");
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(ps.executeQuery());
            return rowSet;
        } // end try
        finally {
            connection.close(); // return this connection to pool
        } // end finally
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
            ResultSet resultSet = statement.executeQuery("select DERSID from DERSLER where DERSADI='"+ders_adi+"'");
            if (resultSet.next()) {
                setDers_id(resultSet.getInt("DERSID"));
            }
        } finally {
            connection.close();
        }

    }

    public void vizeGuncelle() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        IDGETIR();
        
        try {
                PreparedStatement ps = connection.prepareStatement("UPDATE OGRENCIDERSDETAY SET VIZENOTU=" + vize_Notu + " where TCNO='" + ogrenci_tc_no + "' and DERSID = " + ders_id + " ");
                ps.executeUpdate();
            }finally {
                connection.close();
                
            }

        }
    
     public void finalGuncelle() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        IDGETIR();
        
        try {
                PreparedStatement ps = connection.prepareStatement("UPDATE OGRENCIDERSDETAY SET FINALNOTU=" + final_Notu + " where TCNO='" + ogrenci_tc_no + "' and DERSID = " + ders_id + " ");
                ps.executeUpdate();
            }finally {
                connection.close();
                
            }

        }
     
     public ResultSet dersi_alan_ogrenciler() throws SQLException {
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

            PreparedStatement ps = connection.prepareStatement("select DERSLER.DERSADI,toplam FROM DERSLER inner join \n" +
"(select count (tcno) as toplam,dersid from ogrencidersdetay group by dersid) as aratablo on aratablo.dersid = dersler.dersid where AKADEMISYENTCNO = '"+getAkademisyen_tc_no()+"' and DERSLER.DERSID = "+ders_id+"");
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(ps.executeQuery());
            return rowSet;
        } // end try
        finally {
            connection.close(); // return this connection to pool
        } // end finally
    }


    
    public ResultSet dersi_gecen_ogrenciler() throws SQLException {
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
            int baraj=50;
            PreparedStatement ps = connection.prepareStatement("select AD,SOYAD,OGRENCINO,FINALNOTU, (select avg(FINALNOTU) from OGRENCIDERSDETAY) as BARAJ\n" +
"from OGRENCIBILGI inner join OGRENCIDERSDETAY on OGRENCIBILGI.TCNO = OGRENCIDERSDETAY.TCNO where FINALNOTU >= "+baraj+" and DERSID="+ders_id+"");
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(ps.executeQuery());
            return rowSet;
        } // end try
        finally {
            connection.close(); // return this connection to pool
        } // end finally
    }
    
    public ResultSet en_yuksek_not() throws SQLException {
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
            PreparedStatement ps = connection.prepareStatement("select AD,SOYAD,OGRENCINO,(select max(FINALNOTU) from OGRENCIDERSDETAY WHERE DERSID="+ders_id+") as \"ENYUKSEKNOT\" from OGRENCIBILGI,OGRENCIDERSDETAY\n" +
"where OGRENCIBILGI.TCNO = OGRENCIDERSDETAY.TCNO\n" +
"order by FINALNOTU desc\n" +
"fetch next 1 rows only");
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(ps.executeQuery());
            return rowSet;
        } // end try
        finally {
            connection.close(); // return this connection to pool
        } // end finally
    }
    
    public ResultSet ogrenci_ders_ortalamalari() throws SQLException {
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
            PreparedStatement ps = connection.prepareStatement("select AD,SOYAD,OGRENCINO,VIZENOTU,FINALNOTU, ((FINALNOTU+VIZENOTU)/2) as ORTALAMA from OGRENCIDERSDETAY,OGRENCIBILGI \n" +
"where OGRENCIBILGI.TCNO = OGRENCIDERSDETAY.TCNO and DERSID="+ders_id+"\n" +
"order by ORTALAMA asc");
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(ps.executeQuery());
            return rowSet;
        } // end try
        finally {
            connection.close(); // return this connection to pool
        } // end finally
    }

    
    public ResultSet ortalama_aralik() throws SQLException {
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
            PreparedStatement ps = connection.prepareStatement("select AD,SOYAD,OGRENCINO,VIZENOTU,FINALNOTU, ((FINALNOTU+VIZENOTU)/2) as ORTALAMA from OGRENCIDERSDETAY,OGRENCIBILGI \n" +
"where OGRENCIBILGI.TCNO = OGRENCIDERSDETAY.TCNO and DERSID="+ders_id+" AND (FINALNOTU+VIZENOTU)/2 > "+altsinir+" AND (FINALNOTU+VIZENOTU)/2 < "+ustsinir+"");
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(ps.executeQuery());
            return rowSet;
        } // end try
        finally {
            connection.close(); // return this connection to pool
        } // end finally
    }

}
