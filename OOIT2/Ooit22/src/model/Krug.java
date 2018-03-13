package model;

import java.awt.Graphics;

public class Krug extends PovrsinskiOblik implements Comparable {
	private int poluprecnik;
	private Tacka centar;

	public Krug(int ipoluprecnik, Tacka icentar) {
		poluprecnik = ipoluprecnik;
		centar = icentar;
	}

	public Krug(int ipoluprecnik, Tacka icentar, String iboja) {
		setBoja(iboja);
		poluprecnik = ipoluprecnik;
		centar = icentar;
	}

	public void popuni(Graphics g) {
		g.setColor(pronadjiBoju(getBojaUnutrasnosti()));
		g.fillOval(centar.getX() - poluprecnik, centar.getY() - poluprecnik,
				2 * poluprecnik, 2 * poluprecnik);
	}

	public boolean contains(int x, int y) {
		Tacka pomoc = new Tacka(x, y);
		if (pomoc.udaljenost(centar) <= poluprecnik)
			return true;
		else
			return false;
	}

	// preklapanje metode crtaj se
	public void draw(Graphics g) {
		g.setColor(pronadjiBoju(getBoja()));
		g.drawOval(centar.getX() - poluprecnik, centar.getY() - poluprecnik,
				2 * poluprecnik, 2 * poluprecnik);
		if (isSelektovan()) {
			selektovan(g);
		}
		if (this.getBojaUnutrasnosti() != null)
			popuni(g);
	}

	public void selektovan(Graphics g) {
		new Linija(new Tacka(centar.getX() - poluprecnik, centar.getY()),
				new Tacka(centar.getX() + poluprecnik, centar.getY()))
				.selektovan(g);
		new Linija(new Tacka(centar.getX(), centar.getY() - poluprecnik),
				new Tacka(centar.getX(), centar.getY() + poluprecnik))
				.selektovan(g);
	}

	public int compareTo(Object obj) {
		Krug k = (Krug) obj;
		int c = (int) this.poluprecnik - k.poluprecnik;
		return c;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Krug) {
			Krug pomocni = (Krug) obj;
			if (centar.equals(pomocni.getCentar())
					&& poluprecnik == pomocni.getPoluprecnik())
				return true;
			else
				return false;
		}
		return false;
	}

	public String toString() {
		String s = "centar = " + centar + ", Poluprecnik = " + poluprecnik;
		return s;
	}

	public int getPoluprecnik() {
		return poluprecnik;
	}

	public void setPoluprecnik(int poluprecnik) {
		this.poluprecnik = poluprecnik;
	}

	public Tacka getCentar() {
		return centar;
	}

	public void setCentar(Tacka centar) {
		this.centar = centar;
	}

	public double povrsina() {
		double p = poluprecnik * poluprecnik * Math.PI;
		return p;
	}

	public double obim() {
		double o = 2 * poluprecnik * Math.PI;
		return o;
	}

}
