package cgg.a11;

import static cgtools.Vector.*;

import cgg.Ray;
import cgtools.BoundingBox;

public record Background(Material material) implements Shape {
    // Constant representing positive infinity
    static final double INF = Double.POSITIVE_INFINITY;

    /**
     * Calculates the intersection of the background with a ray.
     *
     * @param ray The ray to intersect with the background.
     * @return The hit result representing the intersection, or null if there is no
     *         intersection.
     */
    public Hit intersect(Ray ray) {

        if (!ray.isValid(Double.POSITIVE_INFINITY)) {
            return null;
        }

        var inclination = Math.acos(ray.d().y());
        var azimuth = Math.PI + Math.atan2(ray.d().x(), ray.d().z());
        var u = azimuth / (2 * Math.PI);
        var v = inclination / Math.PI;

        // background color
        return new Hit(
                INF,
                point(INF, INF, INF),
                multiply(-1, ray.d()),
                point(u, v, 0),
                material);
    }

    @Override
    public BoundingBox bounds() {
        return BoundingBox.everything;
    }

}
