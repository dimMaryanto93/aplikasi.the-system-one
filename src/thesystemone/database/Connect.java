/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thesystemone.database;

import com.mysql.jdbc.Connection;

/**
 *
 * @author dimas
 */
public interface Connect {
	//declaration variabel pada class Interface
	String DRIVERSERVER="com.mysql.jdbc.Driver";//inisialisasi driver MySql
	String DRIVEREMBEDDED="";
	String URL ="jdbc:mysql://localhost:3306/system_one";
	String HOST ="localhost";
	String PORT = "3306";
	String DB = "system_one";//inisisalisasi schema pada MySQL
	String username = "root";//inisialisasi username pada MySQl
	String PASS = "dimasMaryanto";//inisialisasi password pada MySQl
	public void panggilDriver();
	public Connection hubungkanDb();
}
