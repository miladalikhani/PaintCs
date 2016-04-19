package Config;

import javax.xml.xpath.XPath;
import java.awt.*;
import java.awt.Polygon;
import java.awt.geom.*;

public class Rectangle extends Shape {
    private double[] xPoint;
    private double[] yPoint;

    public Rectangle(String name, int x, int y, int a, int b, String borderColor, String fillColor, int priority) {
        this(name, new Point2D.Double(x, y), a, b, Color.decode("0x" + borderColor), Color.decode("0x" + fillColor), priority);
    }

    Rectangle(String name, Point2D location, double a, double b, Color border, Color fill, int priority) {
        super(name, location, border, fill, priority);
        xPoint = new double[4];
        yPoint = new double[4];
        xPoint[0] = location.getX() - a / 2;
        xPoint[1] = location.getX() + a / 2;
        xPoint[2] = location.getX() + a / 2;
        xPoint[3] = location.getX() - a / 2;
        yPoint[0] = location.getY() - b / 2;
        yPoint[1] = location.getY() - b / 2;
        yPoint[2] = location.getY() + b / 2;
        yPoint[3] = location.getY() + b / 2;
        int[] x = new int[4];
        int[] y = new int[4];
        for (int i = 0; i < 4; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        this.awtShape = new Polygon(x, y, 4);
    }

    public void setxPoints(double x1, double x2, double x3, double x4) {
        xPoint[0] = x1;
        xPoint[1] = x2;
        xPoint[2] = x3;
        xPoint[3] = x4;
    }

    public void setyPoints(double y1, double y2, double y3, double y4) {
        yPoint[0] = y1;
        yPoint[1] = y2;
        yPoint[2] = y3;
        yPoint[3] = y4;
    }

    @Override
    public void scale(double k) {
        for (int i = 0; i < 4; i++) {
            xPoint[i] = (xPoint[i] - this.getLocation().getX()) * k + this.getLocation().getX();
            yPoint[i] = (yPoint[i] - this.getLocation().getY()) * k + this.getLocation().getY();
        }
        int[] x = new int[4];
        int[] y = new int[4];
        for (int i = 0; i < 4; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        this.awtShape = new Polygon(x, y, 4);
    }

    @Override
    public void move(Point2D dR) {
        for (int i = 0; i < 4; i++) {
            xPoint[i] += dR.getX();
            yPoint[i] += dR.getY();
        }
        int[] x = new int[4];
        int[] y = new int[4];
        for (int i = 0; i < 4; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        awtShape = new Polygon(x, y, 4);
        setLocation(new Point2D.Double(getLocation().getX() + dR.getX(), getLocation().getY() + dR.getY()));
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
            xPoint[i] = this.getLocation().getX() + X * Math.cos(angle * Math.PI / 180) + Y * Math.sin(angle * Math.PI / 180);
            yPoint[i] = this.getLocation().getY() - X * Math.sin(angle * Math.PI / 180) + Y * Math.cos(angle * Math.PI / 180);
        }
        int[] x = new int[4];
        int[] y = new int[4];
        for (int i = 0; i < 4; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        this.awtShape = new Polygon(x, y, 4);
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

    public void setWidth(int width) {
        xPoint[0] = getLocation().getX() - width / 2;
        xPoint[1] = getLocation().getX() + width / 2;
        xPoint[2] = getLocation().getX() + width / 2;
        xPoint[3] = getLocation().getX() - width / 2;
        int[] x = new int[4];
        int[] y = new int[4];
        for (int i = 0; i < 4; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        this.awtShape = new Polygon(x, y, 4);
    }

    public void setHeight(int height) {
        yPoint[0] = getLocation().getY() - height / 2;
        yPoint[1] = getLocation().getY() - height / 2;
        yPoint[2] = getLocation().getY() + height / 2;
        yPoint[3] = getLocation().getY() + height / 2;
        int[] x = new int[4];
        int[] y = new int[4];
        for (int i = 0; i < 4; i++) {
            x[i] = ((int) xPoint[i]);
            y[i] = ((int) yPoint[i]);
        }
        this.awtShape = new Polygon(x, y, 4);
    }

    @Override
    public boolean includes(Point2D point) {
        return this.awtShape.contains(point);
    }

    @Override
    public Rectangle clone() {
        Rectangle tmp = new Rectangle(this.getName() + "_copy", this.getLocation(), 0, 0, this.getBorder(), this.getFill(), this.getPriority());
        tmp.setxPoints(xPoint[0], xPoint[1], xPoint[2], xPoint[3]);
        tmp.setyPoints(yPoint[0], yPoint[1], yPoint[2], yPoint[3]);
        return tmp;
    }
}
