
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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@ManagedBean (name="giris")
@SessionScoped
public class giris {
   @Size(min =11,max = 11,message = "TC Kimlik 11 haneli olmak zorundadır")
   private String tc_no;
   
   @NotBlank(message = "Şifre boş bırakılamaz")
   private String sifre;
    
    public String getTc_no() {
        return tc_no;
    }
    public void setTc_no(String tc_no) {
        this.tc_no = tc_no;
    }
    public String getSifre() {
        return sifre;
    }
    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
    

   DataSource dataSource;
   public giris() { 
       try {
            Context ctx = new InitialContext();
            
            dataSource = (DataSource) ctx.lookup("jdbc/ogrenciOtomasyon");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    public ResultSet bul(ResultSet rs) throws SQLException {
   
    String _tc  = getTc_no();
    String _sifre  = getSifre();
    
         
    if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
      
        Connection connection = dataSource.getConnection();
      
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {

            PreparedStatement ps = connection.prepareStatement("select * from OGRENCIGIRIS where TCNO = '"+_tc+"' and SIFRE = '"+_sifre+"'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            rs=resultSet1;
            return rs;
        } 
        finally {
            connection.close();
        }
    }
    
    public String checkLogin() throws SQLException{
        ResultSet rs = null;
        rs=bul(rs);
        if(rs.next()){
            return "anaSayfa.xhtml";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_ERROR, "Giriş bilgileriniz hatalı", null));
            return "index.xhtml";
        }
    }
    public ResultSet Akademisyen_bul(ResultSet rs) throws SQLException {
   
    String _tc  = getTc_no();
    String _sifre  = getSifre();
    
         
    if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
      
        Connection connection = dataSource.getConnection();
      
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {

            PreparedStatement ps = connection.prepareStatement("select * from AKADEMISYENGIRIS where TCNO = '"+_tc+"' and SIFRE = '"+_sifre+"'");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(ps.executeQuery());
            rs=resultSet1;
            return rs;
        } 
        finally {
            connection.close();
        }
    }
    public String checkAkademisyen() throws SQLException{
        ResultSet rs = null;
        rs=Akademisyen_bul(rs);
        if(rs.next()){
            return "akademisyenAnasayfa.xhtml";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_ERROR, "Giriş bilgileriniz hatalı", null));
            return "index.xhtml";
        }
    }
}
