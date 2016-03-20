package Validation;

import java.util.Scanner;

/**
 * Created by milad on 3/21/16.
 */
public class Main {

    public static void main(String[] args) {
        Doer tmp1 = new Doer();
        OperatorsHandler validator = new OperatorsHandler(tmp1);
        String tmp;
        Scanner input = new Scanner(System.in);
        while (true)
        {
            tmp = input.nextLine();
            if ( tmp.equals("End") )
                return;
            validator.set(tmp);
            System.out.println(validator.getParts());
            System.out.println(validator.validateStatement());
        }
    }
}
