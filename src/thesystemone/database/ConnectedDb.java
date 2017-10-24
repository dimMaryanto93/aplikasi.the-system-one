/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thesystemone.database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author dimas
 */
public class ConnectedDb implements Connect{
	private Connection konek;
	String status;
	
	/**method ini adalah override dari interface class Connect
	 * yang berfungsi untuk mencari driver MySql server
	 * 
	 */
	@Override
	public void panggilDriver() {
		try{
			Class.forName(Connect.DRIVERSERVER);
		}catch(ClassNotFoundException ex){
			JOptionPane.showMessageDialog(null, "DRIVER NOT FOUND",
				"WARNING!",JOptionPane.WARNING_MESSAGE);
		}
	}

	/**method ini override dari interface class Connect
	 * yang berfungi untuk menghubungkan database dengan java dengan cara 
	 * melalukan pemanggilan URL dan setting username dan password
	 * pada class java.sql.Connection akan mengembalikan sebuah nilai
	 * jika pemanggilan success maka akan mengembalikan nilai true karena 
	 * java.sql.Connection bersifat boolean
	 * @return 
	 */
	@Override
	public Connection hubungkanDb() {
		try{
			konek = (Connection) DriverManager.getConnection(Connect.URL,Connect.username,Connect.PASS);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Connection failure"
				,"WARNING!",JOptionPane.WARNING_MESSAGE);
		}
		return konek;
	}
	
}
