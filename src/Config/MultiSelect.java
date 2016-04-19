package Config;

import java.awt.*;
import java.util.ArrayList;

public class MultiSelect implements Cloneable {
    private String name;
    private ArrayList<Group> Groups = new ArrayList<>();
    private int size = 0;

    public MultiSelect(Group group) {
        if (group == null) return;
        this.name = group.getName();
        this.Groups.add(group);
        this.size = 1;
    }

    public ArrayList<Group> getGroups() {
        return this.Groups;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public Group getGroup(int i) {
        return this.Groups.get(i);
    }

    public void changeName ( String name )
    {
        this.name = name;
    }

    public void addGroup(Group group) {
        this.Groups.add(group);
        this.size++;
    }

    public void deleteGroup (Group group)
    {
        for (int i = 0; i < size; i++) {
            if (Groups.get(i) == group) {
                Groups.remove(i);
                break;
            }
        }
        size--;
    }

    public void Move(double DeltaX, double DeltaY) {
        for (int i = 0; i < this.Groups.size(); i++) {
            this.getGroup(i).Move(DeltaX, DeltaY);
        }
    }

    public void Rotate(double angle) {
        for (int i = 0; i < this.Groups.size(); i++) {
            this.getGroup(i).Rotate(angle);
        }
    }

    public void Scale(double k) {
        for (int i = 0; i < this.Groups.size(); i++) {
            this.getGroup(i).Scale(k);
        }
    }

    public void changeBorder (Color newBorder) {
        for (int i = 0; i < this.Groups.size(); i++)
            this.getGroup(i).changeBorder(newBorder);
    }

    public void changeFill (Color newColor ) {
        for (int i = 0; i < this.Groups.size(); i++)
            this.getGroup(i).changeFill(newColor);
    }

    public void draw(Graphics g){
        for (int i = 0; i < Groups.size(); i++) {
            Groups.get(i).draw(g);
        }
    }

    @Override
    public MultiSelect clone() {
        if (size == 0) return new MultiSelect(null);
        MultiSelect tmp = new MultiSelect(null);
        for (int i = 0; i < size; i++) {
            tmp.addGroup(this.getGroup(i).clone());
        }
        return tmp;
    }
}