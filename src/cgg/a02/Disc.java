package cgg.a02;

import cgtools.*;

public class Disc implements Comparable<Disc> {

    private double radius;
    Color color;
    private double centerX;
    private double centerY;

    public Disc(double radius, double centerX, double centerY, Color color) {
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
        this.color = color;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getRadius() {
        return radius;
    }

    public double getCenterY() {
        return centerY;
    }

    public Color getColor() {
        return color;
    }

    // Circlefunction for checkting if pixel is in disc
    public boolean isPointInDisc(double x, double y) {
        double distanceToCenter = Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2));
        boolean isInCircle = distanceToCenter <= radius;
        return isInCircle;
    }

    // Comparator for sortable discs
    public int compareTo(Disc compaCircle) {
        if (this.radius > compaCircle.getRadius()) {
            return 1;
        } else if (this.radius < compaCircle.getRadius()) {
            return -1;
        } else {
            return 0;
        }
    }
}