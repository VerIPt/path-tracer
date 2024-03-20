package test;

import org.junit.Test;

import cgg.Ray;
import cgg.a03.Sphere;
import cgg.a03.Hit;
import static org.junit.Assert.*;
import cgtools.*;

public class test {
    @Test
    public void testHit01() {
        Sphere sphere = new Sphere(1, new Point(0, 0, -2), new Color(1, 1, 1));
        Ray ray = new Ray(new Point(0, 0, 0), new Direction(0, 0, -1), 0, Double.POSITIVE_INFINITY);
        Hit hit = sphere.intersectRay(ray);
        Direction directionTest = new Direction(0, 0, 1);
        Point pointTest = new Point(0, 0, -1);
        assertEquals(hit.n(), directionTest);
        assertEquals(hit.x(), pointTest);
    }

    @Test
    public void testHit02() {
        Sphere sphere = new Sphere(1, new Point(0, 0, -2), new Color(1, 1, 1));
        Ray ray = new Ray(new Point(0, 0, 0), new Direction(0, 1, -1), 0, Double.POSITIVE_INFINITY);
        Hit hit = sphere.intersectRay(ray);
        assertEquals(hit, null);
    }

    @Test
    public void testHit03() {
        Sphere sphere = new Sphere(1, new Point(0, -1, -2), new Color(1, 1, 1));
        Ray ray = new Ray(new Point(0, 0, 0), new Direction(0, 0, -1), 0, Double.POSITIVE_INFINITY);
        Hit hit = sphere.intersectRay(ray);
        Direction directionTest = new Direction(0, 1, 0);
        Point pointTest = new Point(0, 0, -2);
        assertEquals(hit.n(), directionTest);
        assertEquals(hit.x(), pointTest);
    }

    @Test
    public void testHit04() {
        Sphere sphere = new Sphere(1, new Point(0, 0, -2), new Color(1, 1, 1));
        Ray ray = new Ray(new Point(0, 0, -4), new Direction(0, 1, -1), 0, Double.POSITIVE_INFINITY);
        Hit hit = sphere.intersectRay(ray);
        assertEquals(hit, null);
    }

    @Test
    public void testHit05() {
        Sphere sphere = new Sphere(1, new Point(0, 0, -4), new Color(1, 1, 1));
        Ray ray = new Ray(new Point(0, 0, 0), new Direction(0, 0, -1), 0, 2);
        Hit hit = sphere.intersectRay(ray);
        assertEquals(hit, null);
    }
}
