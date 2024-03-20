package cgg.a06;

import cgg.Ray;


public record Group(Shape... shapes) implements Shape {
    /**
     * Calculates the intersection of a ray with the group of shapes.
     *
     * @param ray The ray to intersect with the group of shapes.
     * @return The hit result representing the closest intersection, or null if there is no intersection.
     */
    public Hit intersect(Ray ray) {
        Hit closest = null;
        for (var s : shapes) {
            // Intersect the ray with each shape in the group
            closest = Hit.closest(closest, s.intersect(ray));
        }
        return closest;
        
    }
}
