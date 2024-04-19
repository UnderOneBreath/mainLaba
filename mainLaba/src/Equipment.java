public interface Equipment {

    void setX(int x);
    void setY(int y);
    int getX();
    int getY();
    boolean build(int x, int y, Field field);
    boolean destroy(int x, int y, Field field);
    void sendCoor(int x, int y, Unit unit);

}
