package cgg.a07;

import cgtools.*;
import cgg.Ray;
import java.util.ArrayList;
import java.util.List;
import static cgtools.Vector.*;

public class HalfCircle implements Shape {
    private List<Cylinder> pillars;

    public HalfCircle(Point center, double pillarRadius, double height, int numPillars, double circleRadius, Emission material) {
        this.pillars = generatePillars(center, pillarRadius, height, numPillars, circleRadius, material);
    }

    private List<Cylinder> generatePillars(Point center, double pillarRadius, double height, int numPillars, double circleRadius, Emission material) {
        List<Cylinder> pillars = new ArrayList<>();
        double angleStep = Math.PI / (numPillars - 1);
        var materialColor = material.getColor();
        for (int i = 0; i < numPillars; i++) {
            double angle = i * angleStep;
            double x = center.x() - circleRadius * Math.cos(angle);
            double z = center.z() - circleRadius * Math.sin(angle);
            pillars.add(new Cylinder(pillarRadius, height, point(x, center.y(), z), material));
            height = height - 1;
            material = new Emission((color(materialColor.r(), materialColor.g(), materialColor.b())));
            materialColor = ((color(materialColor.r(), materialColor.g()-0.1, materialColor.b())));
        }

        return pillars;
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

        for (Cylinder pillar : pillars) {
            Hit hit = pillar.intersect(ray);
            if (hit != null) {
                hits.add(hit);
            }
        }

        if (hits.isEmpty()) {
            return null;
        }

        Hit closestHit = findClosestHit(hits);
        return closestHit;
    }
}
