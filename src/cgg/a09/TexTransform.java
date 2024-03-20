package cgg.a09;

import static cgtools.Matrix.*;
import static cgtools.Vector.*;
import cgtools.*;

public record TexTransform(Matrix matrix, Sampler sampler) implements Sampler{

    @Override
    public Color getColor(double x, double y) {
        var uv = multiply(matrix, point(x, y, 0));
        return sampler.getColor(uv.x(), uv.y());
    }
}
