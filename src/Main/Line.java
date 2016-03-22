package Main;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Line extends Shape { //TODO: private array Point
    Line (String name, Point point1, Point point2, Color border, int priority) {
        super (name, new Point ((point1.x + point2.x) / 2, (point1.y + point2.y) / 2), border, null, priority);
        this.awtShape = new Line2D.Double(point1, point2);
    }

    @Override
    public void Move(Point2D newLocation) { //TODO: Graphics, Change Point
        double DeltaX = newLocation.getX() - this.getLocation().getX();
        double DeltaY = newLocation.getY() - this.getLocation().getY();
        this.setLocation(newLocation);
    }

    @Override
    public void ChangeFill(Color newFill) {}

    @Override
    public void Rotate(double angle) { //TODO: Graphics, Change Point
    }

    @Override
    public void Scale(double k) { //TODO: Graphics, Change Point
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D G2d = ((Graphics2D) g);
        G2d.setPaint(Border);
        G2d.draw(awtShape);
    }

    //TODO: Override "include" method
}
