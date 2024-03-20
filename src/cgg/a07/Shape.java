package cgg.a07;
import cgg.Ray;

/*
 * Represents a shape that can be intersected with a ray.
 */ 
public interface Shape {
    Hit intersect(Ray ray);
}
