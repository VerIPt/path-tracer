package cgg.a04;

import cgtools.*;
import static cgtools.Vector.*;


import cgg.Ray;

public record Background(Color color) implements Shape {
    // Constant representing positive infinity
    static final double INF = Double.POSITIVE_INFINITY;

    /**
     * Calculates the intersection of the background with a ray.
     *
     * @param ray The ray to intersect with the background.
     * @return The hit result representing the intersection, or null if there is no intersection.
     */
    public Hit intersect(Ray ray) {
        if (ray.isValid(Double.POSITIVE_INFINITY)) {
            // If the ray is valid (not NaN or infinite), create a hit at infinity with the background color
            return new Hit(INF, point(INF, INF, INF), multiply(-1, ray.d()), color);
        } else {
            // Otherwise, return null to indicate no intersection with the background
            return null;
        }
    }
}
