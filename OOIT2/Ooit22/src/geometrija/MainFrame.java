package geometrija;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.ButtonGroup;

import view.View;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame {

	private JFrame frmCrtanje;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	public JToggleButton tglbtnTacka = new JToggleButton("TACKA");
	public JToggleButton tglbtnLinija = new JToggleButton("LINIJA");
	public JToggleButton tglbtnKrug = new JToggleButton("KRUG");
	public JToggleButton tglbtnKvadrat = new JToggleButton("KVADRAT");
	public JToggleButton tglbtnPravougaonik = new JToggleButton("PRAVOUGAONIK");
	public JToggleButton tglbtnSelektuj = new JToggleButton("SELEKTUJ");
	public JButton btnObrisi = new JButton("OBRISI");
	public JToggleButton tglbtnPopuni = new JToggleButton("POPUNI");
	public JButton btnModifikacija = new JButton("MODIFIKACIJA");
	public JButton btnPomeriZa = new JButton("POMERI ZA");
	public JButton btnPomeriNa = new JButton("POMERI NA");
	
	public String selectedColor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmCrtanje.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrtanje = new JFrame();
		frmCrtanje.getContentPane().setBackground(Color.CYAN);
		frmCrtanje.setTitle("CRTANJE");
		frmCrtanje.setBounds(100, 100, 631, 379);
		frmCrtanje.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrtanje.getContentPane().setLayout(null);

		JToggleButton tglbtnBela = new JToggleButton("BELA");
		tglbtnBela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedColor = "bela";
			}
		});
		buttonGroup_1.add(tglbtnBela);
		tglbtnBela.setBounds(10, 70, 86, 23);
		frmCrtanje.getContentPane().add(tglbtnBela);

		JToggleButton tglbtnCrna = new JToggleButton("CRNA");
		tglbtnCrna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedColor = "crna";
			}
		});
		buttonGroup_1.add(tglbtnCrna);
		tglbtnCrna.setBounds(10, 104, 86, 23);
		frmCrtanje.getContentPane().add(tglbtnCrna);

		JToggleButton tglbtnZelena = new JToggleButton("ZELENA");
		tglbtnZelena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedColor = "zelena";
			}
		});
		buttonGroup_1.add(tglbtnZelena);
		tglbtnZelena.setBounds(10, 138, 86, 23);
		frmCrtanje.getContentPane().add(tglbtnZelena);

		JToggleButton tglbtnZuta = new JToggleButton("ZUTA");
		tglbtnZuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedColor = "zuta";
			}
		});
		buttonGroup_1.add(tglbtnZuta);
		tglbtnZuta.setBounds(10, 172, 86, 23);
		frmCrtanje.getContentPane().add(tglbtnZuta);

		JToggleButton tglbtnPlava = new JToggleButton("PLAVA");
		tglbtnPlava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedColor = "plava";
			}
		});
		buttonGroup_1.add(tglbtnPlava);
		tglbtnPlava.setBounds(10, 206, 86, 23);
		frmCrtanje.getContentPane().add(tglbtnPlava);

		JToggleButton tglbtnCrvena = new JToggleButton("CRVENA");
		tglbtnCrvena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedColor = "crvena";
			}
		});
		buttonGroup_1.add(tglbtnCrvena);
		tglbtnCrvena.setBounds(10, 240, 86, 23);
		frmCrtanje.getContentPane().add(tglbtnCrvena);

		// JToggleButton tglbtnTacka = new JToggleButton("TACKA");
		buttonGroup.add(tglbtnTacka);
		tglbtnTacka.setBounds(117, 11, 130, 23);
		frmCrtanje.getContentPane().add(tglbtnTacka);

		// JToggleButton tglbtnLinija = new JToggleButton("LINIJA");
		buttonGroup.add(tglbtnLinija);
		tglbtnLinija.setBounds(191, 45, 130, 23);
		frmCrtanje.getContentPane().add(tglbtnLinija);

		buttonGroup.add(tglbtnKrug);
		tglbtnKrug.setBounds(268, 11, 130, 23);
		frmCrtanje.getContentPane().add(tglbtnKrug);

		btnPomeriNa.setBounds(505, 307, 100, 23);

		frmCrtanje.getContentPane().add(btnPomeriNa);

		buttonGroup.add(tglbtnKvadrat);
		tglbtnKvadrat.setBounds(351, 45, 130, 23);
		frmCrtanje.getContentPane().add(tglbtnKvadrat);

		buttonGroup.add(tglbtnPravougaonik);
		tglbtnPravougaonik.setBounds(416, 11, 130, 23);
		frmCrtanje.getContentPane().add(tglbtnPravougaonik);

		// JToggleButton tglbtnSelektuj = new JToggleButton("SELEKTUJ");
		buttonGroup.add(tglbtnSelektuj);
		tglbtnSelektuj.setBounds(95, 307, 95, 23);
		frmCrtanje.getContentPane().add(tglbtnSelektuj);

		buttonGroup.add(btnModifikacija);

		btnModifikacija.setBounds(191, 307, 121, 23);
		frmCrtanje.getContentPane().add(btnModifikacija);

		View panel = new View(this);
		panel.setBackground(Color.PINK);
		panel.setBounds(112, 83, 493, 199);
		frmCrtanje.getContentPane().add(panel);
		buttonGroup.add(btnObrisi);

		btnObrisi.setBounds(314, 307, 74, 23);
		frmCrtanje.getContentPane().add(btnObrisi);

		buttonGroup.add(tglbtnPopuni);
		tglbtnPopuni.setBounds(10, 274, 86, 23);
		frmCrtanje.getContentPane().add(tglbtnPopuni);

		btnPomeriZa.setBounds(398, 307, 102, 23);
		frmCrtanje.getContentPane().add(btnPomeriZa);
	}
}
