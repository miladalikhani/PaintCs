package Main;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Group extends Shape {
    private ArrayList<Shape> Shape = new ArrayList<>();

    Group(String name, Shape shape) {
        super(name, shape.getLocation(), shape.getBorder(), shape.getFill(), shape.getPriority());
        this.Shape.add(shape);
        //A = ?;
        //B = ?;
    }

    public Shape getShape(int i) { return this.Shape.get(i); }

    public void addShape(Shape shape) {
        this.Shape.add(shape);
        this.setFill(null);
        this.setBorder(null);
        this.setPriority(0);
        //this.setLocation(?);
    }

    @Override
    public void Move(Point2D newLocation) {
        double DeltaX = newLocation.getX() - this.getLocation().getX();
        double DeltaY = newLocation.getY() - this.getLocation().getY();
        for (int i = 0; i < this.Shape.size(); i++) {
            this.getShape(i).Move(new Point2D.Double(DeltaX + this.getShape(i).getLocation().getX(), DeltaY + this.getShape(i).getLocation().getY()));
        }
    }

    @Override
    public void Rotate(double angle) {
        for (int i = 0; i < this.Shape.size(); i++) {
            this.getShape(i).Rotate(angle);
            double X = this.Shape.get(i).getLocation().getX() - this.getLocation().getX();
            double Y = this.Shape.get(i).getLocation().getY() - this.getLocation().getY();
            this.getShape(i).setLocation(new Point2D.Double(X * Math.cos(angle) - Y * Math.sin(angle) + this.getLocation().getX(), X * Math.sin(angle) + Y * Math.cos(angle) + this.getLocation().getY()));
        }
    }

    @Override
    public void Scale(double k) {
        for (int i = 0; i < this.Shape.size(); i++) {
            this.getShape(i).Scale(k);
            double X = this.getShape(i).getLocation().getX() - this.getLocation().getX();
            double Y = this.getShape(i).getLocation().getY() - this.getLocation().getY();
            X = k * X;
            Y = k * Y;
            this.getShape(i).Move(new Point2D.Double(X, Y));
        }
    }
}
