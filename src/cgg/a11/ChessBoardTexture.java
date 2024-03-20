package cgg.a11;

import cgtools.Sampler;
import cgtools.*;

public record ChessBoardTexture(double size, Color color1, Color color2) implements Sampler {

    public Color getColor(double u, double v) {

        int ui = (int) ((u % 1) * size*2);
        int vi = (int) ((v % 1) * size);

        if ((ui + vi) % 2 == 0)
            return color1;
        else
            return color2;
    };

}
