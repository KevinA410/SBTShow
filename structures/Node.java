package structures;

import java.awt.Point;

public class Node {
	private SBT tree;
	private Integer level;
	private Point coords;
	
	public Node(SBT tree, Point coords, Integer level) {
		this.tree = tree;
		this.coords = coords;
		this.level = level;
	}
	
	public SBT getTree() {
		return this.tree;
	}
	
	public Integer getLevel() {
		return this.level;
	}
	
	public Point getCoords() {
		return this.coords;
	}
}
