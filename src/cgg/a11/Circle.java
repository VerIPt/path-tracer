package cgg.a11;

import cgtools.*;
import static cgtools.Vector.*;

import cgg.Ray;

public class Circle implements Shape {
    Point p;
    double rMin;
    double rMax;
    final Material m;

    public Circle(Point p, double rMin, double rMax, Material m) {
        this.p = p;
        this.rMin = rMin;
        this.rMax = rMax;
        this.m = m;
    }

    /**
     * Returns the minimum radius of the circle.
     *
     * @return The minimum radius of the circle.
     */
    public double getRMin() {
        return rMin;
    }

    /**
     * Returns the maximum radius of the circle.
     *
     * @return The maximum radius of the circle.
     */
    public double getRMax() {
        return rMax;
    }

    public Hit intersect(Ray ray) {
        if (ray == null)
            return null;

        // Solve the quadratic equation to find the intersection points
        double a = dotProduct(ray.d(), ray.d());
        double b = 2 * dotProduct(subtract(ray.x0(), p), ray.d());
        double c = dotProduct(subtract(ray.x0(), p), subtract(ray.x0(), p)) - rMax * rMax;
        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            // No intersection points
            return null;
        }

        // Find the closest valid intersection point
        double t = (-b - Math.sqrt(discriminant)) / (2 * a);
        if (!ray.isValid(t)) {
            // First intersection point is outside valid range, try the second one
            t = (-b + Math.sqrt(discriminant)) / (2 * a);
            if (!ray.isValid(t)) {
                // Both intersection points are outside valid range
                return null;
            }
        }

        // Calculate the intersection point
        Point x = ray.pointAt(t);

        // Check if the intersection point is inside the circle's radii
        double distanceToCenter = length(subtract(x, p));
        if (distanceToCenter < rMin || distanceToCenter > rMax) {
            // Intersection point is outside the circle's radii
            return null;
        } else {
            // Intersection is valid, return the hit result
            return new Hit(t, x, normalize(subtract(x, p)), point(x.x() / (2 * rMax) + 0.5, x.z() / (2 * rMax) + 0.5, 0), m);
        }
    }

    @Override
    public BoundingBox bounds() {
        Point minPoint = point(p.x() - rMax, p.y(), p.z() - rMax);
        Point maxPoint = point(p.x() + rMax, p.y(), p.z() + rMax);
        return new BoundingBox(minPoint, maxPoint);
    }
}
