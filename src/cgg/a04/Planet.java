package cgg.a04;

import cgg.*;
import cgtools.*;
import static cgtools.Vector.*;

public class Planet implements Shape {
    private Sphere sphere;
    private Plane plane1;
    private Plane plane2;

    public Planet(Point center, double radius, double planeRadius, Color sphereColor, Color planeColor) {
        // Create a sphere with the specified center, radius, and color
        this.sphere = new Sphere(radius, center, sphereColor);

        // Calculate the center points for the two planes
        Point planeCenter1 = new Point(center.x(), center.y() + 0.01, center.z());
        Point planeCenter2 = new Point(center.x(), center.y(), center.z());

        // Create two planes with the calculated center points, orientations, and colors
        this.plane1 = new Plane(planeCenter1, yAxis, planeRadius, planeColor);
        this.plane2 = new Plane(planeCenter2, nyAxis, planeRadius, planeColor);
    }

    public Hit intersect(Ray ray) {
        // Intersect the ray with the sphere, plane1, and plane2
        Hit hitSphere = sphere.intersect(ray);
        Hit hitPlane1 = plane1.intersect(ray);
        Hit hitPlane2 = plane2.intersect(ray);

        // Find the closest hit among the sphere, plane1, and plane2
        Hit closestHit = Hit.closest(hitSphere, Hit.closest(hitPlane1, hitPlane2));
        return closestHit;
    }
}
