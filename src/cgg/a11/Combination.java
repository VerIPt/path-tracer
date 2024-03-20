package cgg.a11;

import cgg.Ray;
import cgtools.Color;
import cgtools.Sampler;

public record Combination(Material m1, Material m2, Sampler s) implements Material {

    public Color emission(Ray r, Hit h) {
        var c = s.getColor(h.uv().x(), h.uv().y());
        if (c.r() > 0.1 && c.g() > 0.1 && c.b() > 0.1) {
            return m1.emission(r, h);
        } else {
            return m2.emission(r, h);
        }
    }

    public Color albedo(Ray r, Hit h) {
        var c = s.getColor(h.uv().x(), h.uv().y());
        if (c.r() > 0.1 && c.g() > 0.1 && c.b() > 0.1) {
            return m1.albedo(r, h);
        } else {
            return m2.albedo(r, h);
        }
    }

    public Ray scatteredRay(Ray r, Hit h) {
        var c = s.getColor(h.uv().x(), h.uv().y());
        if (c.r() > 0.1 && c.g() > 0.1 && c.b() > 0.1) {
            return m1.scatteredRay(r, h);
        } else {
            return m2.scatteredRay(r, h);
        }
    }

}
