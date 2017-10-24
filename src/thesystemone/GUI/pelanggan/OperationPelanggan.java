/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thesystemone.GUI.pelanggan;

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
public class OperationPelanggan extends PerintahSQL{
	ConnectedDb hubungkan = new ConnectedDb();
	DefaultTableModel modelTab;
	
	public int getBanyakData(){
		return modelTab.getRowCount();
	}
	public void simpanData(String id,String nama,String alamat,String t4lahir,String tgllahir,String kelamin,
		String pekerjaan,String telp,String tglmasuk){
		String sql = getPerintahInsert(tabelPelanggan, kolomPelanggan.length);
		try{
			Connection sambung = hubungkan.hubungkanDb();
			PreparedStatement simpan = (PreparedStatement) sambung.prepareStatement(sql);
			simpan.setString(1, id);
			simpan.setString(2, nama);
			simpan.setString(3, alamat);
			simpan.setString(4, t4lahir);
			simpan.setString(5, tgllahir);
			simpan.setString(6, kelamin);
			simpan.setString(7, pekerjaan);
			simpan.setString(8, telp);
			simpan.setString(9, tglmasuk);
			simpan.executeUpdate();
			simpan.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "can't Save","Warning!",JOptionPane.WARNING_MESSAGE);
			ex.printStackTrace();
		}
	}
	
	public void ubahData(String id,String nama,String alamat,String t4lahir,String tgllahir,String kelamin,
		String pekerjaan,String telp,String tglmasuk){
		String sql = getPerintahUpdate(tabelPelanggan, kolomPelanggan, id);
		try{
			Connection sambungUbah = hubungkan.hubungkanDb();
			PreparedStatement proses = (PreparedStatement) sambungUbah.prepareStatement(sql);
			proses.setString(1, nama);
			proses.setString(2, alamat);
			proses.setString(3, t4lahir);
			proses.setString(4, tgllahir);
			proses.setString(5, kelamin);
			proses.setString(6, pekerjaan);
			proses.setString(7, telp);
			proses.setString(8, tglmasuk);
			proses.executeUpdate();
			proses.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "can't Update","Warning!",JOptionPane.WARNING_MESSAGE);
			ex.printStackTrace();
		}
	}
	
	public void hapusData(String param){
		String sql = getPerintahHapus(tabelPelanggan, kolomPelanggan[0], param);
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
		modelTab = new DefaultTableModel(null, kolomPelanggan);
		Object[] data = new Object[kolomPelanggan.length];
		int idk = 1;
		try{
			Connection koneksi = hubungkan.hubungkanDb();
			Statement cari = (Statement) koneksi.createStatement();
			ResultSet hasilCari = cari.executeQuery(qry);
			while(hasilCari.next()){
				for(int idx = 0;idx < kolomPelanggan.length;idx++){
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
		
		String[] dataBaris = new String[kolomPelanggan.length];
		for(int idx = 0;idx < kolomPelanggan.length;idx++){
			dataBaris[idx]= modelTab.getValueAt(baris, idx).toString();
		}
		return dataBaris;
	}
	
	public void setDataTabel(String qry){
		modelTab = new DefaultTableModel(null, kolomPelanggan);
		Object[] data = new Object[kolomPelanggan.length];
		int idt = 1;
		try{
			Connection koneksi = hubungkan.hubungkanDb();
			Statement perintah = (Statement) koneksi.createStatement();
			ResultSet hasil = perintah.executeQuery(qry);
			while(hasil.next()){
				for(int idx = 0;idx < kolomPelanggan.length;idx++){
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
	
	public String getNamaByID(){
		return modelTab.getValueAt(0, 1).toString();
	}
	
	public String getT4lahirByID(){
		return  modelTab.getValueAt(0, 3).toString();
	}
	
	public String getKelaminByID(){
		return  modelTab.getValueAt(0,5 ).toString();
	}
	
	public String getPekerjaanByID(){
		return  modelTab.getValueAt(0, 6).toString();
	}
	
	public String getTglMasukByID(){
		return  modelTab.getValueAt(0, 8).toString();
	}
}
