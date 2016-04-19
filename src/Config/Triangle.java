package Config;

import java.awt.*;
import java.awt.Polygon;
import java.awt.geom.*;

public class Triangle extends Shape {
    private double[] xPoint = new double[3];
    private double[] yPoint = new double[3];

    public Triangle(String name, int x1, int y1, int x2, int y2, int x3, int y3, String borderColor, String fillColor, int priority) {
        this(name, new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(x3, y3), Color.decode("0x" + borderColor), Color.decode("0x" + fillColor), priority);
    }

    Triangle(String name, Point2D point1, Point2D point2, Point2D point3, Color border, Color fill, int priority) {
        super(name, new Point2D.Double((point1.getX() + point2.getX() + point3.getX()) / 3, (point1.getY() + point2.getY() + point3.getY()) / 3) {}, border, fill, priority);
        xPoint[0] = point1.getX();
        xPoint[1] = point2.getX();
        xPoint[2] = point3.getX();
        yPoint[0] = point1.getY();
        yPoint[1] = point2.getY();
        yPoint[2] = point3.getY();
        int[] x = new int[3];
        int[] y = new int[3];
        for (int i = 0; i < 3; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        this.awtShape = new Polygon(x, y, 3);
    }

    @Override
    public void move(Point2D dR) {
        for (int i = 0; i < 3; i++) {
            xPoint[i] += dR.getX();
            yPoint[i] += dR.getY();
        }
        int[] x = new int[3];
        int[] y = new int[3];
        for (int i = 0; i < 3; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        awtShape = new Polygon(x, y, 3);
        setLocation(new Point2D.Double(getLocation().getX() + dR.getX(), getLocation().getY() + dR.getY()));
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
            xPoint[i] = this.getLocation().getX() + X * Math.cos(angle * Math.PI / 180) + Y * Math.sin(angle * Math.PI / 180);
            yPoint[i] = this.getLocation().getY() - X * Math.sin(angle * Math.PI / 180) + Y * Math.cos(angle * Math.PI / 180);
        }
        int[] x = new int[3];
        int[] y = new int[3];
        for (int i = 0; i < 3; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        this.awtShape = new Polygon(x, y, 3);
    }

    @Override
    public void scale(double k) {
        for (int i = 0; i < 3; i++) {
            xPoint[i] = (xPoint[i] - this.getLocation().getX()) * k + this.getLocation().getX();
            yPoint[i] = (yPoint[i] - this.getLocation().getY()) * k + this.getLocation().getY();
        }
        int[] x = new int[3];
        int[] y = new int[3];
        for (int i = 0; i < 3; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        this.awtShape = new Polygon(x, y, 3);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D G2d = ((Graphics2D) g);
        G2d.setStroke(new BasicStroke(2));
        G2d.setPaint(Fill);
        G2d.fill(awtShape);
        G2d.setPaint(Border);
        G2d.draw(awtShape);
        G2d.setPaint(Color.BLACK);
        G2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {3, 3}, 0));
    }

    @Override
    public boolean includes(Point2D point) {
        return this.awtShape.contains(point);
    }

    @Override
    public Triangle clone() {
        Triangle tmp = new Triangle(this.getName() + "_copy", new Point2D.Double(xPoint[0], yPoint[0]), new Point2D.Double(xPoint[1], yPoint[1]), new Point2D.Double(xPoint[2], yPoint[2]), this.getBorder(), this.getFill(), this.getPriority());
        return tmp;
    }
}
