package Config;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Group {
    private String name;
    private ArrayList<Shape> Shapes = new ArrayList<>();
    private int size;
    private Point2D location = new Point2D.Double();

    public Group (Shape shape) {
        this.name = shape.getName();
        this.Shapes.add(shape);
        this.size = 1;
        resetLocation();
    }

    public ArrayList<Shape> getShapes() {
        Collections.sort(Shapes);
        return this.Shapes;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public Shape getShape(int i) {
        return this.Shapes.get(i);
    }

    public void changeName(String name)
    {
        this.name = name;
    }

    public void resetLocation() {
        double X = 0, Y = 0;
        for (int i = 0; i < Shapes.size(); i++) {
            X += Shapes.get(i).getLocation().getX();
            Y += Shapes.get(i).getLocation().getY();
        }
        X /= Shapes.size();
        Y /= Shapes.size();
        location.setLocation(X, Y);
    }

    public Point2D getLocation() { return location; }

    public void addShape(Shape shape) {
        this.Shapes.add(shape);
        this.size++;
        resetLocation();
    }

    public void deleteShape(Shape shape)
    {
        for (int i = 0; i < size; i++) {
            if (Shapes.get(i) == shape) {
                Shapes.remove(i);
                break;
            }
        }
        size--;
        resetLocation();
    }

    public void Move(double DeltaX, double DeltaY) {
        for (int i = 0; i < this.Shapes.size(); i++) {
            this.getShape(i).move(new Point2D.Double(DeltaX, DeltaY));
        }
        resetLocation();
    }

    public void Rotate(double angle) {
        for (int i = 0; i < getSize(); i++) {
            getShape(i).rotate(angle);
            double X, Y;
            X = getShape(i).getLocation().getX() - this.getLocation().getX();
            Y = getShape(i).getLocation().getY() - this.getLocation().getY();
            getShape(i).moveTo(new Point2D.Double(location.getX() + X * Math.cos(angle) + Y * Math.sin(angle), location.getY() - X * Math.sin(angle) + Y * Math.cos(angle)));
        }
        resetLocation();
    }

    public void Scale(double k) {
        for (int i = 0; i < this.Shapes.size(); i++) {
            this.getShape(i).scale(k);
            double X, Y;
            X = getShape(i).getLocation().getX() - this.getLocation().getX();
            Y = getShape(i).getLocation().getY() - this.getLocation().getY();
        }
    }

    public void changeBorder (Color newBorder) {
        for (int i = 0; i < this.Shapes.size(); i++)
            this.getShape(i).changeBorder(newBorder);
    }

    public void changeFill (Color newColor ) {
        for (int i = 0; i < this.Shapes.size(); i++)
            this.getShape(i).changeFill(newColor);
    }

    public void select() {
        for (int i = 0; i < size; i++) {
            Shapes.get(i).select();
        }
    }

    public void unSelect() {
        for (int i = 0; i < size; i++) {
            Shapes.get(i).unSelect();
        }
    }

    public void draw(Graphics g){
        for (int i = 0; i < Shapes.size(); i++) {
            Shapes.get(i).draw(g);
        }
    }
}