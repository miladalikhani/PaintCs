import Validation.OperatorsHandler;
import Config.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import Config.*;

/**
 * Created by milad on 3/22/16.
 */
public class Main extends JFrame {
    private Surface surface;
    public Main() {
        surface = new Surface();
        this.add(surface);
        setTitle("PaintCS");
        setSize(1300, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Components shapes = new Components();
        Main test = new Main();
        File file = new File("input/ex1.txt");
        OperatorsHandler validator = new OperatorsHandler();
        Scanner input = new Scanner(file);
        int cnt = 1;
        while ( input.hasNext() ) {
            int thisStatus;
            thisStatus = readNextLine(input,validator);
            System.out.println("Line " + (cnt++) + " : " + thisStatus);
        }
    }
    private static int readNextLine ( Scanner input , OperatorsHandler validator)
    {
        String tmp;
        tmp = input.nextLine();
        return validator.readAndValidate(tmp);
    }
}
