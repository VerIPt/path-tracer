package cgg.a09;

import cgtools.*;
import cgg.Ray;

public interface Material {
    public Color emission(Ray r, Hit h);

    public Color albedo(Ray r, Hit h);

    public Ray scatteredRay(Ray r, Hit h);

}
