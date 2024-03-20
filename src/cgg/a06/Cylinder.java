package cgg.a06;

import cgtools.*;
import static cgtools.Vector.*;

import cgg.Ray;


public record Cylinder(double radius, double height, Point center, Material m) implements Shape {
    public Hit intersect(Ray ray) {
        if (ray == null) {
            return null;
        }
        Direction dir = ray.d();
        Point origin = ray.x0();
        double tmin = ray.tmin();
        double tmax = ray.tmax();
        // Direction x0c = subtract(x0, center);

        // the following three parts are the midnight formula for t1,t2
        // Quelle: Übung und
        // https://de.wikipedia.org/wiki/Zylinder_(Geometrie)#Analytische_Beschreibung
        
        // Calculate coefficients for the quadratic equation

        double a = dir.x() * dir.x() + dir.z() * dir.z();
        double b = 2.0 * (dir.x() * (origin.x() - center.x()) + dir.z() * (origin.z() - center.z()));
        double c = (origin.x() - center.x()) * (origin.x() - center.x()) +
                (origin.z() - center.z()) * (origin.z() - center.z()) - radius * radius;

        double discriminant = b * b - 4.0 * a * c;

        if (discriminant >= 0.0) {
            double t1 = (-b - Math.sqrt(discriminant)) / (2.0 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2.0 * a);

            double t;
            Direction normal;

            if (t1 > tmin && t1 < tmax) {
                double y = origin.y() + t1 * dir.y();
                if (y >= center.y() && y <= center.y() + height) {
                    t = t1;
                    double nx = (origin.x() + t1 * dir.x() - center.x()) / radius;
                    double nz = (origin.z() + t1 * dir.z() - center.z()) / radius;
                    normal = normalize(new Direction(nx, 0.0, nz));
                    return new Hit(t, origin, normal, m);
                }
            }

            if (t2 > tmin && t2 < tmax) {
                double y = origin.y() + t2 * dir.y();
                if (y >= center.y() && y <= center.y() + height) {
                    t = t2;
                    double nx = (origin.x() + t2 * dir.x() - center.x()) / radius;
                    double nz = (origin.z() + t2 * dir.z() - center.z()) / radius;
                    normal = normalize(new Direction(nx, 0.0, nz));
                    return new Hit(t, origin, normal, m);
                }
            }

            double tCap = (center.y() + height - origin.y()) / dir.y();
            if (tCap > tmin && tCap < tmax) {
                double x = origin.x() + tCap * dir.x();
                double z = origin.z() + tCap * dir.z();
                if (Math.pow(x - center.x(), 2) + Math.pow(z - center.z(), 2) <= radius * radius) {
                    t = tCap;
                    normal = normalize(new Direction(0.0, 1.0, 0.0));
                    return new Hit(t, origin, normal, m);
                }
            }
        }
        return null;
    }
}
