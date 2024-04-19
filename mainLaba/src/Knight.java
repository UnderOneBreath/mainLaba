import java.util.ArrayList;

public class Knight extends Unit {
    public Knight(int x, int y) {
        super(x, y);
    }

    @Override
    public void setTarget(int x, int y) {
        setPath(new WaveAlg().findKnightPath(
                getX(), getY(), x, y, getField().getCloneMap()));
    }
}
