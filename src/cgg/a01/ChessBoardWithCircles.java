package cgg.a01;

import cgtools.*;


public class ChessBoardWithCircles {
    private Color color1;
    private Color color2;
    private double radius;
    private Color circleColor;
    private Color background;
    private int lineWidth;
    

    ChessBoardWithCircles(Color color1, Color color2, double radius, Color circleColor, int lineWidth, Color background) {
        this.color1 = color1;
        this.color2 = color2;
        this.radius = radius;
        this.circleColor = circleColor;
        this.lineWidth = lineWidth;
        this.background = background;
        
    }

    public Color getColor(double x, double y, int width, int height, int divider) {

        // calc. of the lines from color1
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

        // calc. the distance of the point to the lines from color1
        double dist0 = Math.abs(((x1 - x) * (y - y0) - (x - x0) * (y1 - y)) / Math.sqrt(quadLine1));
        double dist1 = Math.abs(((x2 - x) * (y - y1) - (x - x1) * (y2 - y)) / Math.sqrt(quadLine2));
        double dist2 = Math.abs(((x3 - x) * (y - y2) - (x - x2) * (y3 - y)) / Math.sqrt(quadLine3));
        double dist3 = Math.abs(((x0 - x) * (y - y3) - (x - x3) * (y0 - y)) / Math.sqrt(quadLine0));
        
        // setting instance of raster
        int square = height / divider;

        // calculating middle of the image
        int centerXx = width / 2;
        int centerYy = height / 2;

        // calculating the width and height of the diamond
        int diamondWidth = width - 2 * 50;
        int diamondHeight = height - 2 * 50;
    
        // checking square in which x and y is located
        int squareX = (int) (x / square);
        int squareY = (int) (y / square);

        // calculating the distance of the pixel to the center of the diamond
        double diamondY = Math.abs(y - centerYy);
        double diamondX = Math.abs(x - centerXx);
        
        boolean isEven = (squareX + squareY) % 2 == 0;
    
        // determining the center of the square
        double centerX = squareX * square + square / 2.0;
        double centerY = squareY * square + square / 2.0;
    
        // checking if the center of the cell is closer to the center of the circle than the radius
        double distanceToCenter = Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2));
        boolean isInCircle = distanceToCenter <= radius;


        // Überprüfung, ob der Punkt innerhalb von 25 Pixeln der Linien liegt
        if (dist0 <= lineWidth || dist1 <= lineWidth || dist2 <= lineWidth || dist3 <= lineWidth) {
            return color1;
        }
        // setzten der umgebungsraute des schachbretts
        if (diamondX / diamondWidth + diamondY / diamondHeight <= 1 && isEven) {
        
            // setting the circle
            if (isInCircle) {
                return circleColor;
            }
            return color2;
        }

        return background;
    }
}    