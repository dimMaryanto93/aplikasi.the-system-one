/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thesystemone.GUI.product;

import static java.lang.Integer.parseInt;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import thesystemone.database.Connect;
import thesystemone.database.ConnectedDb;
import thesystemone.database.PerintahSQL;

/**
 *
 * @author dimas
 */
public class Product extends javax.swing.JInternalFrame {
	ConnectedDb driver;
	OperationProduct opProduct;
	private int n;
	private boolean simpanDataTambah;
	private boolean hapusData = true;
	
	/**
	 * Creates new form GuiProduct
	 */
	public Product() {
		initComponents();
		inisialisasi();
	}
	
	private void inisialisasi(){
		driver = new ConnectedDb();
		driver.panggilDriver();
		opProduct = new OperationProduct();
		kosongkanTabel();
		tampilToTabel(PerintahSQL.getPerintahSelect(PerintahSQL.tabelProduct));
		this.txtNamaDatabase.setText(Connect.DB.toUpperCase()+".SISTEM_ONE."+PerintahSQL.tabelProduct);
		
	}
	
	/**method ini diguankan untuk me-modifikasi JTable yang telah dibuat
	 * mengapa menggunakan DefaultTableModel karena pada saat membuat JTable
	 * kita tidak menbuat Object pada JTabel.
	 * 
	 * @return 
	 */
	private DefaultTableModel getTabelModel(){
		return (DefaultTableModel) tabelProduct.getModel();
	}
	
	private void setHapusData(boolean b){
		this.hapusData = b;
	}
	
	public boolean isHapusData(){
		return hapusData;
	}
	
	/**mothod ini digunakan untuk mengaktifkan/meng-nonAktifkan komponen JTextField ,JComboBox
	 * 
	 * @param b 
	 */
	private void aktifkanKomponen(boolean b){
		this.txtId.setEnabled(b);
		this.txtNama.setEnabled(b);
		this.cboType.setEnabled(b);
		this.cboManufaktur.setEnabled(b);
		this.txtQty.setEnabled(b);
		this.txtHarga.setEnabled(b);
		this.txtDetail.setEnabled(b);
	}
	/**method ini digunakan untuk mengatur komponen JButton new,edit,simpan,edit
	 * setEnabled berfungsi untuk mengaktifkan komponen 
	 * @param tambah
	 * @param simpan
	 * @param batal 
	 */
	private void aturKomponenTombol(boolean tambah,boolean simpan,String batal){
		this.btnAdd.setEnabled(tambah);
		this.btnUpdate.setEnabled(tambah);
		this.btnSave.setEnabled(simpan);
		btnDelete.setText(batal);
		setHapusData(tambah);
		this.aktifkanKomponen(simpan);
	}
	
	/**
	 * method ini digunakan untuk menampilkan data dari JTabel yang telah diseleksi ke JTextField ,JComboBox 
	 * yang ada pada panelDetail atau DataTerseleksi
	 * @param baris 
	 */
	private void dataDetail(int baris){
		this.txtId.setText(getTabelModel().getValueAt(baris, 0).toString());
		this.txtNama.setText(getTabelModel().getValueAt(baris, 1).toString());
		this.cboType.setSelectedItem(getTabelModel().getValueAt(baris, 2).toString());
		this.cboManufaktur.setSelectedItem(getTabelModel().getValueAt(baris, 3).toString());
		this.txtQty.setText(getTabelModel().getValueAt(baris, 4).toString());
		this.txtHarga.setText(getTabelModel().getValueAt(baris, 5).toString());
		this.txtDetail.setText(getTabelModel().getValueAt(baris, 6).toString());		
	}
	/**
	 * method ini diguanakan untuk mendapatkan jumlah data yang tersedia di JTabel
	 * @return 
	 */
	private int getJumlahDataTabel(){
		return getTabelModel().getRowCount()-1;
	}
	
