package Main;

import java.awt.*;

public class Dot extends Shape {
    Dot (String name, Point location, Color border, int priority) {
        super(name, location, border, null, priority);
    }

    @Override
    public void ChangeBorder(Color newBorder) {
        this.setBorder(newBorder);
    }

    @Override
    public void ChangeFill(Color newFill) {}

    @Override
    public void Move(Point newLocation) {
        this.setLocation(newLocation);
    }

    @Override
    public void Rotate(double angle) {}

    @Override
    public void Scale(double k) {}
}
