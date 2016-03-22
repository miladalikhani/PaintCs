package Main;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Triangle extends Shape {
    private ArrayList<Point2D> Point = new ArrayList<>();

    Triangle(String name, Point2D point1, Point2D point2, Point2D point3, Color border, Color fill, int priority) {
        super(name, new Point2D.Double((point1.getX() + point2.getX() + point3.getX()) / 3, (point1.getY() + point2.getY() + point3.getY()) / 3) {}, border, fill, priority);
        Point.add(point1);
        Point.add(point2);
        Point.add(point3);
        Point.add(point1);
        this.awtShape = new GeneralPath();
        ((GeneralPath) this.awtShape).moveTo(point1.getX(), point1.getY());
        for (int i = 1; i < this.Point.size(); i++) {
            ((GeneralPath) this.awtShape).lineTo(this.Point.get(i).getX(), Point.get(i).getY());
        }
    }

    @Override
    public void Move(Point2D newLocation) { //TODO: Graphics, Change Point
        this.setLocation(newLocation);
    }

    @Override
    public void Rotate(double angle) { //TODO: Graphics, Change Point
    }

    @Override
    public void Scale(double k) { //TODO: Graphics, Change Point
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D G2d = ((Graphics2D) g);
        G2d.setPaint(Fill);
        G2d.fill(awtShape);
        G2d.setPaint(Border);
        G2d.draw(awtShape);
    }

    @Override
    public boolean includes(Point2D point) {
        double X1, Y1, X2, Y2;
        for (int i = 0; i < 2; i++) {
            X1 = Point.get(i + 1).getX() - Point.get(i).getX();
            Y1 = Point.get(i + 1).getY() - Point.get(i).getY();
            X2 = Point.get(i).getX() - point.getX();
            Y2 = Point.get(i).getY() - point.getY();
            if (X1 * Y2 > X2 * Y1) return false;
        }
        return true;
    }
}
