package cgg.a10;

import cgtools.Color;
import cgtools.Sampler;

public class Quatsch implements Sampler {
    private double radius;
    private Color color1;
    private Color color2;

    public Quatsch(double radius, Color color1, Color color2) {
        this.radius = radius;
        this.color1 = color1;
        this.color2 = color2;
    }

    
    public Color getColor(double u, double v) {
        double distanceToCenter = Math.sqrt(u * u + v * v);
        boolean isInCircle = distanceToCenter <= radius;

        if (isInCircle)
            return color1;
        else
            return color2;
    }
}


//package cgg.a09;
//
//import cgtools.Color;
//import cgtools.Sampler;
//
//public record PolkaDots(double radius, Color color1, Color color2) implements Sampler {
//
//    public Color getColor(double u, double v) {
//
//        int ui = (int) ((u % 1) * radius * 2);
//        int vi = (int) ((v % 1) * radius);
//
//        int field = calculateFieldSize();
//
//        double centerU = ui * field + field / 2;
//        double centerV = vi * field + field / 2;
//
//        double distanceToCenter = Math.sqrt(Math.pow(centerU - u, 2) + Math.pow(centerV - v, 2));
//        boolean isInCircle = distanceToCenter <= radius;
//
//        if (isInCircle)
//            return color1;
//        else
//            return color2;
//    }
//
//    private int calculateFieldSize() {
//        int size = 1;
//        while (size * 2 <= radius) {
//            size *= 2;
//        }
//        return size;
//    }
//}