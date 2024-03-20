/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools;

// See
// https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Record.html
// for details on the record data type.
public record Color(double r, double g, double b) {
  
  @Override
  public String toString() {
    return String.format("(Col: %.2f %.2f %.2f)", r, g, b);
  }
}
