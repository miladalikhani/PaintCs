package Main;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Dot extends Shape {
    Dot (String name, Point2D location, Color border, int priority) {
        super(name, location, border, null, priority);
        this.awtShape = new Line2D.Double(location, location);
    }

    @Override
    public void changeFill(Color newFill) {}

    @Override
    public void move(Point2D dR, Graphics g) {
        setLocation(new Point2D.Double(this.getLocation().getX() + dR.getX(), this.getLocation().getY() + dR.getY()));
        this.awtShape = new Line2D.Double(this.getLocation().getX(), this.getLocation().getY(), this.getLocation().getX(), this.getLocation().getY());
        draw(g);
    }

    @Override
    public void rotate(double angle, Graphics g) {}

    @Override
    public void scale(double k, Graphics g) {}

    @Override
    public void moveTo(Point2D newLocation, Graphics g) {
        move(new Point2D.Double(newLocation.getX() - this.getLocation().getX(), newLocation.getY() - this.getLocation().getY()), g);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D G2d = ((Graphics2D) g);
        G2d.setPaint(Border);
        G2d.draw(awtShape);
        G2d.dispose();
    }

    @Override
    public boolean includes(Point2D point) {
        return this.getLocation().distance(point) <= 3;
    }
}
