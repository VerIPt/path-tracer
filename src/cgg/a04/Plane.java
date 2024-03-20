package cgg.a04;

import cgtools.*;
import static cgtools.Vector.*;


import cgg.Ray;

public class Plane implements Shape {
    Point p;
    Direction n;
    double r;
    Color c;

    public Plane(Point p, Direction n, double r, Color c) {
        this.p = p;
        this.n = n;
        this.r = r;
        this.c = c;
    }

    /**
     * Returns the radius of the plane.
     *
     * @return The radius of the plane.
     */
    public double getR() {
        return r;
    }

    public Hit intersect(Ray ray) {
        double nd = dotProduct(ray.d(), n);
        if (nd == 0.0) {
            // Ray is parallel to the plane, no intersection
            return null;
        }

        // Calculate the parameter t for the intersection point
        double t = dotProduct(subtract(p, ray.x0()), n) / nd;
        if (!ray.isValid(t)) {
            // Invalid t value, intersection is outside valid range
            return null;
        }

        // Calculate the intersection point
        Point x = ray.pointAt(t);

        if (length(subtract(x, p)) > r) {
            // Intersection point is outside the plane's radius
            return null;
        } else {
            // Intersection is valid, return the hit result
            return new Hit(t, x, n, c);
        }
    }
}
