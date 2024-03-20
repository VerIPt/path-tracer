package cgg.a09;

import cgg.Ray;
import cgtools.BoundingBox;
import cgtools.Matrix;
import java.util.ArrayList;
import java.util.List;

import static cgtools.Matrix.identity;

public class Group implements Shape {
    ArrayList<Shape> shapes = new ArrayList<>();
    private final Transformation transform;
    private BoundingBox bb;

    public Group(Shape... shapes) {
        this(identity, shapes);
        bb = calcBB();
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
        bb = calcBB();

    }

    public void addShape(Shape s) {
        if (shapes == null) {
            shapes = new ArrayList<>();
        }

        shapes.add(s);
        bb = calcBB();
        
    }

    public Hit intersect(Ray ray) {
        
        if(!bb.intersect(ray)) {
            return null;
        }

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



    public BoundingBox calcBB() {
        BoundingBox bb = BoundingBox.empty;
        for (Shape s : shapes) {
            bb = bb.extend(s.bounds());
        }
        return bb.transform(transform.getMatrix());
    }
    

    @Override
    public BoundingBox bounds() {
        return bb;
    }
}
