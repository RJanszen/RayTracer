
public class Point {

	double x, y, z;

	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean shadow(Point lightsource, Sphere[] spheres, Sphere sphere) {
		
		Line projection = new Line (this, lightsource);
		double sphereFinder;
		int sphereCounter = 0;
		
		for (Sphere i : spheres) {
			sphereFinder = i.intersectionDistance(projection);
			if (sphereFinder > 0) sphereCounter++;
		}
		
		if (sphereCounter < 1) return false;
		return true;
	}

	// linear algebra : https://www.khanacademy.org/math/linear-algebra/vectors-and-spaces/dot-cross-products/v/vector-dot-product-and-vector-length
	Point subtract(Point point) {
		return new Point(this.x - point.x, this.y - point.y, this.z - point.z);
	}

	Point addition(Point point) {
		return new Point(this.x + point.x, this.y + point.y, this.z + point.z);
	}

	Point scalarMultiplication(double factor) {
		return new Point(this.x * factor, this.y * factor, this.z * factor);
	}

	double dotProduct(Point point) {
		return this.x * point.x + this.y * point.y + this.z * point.z;
	}

	double vectorLength() {
		return Math.sqrt(this.dotProduct(this));
	}

}
