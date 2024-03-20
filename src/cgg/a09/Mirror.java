package cgg.a09;

import cgtools.*;
import cgg.Ray;

import static cgtools.Vector.*;

public record Mirror(Color mirror) implements Material {

    @Override
    public Color emission(Ray r, Hit h) {
        return black;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return black;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        Direction reflectionDirection = reflect(r.d(), h.n());
        Point reflectionOrigin = h.x();
        double tMin = Util.EPSILON;
        double tMax = Double.POSITIVE_INFINITY;

        return new Ray(reflectionOrigin, reflectionDirection, tMin, tMax);
    }

    private Direction reflect(Direction incident, Direction normal) {
        return subtract(incident, multiply(2.0 * dotProduct(incident, normal), normal));
    }
}
