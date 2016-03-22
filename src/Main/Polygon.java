package Main;

import java.awt.*;
import java.awt.geom.Point2D;

public class Polygon extends Shape {
    private int N;
    private double Length;
    private double Radius;

    Polygon(String name, int n, Point2D location, double length, Color border, Color fill, int priority) {
        super(name, location, border, fill, priority);
        this.N = n;
        this.Length = length;
        this.Radius = length / (2 * Math.sin(180 / n));
        for (int i = 0; i < n; i++) {
            this.Point.add(new Point2D.Double(this.Radius * Math.sin(360 * i / n), this.Radius * (1 - Math.cos(360 * i / n))));
        }
        //this.Point.add(this.Point.get(0));    //if we want to draw by lines
        //A = ?;
        //B = ?;
    }

    int getN() { return this.N; }

    double getLength() { return this.Length; }

    void setLength(double length) { this.Length = length; }

    double getRadius() { return this.Radius; }

    void setRadius(double radius) { this.Radius = radius; }

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
        this.setRadius(this.getRadius() * k);
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
