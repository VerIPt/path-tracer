package cgg.a06;

import static java.lang.Math.*;
import cgg.Image_Paralel;
import cgtools.*;
import cgg.*;
import static cgtools.Vector.*;
import static cgtools.Matrix.*;

public class Main {
    public static void main(String[] args) {
        // Set the width and height of the image
        final int width = 1280;
        final int height = 720;

        // Set the gamma correction value
        final double gammaCorrection = 2.2;

        // Set the number of sampling points
        final int samplingPoints = 25;

        // Create a new camera with the specified width, height, and field of view
        var camera = new Camera(
                width,
                height,
                toRadians(50),
                multiply(
                        rotation(yAxis, +10),
                        rotation(xAxis, -15),
                        translation(0, 5, 40)));
        // translation(0, 30, 0),

        // defuse

        var orange = new Diffuse(hsvToRgb(color(0.1, 1.0, 0.8)));
        var green = new Diffuse(hsvToRgb(color(0.21, 1.0, 0.8)));
        var grey = new Diffuse(hsvToRgb(color(0, 0, 0.8)));
        //var redD = new Diffuse(hsvToRgb(color(0.0, 1.0, 0.8)));
        var violet = new Diffuse(hsvToRgb(color(0.9, 1.0, 0.8)));
        var blaack = new Diffuse(hsvToRgb(color(1, 0.1, 0.1)));

        // emmision
        var blaack1 = new Emission(hsvToRgb(color(0.02, 0.02, 0.02)));
        var reed = new Emission(hsvToRgb(color(0.0, 1.0, 0.8)));
        var ooorange = new Emission(hsvToRgb(color(0.1, 1.0, 0.7)));

        //var sky = new Emission(hsvToRgb(color(0.09, 0.05, 1)));

        Image_Paralel image = new Image_Paralel(width, height, gammaCorrection);

        var center = new Point(0, 0, 0);

        var scene = new Group(

                new Sphere(1.2, point(0, 10.2, 0), orange),
                new Sphere(10, point(0, 40, 0), ooorange),
                new Background(blaack1),
                new Plane(point(0, 0, 0), direction(0, 1, 0), 30, grey),
                new Plane(point(0, 0.1, 0), direction(0, 1, 0), 4, blaack),

                new SpiralStairCase(center, 10, 0.5, 3, reed, green, violet),
                new HalfCircle(
                        point(0, 0, 0),
                        0.5,
                        10,
                        10,
                        8.5,
                        ooorange)

        // spheres on the ground
        // new Sphere(1.8, point(-4, 0, -2), redD),
        // new Sphere(2, point(-6, 0, -4), orange),
        // new Sphere(1, point(-8, 0, 5), grey),
        // new Sphere(1.5, point(-10, 0, 10), redD),
        // new Sphere(1.8, point(8, 0, 15), orange)
        //// new Cylinder(1, 10, point(0, 0, 0), violet),
        // new Sphere(1.5, point(6, 0, 10), redD),

        );

        // Sample the image using a stratified sampler and a raytracer
        image.sample(new StratifiedSampler(new Raytracer(camera, scene, 5), samplingPoints));

        // Write the image to a file
        image.write("doc/a06_camera.png");

        // Print a message indicating that the image has been written
        System.out.println("Image spheres written");
    }
}
