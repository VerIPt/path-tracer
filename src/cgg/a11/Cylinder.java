package cgg.a11;

import cgtools.*;
import static cgtools.Vector.*;

import cgg.Ray;
public record Cylinder(double radius, double height, Point center, Material m) implements Shape {
    public Hit intersect(Ray ray) {
        if (ray == null) {
            return null;
        }
        // Vector subtraction to find the vector from the ray's origin to the base center of the cylinder
        Direction x0 = Vector.subtract(ray.x0(), center);
        // Dot product to find the squared length of the ray's direction vector in the x-z plane
        double a = ray.d().x() * ray.d().x() + ray.d().z() * ray.d().z();
        // Dot product and scalar multiplication to find 2 times the dot product of x0 and the ray's direction vector in the x-z plane
        double b = 2 * (x0.x() * ray.d().x() + x0.z() * ray.d().z());
        // Dot product and scalar subtraction to find the squared length of x0 minus the squared radius of the cylinder in the x-z plane
        double c = x0.x() * x0.x() + x0.z() * x0.z() - radius * radius;
        // Discriminant of the quadratic equation
        double discriminant = b*b-4*a*c;
        double ymin = center.y();
        double ymax = ymin+height;
        // Check if the discriminant is negative (i.e., there are no real solutions for t)
        if (discriminant < 0) {
            return null;
        }
        // Quadratic formula to find the first solution for t
        double t1 = (-b-Math.sqrt(discriminant))/(2*a);
        // Quadratic formula to find the second solution for t
        double t2 = (-b+Math.sqrt(discriminant))/(2*a);
        // Check if t1 is valid (i.e., it lies on the ray)
        if (ray.isValid(t1)){
            Point p1 = ray.pointAt(t1);
            // Check if the intersection point is within the height of the cylinder
            if (p1.y() >= ymin && p1.y() <= ymax) {
                // Vector subtraction and scalar division to find the normal vector at the intersection point
                Direction d = new Direction(p1.x()-center.x(), 0, p1.z()-center.z());
                // Normalization of the normal vector
                Vector.normalize(d);
                // Creation of a new Hit object with the calculated intersection point, normal vector, and material
                return new Hit(t1, p1, d, m);
            }
            if(p1.y() == ymin){
                Plane downPlane = new Plane(center,new Direction(0,-1,0),radius,m);
                return downPlane.intersect(ray);
            }
            if(p1.y() == ymax){
                Point topCenter = new Point(center.x(), ymax, center.z());
                Plane upPlane = new Plane(topCenter,new Direction(0,1,0),radius,m);
                return upPlane.intersect(ray);
            }
        }
        // Check if t2 is valid (i.e., it lies on the ray)
        if (ray.isValid(t2)){
            Point p2 = ray.pointAt(t2);
            // Check if the intersection point is within the height of the cylinder
            if (p2.y() >= ymin && p2.y() <= ymax) {
                // Vector subtraction and scalar division to find the normal vector at the intersection point
                Direction d = new Direction(p2.x()-center.x(), 0, p2.z()-center.z());
                // Normalization of the normal vector
                Vector.normalize(d);
                // Creation of a new Hit object with the calculated intersection point, normal vector, and material
                return new Hit(t2, p2, d, m);
            }
            if(p2.y() == ymin){
                Plane downPlane = new Plane(center,new Direction(0,-1,0),radius,m);
                return downPlane.intersect(ray);
            }
            if(p2.y() == ymax){
                Point topCenter = new Point(center.x(), ymax, center.z());
                Plane upPlane = new Plane(topCenter,new Direction(0,1,0),radius,m);
                return upPlane.intersect(ray);
            }
        }
        // Return null if there is no valid intersection point
        return null;
    }
    //@Override
    //public BoundingBox bounds() {
    //    Point min = new Point(center.x()-radius, center.y(), center.z()-radius);
    //    Point max = new Point(center.x()+radius, center.y() + height, center.z()+radius);
    //    return new BoundingBox(min, max);
    //} 

    @Override
    public BoundingBox bounds() {
        return new BoundingBox(
            point(center.x() - radius, center.y(), center.z() - radius),
            point(center.x() + radius, center.y() + height, center.z() + radius));
        
    }
}