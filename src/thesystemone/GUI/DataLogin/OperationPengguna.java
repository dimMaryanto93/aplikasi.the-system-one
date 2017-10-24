/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thesystemone.GUI.DataLogin;

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
public class OperationPengguna extends PerintahSQL {
	ConnectedDb hubungkan = new ConnectedDb();
	DefaultTableModel modelTab;
	
	public int getBanyakData(){
		return modelTab.getRowCount();
	}
	
	public void simpanData(String id,String pass,int hak){
		String sql = getPerintahInsert(tabelLogin, kolomLogin.length);
		try{
			Connection sambung = hubungkan.hubungkanDb();
			PreparedStatement simpan = (PreparedStatement) sambung.prepareStatement(sql);
			simpan.setString(1, id);
			simpan.setString(2, pass);
			simpan.setInt(3, hak);
			simpan.executeUpdate();
			simpan.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "can't Save","Warning!",JOptionPane.WARNING_MESSAGE);
			ex.printStackTrace();
		}
	}
	
	public void ubahData(String id,String pass,int hak){
		String sql = getPerintahUpdate(tabelLogin, kolomLogin, id);
		try{
			Connection sambungUbah = hubungkan.hubungkanDb();
			PreparedStatement proses = (PreparedStatement) sambungUbah.prepareStatement(sql);
			proses.setString(1, pass);
			proses.setInt(2, hak);
			proses.executeUpdate();
			proses.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "can't Update","Warning!",JOptionPane.WARNING_MESSAGE);
			ex.printStackTrace();
		}
	}
	
	public void hapusData(String param){
		String sql = getPerintahHapus(tabelLogin, kolomLogin[0], param);
		try{
			Connection hapus = hubungkan.hubungkanDb();
			PreparedStatement proses = (PreparedStatement) hapus.prepareStatement(sql);
			proses.executeUpdate();
			proses.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "can't Delete","Warning!",JOptionPane.WARNING_MESSAGE);
			ex.printStackTrace();
		}
	}
	
	public DefaultTableModel getDataTabelLogin(String qry){
		modelTab = new DefaultTableModel(null, kolomLogin);
		Object[] data = new Object[kolomLogin.length];
		int idk = 1;
		try{
			Connection koneksi = hubungkan.hubungkanDb();
			Statement cari = (Statement) koneksi.createStatement();
			ResultSet hasilCari = cari.executeQuery(qry);
			while(hasilCari.next()){
				for(int idx = 0;idx < kolomLogin.length;idx++){
					data[idx] = hasilCari.getObject(idk);
					idk++;
				}
				idk = 1;
				modelTab.addRow(data);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return modelTab;
	}
	
	public Object[] getDataKolom(int idxKolom){
		Object[] data = new Object[modelTab.getRowCount() +1];
		data[0]="";
		int idk = 1;
		for(int idx = 0;idx < modelTab.getRowCount();idx++){
			data[idk++]=modelTab.getValueAt(idx, idxKolom);
		}		
		return data;	
	}
	
	public String[] getDataTabel(int baris){
		
		String[] dataBaris = new String[kolomLogin.length];
		for(int idx = 0;idx < kolomLogin.length;idx++){
			dataBaris[idx]= modelTab.getValueAt(baris, idx).toString();
		}
		return dataBaris;
	}
	
	public void setDataTabel(String qry){
		modelTab = new DefaultTableModel(null, kolomLogin);
		Object[] data = new Object[kolomLogin.length];
		int idt = 1;
		try{
			Connection koneksi = hubungkan.hubungkanDb();
			Statement perintah = (Statement) koneksi.createStatement();
			ResultSet hasil = perintah.executeQuery(qry);
			while(hasil.next()){
				for(int idx = 0;idx < kolomLogin.length;idx++){
					data[idx] = hasil.getString(idt);
					idt++;
				}
				idt = 1;
				modelTab.addRow(data);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public String getUsernameByID(){
		return modelTab.getValueAt(0, 0).toString();
	}
	
	public String getHakByID(){
		return  modelTab.getValueAt(0, 2).toString();
	}
	
	
}


