package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import graphics.Draw;
import graphics.JRoundTextField;
import javax.swing.JSlider;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class View extends JFrame {
	private final int width = 800, height = 600;
	private JPanel contentPane = (JPanel) this.getContentPane();
	public Draw drawing;
	public JRoundTextField txt_input;
	public JButton btn_show;
	public JButton btn_random;
	public JButton btn_export;
	public JSlider slider;
	
	private JPanel header;
	private JPanel title;
	private JPanel controllers;
	private JPanel buttons;
	private JPanel panel;
	private JPanel aplication;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	

	public View() {
		initialize();
	}
	
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
			System.out.println(e);
		}
		
		this.setSize(width, height);
		this.setTitle("SBTShow: Show and Export your Binary Tree V 1.1");
		this.setMinimumSize(new Dimension(777, 600));
		
		/*
		 * PANELS
		 */
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		header = new JPanel();
		header.setOpaque(true);
		header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
		contentPane.add(header, BorderLayout.NORTH);
		
		title = new JPanel();
		header.add(title);
		
		controllers = new JPanel();
		header.add(controllers);
		controllers.setLayout(new BorderLayout(0, 0));
		
		buttons = new JPanel();
		controllers.add(buttons, BorderLayout.EAST);
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel = new JPanel();
		controllers.add(panel, BorderLayout.CENTER);
		
		aplication = new JPanel();
		aplication.setLayout(new BorderLayout(0, 0));
		getContentPane().add(aplication, BorderLayout.CENTER);
		
		/*
		 * TITLE
		 */
		JLabel lbl_title = new JLabel("SBTShow");
		lbl_title.setForeground(new Color(219, 225, 222));
		lbl_title.setFont(new Font("Dyuthi", Font.BOLD, 20));
		title.add(lbl_title);
		
		/**
		 * INPUT
		 */
		JLabel lbl_info = new JLabel("  Numbers: ");
		lbl_info.setForeground(new Color(219, 225, 222));
		lbl_info.setFont(new Font("Dyuthi", Font.BOLD, 18));
		controllers.add(lbl_info, BorderLayout.WEST);
		panel.setLayout(null);
		
		txt_input = new JRoundTextField();
		txt_input.setBackground(new Color(219, 225, 222));
		panel.add(txt_input);
		txt_input.setColumns(10);
		
		/*
		 * BUTTONS
		 */
		Image img;
		int btn_size = 40;
		int icon_size = btn_size - 15;
		
		btn_show = new JButton("");
		btn_show.setBorder(null);
		btn_show.setPreferredSize(new Dimension(btn_size, btn_size));
		img = new ImageIcon(this.getClass().getResource("/icons/draw.png")).getImage();
		btn_show.setIcon(new ImageIcon(img.getScaledInstance(icon_size, icon_size, Image.SCALE_SMOOTH)));
		buttons.add(btn_show);
		
		btn_random = new JButton("");
		btn_random.setBorder(null);
		btn_random.setPreferredSize(new Dimension(btn_size, btn_size));
		img = new ImageIcon(this.getClass().getResource("/icons/random.png")).getImage();
		
		JLabel sp3 = new JLabel("  ");
		buttons.add(sp3);
		btn_random.setIcon(new ImageIcon(img.getScaledInstance(icon_size, icon_size, Image.SCALE_SMOOTH)));
		buttons.add(btn_random);
		
		btn_export = new JButton("");
		btn_export.setBorder(null);
		btn_export.setPreferredSize(new Dimension(btn_size, btn_size));
		img = new ImageIcon(this.getClass().getResource("/icons/export.png")).getImage();
		
		JLabel sp4 = new JLabel("  ");
		buttons.add(sp4);
		
		btn_export.setIcon(new ImageIcon(img.getScaledInstance(icon_size, icon_size, Image.SCALE_SMOOTH)));
		buttons.add(btn_export);
		
		JLabel sp5 = new JLabel(" ");
		buttons.add(sp5);
		
		/*
		 * DRAWING AREA
		 */
		drawing = new Draw();
		aplication.add(drawing, BorderLayout.CENTER);
		drawing.setBackground(Color.WHITE);
		drawing.setLayout(null);
		
		slider = new JSlider(JSlider.HORIZONTAL, 20, 100, 60);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(5);
		aplication.add(slider, BorderLayout.SOUTH);
		
		lblNewLabel = new JLabel("   ");
		aplication.add(lblNewLabel, BorderLayout.WEST);
		
		lblNewLabel_1 = new JLabel("   ");
		aplication.add(lblNewLabel_1, BorderLayout.EAST);
		
		/*
		 * SPACES
		 */
		JLabel sp = new JLabel(" ");
		controllers.add(sp, BorderLayout.SOUTH);
		
		JLabel sp2 = new JLabel(" ");
		controllers.add(sp2, BorderLayout.NORTH);
		
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				txt_input.setBounds(0, 12, (int) (getWidth() * 0.65), 30);
				contentPane.updateUI();
			}
		});
		
		setColorTheme(new Color(45, 46, 51));
	}
	
	private void setColorTheme(Color color) {
		contentPane.setBackground(color);
		header.setBackground(color);
		title.setBackground(color);
		controllers.setBackground(color);
		buttons.setBackground(color);
		panel.setBackground(color);
		aplication.setBackground(color);
		btn_show.setBackground(color);
		btn_random.setBackground(color);
		btn_export.setBackground(color);
		slider.setBackground(color);
	}
}
