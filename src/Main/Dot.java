package Main;

import java.awt.*;
import java.awt.geom.Point2D;

public class Dot extends Shape {
    Dot (String name, Point2D location, Color border, int priority) {
        super(name, location, border, null, priority);
        this.setA(3);
        this.setB(3);
        this.Point.add (location);
        this.N = 1;
    }

    @Override
    public void ChangeBorder(Color newBorder) {
        this.setBorder(newBorder);
    }

    @Override
    public void ChangeFill(Color newFill) {}

    @Override
    public void Move(Point2D newLocation) {
        this.setLocation(newLocation);
        this.Point.remove(0);
        this.Point.add(newLocation);
    }

    @Override
    public void Rotate(double angle) {}

    @Override
    public void Scale(double k) {}
}