	private boolean isSimpanTambah(){
		return simpanDataTambah;
	}
	
	/**
	 * method ini digunakan untuk menhapus data/kalimat yang ada pada JTextField,JComboBox dll
	 */
	private void kosongDataDetail(){
		this.txtId.setText("");
		this.txtNama.setText("");
		this.cboType.setSelectedIndex(0);
		this.cboManufaktur.setSelectedIndex(0);
		this.txtQty.setText("");
		this.txtHarga.setText("");
		this.txtDetail.setText("");		
	}
	
	/**
	 * method ini digunakan untuk menghapus / mengosongkan baris di JTabel
	 * namun perlu diingan ini bukan untuk menghapus data yang ada di table product
	 * data yang di database masih tetap ada di database 
	 */
	private void kosongkanTabel(){
		int k = 0;
		while (k<getTabelModel().getRowCount()) {			
			getTabelModel().removeRow(0);
			k++;
		}
	}
	/**method ini digunakan unutk navaigasi akhir / merubah data yang terseleksi menjadj ke paling terakhir
	 * 
	 */
	private void navigasiAkhir(){
		n = getTabelModel().getRowCount() - 1;
		int a = n;
		dataDetail(n);
		this.txtDataTerseleksi.setText(Integer.toString(a + 1));
	}
	
	/**
	 * method ini digunakan untuk navigasi awal / merubah data yang terseleksi menjadi ke semula atou paring pertama
	 */
	private void navigasiAwal(){
		n = 0;
		dataDetail(n);
		this.txtDataTerseleksi.setText(Integer.toString(n + 1));
	}
	/**method ini digunakan untuk navigasi maju / merubah data yang terseleksi ke bawah pada JTable
	 * 
	 */
	private void navigasiMaju(){
		++n;
		if(n> getTabelModel().getRowCount() - 1){
			n = getTabelModel().getRowCount() - 1;
			JOptionPane.showMessageDialog(null, "Finnaly Rows");
		}
		int a = n;
		dataDetail(a);
		this.txtDataTerseleksi.setText(Integer.toString(a + 1));
	}
	
	/** method ini digunakan untuk navigasi kembali/ merubah data yang terseleksi ke atas pada JTable
	 * 
	 */
	private void navigasiMundur(){
		--n;
		if(n<0){
			n = 0;
			JOptionPane.showMessageDialog(null, "First Rows");
		}
		int a = n;
		this.txtDataTerseleksi.setText(Integer.toString(a + 1));
		dataDetail(n);
	}
	
	/**
	 * method ini digunakan untuk menambahkan baris kosong pada JTable
	 */
	private void addBarisKosong(){
		getTabelModel().addRow(new Object[]{null,null,null,null,null,null,null});
	}
	
