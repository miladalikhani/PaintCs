package Main;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Circle extends Shape {
    private double Radius;

    Circle(String name, Point2D location, double radius, Color border, Color fill, int priority) {
        super(name, location, border, fill, priority);
        this.setRadius(radius);
        this.awtShape = new Ellipse2D.Double(location.getX() - radius, location.getY() - radius, 2 * radius, 2* radius);
    }

    public double getRadius() { return Radius; }

    public void setRadius(double radius) { Radius = radius; }

    @Override
    public void Scale(double k) {
        this.setRadius(this.getRadius() * k);
    }

    @Override
    public void Rotate(double angle) {}

    @Override
    public void Move(Point2D newLocation) {

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D G2d = ((Graphics2D) g);
        G2d.setPaint(Fill);
        G2d.fill(awtShape);
        G2d.setPaint(Border);
        G2d.draw(awtShape);
    }

    //TODO: Override "include" method
}
