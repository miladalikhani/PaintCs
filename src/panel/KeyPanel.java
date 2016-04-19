package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class KeyPanel extends JPanel{
    public Color borderColor = Color.BLACK;
    public Color fillColor = Color.WHITE;
    private ArrayList<JButton> buttons = new ArrayList<>();
    public int buttonCode;

    public KeyPanel() {
        JButton hand = new JButton();
        buttons.add(hand);
        JButton circle = new JButton();
        buttons.add(circle);
        JButton rectangle = new JButton();
        buttons.add(rectangle);
        JButton line = new JButton();
        buttons.add(line);
        JButton polygon = new JButton();
        buttons.add(polygon);
        JButton pencil = new JButton();
        buttons.add(pencil);
        JButton borderColorButton = new JButton();
        JButton fillColorButton = new JButton();

        hand.setToolTipText("Hand");
        hand.setIcon(new ImageIcon(getClass().getResource("hand.png")));
        hand.setPreferredSize(new Dimension(40, 40));
        hand.setSelectedIcon(new ImageIcon(getClass().getResource("hand_select.png")));
        hand.setVisible(true);

        circle.setToolTipText("Circle");
        circle.setIcon(new ImageIcon(getClass().getResource("circle.png")));
        circle.setPreferredSize(new Dimension(40, 40));
        circle.setSelectedIcon(new ImageIcon(getClass().getResource("circle_select.png")));
        circle.setVisible(true);

        rectangle.setToolTipText("Rectangle");
        rectangle.setIcon(new ImageIcon(getClass().getResource("rectangle.png")));
        rectangle.setPreferredSize(new Dimension(40, 40));
        rectangle.setSelectedIcon(new ImageIcon(getClass().getResource("rectangle_select.png")));
        rectangle.setVisible(true);

        line.setToolTipText("Line");
        line.setIcon(new ImageIcon(getClass().getResource("line.png")));
        line.setPreferredSize(new Dimension(40, 40));
        line.setSelectedIcon(new ImageIcon(getClass().getResource("line_select.png")));
        line.setVisible(true);

        polygon.setToolTipText("Polygon");
        polygon.setIcon(new ImageIcon(getClass().getResource("polygon.png")));
        polygon.setPreferredSize(new Dimension(40, 40));
        polygon.setSelectedIcon(new ImageIcon(getClass().getResource("polygon_select.png")));
        polygon.setVisible(true);

        pencil.setToolTipText("Pencil");
        pencil.setIcon(new ImageIcon(getClass().getResource("pencil.png")));
        pencil.setPreferredSize(new Dimension(40, 40));
        pencil.setSelectedIcon(new ImageIcon(getClass().getResource("pencil_select.png")));
        pencil.setVisible(true);

        for (int i = 0; i < buttons.size(); i++) {
            final int k = i;
            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int j = 0; j < buttons.size(); j++) {
                        buttons.get(j).setSelected(false);
                    }
                    buttons.get(k).setSelected(true);
                    buttonCode = k;
                }
            });
        }

        borderColorButton.setToolTipText("Border Color");
        borderColorButton.setBackground(Color.BLACK);
        borderColorButton.setPreferredSize(new Dimension(40, 40));
        borderColorButton.setBackground(borderColor);
        borderColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borderColor = JColorChooser.showDialog(borderColorButton, "Choose your border color...", borderColor);
                borderColorButton.setBackground(borderColor);
            }
        });

        fillColorButton.setToolTipText("Fill Color");
        fillColorButton.setBackground(Color.WHITE);
        fillColorButton.setPreferredSize(new Dimension(40, 40));
        fillColorButton.setBackground(fillColor);
        fillColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillColor = JColorChooser.showDialog(fillColorButton, "Choose your fill color...", fillColor);
                fillColorButton.setBackground(fillColor);
            }
        });

        hand.setSelected(true);
        buttonCode = 0;

        this.add(hand);
        this.add(circle);
        this.add(rectangle);
        this.add(line);
        this.add(polygon);
        this.add(pencil);
        this.add(borderColorButton);
        this.add(fillColorButton);
    }
}
