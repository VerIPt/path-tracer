package cgg.a06;

import cgtools.*;
import cgg.Ray;


import static cgtools.Vector.*;

public record Emission(Color e) implements Material {

    public Color getColor(){
        return e;
    }

    @Override
    public Color emission(Ray r, Hit h) {
        return e;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return black; // Da es sich um den Hintergrund handelt, hat er keine Albedo (Reflexionsvermögen)
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        return null; // Da es sich um den Hintergrund handelt, gibt es keinen gestreuten Strahl (scattered ray)
    }
}
