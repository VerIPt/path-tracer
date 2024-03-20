package cgg.a10;

import cgtools.Direction;
import cgtools.Point;
import static cgtools.Vector.*;

public record Hit(double t, Point x, Direction n, Point uv, Material m) implements Comparable<Hit> {
    
    public Hit(double t, Point x, Direction n, Material m) {
        this(t, x, n, zero, m);
    }
    
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
        return String.format("(Hit: %.2f %s %s %s)", t, x, n, m);
    }

    @Override
    public int compareTo(Hit hit) {
        if (this.t() < hit.t()) {
            return -1;
        } else if (this.t() > hit.t()) {
            return 1;
        } else {
            // Vergleiche anhand des hashCode der Hit-Objekte, um sicherzustellen, dass sie
            // unterschiedlich sind
            return Integer.compare(this.hashCode(), hit.hashCode());
        }
    }

}
