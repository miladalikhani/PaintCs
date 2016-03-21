package Validation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by milad on 3/21/16.
 */
public class Main {

    public static void main(String[] args) {
        Doer tmp1 = new Doer();
        OperatorsHandler validator = new OperatorsHandler(tmp1);
        String tmp;
        Scanner input = null;
        int LineNumber = 0;

        Scanner inputFile = new Scanner(System.in);
        System.out.println("Enter name of input file");
        String FileName = inputFile.nextLine();
        try {
            input = new Scanner(new File(FileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (input != null && input.hasNextLine())
        {
            LineNumber++;
            tmp = input.nextLine();
            if ( tmp.equals("End") )
                return;
            validator.set(tmp);
            //System.out.println(validator.getParts());
            //System.out.println(validator.validateStatement());
            switch (validator.validateStatement()) {
                case 0:
                    System.out.println("Invalid operator '" + validator.getParts().get(0) + "' at line " + LineNumber);
                    break;
                case 1:
                    System.out.println("Illegal number of arguments at line " + LineNumber);
                    break;
                case 2:
                    System.out.println("Type mismatch - Color needed. At line " + LineNumber);
                    break;
                case 3:
                    System.out.println("Type mismatch - Integer needed. At line " + LineNumber);
                    break;


            }
        }
        System.out.println(LineNumber);
    }
}
