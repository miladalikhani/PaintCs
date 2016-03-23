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
        N = n;
        Length = length;
        Radius = length / (2 * Math.sin(Math.PI / n));
        xPoint = new int[n];
        yPoint = new int[n];
        for (int i = 0; i < n; i++) {
            xPoint[i] = ((int) (Radius * Math.sin(2 * Math.PI * i / n) + location.getX()));
            yPoint[i] = ((int) (Radius * (1 - Math.cos(2 * Math.PI * i / n)) + location.getY()));
        }
        this.awtShape = new java.awt.Polygon(xPoint, yPoint, n);
    }

    int getN() { return N; }

    double getLength() { return Length; }

    void setLength(double length) { Length = length; }

    double getRadius() { return Radius; }

    void resetRadius() { Radius = Length / (2 * Math.sin(Math.PI / N)); }

    @Override
    public void move(Point2D dR) {
        this.setLocation(new Point2D.Double(this.getLocation().getX() + dR.getX(), this.getLocation().getY() + dR.getY()));
        for (int i = 0; i < N; i++) {
            xPoint[i] += dR.getX();
            yPoint[i] += dR.getY();
        }
        this.awtShape = new java.awt.Polygon(xPoint, yPoint, N);
    }

    @Override
    public void moveTo(Point2D newLocation) {
        this.move(new Point2D.Double(newLocation.getX() - this.getLocation().getX(), newLocation.getY() - this.getLocation().getY()));
    }

    @Override
    public void scale(double k) {
        this.setLength(this.getLength() * k);
        this.resetRadius();
        for (int i = 0; i < N; i++) {
            xPoint[i] = ((int) (Radius * Math.sin(2 * Math.PI * i / N) + this.getLocation().getX()));
            yPoint[i] = ((int) (Radius * (1 - Math.cos(2 * Math.PI * i / N)) + this.getLocation().getY()));
        }
        this.awtShape = new java.awt.Polygon(xPoint, yPoint, N);
    }

    @Override
    public void rotate(double angle) {
        double X, Y;
        for (int i = 0; i < N; i++) {
            X = xPoint[i] - this.getLocation().getX();
            Y = yPoint[i] - this.getLocation().getY();
            xPoint[i] = ((int) (this.getLocation().getX() + X * Math.cos(angle * Math.PI / 180) + Y * Math.sin(angle * Math.PI / 180)));
            yPoint[i] = ((int) (this.getLocation().getY() - X * Math.sin(angle * Math.PI / 180) + Y * Math.cos(angle * Math.PI / 180)));
        }
        this.awtShape = new java.awt.Polygon(xPoint, yPoint, N);
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
        return this.awtShape.contains(point);
    }
}
