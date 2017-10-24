/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thesystemone;

import java.awt.Dimension;
import java.awt.Toolkit;
import thesystemone.GUI.MainMenu;

/**
 *
 * @author dimas
 */
public class TheSystemOne {
	
	private Dimension layarUtama;

	public TheSystemOne() {
		MainMenu utama = new MainMenu();
		utama.setSize(getLayarUtama().width,getLayarUtama().height);
		utama.setVisible(true);
	}

	public Dimension getLayarUtama() {
		layarUtama = Toolkit.getDefaultToolkit().getScreenSize();
		return layarUtama;
	}	

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		new TheSystemOne();
		
	}
	
}
