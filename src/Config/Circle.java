package Config;

import java.awt.*;
import java.awt.geom.*;

public class Circle extends Shape {
    private double Radius;

    public Circle(String name, int x, int y, int r, String borderColor, String fillColor, int priority) {
        this(name, new Point2D.Double(x, y), r, Color.decode("0x" + borderColor), Color.decode("0x" + fillColor), priority);
    }

    Circle(String name, Point2D location, double radius, Color border, Color fill, int priority) {
        super(name, location, border, fill, priority);
        this.setRadius(radius);
        this.awtShape = new Ellipse2D.Double(location.getX() - radius, location.getY() - radius, 2 * radius, 2* radius);
    }

    public double getRadius() { return Radius; }

    public void setRadius(double radius) { Radius = radius; }

    @Override
    public void scale(double k) {
        this.setRadius(this.getRadius() * k);
        this.awtShape = new Ellipse2D.Double(this.getLocation().getX() - Radius, this.getLocation().getY() - Radius, 2 * Radius, 2 * Radius);
    }

    @Override
    public void rotate(double angle) {}

    @Override
    public void move(Point2D dR) {
        this.setLocation(new Point2D.Double(this.getLocation().getX() + dR.getX(), this.getLocation().getY() + dR.getY()));
        this.awtShape = new Ellipse2D.Double(this.getLocation().getX() - Radius, this.getLocation().getY() - Radius, 2 * Radius, 2 * Radius);
    }

    @Override
    public void moveTo(Point2D newLocation) {
        move(new Point2D.Double(newLocation.getX() - this.getLocation().getX(), newLocation.getY() - this.getLocation().getY()));
    }

    @Override
    public void draw(Graphics g) {
//        System.out.println(getName());
        Graphics2D G2d = ((Graphics2D) g);
        G2d.setStroke(new BasicStroke(2));
        G2d.setPaint(Fill);
        G2d.fill(awtShape);
        G2d.setPaint(Border);
        G2d.draw(awtShape);
    }

    @Override
    public boolean includes(Point2D point) {
        return this.awtShape.contains(point);
    }

    @Override
    public Circle clone() {
        Circle tmp = new Circle(this.getName() + "_copy", this.getLocation(), this.getRadius(), this.getBorder(), this.getFill(), this.getPriority());
        return tmp;
    }
}
