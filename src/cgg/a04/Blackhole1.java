package cgg.a04;

import cgg.*;
import cgtools.*;
import static cgtools.Vector.*;
import java.util.*;

public class Blackhole1 implements Shape {
        private Sphere sphere;
        private Plane plane1;
        private Plane plane1_2;
        private Plane plane1_3;
        private Plane plane1_4;
        private Plane plane1_5;
        private Plane plane1_6;
        private Plane plane1_7;
        private Plane plane1_8;
        private Plane plane1_9;
        private Plane plane1_10;
        private Plane plane2;
        private Plane plane2_2;
        private Plane plane2_3;
        private Plane plane2_4;
        private Plane plane2_5;
        private Plane plane2_6;
        private Plane plane2_7;
        private Plane plane2_8;
        private Plane plane2_9;
        private Plane plane2_10;

        public Blackhole1(Point center, double radius, double planeRadius, Color sphereColor, Color planeColor) {
                // Initialize the sphere for the blackhole
                this.sphere = new Sphere(radius, center, sphereColor);

                Point planeCenter1 = new Point(center.x(), center.y(), center.z());

                // Initialize all the planes that are vertically aligned
                // hard coded because the radiants are not the same and colors do
                // not change linearly
                
                this.plane1 = new Plane(planeCenter1, zAxis, planeRadius, planeColor);
                this.plane1_2 = new Plane(new Point(planeCenter1.x(), planeCenter1.y(), planeCenter1.z() + 0.1), zAxis,
                                planeRadius - 0.15, new Color(planeColor.r(), planeColor.g() + 0.1, planeColor.b()));
                this.plane1_3 = new Plane(new Point(planeCenter1.x(), planeCenter1.y(), planeCenter1.z() + 0.2), zAxis,
                                planeRadius - 0.4, new Color(planeColor.r(), planeColor.g() + 0.2, planeColor.b()));
                this.plane1_4 = new Plane(new Point(planeCenter1.x(), planeCenter1.y(), planeCenter1.z() + 0.3), zAxis,
                                planeRadius - 0.9, new Color(planeColor.r(), planeColor.g() + 0.3, planeColor.b()));
                this.plane1_5 = new Plane(new Point(planeCenter1.x(), planeCenter1.y(), planeCenter1.z() + 0.4), zAxis,
                                planeRadius - 1.2, new Color(planeColor.r(), planeColor.g() + 0.4, planeColor.b()));
                this.plane1_6 = new Plane(new Point(planeCenter1.x(), planeCenter1.y(), planeCenter1.z() + 0.5), zAxis,
                                planeRadius - 1.3, new Color(planeColor.r(), planeColor.g() + 0.5, planeColor.b()));
                this.plane1_7 = new Plane(new Point(planeCenter1.x(), planeCenter1.y(), planeCenter1.z() + 0.6), zAxis,
                                planeRadius - 1.8, new Color(planeColor.r(), planeColor.g() + 0.9, planeColor.b()));
                this.plane1_8 = new Plane(new Point(planeCenter1.x(), planeCenter1.y(), planeCenter1.z() + 0.7), zAxis,
                                planeRadius - 2, white);
                this.plane1_9 = new Plane(new Point(planeCenter1.x(), planeCenter1.y(), planeCenter1.z() + 0.15), zAxis,
                                planeRadius - 0.35, black);
                this.plane1_10 = new Plane(new Point(planeCenter1.x(), planeCenter1.y(), planeCenter1.z() + 0.05),
                                zAxis,
                                planeRadius - 0.1, black);

                // Initialize all the planes that are horizontally aligned
                // hard coded because the radiants are not the same and colors do
                // not change linearly
                Direction planeDirection = new Direction(0.2, 1, 0.1);
                this.plane2 = new Plane(planeCenter1, planeDirection, planeRadius * 2, planeColor);
                this.plane2_2 = new Plane(new Point(planeCenter1.x(), planeCenter1.y() + 0.01, planeCenter1.z()),
                                planeDirection,
                                planeRadius * 2 - 0.5, new Color(planeColor.r(), planeColor.g() + 0.1, planeColor.b()));
                this.plane2_3 = new Plane(new Point(planeCenter1.x(), planeCenter1.y() + 0.02, planeCenter1.z()),
                                planeDirection,
                                planeRadius * 2 - 1, new Color(planeColor.r(), planeColor.g() + 0.2, planeColor.b()));
                this.plane2_4 = new Plane(new Point(planeCenter1.x(), planeCenter1.y() + 0.03, planeCenter1.z()),
                                planeDirection,
                                planeRadius * 2 - 1.3, new Color(planeColor.r(), planeColor.g() + 0.3, planeColor.b()));
                this.plane2_5 = new Plane(new Point(planeCenter1.x(), planeCenter1.y() + 0.04, planeCenter1.z()),
                                planeDirection,
                                planeRadius * 2 - 2, new Color(planeColor.r(), planeColor.g() + 0.4, planeColor.b()));
                this.plane2_6 = new Plane(new Point(planeCenter1.x(), planeCenter1.y() + 0.05, planeCenter1.z()),
                                planeDirection,
                                planeRadius * 2 - 2.5, new Color(planeColor.r(), planeColor.g() + 0.5, planeColor.b()));
                this.plane2_7 = new Plane(new Point(planeCenter1.x(), planeCenter1.y() + 0.06, planeCenter1.z()),
                                planeDirection,
                                planeRadius * 2 - 2.7, new Color(planeColor.r(), planeColor.g() + 0.4, planeColor.b()));
                this.plane2_8 = new Plane(new Point(planeCenter1.x(), planeCenter1.y() + 0.07, planeCenter1.z()),
                                planeDirection,
                                planeRadius * 2 - 2.8,
                                new Color(planeColor.r(), planeColor.g() + 0.55, planeColor.b()));
                this.plane2_9 = new Plane(new Point(planeCenter1.x(), planeCenter1.y() + 0.08, planeCenter1.z()),
                                planeDirection,
                                planeRadius - 5, new Color(planeColor.r(), planeColor.g() + 0.5, planeColor.b()));
                this.plane2_10 = new Plane(new Point(planeCenter1.x(), planeCenter1.y() + 0.09, planeCenter1.z()),
                                planeDirection,
                                planeRadius - 1.8, white);
        }

        // Iterates through list of hits and returns the closest hit
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

        // Returns the closest hit of all the objects in the scene
        public Hit intersect(Ray ray) {
                List<Hit> hits = new ArrayList<>();
                hits.add(sphere.intersect(ray));
                hits.add(plane1.intersect(ray));
                hits.add(plane2.intersect(ray));
                hits.add(plane1_3.intersect(ray));
                hits.add(plane1_4.intersect(ray));
                hits.add(plane1_5.intersect(ray));
                hits.add(plane1_2.intersect(ray));
                hits.add(plane1_6.intersect(ray));
                hits.add(plane1.intersect(ray));
                hits.add(plane2_3.intersect(ray));
                hits.add(plane2_4.intersect(ray));
                hits.add(plane2_5.intersect(ray));
                hits.add(plane2_2.intersect(ray));
                hits.add(plane2_6.intersect(ray));
                hits.add(plane1_7.intersect(ray));
                hits.add(plane1_8.intersect(ray));
                hits.add(plane2_10.intersect(ray));
                hits.add(plane2_7.intersect(ray));
                hits.add(plane2_8.intersect(ray));
                hits.add(plane2_9.intersect(ray));
                hits.add(plane1_9.intersect(ray));
                hits.add(plane1_10.intersect(ray));
                Hit closestHit = findClosestHit(hits);
                return closestHit;
        }
}