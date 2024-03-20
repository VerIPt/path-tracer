package cgg.Rendering_competition;

import cgg.Ray;
import cgtools.*;
import static cgtools.Vector.*;

public record GlassMaterial(Color albedo , double opticalIndex) implements Material {

    public Color getEmmision(){
        return albedo;
    }
    @Override
    public Color emission(Ray r, Hit h) {
        return black;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return albedo;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        Direction scattered = null;
        Direction n = h.n();
        Direction d = r.d();
        double n1 = 1.0;
        double n2 = opticalIndex;
        if (dotProduct(n, d) > 0) {
            n = multiply(n, -1);
            n1 = opticalIndex;
            n2 = 1.0;
        }
        if (refract(d, n, n1, n2) != null) {
            if (Random.random() > schlick(d, n, n1, n2)) {
                scattered = refract(d, n, n1, n2);
            } else {
                scattered = reflect(d, n);
            }
        } else {
            scattered = reflect(d, n);
        }
        return new Ray(h.x(), scattered, 0.00001, Double.POSITIVE_INFINITY);

    }

    public Direction refract(Direction d, Direction n, double n1, double n2) {
        double r = n1 / n2;
        double c = -1.0 * dotProduct(n, d);
        double dt = ((1 - Math.pow(r, 2) * (1 - Math.pow(c, 2))));
        if (dt > 0) {
            Direction result = add(multiply(r, d), multiply(r * c - Math.sqrt(dt), n));
            return result;
        }
        return null;
    }

    public double schlick(Direction d, Direction n, double n1, double n2) {
        double specularReflectionFactor = Math.pow((n1 - n2) / (n1 + n2), 2);
        return specularReflectionFactor + (1 - specularReflectionFactor) * Math.pow((1 + dotProduct(n, d)), 5);
    }

    public Direction reflect(Direction d, Direction n) {
        return subtract(d, (multiply(2, multiply(dotProduct(n, d), n))));
    }
}
