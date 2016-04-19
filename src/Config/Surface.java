package Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Surface extends JPanel {
    ArrayList<Group> group = new ArrayList<Group>();
    ArrayList<Config.Shape> sortedShape = new ArrayList<>();
    MultiSelect Select = null;
    public ArrayList<Integer> keyPressed = new ArrayList<>();
    public int buttonCode;
    public Color borderColor = null;
    public Color fillColor = null;

    public Surface() {
        PaintListener listener = new PaintListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        addKeyListener(listener);
    }

    public void addShape(Config.Shape shape)
    {
        this.group.add(new Group(shape));
        this.sortedShape.add(shape);
    }

    public void setShapes (ArrayList<Group> shapes )
    {
        group = shapes;
    }

    public void setSortedShape (ArrayList<Config.Shape> sorted) { sortedShape = sorted; }

    private void drawAll(Graphics g) {
        for (int i = 0; i < sortedShape.size(); i++) {
            sortedShape.get(i).draw(g);
        }
    }

    public void addKeyPressed(int keyCode) {
        if (keyPressed.indexOf(keyCode) == -1) keyPressed.add(keyCode);
    }

    public void removeKey(int keyCode) {
        for (int i = 0; i < keyPressed.size(); i++) {
            if (keyPressed.get(i).equals(keyCode)) {
                keyPressed.remove(i);
                break;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawAll(g);
    }

    public boolean isFocusable() {
        return true;
    }

    public Group searchGroup(Config.Shape shape) {
        for (int i = 0; i < group.size(); i++) {
            for (int j = 0; j < group.get(i).getSize(); j++) {
                if (group.get(i).getShape(j) == shape) return group.get(i);
            }
        }
        return null;
    }

    public String nameGenerator() {
        return "Shape " + (sortedShape.size() + 1);
    }

    class PaintListener implements MouseListener, MouseMotionListener, KeyListener {
        java.awt.Point pMouse = new Point();
        Group curGroup = null;
        Point drag = null;
        Config.Shape drawShape = null;
        boolean drawing = false;

        public Point2D getPMouse() { return new Point2D.Double(pMouse.getX(), pMouse.getY()); }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) drawing = false;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (buttonCode == 3) {
                if (!drawing) {
                    drawing = true;
                    pMouse = e.getPoint();
                    drawShape = new PolyLine(nameGenerator(), getPMouse(), borderColor, fillColor, sortedShape.size());
                    ((PolyLine) drawShape).addPoint(getPMouse());
                    addShape(drawShape);
                    repaint();
                }
                else {
                    pMouse = e.getPoint();
                    ((PolyLine) drawShape).addPoint(getPMouse());
                    repaint();
                }
            }
            if (buttonCode != 0) return;
            if (e.isControlDown()) {
                for (int i = sortedShape.size() - 1; i >= 0; i--) {
                    if (sortedShape.get(i).includes(this.getPMouse())) {
                        if (sortedShape.get(i).isSelected()) {
                            Select.deleteGroup(searchGroup(sortedShape.get(i)));
                            searchGroup(sortedShape.get(i)).unSelect();
                        } else {
                            Select.addGroup(searchGroup(sortedShape.get(i)));
                            searchGroup(sortedShape.get(i)).select();
                        }
                        break;
                    }
                }
            }
            else {
                boolean flag = false;
                for (int i = sortedShape.size() - 1; i >= 0; i--) {
                    if (sortedShape.get(i).includes(this.getPMouse())) {
                        if (!sortedShape.get(i).isSelected()) {
                            if (Select == null) {
                                Select = new MultiSelect(searchGroup(sortedShape.get(i)));
                                searchGroup(sortedShape.get(i)).select();
                            }
                            else {
                                if (!sortedShape.get(i).isSelected()) {
                                    for (int j = 0; j < Select.getSize(); j++) {
                                        Select.getGroup(j).unSelect();
                                    }
                                    Select = new MultiSelect(searchGroup(sortedShape.get(i)));
                                    searchGroup(sortedShape.get(i)).select();
                                }
                            }
                        }
                        flag = true;
                        break;
                    }
                }
                if (!flag)
                    if (Select != null) {
                        for (int i = 0; i < Select.getSize(); i++) {
                            Select.getGroup(i).unSelect();
                        }
                        Select = null;
                    }
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            curGroup = null;
            drag = null;
            if(!drawing) drawShape = null;
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            switch (buttonCode) {
                case 0:
                    if (keyPressed.size() == 1 && keyPressed.get(0) == KeyEvent.VK_R) {
                        Point cur = new Point(e.getPoint());
                        if (Select == null || SwingUtilities.isRightMouseButton(e)) return;
                        if (curGroup == null) {
                            for (int i = sortedShape.size() - 1; i >= 0; i--) {
                                if (sortedShape.get(i).includes(this.getPMouse()))
                                    curGroup = searchGroup(sortedShape.get(i));
                            }
                        }
                        Point R = new Point(cur.x - ((int) curGroup.getLocation().getX()), cur.y - ((int) curGroup.getLocation().getY()));
                        Point P = new Point(pMouse.x - ((int) curGroup.getLocation().getX()), pMouse.y - ((int) curGroup.getLocation().getY()));
                        double angle = -180 / Math.PI * Math.asin(((double) (P.x * R.y - P.y * R.x)) / R.distance(0, 0) / P.distance(0, 0));
                        Select.Rotate(angle);
                        pMouse.setLocation(cur);
                    }
                    if (keyPressed.size() == 1 && keyPressed.get(0) == KeyEvent.VK_S) {
                        Point cur = new Point(e.getPoint());
                        if (Select == null || SwingUtilities.isRightMouseButton(e)) return;
                        if (curGroup == null) {
                            for (int i = sortedShape.size() - 1; i >= 0; i--) {
                                if (sortedShape.get(i).includes(this.getPMouse()))
                                    curGroup = searchGroup(sortedShape.get(i));
                            }
                        }
                        Point R = new Point(cur.x - ((int) curGroup.getLocation().getX()), cur.y - ((int) curGroup.getLocation().getY()));
                        Point P = new Point(pMouse.x - ((int) curGroup.getLocation().getX()), pMouse.y - ((int) curGroup.getLocation().getY()));
                        if (P.distance(0, 0) == 0) return;
                        double k = R.distance(0, 0) / P.distance(0, 0);
                        Select.Scale(k);
                        pMouse.setLocation(cur);
                    } else {
                        Point cur = new Point(e.getPoint());
                        Point dif = new Point(cur.x - pMouse.x, cur.y - pMouse.y);
                        pMouse.setLocation(cur);
                        if (Select == null || SwingUtilities.isRightMouseButton(e)) return;
                        Select.Move(dif.x, dif.y);
                    }
                    repaint();
                    break;
                case 1:
                    if (Select != null) {
                        for (int i = 0; i < Select.getSize(); i++) {
                            Select.getGroup(i).unSelect();
                        }
                        Select = null;
                        repaint();
                    }
                    if (drag == null) {
                        drag = e.getPoint();
                        pMouse = e.getPoint();
                        int x, y;
                        x = drag.x;
                        y = drag.y;
                        drawShape = new Circle(nameGenerator(), new Point2D.Double(x, y), 1, borderColor, fillColor, sortedShape.size());
                        addShape(drawShape);
                        repaint();
                    }
                    else {
                        pMouse = e.getPoint();
                        double r = drag.distance(pMouse);
                        drawShape.moveTo(drawShape.getLocation());
                        ((Circle) drawShape).setRadius(r);
                        repaint();
                    }
                    break;
                case 2:
                    if (Select != null) {
                        for (int i = 0; i < Select.getSize(); i++) {
                            Select.getGroup(i).unSelect();
                        }
                        Select = null;
                        repaint();
                    }
                    if (drag == null) {
                        drag = e.getPoint();
                        pMouse = e.getPoint();
                        int x = drag.x;
                        int y = drag.y;
                        drawShape = new Rectangle(nameGenerator(), new Point2D.Double(x, y), 0.0, 0.0, borderColor, fillColor, sortedShape.size());
                        addShape(drawShape);
                        repaint();
                    }
                    else {
                        pMouse = e.getPoint();
                        int width, height;
                        width = 2 * Math.abs(pMouse.x - drag.x);
                        height = 2 * Math.abs(pMouse.y - drag.y);
                        ((Rectangle) drawShape).setWidth(width);
                        ((Rectangle) drawShape).setHeight(height);

                        repaint();
                    }
                    break;
                case 3:
                    break;
                case 4:
                    if (Select != null) {
                        for (int i = 0; i < Select.getSize(); i++) {
                            Select.getGroup(i).unSelect();
                        }
                        Select = null;
                        repaint();
                    }
                    if (drag == null) {
                        drag = e.getPoint();
                        pMouse = e.getPoint();
                        int x = drag.x;
                        int y = drag.y;
                        drawShape = new Polygon(nameGenerator(), 5, new Point2D.Double(x, y), 100.0, borderColor, fillColor, sortedShape.size());
                        ((Polygon) drawShape).setRadius(1);
                        addShape(drawShape);
                        repaint();
                    }
                    else {
                        pMouse = e.getPoint();
                        ((Polygon) drawShape).setRadius(drag.distance(pMouse));
                        double angle = 180 / Math.PI * Math.asin(((double) (pMouse.x - drag.x)) / drag.distance(pMouse));
                        if (pMouse.x > drag.x && pMouse.y < drag.y) ((Polygon) drawShape).rotateTo(180 - angle);
                        if (pMouse.x > drag.x && pMouse.y > drag.y) ((Polygon) drawShape).rotateTo(angle);
                        if (pMouse.x < drag.x && pMouse.y > drag.y) ((Polygon) drawShape).rotateTo(angle);
                        if (pMouse.x < drag.x && pMouse.y < drag.y) ((Polygon) drawShape).rotateTo(180 - angle);

                        repaint();
                    }
                    break;
                case 5:
                    if (Select != null) {
                        for (int i = 0; i < Select.getSize(); i++) {
                            Select.getGroup(i).unSelect();
                        }
                        Select = null;
                        repaint();
                    }
                    if (drag == null) {
                        drag = e.getPoint();
                        pMouse = e.getPoint();
                        drawShape = new PolyLine(nameGenerator(), getPMouse(), borderColor, fillColor, sortedShape.size());
                        addShape(drawShape);
                        repaint();
                    }
                    else {
                        pMouse = e.getPoint();
                        ((PolyLine) drawShape).addPoint(getPMouse());
                        repaint();
                    }
                    break;
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            pMouse = e.getPoint();
            if (drawing) {
                ((PolyLine) drawShape).setPoint(((PolyLine) drawShape).pointNumber() - 1, getPMouse());
                repaint();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            addKeyPressed(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            removeKey(e.getKeyCode());
        }

    }
}
