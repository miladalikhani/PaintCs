package Main;

import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.management.MemoryPoolMXBean;

public class Polygon extends Shape {
    private int N;
    private double Length;
    private double Radius;
    private int[] xPoint;
    private int[] yPoint;

    Polygon(String name, int n, Point2D location, double length, Color border, Color fill, int priority) {
        super(name, location, border, fill, priority);
        this.N = n;
        this.Length = length;
        this.Radius = length / (2 * Math.sin(180 / n));
        xPoint = new int[n];
        yPoint = new int[n];
        for (int i = 0; i < n; i++) {
            xPoint[i] = ((int) (this.Radius * Math.sin(360 * i / n)));
            yPoint[i] = ((int) (this.Radius * (1 - Math.cos(360 * i / n))));
        }
        this.awtShape = new java.awt.Polygon(xPoint, yPoint, n);
    }

    int getN() { return this.N; }

    double getLength() { return this.Length; }

    void setLength(double length) { this.Length = length; }

    double getRadius() { return this.Radius; }

    void setRadius(double radius) { this.Radius = radius; }

    @Override
    public void Move(Point2D newLocation) { //TODO: Graphics, Change xPoint & yPoint
        this.setLocation(newLocation);
    }

    @Override
    public void Scale(double k) { //TODO: Graphics, Change xPoint & yPoint
        this.setRadius(this.getRadius() * k);
    }

    @Override
    public void Rotate(double angle) { //TODO: Graphics, Change xPoint & yPoint
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

    @Override
    public boolean includes(Point2D point) {
        double X1, Y1, X2, Y2;
        for (int i = 0; i < 2; i++) {
            X1 = xPoint[i + 1] - xPoint[i];
            Y1 = yPoint[i + 1] - yPoint[i];
            X2 = xPoint[i] - point.getX();
            Y2 = yPoint[i] - point.getY();
            if (X1 * Y2 > X2 * Y1) return false;
        }
        return true;
    }
}
