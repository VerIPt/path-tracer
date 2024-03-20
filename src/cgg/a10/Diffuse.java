package cgg.a10;

import cgtools.*;
import cgg.Ray;
import static cgtools.Vector.*;
import static cgtools.Random.*;

public record Diffuse(Sampler albedo, Sampler emission) implements Material {
    
    public Diffuse(Sampler albedo) {
        this(albedo, new Constant(black));
    }

    public Diffuse(Color albedo, Color emission) {
        this(new Constant(albedo), new Constant(black));
    }


    public Diffuse(Color albedo) {
        this(albedo, black);
    }

    public Color emission(Ray r, Hit h) {
        return emission.getColor(h.uv().x(), h.uv().y());
    }

    public Color albedo(Ray r, Hit h) {
        return albedo.getColor(h.uv().x(), h.uv().y());
    }

    public Ray scatteredRay(Ray r, Hit h) {
        return new Ray(
                h.x(),
                normalize(add(h.n(), randomDir())),
                Util.EPSILON,
                Double.POSITIVE_INFINITY);
    }

    public static Direction randomDir() {
        var d = direction(
                random() * 2 - 1,
                random() * 2 - 1,
                random() * 2 - 1);
        return squaredLength(d) <= 1.0 ? d : randomDir();

    }
}
