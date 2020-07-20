package controller;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import structures.SBT;
import view.RandomOpt;
import view.View;

public class Controller {
	private View view;
	private RandomOpt randomDialog;
	private SBT tree;

	public Controller(View view, RandomOpt randomD) {
		this.view = view;

		view.drawing.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int x = arg0.getX();
				int y = arg0.getY();
				view.drawing.setCoords(x, y);
				view.drawing.setTree(tree);
				view.drawing.repaint();
				view.drawing.drawTags();
				view.drawing.updateUI();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		view.btn_show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				input();
				view.drawing.setCoords(view.drawing.getWidth()/2, view.drawing.getSpace());
				view.drawing.setTree(tree);
				view.drawing.drawTags();
				view.drawing.updateUI();

			}
		});

		view.btn_random.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				randomDialog.txt_nodes.setText("");
				randomDialog.txt_r1.setText("");
				randomDialog.txt_r2.setText("");
				randomDialog.setVisible(true);
			}
		});

		view.btn_export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				export();
			}
		});
		
		view.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				view.drawing.drawTags();
				view.drawing.updateUI();
				view.drawing.repaint();
			}
		});
		
		view.slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int val = view.slider.getValue();
				view.drawing.setZoom(val, val/2);
				view.repaint();
				view.drawing.drawTags();
				view.drawing.updateUI();
			}
		});
		
		this.randomDialog = randomD;
		
		randomDialog.cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				randomDialog.setVisible(false);
			}
		});
		
		randomDialog.randomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Random r = new Random();
				int nodes = r.nextInt(15);
				int r1 = 0;
				int r2 = 500;
				random(nodes, r1, r2);
				view.drawing.setCoords(view.drawing.getWidth()/2, view.drawing.getSpace());
				view.drawing.setTree(tree);
				view.drawing.drawTags();
				view.drawing.updateUI();
				
				randomDialog.setVisible(false);
			}
		});
		
		randomDialog.okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int nodes, r1, r2;
				nodes = r1 = r2 = 0;
				
				try{
					nodes = Integer.parseInt(randomDialog.txt_nodes.getText());
					r1 = Integer.parseInt(randomDialog.txt_r1.getText());
					r2 = Integer.parseInt(randomDialog.txt_r2.getText());
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Please!, only join numbers.");
				}
				
				random(nodes, r1, r2);
				view.drawing.setCoords(view.drawing.getWidth()/2, view.drawing.getSpace());
				view.drawing.setTree(tree);
				view.drawing.drawTags();
				view.drawing.updateUI();
				
				randomDialog.setVisible(false);
			}
		});
	}

	public void start() {
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
		
		randomDialog.setLocationRelativeTo(null);
	}

	private void input() {
		String input = view.txt_input.getText().trim();
		if (input.equals("")) {
			JOptionPane.showMessageDialog(null, "Join the numbers i.e (3,8,1, ...,n)");
			return;
		}

		String[] str = input.split(",");
		Integer[] numbers = new Integer[str.length];
		try {
			for (int i = 0; i < str.length; i++)
				numbers[i] = Integer.parseInt(str[i]);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Please, only join numbers");
			return;
		}

		SBT tree = new SBT();
		for (Integer i : numbers)
			tree.insert(i);

		this.tree = tree;
	}

	private void random(int nodes, int r1, int r2) {
		if(nodes > (r2-r1)) {
			JOptionPane.showMessageDialog(null, "The number of nodes cannot be greater than the difference of the ranges");
			return;
		}
	
		Random r = new Random();
		int root = r.nextInt(r2);
		int left = r.nextInt(nodes);
		int right = nodes-left;
		int cont = 0;
		
		tree.insert(root);
		while(cont < left) {
			int num = r.nextInt(root)+r1;
			if(!tree.exist(num)) {
				tree.insert(num);
				cont++;
			}
		}
		cont = 0;
		
		while(cont < right) {
			int num = r.nextInt(r2)+root;
			if(!tree.exist(num)) {
				tree.insert(num);
				cont++;
			}
		}
	}

	public static void main(String[] args) {
		View view = new View();
		RandomOpt random = new RandomOpt();
		Controller controller = new Controller(view, random);
		controller.start();
	}

	private void export() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("Select Folder");
	    chooser.setAcceptAllFileFilterUsed(false);
	    
		int w = view.drawing.getWidth();
		int h = view.drawing.getHeight();

		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();

		view.drawing.paint(g);
		g.dispose();
		
		int selection = chooser.showOpenDialog(view);
		if(selection == JFileChooser.APPROVE_OPTION) {
			String title = "";
			File outputFile;
			int count = 0;
			do {
				if(count++ > 0) {
					JOptionPane.showMessageDialog(null, "This file already exist");
				}
				title = JOptionPane.showInputDialog("Input the title of image");
				String path = chooser.getSelectedFile().getAbsolutePath() + "/" + title + ".png";
				outputFile = new File(path);
			}while(outputFile.exists());
			
			if(outputFile.isFile()) {
				
			}
			try {
				System.out.println(outputFile.getAbsolutePath());
				ImageIO.write(bi, "png", outputFile);
				JOptionPane.showMessageDialog(null, "Export Sucesfully");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "The image can't be export");
			}
		}
		
		
	}

}
