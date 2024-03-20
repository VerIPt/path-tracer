/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import static cgtools.Vector.*;
import cgg.*;
import cgtools.*;

public class Main {

  public static void main(String[] args) {
    final int width = 480;
    final int height = 270;
    final Color color1 = red;
    final Color color2 = blue;
    final Color color3 = green;
    final Color background = black;
    final double gammmakorrektur = 2.2;

    int divider = 5;
    int lineWidth = 10;
    int radius = 15;

    ChessBoardWithCircles chessBoardWithCircles = new ChessBoardWithCircles(color1, color2, radius, color3, lineWidth,
        background);
    Image_Paralel image1 = new Image_Paralel(width, height, gammmakorrektur);
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        image1.setPixel(x, y, chessBoardWithCircles.getColor(x, y, width, height, divider));
      }
    }

    // Write the image to disk.
    final String filename1 = "doc/a01-pattern.png";
    image1.write(filename1);
    System.out.println("Wrote image: " + filename1);

  }
}
