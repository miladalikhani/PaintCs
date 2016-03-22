package Main;

import java.awt.*;
import java.awt.geom.Point2D;

public class Line extends Shape {
    Line (String name, Point point1, Point point2, Color border, int priority) {
        super (name, new Point ((point1.x + point2.x) / 2, (point1.y + point2.y) / 2), border, null, priority);
        this.setA (Math.abs(point1.x - point2.x));
        this.setB(Math.abs(point1.y - point2.y));
    }

    public Point2D getPoint1() { return this.Point.get(0); }

    public void setPoint1(Point2D point1) {
        this.Point.remove(0);
        this.Point.add(0, point1);
    }

    public Point2D getPoint2() { return this.Point.get(1); }

    public void setPoint2(Point2D point2) {
        this.Point.remove(1);
        this.Point.add(point2);
    }

    @Override
    public void Move(Point2D newLocation) {
        double DeltaX = newLocation.getX() - this.getLocation().getX();
        double DeltaY = newLocation.getY() - this.getLocation().getY();
        this.setLocation (newLocation);
        this.setPoint1 (new Point2D.Double (DeltaX + this.getPoint1().getX(), DeltaY + this.getPoint1().getY()));
        this.setPoint2 (new Point2D.Double(DeltaX + this.getPoint2().getX(), DeltaY + this.getPoint2().getY()));
    }

    @Override
    public void ChangeBorder(Color newBorder) {
        this.setBorder(newBorder);
    }

    @Override
    public void ChangeFill(Color newFill) {}

    @Override
    public void Rotate(double angle) {
        double X1 = this.getPoint1().getX() - this.getLocation().getX();
        double Y1 = this.getPoint1().getY() - this.getLocation().getY();
        double X2 = this.getPoint2().getX() - this.getLocation().getX();
        double Y2 = this.getPoint2().getY() - this.getLocation().getY();
        this.setPoint1 (new Point2D.Double (X1 * Math.cos(angle) - Y1 * Math.sin(angle) + this.getLocation().getX(), X1 * Math.sin(angle) + Y1 * Math.cos(angle) + this.getLocation().getY()));
        this.setPoint1 (new Point2D.Double (X2 * Math.cos(angle) - Y2 * Math.sin(angle) + this.getLocation().getX(), X2 * Math.sin(angle) + Y2 * Math.cos(angle) + this.getLocation().getY()));
    }

    @Override
    public void Scale(double k) {   //must check k
        double X1 = this.getPoint1().getX() - this.getLocation().getX();
        double Y1 = this.getPoint1().getY() - this.getLocation().getY();
        double X2 = this.getPoint2().getX() - this.getLocation().getX();
        double Y2 = this.getPoint2().getY() - this.getLocation().getY();
        this.setPoint1 (new Point2D.Double (X1 * k + this.getLocation().getX(), Y1 * k + this.getLocation().getY()));
        this.setPoint1 (new Point2D.Double (X2 * k + this.getLocation().getX(), Y2 * k + this.getLocation().getY()));
    }
}
