package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import structures.Node;
import structures.Pair;
import structures.SBT;

@SuppressWarnings("serial")
public class Draw extends JPanel{
	private SBT tree;
	//min -> 20 || max -> 100
	private int width = 40;
	private int space = 20;
	private int fontSize = 20;
	int x, y;
	private Font font;
	
	public Draw() {
		tree = null;
		this.font = new Font("Helvetica", Font.PLAIN, fontSize);
	}
	
	public void setTree(SBT tree) {
		this.tree = tree;
	}
	
	public SBT getTree() {
		return tree;
	}
	
	public int getFontSize() {
		return this.fontSize;
	}
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getSpace() {
		return this.space;
	}
	
	public void setZoom(int width, int fontSize) {
		this.width = width;
		this.font = new Font("Helvetica", Font.PLAIN, fontSize);
	}
	
	private ArrayList<Point> getPoints() {
		if(tree == null) return null;

		ArrayList<Point> tags = new ArrayList<Point>();
		
		int maxNodes = (int) Math.pow(2, tree.depth());
		int maxWidth = width * maxNodes;
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(tree.getRoot(), new Point(x, y), 1));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			SBT t = node.getTree();
			Point p = node.getCoords();
			maxNodes = (int) Math.pow(2, node.getLevel());
			
			tags.add(p);
			
			int y = p.y + width + space;
			if(t.getLeft() != null) {
				int x = p.x - (maxWidth/maxNodes);
				queue.add(new Node(t.getLeft(), new Point(x, y), node.getLevel()+1));
			}
			
			if(t.getRight() != null) {
				int x = p.x + (maxWidth/maxNodes);
				queue.add(new Node(t.getRight(), new Point(x , y), node.getLevel()+1));
			}
		}
		
		//x = getWidth()/2;
		//y = space;
		return tags;
	}
	
	private ArrayList<String> getTags() {
		this.removeAll();
		
		if(tree == null) return null;

		ArrayList<String> tags = new ArrayList<String>();
		
		Queue<SBT> queue = new LinkedList<>();
		queue.add(tree);
		
		while(!queue.isEmpty()) {
			SBT t = queue.poll();
			tags.add(String.valueOf(t.getNum()));
			
			if(t.getLeft() != null) queue.add(t.getLeft());
			if(t.getRight() != null) queue.add(t.getRight());
		}
		return tags;
	}
	
	public void drawTags() {
		if(tree == null) return;
		
		ArrayList<Point> points = getPoints();
		ArrayList<String> tags = getTags();
		
		while(!points.isEmpty()) {
			Point p = points.remove(0);
			String tag = tags.remove(0);
			
			JLabel lbl = new JLabel(tag, SwingConstants.CENTER);
			lbl.setBackground(null);
			lbl.setFont(font);
			lbl.setForeground(Color.black);
			this.add(lbl);
			lbl.setBounds(p.x, p.y, width, width);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		if(tree == null) return;
		
		ArrayList<Point> points = getPoints();
		Queue<Pair<SBT, Point>> queue = new LinkedList<>();
		queue.add(new Pair<SBT, Point>(tree, points.remove(0)));
		
		while(!queue.isEmpty()) {
			Pair<SBT, Point> current = queue.poll();
			SBT t = current.arg0;
			Point p = current.arg1;
			
			g.setColor(Color.BLACK);
			g.drawOval(p.x, p.y, width, width);
			
			if(t.getLeft() != null) {
				Point son = points.remove(0);
				g.drawLine(p.x, p.y + width, son.x + (width/2), son.y);
				queue.add(new Pair<SBT, Point>(t.getLeft(), son));
			}
			
			if(t.getRight() != null) {
				Point son = points.remove(0);
				g.drawLine(p.x + width, p.y + width, son.x + (width/2), son.y);
				queue.add(new Pair<SBT, Point>(t.getRight(), son));
			}
		}

	}
}
