/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesystemone.database;

/**
 *
 * @author dimas
 */
public class PerintahSQL {
/**
 * declaration variable + inisialisais
 */
	final static public String database = "system_one",
		schema = "system_one",
		tabelLogin = "login",
		tabelProduct = "Product",
		tabelPelanggan="pelanggan";
		

	/**
	 * declaration variable
	 */
	final static public String[] kolomLogin = {"ID", "PASS", "HAK"},
		kolomProduct = {"ID","NAMA","TYPE","PABRIK","QTY","HARGA","DETAIL"},
		kolomPelanggan = {"ID","NAMA","ALAMAT","T4LAHIR","TGLLAHIR","KELAMIN","PEKERJAAN","TELP","TGLMASUK"};

	/**inisialisasi & set TABEL Login
	 * ID =  varchar(25) not null primary key
	 * pass = varchar(25)
	 * hak = Integer
	 */
	
	/**inisialisasi & set TABEL Product
	 * ID = varchar(5) not null primary key
	 * nama = varchar(25)
	 * type = varchar(15)
	 * pabrik = varchar(25)
	 * Qty = Integer
	 * Harga = Integer
	 * Detail = varchar(50)
	 */
	
	/**inisialisasi & set TABEL Pelanggan
	 *ID = VARCHAR(5) NOT NULL
	 * NAMA = VARCHAR(30)
	 * ALAMAT = VARCHAR(30)
	 * T4LAHIR = VARCHAR(20)
	 * TGLLAHIR = VARCHAR(10)
	 * KELAMIN = VARCHAR(10)
	 * PEKERJAAN = VARCHAR(15)
	 * TELP = VARCHAR(15)
	 * TGLMASUK = VARCHAR(10)
	 */
	//End declaration & inisisalisasi
	
	/**
	 * method ini akan menghasilkan perintah SQL insert
	 * var tabel = product , jumlahKolom = 7;
	 * 
	 * @param table
	 * @param jumlahKolom
	 * @return insert into product value(?,?,?,?,?,?,?)
	 * value(?,?,?,?,?,?,?) artinya jumlah column yang tersedia di databese 
	 */
	
	public static String getPerintahInsert(String table, int jumlahKolom) {
		String nilai = "?";
		for (int n = 1; n < jumlahKolom; n++) {
			nilai = nilai.concat(",?");
		}
		return "INSERT INTO " + table + " values(" + nilai + ")";
	}
	
	/**
	 * method ini akan menghasilkan perintah sql Delete
	 * @param tabel product
	 * @param kolomKey id
	 * @param param asm01
	 * @return delete from product where id = 'asm01'
	 */
	public static String
		getPerintahHapus(String tabel, String kolomKey, String param) {
		return "DELETE FROM " + tabel + " WHERE " + kolomKey + " = '" + param + "'";
	}

		/**
		 * method ini akan menghasilkan printah sql select
		 * @param tabel product
		 * @return select * from product
		 */
	public static String getPerintahSelect(String tabel) {
		return "SELECT * FROM " + tabel;
	}

	/**method ini akan menghasilakan perintah select dengan parameter
	 * 
	 * @param tabel product
	 * @param kolomKey id
	 * @param param asm01
	 * @return select * from product where id like 'asm01'
	 */
	public static String
		getPerintahSelect(String tabel, String kolomKey, String param) {
		return "SELECT * FROM " + tabel + " where " + kolomKey + " LIKE '" + param + "'";
	}

		/**method ini akan menghasilkan peritah select dengan kolom dan parameter
		 * 
		 * @param tabel product
		 * @param kolom id,nama,type,pabrik,qty,harga,detail
		 * @param param harga
		 * @param paramInt 100000
		 * @return select * from product where id and name = 100000
		 */
	public static String
		getPerintahSelect(String tabel, String[] kolom, String[] param, int[] paramInt) {
		String klausa = kolom[0] + "='" + param[0] + "'";
		int b = 1;
		for (int a = 1; a < kolom.length; a++) {
			if (kolom.length >= 3) {
				if (b < param.length) {
					klausa = klausa.concat(" and " + kolom[a] + " = '" + param[b] + "'");
					b++;
				} else {
					b = 0;
					klausa = klausa.concat(" and " + kolom[a] + " = " + param[b]);
				}
			} else {
				klausa = klausa.concat(" and " + kolom[1] + " = " + param[1]);
				klausa = klausa.concat(" and " + kolom[2] + " = " + paramInt[0]);
			}
		}
		String statement = getPerintahSelect(tabel) + " where ".concat(klausa);
		return statement;
	}

		/**method ini akan menghasilakan perintah select dengan kolom dan 
		 * 
		 * @param tabel product
		 * @param kolom product
		 * @param idxKolomKey 3
		 * @param param motherboard
		 * @return select product from product where type = motherboard
		 */
	public static String getPerintahSelect(String tabel, String[] kolom, int idxKolomKey, String param) {
		String col = kolom[0];
		for (int b = 1; b < kolom.length; b++) {
			col = col.concat("," + kolom[b]);
		}
		return "SELECT " + col + " from " + tabel + " where " + kolom[idxKolomKey] + " = '" + param + "'";
	}
	
	/**method ini akan menghasilkan perintah select dengan parameter integer
	 * 
	 * @param tabel product
	 * @param kolomKey harga
	 * @param paramKecil 1000000
	 * @param paramBesar 9000000
	 * @return select * from product where harga >= 100000 and harga <= 9000000
	 */
	public static String getPerintahSelect(String tabel, String kolomKey, int paramKecil, int paramBesar) {
		return "SELECT * FROM " + tabel + " where " + kolomKey + " >= " + paramKecil + " and " + kolomKey + " <= " + paramBesar;
	}

	/**method ini akan menghasikan perintah update
	 * 
	 * @param tabel product
	 * @param kolom product
	 * @param nilaiKunci asm01
	 * @return update product set (=?,=?,=?,=?,=?,=?,=?) where id = 'asm01' 
	 */
	public static String getPerintahUpdate(String tabel, String[] kolom, String nilaiKunci) {
		String input = kolom[1];
		for (int a = 2; a < kolom.length; a++) {
			input = input.concat("=?," + kolom[a]);
		}
		String sqlUbah = "update " + tabel + " set " + input.concat("=?") + " where " + kolom[0] + " = '" + nilaiKunci + "'";
		return sqlUbah;
	}

}
