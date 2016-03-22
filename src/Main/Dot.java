package Main;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Dot extends Shape {
    Dot (String name, Point2D location, Color border, int priority) {
        super(name, location, border, null, priority);
        this.awtShape = new Line2D.Double(location, location);
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
    }

    @Override
    public void Rotate(double angle) {}

    @Override
    public void Scale(double k) {}

    @Override
    public void draw(Graphics g) {
        Graphics2D G2d = ((Graphics2D) g);
        G2d.setPaint(Border);
        G2d.draw(awtShape);
    }

    //TODO: Override "includes" method
}
