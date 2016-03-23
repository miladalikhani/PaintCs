package Config;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by milad on 3/22/16.
 */
public class Components {
    ArrayList<Group> groups = new ArrayList<>();

    public void addGroup(Group newGroup) {
        groups.add(newGroup);
    }

    public getLastPriority()
    {

    }

    public ArrayList<Shape> sortedShapes ()
    {
        ArrayList<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < groups.size() ; i++) {
            for (int j = 0; j < groups.get(i).getSize() ; j++) {
                shapes.add(groups.get(i).getShape(j));
            }
        }
        Collections.sort(shapes);
        return shapes;
    }

    public void changePriority ( Group group , int newPriority )
    {
        ArrayList<Shape> shapes = sortedShapes();
        ArrayList<Shape> groupShapes = group.getShapes();
        ArrayList<Shape> biggerShape = new ArrayList<>();
        shapes.removeAll(groupShapes);
        int cnt = 1 ;
        for (int i = 0; i < shapes.size() ; i++) {
            if ( shapes.get(i).getPriority() > newPriority )
                biggerShape.add(shapes.get(i));
        }
        shapes.removeAll(biggerShape);
        Collections.sort(shapes);
        for (int i = 0; i < shapes.size() ; i++) {
            shapes.get(i).setPriority(cnt++);
        }
        for (int i = 0; i < groupShapes.size() ; i++) {
            groupShapes.get(i).setPriority(cnt++);
        }
        for (int i = 0; i < biggerShape.size(); i++) {
            biggerShape.get(i).setPriority(cnt++);
        }
    }
}
