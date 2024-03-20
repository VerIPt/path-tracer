/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a02;

import java.util.Collections;
import cgtools.*;
import static cgtools.Vector.*;

import java.util.ArrayList;

public class ColoredDiscs implements Sampler {

  Color color;
  int radius;
  ArrayList<Disc> content;
  int circles;
  int width;
  int height;
  Color background = new Color(Math.random(), Math.random(), Math.random());

  public ColoredDiscs(int circles, int width, int height) {
    this.circles = circles;
    this.width = width;
    this.height = height;
    // creating arraylist for all circles with random color, size and position
    content = new ArrayList<Disc>(circles);
    for (int i = 0; i < circles; i++) {
      int radius = (int) (Math.random() * 100);
      int centerX = (int) (Math.random() * width);
      int centerY = (int) (Math.random() * height);
      color = new Color(Math.random(), 1, 1);
      content.add(new Disc(radius, centerX, centerY, hsvToRgb(color))); // hsvToRgb for better colorgradient
    }
    // sorting that every smaller disk comes in front of bigger
    Collections.sort(content);
  }

  // Returns the color for the given position.
  public Color getColor(double x, double y) {
    for (int i = 0; i < circles; i++) {
      if (content.get(i).isPointInDisc(x, y)) {
        //return multiply(content.get(i).getCenterX() / (width * 1.5), content.get(i).getColor());
        return content.get(i).getColor();
      }
    }
    return background;
  }
}