	/**method ini digunakan untuk menampilkan data yang ada di database ke JTable
	 * getTabelModel digunakan untuk memodifikasi / mendapatkan Data pada JTabel yang dibuat
	 * getTabelModel().setValueAt(Integer.parseInt()) digunakan untuk mensetting nilai pada JTabel
	 * Integer.parseInt digunakan untuk mengconfersi type data dari berbagai Typedata ke Integer
	 * 
	 * @param sql 
	 */
	private void tampilToTabel(String sql){
		/**
		 * mengisi model tabel dengan sebuah data, 
		 * dilakukan pada kelas OperationProduct
		 */
		opProduct.setDataTabel(sql);
		kosongkanTabel();
		for(int a = 0; a<opProduct.getBanyakData();a++){
			addBarisKosong();
			//munkin salah disini
			for(int b = 0;b < getTabelModel().getColumnCount();b++){
				String[] data = opProduct.getDataTabel(a);
				if(b == 4){
					getTabelModel().setValueAt(Integer.parseInt(data[b]), a, b);
				}else if(b == 5){
					getTabelModel().setValueAt(Integer.parseInt(data[b]), a, b);
				}else{
					getTabelModel().setValueAt(data[b], a, b);
				}
				
			}
			
		}
		
	}
	
	

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                scrolTabel = new javax.swing.JScrollPane();
                tabelProduct = new javax.swing.JTable();
                PanelNavi = new javax.swing.JPanel();
                ToolBarBack = new javax.swing.JToolBar();
                btnAwal = new javax.swing.JButton();
                btnKembali = new javax.swing.JButton();
                ToolBarMaju = new javax.swing.JToolBar();
                btnMaju = new javax.swing.JButton();
                btnAkhir = new javax.swing.JButton();
                txtDataTerseleksi = new javax.swing.JTextField();
                lbJumlahDt = new javax.swing.JLabel();
                jLabel1 = new javax.swing.JLabel();
                lbDatabase = new javax.swing.JLabel();
                txtNamaDatabase = new javax.swing.JTextField();
                PanelData = new javax.swing.JPanel();
                txtId = new javax.swing.JTextField();
                txtNama = new javax.swing.JTextField();
                txtQty = new javax.swing.JTextField();
                txtHarga = new javax.swing.JTextField();
                scrollDetail = new javax.swing.JScrollPane();
                txtDetail = new javax.swing.JTextArea();
                lbId = new javax.swing.JLabel();
                lbProduct = new javax.swing.JLabel();
                lbType = new javax.swing.JLabel();
                lbManufaktur = new javax.swing.JLabel();
                lbQty = new javax.swing.JLabel();
                lbHarga = new javax.swing.JLabel();
                lbDetail = new javax.swing.JLabel();
                cboType = new javax.swing.JComboBox();
                cboManufaktur = new javax.swing.JComboBox();
                PanelButton = new javax.swing.JPanel();
                btnSave = new javax.swing.JButton();
                btnExit = new javax.swing.JButton();
                btnClose = new javax.swing.JButton();
                btnAdd = new javax.swing.JButton();
                btnUpdate = new javax.swing.JButton();
                btnDelete = new javax.swing.JButton();
                PanelPencarian = new javax.swing.JPanel();
                cekCari = new javax.swing.JCheckBox();
                lbSort = new javax.swing.JLabel();
                cboKolomCari = new javax.swing.JComboBox();
                txtParam1 = new javax.swing.JTextField();
                txtParam2 = new javax.swing.JTextField();
                jButton11 = new javax.swing.JButton();

                setClosable(true);
                setMaximizable(true);
                setTitle("Data Product");

                tabelProduct.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "ID", "Nama Product", "Type Product", "Manufaktur", "QTY", "Harga", "Detail"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                tabelProduct.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
                scrolTabel.setViewportView(tabelProduct);

                PanelNavi.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                ToolBarBack.setFloatable(false);
                ToolBarBack.setRollover(true);

