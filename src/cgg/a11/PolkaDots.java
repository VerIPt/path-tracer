package cgg.a11;

import cgtools.*;
import cgtools.Sampler;

public class PolkaDots implements Sampler {

    public final Color a;
    public final Color b;
    public final double r;

    public PolkaDots(double size, Color a, Color b) {
        this.a = a;
        this.b = b;
        this.r = (size - Math.floor(size)) / 2;
    }

    @Override
    public Color getColor(double u, double v) {
        u = (u % 1) - 0.5;
        v = (v % 1) - 0.5;
        if (Math.sqrt(u * u + v * v) < r)
            return a;
        else
            return b;
    }

    @Override
    public String toString() {
        return String.format("(Dot %s, %s)", a.toString(), b.toString());
    }
}
