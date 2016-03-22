package Main;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {
    Rectangle(String name, Point2D location, double a, double b, Color border, Color fill, int priority) {
        super(name, location, border, fill, priority);
//        this.awtShape = new java.awt.Rectangle(((int) (location.getX() - a / 2)), ((int) (location.getY() - b / 2)), ((int) a), ((int) b));
        this.awtShape = new Rectangle2D.Double(location.getX() - a / 2, location.getY() - b / 2, a, b);
    }

    @Override
    public void Scale(double k) {

    }

    @Override
    public void Move(Point2D newLocation) {
        this.setLocation(newLocation);
    }

    @Override
    public void Rotate(double angle) {
        this.setAngle(this.getAngle() + angle);
    }
}
