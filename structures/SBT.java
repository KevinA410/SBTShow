package structures;

public class SBT {
	private Integer num;
	private SBT left, right;
	
	public SBT() {
		num = null;
		left = right = null;
	}

	public void insert(Integer arg) {
		if(arg == null) return;
		
		if(this.num == null) {
			this.num = arg;
		}else if(arg < this.num) {
			if(this.left == null) this.left = new SBT();
			left.insert(arg);
		}else if(arg > this.num) {
			if(this.right == null) this.right = new SBT();
			right.insert(arg);
		}
	}
	
	public int depth() {
		return depth(this)-1;
	}
	
	private int depth(SBT root) {
		if(root == null) return 0;
		
		int d1 = depth(root.left) + 1;
		int d2 = depth(root.right) + 1;
			
		return (d1 >= d2) ? d1 : d2;
	}
	
	public boolean exist(Integer num) {
		if(this.num == null) return false;
		
		if(this.num == num) return true;
		else if(this.num < num) return this.left.exist(num);
		else return this.right.exist(num);
	}
	
	public SBT getRoot() {
		return this;
	}

	public SBT getLeft() {
		return this.left;
	}

	public SBT getRight() {
		return this.right;
	}
	
	public Integer getNum() {
		return this.num;
	}
}
