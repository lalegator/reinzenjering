package view;

import geometrija.Modifikacija;
import geometrija.Pomeri;

import java.awt.Color;
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

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel {
	ArrayList<Oblik> oblici = new ArrayList<Oblik>();
	MainFrame _frmMain;
	Tacka pocetna;
	Tacka krajnja;
	int klik = 1;

	public DrawingPanel(MainFrame frmMain) {
		this.setBackground(Color.PINK);
		this.setBounds(112, 83, 493, 199);
		
		_frmMain = frmMain;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (_frmMain.tglbtnTacka.isSelected()) {
					Tacka t = new Tacka(e.getX(), e.getY(), _frmMain.selectedColor);
					oblici.add(t);
					klik = 1;
				} else if (_frmMain.tglbtnLinija.isSelected()) {
					if (klik == 1) {
						pocetna = new Tacka(e.getX(), e.getY());
						klik++;
					} else {
						krajnja = new Tacka(e.getX(), e.getY());
						klik = 1;
						Linija l = new Linija(pocetna, krajnja, _frmMain.selectedColor);
						oblici.add(l);
					}
				} else if (_frmMain.tglbtnKrug.isSelected()) {
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
									_frmMain.selectedColor);
							oblici.add(k);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								"Morate uneti ceo broj.", "Obavestenje",
								JOptionPane.INFORMATION_MESSAGE);
					}
					klik = 1;
				} else if (_frmMain.tglbtnKvadrat.isSelected()) {
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
									Integer.parseInt(stranica), _frmMain.selectedColor);
							oblici.add(k);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								"Morate uneti ceo broj.", "Obavestenje.",
								JOptionPane.INFORMATION_MESSAGE);
					}
					klik = 1;
				} else if (_frmMain.tglbtnPravougaonik.isSelected()) {
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
									visina, _frmMain.selectedColor);
							oblici.add(p);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								"Morate uneti ceo broj.", "Obavestenje.",
								JOptionPane.INFORMATION_MESSAGE);
					}
					klik = 1;
				} else if (_frmMain.tglbtnSelektuj.isSelected()) {
					Iterator itr = oblici.iterator();
					while (itr.hasNext()) {
						Oblik obl = (Oblik) itr.next();
						obl.setSelektovan(false);
						if (obl.contains(e.getX(), e.getY())) {
							obl.setSelektovan(true);
						}
					}
				}

				else if (_frmMain.tglbtnPopuni.isSelected()) {
					Iterator itr = oblici.iterator();
					ArrayList kliknuti = new ArrayList();
					PovrsinskiOblik pomoc = null;
					while (itr.hasNext()) {
						Oblik obl = (Oblik) itr.next();
						if (obl.contains(e.getX(), e.getY())) {
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
						pomoc.setBojaUnutrasnosti(_frmMain.selectedColor);
				}
			}
		});

		_frmMain.btnModifikacija.addMouseListener(new MouseAdapter() {
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

		_frmMain.btnPomeriZa.addMouseListener(new MouseAdapter() {
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

		_frmMain.btnObrisi.addMouseListener(new MouseAdapter() {
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

		_frmMain.btnPomeriNa.addMouseListener(new MouseAdapter() {
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

		Iterator<Oblik> it = oblici.iterator();
		while (it.hasNext()) {
			Oblik o = it.next();
			o.draw(g);
		}
		repaint();
		this.grabFocus();
	}
}
