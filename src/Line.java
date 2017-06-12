
public class Line {
	
	// Coordinates [one(x,y,z), two(x,y,z after traveling length 1)]
	public Point one, vector, two;
	
	// Constructor with two known point coordinates (a(x,y) and b(x,y))
	public Line(Point one, Point two) {
		this.one = one;
		this.two = two;
		this.vector = two.subtract(one).scalarMultiplication(1.0 / two.subtract(one).vectorLength());
	}
	
	public double vectorLength() {
		
		return Math.sqrt(Math.pow(this.two.x - this.one.x, 2) + Math.pow(this.two.y - this.one.y, 2) + Math.pow(this.two.z - this.one.z, 2));
	}
	
	public Point intersectionPoint(double distance) {
		
		double factor = distance / this.vectorLength();
		double x = factor * ( this.two.x - this.one.x );
		double y = factor * ( this.two.y - this.one.y );
		double z = factor * ( this.two.z - this.one.z );

		return new Point(x, y, z);
	}
}
