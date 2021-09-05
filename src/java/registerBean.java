
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@ManagedBean(name = "registerBean")
@RequestScoped

public class registerBean {

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

    public String getAnne_adi() {
        return anne_adi;
    }

    public void setAnne_adi(String anne_adi) {
        this.anne_adi = anne_adi;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getTarih() {
        return tarih;
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
    public int getOgrenciNo() {
        return ogrenciNo;
    }

    public void setOgrenciNo(int ogrenciNo) {
        this.ogrenciNo = ogrenciNo;
    }

    private int ogrenciNo;
    @Size(min =11,max = 11,message = "TC Kimlik 11 haneli olmak zorundadır")
    private String tc_no;
    @NotBlank(message = "İsim boş olamaz")
    private String ad;
    @NotBlank(message = "Soyisim boş olamaz")
    private String soyad;
    @NotBlank(message = "Baba adı boş olamaz")
    private String baba_adi;
    @NotBlank(message = "Anne adı boş olamaz")
    private String anne_adi;
    @NotBlank(message = "Cinsiyet boş olamaz")
    private String cinsiyet;
    private String tarih;
    @NotBlank(message = "Şifreniz boş olamaz")
    private String sifre;
    DataSource dataSource;

    public registerBean() {
        try {
            Context ctx = new InitialContext();

            dataSource = (DataSource) ctx.lookup("jdbc/ogrenciOtomasyon");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public String kaydet() throws SQLException {

        String _tc_no = getTc_no();
        String _ad = getAd();
        String _soyad = getSoyad();
        String _baba_adi = getBaba_adi();
        String _anne_adi = getAnne_adi();
        String _cinsiyet = getCinsiyet();
        String _tarih = getTarih();
        String _sifre = getSifre();

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        
            try {
                
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select TCNO from OGRENCIBILGI where TCNO = '"+_tc_no+"'");
                if(resultSet.next()){
                FacesMessage message = new FacesMessage("Böyle bir kayıt bulunmaktadır");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "registerPage.xhtml";
                }else{

                PreparedStatement ps = connection.prepareStatement("insert into OGRENCIBILGI (TCNO,AD,SOYAD,BABADI,ANNEADI,CINSIYET,TARIH) values('" + _tc_no + "','" + _ad + "','" + _soyad + "','" + _baba_adi + "','" + _anne_adi + "','" + _cinsiyet + "','" + _tarih + "')");
                ps.executeUpdate();
                PreparedStatement ps1 = connection.prepareStatement("insert into OGRENCIGIRIS values('" + _tc_no + "','" + _sifre + "')");
                ps1.executeUpdate();
                FacesMessage message = new FacesMessage("Kayıt Başarılı");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "index.xhtml";
                }
            } finally {
                connection.close();

            }

    }
    public String Akademisyen_kaydet() throws SQLException {

        String _tc_no = getTc_no();
        String _ad = getAd();
        String _soyad = getSoyad();
        String _baba_adi = getBaba_adi();
        String _anne_adi = getAnne_adi();
        String _cinsiyet = getCinsiyet();
        String _tarih = getTarih();
        String _sifre = getSifre();

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        
            try {
                
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select TCNO from AKADEMISYENBILGI where TCNO = '"+_tc_no+"'");
                if(resultSet.next()){
                FacesMessage message = new FacesMessage("Böyle bir kayıt bulunmaktadır");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "registerPage.xhtml";
                }else{

                PreparedStatement ps = connection.prepareStatement("insert into AKADEMISYENBILGI (TCNO,AD,SOYAD,BABADI,ANNEADI,CINSIYET,TARIH) values('" + _tc_no + "','" + _ad + "','" + _soyad + "','" + _baba_adi + "','" + _anne_adi + "','" + _cinsiyet + "','" + _tarih + "')");
                ps.executeUpdate();
                PreparedStatement ps1 = connection.prepareStatement("insert into AKADEMISYENGIRIS values('" + _tc_no + "','" + _sifre + "')");
                ps1.executeUpdate();
                FacesMessage message = new FacesMessage("Kayıt Başarılı");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "index.xhtml";
                }
            } finally {
                connection.close();

            }

    }
    
}
