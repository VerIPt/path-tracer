package cgg.a04;


import cgtools.*;
import static cgtools.Vector.*;

import cgg.Ray;

public record Raytracer(Camera camera, Group scene) implements Sampler {

    // Implementation of the getColor method from the Sampler interface
    public Color getColor(double x, double y) {
        // Generate a ray from the camera for the given pixel coordinates (x, y)
        Ray ray = camera.generateRay(x, y);
        
        // Find the closest intersection of the ray with the objects in the scene
        Hit hit = scene.intersect(ray);
        
        // Shade the surface using the hit's normal and color
        return shade(hit.n(), hit.c());
    }
    
    // Method to calculate the shading of a surface based on its normal and color
    static Color shade(Direction normal, Color color) {
        Direction lightDir = normalize(direction(1, 1, 0.5));
        
        // Calculate ambient component (20% of the color)
        Color ambient = multiply(0.2, color);
        
        // Calculate diffuse component (80% of the color multiplied by the dot product of the light direction and normal)
        Color diffuse = multiply(0.8 * Math.max(0, dotProduct(lightDir, normal)), color);
        
        // Combine ambient and diffuse components to get the final shaded color
        return add(ambient, diffuse);
    }
}
