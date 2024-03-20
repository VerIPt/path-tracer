package cgg.Rendering_competition;

import cgtools.*;
import static cgtools.Vector.*;

import cgg.Ray;

public record Sphere(double radius, Point center, Material m) implements Shape {

    public Hit intersect(Ray ray) {
        if (ray == null) {
            return null;
        }
        Direction d = ray.d();
        Point x0 = ray.x0();
        
        Direction x0c = subtract(x0, center);

        // the following three parts are the midnight formula for t1,t2
        // https://de.wikipedia.org/wiki/Quadratische_Gleichung
        // Calculate coefficients for the quadratic equation
        double a = dotProduct(d, d);
        double b = 2 * dotProduct(d, x0c);
        double c = dotProduct(x0c, x0c) - Math.pow(radius, 2);

        // Calculate the discriminant
        double discriminant = Math.pow(b, 2) - 4 * a * c;

        // Check if the ray intersects with the sphere
        if (discriminant < 0) {
            return null;
        }

        // Calculate the solutions for t
        double t0 = (-b - Math.sqrt(discriminant)) / (2 * a);
        double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);

        if (ray.isValid(t0)) {
            Point x = ray.pointAt(t0);
            Direction n = normalize(subtract(x, center));
            var inclination = Math.acos(n.y());
            var azimuth = Math.PI + Math.atan2(n.x(), n.z());
            var u = azimuth / (2 * Math.PI);
            var v = inclination / Math.PI;
            return new Hit(t0, x, n, point(u, v, 0), m);

        } else if (ray.isValid(t1)) {
            Point x = ray.pointAt(t1);
            Direction n = normalize(subtract(x, center));
            var inclination = Math.acos(n.y());
            var azimuth = Math.PI + Math.atan2(n.x(), n.z());
            var u = azimuth / (2 * Math.PI);
            var v = inclination / Math.PI;
            return new Hit(t1, x, n, point(u, v, 0), m);
        }
        return null;
    }

    @Override
    public BoundingBox bounds() {
        return new BoundingBox(
                point(center.x() - radius, center.y() - radius, center.z() - radius),
                point(center.x() + radius, center.y() + radius, center.z() + radius));
    }

}
