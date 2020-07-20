package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RandomOpt extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField txt_nodes, txt_r1, txt_r2;
	public JButton cancelButton, randomButton, okButton;

	/**
	 * Create the dialog.
	 */
	public RandomOpt() {
		initComp();
	}

	private void initComp() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println(e);
		}

		setBounds(100, 100, 259, 255);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel title = new JLabel("Parameters");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.black);
		title.setBounds(0, 12, this.getWidth(), 30);
		contentPanel.add(title);

		JLabel lblNodes = new JLabel("Nodes #:");
		lblNodes.setBounds(10, 54, 70, 15);
		lblNodes.setForeground(Color.black);
		contentPanel.add(lblNodes);

		txt_nodes = new JTextField();
		txt_nodes.setBounds(85, 50, 114, 26);
		contentPanel.add(txt_nodes);
		txt_nodes.setColumns(10);

		JLabel lbllimit1 = new JLabel("Range:");
		lbllimit1.setForeground(Color.black);
		lbllimit1.setBounds(30, 86, 50, 47);
		contentPanel.add(lbllimit1);

		txt_r1 = new JTextField();
		txt_r1.setBounds(85, 98, 39, 26);
		contentPanel.add(txt_r1);
		txt_r1.setColumns(10);

		JLabel lblTo = new JLabel("To");
		lblTo.setForeground(Color.black);
		lblTo.setBounds(127, 96, 50, 26);
		contentPanel.add(lblTo);

		txt_r2 = new JTextField();
		txt_r2.setColumns(10);
		txt_r2.setBounds(160, 95, 39, 26);
		contentPanel.add(txt_r2);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		randomButton = new JButton("Random");
		buttonPane.add(randomButton);

		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}
}
