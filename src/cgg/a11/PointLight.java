package cgg.a11;

import cgtools.*;
import cgg.Light;
import cgg.Ray;


public record PointLight(Point sourcePoint, Color lightColor, double radius) implements Light {

    public Color incomingIntensity(Hit hit, Shape scene) {
        Direction posToLight = Vector.subtract(sourcePoint, hit.x());
        double t = Vector.length(posToLight);
        Ray lightRay = new Ray(hit.x(), Vector.normalize(posToLight), 0.00001, t);
        Hit lightHit = scene.intersect(lightRay);
        if (lightHit != null && lightHit.t() < Double.POSITIVE_INFINITY) {
            return Vector.black;
        }
        // Lambertsche Gleichung

        // |p - x|^2
        double nominator = (t / radius) * (t / radius);
        // p-x normalized * n
        posToLight = Vector.normalize(posToLight);
        double rightTerm = Vector.dotProduct(hit.n(), posToLight);
        double fraction = 1 / nominator;
        if (t > radius) {
            fraction = Math.pow(fraction, 1 / fraction);
        }
        return Vector.multiply(fraction * rightTerm, lightColor);
    }
}
