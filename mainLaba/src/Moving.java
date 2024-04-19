import java.util.ArrayList;

public class Moving {
    public void move(ArrayList<Point> path, OnFieldMovable onFieldMovable, Unit unit) {
        if (!path.isEmpty()) {
            Point p = path.get(0);
            onFieldMovable.remove(unit.getX(), unit.getY());
            unit.setX(p.getX());
            unit.setY(p.getY());
            onFieldMovable.putItem(unit.getX(), unit.getY());
            path.remove(0);
        }
    }
}
