package cgg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cgtools.*;

public class Image_Paralel {

  private int width;
  private int height;
  private double[] data;
  private double gammaCorrection;

  // Constructor to initialize the Image object with width, height, and gamma
  // correction value
  public Image_Paralel(int width, int height, double gammaCorrection) {
    this.width = width;
    this.height = height;
    data = new double[width * height * 3];
    this.gammaCorrection = gammaCorrection;
  }

  // Method to set the pixel at the specified coordinates to the given color
  public void setPixel(int x, int y, Color color) {
    int index = 3 * (y * width + x);
    data[index + 0] = Math.pow(color.r(), 1 / gammaCorrection);
    data[index + 1] = Math.pow(color.g(), 1 / gammaCorrection);
    data[index + 2] = Math.pow(color.b(), 1 / gammaCorrection);
  }

  // Method to sample the image using the provided content (Sampler)
  public void sample(Sampler content) {
    int totalPixels = width * height;
    int currentPixel = 0;
    long startTime = System.nanoTime();

    record getColorCallable(Sampler content, int x, int y) implements Callable<Color> {
      @Override
      public Color call() throws Exception {
        return content.getColor(x, y);
      }
    }

    int cores = Runtime.getRuntime().availableProcessors();
    ExecutorService pool = Executors.newFixedThreadPool(cores);
    // Iterate over each pixel in the image
    List<Future<Color>> pixels = new ArrayList<>();
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        pixels.add(pool.submit(new getColorCallable(content, x, y)));
        
        
        // Time measurement
        currentPixel++;

        // Calculate the progress percentage and estimated remaining time
        int progressPercentage = (int) (((double) currentPixel / totalPixels) * 100);
        long elapsedTime = System.nanoTime() - startTime;
        long estimatedTimeRemaining = (long) ((elapsedTime / (double) currentPixel) * (totalPixels - currentPixel));

        // Build the progress bar string
        StringBuilder progressBar = new StringBuilder("[");
        for (int i = 0; i < 50; i++) {
          if (i < progressPercentage / 2) {
            progressBar.append("=");
          } else if (i == progressPercentage / 2) {
            progressBar.append(">");
          } else {
            progressBar.append(" ");
          }
        }
        progressBar.append("]");

        // Print the progress information
        System.out.print("\r" + "Sampling" + progressBar.toString() + " " + progressPercentage + "%"
            + " Time remaining: " + estimatedTimeRemaining / 1_000_000_000 + "s");
      
      }
      
    }
    currentPixel = 0;
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        Future<Color> pixel = pixels.get(x*height+y); // Korrekte Indexberechnung
    try {
      Color color = pixel.get();
      setPixel(x, y, color);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }


    // Time measurement
    currentPixel++;

    // Calculate the progress percentage and estimated remaining time
    int progressPercentage = (int) (((double) currentPixel / totalPixels) * 100);
    long elapsedTime = System.nanoTime() - startTime;
    long estimatedTimeRemaining = (long) ((elapsedTime / (double) currentPixel) * (totalPixels - currentPixel));

    // Build the progress bar string
    StringBuilder progressBar = new StringBuilder("[");
    for (int i = 0; i < 50; i++) {
      if (i < progressPercentage / 2) {
        progressBar.append("=");
      } else if (i == progressPercentage / 2) {
        progressBar.append(">");
      } else {
        progressBar.append(" ");
      }
    }
    progressBar.append("]");

    // Print the progress information
    System.out.print("\r" + "Set Pixel" + progressBar.toString() + " " + progressPercentage + "%"
        + " Time remaining: " + estimatedTimeRemaining / 1_000_000_000 + "s");
  
      }

      
    }

    pool.shutdown();

  }

  // Method to write the image to a file with the specified filename
  public void write(String filename) {
    // Use cgtools.ImageWriter.write() to implement this.
    ImageWriter.write(filename, data, width, height);
  }

}
