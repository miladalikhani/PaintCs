package Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

public class Surface extends JPanel {
    ArrayList<Group> group = new ArrayList<>();
    Group Select = null;
    PaintListener listener = null;

    public Surface() {
        listener = new PaintListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    public void setShapes ( ArrayList<Group> shapes )
    {
        this.group = shapes;
    }

    private void drawAll(Graphics g) {
        Iterator<Group> it = group.iterator();
        while (it.hasNext()) {
            it.next().draw(g);
        }
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        drawAll(g);
    }

    class PaintListener implements MouseListener, MouseMotionListener {
        java.awt.Point pMouse = null;

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

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            pMouse = e.getPoint();
        }
    }

}
