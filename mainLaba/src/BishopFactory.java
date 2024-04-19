public class BishopFactory extends UnitFactory{
    @Override
    public Unit create(int x, int y) {
        return new Bishop(x,y);
    }
}