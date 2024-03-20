package cgg.a07;

import static cgtools.Matrix.invert;
import static cgtools.Matrix.multiply;
import static cgtools.Matrix.transpose;

import cgtools.Matrix;
import cgg.Ray;

public class Transformation {
	
	public final Matrix matrix;
	public final Matrix inverse;
	public final Matrix inverseTranspose;
	
	public Transformation(Matrix matrix) {
		this.matrix = matrix;
		this.inverse = invert(matrix);
		this.inverseTranspose = transpose(inverse);
	}
	
	public Ray transformRay(Ray ray) {
		return new Ray(multiply(inverse, ray.x0()), multiply(inverse, ray.d()), ray.tmin(), ray.tmax());
	}
	
	public Hit transformHit(Hit hit) {
		if (hit != null) {
			return new Hit(hit.t(), multiply(matrix, hit.x()), multiply(inverseTranspose, hit.n()), hit.m());
		} else {
			return null;
		}
	}

}