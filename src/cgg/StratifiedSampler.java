package cgg;


import static cgtools.Vector.*;

import cgtools.Color;
import cgtools.Sampler;

public record StratifiedSampler(Sampler content, int n) implements Sampler {

  // StratifiedSampler taken from presentation ccg
  public Color getColor(double x, double y) {
    Color color = black;
    for (int xi = 0; xi < n; xi++) {
      for (int yi = 0; yi < n; yi++) {
        double rx = cgtools.Random.random();
        double ry = cgtools.Random.random();
        double xs = x + (xi + rx) / n;
        double ys = y + (yi + ry) / n;
        color = add(color, content.getColor(xs, ys));
      }
    }
    return divide(color, n * n);
  }
}
