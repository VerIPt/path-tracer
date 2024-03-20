package cgg.a04;
import static cgtools.Vector.*;
import cgg.Ray;
import java.util.ArrayList;
import java.util.List;
import cgtools.*;

public class Stars implements Shape {
    private List<Sphere> stars;

    // Constructor that initializes the Stars object with a specified number of stars
    public Stars(int numStars) {
        stars = generateStars(numStars);
    }

    // Getter method to retrieve the list of stars
    public List<Sphere> getStars() {
        return stars;
    }

    @Override
    // Implementation of the intersect method from the Shape interface
    public Hit intersect(Ray ray) {
        Hit closestHit = null;
        double tMin = Double.POSITIVE_INFINITY;

        // Iterate over each star and find the closest intersection with the ray
        for (Sphere star : stars) {
            Hit hit = star.intersect(ray);
            if (hit != null && hit.t() < tMin) {
                closestHit = hit;
                tMin = hit.t();
            }
        }

        return closestHit;
    }

    private List<Sphere> generateStars(int numStars) {
        List<Sphere> stars = new ArrayList<>();

        // Generate random stars with random positions, radii, and colors
        for (int i = 0; i < numStars; i++) {
            Point center = generateRandomPoint();
            double radius = Math.random() * 0.01 + 0.009;
            Color color = generateRandomColor();
            stars.add(new Sphere(radius, center, color));
        }

        return stars;
    }

    private Point generateRandomPoint() {
        // Generate a random point within a certain range
        double x = Math.random() * 50 - 25;
        double y = Math.random() * 24 - 12;
        double z = -50;
        return point(x, y, z);
    }

    private Color generateRandomColor() {
        // Generate a random color between white and bluish-white or white and reddish-white
        double r = Math.random() * 0.1 + 0.9; // Random red component between 0.9 and 1.0
        double g = Math.random() * 0.1 + 0.9; // Random green component between 0.9 and 1.0
        double b = Math.random() * 0.1 + 0.9; // Random blue component between 0.9 and 1.0
        return new Color(r, g, b);
    }
}
