package geometrija;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pomeri extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField textField;
	public JTextField textField_1;
	public int korX = -1;
	public int korY = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Pomeri dialog = new Pomeri();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Pomeri() {
		setModal(true);
		setTitle("POMERI ZA");
		setBounds(100, 100, 325, 300);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.PINK);
		contentPanel.setBounds(0, 0, 309, 229);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblX = new JLabel("X :");
			lblX.setBounds(43, 50, 19, 14);
			contentPanel.add(lblX);
		}

		JLabel lblY = new JLabel("Y :");
		lblY.setBounds(43, 92, 46, 14);
		contentPanel.add(lblY);

		textField = new JTextField();
		textField.setBounds(79, 47, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(79, 89, 86, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.GRAY);
			buttonPane.setBounds(0, 229, 309, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean grX = false;
						boolean grY = false;
						try {
							korX = Integer.parseInt(textField.getText());
						} catch (Exception ex) {
							grX = true;
							textField.setText(null);
						}
						try {
							korY = Integer.parseInt(textField_1.getText());
						} catch (Exception ex) {
							grY = true;
							textField_1.setText(null);
						}
						if (!grX && !grY)
							dispose();
						else
							JOptionPane.showMessageDialog(null,
									"Morate uneti broj.", "Obavestenje",
									JOptionPane.INFORMATION_MESSAGE);
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
