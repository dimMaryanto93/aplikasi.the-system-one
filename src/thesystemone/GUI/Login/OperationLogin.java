/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thesystemone.GUI.Login;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import thesystemone.database.ConnectedDb;
import thesystemone.database.PerintahSQL;

/**
 *
 * @author dimas
 */
public class OperationLogin {
	
ConnectedDb sambung = new ConnectedDb();
    public boolean login = false;
    public boolean admin = false;

    //method ini digunakan untuk mendapatkan sebuah object jika pengguna telah login / belum login dengan menggunakan boolean mengebalikan nilai berupa true / false
    public boolean isLogin() {
        return login;
    }

    //method ini diguakan untuk mengsettion sebuah object jikan penggunan telah login / belum login
    public void setLogin(boolean login) {
        this.login = login;
    }

    //method ini digunakan untuk mendapatkan bahwa yang telah login adalah administartor
    public boolean isAdmin() {
        return admin;
    }

    //method ini digunakan untuk mensetting bahwa yang login adalah administrator
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    /**method ini digunakan untuk melakukan proses login dengan menggunakan parameter
     * 
     * @param pemakai merupakan type data String yang nantinya akan dimasukan data yang di input dari object txtUser
     * @param pass merupakan type data String yang nantinya akan dimasukan data yang di input dari Object txtPass
     * @param hakAkses merupakan type data int yang nantinya akan dimasukan data yang di input dari Object cboHak
     * 
     * sambung.panggilDriver(); coding ini digunakan untuk memanggil driver yand ada di databese
     * String sql = PerintahSQL.getPerintahSelect(tabel, kolom[0], pemakai);
     * perinthan di atas akan menggasilkan script sebagai berikut
     * select * from login where username like "data yang dimasukan dari JTextField/txtUsername"
     * Connection merupakan komponen yang menangani koneksi ke database
     * Statment merupakan komponen / object yang menangani pengiriman perintah sql ke database atau melakukan kompilasi perintah sql
     * Statment merupakan komponen yang menagani penyimpanan data yang didapat dari database
     */
    
    public void prosesLogin(String pemakai,String pass ,int hakAkses){
        String tabel = PerintahSQL.tabelLogin;
        String[] kolom = PerintahSQL.kolomLogin;
        sambung.panggilDriver();
        try{
            String sql = PerintahSQL.getPerintahSelect(tabel, kolom[0], pemakai);
            Connection koneksi = sambung.hubungkanDb();
            Statement perintah = koneksi.createStatement();
            ResultSet hasil = perintah.executeQuery(sql);
            
	    /**coding ini diguanakan untuk mendapatkan data yang ada di tabel login
	     * dan data yang di input dan di databese jida data yang di dapat tidak sesuai maka
	     * akan dilemparkan ke perintah else dengan pesan "USER NOT FOUNDS"
	     */
	    if(hasil.next()){
                Object[] data = {hasil.getString(1),hasil.getString(2)
                        ,hasil.getString(3)};
                setLogin((data[1].toString().equals(pass) 
                        && data[2].equals(Integer.toString(hakAkses))));
                setAdmin(data[2].equals("0"));
            }else{
                JOptionPane.showMessageDialog(null, "User Not Founds","Warning",JOptionPane.WARNING_MESSAGE);
                setLogin(false);
                setAdmin(false);
            }            
        }catch(SQLException ex){
            ex.printStackTrace();
//ex.printStackTrace() diguanakan untuk mengetahui kesalahan sintax pada saat mengcompile perintah sql
        }
        
    }
	
}
