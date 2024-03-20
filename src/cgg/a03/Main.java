package cgg.a03;

import cgg.*;
import cgtools.*;
import static cgtools.Vector.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int width = 1920;
        final int height = 1080;
        final double angle = 120;
        final double gammaCorrection = 2.2;
        final int samplingPoints = 1;
        final Color background = black;

        Camera pinHole = new Camera(width, height, angle);

        // Generate spheres using the recursive Mandelbulb algorithm
        ArrayList<Sphere> spheres = recursiveMandelbulb(0, 3, new Point(0, 0, -50), 10.0);

        // Add additional spheres
        spheres.add(new Sphere(3.5, point(0, 0, -40), red));
        spheres.add(new Sphere(12.5, point(0, 0, -60), blue));

        Image_Paralel image = new Image_Paralel(width, height, gammaCorrection);

        // Sample the scene using random sampling
        image.sample(new RandomSampler(new Raytracer(pinHole, spheres, background), samplingPoints));
        image.write("doc/a03-spheres.png");
        System.out.println("image spheres written");

    }

    public static Color getRandomColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b);
    }

    public static ArrayList<Sphere> recursiveMandelbulb(int depth, int maxDepth, Point center, double scale) {
        ArrayList<Sphere> spheres = new ArrayList<>();

        // Check if the maximum depth has been reached
        if (depth >= maxDepth) {
            return spheres;
        }

        int numSpheres = 4; // Number of spheres per level
        double radius = 1.5; // Sphere radius

        for (int i = 0; i < numSpheres; i++) {
            double angle = 2 * Math.PI * i / numSpheres;
            double x = center.x() + scale * Math.cos(angle);
            double y = center.y() + scale * Math.sin(angle);
            double z = center.z();
            Point sphereCenter = new Point(x, y, z);

            spheres.add(new Sphere(radius, sphereCenter, getRandomColor()));

            // Recursive call for the next level
            ArrayList<Sphere> childSpheres = recursiveMandelbulb(depth + 1, maxDepth, sphereCenter, scale / 2);
            spheres.addAll(childSpheres);
        }

        return spheres;
    }

}
