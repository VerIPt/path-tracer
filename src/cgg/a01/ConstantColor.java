/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import cgtools.*;

public record ConstantColor(Color color, int width, int heigth) {

// Represents the contents of an image. Provides the same color for all pixels.

  // Returns the color for the given position.
  public Color getColor(double x, double y) {
    int xi = (int) (x / (width / 8));
    int yi = (int) (y / (heigth / 8));
    double u = ((x / width / 8) - xi);
    double v = ((y / heigth / 8) - yi);

    if (u + v < 1){
      if ((xi + yi) % 2 == 0 ){
       return color;
        // return multiply(width, color);
      }
      //else return multiply(heigth, color);
    }
    else {
      return color;
  }
    return color;
  }
}

 
 