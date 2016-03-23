package Config;

import java.awt.*;
import java.awt.Polygon;
import java.awt.geom.Point2D;

public class Rectangle extends Shape {
    private int[] xPoint;
    private int[] yPoint;

    public Rectangle(String name, int x, int y, int a, int b, String borderColor, String fillColor, int priority) {
        this(name, new Point2D.Double(x, y), a, b, Color.decode("0x" + borderColor), Color.decode("0x" + fillColor), priority);
    }

    Rectangle(String name, Point2D location, double a, double b, Color border, Color fill, int priority) {
        super(name, location, border, fill, priority);
        xPoint = new int[4];
        yPoint = new int[4];
        xPoint[0] = ((int) (location.getX() - a / 2));
        xPoint[1] = ((int) (location.getX() + a / 2));
        xPoint[2] = ((int) (location.getX() + a / 2));
        xPoint[3] = ((int) (location.getX() - a / 2));
        yPoint[0] = ((int) (location.getY() - b / 2));
        yPoint[1] = ((int) (location.getY() - b / 2));
        yPoint[2] = ((int) (location.getY() + b / 2));
        yPoint[3] = ((int) (location.getY() + b / 2));
        this.awtShape = new Polygon(xPoint, yPoint, 4);
    }

    @Override
    public void scale(double k) {
        for (int i = 0; i < 4; i++) {
            xPoint[i] = ((int) (this.getLocation().getX() + (xPoint[i] - this.getLocation().getX()) * k));
            yPoint[i] = ((int) (this.getLocation().getY() + (yPoint[i] - this.getLocation().getY()) * k));
        }
        this.awtShape = new Polygon(xPoint, yPoint, 4);
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
        for (int i = 0; i < 4; i++) {
            X = xPoint[i] - this.getLocation().getX();
            Y = yPoint[i] - this.getLocation().getY();
            xPoint[i] = ((int) (this.getLocation().getX() + X * Math.cos(angle * Math.PI / 180) + Y * Math.sin(angle * Math.PI / 180)));
            yPoint[i] = ((int) (this.getLocation().getY() - X * Math.sin(angle * Math.PI / 180) + Y * Math.cos(angle * Math.PI / 180)));
        }
        this.awtShape = new Polygon(xPoint, yPoint, 4);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D G2d = ((Graphics2D) g);
        G2d.setPaint(Fill);
        G2d.fill(awtShape);
        G2d.setPaint(Border);
    }

    @Override
    public boolean includes(Point2D point) {
        return this.awtShape.contains(point);
    }
}
