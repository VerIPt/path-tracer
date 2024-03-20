package cgg.Rendering_competition;

import cgtools.*;
import cgg.Ray;

import static cgtools.Vector.*;

public record Emission(Sampler emission) implements Material {

    public Emission(Color e){
        this(new Constant(e));
    }
    public Color getEmmision(){
        return emission.getColor(0,0);
    }
    
    @Override
    public Color emission(Ray r, Hit h) {
        return emission.getColor(h.uv().x(), h.uv().y());
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
