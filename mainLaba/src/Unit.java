import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class Unit {
    private int x;
    private int y;
    private boolean isSelected = false;
    private Field field;
    private int hp = 100;
    private ArrayList<Point> path = new ArrayList<>();
    private Equipment equipment = new Showel();
    private boolean isHaveShovel = false;
    private boolean isHaveLeyka = false;
    public void isHaveShovel(boolean t) {
        isHaveShovel = t;
    }
    public boolean getShovel(){
        return isHaveShovel;
    }
    public void isHaveLeyka(boolean t) {
        isHaveLeyka = t;
    }
    public boolean getLeyka(){
        return isHaveLeyka;
    }

    public ArrayList<Point> getPath() {
        return path;
    }

    public void setPath(ArrayList<Point> path) {
        this.path = path;
    }

    public void setField(Field field) {
        this.field = field;
        field.putItem(x,y);
    }
    public Unit(int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < 7; i++) {
            course.add(i);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void unitGetShovel(Equipment equipment) {
        this.equipment = equipment;
    }
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public abstract void setTarget(int x, int y);


    public Field getField() {
        return field;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move() {
        moveWaveAlg(path);
    }
    public void moveWaveAlg(ArrayList<Point> path){
        if(!path.isEmpty()){
            Point p = path.get(0);
            if(field.isFree(p.getX(), p.getY())) {
                getField().remove(getX(), getY());
                setX(p.getX());
                setY(p.getY());
                getField().putItem(getX(), getY());
                path.remove(0);
                Collections.rotate(course, 1);
            }
            changeHP();
        }
    }
    public int getHp(){
        return hp;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public void changeHP(){
        hp -= 2;
    }

    public void build() {
        equipment.build(x, y, field);
    }

    public void destroy() {
        equipment.destroy(x, y, field);
    }

    public void dropEquipment() {
        isHaveShovel = false;
        isHaveLeyka = false;
    }

    public int getCourse() {
        return course.get(0);
    }

    ArrayList<Integer> course = new ArrayList<>(); //0-7
}