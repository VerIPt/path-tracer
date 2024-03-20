package cgg.a07;

import static java.lang.Math.*;

import java.util.ArrayList;

import cgg.Image_Paralel;
import cgtools.*;
import cgg.*;
import static cgtools.Vector.*;
import static cgtools.Matrix.*;

public class Main {
    public static void main(String[] args) {
        // Set the width and height of the image
        final int width = 480;
        final int height = 270;

        // Set the gamma correction value
        final double gammaCorrection = 2.2;

        // Set the number of sampling points
        final int samplingPoints = 100
        ;

        // Create a new camera with the specified width, height, and field of view
        var camera = new Camera(
                width,
                height,
                toRadians(50),
                multiply(
                        // rotation(yAxis, +20),
                        rotation(xAxis, -20),
                        translation(0, 20, 100)));
        // translation(0, 30, 0),

        // defuse
        
        var orange = new Diffuse(hsvToRgb(color(0.1, 1.0, 0.8)));
        var green = new Diffuse(hsvToRgb(color(0.21, 1.0, 0.8)));
        var grey = new Diffuse(hsvToRgb(color(0, 0, 0.8)));
        var white = new Diffuse(hsvToRgb(color(0, 0, 1)));
        // hsv 1. farbton, 2. sättigung, 3. helligkeit
        var redD = new Diffuse(hsvToRgb(color(0.0, 1.0, 0.8)));
        var violet = new Diffuse(hsvToRgb(color(0.9, 1.0, 0.8)));
        var blaack = new Diffuse(hsvToRgb(color(1, 0.1, 0.1)));

        // emmision
        var blaack1 = new Emission(hsvToRgb(color(0.02, 0.02, 0.02)));
        var reed = new Emission(hsvToRgb(color(0.0, 1.0, 0.8)));
        var ooorange = new Emission(hsvToRgb(color(0.1, 1.0, 0.7)));
        var sky = new Emission(color(1));
        

        Image_Paralel image = new Image_Paralel(width, height, gammaCorrection);

        var center = new Point(0, 0, 0);
        var cylinder = new Group(rotation(zAxis, -90), new Cylinder(0.8, 15, center, white));
        

        var spiral = spiral(cylinder, yAxis, 55, 5);
        
        var scene = new Group(
            new Background(sky),
            new Cylinder(3, 15, center, grey),
            new Sphere(15, point(30, 15, 10), grey),
            new Sphere(5, point(0, 32, 0), grey),
            new Plane(point(0, 0, 0), yAxis, 30, grey));
        
        
        
        
        
        //new Background(blaack),
                //new Sphere(5, point(0, 28, 0), orange),
                //new Sphere(50, point(0, 90, 0), sky),
                //new Cylinder(3, 28, center, white),
                //spiral,
                //new HalfCircle(point(0,-70,0), 10, 200, 10, 200, ooorange),
                //new Plane(point(0, 0, 0), yAxis, 30, grey));

        image.sample(new StratifiedSampler(new Raytracer(camera, scene, 5), samplingPoints));

        // Write the image to a file
        image.write("doc/a07-Cylinder-test.png");

        // Print a message indicating that the image has been written
        System.out.println("Image spheres written");
    }

    private static Group spiral(Group g, Direction d, int n, double twist) {
        var list = new ArrayList<Shape>();
        d = divide(d, 2);
        for (int i = 0; i != n; i++) {
            list.add(new Group(

                    multiply(
                            rotation(yAxis, twist),
                            translation(multiply(i, d))),
                    g));
            twist = twist + 10;
        }
        return new Group(list);
    }

}
