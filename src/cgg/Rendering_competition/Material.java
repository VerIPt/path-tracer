package cgg.Rendering_competition;

import cgtools.*;
import cgg.Ray;

public interface Material {
    public Color getEmmision();

    public Color emission(Ray r, Hit h);

    public Color albedo(Ray r, Hit h);

    public Ray scatteredRay(Ray r, Hit h);

}
