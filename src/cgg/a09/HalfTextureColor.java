package cgg.a09;

import cgtools.Sampler;
import cgtools.*;

public record HalfTextureColor(double radius, Color color1, Color color2) implements Sampler {

    public Color getColor(double u, double v) {

        int ui = (int) ((u % 1) * radius*2);
        int vi = (int) ((v % 1) * radius);

        var field = radius / radius;

        double centerU = ui * field + field / 2;
        double centerV = vi * field + field / 2;

        double distanceToCenter = Math.sqrt(Math.pow(centerU- u, 2) + Math.pow(centerV - v, 2));
        boolean isInCircle = distanceToCenter <= radius;

        
        //if ((ui + vi) % 2 == 0)
        //    return color1;
        if(isInCircle)
            return color1;
        else
            return color2;
    };


}
