
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@ManagedBean(name = "passwordResetBean")
@RequestScoped

public class passwordResetBean {

    public String getTc_no() {
        return tc_no;
    }

    public void setTc_no(String tc_no) {
        this.tc_no = tc_no;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getBaba_adi() {
        return baba_adi;
    }

    public void setBaba_adi(String baba_adi) {
        this.baba_adi = baba_adi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
    @Size(min = 11,max = 11,message = "TC Kimlik NO 11 haneli olmak zorundadır")
    private String tc_no;
    @NotBlank(message = "Ad boş bırakılamaz")
    private String ad;
    @NotBlank(message = "Soyad boş bırakılamaz")
    private String soyad;
    @NotBlank(message = "Baba Adı boş bırakılamaz")
    private String baba_adi;
    @NotBlank(message = "Şifre boş bırakılamaz")
    private String sifre;
    private String tarih;

    DataSource dataSource;

    public passwordResetBean() {
        try {
            Context ctx = new InitialContext();

            dataSource = (DataSource) ctx.lookup("jdbc/ogrenciOtomasyon");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public ResultSet check(ResultSet rs) throws SQLException {

        String _tc_no = getTc_no();
        String _ad = getAd();
        String _soyad = getSoyad();
        String _baba_adi = getBaba_adi();
        String _sifre = getSifre();
        String _tarih = getTarih();

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {

            PreparedStatement ps = connection.prepareStatement("select * from OGRENCIBILGI where TCNO = '" + _tc_no + "' and AD = '" + _ad + "' and SOYAD = '" + _soyad + "' and BABADI = '" + _baba_adi + "' and TARIH = '" + _tarih + "'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            rs = resultSet1;
            return rs;
        } finally {
            connection.close();
        }
    }

    public String guncelle() throws SQLException, InterruptedException {
        ResultSet rs = null;
        rs = check(rs);
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        if (rs.next()) {
            PreparedStatement ps = connection.prepareStatement("update OGRENCIGIRIS set SIFRE = '" + getSifre() + "' where TCNO = '" + getTc_no() + "'");
            ps.executeUpdate();
            FacesMessage message = new FacesMessage("Şifre Değiştirme Başarılı");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "index.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_ERROR, "Hatalı bilgi girişi", null));
            return "passwordResetPage.xhtml";
        }
    }
    
    public ResultSet Akademisyen_check(ResultSet rs) throws SQLException {

        String _tc_no = getTc_no();
        String _ad = getAd();
        String _soyad = getSoyad();
        String _baba_adi = getBaba_adi();
        String _sifre = getSifre();
        String _tarih = getTarih();

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {

            PreparedStatement ps = connection.prepareStatement("select * from AKADEMISYENBILGI where TCNO = '" + _tc_no + "' and AD = '" + _ad + "' and SOYAD = '" + _soyad + "' and BABADI = '" + _baba_adi + "' and TARIH = '" + _tarih + "'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            rs = resultSet1;
            return rs;
        } finally {
            connection.close();
        }
    }

    public String Akademisyen_guncelle() throws SQLException, InterruptedException {
        ResultSet rs = null;
        rs = Akademisyen_check(rs);
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        if (rs.next()) {
            PreparedStatement ps = connection.prepareStatement("update AKADEMISYENGIRIS set SIFRE = '" + getSifre() + "' where TCNO = '" + getTc_no() + "'");
            ps.executeUpdate();
            FacesMessage message = new FacesMessage("Şifre Değiştirme Başarılı");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "index.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_ERROR, "Hatalı bilgi girişi", null));
            return "passwordResetPage.xhtml";
        }
    }
    

}
