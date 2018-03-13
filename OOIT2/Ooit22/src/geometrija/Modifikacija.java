package geometrija;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;

public class Modifikacija extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField textField;
	public JTextField textFieldVisina;
	public JLabel lblVisina = new JLabel("VISINA");
	public JLabel lblPoluprecnik = new JLabel("POLUPRECNIK ");
	public JLabel lblSirina = new JLabel("SIRINA");
	public JLabel lblStranica = new JLabel("STRANICA");
	public String[] boje = { "", "BELA", "CRNA", "ZELENA", "ZUTA", "PLAVA", "CRVENA" };
	public int stranica;
	public int visina;
	public String boja;
	public String bojaunutrasnjosti;
	public JComboBox comboBoxBoja = new JComboBox(boje);
	public JComboBox comboBoxBojaUnutrasnjosti = new JComboBox(boje);
	public JLabel lblBojaUnutrasnjosti = new JLabel("BOJA UNUTRASNJOSTI");
	public JLabel lblBoja = new JLabel("BOJA");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Modifikacija dialog = new Modifikacija();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Modifikacija() {
		setModal(true);
		setTitle("MODIFIKACIJA");
		setBounds(100, 100, 306, 277);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.PINK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblStranica.setVisible(false);
		lblStranica.setBounds(42, 43, 68, 14);
		contentPanel.add(lblStranica);

		textField = new JTextField();
		textField.setVisible(false);
		textField.setBounds(150, 40, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		textFieldVisina = new JTextField();
		textFieldVisina.setVisible(false);
		textFieldVisina.setBounds(150, 71, 86, 20);
		contentPanel.add(textFieldVisina);
		textFieldVisina.setColumns(10);

		lblVisina.setVisible(false);
		lblVisina.setBounds(42, 74, 46, 14);
		contentPanel.add(lblVisina);

		lblPoluprecnik.setVisible(false);
		lblPoluprecnik.setBounds(23, 43, 87, 14);
		contentPanel.add(lblPoluprecnik);

		lblSirina.setVisible(false);
		lblSirina.setBounds(42, 43, 46, 14);
		contentPanel.add(lblSirina);

		lblBoja.setBounds(23, 138, 46, 14);
		contentPanel.add(lblBoja);

		lblBojaUnutrasnjosti.setBounds(23, 163, 140, 14);
		contentPanel.add(lblBojaUnutrasnjosti);

		comboBoxBoja.setBounds(173, 135, 65, 20);
		contentPanel.add(comboBoxBoja);

		comboBoxBojaUnutrasnjosti.setBounds(173, 160, 65, 20);
		contentPanel.add(comboBoxBojaUnutrasnjosti);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							stranica = Integer.parseInt(textField.getText());
							if (stranica < 0)
								JOptionPane.showMessageDialog(null,
										"Morate uneti ceo pozitivan broj.",
										"Obavestenje", 3);
						} catch (Exception ex) {
						}
						if (textFieldVisina.isVisible()) {
							try {
								visina = Integer.parseInt(textFieldVisina
										.getText());
								if (visina < 0)
									JOptionPane.showMessageDialog(null,
											"Morate uneti pozitivan broj.",
											"Obavestenje", 3);
							} catch (Exception ex) {
							}
						}
						if (comboBoxBoja.getSelectedItem() != "")
							boja = (String) comboBoxBoja.getSelectedItem();
						if (comboBoxBojaUnutrasnjosti.getSelectedItem() != "")
							bojaunutrasnjosti = (String) comboBoxBojaUnutrasnjosti
									.getSelectedItem();
						dispose();// gasi se prozor
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
