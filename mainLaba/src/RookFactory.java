public class RookFactory extends UnitFactory{

    @Override
    public Unit create(int x, int y) {
        return new Rook(x,y);
    }
}