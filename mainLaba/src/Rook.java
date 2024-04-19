import java.util.ArrayList;

public class Rook extends Unit {
    public Rook(int x, int y) {
        super(x, y);
    }

    @Override
    public void setTarget(int x, int y) {
        setPath(new WaveAlg().findRookPath(
                getX(), getY(), x, y, getField().getCloneMap()));
    }
}

