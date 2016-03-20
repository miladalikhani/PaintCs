package Main;

import java.awt.*;
import java.awt.geom.Point2D;

public class Rectangle extends Shape {
    Rectangle(String name, Point2D location, double a, double b, Color border, Color fill, int priority) {
        super(name, location, border, fill, priority);
        this.setA(a);
        this.setB(b);
        this.Point.add(new Point2D.Double(location.getX() - a/2, location.getY() - b/2));
        this.Point.add(new Point2D.Double(location.getX() + a/2, location.getY() - b/2));
        this.Point.add(new Point2D.Double(location.getX() + a/2, location.getY() + b/2));
        this.Point.add(new Point2D.Double(location.getX() - a/2, location.getY() + b/2));
        //add first point if we want draw rectangle with lines
        //this.Point.add(new Point2D.Double(location.getX() - a/2, location.getX() - b/2));
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
        this.setA(this.getA() * k);
        this.setB(this.getB() * k);
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
}
