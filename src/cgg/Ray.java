package cgg;

import static cgtools.Vector.*;

import cgtools.Direction;
import cgtools.Point;

public record Ray(Point x0, Direction d, double tmin, double tmax){

    public Point pointAt(double t) {
        return add(x0, multiply(t, d));
    }

    public boolean isValid(double t) {
        return t >= tmin && t <= tmax;
    }
}