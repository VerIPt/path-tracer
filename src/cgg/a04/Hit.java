package cgg.a04;


import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public record Hit(double t, Point x, Direction n, Color c) implements Comparable<Hit> {

    public static Hit closest(Hit h1, Hit h2) {
        if (h1 == null && h2 == null) {
            return null;
        } else if (h1 == null) {
            return h2;
        } else if (h2 == null) {
            return h1;
        } else if (h1.t() < h2.t()) {
            return h1;
        } else {
            return h2;
        }
    }

    public String toString() {
        return String.format("(Hit: %.2f %s %s %s)", t, x, n, c);
    }

    @Override
public int compareTo(Hit hit) {
    if (this.t() < hit.t()) {
        return -1;
    } else if (this.t() > hit.t()) {
        return 1;
    } else {
        // Vergleiche anhand des hashCode der Hit-Objekte, um sicherzustellen, dass sie unterschiedlich sind
        return Integer.compare(this.hashCode(), hit.hashCode());
    }
}


}
