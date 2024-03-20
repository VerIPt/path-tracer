package cgg.a03;

import cgtools.Direction;
import cgtools.Vector;

// Implements the model of a pinhole camera
// Produces a ray per sampling point
// Provides a method to return a ray
// Parameters: image width, image height, field of view angle

public class Camera {

    private double angle; // Field of view angle
    private int w; // Image width
    private int h; // Image height
    private double z; // Distance from the image plane to the pinhole

    public Camera(int w, int h, double angle) {
        this.angle = angle;
        this.w = w;
        this.h = h;
        this.z = (w / 2) / Math.tan(angle / 2);
    }

    public double getAngle() {
        return angle;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public double getZ() {
        return z;
    }

    // Compute the direction of a ray given a pixel position (x, y)
    public Direction rayDirection(double x, double y) {
        double xd = x - (w / 2); // Offset from the center in the x-axis
        double yd = -(y - (h / 2)); // Offset from the center in the y-axis (inverted)
        double z = -(w / 2) / Math.tan(angle / 2); // Distance from the image plane to the pinhole
        return Vector.normalize(new Direction(xd, yd, z));
    }
}
