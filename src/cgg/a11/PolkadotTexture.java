package cgg.a11;

import cgtools.Color;
import cgtools.Sampler;

public record PolkadotTexture(double r, Sampler sampler, Color background) implements Sampler{

@Override
public Color getColor(double x, double y) {
    double mx = r * 2 + r / 4;
    double my = r * 2 + r / 4;
    double dx = (x % mx) - mx / 2;
    double dy = (y % my) - my / 2;
    double d = Math.sqrt(dx * dx + dy * dy);
    if (d <= r) {
        // Scale the texture coordinates to fit within the circle
         // Convert Cartesian coordinates to polar coordinates
        double theta = Math.atan2(dy, dx);
        double radius = d / r;
        
        // Map polar coordinates to UV coordinates
        double u = 0.5 + radius * Math.cos(theta) * 0.5;
        double v = 0.5 + radius * Math.sin(theta) * 0.5;
        
        
        Color color = sampler.getColor(u, v);
        
        // Adjust the color range if needed
        double r = Math.pow(255*color.r()/255, 2.2);
		double g = Math.pow(255*color.g()/255, 2.2);
		double b = Math.pow(255*color.b()/255, 2.2);
        		return new Color(r,g,b);
    }
    return background; // or any default background color
}
    
}
