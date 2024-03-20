package cgg.a07;

import cgg.Ray;
import cgtools.Matrix;
import java.util.ArrayList;
import java.util.List;

import static cgtools.Matrix.identity;


public class Group implements Shape {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private final Transformation transform;

    public Group(Shape... shapes) {
        this(identity, shapes);
    }

    public Group(List<Shape> list) {
        this(list.toArray(new Shape[list.size()]));
    }

    public Group(Matrix matrix, Shape... shapes) {
        this.shapes = new ArrayList<>();
        this.transform = new Transformation(matrix);
        for (Shape s : shapes) {
            addShape(s);
        }
    }
    public void addShape(Shape s){
        if(shapes == null){
            shapes = new ArrayList<>();
        }
        shapes.add(s);
    }

    public Hit intersect(Ray ray) {
        Hit closest = null;
        if (ray != null) {
            ray = transform.transformRay(ray);
        }
        for (var s : shapes) {
            // Intersect the ray with each shape in the group
            closest = Hit.closest(closest, s.intersect(ray));
        }
        if (closest != null) {
            closest = transform.transformHit(closest);
        }
        return closest;
    }
}
