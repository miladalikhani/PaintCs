package Config;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;

public class Group {
    private String name;
    private ArrayList<Shape> Shapes = new ArrayList<>();
    private int size;

    public Group(Shape shape) {
        this.name = shape.getName();
        this.Shapes.add(shape);
        this.size = 1;
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
            this.getShape(i).move(new Point2D.Double(DeltaX + this.getShape(i).getLocation().getX(), DeltaY + this.getShape(i).getLocation().getY()));
        }
    }

    public void Rotate(double angle) {
        for (int i = 0; i < this.Shapes.size(); i++) {
            this.getShape(i).rotate(angle);
        }
    }

    public void Scale(double k) {
        for (int i = 0; i < this.Shapes.size(); i++) {
            this.getShape(i).scale(k);
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

    public void draw(Graphics g){
        for (int i = 0; i < Shapes.size(); i++) {
            Shapes.get(i).draw(g);
        }
    }
}