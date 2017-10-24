/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thesystemone.GUI.pelanggan;

import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import thesystemone.database.ConnectedDb;
import thesystemone.database.PerintahSQL;

/**
 *
 * @author dimas
 */
public class Pelanggan extends javax.swing.JInternalFrame {

	ConnectedDb driver;
	OperationPelanggan opPelanggan;
	private int n;
	private boolean simpanTambah;
	private boolean hapusData;
	private boolean simpanDataTambah;
	//variable array ini diguanakan untuk item di JComboBox sebagai keterangan
	String[] bulan = {"JANUARI","FEBRUARI","MARET","APRIL","MEI","JUNI",
		"JULI","AGUSTUS","SEPTEMBER","OKTOBER","NOVEMBER","DESEMBER"};
	
	/**
	 * Creates new form Pelanggan
	 */
	public Pelanggan() {
		initComponents();
		inisialisasi();
	}
	
	private int hariIni(){
		Calendar h = Calendar.getInstance();
		int hari = h.get(Calendar.DAY_OF_MONTH);
		return hari;		
	}
	
	private int bulanIni(){
		Calendar b = Calendar.getInstance();
		int bulan = b.get(Calendar.MONTH);
		return bulan;
	}
	
	private int tahunIni(){
		Calendar t = Calendar.getInstance();
		int tahun = t.get(Calendar.YEAR);
		return tahun;
	}
	
	private void inisialisasi(){
		driver = new ConnectedDb();
		driver.panggilDriver();
		opPelanggan = new OperationPelanggan();
		kosongkanTabel();
		tampilToTabel(PerintahSQL.getPerintahSelect(PerintahSQL.tabelPelanggan));
		txtNamaDatabese.setText(PerintahSQL.tabelPelanggan);
	}
	
	private DefaultTableModel getTabelModel(){
		return (DefaultTableModel) tabelPelanggan.getModel();
	}

	public boolean isHapusData() {
		return hapusData;
	}

	public void setHapusData(boolean hapusData) {
		this.hapusData = hapusData;
	}
	
	private void aktifkanKomponen(boolean b){
		this.txtId.setEnabled(b);
		this.txtNama.setEnabled(b);
		this.txtAlamat.setEnabled(b);
		this.txtT4l.setEnabled(b);
		this.cboHariLahir.setEnabled(b);
		this.cboBulanLahir.setEnabled(b);
		this.cboTahunLahir.setEnabled(b);
		this.cboKelamin.setEnabled(b);
		this.txtPekerjaan.setEnabled(b);
		this.txtTelp.setEnabled(b);
		this.cboHariMasuk.setEnabled(b);
		this.cboBulanMasuk.setEnabled(b);
		this.cboTahunMasuk.setEnabled(b);
	}
	
	private void aturKomponen(boolean tambah,boolean simpan,String batal){
		this.btnNew.setEnabled(tambah);
		this.btnEdit.setEnabled(tambah);
		this.btnSave.setEnabled(simpan);
		btnDelete.setText(batal);
		setHapusData(tambah);
		this.aktifkanKomponen(simpan);
	}
	
	/**method ini digunakan untuk mensetting data yang ada di tabel ke komponen JTextFiled dan JComboBox
	 * 
	 * @param baris 
	 */
	private void dataDetail(int baris){
		this.txtId.setText(getTabelModel().getValueAt(baris, 0).toString());
		this.txtNama.setText(getTabelModel().getValueAt(baris, 1).toString());
		this.txtAlamat.setText(getTabelModel().getValueAt(baris, 2).toString());
		this.txtT4l.setText(getTabelModel().getValueAt(baris, 3).toString());
		String tglLahir = String.valueOf(getTabelModel().getValueAt(baris, 4).toString());
		int hariLahir  = Integer.parseInt(tglLahir.substring(0,2));
		int bulanLahir = Integer.parseInt(tglLahir.substring(3,5));
		int tahunLahir = Integer.parseInt(tglLahir.substring(6,10));
		//mungkin salah
		this.cboHariLahir.setSelectedIndex(hariLahir-1);
		this.cboBulanLahir.setSelectedIndex(bulanLahir-1);
		this.cboTahunLahir.setSelectedItem(tahunLahir);
		this.cboKelamin.setSelectedItem(getTabelModel().getValueAt(baris, 5));
		this.txtPekerjaan.setText(getTabelModel().getValueAt(baris, 6).toString());
		this.txtTelp.setText(getTabelModel().getValueAt(baris, 7).toString());
		String tglMasuk = String.valueOf(getTabelModel().getValueAt(baris, 8).toString());
		int hariMasuk = Integer.parseInt(tglMasuk.substring(0,2));
		int bulanMasuk = Integer.parseInt(tglMasuk.substring(3,5));
		int tahunMasuk = Integer.parseInt(tglMasuk.substring(6,10));
		this.cboHariMasuk.setSelectedIndex(hariMasuk-1);
		this.cboBulanMasuk.setSelectedIndex(bulanMasuk-1);
		this.cboTahunMasuk.setSelectedItem(tahunMasuk);		
	}
	
