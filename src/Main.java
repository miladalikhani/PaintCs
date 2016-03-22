import Validation.OperatorsHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by milad on 3/22/16.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
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
