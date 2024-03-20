package cgg.a01;

import cgtools.*;

public class RegularPattern {
    Color color1;
    Color color2;

    RegularPattern(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
    }

    public Color getColor(double x, double y, int width, int height, int lineWidth) {
        // Berechnung der Linien
        int x0 = width / 2;
        int y0 = 0;
        double quadLine0 = (x0 - x) * (x0 - x) + (y0 - y) * (y0 - y);
        int y1 = height / 2;
        int x1 = 0;
        double quadLine1 = (x1 - x) * (x1 - x) + (y1 - y) * (y1 - y);
        int x2 = width / 2;
        int y2 = height;
        double quadLine2 = (x2 - x) * (x2 - x) + (y2 - y) * (y2 - y);
        int x3 = width;
        int y3 = height / 2;
        double quadLine3 = (x3 - x) * (x3 - x) + (y3 - y) * (y3 - y);

        // Berechnung der Abstände des Punktes zu den Linien
        double dist0 = Math.abs(((x1 - x) * (y - y0) - (x - x0) * (y1 - y)) / Math.sqrt(quadLine1));
        double dist1 = Math.abs(((x2 - x) * (y - y1) - (x - x1) * (y2 - y)) / Math.sqrt(quadLine2));
        double dist2 = Math.abs(((x3 - x) * (y - y2) - (x - x2) * (y3 - y)) / Math.sqrt(quadLine3));
        double dist3 = Math.abs(((x0 - x) * (y - y3) - (x - x3) * (y0 - y)) / Math.sqrt(quadLine0));

        // Überprüfung, ob der Punkt innerhalb von 5 Pixeln der Linien liegt
        if (dist0 <= lineWidth || dist1 <= lineWidth || dist2 <= lineWidth || dist3 <= lineWidth) {
            return color1;
        } else {
            return color2;
        }
    }
}
