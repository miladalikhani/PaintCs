package Main;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class Shape implements Comparable<Shape> {
    protected Point2D Location;
    protected Color Border;
    protected Color Fill;
    protected String Name;
    protected double Angle;
    protected int Priority;
    protected java.awt.Shape awtShape;

    public Shape (String name, Point2D location, Color border, Color fill, int priority) {
        Name = name;
        Location = location;
        Border = border;
        Fill = fill;
        Priority = priority;
        Angle = 0;
    }

    public java.awt.Shape getAwtShape() { return awtShape; }

    public Color getBorder() { return Border; }

    public void setBorder (Color border) { Border = border; }

    public Color getFill() { return Fill; }

    public void setFill (Color fill) { Fill = fill; }

    public double getAngle() { return Angle; }

    public void setAngle (double angle) { Angle = angle; }

    public Point2D getLocation() { return Location; }

    public void setLocation (Point2D location) { Location = location; }

    public String getName() { return Name; }

    public void setName (String name) { Name = name; }

    public int getPriority () { return Priority; }

    public void setPriority (int priority) { Priority = priority; }

    public abstract void Move (Point2D newLocation);

    public abstract void Scale (double k);

    public void ChangeBorder (Color newBorder) { this.setBorder(newBorder); }

    public void ChangeFill (Color newFill) { this.setFill(newFill); }

    public abstract void Rotate (double angle);

    public static int compare (Shape a, Shape b)
    {
        if ( a.getPriority() > b.getPriority() )
            return 1;
        else if ( a.getPriority() < b.getPriority() )
            return -1;
        return 0;
    }

    @Override
    public int compareTo(Shape shape) {
        return Shape.compare(this, shape);
    }
}
