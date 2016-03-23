package Config;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Shape implements Comparable<Shape> {
    protected Point2D Location;
    protected Color Border;
    protected Color Fill;
    protected String Name;
    protected int Priority;
    protected java.awt.Shape awtShape;

    public Shape (String name, Point2D location, Color border, Color fill, int priority) {
        Name = name;
        Location = location;
        Border = border;
        Fill = fill;
        Priority = priority;
    }

    public java.awt.Shape getAwtShape() { return awtShape; }

    public Color getBorder() { return Border; }

    public void setBorder (Color border) { Border = border; }

    public Color getFill() { return Fill; }

    public void setFill (Color fill) { Fill = fill; }

    public Point2D getLocation() { return Location; }

    public void setLocation (Point2D location) { Location = location; }

    public String getName() { return Name; }

    public void setName (String name) { Name = name; }

    public int getPriority () { return Priority; }

    public void setPriority (int priority) { Priority = priority; }

    public abstract void move (Point2D dR);

    public abstract void moveTo (Point2D newLocation);

    public abstract void scale (double k);

    public void changeBorder (Color newBorder) { this.setBorder(newBorder); }

    public void changeFill (Color newFill) { this.setFill(newFill); }

    public abstract void rotate (double angle);

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

    public abstract void draw(Graphics g);

    public boolean includes(Point2D point) { return false; }
}
