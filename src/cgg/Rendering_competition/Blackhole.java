package cgg.Rendering_competition;

import cgg.*;
import cgtools.*;
import static cgtools.Vector.*;
import java.util.*;

public class Blackhole implements Shape {
    private Sphere sphere;
    private List<Plane> planes;
    Point center;
    double planeRadius;

    public Blackhole(Point center, double radius, double planeRadius, Material sphereColor, Material planeColor) {
        this.sphere = new Sphere(radius, center, sphereColor);
        this.planes = generatePlanes(center, radius, planeRadius, planeColor);
        this.center = center;
        this.planeRadius = planeRadius;
    }

    private List<Plane> generatePlanes(Point center, double radius, double planeRadius, Material planeColor) {
        List<Plane> generatedPlanes = new ArrayList<>();

        // Generate small planes
        double colorFactor = 0;
        double currentRadius = planeRadius;
        while (currentRadius >= radius) {

            Point planeCenter = new Point(center.x(), center.y(), center.z());
            var planeColorVariation = new Emission(new Color(planeColor.getEmmision().r(),
                    planeColor.getEmmision().g() + colorFactor, planeColor.getEmmision().b()));

            Plane plane = new Plane(planeCenter, zAxis, currentRadius, planeColorVariation);
            generatedPlanes.add(plane);

            // Radius of the plane is reduced by a random value
            currentRadius = currentRadius - Math.random() * 0.5;
            
            
            // for event horizon of the black hole
            if (currentRadius < radius) {
                planeCenter = new Point(center.x(), center.y(), center.z());
                plane = new Plane(planeCenter, zAxis, radius + 0.2, new Emission(white));
                generatedPlanes.add(plane);

                break;
            }
            colorFactor = colorFactor + 0.04;

        }

        // Generate large planes
        currentRadius = planeRadius * 1.7;
        colorFactor = 0;
        while (currentRadius >= radius) {
            Point planeCenter = new Point(center.x(), center.y() + 0.01, center.z());
            var planeColorVariation = new Emission(new Color(planeColor.getEmmision().r(),
                    planeColor.getEmmision().g() + colorFactor, planeColor.getEmmision().b()));

            // Use the color factor for color variation
            Plane plane = new Plane(planeCenter, new Direction(0.2, 1, 0.1), currentRadius, planeColorVariation);
            generatedPlanes.add(plane);
            currentRadius = currentRadius - Math.random();
            
            // für ereignishorizont am schwarzen loch
            if (currentRadius < radius) {
                planeCenter = new Point(center.x(), center.y() + 0.01, center.z());
                plane = new Plane(planeCenter, new Direction(0.2, 1, 0.1), radius + 0.2, new Emission(white));
                generatedPlanes.add(plane);
                break;
            }
            colorFactor = colorFactor + 0.02;

        }

        return generatedPlanes;
    }

    public Hit findClosestHit(List<Hit> hits) {
        if (hits.isEmpty()) {
            return null;
        }

        Hit closestHit = hits.get(0);
        for (int i = 1; i < hits.size(); i++) {
            Hit currentHit = hits.get(i);
            closestHit = Hit.closest(closestHit, currentHit);
        }

        return closestHit;
    }

    public Hit intersect(Ray ray) {
        List<Hit> hits = new ArrayList<>();
        hits.add(sphere.intersect(ray));
        for (Plane plane : planes) {
            Hit hit = plane.intersect(ray);
            if (hit != null) {
                hits.add(hit);
            }
        }

        Hit closestHit = findClosestHit(hits);
        return closestHit;
    }

    @Override
    public BoundingBox bounds() {
        return new BoundingBox(
                point(center.x() - planeRadius, center.y(), center.z() - planeRadius),
                point(center.x() + planeRadius, center.y(), center.z() + planeRadius));
    }
}
