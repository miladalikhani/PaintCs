package Main;

import java.awt.*;

public abstract class Shape {
    protected Point Location;
    protected Color Border;
    protected Color Fill;
    protected double A, B;
    protected String Name;
    protected double Angle;

    public Shape (String name, Point location, Color border, Color fill) {
        this.Name = name;
        this.Location = location;
        this.Border = border;
        this.Fill = fill;
        this.A = 0;
        this.B = 0;
        this.Angle = 0;
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

    public Point getLocation() { return Location; }

    public void setLocation (Point location) { Location = location; }

    public String getName() { return Name; }

    public void setName (String name) { Name = name; }

    public abstract void Move (Point newLocation);
    public abstract void Scale (double k);
    public abstract void ChangeBorder (Color newBorder);
    public abstract void ChangeFill (Color newFill);
    public abstract void Rotate (double angle);
}
