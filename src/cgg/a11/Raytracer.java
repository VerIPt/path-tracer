package cgg.a11;

import cgg.Light;
import cgg.Ray;

import cgtools.Color;
import cgtools.Sampler;
import cgtools.Vector;

public class Raytracer implements Sampler {

    protected Camera camera;
    protected Group group;
    protected World world;

    public Raytracer(Camera camera, Group group){
        this.camera = camera;
        this.group = group;
        this.world = new World(null, group);
    }

    public Raytracer(Camera camera, World world){
        this.camera = camera;
        this.world = world;
        this.group = world.scene;
    }

    public Color getColor(double x, double y) {
        Ray cameraRay = camera.generateRay(x, y);
        return calculateRadiance(group, cameraRay, 5);
    }

    public Color calculateRadiance(Shape scene, Ray ray, int depth) {
        if (depth == 0) {
            return Vector.black;
        }
        Hit hit = scene.intersect(ray);

        Ray secondaryRay = hit.m().scatteredRay(ray, hit);
        Color emission = hit.m().emission(ray, hit);
        Color albedo = hit.m().albedo(ray, hit);

        if (secondaryRay != null) {
            Color lightIntensity = null;
            if(world.lightList != null){
                lightIntensity = Vector.black;
                for(Light light: world.lightList){
                    Color intensity = light.incomingIntensity(hit, scene);
                    intensity = Vector.multiply(intensity, albedo);
                    lightIntensity = Vector.add(lightIntensity, intensity);
                }
            }
            Color a1 = Vector.add(albedo, emission);
            Color radiance = Vector.multiply(a1, calculateRadiance(scene, secondaryRay, depth - 1));

            if(lightIntensity != null){
                radiance = Vector.add(radiance, lightIntensity);
            }
            return radiance;
        }
        return emission;
    }
}