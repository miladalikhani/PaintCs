import Config.Polygon;
import Config.Rectangle;
import Validation.OperatorsHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Config.*;
import panel.KeyPanel;

public class Main extends JFrame {
    public Surface surface;
    public KeyPanel keyPanel;
    public Components shapes;
    public Container Pane;

    public Main() {
        Pane = getContentPane();
        Pane.setLayout(new BoxLayout(Pane, BoxLayout.Y_AXIS));
        shapes = new Components();
        surface = new Surface();
        surface.setPreferredSize(new Dimension(0, 650));
        surface.setAlignmentY(Box.CENTER_ALIGNMENT);
        surface.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        keyPanel = new KeyPanel();
        keyPanel.setPreferredSize(new Dimension(0, 50));
        keyPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        keyPanel.setAlignmentX(Box.CENTER_ALIGNMENT);
        keyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        Pane.add(surface);
        Pane.add(keyPanel);

        setTitle("PaintCS");
        setSize(1300, 700);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        File file = new File("input/ex1.txt");
        OperatorsHandler validator = new OperatorsHandler();
        Scanner input = new Scanner(file);
        int cnt = 1;

        while (input.hasNext()) {
            int thisStatus;
            thisStatus = readNextLine(input,validator);
            switch (thisStatus) {
                case 0:
                    System.out.printf("Operator is not valid at line %d\n", cnt);
                    break;
                case 1:
                    System.out.printf("Arguments of this operation is not valid at line %d\n", cnt);
                    break;
                case 2:
                    System.out.printf("Hexadecimal number is not valid at line %d\n", cnt);
                    break;
                case 3:
                    System.out.printf("Decimal number is not valid at line %d\n", cnt);
                    break;
                case 30:
                    System.out.printf("Float Point number is not valid at line %d\n", cnt);
                case 6:
                    main.shapes.addGroup(new Group( new Dot( validator.get(2) , Integer.parseInt(validator.get(3)) , Integer.parseInt(validator.get(4)) , validator.get(5) , main.shapes.getSize() + 1 )));
                    break;
                case 7:
                    main.shapes.addGroup(new Group( new Dot( validator.get(2) , Integer.parseInt(validator.get(3)) , Integer.parseInt(validator.get(4)) , validator.get(5) , Integer.parseInt(validator.get(6)))));
                    break;
                case 8:
                    main.shapes.addGroup(new Group( new Line( validator.get(2) , Integer.parseInt(validator.get(3)) , Integer.parseInt(validator.get(4)) , Integer.parseInt(validator.get(5)) , Integer.parseInt(validator.get(6)) , validator.get(7) , main.shapes.getSize() + 1 )));
                    break;
                case 9:
                    main.shapes.addGroup(new Group( new Line( validator.get(2) , Integer.parseInt(validator.get(3)) , Integer.parseInt(validator.get(4)) , Integer.parseInt(validator.get(5)) , Integer.parseInt(validator.get(6)) , validator.get(7) , Integer.parseInt(validator.get(8)))));
                    break;
                case 10:
                    main.shapes.addGroup(new Group( new Circle( validator.get(2) , Integer.parseInt(validator.get(3)) , Integer.parseInt(validator.get(4)) , Integer.parseInt(validator.get(5)) , validator.get(6) , validator.get(7) , main.shapes.getSize() + 1 )));
                    break;
                case 11:
                    main.shapes.addGroup(new Group( new Circle( validator.get(2) , Integer.parseInt(validator.get(3)) , Integer.parseInt(validator.get(4)) , Integer.parseInt(validator.get(5)) , validator.get(6) , validator.get(7) , Integer.parseInt(validator.get(8)) )));
                    break;
                case 12:
                    main.shapes.addGroup( new Group( new Rectangle( validator.get(2) , Integer.parseInt(validator.get(3)) , Integer.parseInt(validator.get(4)) , Integer.parseInt(validator.get(5)) , Integer.parseInt(validator.get(6)) , validator.get(7) , validator.get(8) , main.shapes.getSize() + 1 )));
                    break;
                case 13:
                    main.shapes.addGroup( new Group( new Rectangle( validator.get(2) , Integer.parseInt(validator.get(3)) , Integer.parseInt(validator.get(4)) , Integer.parseInt(validator.get(5)) , Integer.parseInt(validator.get(6)) , validator.get(7) , validator.get(8) , Integer.parseInt(validator.get(9)) )));
                    break;
                case 14:
                    main.shapes.addGroup( new Group( new Triangle( validator.get(2) , Integer.parseInt(validator.get(3)) , Integer.parseInt(validator.get(4)) , Integer.parseInt(validator.get(5)) , Integer.parseInt(validator.get(6)) , Integer.parseInt(validator.get(7)) , Integer.parseInt(validator.get(8)) , validator.get(9) , validator.get(10) , main.shapes.getSize() + 1 )));
                    break;
                case 15:
                    main.shapes.addGroup( new Group( new Triangle( validator.get(2) , Integer.parseInt(validator.get(3)) , Integer.parseInt(validator.get(4)) , Integer.parseInt(validator.get(5)) , Integer.parseInt(validator.get(6)) , Integer.parseInt(validator.get(7)) , Integer.parseInt(validator.get(8)) , validator.get(9) , validator.get(10) , Integer.parseInt(validator.get(11)) )));
                    break;
                case 16:
                    main.shapes.addGroup( new Group( new Polygon( validator.get(3) , Integer.parseInt(validator.get(2)) , Integer.parseInt(validator.get(4)) , Integer.parseInt(validator.get(5)) , Integer.parseInt(validator.get(6)) , validator.get(7) , validator.get(8) , main.shapes.getSize() + 1  )));
                    break;
                case 17:
                    main.shapes.addGroup( new Group( new Polygon( validator.get(3) , Integer.parseInt(validator.get(2)) , Integer.parseInt(validator.get(4)) , Integer.parseInt(validator.get(5)) , Integer.parseInt(validator.get(6)) , validator.get(7) , validator.get(8) , Integer.parseInt(validator.get(9)) )));
                    break;
                case 18:
                    // TODO: 3/24/16 complete it
                    main.shapes.remove(Integer.parseInt(validator.get(1)));
                    break;
                case 19:
                    main.shapes.findGroup(validator.get(1)).Scale(Double.parseDouble(validator.get(2)));
                    break;
                case 20:
                    main.shapes.findGroup(validator.get(1)).Rotate(Integer.parseInt(validator.get(2)));
                    break;
                case 21:
                    // TODO: 3/24/16 complere it
                    break;
                case 22:
                    break;
                case 23:
                    break;
                case 24:
                    break;
                case 25:
                    // TODO: 3/24/16 namosie ghaziye
                    break;
                case 26:
                    break;
                case 27:
                    break;
                case 28:
                    main.shapes.findGroup(validator.get(1)).changeBorder(Color.decode(validator.get(2)));
                    break;
                case 29:
                    main.shapes.findGroup(validator.get(1)).changeFill( Color.decode(validator.get(2)));
                    break;
                case 31:
                    main.shapes.changePriority(main.shapes.findGroup(validator.get(1)) , Integer.parseInt(validator.get(2)));
                    break;
            }
            cnt++;
        }
        main.surface.setShapes(main.shapes.getGroups());
        main.surface.setSortedShape(main.shapes.sortedShapes());
        Timer timer = new Timer(100, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.surface.buttonCode = main.keyPanel.buttonCode;
                main.surface.borderColor = main.keyPanel.borderColor;
                main.surface.fillColor = main.keyPanel.fillColor;

                main.surface.requestFocus();
            }
        });
        timer.start();

        main.setVisible(true);
    }

    private static int readNextLine(Scanner input, OperatorsHandler validator) {
        String tmp;
        tmp = input.nextLine();
        return validator.readAndValidate(tmp);
    }
}
