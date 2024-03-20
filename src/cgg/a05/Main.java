package cgg.a05;

import static java.lang.Math.*;


import cgg.*;
import static cgtools.Vector.*;

public class Main {
    public static void main(String[] args) {
        // Set the width and height of the image
        final int width = 1920;
        final int height = 1080;

        // Set the gamma correction value
        final double gammaCorrection = 2.2;

        // Set the number of sampling points
        final int samplingPoints = 100;

        // Create a new camera with the specified width, height, and field of view
        var camera = new Camera(width, height, toRadians(50));
        // defuse

        var orange = new Diffuse(hsvToRgb(color(0.1, 1.0, 0.8)));
        //var green = new Diffuse(hsvToRgb(color(0.21, 1.0, 0.8)));
        var grey = new Diffuse(hsvToRgb(color(0, 0, 0.8)));
        var redD = new Diffuse(hsvToRgb(color(0.0, 1.0, 0.8)));
        //var violet = new Diffuse(hsvToRgb(color(0.9, 1.0, 0.8)));
        var blaack = new Diffuse(hsvToRgb(color(1, 0.1, 0.1)));

        // emmision
        var blaack1 = new Emission(hsvToRgb(color(0.02, 0.02, 0.02)));
        var reed = new Emission(hsvToRgb(color(0.0, 1.0, 0.8)));

        //var sky = new Emission(hsvToRgb(color(0.09, 0.05, 1)));

        Image_Paralel image = new Image_Paralel(width, height, gammaCorrection);

        // hole, and stars
        var scene = new Group(
                // Blackhole lamp
                new Background(blaack1),
                new Plane(point(0, -8, -5), direction(0, 1, 0), 150, grey),
                new Blackhole(point(0, 2.8, -60), 4, 6, blaack, reed, red, black),
                new Sphere(1.5, point(0, -0, -60), blaack),
                new Sphere(1.5, point(0, -0.75, -60), blaack),
                new Sphere(1.5, point(0, -1.5, -60), blaack),
                new Sphere(1.5, point(0, -2.25, -60), blaack),
                new Sphere(1.5, point(0, -3, -60), blaack),
                new Sphere(1.5, point(0, -3.75, -60), blaack),
                new Sphere(1.5, point(0, -4.5, -60), blaack),
                new Sphere(1.5, point(0, -5.25, -60), blaack),
                new Sphere(1.5, point(0, -6, -60), blaack),
                new Sphere(1.5, point(0, -6.75, -60), blaack),
                new Sphere(1.5, point(0, -7.5, -60), blaack),
                new Plane(point(0, -7.8, -60), direction(0, 1, 0), 5, blaack),
                new Sphere(3, point(0, -8.50, -60), blaack),
                // spheres on the ground
                new Sphere(1.8, point(-4, -6.2, -70), redD),
                new Sphere(2, point(-6, -6.2, -68), orange),
                new Sphere(1, point(-8, -7, -55), grey),
                new Sphere(1.5, point(-10, -6.5, -50), redD),
                new Sphere(1.8, point(8, -6.2, -45), orange),
                new Sphere(1.5, point(6, -6.5, -50), redD));

        // Sample the image using a stratified sampler and a raytracer
        image.sample(new StratifiedSampler(new Raytracer(camera, scene, 5), samplingPoints));

        // Write the image to a file
        image.write("doc/a05_scene_defuse_test.png");

        // Print a message indicating that the image has been written
        System.out.println("Image spheres written");
    }
}
