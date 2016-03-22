package Main;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Circle extends Shape {
    private double Radius;

    Circle(String name, Point2D location, double radius, Color border, Color fill, int priority) {
        super(name, location, border, fill, priority);
        this.setRadius(radius);
        this.setA(2 * radius);
        this.setB(2 * radius);
        this.Point = null;
    }

    public double getRadius() { return Radius; }

    public void setRadius(double radius) { Radius = radius; }

    @Override
    public void Scale(double k) {
        this.setRadius(this.getRadius() * k);
    }

    @Override
    public void Rotate(double angle) {}
}
