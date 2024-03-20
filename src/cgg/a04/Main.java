package cgg.a04;

import static java.lang.Math.*;
import cgg.Image_Paralel;
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
        final int samplingPoints = 16;

        // Create a new camera with the specified width, height, and field of view
        var camera = new Camera(width, height, toRadians(50));

        // Define colors using the HSV color model and convert them to RGB
        //var color1 = hsvToRgb(color(1, 0.05, 0.05));
        //var color2 = hsvToRgb(color(0.8, 1, 1));
        //var color3 = hsvToRgb(color(1, 1, 1));
        //var color4 = hsvToRgb(color(0.65, 1, 1));

        // Set the background color
        var background = color(0.04, 0, 0);

        // Create a new image with the specified width, height, and gamma correction
        Image_Paralel image = new Image_Paralel(width, height, gammaCorrection);

        // Create the scene using a group of objects, including a background, a black
        // hole, and stars
        var scene = new Group(
                new Background(background),
                new Blackhole(point(0, 0, -30), 4, 6, black, red),
                new Stars(1000));

        // Sample the image using a stratified sampler and a raytracer
        image.sample(new RandomSampler(new Raytracer(camera, scene), samplingPoints));

        // Write the image to a file
        image.write("doc/a04_scene.png");

        // Print a message indicating that the image has been written
        System.out.println("Image spheres written");
    }
}
