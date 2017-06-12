
public class Sphere {

	Point center;
	double radius;

	public Sphere(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	// Distance to intersect ( http://paulbourke.net/geometry/circlesphere/index.html#linesphere )
	public Double intersectionDistance (Line line) {
		Point l = line.one.subtract(this.center);
		double a = line.vector.dotProduct(line.vector);
		double b = 2 * line.vector.dotProduct(l);
		double c = l.dotProduct(l) - Math.pow(this.radius, 2);
		Double[] roots = solveQuadratic(a, b, c);
		if (roots != null) {
			if (roots[0] > 0.1 && (roots[0] < roots[1] || roots[1] < 0.1)) {
				return roots[0];
			} else if (roots[1] > 0.1 && (roots[1] < roots[0] || roots[0] < 0.1)) {
				return roots[1];
			}
		}
		return 0.0;
	}
	
	// The exact behavior is determined by the expression within the square root: b * b - 4 * a * c
	private static Double[] solveQuadratic(double a, double b, double c) {
		double discriminant = (b * b) - (4 * a * c);
		Double[] roots = new Double[2];
		// No intercepts
		if (discriminant < 0) {
			return null;
		// Tangent intercept
		} else if (discriminant == 0) {
			roots[0] = roots[1] = -0.5 * b / a;
		// Two intercepts
		} else {
			double q = -0.5 * (b + (b > 0 ? 1 : -1) * Math.sqrt(discriminant));
			roots[0] = q / a;
			roots[1] = c / q;
		}
		return roots;
	}
}
