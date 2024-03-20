package cgg.Rendering_competition;

import static java.lang.Math.*;

import java.io.FileWriter;
import java.io.IOException;
import cgtools.*;
import cgg.*;
import static cgtools.Vector.*;

import static cgtools.Matrix.*;
import static cgtools.Matrix.multiply;

//
public class Main {
        public static void main(String[] args) {
                // Set the width and height of the image
                final int width = 480;
                final int height = 270;

                // Set the gamma correction value
                final double gammaCorrection = 2.2;

                // Set the number of sampling points
                final int samplingPoints = 8;

                // Create a new camera with the specified width, height, and field of view

                // hsv 1. farbton, 2. sättigung, 3. helligkeit

                var violet = new Emission(hsvToRgb(color(0.7, 1.0, 0.8)));
                var blaack = new Diffuse(color(0));

                // emmision
                var reed = new Emission(hsvToRgb(color(0.0, 1.0, 0.8)));

                var offwhite = new Emission(color(1));

                // texture

                var universe = new Emission(new ImageTexture("img/spaceSquare.jpg"));
                var universe2 = new Emission(new ImageTexture("img/universe03_background.jpg"));

                var sky = new Emission(new ImageTexture("img/clouds2.jpg"));

                // neue materialien
                var glas = new GlassMaterial(color(1), 1);
                var glas2 = new GlassMaterial(color(1), 5);

                var spaceGround = new Group(rotation(xAxis, 90), new Plane(point(0, 0, 0), yAxis, 110, universe));
                var spaceGround2 = new Group(rotation(xAxis, 90), new Plane(point(0, 0, 0), yAxis, 110, universe2));

                var image = new Image_Paralel(width, height, gammaCorrection);
                var centerPic = new Point(0, 4, 0);

                var uni0 = new Group(new Sphere(2.7, zero, glas),
                                new Sphere(2.5, zero, new Emission(new ImageTexture("img/universe01.jpg"))));
                var uni1 = new Group(new Sphere(2.7, zero, glas),
                                new Sphere(2.5, zero, new Emission(new ImageTexture("img/universe02.jpg"))));
                var uni2 = new Group(new Sphere(2.2, zero, glas),
                                new Sphere(2.0, zero, new Emission(new ImageTexture("img/universe03.jpg"))));
                var uni3 = new Group(new Sphere(1.7, zero, glas),
                                new Sphere(1.5, zero, new Emission(new ImageTexture("img/universe04.jpg"))));
                var uni4 = new Group(new Sphere(2.0, zero, glas),
                                new Sphere(1.8, zero, new Emission(new ImageTexture("img/universe05.jpg"))));
                var uni5 = new Group(new Sphere(1.2, zero, glas),
                                new Sphere(1.0, zero, new Emission(new ImageTexture("img/universe06.jpg"))));
                var uni6 = new Group(new Sphere(1.2, zero, glas),
                                new Sphere(1.0, zero, new Emission(new ImageTexture("img/universe07.jpg"))));
                var uni7 = new Group(new Sphere(1.4, zero, glas),
                                new Sphere(1.2, zero, new Emission(new ImageTexture("img/batik.jpg"))));

                // Print Frames:
                // 61 by 59.5

                double cameramovementMultiverse = 34.55; // start 34.55
                double cameramovementWhiteHole = 16.5;// start 16.5
                double cameramovementBlackHole = 90; // start 90
                double spinUniverse = 0;
                double spinCamera = 0;

                // Important for new initialising use -1 parameter!!!!!

                for (int i = 155; i < 362; i++) {

                        if (i <= 154) {

                                if (i > 90 && i % 2 == 0) {
                                        printBlackhole(0.5, cameramovementBlackHole, i, width, height, samplingPoints,
                                                        image,
                                                        spaceGround,
                                                        blaack, reed, glas, centerPic);

                                } else {
                                        printBlackhole(0, cameramovementBlackHole, i, width, height, samplingPoints,
                                                        image,
                                                        spaceGround,
                                                        blaack, reed, glas, centerPic);
                                }
                                cameramovementBlackHole -= 0.5;
                        }

                        if (i > 154 && i <= 362) {
                                printMultiverse(spinCamera, cameramovementMultiverse, i, width, height, samplingPoints,
                                                image, sky,
                                                spinUniverse, glas, glas2, uni0, uni1, uni2, uni3, uni4, uni5, uni6,
                                                uni7);
                                cameramovementMultiverse -= 0.15;
                                spinUniverse += 0.5;
                                spinCamera += 0.5;
                        }

                        if (i > 363 && i < 525) {

                                if (i < 450 && i % 2 == 0) {
                                        printBlackhole(0.5, cameramovementWhiteHole, i, width, height, samplingPoints,
                                                        image,
                                                        spaceGround2,
                                                        offwhite, violet, glas, centerPic);

                                } else {
                                        printBlackhole(0, cameramovementWhiteHole, i, width, height, samplingPoints,
                                                        image,
                                                        spaceGround2,
                                                        offwhite, violet, glas, centerPic);
                                }
                                cameramovementWhiteHole += 0.5;
                        }
                        try {
                                FileWriter fileWriter = new FileWriter("ani/Progress.txt");
                                fileWriter.write("Image: " + i + " written\n");
                                fileWriter.write("Multiverse Distance: " + cameramovementMultiverse + "\n");
                                fileWriter.write("Blackhole Distance: " + cameramovementBlackHole + "\n");
                                fileWriter.write("Whitehole Distance: " + cameramovementWhiteHole + "\n");
                                fileWriter.write("Spin: " + spinUniverse + "\n");
                                fileWriter.write("Spin Camera: " + spinCamera + "\n");
                                fileWriter.write("-------------------------------------\n");
                                fileWriter.close();

                        } catch (IOException e) {
                                System.out.println("Error writing file");
                        }
                }
        }

