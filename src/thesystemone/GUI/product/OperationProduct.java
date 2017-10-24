/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thesystemone.GUI.product;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import thesystemone.database.ConnectedDb;
import thesystemone.database.PerintahSQL;

/**
 *
 * @author dimas
 */
public class OperationProduct extends PerintahSQL{
	//declaration variable & object
	ConnectedDb hub = new ConnectedDb();
	DefaultTableModel modelTabel;
	
	/**
	 * method ini digunakan untuk mendapatkan banyak 
	 * data yang terdapat pada JTable
	 * 
	 * @return jmlbanyakdataTabel[integer] 
	 */
	public int getBanyakData(){
		return modelTabel.getRowCount();
	}
	
	/**
	 * method ini diguanakan untuk mengimpan data product 
	 * dengan menggunkan sebuah parameter sbb:	 * 
	 * @param id  merupakan variable String yand diguanakan untuk mengimpan sebuah Object dari JTextField : txtId
	 *		di asumsikan id disini merupakan primary key jadi apabila mengimput data yang telah 
	 *		tersedia datanya di database maka perintah insert akan melempar ekpersi ke SQLException
	 *		karena id bersifat unik
	 * @param nama merupakan variable String yand diguanakan untuk mengimpan sebuah Object dari JTextField : txtNama
	 * @param type merupakan variable Object yang digunakan untuk mengimpan sebuah Object dari JComboBox : cboType
	 * @param pabrik type merupakan variable Object yang digunakan untuk mengimpan 
	 *	  sebuah Object dari JComboBox : cboManufaktur
	 * @param qty merupakan variable Integer yang digunakan untuk mengimpan sebuah Object dari JTextField : txtQty 
	 * @param harga merupakan variable Integer yang diguankan untuk mengimpan sebuah Object dari 
	 *		JTextField : txtHarga
	 * @param detail merupakan variable String yang digunakan utnuk mengimpan Object dari JTextArea : txtDetail
	 * method ini berfungsi sebagai jembatan antar database MySql dengan java untuk mengimpan data dengan cara kerja
	 * mengimpan perintah sql insert yang berada pada kelas induk yaitu PerintahSQL di sebuah 
	 * variable sql berupa String, lalu mengcase Try untuk menghubungkan sebuah connection kemudian 
	 * kelas PreparedStatement diguankan untuk mengcompile perintah sql insert yang telah di inisalisasi
	 * kemudian buat sebuah object yang mewakili sebuah proses penyimpana misal proses lalu proses di inisialisasi
	 * sebagai PreparedStatement yang dihubungkan ke database
	 * lalu setting object proses tersebut sesuai dengan type data dari parameter tersebut yang akan berbentuk array 
	 * setelah itu lalukan executeUpdate untuk menggcompile perintahSQL.getPerintahInsert tersebut dan close
	 * perintah catch(SQLException ex) ini digunakan untuk mengalihkan sesalah yang dilakukan pada saat java 
	 * mengcompile perintah sql, jika terjadi kesalah maka akan memuncukan sebuah pesan Can't Save Complete yang 
	 * di ekpresikan dengan JOptionPane secara GUI (graphic User Interface) dan secara console.
	 * ex.printStackTrace(); digunakan untuk mendapatkan pesan kesalahan pada melakukan compilasi perintahSQL 
	 * dengan java pada saat di execute
	 * 
	 */
	public void simpanData(String id,String nama,Object type,Object pabrik,
		int qty,int harga,String detail){
		String sql = getPerintahInsert(tabelProduct, kolomProduct.length);
		try{
			Connection sambung = hub.hubungkanDb();
			
			PreparedStatement proses = (PreparedStatement) sambung.prepareStatement(sql);
			proses.setString(1, id);
			proses.setString(2, nama);
			proses.setObject(3, type.toString());
			proses.setObject(4, pabrik.toString());
			proses.setInt(5, qty);
			proses.setInt(6, harga);
			proses.setString(7, detail);
			proses.executeUpdate();
			proses.close();			
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "can't Save Complete!","Warning",JOptionPane.WARNING_MESSAGE);
			ex.printStackTrace();
		}
	}
	/**method ini diguankan untuk Update Data pada tabel Product
	 * 
	 * @param id merupakan variable String yand diguanakan untuk mengimpan sebuah Object dari JTextField : txtId
	 *		di asumsikan id disini merupakan primary key jadi apabila mengimput data yang telah 
	 *		tersedia datanya di database maka perintah insert akan melempar ekpersi ke SQLException
	 *		karena id bersifat unik	
	 * @param nama merupakan variable String yand diguanakan untuk mengimpan sebuah Object dari JTextField : txtNama
	 * @param type merupakan variable Object yang digunakan untuk mengimpan sebuah Object dari JComboBox : cboType
	 * @param pabrik type merupakan variable Object yang digunakan untuk mengimpan 
	 *	  sebuah Object dari JComboBox : cboManufaktur
	 * @param qty merupakan variable Integer yang digunakan untuk mengimpan sebuah Object dari JTextField : txtQty 
	 * @param harga merupakan variable Integer yang diguankan untuk mengimpan sebuah Object dari 
	 *		JTextField : txtHarga
	 * @param detail merupakan variable String yang digunakan utnuk mengimpan Object dari JTextArea : txtDetail
	 * 
	 * untuk penjelasan mengenai coding di bawah ini tidak akan dijelaskan kembali karena menggunakan konsep 
	 * yang sama dengan method simpanData() hanya terdapat sedikit perbedaan penulis disini mengasumsikan bahwa 
	 * id tidak dapat di edit karena sebagai primary key jadi id tersebut sebagai acuan data yang akan diedit.
	 */
	public void ubahData(String id,String nama,Object type,Object pabrik,
		int qty,int harga,String detail){
		String sql = getPerintahUpdate(tabelProduct, kolomProduct, id);
		
		try{
			Connection sambungUbah = hub.hubungkanDb();
			java.sql.PreparedStatement proses = sambungUbah.prepareStatement(sql);
			proses.setString(1, nama);
			proses.setObject(2, type.toString());
			proses.setObject(3, pabrik.toString());
			proses.setInt(4, qty);
			proses.setInt(5, harga);
			proses.setString(6, detail);
			proses.executeUpdate();
			proses.close();	
			
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "can't Update Complete!","Warning",JOptionPane.WARNING_MESSAGE);
			ex.printStackTrace();
		}
	}
	
	/**method ini digunakan untuk menghapus data dari tabel product / delete
	 *  
	 * @param param merupakan sebuah variabel yang akan mengabil data dari Object/class JTextField : txtId pada Class
	 *		Product.java
	 * 
	 */
	public void hapusData(String param){
		String sql = getPerintahHapus(tabelProduct, kolomProduct[0], param);
		
		try{
			Connection hapus = hub.hubungkanDb();
			PreparedStatement proses = (PreparedStatement) hapus.prepareStatement(sql);
			proses.executeUpdate();
			proses.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "can't Delete Complete!","Warning",JOptionPane.WARNING_MESSAGE);
			ex.getMessage();
		}
	}
	
	/**method ini digunakan untuk menampikan data ke tabel / JTable pada javaSwing.
	 * 
	 * @param qry
	 * @return 
	 */	
	public DefaultTableModel getDataTabelProduct(String qry){
		modelTabel = new DefaultTableModel(null, kolomProduct);
		Object[] data = new Object[kolomProduct.length];
		
		int idk = 1;
		try{
			Connection koneksi = hub.hubungkanDb();
			Statement cari = (Statement) koneksi.createStatement();
			ResultSet hasilCari = cari.executeQuery(qry);
			while(hasilCari.next()){
				for(int idx = 0;idx<kolomProduct.length;idx++){
					data[idx] = hasilCari.getObject(idk);
					idk++;
				}
				idk = 1;
				modelTabel.addRow(data);				
			}
			
		}catch(SQLException ex){
			ex.getMessage();
			
		}		
		return modelTabel;
	}
	/**method ini diguankan untuk mendapatkan data column yang ada di JTable
	 * 
	 * @param idkKolom
	 * @return 
	 */
	public Object[] getDataKolom(int idkKolom){
		Object[] data = new Object[modelTabel.getRowCount()+1];
		data[0] = "";
		int idk = 1;
		for(int idx = 0; idx < modelTabel.getRowCount();idx++){
			data[idk++] = modelTabel.getValueAt(idx, idkKolom);
		}
		return data;
	}
	
	/**method ini digunakan untuk mendapatakan data rows yang ada di JTable
	 * 
	 * @param baris
	 * @return 
	 */
	public String[] getDataTabel(int baris){
		String[] databaris=new String[kolomProduct.length];
		for(int idx = 0;idx < kolomProduct.length;idx++){
			databaris[idx] = modelTabel.getValueAt(baris, idx).toString();
		}		
		return databaris;
	}
	
	/**
	 * 
	 * @param qry 
	 */
	public void setDataTabel(String qry){
		modelTabel = new DefaultTableModel(null,kolomProduct);
		Object[] data = new Object[kolomProduct.length];
		int idt = 1;
		
		try{
			Connection koneksi = hub.hubungkanDb();
			Statement perintah = (Statement) koneksi.createStatement();
			ResultSet hasil = perintah.executeQuery(qry);
			
			while(hasil.next()){
				for (int i = 0; i < kolomProduct.length; i++) {
					data[i] = hasil.getString(idt);
					idt++;
				}
				idt=1;
				modelTabel.addRow(data);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public String getNamaById(){
		return modelTabel.getValueAt(0, 1).toString();		
	}
	
	public String getTypeByID(){
		return modelTabel.getValueAt(0, 2).toString();		
	}
	
	public String getPabrikByID(){
		return modelTabel.getValueAt(0, 3).toString();
	}
	
	
}
