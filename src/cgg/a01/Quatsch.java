package cgg.a01;
import cgtools.*;
public class Quatsch {
    Color color1;
    Color color2;

    Quatsch(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
    }

    public Color getColor (double x, double y, int width, int height, int divider) {
        // calculating middle of the image
        int centerX = width / 2;
        int centerY = height / 2;
        
        // calculating the width and height of the diamond
        int diamondWidth = width - 2 * 120;
        int diamondHeight = height - 2 * 120;
        
        // calculating the distance of the pixel to the center of the diamond
        double diamondY = Math.abs(y - centerY);
        double diamondX = Math.abs(x - centerX);
        
        int square = height / divider;


        // checking square in which x and y is located
        int squareX = (int) (x / square);
        int squareY = (int) (y / square);

        boolean isEven = (squareX + squareY) % 2 == 0;

        // checking if the pixel is in the diamond and set color
        if (diamondX / diamondWidth + diamondY / diamondHeight <= 1 && isEven) {
            return color2;
        } else {
            return color1;
        }
        
    }        
}