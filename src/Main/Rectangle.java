package Main;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape { //TODO: private array Point
    Rectangle(String name, Point2D location, double a, double b, Color border, Color fill, int priority) {
        super(name, location, border, fill, priority);
//        this.awtShape = new java.awt.Rectangle(((int) (location.getX() - a / 2)), ((int) (location.getY() - b / 2)), ((int) a), ((int) b));
        this.awtShape = new Rectangle2D.Double(location.getX() - a / 2, location.getY() - b / 2, a, b);
    }

    @Override
    public void Scale(double k) { //TODO: Graphics, Change Point

    }

    @Override
    public void Move(Point2D newLocation) { //TODO: Graphics, Change Point
        this.setLocation(newLocation);
    }

    @Override
    public void Rotate(double angle) { //TODO: Graphics, Change Point
        this.setAngle(this.getAngle() + angle);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D G2d = ((Graphics2D) g);
        G2d.setPaint(Fill);
        G2d.fill(awtShape);
        G2d.setPaint(Border);
        G2d.draw(awtShape);
    }

    //TODO: Override "includes" method
}
