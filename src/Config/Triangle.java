package Config;

import java.awt.*;
import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class Triangle extends Shape {
    private int[] xPoint = new int[3];
    private int[] yPoint = new int[3];

    public Triangle(String name, int x1, int y1, int x2, int y2, int x3, int y3, String borderColor, String fillColor, int priority) {
        this(name, new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(x3, y3), Color.decode("0x" + borderColor), Color.decode("0x" + fillColor), priority);
    }

    Triangle(String name, Point2D point1, Point2D point2, Point2D point3, Color border, Color fill, int priority) {
        super(name, new Point2D.Double((point1.getX() + point2.getX() + point3.getX()) / 3, (point1.getY() + point2.getY() + point3.getY()) / 3) {}, border, fill, priority);
        xPoint[0] = ((int) point1.getX());
        xPoint[1] = ((int) point2.getX());
        xPoint[2] = ((int) point3.getX());
        yPoint[0] = ((int) point1.getY());
        yPoint[1] = ((int) point2.getY());
        yPoint[2] = ((int) point3.getY());
        this.awtShape = new GeneralPath();
        this.awtShape = new Polygon(xPoint, yPoint, 3);
    }

    @Override
    public void move(Point2D dR) {
        ((Polygon) this.awtShape).translate(((int) dR.getX()), ((int) dR.getY()));
        xPoint = ((Polygon) this.awtShape).xpoints;
        yPoint = ((Polygon) this.awtShape).ypoints;
        this.setLocation(new Point2D.Double(this.getLocation().getX() + dR.getX(), this.getLocation().getY() + dR.getY()));
    }

    @Override
    public void moveTo(Point2D newLocation) {
        this.move(new Point2D.Double(newLocation.getX() - this.getLocation().getX(), newLocation.getY() - this.getLocation().getY()));
    }

    @Override
    public void rotate(double angle) {
        double X, Y;
        for (int i = 0; i < 3; i++) {
            X = xPoint[i] - this.getLocation().getX();
            Y = yPoint[i] - this.getLocation().getY();
            xPoint[i] = ((int) (this.getLocation().getX() + X * Math.cos(angle * Math.PI / 180) + Y * Math.sin(angle * Math.PI / 180)));
            yPoint[i] = ((int) (this.getLocation().getY() - X * Math.sin(angle * Math.PI / 180) + Y * Math.cos(angle * Math.PI / 180)));
        }
        this.awtShape = new Polygon(xPoint, yPoint, 3);
    }

    @Override
    public void scale(double k) {
        for (int i = 0; i < 3; i++) {
            xPoint[i] = ((int) (this.getLocation().getX() + (xPoint[i] - this.getLocation().getX()) * k));
            yPoint[i] = ((int) (this.getLocation().getY() + (yPoint[i] - this.getLocation().getY()) * k));
        }
        this.awtShape = new Polygon(xPoint, yPoint, 3);
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
