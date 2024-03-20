package cgg.a03;


import cgg.Ray;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Sampler;

import static cgtools.Vector.*;
import java.util.ArrayList;

public record Raytracer(Camera camera, ArrayList<Sphere> spheres, Color background) implements Sampler {

    // Get the color for a given pixel (x, y)
    public Color getColor(double x, double y) {
        Hit closestHit = null;
        double closestT = Double.POSITIVE_INFINITY;

        // Iterate over all spheres and find the closest intersection
        for (Sphere sphere : spheres) {
            Ray ray = new Ray(new Point(0, 0, 0), camera.rayDirection(x, y), 0.0001, Double.POSITIVE_INFINITY);
            Hit hit = sphere.intersectRay(ray);

            // Check if there is an intersection and if it's closer than previous
            // intersections
            if (hit != null && hit.t() < closestT) {
                closestHit = hit;
                closestT = hit.t();
            }
        }

        // If there is a closest hit, shade the intersection point, otherwise return the
        // background color
        if (closestHit != null) {
            return shade(closestHit.n(), closestHit.c());
        }

        return background;
    }

    // Shade an intersection point using the Phong lighting model
    static Color shade(Direction normal, Color color) {
        Direction lightDir = normalize(direction(1, 1, 0.5));
        Color ambient = multiply(0.1, color);
        Color diffuse = multiply(0.9 * Math.max(0, dotProduct(lightDir, normal)), color);
        return add(ambient, diffuse);
    }
}
