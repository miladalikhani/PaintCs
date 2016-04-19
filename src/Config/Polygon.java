package Config;

import java.awt.*;
import java.awt.geom.*;

public class Polygon extends Shape {
    private int N;
    private double Length;
    private double Radius;
    private double[] xPoint;
    private double[] yPoint;
    private double rotateAngle = 0;

    public Polygon(String name, int n, int x, int y, int l, String borderColor, String fillColor, int priority) {
        this(name, n, new Point2D.Double(x, y), l, Color.decode("0x" + borderColor), Color.decode("0x" + fillColor), priority);
    }

    public Polygon(String name, int n, Point2D location, double length, Color border, Color fill, int priority) {
        super(name, location, border, fill, priority);
        N = n;
        Length = length;
        Radius = Math.abs(length) / (2 * Math.sin(Math.PI / n));
        xPoint = new double[n];
        yPoint = new double[n];
        for (int i = 0; i < n; i++) {
            xPoint[i] = ((int) (Radius * Math.sin(2 * Math.PI * i / n) + location.getX()));
            yPoint[i] = ((int) (Radius * Math.cos(2 * Math.PI * i / n) + location.getY()));
        }
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        this.awtShape = new java.awt.Polygon(x, y, n);
    }

    int getN() { return N; }

    double getLength() { return Length; }

    void setRadius(double radius) {
        scale(Math.abs(radius) / Radius);
    }

    double getRadius() { return Radius; }

    void resetRadius() { Radius = Length / (2 * Math.sin(Math.PI / N)); }

    public void setxPoint(double[] xPoint) {
        for (int i = 0; i < xPoint.length; i++) {
            this.xPoint[i] = xPoint[i];
        }
    }

    public void setyPoint(double[] yPoint) {
        for (int i = 0; i < yPoint.length; i++) {
            this.yPoint[i] = yPoint[i];
        }
    }

    @Override
    public void move(Point2D dR) {
        for (int i = 0; i < N; i++) {
            xPoint[i] += dR.getX();
            yPoint[i] += dR.getY();
        }
        int[] x = new int[N];
        int[] y = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        awtShape = new java.awt.Polygon(x, y, N);
        setLocation(new Point2D.Double(getLocation().getX() + dR.getX(), getLocation().getY() + dR.getY()));
    }

    @Override
    public void moveTo(Point2D newLocation) {
        this.move(new Point2D.Double(newLocation.getX() - this.getLocation().getX(), newLocation.getY() - this.getLocation().getY()));
    }

    @Override
    public void scale(double k) {
        for (int i = 0; i < N; i++) {
            xPoint[i] = (xPoint[i] - this.getLocation().getX()) * k + this.getLocation().getX();
            yPoint[i] = (yPoint[i] - this.getLocation().getY()) * k + this.getLocation().getY();
        }
        int[] x = new int[N];
        int[] y = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        Length *= k;
        resetRadius();
        this.awtShape = new java.awt.Polygon(x, y, N);
    }

    @Override
    public void rotate(double angle) {
        rotateAngle += angle;
        double X, Y;
        for (int i = 0; i < N; i++) {
            X = xPoint[i] - this.getLocation().getX();
            Y = yPoint[i] - this.getLocation().getY();
            xPoint[i] = this.getLocation().getX() + X * Math.cos(angle * Math.PI / 180) + Y * Math.sin(angle * Math.PI / 180);
            yPoint[i] = this.getLocation().getY() - X * Math.sin(angle * Math.PI / 180) + Y * Math.cos(angle * Math.PI / 180);
        }
        int[] x = new int[N];
        int[] y = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        this.awtShape = new java.awt.Polygon(x, y, N);
    }

    public void rotateTo(double angle) {
        rotate(angle - rotateAngle);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D G2d = ((Graphics2D) g);
        G2d.setStroke(new BasicStroke(2));
        G2d.setPaint(Fill);
        G2d.fill(awtShape);
        G2d.setPaint(Border);
        G2d.draw(awtShape);
    }

    @Override
    public boolean includes(Point2D point) {
        return this.awtShape.contains(point);
    }

    @Override
    public Polygon clone() {
        Polygon tmp = new Polygon(this.getName() + "_copy", N, this.getLocation(), this.getLength(), this.getBorder(), this.getFill(), this.getPriority());
        tmp.setxPoint(this.xPoint);
        tmp.setyPoint(this.yPoint);
        return tmp;
    }
}
