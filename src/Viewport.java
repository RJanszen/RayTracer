
public class Viewport {

	Point one, two, three, four;
	int[] size = new int[2];

	public Viewport(Point one, Point two, Point three, Point four) {
		this.one = one;
		this.two = two;
		this.three = three;
		this.four = four;
		this.size[0] = (int) Math.max(Math.max(one.x, two.x), Math.max(three.x, four.x)) - 
				(int) Math.min(Math.min(one.x, two.x), Math.min(three.x, four.x));
		this.size[1] = (int) Math.max(Math.max(one.y, two.y), Math.max(three.y, four.y)) - 
				(int) Math.min(Math.min(one.y, two.y), Math.min(three.y, four.y));
	}

	// Fetch coordinate of a viewport location (x,y)
	public Point getPoint(int portX, int portY) {
		
		double[] point = new double[3];
		
		point[0] = (int) Math.min(Math.min(one.x, two.x), Math.min(three.x, four.x)) + portX;
		point[1] = (int) Math.min(Math.min(one.y, two.y), Math.min(three.y, four.y)) + portY;
		point[2] = one.z;
		
		return new Point(point[0], point[1], point[2]);
	}

}
