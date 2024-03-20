package cgg.a03;

import cgtools.*;
import static cgtools.Vector.*;

import cgg.Ray;



public class Sphere{

    private double radius;
    private Color color;
    private Point center;

    public Sphere(double radius, Point center, Color color) {
        this.radius = radius;
        this.center = center;
        this.color = color;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    public Hit intersectRay(Ray r) {
        Direction d = r.d();
        Point x0 = r.x0();
        double tmin = r.tmin();
        double tmax = r.tmax();
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
        Point x = r.pointAt(t);
        Direction n = normalize(subtract(x, center));

        // Create a hit object to represent the intersection
        Hit hit = new Hit(t, x, n, color);
        return hit;
    }
    
    // Comparator for sortable spheres based on their radius
    //public int compareTo(Sphere compSphere) {
    //    if (this.radius > compSphere.getRadius()) {
    //        return 1;
    //    } else if (this.radius < compSphere.getRadius()) {
    //        return -1;
    //    } else {
    //        return 0;
    //    }
    //}
}
