package Main;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class Shape implements Comparable<Shape> {
    protected Point2D Location;
    protected ArrayList<Point2D> Point = new ArrayList<>();
    protected int N;
    protected Color Border;
    protected Color Fill;
    protected double A, B;
    protected String Name;
    protected double Angle;
    protected int Priority;

    public Shape (String name, Point2D location, Color border, Color fill, int priority) {
        this.Name = name;
        this.Location = location;
        this.Border = border;
        this.Fill = fill;
        this.Priority = priority;
        this.A = 0;
        this.B = 0;
        this.Angle = 0;
        this.N = 0;
    }

    public Color getBorder() { return Border; }

    public void setBorder (Color border) { Border = border; }

    public Color getFill() { return Fill; }

    public void setFill (Color fill) { Fill = fill; }

    public double getA() { return A; }

    public void setA (double a) { A = a; }

    public double getAngle() { return Angle; }

    public void setAngle (double angle) { Angle = angle; }

    public double getB() { return B; }

    public void setB (double b) { B = b; }

    public Point2D getLocation() { return Location; }

    public void setLocation (Point2D location) { Location = location; }

    public String getName() { return Name; }

    public void setName (String name) { Name = name; }

    public int getPriority () { return Priority; }

    public void setPriority (int priority) { Priority = priority; }

    public abstract void Move (Point2D newLocation);

    public abstract void Scale (double k);

    public abstract void ChangeBorder (Color newBorder);

    public abstract void ChangeFill (Color newFill);

    public abstract void Rotate (double angle);

    public static int compare ( Shape a , Shape b )
    {
        if ( a.getPriority() > b.getPriority() )
            return 1;
        else if ( a.getPriority() < b.getPriority() )
            return -1;
        return 0;
    }

    @Override
    public int compareTo(Shape shape) {
        return Shape.compare(this , shape);
    }
}
