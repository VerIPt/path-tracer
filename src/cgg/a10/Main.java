package cgg.a10;

import static java.lang.Math.*;

import java.util.ArrayList;

import cgtools.*;
import cgg.*;
import static cgtools.Vector.*;
import static cgtools.Vector.multiply;
import static cgtools.Matrix.*;
import static cgtools.Matrix.multiply;

//
public class Main {
        public static void main(String[] args) {
                // Set the width and height of the image
                final int width = 470;
                final int height = 280;

                // Set the gamma correction value
                final double gammaCorrection = 2.2;

                // Set the number of sampling points
                final int samplingPoints = 5;

                // Create a new camera with the specified width, height, and field of view
                var camera = new Camera(
                                width,
                                height,
                                toRadians(90),
                                multiply(
                                                // rotation(yAxis, +20),
                                                rotation(xAxis, 0),
                                                translation(0, 0, 50)));
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

                // texture
                var earth = new Diffuse(new ImageTexture("img/earth.jpg"));
                var universe = new Emission(new ImageTexture("img/space2.jpg"));
                var chessBoardTexture = new Diffuse(
                                new ChessBoardTexture(5, color(1),
                                                color(0)));
                var polkaDots = new Diffuse(new PolkaDots(1, color(1), color(0)));
                var quatsch = new Diffuse(new Quatsch(1, color(1), color(0)));

                var sun = new Emission(new ImageTexture("img/sun.jpg"));
                var sky = new Emission(new ImageTexture("img/sky3.jpg"));

                // neue materialien
                var spiegel = new Metal(color(1));
                var glas = new GlassMaterial(color(1), 1.8);

                var image = new Image_Paralel(width, height, gammaCorrection);

                var cylinder = new Group(
                                rotation(zAxis, -90),
                                new Cylinder(0.8, 15, zero, orange));
                int heightStaircase = 52;// 52
                int rowStaircase = 50;
                int rowStairGrid = 50;
                // var spiral = spiral(cylinder, yAxis, 55, 10);
                var spiral2 = spiral2(yAxis, heightStaircase, 10);
                var stairLine = muchoMas(spiral2, direction(0, 0, -20), rowStaircase, heightStaircase);
                var stairGrid = muchoMas(stairLine, direction(-20, 0, 0), rowStairGrid, heightStaircase);

                // scene
                var scene = new Group(
                                new Background(sky)
                                //new Circle(zero, 7, 10, reed)
                                // new Plane(point(0, -25, 0), nyAxis, 100, sun),
                                // new Sphere(15, new Point(10, 10, 0), spiegel),
                                // new Sphere(25, new Point(-20, 10, 25), glas),
                                // stairGrid

                );

                int shapeCount = scene.shapes.size();
                // int shapeCount = scene.shapes.size() + ((heightStaircase - 1) * rowStaircase
                // * rowStairGrid);
                // measure renderTime
                Long startTime = System.nanoTime();

                image.sample(new StratifiedSampler(new Raytracer(camera, scene, 5), samplingPoints));

                // Write the image to a file
                image.write("doc/a10_test2.png");

                Long endTime = System.nanoTime();
                double elapsedTimeSeconds = (endTime - startTime) / 1_000_000_000.0;
                System.out.println("\nShapes: " + shapeCount);
                System.out.println("Took " + elapsedTimeSeconds + " s");
                System.out.println("-------------------------------------");
                // Print a message indicating that the image has been written
                System.out.println("Image spheres written");
        }

        private static Group spiral2(Direction d, int n, double twist) {
                var list = new ArrayList<Shape>();
                d = divide(d, 2);
                for (int i = 0; i != n; i++) {
                        list.add(new Group(

                                        multiply(
                                                        rotation(yAxis, twist),
                                                        translation(multiply(i, d))),
                                        new Group(
                                                        rotation(zAxis, -90),
                                                        new Cylinder(1, 12,
                                                                        point(0, 0, 0),
                                                                        new Diffuse(hsvToRgb(color(((0.2 * i) * 0.1),
                                                                                        1.0, 0.8)))))));
                        twist = twist + 10;
                }
                return new Group(list);
        }

        private static Group muchoMas(Group g, Direction d, int n, int height) {
                var list = new ArrayList<Shape>();
                for (int i = 0; i != n; i++) {
                        if (i % 2 == 0) {
                                list.add(new Group(
                                                multiply(
                                                                rotation(zAxis, 180),
                                                                translation(multiply(i, d)),
                                                                translation(0, -height / 2, 0)),
                                                g));
                        } else {
                                list.add(new Group(
                                                translation(multiply(i, d)),
                                                g));
                        }
                }
                return new Group(list);
        }

}
