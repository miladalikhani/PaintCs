import Config.Polygon;
import Config.Rectangle;
import Validation.OperatorsHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.IntSummaryStatistics;
import java.util.Scanner;
import Config.*;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

/**
 * Created by milad on 3/22/16.
 */
public class Main extends JFrame {
    public Surface surface;
    public Components shapes = new Components();
    public Main() {
        surface = new Surface();
        add(surface);
        setTitle("PaintCS");
        setSize(1300, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        surface.setVisible(true);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        File file = new File("input/ex1.txt");
        OperatorsHandler validator = new OperatorsHandler();
        main.setVisible(true);
        Scanner input = new Scanner(file);
        int cnt = 1;
        while ( input.hasNext() ) {
            int thisStatus;
            thisStatus = readNextLine(input,validator);
            System.out.println("Line " + (cnt++) + " : " + thisStatus);
            switch ( thisStatus)
            {
                case 0:
                    System.out.printf("Operation is not valid\n");
                    break;
                case 1:
                    System.out.printf("argumant of this operation is not valid\n");
                    break;
                case 2:
                    System.out.printf("Hexa decimal Number is not valid\n");
                    break;
                case 3:
                    System.out.printf("Decimal number is not valid\n");
                    break;
                case 30:
                    System.out.printf("float Point number is not valid\n");
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
        }
    }
    private static int readNextLine ( Scanner input , OperatorsHandler validator)
    {
        String tmp;
        tmp = input.nextLine();
        return validator.readAndValidate(tmp);
    }
}
