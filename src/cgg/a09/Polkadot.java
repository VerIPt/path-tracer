package cgg.a09;

import cgtools.Color;
import cgtools.Sampler;

public record Polkadot(double r, Color circle, Color background) implements Sampler{

    @Override
    public Color getColor(double x, double y) {
        double mx = r*2+r/4;
        double my = r*2+r/4;
        double dx = (x%mx)-mx/2;
        double dy = (y%my)-my/2;
        double d = Math.sqrt(dx*dx+dy*dy);
        if (d<=r){
            
            return circle;
        }
        return background;
    }
    
}