        public static void printMultiverse(double spinCamera, double cameramovement, int i, int width, int height,
                        int samplingPoints, Image_Paralel image,
                        Material sky, double spinUniverse, Material glas, Material glas2, Group uni0, Group uni1,
                        Group uni2,
                        Group uni3,
                        Group uni4,
                        Group uni5, Group uni6, Group uni7) {

                var camera = new Camera(
                                width,
                                height,
                                toRadians(50),
                                multiply(
                                                rotation(zAxis, spinCamera),
                                                rotation(xAxis, 0),
                                                translation(0, 0, cameramovement)));

                var scene = new Group(
                                new Group(rotation(yAxis, 90),
                                new Background(new Emission(new ImageTexture("img/batik_square.jpg")))),
                                // new Group(
                                // multiply(
                                // translation(0, 0, -1500),
                                // rotation(xAxis, 90)),
                                // new Plane(point(0, 0, 0), yAxis, 1000,
                                // new Diffuse(new ImageTexture(
                                // "img/batik_square.jpg")))),

                                new Sphere(2, point(0, 0, 34), glas2),

                                new Group(
                                                multiply(
                                                                translation(8, 8, -0.5),
                                                                rotation(xAxis, spinUniverse),
                                                                rotation(yAxis, spinUniverse)),
                                                uni0),
                                new Group(
                                                multiply(
                                                                translation(-1, 3, -10),
                                                                rotation(xAxis, spinUniverse),
                                                                rotation(yAxis, spinUniverse)),
                                                uni0),
                                new Group(
                                                multiply(
                                                                translation(0, 0, 0),
                                                                rotation(xAxis, spinUniverse),
                                                                rotation(yAxis, spinUniverse)),
                                                uni1),

                                new Group(
                                                multiply(
                                                                translation(4.5, 2.5, 5),
                                                                rotation(nxAxis, spinUniverse),
                                                                rotation(nyAxis, spinUniverse)),
                                                uni2),
                                new Group(
                                                multiply(
                                                                translation(-3.5, 2.5, 20),
                                                                rotation(nxAxis, spinUniverse),

                                                                rotation(nyAxis, spinUniverse)),
                                                uni2),

                                new Group(
                                                multiply(
                                                                translation(-4.5, 1, 2),
                                                                rotation(nxAxis, spinUniverse),
                                                                rotation(nyAxis, spinUniverse)),
                                                uni3),

                                new Group(
                                                multiply(
                                                                translation(3.5, -2, 6),
                                                                rotation(nxAxis, spinUniverse),
                                                                rotation(nyAxis, -spinUniverse)),
                                                uni4),
                                new Group(
                                                multiply(
                                                                translation(-3.5, -2.5, 6),
                                                                rotation(nxAxis, spinUniverse),
                                                                rotation(nyAxis, -spinUniverse)),
                                                uni4),
                                // Aktuel
                                new Group(
                                                multiply(
                                                                translation(-2.5, 2.5, 11),
                                                                rotation(nxAxis, spinUniverse),
                                                                rotation(yAxis, -spinUniverse)),
                                                uni5),
                                // Aktuel
                                new Group(
                                                multiply(
                                                                translation(-6, 1.5, 10),
                                                                rotation(nxAxis, spinUniverse),
                                                                rotation(yAxis, -spinUniverse)),
                                                uni5),

                                new Group(
                                                multiply(
                                                                translation(0, -3, 14),
                                                                rotation(nxAxis, spinUniverse),
                                                                rotation(nyAxis, -spinUniverse)),
                                                uni6),
                                new Group(
                                                multiply(
                                                                translation(-6.5, 5, 14),
                                                                rotation(nxAxis, spinUniverse),
                                                                rotation(nyAxis, -spinUniverse)),
                                                uni6),
                                new Group(
                                                multiply(
                                                                translation(-3.5, -3.6, 13.5),
                                                                rotation(nxAxis, spinUniverse),
                                                                rotation(nyAxis, -spinUniverse)),
                                                uni7),
                                new Group(
                                                multiply(
                                                                translation(4, 0, 11),
                                                                rotation(nxAxis, spinUniverse),
                                                                rotation(nyAxis, -spinUniverse)),
                                                uni7)

                );

                int shapeCount = scene.shapes.size();

                Long startTime = System.nanoTime();

                image.sample(new StratifiedSampler(new Raytracer(camera, scene, 5), samplingPoints));

                // Write the image to a file
                image.write("ani/competition" + i + ".png");
                // image.write("ani/competition_test.png");
                Long endTime = System.nanoTime();
                double elapsedTimeSeconds = (endTime - startTime) / 1_000_000_000.0;
                System.out.println("\nShapes: " + shapeCount);
                System.out.println("Took " + elapsedTimeSeconds + " s");
                System.out.println("Image competition " + i + " written");
                System.out.println("Distance: " + cameramovement);
                System.out.println("Spin: " + spinUniverse);
                System.out.println("Spin Camera: " + spinCamera);
                System.out.println("-------------------------------------");

                
        }

