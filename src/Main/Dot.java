package Main;

import java.awt.*;
import java.awt.geom.Point2D;

public class Dot extends Shape {
    Dot(String name, Point2D location, Color border, int priority) {
        super(name, location, border, null, priority);
        this.setA(3);
        this.setB(3);
        this.Point.add (location);
    }

    @Override
    public void Rotate(double angle) {}

    @Override
    public void Scale(double k) {}
}
