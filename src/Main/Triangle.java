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
            ((GeneralPath) this.awtShape).lineTo(this.Point.get(i).getX(), this.Point.get(i).getY());
        }
    }

    @Override
    public void Move(Point2D newLocation) {
        for (int i = 0; i < this.Point.size(); i++) {
            double X = this.Point.get(i).getX() - this.getLocation().getX();
            double Y = this.Point.get(i).getY() - this.getLocation().getY();
            this.Point.get(i).setLocation(X + newLocation.getX(), Y + newLocation.getY());
        }
        this.setLocation(newLocation);
    }

    @Override
    public void Rotate(double angle) {
        this.setAngle(this.getAngle() + angle);
        for (int i = 0; i < this.Point.size(); i++) {
            double X = this.Point.get(i).getX() - this.getLocation().getX();
            double Y = this.Point.get(i).getY() - this.getLocation().getY();
            this.Point.get(i).setLocation(X * Math.cos(angle) - Y * Math.sin(angle) + this.getLocation().getX(), X * Math.sin(angle) + Y * Math.cos(angle) + this.getLocation().getY());
        }
    }

    @Override
    public void Scale(double k) {
        for (int i = 0; i < this.Point.size(); i++) {
            double X = this.Point.get(i).getX() - this.getLocation().getX();
            double Y = this.Point.get(i).getY() - this.getLocation().getY();
            X = k * X;
            Y = k * Y;
            this.Point.get(i).setLocation(X, Y);
        }
    }
}
