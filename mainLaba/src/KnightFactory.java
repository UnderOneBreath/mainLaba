public class KnightFactory extends UnitFactory{
    @Override
    public Unit create(int x, int y) {
        return new Knight(x,y);
    }
}