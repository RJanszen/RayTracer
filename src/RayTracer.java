import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RayTracer {

	private static void scene(Point viewPoint, Viewport viewPort, Sphere[] spheres, Point[] lightSource)
			throws IOException {

		Color[][] pixels = new Color[viewPort.size[0]][viewPort.size[1]];

		BufferedImage image = new BufferedImage(viewPort.size[0], viewPort.size[1], BufferedImage.TYPE_INT_RGB);
		Color bg = new Color(0, 0, 0);
		int backgroundColor = bg.getRGB();
		double shortestDistance, furthestDistance, tint;

		for (int x = 0; x < viewPort.size[0]; x++) {
			for (int y = 0; y < viewPort.size[1]; y++) {

				Line projectionL = new Line(viewPoint, viewPort.getPoint(x, y));
				boolean sphereFound = false;

				for (Sphere sphere : spheres) {

					Point intersecP = projectionL.intersectionPoint((sphere.intersectionDistance(projectionL)));

					if (sphere.intersectionDistance(projectionL) == 0.0)
						continue;

					shortestDistance = new Line(sphere.center, lightSource[0]).vectorLength() - sphere.radius;
					furthestDistance = Math
							.sqrt(Math.pow(shortestDistance + sphere.radius, 2) + Math.pow(sphere.radius, 2));
					Line rayOfLight = new Line(lightSource[0], intersecP);

					if (intersecP.shadow(lightSource[0], spheres, sphere) == false) {
						tint = 1 - (rayOfLight.vectorLength() - shortestDistance)
								/ (furthestDistance - shortestDistance);
					} else {
						tint = 0.5 - (rayOfLight.vectorLength() - shortestDistance)
								/ (furthestDistance - shortestDistance);
					}
					if (tint < 0.25)
						tint = 0.25;
					pixels[x][y] = new Color((int) (int) (255 * tint), (int) (255 * tint), (int) (255 * tint));
					sphereFound = true;
				}
				if (sphereFound == false)
					pixels[x][y] = new Color(backgroundColor);
			}
		}

		for (int x = 0; x < pixels.length; x++) {
			for (int y = 0; y < pixels[0].length; y++) {
				image.setRGB(x, y, pixels[x][y].getRGB());
			}
		}
		ImageIO.write(image, "png", new File("image.png"));
	}

	public static void main(String[] args) throws IOException {

		Point viewpoint = new Point(0, 0, 0);
		Viewport viewport = new Viewport(new Point(320, 240, 1000), new Point(-320, 240, 1000), new Point(320, -240, 1000),
				new Point(-320, -240, 1000));
		Sphere[] spheres = new Sphere[] { new Sphere(new Point(50, 0, 650), 10), new Sphere(new Point(0, 0, 800), 35),
				new Sphere(new Point(-55, -20, 850), 8) };
		Point[] lightsources = new Point[] { new LightSource(80, 0, 500), new LightSource(500, -100, 75) };

		scene(viewpoint, viewport, spheres, lightsources);

	}
}
