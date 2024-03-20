package cgg.a06;

//import cgg.*;
import cgtools.*;
import static cgtools.Vector.*;

import cgg.Ray;


public record Raytracer(Camera camera, Group scene, int depth) implements Sampler {

    
    // Implementation of the getColor method from the Sampler interface
    public Color getColor(double x, double y) {
        // Generate a ray from the camera for the given pixel coordinates (x, y)
        Ray ray = camera.generateRay(x, y);
        
        // Calculate the radiance for the generated ray
        return calculateRadiance(scene, ray, depth);
    }
    
    // Recursive method to calculate the radiance of a ray in the scene
    private Color calculateRadiance(Group scene, Ray ray, int depth) {
        if (depth <= 0) {
            return black;
        }
        
        // Find the closest intersection of the ray with the objects in the scene
        Hit hit = scene.intersect(ray);
        
        // If there is no intersection, return the background color
        if (hit == null) {
            return white;
        }
        Material material = hit.m();
        // Get the material of the intersected object
        
        
        // Calculate the emitted radiance of the hit point
        Color emittedRadiance = material.emission(ray, hit);
        
        // Calculate the reflected radiance by recursively tracing reflected rays
        Ray scatteredRay = material.scatteredRay(ray, hit);
        
        Color reflectedRadiance = multiply(material.albedo(ray, hit), 
        calculateRadiance(scene, scatteredRay, depth - 1));
        // Calculate the total radiance as the sum of emitted and reflected radiance
        return add(emittedRadiance, reflectedRadiance);
    }
}
