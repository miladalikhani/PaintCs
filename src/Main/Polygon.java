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
    public void Move(Point2D newLocation) {
        this.setLocation(newLocation);
    }

    @Override
    public void Scale(double k) {
        this.setRadius(this.getRadius() * k);
    }

    @Override
    public void Rotate(double angle) {
        this.setAngle(this.getAngle() + angle);
    }
}
