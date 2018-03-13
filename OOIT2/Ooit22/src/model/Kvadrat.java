package model;

import java.awt.Graphics;

public class Kvadrat extends PovrsinskiOblik implements Comparable {
	protected Tacka goreLevo;
	protected int stranica;

	public Kvadrat(Tacka igoreLevo, int istranica) {
		goreLevo = igoreLevo;
		stranica = istranica;
	}

	public Kvadrat(Tacka igoreLevo, int istranica, String iboja) {
		setBoja(iboja);
		goreLevo = igoreLevo;
		stranica = istranica;
	}

	public boolean sadrzi(int x, int y) {
		if (goreLevo.getX() <= x && x <= goreLevo.getX() + stranica
				&& y >= goreLevo.getY() && y <= goreLevo.getY() + stranica)
			return true;
		else
			return false;
	}

	public void popuni(Graphics g) {
		g.setColor(pronadjiBoju(getBojaUnutrasnosti()));
		g.fillRect(goreLevo.getX() + 1, goreLevo.getY() + 1, stranica - 1,
				stranica - 1);
	}

	public void crtajSe(Graphics g) {
		int ddx = goreLevo.getX() + stranica;
		int ddy = goreLevo.getY() + stranica;
		if (stranica > 0) {
			g.setColor(pronadjiBoju(getBoja()));
			g.drawLine(goreLevo.getX(), goreLevo.getY(), ddx, goreLevo.getY());
			g.drawLine(goreLevo.getX(), goreLevo.getY(), goreLevo.getX(), ddy);
			g.drawLine(goreLevo.getX(), ddy, ddx, ddy);
			g.drawLine(ddx, goreLevo.getY(), ddx, ddy);
			if (isSelektovan()) {
				selektovan(g);
			}
			if (this.getBojaUnutrasnosti() != null)
				popuni(g);

		}
	}

	public void selektovan(Graphics g) {
		new Linija(goreLevo, new Tacka(goreLevo.getX() + stranica,
				goreLevo.getY())).selektovan(g);
		
		new Linija(goreLevo, new Tacka(goreLevo.getX(), goreLevo.getY()
				+ stranica)).selektovan(g);
		
		new Linija(new Tacka(goreLevo.getX(), goreLevo.getY() + stranica),
				new Tacka(goreLevo.getX() + stranica, goreLevo.getY()
						+ stranica)).selektovan(g);
		
		new Linija(new Tacka(goreLevo.getX() + stranica, goreLevo.getY()
				+ stranica), new Tacka(goreLevo.getX() + stranica,
				goreLevo.getY())).selektovan(g);
	}

	public int compareTo(Object obj) {
		Kvadrat drugi = (Kvadrat) obj;
		double d = stranica - drugi.getStranica();
		int c = (int) d;
		return c;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Kvadrat) {
			Kvadrat pomocni = (Kvadrat) obj;
			if (goreLevo.equals(pomocni.getGoreLevo())
					&& stranica == pomocni.getStranica())
				return true;
			else
				return false;
		}
		return false;
	}

	public Linija dijagonalaKvadrata() {
		int x = goreLevo.getX() + stranica;
		int y = goreLevo.getY() + stranica;
		Tacka doleDesno = new Tacka(x, y);
		Linija l = new Linija(goreLevo, doleDesno);
		return l;
	}

	public Tacka centralnaTacka() {
		Tacka t = dijagonalaKvadrata().sredisnjaTackaLinije();
		return t;
	}

	public String toString() {
		String s = "gornji levi ugao=" + goreLevo + ", stranica=" + stranica;
		return s;
	}

	public Tacka getGoreLevo() {
		return goreLevo;
	}

	public void setGoreLevo(Tacka goreLevo) {
		this.goreLevo = goreLevo;
	}

	public int getStranica() {
		return stranica;
	}

	public void setStranica(int stranica) {
		this.stranica = stranica;
	}

	public double obim() {
		int o = 4 * stranica;
		return o;
	}

	public double povrsina() {
		int p = stranica * stranica;
		return p;

	}

}
