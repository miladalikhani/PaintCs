package Main;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;

public class Group {
    String name;
    private ArrayList<Shape> Shapes = new ArrayList<>();
    private int size;

    Group(Shape shape) {
        this.name = shape.getName();
        this.Shapes.add(shape);
        this.size = 1;
    }

    public ArrayList<Shape> getShapes() {
        Collections.sort(Shapes);
        return this.Shapes;
    }

    public int getSize() {
        return size;
    }
    public Shape getShape(int i) {
        return this.Shapes.get(i);
    }

    public void changeName ( String name )
    {
        this.name = name;
    }

    public void addShape(Shape shape) {
        this.Shapes.add(shape);
        this.size++;
    }

    public void deleteShape (Shape shape)
    {
        this.Shapes.remove(shape);
        size--;
    }

    public void Move(double DeltaX, double DeltaY) {
        for (int i = 0; i < this.Shapes.size(); i++) {
            this.getShape(i).Move(new Point2D.Double(DeltaX + this.getShape(i).getLocation().getX(), DeltaY + this.getShape(i).getLocation().getY()));
        }
    }

    public void Rotate(double angle) {
        for (int i = 0; i < this.Shapes.size(); i++) {
            this.getShape(i).Rotate(angle);
        }
    }

    public void Scale(double k) {
        for (int i = 0; i < this.Shapes.size(); i++) {
            this.getShape(i).Scale(k);
        }
    }

    public void changeBorder (Color newBorder) {
        for (int i = 0; i < this.Shapes.size(); i++)
            this.getShape(i).ChangeBorder(newBorder);
    }

    public void changeFill (Color newColor ) {
        for (int i = 0; i < this.Shapes.size(); i++)
            this.getShape(i).ChangeFill(newColor);
    }

}
