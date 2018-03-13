package geometrija;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box.Filler;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Krug;
import model.Kvadrat;
import model.Linija;
import model.Oblik;
import model.PovrsinskiOblik;
import model.Pravougaonik;
import model.Tacka;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Crtez extends JPanel {
	ArrayList oblici = new ArrayList();
	Crtanje frmCrtanje;
	Tacka pocetna;
	Tacka krajnja;
	int klik = 1;

	public Crtez(Crtanje tf) {
		frmCrtanje = tf;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * int x = e.getX(); int y = e.getY(); Tacka t = new Tacka (x,
				 * y); oblici.add(t);
				 */
				if (frmCrtanje.tglbtnTacka.isSelected()) {
					Tacka t = new Tacka(e.getX(), e.getY(), frmCrtanje.boja);
					oblici.add(t);
					klik = 1;
				} else if (frmCrtanje.tglbtnLinija.isSelected()) {
					if (klik == 1) {
						pocetna = new Tacka(e.getX(), e.getY());
						klik++;
					} else {
						krajnja = new Tacka(e.getX(), e.getY());
						klik = 1;
						Linija l = new Linija(pocetna, krajnja, frmCrtanje.boja);
						oblici.add(l);
					}
				} else if (frmCrtanje.tglbtnKrug.isSelected()) {
					try {
						Tacka t = new Tacka(e.getX(), e.getY());
						String poluprecnik = JOptionPane.showInputDialog(null,
								"Unos poluprecnika:");
						if (Integer.parseInt(poluprecnik) <= 0) {
							JOptionPane.showMessageDialog(null,
									"Morate uneti pozitivan broj.",
									"Obavestenje",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							Krug k = new Krug(Integer.parseInt(poluprecnik), t,
									frmCrtanje.boja);
							oblici.add(k);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								"Morate uneti ceo broj.", "Obavestenje",
								JOptionPane.INFORMATION_MESSAGE);
					}
					klik = 1;
				} else if (frmCrtanje.tglbtnKvadrat.isSelected()) {
					try {
						Tacka t = new Tacka(e.getX(), e.getY());
						String stranica = JOptionPane.showInputDialog(null,
								"Unos stranice kvadrata:");
						if (Integer.parseInt(stranica) <= 0) {
							JOptionPane.showMessageDialog(null,
									"Morate uneti pozitivan broj.",
									"Obavestenje.",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							Kvadrat k = new Kvadrat(t,
									Integer.parseInt(stranica), frmCrtanje.boja);
							oblici.add(k);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								"Morate uneti ceo broj.", "Obavestenje.",
								JOptionPane.INFORMATION_MESSAGE);
					}
					klik = 1;
				} else if (frmCrtanje.tglbtnPravougaonik.isSelected()) {
					try {
						Tacka t = new Tacka(e.getX(), e.getY());
						int visina = Integer.parseInt(JOptionPane
								.showInputDialog(null,
										"Unos visine pravougaonika:"));
						int sirina = Integer.parseInt(JOptionPane
								.showInputDialog(null,
										"Unos sirine pravougaonika:"));
						if (sirina <= 0 || visina <= 0) {
							JOptionPane.showMessageDialog(null,
									"Morate uneti pozitivan broj.",
									"Obavestenje.",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							Pravougaonik p = new Pravougaonik(t, sirina,
									visina, frmCrtanje.boja);
							oblici.add(p);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								"Morate uneti ceo broj.", "Obavestenje.",
								JOptionPane.INFORMATION_MESSAGE);
					}
					klik = 1;
				} else if (frmCrtanje.tglbtnSelektuj.isSelected()) {
					Iterator itr = oblici.iterator();
					while (itr.hasNext()) {
						Oblik obl = (Oblik) itr.next();
						obl.setSelektovan(false);
						if (obl.sadrzi(e.getX(), e.getY())) {
							obl.setSelektovan(true);
						}
					}
				}

				else if (frmCrtanje.tglbtnPopuni.isSelected()) {
					Iterator itr = oblici.iterator();
					ArrayList kliknuti = new ArrayList();
					PovrsinskiOblik pomoc = null;
					while (itr.hasNext()) {
						Oblik obl = (Oblik) itr.next();
						if (obl.sadrzi(e.getX(), e.getY())) {
							if (obl instanceof PovrsinskiOblik) {
								kliknuti.add(obl);
								pomoc = (PovrsinskiOblik) obl;
							}
						}
					}
					Iterator ite = kliknuti.iterator();
					while (ite.hasNext()) {
						PovrsinskiOblik obl = (PovrsinskiOblik) ite.next();
						if (pomoc.povrsina() > obl.povrsina()) {
							pomoc = obl;
						}
					}
					if (pomoc != null)
						pomoc.setBojaUnutrasnosti(frmCrtanje.boja);
					/*
					 * else JOptionPane.showMessageDialog(null, "Greska"
					 * ,"Upozorenje", 2);
					 */
				}
			}
		});

		frmCrtanje.btnModifikacija.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Iterator itr = oblici.iterator();
				while (itr.hasNext()) {
					Oblik obl = (Oblik) itr.next();
					if (obl.isSelektovan()) {
						if (obl instanceof PovrsinskiOblik) {
							if (obl instanceof Kvadrat) {
								if (obl instanceof Pravougaonik) {
									Modifikacija modif = new Modifikacija();
									modif.lblSirina.setVisible(true);
									modif.lblVisina.setVisible(true);
									modif.textField.setVisible(true);
									modif.textFieldVisina.setVisible(true);
									modif.setVisible(true);
									if (modif.stranica > 0) {
										((Pravougaonik) obl)
												.setSirina(modif.stranica);
									}
									if (modif.visina > 0) {
										((Pravougaonik) obl)
												.setVisina(modif.visina);
									}
									if (modif.boja != null) {
										obl.setBoja(modif.boja);
									}
									if (modif.bojaunutrasnjosti != null) {
										((PovrsinskiOblik) obl)
												.setBojaUnutrasnosti(modif.bojaunutrasnjosti);
									}
								} else {
									Modifikacija modif = new Modifikacija();
									modif.lblStranica.setVisible(true);
									modif.textField.setVisible(true);
									modif.setVisible(true);
									if (modif.stranica > 0) {
										((Kvadrat) obl)
												.setStranica(modif.stranica);// oblik
																				// nema
																				// stranicu
									}
									if (modif.boja != null) {
										obl.setBoja(modif.boja);// oblik ima
																// boju, pa nema
																// (kvadrat)
									}
									if (modif.bojaunutrasnjosti != null) {
										((PovrsinskiOblik) obl)
												.setBojaUnutrasnosti(modif.bojaunutrasnjosti);
									}
								}
							}

							if (obl instanceof Krug) {
								Modifikacija modif = new Modifikacija();
								modif.lblPoluprecnik.setVisible(true);
								modif.textField.setVisible(true);
								modif.setVisible(true);
								if (modif.stranica > 0) {
									((Krug) obl).setPoluprecnik(modif.stranica);
								}
								if (modif.boja != null) {
									obl.setBoja(modif.boja);
								}
								if (modif.bojaunutrasnjosti != null) {
									((PovrsinskiOblik) obl)
											.setBojaUnutrasnosti(modif.bojaunutrasnjosti);
								}
							}
						} else {
							Modifikacija modif = new Modifikacija();
							modif.lblBojaUnutrasnjosti.setVisible(false);
							modif.comboBoxBojaUnutrasnjosti.setVisible(false);
							modif.lblBoja.setBounds(42, 86, 46, 14);
							;
							modif.comboBoxBoja.setBounds(160, 86, 65, 20);
							modif.setVisible(true);
							obl.setBoja(modif.boja);
						}

					}

				}
			}

		});

		frmCrtanje.btnPomeriZa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Pomeri po = new Pomeri();
				Iterator itr = oblici.iterator();
				while (itr.hasNext()) {
					Oblik obl = (Oblik) itr.next();
					po.korX = 0;
					po.korY = 0;
					if (obl.isSelektovan()) {
						po.setVisible(true);
						if (obl instanceof Tacka) {
							((Tacka) obl).pomeriZa(po.korX, po.korY);
						}
						if (obl instanceof Linija) {
							((Linija) obl).pomeriZa(po.korX, po.korY);
						}
						if (obl instanceof Krug) {
							((Krug) obl).getCentar().pomeriZa(po.korX, po.korY);
						}
						if (obl instanceof Kvadrat) {
							((Kvadrat) obl).getGoreLevo().pomeriZa(po.korX,
									po.korY);
						}
						if (obl instanceof Pravougaonik) {
							((Pravougaonik) obl).getGoreLevo().pomeriZa(
									po.korX, po.korY);
						}

					}

				}
			}
		});

		frmCrtanje.btnObrisi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Iterator itr = oblici.iterator();
				while (itr.hasNext()) {
					Oblik o = (Oblik) itr.next();
					if (o.isSelektovan())
						itr.remove();
				}
			}
		});

		frmCrtanje.btnPomeriNa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Pomeri po = new Pomeri();
				po.setTitle("POMERI NA");
				Iterator itr = oblici.iterator();
				while (itr.hasNext()) {
					Oblik obl = (Oblik) itr.next();
					if (obl.isSelektovan()) {
						po.setVisible(true);
						if (po.korX > -1 && po.korY > -1) {
							if (obl instanceof Tacka) {
								((Tacka) obl).pomeriNa(po.korX, po.korY);
							}
							if (obl instanceof Linija) {
								((Linija) obl).pomeriNa(po.korX, po.korY);
							}
							if (obl instanceof Krug) {
								((Krug) obl).getCentar().pomeriNa(po.korX,
										po.korY);
							}
							if (obl instanceof Kvadrat) {
								((Kvadrat) obl).getGoreLevo().pomeriNa(po.korX,
										po.korY);
							}
							if (obl instanceof Pravougaonik) {
								((Pravougaonik) obl).getGoreLevo().pomeriNa(
										po.korX, po.korY);
							}
						}
					}
				}

			}
		});

	}

	public void paint(Graphics g) {
		super.paint(g);

		Iterator it = oblici.iterator();
		while (it.hasNext()) {
			Oblik o = (Oblik) it.next();
			o.crtajSe(g);
		}
		repaint();
		this.grabFocus();
		/*
		 * Tacka t1 = new Tacka(50, 100, "plava"); t1.crtajSe(g); Tacka t2 = new
		 * Tacka(200, 400, "plava"); Linija l1 = new Linija(t1, t2, "crvena");
		 * l1.crtajSe(g); Kvadrat kv1 = new Kvadrat(t2, 300, "zelena");
		 * kv1.crtajSe(g); kv1.centralnaTacka().crtajSe(g); Krug k1 = new Krug
		 * (100,kv1.centralnaTacka(), "plava"); k1.crtajSe(g); Kvadrat kv2 = new
		 * Kvadrat(new Tacka(500, 150),(int) l1.duzina()/3 ,t1.getBoja());
		 * kv2.crtajSe(g); kv2.centralnaTacka().crtajSe(g);
		 * kv2.dijagonalaKvadrata().crtajSe(g); Krug k2 = new Krug((int)
		 * kv2.dijagonalaKvadrata().duzina()/2, kv2.centralnaTacka(), "zuta");
		 * k2.crtajSe(g); Krug k3 = new Krug((int) kv2.getStranica()/2,
		 * kv2.centralnaTacka(), "bela"); k3.crtajSe(g); Pravougaonik pr1 = new
		 * Pravougaonik(new Tacka(600, 500), 200, 100, "zelena");
		 * pr1.crtajSe(g); Krug k4 = new Krug((int)
		 * pr1.dijagonalaPravougaonika().duzina()/2, pr1.centralnaTacka(),
		 * l1.getBoja()); k4.crtajSe(g);
		 * 
		 * Tacka t1 = new Tacka(100, 200, "crvena"); t1.setSelektovan(true);
		 * t1.crtajSe(g);
		 * 
		 * Tacka t2 = new Tacka(700, 200); Tacka t3 = new Tacka(100, 50);
		 * 
		 * Linija l1= new Linija(t2, t3, "zelena"); l1.setSelektovan(true);
		 * l1.crtajSe(g);
		 * 
		 * Kvadrat kv1 = new Kvadrat (new Tacka (100, 500), 100);
		 * kv1.setSelektovan(true); //kv1.crtajSe(g);
		 * 
		 * Pravougaonik p1 = new Pravougaonik(new Tacka(600,500), 200, 100,
		 * "crvena"); p1.setSelektovan(true); p1.crtajSe(g);
		 * 
		 * Krug k4 = new Krug (180, p1.centralnaTacka(), "zuta" );
		 * k4.setSelektovan(true); k4.crtajSe(g);
		 * 
		 * kv1.setBoja("zelena"); kv1.setSelektovan(false);
		 * 
		 * kv1.setBoja("plava"); kv1.setBojaUnutrasnosti("zelena");
		 * kv1.popuni(g); kv1.crtajSe(g);
		 * 
		 * k4.setBoja("zelena"); k4.setBojaUnutrasnosti("crvena"); k4.popuni(g);
		 * k4.crtajSe(g);
		 */

	}

	/*
	 * public static void main(String[] args) { JFrame prozor = new JFrame();
	 * prozor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); Crtez c = new
	 * Crtez(); prozor.getContentPane().add(c); prozor.setSize(1024, 768);
	 * prozor.setVisible(true); }
	 */
}