	private int getJumlahDataTabel(){
		return getTabelModel().getRowCount()-1;
	}
	
	private boolean isSimpanTambah(){
		return simpanDataTambah;
	}
	
	private void kosongkanDataDetail(){
		this.txtId.setText("");
		this.txtNama.setText("");
		this.txtAlamat.setText("");
		this.txtT4l.setText("");
		this.cboHariLahir.setSelectedIndex(0);
		this.cboBulanLahir.setSelectedIndex(0);
		this.cboTahunLahir.setSelectedIndex(0);
		this.cboKelamin.setSelectedIndex(0);
		this.txtPekerjaan.setText("");
		this.txtTelp.setText("");
		this.cboHariMasuk.setSelectedIndex(hariIni()-1);
		this.cboBulanMasuk.setSelectedIndex(bulanIni());
		this.cboTahunMasuk.setSelectedItem(tahunIni());
	}
	
	private void kosongkanTabel(){
		int k = 0;
		while(k< getTabelModel().getRowCount()){
			getTabelModel().removeRow(0);
			k++;
		}
	}
	
	private void navigasiAkhir(){
		n = getTabelModel().getRowCount() - 1;
		int a = n;
		dataDetail(n);
		this.txtJmlData.setText(Integer.toString(a+1));
	}
	
	private void navigasiAwal(){
		n = 0;
		int a= n;
		dataDetail(n);
		this.txtJmlData.setText(Integer.toString(n+1));
	}
	
	private void navigasiMaju(){
		++n;
		if(n > getTabelModel().getRowCount()-1){
			n = getTabelModel().getRowCount()-1;
			JOptionPane.showMessageDialog(null, "Finnaly");
		}
		int a = n;
		dataDetail(a);
		this.txtJmlData.setText(Integer.toString(a+1));
	}
	
	private void navigasiMundur(){
		--n;
		if(n < 0){
			n = 0;
			JOptionPane.showMessageDialog(null, "First");
		}
		int a = n;
		dataDetail(a);
		this.txtJmlData.setText(Integer.toString(a+1));
		
	}
	
	private void addBarisKosong(){
		getTabelModel().addRow(new Object[]{null,null,null});
	}
	
