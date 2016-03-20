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

    public int validateStatement ()
    {
         /*
            0 -> operation eror
            1 -> error in number of input
            2 -> hexaDecimal Format error
            3 -> decimal format error
            6,7 -> drawPoint
            8,9 -> drawLine
            10,11 -> draw Circle
            12,13 -> draw Rectangle
            14,15 -> draw Triangel
            16,17 -> draw Polygon
         */
        if ( parts.size() == 0 )
        {
            return 0;
        }
        else
        {
            switch (parts.get(0))
            {
                return 0;
            }
        }
    }

    public int validateSemanticInAdd ( ArrayList<String> parts ) // FIXME: 3/21/16 parts should start with shape name
    {
        /*
            0 -> operation eror
            1 -> error in number of input
            2 -> hexaDecimal Format error
            3 -> decimal format error
            6,7 -> drawPoint
            8,9 -> drawLine
            10,11 -> draw Circle
            12,13 -> draw Rectangle
            14,15 -> draw Triangel
            16,17 -> draw Polygon
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
                    if ( !isHex(parts.get(4)) )
                        return 2;
                    if ( !isInteger(parts.get(2)) || !isInteger(parts.get(3)) )
                        return 3;
                    if ( parts.size() == 5)
                    {
                        return 6;
                    }
                    else
                    {
                        if ( !isInteger(parts.get(5)) )
                            return 3;
                        return 7;
                    }
                }
                else
                    return 1;
            case "Line":
                if ((parts.size() == 7) || (parts.size() == 8))
                {
                    if ( !isHex(parts.get(6)) )
                        return 2;
                    if ( !isInteger(parts.get(2)) || !isInteger(parts.get(3)) || !isInteger(parts.get(4)) || !isInteger(parts.get(5)) )
                        return 3;
                    if ( parts.size() == 7)
                    {
                        return 8;
                    }
                    else
                    {
                        if ( !isInteger(parts.get(7)) )
                            return 3;
                        return 9;
                    }
                }
                else
                    return 1;
            case "Circle":
                if ((parts.size() == 7) || (parts.size() == 8))
                {
                    if ( !isHex(parts.get(5)) || !isHex(parts.get(6)) )
                        return 2;
                    if ( !isInteger(parts.get(2)) || !isInteger(parts.get(3)) || !isInteger(parts.get(4)) )
                        return 3;
                    if ( parts.size() == 7)
                    {
                        return 10;
                    }
                    else
                    {
                        if ( !isInteger(parts.get(7)) )
                            return 3;
                        return 11 ;
                    }
                }
                else
                    return 1;
            case "Rectangle":
                if ((parts.size() == 8) || (parts.size() == 9))
                {
                    if ( !isHex(parts.get(6)) || !isHex(parts.get(7)) )
                        return 2;
                    if ( !isInteger(parts.get(2)) || !isInteger(parts.get(3)) || !isInteger(parts.get(4)) || !isInteger(parts.get(5)) )
                        return 3;
                    if ( parts.size() == 8)
                    {
                        return 12;
                    }
                    else
                    {
                        if ( !isInteger(parts.get(8)) )
                            return 3;
                        return 13 ;
                    }
                }
                else
                    return 1;
            case "Triangle":
                if ((parts.size() == 10) || (parts.size() == 11))
                {
                    if ( !isHex(parts.get(8)) || !isHex(parts.get(9)) )
                        return 2;
                    if ( !isInteger(parts.get(2)) || !isInteger(parts.get(3)) || !isInteger(parts.get(4)) || !isInteger(parts.get(5)) || !isInteger(parts.get(6)) || !isInteger(parts.get(7)) )
                        return 3;
                    if ( parts.size() == 10)
                    {
                        return 14;
                    }
                    else
                    {
                        if ( !isInteger(parts.get(10)) )
                            return 3;
                        return 15 ;
                    }
                }
                else
                    return 1;
            case "Polygon":
                if ((parts.size() == 8) || (parts.size() == 9))
                {
                    if ( !isHex(parts.get(6)) || !isHex(parts.get(7)) )
                        return 2;
                    if ( !isInteger(parts.get(1)) || !isInteger(parts.get(3)) || !isInteger(parts.get(4)) || !isInteger(parts.get(5)) )
                        return 3;
                    if ( parts.size() == 8)
                    {
                        return 16;
                    }
                    else
                    {
                        if ( !isInteger(parts.get(8)) )
                            return 3;
                        return 17;
                    }

                }
                else
                    return 1;
            default:
                return 0;
        }
    }

    public ArrayList<String> getParts() {
        return parts;
    }

    private void parseStatement ()
    {
        parts.clear();
        String tmp = "" ;
        for (int i = 0; i < this.operation.length(); i++) {
            if ( (this.operation.charAt(i) == ',') || (this.operation.charAt(i) == ' ') || (this.operation.charAt(i) == ';') )
            {
                this.parts.add(tmp);
                tmp = "";
            }
            else
            {
                tmp += this.operation.charAt(i);
            }
        }
        if (!tmp.equals(""))
            this.parts.add(tmp);
    }
    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
    private boolean isHex(String s) {
        try {
            Integer.parseInt(s,16);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        if( s.length() != 6)
            return false;
        return true;
    }

}
