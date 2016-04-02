package Config;

import javax.swing.*;
import java.awt.*;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

public class Surface extends JPanel {
    ArrayList<Group> group = new ArrayList<Group>();
    ArrayList<Config.Shape> sortedShape = new ArrayList<>();
    Group Select = null;
    PaintListener listener = null;

    @Override
    public String toString() {
        String answer = "hello\n" ;
        for (int i = 0; i < group.size(); i++) {
            for (int j = 0; j < group.get(i).getSize(); j++) {
                answer += group.get(i).getShape(j).getName() + "\n";
            }

        }
        return answer;
    }

    public Surface() {
        listener = new PaintListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    public void addShape(Group group)
    {
        this.group.add(group);
    }

    public void setShapes (ArrayList<Group> shapes )
    {
        group = shapes;
    }

    public void setSortedShape (ArrayList<Config.Shape> sorted) { sortedShape = sorted; }

    private void drawAll(Graphics g) {
        Iterator<Config.Shape> it = sortedShape.iterator();
        while (it.hasNext()) {
            //System.out.println(it.next().getName());
            it.next().draw(g);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawAll(g);
    }

    class PaintListener implements MouseListener, MouseMotionListener {
        java.awt.Point pMouse = new Point();

        public Point2D getPMouse() { return new Point2D.Double(pMouse.getX(), pMouse.getY()); }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) {

            }
            else {
                if (e.isControlDown()) {
                    for (int i = sortedShape.size() - 1; i >= 0; i--) {
                        if (sortedShape.get(i).includes(this.getPMouse())) {
                            if (sortedShape.get(i).isSelected()) {
                                Select.deleteShape(sortedShape.get(i));
                                sortedShape.get(i).unSelect();
                            } else {
                                Select.addShape(sortedShape.get(i));
                                sortedShape.get(i).select();
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
                                    Select = new Group(sortedShape.get(i));
                                    sortedShape.get(i).select();
                                }
                                else {
                                    if (!sortedShape.get(i).isSelected()) {
                                        Select = new Group(sortedShape.get(i));
                                        sortedShape.get(i).select();
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
                                Select.getShape(i).unSelect();
                            }
                            Select = null;
                        }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point cur = new Point(e.getPoint());
            Point dif = new Point(cur.x - pMouse.x, cur.y - pMouse.y);
            pMouse.setLocation(cur);
            if (Select == null) return;
            Select.Move(dif.x, dif.y);
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            pMouse = e.getPoint();
        }
    }

}
