package cgg;


import static cgtools.Vector.*;

import cgtools.Color;
import cgtools.Sampler;

public record RandomSampler(Sampler content, int n) implements Sampler {

    // Randomsampler taken from Presentation ccg
    public Color getColor(double x, double y) {
        Color color = black;
        for (int i = 0; i < n; i++) {
            double rx = cgtools.Random.random();
            double ry = cgtools.Random.random();
            double xs = x + rx;
            double ys = y + ry;
            color = add(color, content.getColor(xs, ys));
        }
        return divide(color, n);
    }
}
