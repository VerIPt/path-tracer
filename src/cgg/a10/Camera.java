package cgg.a10;

import cgtools.*;
import static cgtools.Vector.*;
import static cgtools.Matrix.*;
import cgg.Ray;

public record Camera(int width, int height, double fov, Matrix v) {

    /**
     * Generates a ray from the camera's position towards the specified pixel
     * coordinates.
     *
     * @param x The x-coordinate of the pixel.
     * @param y The y-coordinate of the pixel.
     * @return The generated ray.
     */
    public Ray generateRay(double x, double y) {
        // Calculate the direction of the ray from the camera's position to the
        // specified pixel
        Direction d = direction(x - width / 2.0,
                height / 2.0 - y,
                -distance());
        // Create and return a new ray with the calculated direction, originating from
        // the camera's position
        return new Ray(
                multiply(v, zero),
                multiply (v, normalize(d)),
                0,
                Double.POSITIVE_INFINITY);
    }

    /**
     * Calculates the distance between the camera and the image plane.
     *
     * @return The distance between the camera and the image plane.
     */
    public double distance() {
        return (width / 2.0) / Math.tan(fov / 2.0);
    }

}
