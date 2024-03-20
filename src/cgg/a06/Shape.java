package cgg.a06;
import cgg.Ray;


/*
 * Represents a shape that can be intersected with a ray.
 */ 
public interface Shape {
    Hit intersect(Ray ray);
}