        public static void printBlackhole(double cameraAngle, double cameramovement, int i, int width, int height,
                        int samplingPoints, Image_Paralel image, Group spaceGround, Material blaack, Material reed,
                        Material glas, Point centerPic) {

                var camera = new Camera(
                                width,
                                height,
                                toRadians(50),
                                multiply(
                                                rotation(zAxis, cameraAngle),
                                                rotation(xAxis, -2.5),
                                                translation(0, 4, cameramovement)));

                var scene = new Group(
                                new Background(blaack),
                                new Group(translation(0, 0, -100), spaceGround),
                                // new Sphere(5, new Point (0, 10 , 0 ), blaack),
                                new Blackhole(centerPic, 12, 18, blaack, reed),
                                new Sphere(12.5, centerPic, glas));

                int shapeCount = scene.shapes.size();

                Long startTime = System.nanoTime();

                image.sample(new StratifiedSampler(new Raytracer(camera, scene, 5), samplingPoints));

                // Write the image to a file
                image.write("ani/competition" + i + ".png");
                // image.write("ani/competition_test.png");
                Long endTime = System.nanoTime();
                double elapsedTimeSeconds = (endTime - startTime) / 1_000_000_000.0;
                System.out.println("\nShapes: " + shapeCount);
                System.out.println("Took " + elapsedTimeSeconds + " s");
                System.out.println("-------------------------------------");
                // Print a message indicating that the image has been written
                System.out.println("Image competition " + i + " written");
                System.out.println("Distance: " + cameramovement);

        }
}