                btnAwal.setText("First line");
                btnAwal.setFocusable(false);
                btnAwal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnAwal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                btnAwal.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAwalActionPerformed(evt);
                        }
                });
                ToolBarBack.add(btnAwal);

                btnKembali.setText("Backward");
                btnKembali.setFocusable(false);
                btnKembali.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnKembali.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                btnKembali.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnKembaliActionPerformed(evt);
                        }
                });
                ToolBarBack.add(btnKembali);

                ToolBarMaju.setFloatable(false);
                ToolBarMaju.setRollover(true);

                btnMaju.setText("Forward");
                btnMaju.setFocusable(false);
                btnMaju.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnMaju.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                btnMaju.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnMajuActionPerformed(evt);
                        }
                });
                ToolBarMaju.add(btnMaju);

                btnAkhir.setText("Finally");
                btnAkhir.setFocusable(false);
                btnAkhir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnAkhir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                btnAkhir.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAkhirActionPerformed(evt);
                        }
                });
                ToolBarMaju.add(btnAkhir);

                txtDataTerseleksi.setEditable(false);
                txtDataTerseleksi.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
                txtDataTerseleksi.setText("Data Terseleksi");

                lbJumlahDt.setText("/ Jumlah Data");

                jLabel1.setText(":");

                lbDatabase.setText("Database.Schema.Tabel");

                txtNamaDatabase.setEditable(false);
                txtNamaDatabase.setText("Database.Schema.Table");

                javax.swing.GroupLayout PanelNaviLayout = new javax.swing.GroupLayout(PanelNavi);
                PanelNavi.setLayout(PanelNaviLayout);
                PanelNaviLayout.setHorizontalGroup(
                        PanelNaviLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelNaviLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ToolBarBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDataTerseleksi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbJumlahDt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(lbDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtNamaDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ToolBarMaju, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                PanelNaviLayout.setVerticalGroup(
                        PanelNaviLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelNaviLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(PanelNaviLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(PanelNaviLayout.createSequentialGroup()
                                                .addGroup(PanelNaviLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(txtNamaDatabase))
                                                .addGap(10, 10, 10))
                                        .addComponent(ToolBarMaju, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                        .addComponent(ToolBarBack, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(PanelNaviLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lbJumlahDt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtDataTerseleksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                );

                PanelData.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Terpilih"));

                txtId.setEnabled(false);
                txtId.addAncestorListener(new javax.swing.event.AncestorListener() {
                        public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                        }
                        public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                                txtIdAncestorAdded(evt);
                        }
                        public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                        }
                });

                txtNama.setEnabled(false);

                txtQty.setEnabled(false);

                txtHarga.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
                txtHarga.setText("Rp.0,-");
                txtHarga.setEnabled(false);

                txtDetail.setColumns(20);
                txtDetail.setRows(5);
                txtDetail.setEnabled(false);
                scrollDetail.setViewportView(txtDetail);

                lbId.setText("ID Product");

                lbProduct.setText("Nama Product");

                lbType.setText("Type Product");

                lbManufaktur.setText("Manufaktur");

                lbQty.setText("Qty");

                lbHarga.setText("Harga Jual");

                lbDetail.setText("Detail");

                cboType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Motherboard", "Proccessor", "Grafic Card", "LCD / LED", "Keyboard & Mouse", "CASSING", "Hardisk", "Asseccories" }));
                cboType.setEnabled(false);

                cboManufaktur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ASUS", "GIGABYTE", "MSI", "ASROCK", " ", " " }));
                cboManufaktur.setEnabled(false);

                javax.swing.GroupLayout PanelDataLayout = new javax.swing.GroupLayout(PanelData);
                PanelData.setLayout(PanelDataLayout);
                PanelDataLayout.setHorizontalGroup(
                        PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelDataLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(lbType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbManufaktur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtId)
                                        .addComponent(txtNama)
                                        .addComponent(cboType, 0, 190, Short.MAX_VALUE)
                                        .addComponent(cboManufaktur, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(PanelDataLayout.createSequentialGroup()
                                                .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbQty, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                PanelDataLayout.setVerticalGroup(
                        PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelDataLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbId)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbHarga)
                                        .addComponent(lbQty))
                                .addGap(18, 18, 18)
                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PanelDataLayout.createSequentialGroup()
                                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lbDetail))
                                                        .addComponent(lbProduct))
                                                .addGap(18, 18, 18)
                                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lbType)
                                                        .addComponent(cboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lbManufaktur)
                                                        .addComponent(cboManufaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(scrollDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(14, Short.MAX_VALUE))
                );

                PanelButton.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                btnSave.setText("SAVE");
                btnSave.setEnabled(false);
                btnSave.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnSaveActionPerformed(evt);
                        }
                });

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

                btnAdd.setText("NEW");
                btnAdd.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAddActionPerformed(evt);
                        }
                });

                btnUpdate.setText("EDIT");
                btnUpdate.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnUpdateActionPerformed(evt);
                        }
                });

                btnDelete.setText("DELETE");
                btnDelete.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnDeleteActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout PanelButtonLayout = new javax.swing.GroupLayout(PanelButton);
                PanelButton.setLayout(PanelButtonLayout);
                PanelButtonLayout.setHorizontalGroup(
                        PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelButtonLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                PanelButtonLayout.setVerticalGroup(
                        PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelButtonLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(PanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnExit)
                                        .addComponent(btnClose)
                                        .addComponent(btnAdd)
                                        .addComponent(btnUpdate)
                                        .addComponent(btnSave)
                                        .addComponent(btnDelete))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                PanelPencarian.setBorder(javax.swing.BorderFactory.createTitledBorder("Pencarian"));

                cekCari.setSelected(true);
                cekCari.setText("Show All");

                lbSort.setText("Sort by");

                cboKolomCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Pilih Kolom-", "ID Product", "Nama Product", "Type Product", "Harga" }));
                cboKolomCari.setEnabled(false);

                txtParam1.setEnabled(false);

                txtParam2.setEnabled(false);

                jButton11.setText("Cari");
                jButton11.setEnabled(false);

                javax.swing.GroupLayout PanelPencarianLayout = new javax.swing.GroupLayout(PanelPencarian);
                PanelPencarian.setLayout(PanelPencarianLayout);
                PanelPencarianLayout.setHorizontalGroup(
                        PanelPencarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelPencarianLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cekCari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbSort, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboKolomCari, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtParam1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtParam2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton11)
                                .addContainerGap())
                );
                PanelPencarianLayout.setVerticalGroup(
                        PanelPencarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelPencarianLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(PanelPencarianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cekCari)
                                        .addComponent(lbSort)
                                        .addComponent(cboKolomCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtParam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtParam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton11))
                                .addContainerGap(22, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scrolTabel)
                                        .addComponent(PanelNavi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(PanelData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(PanelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(PanelPencarian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(PanelPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrolTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PanelNavi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PanelData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
                // TODO add your handling code here:
		navigasiMundur();
        }//GEN-LAST:event_btnKembaliActionPerformed

        private void txtIdAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtIdAncestorAdded
                // TODO add your handling code here:
        }//GEN-LAST:event_txtIdAncestorAdded

        private void btnAwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAwalActionPerformed
                // TODO add your handling code here:
		navigasiAwal();
        }//GEN-LAST:event_btnAwalActionPerformed

        private void btnMajuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMajuActionPerformed
                // TODO add your handling code here:
		navigasiMaju();
        }//GEN-LAST:event_btnMajuActionPerformed

        private void btnAkhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAkhirActionPerformed
                // TODO add your handling code here:
		navigasiAkhir();
        }//GEN-LAST:event_btnAkhirActionPerformed

        private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
                // TODO add your handling code here:
		simpanDataTambah = true;
		kosongDataDetail();
		aturKomponenTombol(false, true, "Batal");
        }//GEN-LAST:event_btnAddActionPerformed

        private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
                // TODO add your handling code here:
		simpanDataTambah = false;
		aturKomponenTombol(false, true, "Batal");
		txtId.setEditable(false);
        }//GEN-LAST:event_btnUpdateActionPerformed

        private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
                // TODO add your handling code here:
		String id = this.txtId.getText();
			/**coding ini digunakan untuk menyimpan data yand telah
			 * di input dari keyboard ke object JTextField dengan 
			 * tujuan memudahkan. 
			 */
			String nama = this.txtNama.getText();
			String type = this.cboType.getSelectedItem().toString();
			String pabrik = this.cboManufaktur.getSelectedItem().toString();
			int qty = parseInt(this.txtQty.getText());
			int harga = parseInt(this.txtHarga.getText());
			String detail = this.txtDetail.getText();
		if(isSimpanTambah()){
			/**opProduct adalah instance object dari Class OperationProduct
			 * opProduct.simpanData berfungsi untuk menyimpan data
			 * dari variable di atas akan dikirimkan sebagai parameter 
			 * ke method simpanData di class OperationProduct 
			 */
			opProduct.simpanData(id, nama, type, pabrik, qty, harga, detail);
		}else{
			/**opProduct.ubahData berfungsi untuk mengUpdate data,
			 * dengan menggunakan parameter yang dikirimkan ke Class OperationProduct
			 */
			opProduct.ubahData(id, nama, type, pabrik, qty, harga, detail);
			simpanDataTambah = true;
			dataDetail(0);
		}
		/**menampilkan data yang talah di input/update ke JTable
		 */
		tampilToTabel(PerintahSQL.getPerintahSelect(PerintahSQL.tabelProduct));
		
		/**pemanggilan method aturKomponenTombol(true, false, "Hapus");
		 * akan menghasilkan komponen button yang ada pada penelButton
		 * seperti button new , edit , simpan , hapus
		 * yang berubah menjdi aktif adalah new , 
		 * 
		 */
		aturKomponenTombol(true, false, "Hapus");
        }//GEN-LAST:event_btnSaveActionPerformed

        private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
                // TODO add your handling code here:
		if(isHapusData()){
			opProduct.hapusData(this.txtId.getText());
			tampilToTabel(PerintahSQL.getPerintahSelect(PerintahSQL.tabelProduct));
			dataDetail(0);
		}else{
			aturKomponenTombol(true, false, "Hapus");
		}
		
        }//GEN-LAST:event_btnDeleteActionPerformed

        private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
                // TODO add your handling code here:
		System.exit(0);
        }//GEN-LAST:event_btnExitActionPerformed

        private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
                // TODO add your handling code here:
		this.dispose();
        }//GEN-LAST:event_btnCloseActionPerformed

	

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel PanelButton;
        private javax.swing.JPanel PanelData;
        private javax.swing.JPanel PanelNavi;
        private javax.swing.JPanel PanelPencarian;
        private javax.swing.JToolBar ToolBarBack;
        private javax.swing.JToolBar ToolBarMaju;
        private javax.swing.JButton btnAdd;
        private javax.swing.JButton btnAkhir;
        private javax.swing.JButton btnAwal;
        private javax.swing.JButton btnClose;
        private javax.swing.JButton btnDelete;
        private javax.swing.JButton btnExit;
        private javax.swing.JButton btnKembali;
        private javax.swing.JButton btnMaju;
        private javax.swing.JButton btnSave;
        private javax.swing.JButton btnUpdate;
        private javax.swing.JComboBox cboKolomCari;
        private javax.swing.JComboBox cboManufaktur;
        private javax.swing.JComboBox cboType;
        private javax.swing.JCheckBox cekCari;
        private javax.swing.JButton jButton11;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel lbDatabase;
        private javax.swing.JLabel lbDetail;
        private javax.swing.JLabel lbHarga;
        private javax.swing.JLabel lbId;
        private javax.swing.JLabel lbJumlahDt;
        private javax.swing.JLabel lbManufaktur;
        private javax.swing.JLabel lbProduct;
        private javax.swing.JLabel lbQty;
        private javax.swing.JLabel lbSort;
        private javax.swing.JLabel lbType;
        private javax.swing.JScrollPane scrolTabel;
        private javax.swing.JScrollPane scrollDetail;
        private javax.swing.JTable tabelProduct;
        private javax.swing.JTextField txtDataTerseleksi;
        private javax.swing.JTextArea txtDetail;
        private javax.swing.JTextField txtHarga;
        private javax.swing.JTextField txtId;
        private javax.swing.JTextField txtNama;
        private javax.swing.JTextField txtNamaDatabase;
        private javax.swing.JTextField txtParam1;
        private javax.swing.JTextField txtParam2;
        private javax.swing.JTextField txtQty;
        // End of variables declaration//GEN-END:variables
}
