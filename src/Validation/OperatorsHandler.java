package Validation;

import java.util.ArrayList;

/**
 * Created by milad on 3/20/16.
 */

public class OperatorsHandler {
    private String operation;
    private Doer worker;
    private ArrayList<String> parts;

    OperatorsHandler( Doer worker)
    {
        this.worker = worker;
        parts = new ArrayList<>();
    }

    public void set ( String operation )
    {
        this.operation = operation;
        parseStatement();
    }

    public int validateSemantic ()
    {
        /*
            0 -> semantic error
         */
        if ( parts.size() == 0 )
        {
            return 0;
        }
        switch (parts.get(0))// TODO: 3/20/16 takmil kardan dakhel ina 
        {
            case "Point":
                if ((parts.size() == 5) || (parts.size() == 6))
                {

                }
                else
                    return 0;
                break;
            case "Line":
                if ((parts.size() == 7) || (parts.size() == 8))
                {

                }
                else
                    return 0;
                break;
            case "Circle":
                if ((parts.size() == 7) || (parts.size() == 8))
                {

                }
                else
                    return 0;
                break;
            case "Rectangle":
                if ((parts.size() == 8) || (parts.size() == 9))
                {

                }
                else
                    return 0;
                break;
            case "Triangle":
                if ((parts.size() == 10) || (parts.size() == 11))
                {

                }
                else
                    return 0;
                break;
            case "Polygon":
                if ((parts.size() == 8) || (parts.size() == 9))
                {

                }
                else
                    return 0;
                break;
            default:
                return 0;
        }
        return 0;
    }

    public ArrayList<String> getParts() {
        return parts;
    }

    private void parseStatement ()
    {
        parts.clear();
        String tmp = "" ;
        for (int i = 0; i < this.operation.length(); i++) {
            if ( (this.operation.charAt(i) == ',') || (this.operation.charAt(i) == '(') || (this.operation.charAt(i) == ')') )
            {
                this.parts.add(tmp);
                tmp = "";
            }
            else if ( this.operation.charAt(i) != ' ' && this.operation.charAt(i) != '\t' )
            {
                tmp += this.operation.charAt(i);
            }
        }
        if (!tmp.equals(""))
            this.parts.add(tmp);
    }

}
