package cgg.a10;
import cgtools.*;

public record Constant(Color color) implements Sampler {

    @Override
    public Color getColor(double x, double y) {
        return color;
    }
    
}
