package cgg.a07;

import static cgtools.Vector.*;
import cgtools.*;
import cgg.Ray;

import java.util.*;

public class SpiralStairCase implements Shape {
    private List<Sphere> steps;
    private Cylinder pillar;

    public SpiralStairCase(Point p, double height, double rPillar, double rStairCase, Material mPillar,
            Material mSteps, Material mRailing) {

        this.steps = generateSpheres(p, height, rStairCase, mSteps);
        this.pillar = new Cylinder(rPillar, height, p, mPillar);
    }

    private List<Sphere> generateSpheres(Point p, double height, double rStairCase, Material mSteps) {
        List<Sphere> spheres = new ArrayList<Sphere>();
        double currentHeight = p.y();
        double angle = 0.0;
        double angleStep = 2 * Math.PI / (rStairCase * 20);
    
        while (currentHeight <= height) {
            double x = p.x() + rStairCase * Math.cos(angle);
            double z = p.z() + rStairCase * Math.sin(angle);
            double numSpheres = rStairCase * 2 * Math.PI * 0.5; // Anzahl der Kugeln pro Stufe
    
            for (int i = 0; i < numSpheres; i++) {
                double sphereAngle = i * (2 * Math.PI) / numSpheres;
                double xOffset = (rStairCase - 0.1) * 0.2 * Math.cos(sphereAngle); // X-Offset für gleichmäßige Verteilung
                double zOffset = (rStairCase - 0.1) * 0.2 * Math.sin(sphereAngle); // Z-Offset für gleichmäßige Verteilung
                spheres.add(new Sphere(rStairCase / 20, point(x + xOffset, currentHeight, z + zOffset), mSteps));
            }
    
            currentHeight += 0.05;
            angle += angleStep;
        }
    
        return spheres;
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
        hits.add(pillar.intersect(ray));
        for (Sphere sphere : steps) {
            Hit hit = sphere.intersect(ray);
            if (hit != null) {
                hits.add(hit);
            }
        }
        
        Hit closestHit = findClosestHit(hits);
        return closestHit;
    }
}
