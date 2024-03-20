package cgg.a02;

import cgg.*;


public class Main {

  public static void main(String[] args) {
    final int width = 480;
    final int height = 270;
    final int samplingPoints = 16;
    final int circles = 100;
    final double gammmakorrektur = 2.2;

    // creating image of colored discs with sampling
    ColoredDiscs content = new ColoredDiscs(circles, width, height);
    Image_Paralel imageStratified = new Image_Paralel(width, height, gammmakorrektur);
    StratifiedSampler sampler2 = new StratifiedSampler(content, samplingPoints);
    imageStratified.sample(sampler2);

    // RandomSampler sampler = new RandomSampler(content, samplingPoints);
    // imageRandom.sample(sampler);
    // Image imageRandom = new Image(width, height, gammmakorrektur);

    // Write the image to disk.
    final String filename1 = "doc/a02-discs-supersampling.png";
    imageStratified.write(filename1);
    System.out.println("Wrote image: " + filename1);
    // Write the image to disk.
    // final String filename2 = "doc/a02-discs-supersampling_2.png";
    // imageRandom.write(filename2);
    // System.out.println("Wrote image: " + filename2);
  }
}