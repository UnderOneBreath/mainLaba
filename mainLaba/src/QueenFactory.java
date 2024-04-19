public class QueenFactory extends UnitFactory{
    @Override
    public Unit create(int x, int y) {
        return new Queen(x,y);
    }
}