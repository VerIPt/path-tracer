package cgg.a07;

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
        double tmin = ray.tmin();
        double tmax = ray.tmax();
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
        double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);

        // Determine the valid intersection point
        double t;
        if (t1 < t2) {
            t = t1;
        } else {
            t = t2;
        }

        // Check if the intersection point is within the valid range
        if (t < tmin || t > tmax) {
            return null;
        }

        // Calculate the intersection point and normal
        Point x = ray.pointAt(t);
        Direction n = normalize(subtract(x, center));

        // Create a hit object to represent the intersection
        Hit hit = new Hit(t, x, n, m);
        return hit;
    }

    
}
