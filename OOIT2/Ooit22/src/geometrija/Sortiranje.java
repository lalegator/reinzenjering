package geometrija;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Sortiranje {

	private JFrame frmSortiranje;
	private JTextField textFieldXP;
	private JTextField textFieldYP;
	private JTextField textFieldXK;
	private JTextField textFieldYK;
	DefaultListModel dlm = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sortiranje window = new Sortiranje();
					window.frmSortiranje.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sortiranje() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSortiranje = new JFrame();
		frmSortiranje.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmSortiranje.setTitle("Sortiranje");
		frmSortiranje.setBounds(100, 100, 450, 300);
		frmSortiranje.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSortiranje.getContentPane().setLayout(null);

		JLabel lblXPocetne = new JLabel("X pocetne");
		lblXPocetne.setBounds(24, 24, 67, 14);
		frmSortiranje.getContentPane().add(lblXPocetne);

		textFieldXP = new JTextField();
		textFieldXP.setBounds(85, 21, 86, 20);
		frmSortiranje.getContentPane().add(textFieldXP);
		textFieldXP.setColumns(10);

		JLabel lblYPocetne = new JLabel("Y pocetne");
		lblYPocetne.setBounds(24, 61, 66, 14);
		frmSortiranje.getContentPane().add(lblYPocetne);

		textFieldYP = new JTextField();
		textFieldYP.setBounds(85, 58, 86, 20);
		frmSortiranje.getContentPane().add(textFieldYP);
		textFieldYP.setColumns(10);

		JLabel lblXKrajnje = new JLabel("X krajnje");
		lblXKrajnje.setBounds(227, 24, 57, 14);
		frmSortiranje.getContentPane().add(lblXKrajnje);

		textFieldXK = new JTextField();
		textFieldXK.setBounds(294, 21, 86, 20);
		frmSortiranje.getContentPane().add(textFieldXK);
		textFieldXK.setColumns(10);

		JLabel lblYKrajnje = new JLabel("Y krajnje");
		lblYKrajnje.setBounds(227, 61, 67, 14);
		frmSortiranje.getContentPane().add(lblYKrajnje);

		textFieldYK = new JTextField();
		textFieldYK.setBounds(294, 58, 86, 20);
		frmSortiranje.getContentPane().add(textFieldYK);
		textFieldYK.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 103, 165, 133);
		frmSortiranje.getContentPane().add(scrollPane);

		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setBackground(Color.PINK);
		list.setForeground(Color.BLACK);
		list.setModel(dlm);

		JButton btnPostavi = new JButton("POSTAVI");
		btnPostavi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int prvi = Integer.parseInt(textFieldXP.getText());
					int drugi = Integer.parseInt(textFieldXK.getText());
					int treci = Integer.parseInt(textFieldYP.getText());
					int cetvrti = Integer.parseInt(textFieldYK.getText());
					if (prvi<0 || drugi<0 || treci<0 || cetvrti <0){
						JOptionPane.showMessageDialog(null, "Morate uneti pozitivan broj.","Obavestenje", JOptionPane.INFORMATION_MESSAGE);
					} 
					else {
						Linija l = new Linija(new Tacka(prvi, drugi), new Tacka(treci, cetvrti));
						dlm.addElement(l);
						textFieldXP.setText(null);
						textFieldXK.setText(null);
						textFieldYP.setText(null);
						textFieldYK.setText(null);
					}
				}
				catch (Exception ex){
					JOptionPane.showMessageDialog(null, "Morate uneti ceo pozitivan broj.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		btnPostavi.setBounds(272, 124, 106, 23);
		frmSortiranje.getContentPane().add(btnPostavi);

		JButton btnSortiraj = new JButton("SORTIRAJ");
		btnSortiraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object niz [] =  dlm.toArray();
				Arrays.sort(niz);
				dlm.removeAllElements();
				for (int i=0; i<niz.length; i++){
					dlm.addElement(niz[i]);

				}


			}
		});
		btnSortiraj.setBounds(272, 177, 106, 23);
		frmSortiranje.getContentPane().add(btnSortiraj);

	}
}
