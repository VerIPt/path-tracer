package cgg.a08;

import cgtools.*;
import static cgtools.Vector.*;

import cgg.Ray;

public class Plane implements Shape {
    Point p;
    Direction n;
    double r;
    final Material m;

    public Plane(Point p, Direction n, double r, Material m) {
        this.p = p;
        this.n = n;
        this.r = r;
        this.m = m;
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
        if (ray == null)
            return null;
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
            return new Hit(t, x, n, m);
        }
    }

    @Override
    public BoundingBox bounds() {
        return new BoundingBox(
            point(p.x() - r, p.y(), p.z() - r),
            point(p.x() + r, p.y(), p.z() + r));
    }
}
