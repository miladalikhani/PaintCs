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
            switch (thisStatus)
            {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
                    break;
                case 18:
                    break;
                case 19:
                    break;
                case 20:
                    break;
                case 21:
                    break;
                case 22:
                    break;
                case 23:
                    break;
                case 24:
                    break;
                case 25:
                    break;
                case 26:
                    break;
                case 27:
                    break;
                case 28:
                    break;
                case 29:
                    break;
                case 30:
                    break;
                case 31:
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
