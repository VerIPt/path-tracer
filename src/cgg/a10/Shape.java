package cgg.a10;
import cgg.Ray;
import cgtools.BoundingBox;

/*
 * Represents a shape that can be intersected with a ray.
 */ 
public interface Shape {
    public Hit intersect(Ray ray);
    public BoundingBox bounds();
}
