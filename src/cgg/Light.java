package cgg;

import cgg.a11.Hit;
import cgg.a11.Shape;
import cgtools.Color;

public interface Light {
    public Color incomingIntensity(Hit hit, Shape scene);
}
