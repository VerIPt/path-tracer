package cgg.a11;

import cgtools.Color;
import cgtools.Util;

import static cgtools.Vector.*;

import cgg.Ray;

public record Metal(Color albedo) implements Material {

    public Color emission(Ray r, Hit h) {
        return black;
    }

    public Color albedo(Ray r, Hit h) {
        return albedo;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        return new Ray(
                h.x(),
                subtract(r.d(), multiply(2 * dotProduct(r.d(), h.n()), h.n())),
                Util.EPSILON, Double.POSITIVE_INFINITY);
    }

}
