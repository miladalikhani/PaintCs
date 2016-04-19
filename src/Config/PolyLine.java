package Config;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class PolyLine extends Shape {
    private ArrayList<Point2D> points = new ArrayList<>();
    private ArrayList<Line2D> lines = new ArrayList<>();
    private Point2D topLeft;
    private Point2D bottomRight;

    public PolyLine(String name, Point2D firstPoint, Color border, Color fill, int priority) {
        super(name, firstPoint, border, fill, priority);
        points.add(firstPoint);

        topLeft = new Point2D.Double(firstPoint.getX(), firstPoint.getY());
        bottomRight = new Point2D.Double(firstPoint.getX(), firstPoint.getY());
    }

    public int pointNumber() {
        return points.size();
    }

    public void setPoint (int index, Point2D point) {
        points.get(index).setLocation(point);
        if (index > 0) {
            lines.get(index - 1).setLine(points.get(index - 1), point);
        }
        if (index < lines.size() - 1) {
            lines.get(index).setLine(point, points.get(index + 1));
        }
        double maxX = 0, maxY = 0;
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
        for (int i = 0; i < points.size(); i++) {
            maxX = Math.max(maxX, points.get(i).getX());
            minX = Math.min(minX, points.get(i).getX());
            maxY = Math.max(maxY, points.get(i).getY());
            minY = Math.min(minY, points.get(i).getY());
        }
        topLeft.setLocation(minX, minY);
        bottomRight.setLocation(maxX, maxY);
        setLocation(new Point2D.Double((minX + maxX) / 2, (minY + maxY) / 2));
    }

    public void addPoint (Point2D point) {
        points.add(point);
        lines.add(new Line2D.Double(points.get(points.size() - 1), point));
        double maxX = 0, maxY = 0;
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
        for (int i = 0; i < points.size(); i++) {
            maxX = Math.max(maxX, points.get(i).getX());
            minX = Math.min(minX, points.get(i).getX());
            maxY = Math.max(maxY, points.get(i).getY());
            minY = Math.min(minY, points.get(i).getY());
        }
        topLeft.setLocation(minX, minY);
        bottomRight.setLocation(maxX, maxY);
        setLocation(new Point2D.Double((minX + maxX) / 2, (minY + maxY) / 2));
    }

    @Override
    public void setFill(Color fill) {}

    @Override
    public void move(Point2D dR) {
        for (int i = 0; i < points.size(); i++) {
            setPoint(i, new Point2D.Double(points.get(i).getX() + dR.getX(), points.get(i).getY() + dR.getY()));
        }
    }

    @Override
    public void moveTo(Point2D newLocation) {
        move(new Point2D.Double(newLocation.getX() - getLocation().getX(), newLocation.getY() - getLocation().getY()));
    }

    @Override
    public void scale(double k) {
        for (int i = 0; i < points.size(); i++) {
            setPoint(i, new Point2D.Double(getLocation().getX() + (points.get(i).getX() - getLocation().getX()) * k, getLocation().getY() + (points.get(i).getY() - getLocation().getY()) * k));
        }
    }

    @Override
    public void rotate(double angle) {
        double X, Y;
        for (int i = 0; i < points.size(); i++) {
            X = points.get(i).getX() - getLocation().getX();
            Y = points.get(i).getY() - getLocation().getY();
            setPoint(i, new Point2D.Double(getLocation().getX() + X * Math.cos(Math.PI / 180 * angle) + Y * Math.sin(Math.PI / 180 * angle), getLocation().getY() - X * Math.sin(Math.PI / 180 * angle) + Y * Math.cos(Math.PI / 180 * angle)));
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D G2d = ((Graphics2D) g);
        G2d.setStroke(new BasicStroke(1));
        G2d.setPaint(getBorder());
        for (int i = 0; i < lines.size(); i++) {
            G2d.draw(lines.get(i));
        }
        if (selected) {
            G2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {3, 3}, 0));
            G2d.setPaint(Color.BLACK);
            G2d.draw(new Rectangle2D.Double(topLeft.getX(), topLeft.getY(), bottomRight.getX() - topLeft.getX(), bottomRight.getY() - topLeft.getY()));
        }
    }

    @Override
    public boolean includes(Point2D point) {
        double x1, y1, x2, y2, r;
        for (int i = 0; i < lines.size(); i++) {
            x1 = lines.get(i).getX1();
            x2 = lines.get(i).getX2();
            y1 = lines.get(i).getY1();
            y2 = lines.get(i).getY2();
            r = (x2 - x1) * point.getY() + (y2 - y1) * point.getX() + x1 * y2 - x2 * y1;
            r /= Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
            if (r <= 3 && topLeft.getX() - 3 <= point.getX() && point.getX() <= bottomRight.getX() + 3 && topLeft.getY() - 3 <= point.getY() && point.getY() <= bottomRight.getY() + 3) return  true;
        }
        return false;
    }
}
