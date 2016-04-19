package Config;

import java.awt.*;
import java.awt.geom.*;

public class Line extends Shape {
    private Point2D.Double[] Point = new Point2D.Double[2];

    public Line(String name, int x1, int y1, int x2, int y2, String color, int priority) {
        this(name, new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), Color.decode("0x" + color), priority);
    }

    Line (String name, Point2D point1, Point2D point2, Color border, int priority) {
        super (name, new Point2D.Double ((point1.getX() + point2.getX()) / 2, (point1.getY() + point2.getY()) / 2), border, null, priority);
        Point[0] = (Point2D.Double) point1;
        Point[1] = (Point2D.Double) point2;
        this.awtShape = new Line2D.Double(point1, point2);
    }

    @Override
    public void move(Point2D dR) {
        Point[0].setLocation(Point[0].getX() + dR.getX(), Point[0].getY() + dR.getY());
        Point[1].setLocation(Point[1].getX() + dR.getX(), Point[1].getY() + dR.getY());
        this.setLocation(new Point2D.Double(this.getLocation().getX() + dR.getX(), this.getLocation().getY() + dR.getY()));
        this.awtShape = new Line2D.Double(Point[0], Point[1]);
    }

    @Override
    public void changeFill(Color newFill) {}

    @Override
    public void rotate(double angle) {
        double X = Point[0].getX() - this.getLocation().getX();
        double Y = Point[0].getY() - this.getLocation().getY();
        Point[0].setLocation(this.getLocation().getX() + X * Math.cos(angle * Math.PI / 180) + Y * Math.sin(angle * Math.PI / 180), this.getLocation().getY() - X * Math.sin(angle * Math.PI / 180) + Y * Math.cos(angle * Math.PI / 180));
        X = Point[1].getX() - this.getLocation().getX();
        Y = Point[1].getY() - this.getLocation().getY();
        Point[1].setLocation(this.getLocation().getX() + X * Math.cos(angle * Math.PI / 180) + Y * Math.sin(angle * Math.PI / 180), this.getLocation().getY() - X * Math.sin(angle * Math.PI / 180) + Y * Math.cos(angle * Math.PI / 180));
        this.awtShape = new Line2D.Double(Point[0], Point[1]);
    }

    @Override
    public void scale(double k) {
        double X = Point[0].getX() - this.getLocation().getX();
        double Y = Point[0].getY() - this.getLocation().getY();
        Point[0].setLocation(this.getLocation().getX() + X * k, this.getLocation().getY() + Y * k);
        X = Point[1].getX() - this.getLocation().getX();
        Y = Point[1].getY() - this.getLocation().getY();
        Point[1].setLocation(this.getLocation().getX() + X * k, this.getLocation().getY() + Y * k);
        this.awtShape = new Line2D.Double(Point[0], Point[1]);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D G2d = ((Graphics2D) g);
        G2d.setPaint(Border);
        G2d.draw(awtShape);
    }

    @Override
    public void moveTo(Point2D newLocation) {
        move(new Point2D.Double(newLocation.getX() - this.getLocation().getX(), newLocation.getY() - this.getLocation().getY()));
    }

    @Override
    public boolean includes(Point2D point) {
        return Point[0].distance(point) + Point[1].distance(point) <= 3;
    }
}
