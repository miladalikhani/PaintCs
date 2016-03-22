package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

public class PaintListener implements MouseListener, MouseMotionListener { //TODO: Everything!
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