	private void tampilToTabel(String sql){
		opPelanggan.setDataTabel(sql);
		kosongkanTabel();
		for(int a = 0;a< opPelanggan.getBanyakData();a++){
			addBarisKosong();
			for(int b = 0;b<getTabelModel().getColumnCount();b++){
				String[] data = opPelanggan.getDataTabel(a);
				getTabelModel().setValueAt(data[b], a, b);
			}
		}
		this.txtJmlData.setText(this.getTabelModel().getRowCount()-1+"");
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                RadioGrupKelamin = new javax.swing.ButtonGroup();
                penelPencarian = new javax.swing.JPanel();
                cekCar = new javax.swing.JCheckBox();
                lbFillter = new javax.swing.JLabel();
                cboCari = new javax.swing.JComboBox();
                txtParam1 = new javax.swing.JTextField();
                txtParam2 = new javax.swing.JTextField();
                btnCari = new javax.swing.JButton();
                scrolTabelPelanggan = new javax.swing.JScrollPane();
                tabelPelanggan = new javax.swing.JTable();
                panelNavigasi = new javax.swing.JPanel();
                btnNaviAwal = new javax.swing.JButton();
                btnNaviKembali = new javax.swing.JButton();
                btnNaviAkhir = new javax.swing.JButton();
                btnNaviMaju = new javax.swing.JButton();
                txtJmlData = new javax.swing.JTextField();
                lbJmlData = new javax.swing.JLabel();
                txtNamaDatabese = new javax.swing.JTextField();
                lbNamaDb = new javax.swing.JLabel();
                lbt2 = new javax.swing.JLabel();
                panelDataTerpilih = new javax.swing.JPanel();
                lbID = new javax.swing.JLabel();
                lbNama = new javax.swing.JLabel();
                lbt4l = new javax.swing.JLabel();
                lbKel = new javax.swing.JLabel();
                lbPek = new javax.swing.JLabel();
                txtId = new javax.swing.JTextField();
                txtNama = new javax.swing.JTextField();
                txtT4l = new javax.swing.JTextField();
                txtPekerjaan = new javax.swing.JTextField();
                lbTlp = new javax.swing.JLabel();
                lbTglM = new javax.swing.JLabel();
                lbTglL = new javax.swing.JLabel();
                lbAlamat = new javax.swing.JLabel();
                txtTelp = new javax.swing.JTextField();
                cboHariMasuk = new javax.swing.JComboBox();
                cboBulanMasuk = new javax.swing.JComboBox();
                cboTahunMasuk = new javax.swing.JComboBox();
                cboHariLahir = new javax.swing.JComboBox();
                cboBulanLahir = new javax.swing.JComboBox();
                cboTahunLahir = new javax.swing.JComboBox();
                scrollTextArea = new javax.swing.JScrollPane();
                txtAlamat = new javax.swing.JTextArea();
                cboKelamin = new javax.swing.JComboBox();
                panelTombol = new javax.swing.JPanel();
                btnExit = new javax.swing.JButton();
                btnClose = new javax.swing.JButton();
                btnNew = new javax.swing.JButton();
                btnEdit = new javax.swing.JButton();
                btnSave = new javax.swing.JButton();
                btnDelete = new javax.swing.JButton();

                setClosable(true);
                setMaximizable(true);

                penelPencarian.setBorder(javax.swing.BorderFactory.createTitledBorder("Pencarian"));

                cekCar.setSelected(true);
                cekCar.setText("Show All");

                lbFillter.setText("Fillter By");

                cboCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                cboCari.setEnabled(false);

                txtParam1.setEnabled(false);

                txtParam2.setEnabled(false);

                btnCari.setText("Cari");
                btnCari.setEnabled(false);

                javax.swing.GroupLayout penelPencarianLayout = new javax.swing.GroupLayout(penelPencarian);
                penelPencarian.setLayout(penelPencarianLayout);
                penelPencarianLayout.setHorizontalGroup(
                        penelPencarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(penelPencarianLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cekCar)
                                .addGap(18, 18, 18)
                                .addComponent(lbFillter, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboCari, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtParam1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtParam2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                penelPencarianLayout.setVerticalGroup(
                        penelPencarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(penelPencarianLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(penelPencarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cekCar)
                                        .addComponent(lbFillter)
                                        .addComponent(cboCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtParam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtParam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCari))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                tabelPelanggan.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "ID", "NAMA", "Alamat", "Tempat Lahir", "Tanggal Lahir ", "Kelamin", "Pekerjaan", "Telp/HP", "Tgl Masuk"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                scrolTabelPelanggan.setViewportView(tabelPelanggan);

                panelNavigasi.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                btnNaviAwal.setText("Awal");
                btnNaviAwal.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnNaviAwalActionPerformed(evt);
                        }
                });

                btnNaviKembali.setText("Kembali");
                btnNaviKembali.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnNaviKembaliActionPerformed(evt);
                        }
                });

                btnNaviAkhir.setText("Akhir");
                btnNaviAkhir.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnNaviAkhirActionPerformed(evt);
                        }
                });

                btnNaviMaju.setText("Maju");
                btnNaviMaju.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnNaviMajuActionPerformed(evt);
                        }
                });

                txtJmlData.setEditable(false);

                lbJmlData.setText("/ Data terseleksi");

                txtNamaDatabese.setEditable(false);

                lbNamaDb.setText("Nama Tabel");

                lbt2.setText(":");

                javax.swing.GroupLayout panelNavigasiLayout = new javax.swing.GroupLayout(panelNavigasi);
                panelNavigasi.setLayout(panelNavigasiLayout);
                panelNavigasiLayout.setHorizontalGroup(
                        panelNavigasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelNavigasiLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnNaviAwal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNaviKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtJmlData, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbJmlData)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                                .addComponent(lbt2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                                .addComponent(lbNamaDb)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNamaDatabese, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNaviMaju, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNaviAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                panelNavigasiLayout.setVerticalGroup(
                        panelNavigasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelNavigasiLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelNavigasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnNaviAwal)
                                        .addComponent(btnNaviKembali)
                                        .addComponent(btnNaviAkhir)
                                        .addComponent(btnNaviMaju)
                                        .addComponent(txtJmlData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbJmlData)
                                        .addComponent(txtNamaDatabese, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbNamaDb)
                                        .addComponent(lbt2))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                panelDataTerpilih.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Terpilih"));

                lbID.setText("ID");

                lbNama.setText("Nama");

                lbt4l.setText("Tempat Lahir");

                lbKel.setText("Kelamin");

                lbPek.setText("Pekerjaan");

                txtId.setEnabled(false);

                txtNama.setEnabled(false);

                txtT4l.setEnabled(false);

                txtPekerjaan.setEnabled(false);

                lbTlp.setText("Telp");

                lbTglM.setText("Tgl Masuk");

                lbTglL.setText("Tgl Lahir");

                lbAlamat.setText("Alamat");

                txtTelp.setEnabled(false);

                cboHariMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "item 1", "item 2", "item 3", "item 4" }));
                cboHariMasuk.setEnabled(false);
                cboHariMasuk.removeAllItems();
                for(int a = 1;a<30;a++){
                        cboHariMasuk.addItem(a<=9?"0"+a:a);
                }

                cboBulanMasuk.removeAllItems();
                cboBulanMasuk.setEnabled(false);
                for(int i = 0;i<12;i++){
                        cboBulanMasuk.addItem(bulan[i]);
                }

                cboTahunMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                cboTahunMasuk.setEnabled(false);
                cboTahunMasuk.removeAllItems();
                for(int t = 2013 ; t <= 2020 ; t++){
                        cboTahunMasuk.addItem(t);
                }

                cboHariLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                cboHariLahir.setEnabled(false);
                cboHariLahir.removeAllItems();
                for(int a = 1;a<=30;a++){
                        cboHariLahir.addItem(a<=9?"0"+a:a);
                }

                cboBulanLahir.removeAllItems();
                cboBulanLahir.setEnabled(false);
                for(int b = 0;b<12;b++){
                        cboBulanLahir.addItem(bulan[b]);
                }

                cboTahunLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                cboTahunLahir.setEnabled(false);
                cboTahunLahir.removeAllItems();
                for(int t = 1990 ;t<= 2013;t++){
                        cboTahunLahir.addItem(t);
                }

                txtAlamat.setColumns(20);
                txtAlamat.setRows(5);
                txtAlamat.setEnabled(false);
                scrollTextArea.setViewportView(txtAlamat);

                cboKelamin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki - Laki", "Perempuan" }));
                cboKelamin.setEnabled(false);

                javax.swing.GroupLayout panelDataTerpilihLayout = new javax.swing.GroupLayout(panelDataTerpilih);
                panelDataTerpilih.setLayout(panelDataTerpilihLayout);
                panelDataTerpilihLayout.setHorizontalGroup(
                        panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelDataTerpilihLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbt4l, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(lbKel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbPek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNama)
                                        .addComponent(txtT4l)
                                        .addComponent(txtPekerjaan)
                                        .addComponent(txtId)
                                        .addComponent(cboKelamin, 0, 200, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbTlp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbTglM, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(lbTglL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTelp)
                                        .addComponent(scrollTextArea)
                                        .addGroup(panelDataTerpilihLayout.createSequentialGroup()
                                                .addGroup(panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(panelDataTerpilihLayout.createSequentialGroup()
                                                                .addComponent(cboHariLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cboBulanLahir, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDataTerpilihLayout.createSequentialGroup()
                                                                .addComponent(cboHariMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cboBulanMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cboTahunMasuk, 0, 84, Short.MAX_VALUE)
                                                        .addComponent(cboTahunLahir, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                panelDataTerpilihLayout.setVerticalGroup(
                        panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelDataTerpilihLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbID)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbTlp)
                                        .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbNama)
                                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbTglM)
                                        .addComponent(cboHariMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboBulanMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboTahunMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbt4l)
                                        .addComponent(txtT4l, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbTglL)
                                        .addComponent(cboHariLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboBulanLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboTahunLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelDataTerpilihLayout.createSequentialGroup()
                                                .addGroup(panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lbKel)
                                                        .addComponent(lbAlamat)
                                                        .addComponent(cboKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panelDataTerpilihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lbPek)
                                                        .addComponent(txtPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(scrollTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                panelTombol.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                btnExit.setText("Exit");
                btnExit.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnExitActionPerformed(evt);
                        }
                });

                btnClose.setText("Close");
                btnClose.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCloseActionPerformed(evt);
                        }
                });

                btnNew.setText("New");
                btnNew.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnNewActionPerformed(evt);
                        }
                });

                btnEdit.setText("Edit");
                btnEdit.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnEditActionPerformed(evt);
                        }
                });

                btnSave.setText("Save");
                btnSave.setEnabled(false);
                btnSave.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnSaveActionPerformed(evt);
                        }
                });

                btnDelete.setText("Delete");
                btnDelete.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnDeleteActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout panelTombolLayout = new javax.swing.GroupLayout(panelTombol);
                panelTombol.setLayout(panelTombolLayout);
                panelTombolLayout.setHorizontalGroup(
                        panelTombolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTombolLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                panelTombolLayout.setVerticalGroup(
                        panelTombolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTombolLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelTombolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnExit)
                                        .addComponent(btnClose)
                                        .addComponent(btnNew)
                                        .addComponent(btnEdit)
                                        .addComponent(btnSave)
                                        .addComponent(btnDelete))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(penelPencarian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(scrolTabelPelanggan)
                                        .addComponent(panelNavigasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelDataTerpilih, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelTombol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(penelPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scrolTabelPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelNavigasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelDataTerpilih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelTombol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
                // TODO add your handling code here:
		System.exit(0);
        }//GEN-LAST:event_btnExitActionPerformed

        private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
                // TODO add your handling code here:
		dispose();
        }//GEN-LAST:event_btnCloseActionPerformed

        private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
                // TODO add your handling code here:
		simpanDataTambah = true;
		kosongkanDataDetail();
		aturKomponen(false, true, "Cancel");
        }//GEN-LAST:event_btnNewActionPerformed

        private void btnNaviAwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNaviAwalActionPerformed
                // TODO add your handling code here:
		navigasiAwal();
        }//GEN-LAST:event_btnNaviAwalActionPerformed

        private void btnNaviKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNaviKembaliActionPerformed
                // TODO add your handling code here:
		navigasiMundur();
        }//GEN-LAST:event_btnNaviKembaliActionPerformed

        private void btnNaviMajuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNaviMajuActionPerformed
                // TODO add your handling code here:
		navigasiMaju();
        }//GEN-LAST:event_btnNaviMajuActionPerformed

        private void btnNaviAkhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNaviAkhirActionPerformed
                // TODO add your handling code here:
		navigasiAkhir();
        }//GEN-LAST:event_btnNaviAkhirActionPerformed

        private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
                // TODO add your handling code here:
		simpanDataTambah = false;
		aturKomponen(false, true, "Cancel");
		this.txtId.setEnabled(false);
        }//GEN-LAST:event_btnEditActionPerformed

        private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
                // TODO add your handling code here:
		String id = txtId.getText();
		String nama = txtNama.getText();
		String alamat = txtAlamat.getText();
		String t4lahir = txtT4l.getText();
		String tglLahir = cboHariLahir.getSelectedItem().toString()+"-"	+
			((cboBulanLahir.getSelectedIndex()+1)<=9?"0"+(cboBulanLahir.getSelectedIndex()+1)
			:(cboBulanLahir.getSelectedIndex()+1))+"-"+
			cboTahunLahir.getSelectedItem().toString();
		String kelamin = cboKelamin.getSelectedItem().toString();
		String perkerjaan = txtPekerjaan.getText();
		String telp = txtTelp.getText();
		String tglMasuk = cboHariMasuk.getSelectedItem().toString()+"-"+
			((cboBulanMasuk.getSelectedIndex()+1)<=9?"0"+(cboBulanMasuk.getSelectedIndex()+1)
			:(cboBulanMasuk.getSelectedIndex()+1))+"-"+
			cboTahunMasuk.getSelectedItem().toString();		
		if(isSimpanTambah()){
			opPelanggan.simpanData(id, nama, alamat, t4lahir, tglLahir, 
				kelamin, perkerjaan, telp, tglMasuk);
			tampilToTabel(PerintahSQL.getPerintahSelect(PerintahSQL.tabelPelanggan));
			dataDetail(getJumlahDataTabel() -1);			
		}else{
			opPelanggan.ubahData(id, nama, alamat, t4lahir, tglLahir,
				kelamin, perkerjaan, telp, tglMasuk);
			simpanDataTambah = true;
			tampilToTabel(PerintahSQL.getPerintahSelect(PerintahSQL.tabelPelanggan));
			dataDetail(0);
		}
		aturKomponen(true, false, "Delete");
        }//GEN-LAST:event_btnSaveActionPerformed

        private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
                // TODO add your handling code here:
		if(isHapusData()){
			opPelanggan.hapusData(this.txtId.getText());
			tampilToTabel(PerintahSQL.getPerintahSelect(PerintahSQL.tabelPelanggan));
			dataDetail(0);
		}else
			aturKomponen(true, false, "Delete");
        }//GEN-LAST:event_btnDeleteActionPerformed


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.ButtonGroup RadioGrupKelamin;
        private javax.swing.JButton btnCari;
        private javax.swing.JButton btnClose;
        private javax.swing.JButton btnDelete;
        private javax.swing.JButton btnEdit;
        private javax.swing.JButton btnExit;
        private javax.swing.JButton btnNaviAkhir;
        private javax.swing.JButton btnNaviAwal;
        private javax.swing.JButton btnNaviKembali;
        private javax.swing.JButton btnNaviMaju;
        private javax.swing.JButton btnNew;
        private javax.swing.JButton btnSave;
        private javax.swing.JComboBox cboBulanLahir;
        private javax.swing.JComboBox cboBulanMasuk;
        private javax.swing.JComboBox cboCari;
        private javax.swing.JComboBox cboHariLahir;
        private javax.swing.JComboBox cboHariMasuk;
        private javax.swing.JComboBox cboKelamin;
        private javax.swing.JComboBox cboTahunLahir;
        private javax.swing.JComboBox cboTahunMasuk;
        private javax.swing.JCheckBox cekCar;
        private javax.swing.JLabel lbAlamat;
        private javax.swing.JLabel lbFillter;
        private javax.swing.JLabel lbID;
        private javax.swing.JLabel lbJmlData;
        private javax.swing.JLabel lbKel;
        private javax.swing.JLabel lbNama;
        private javax.swing.JLabel lbNamaDb;
        private javax.swing.JLabel lbPek;
        private javax.swing.JLabel lbTglL;
        private javax.swing.JLabel lbTglM;
        private javax.swing.JLabel lbTlp;
        private javax.swing.JLabel lbt2;
        private javax.swing.JLabel lbt4l;
        private javax.swing.JPanel panelDataTerpilih;
        private javax.swing.JPanel panelNavigasi;
        private javax.swing.JPanel panelTombol;
        private javax.swing.JPanel penelPencarian;
        private javax.swing.JScrollPane scrolTabelPelanggan;
        private javax.swing.JScrollPane scrollTextArea;
        private javax.swing.JTable tabelPelanggan;
        private javax.swing.JTextArea txtAlamat;
        private javax.swing.JTextField txtId;
        private javax.swing.JTextField txtJmlData;
        private javax.swing.JTextField txtNama;
        private javax.swing.JTextField txtNamaDatabese;
        private javax.swing.JTextField txtParam1;
        private javax.swing.JTextField txtParam2;
        private javax.swing.JTextField txtPekerjaan;
        private javax.swing.JTextField txtT4l;
        private javax.swing.JTextField txtTelp;
        // End of variables declaration//GEN-END:variables
}
