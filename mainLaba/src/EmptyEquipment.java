public class EmptyEquipment implements Equipment {

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public boolean build(int x, int y, Field field) {
        return false;
    }

    @Override
    public boolean destroy(int x, int y, Field field) {
        return false;
    }
    @Override
    public void sendCoor(int x, int y, Unit unit) {}
}
