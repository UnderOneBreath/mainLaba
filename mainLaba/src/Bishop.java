import java.util.ArrayList;

public class Bishop extends Unit {
    public Bishop(int x, int y) {
        super(x, y);
    }

    @Override
    public void setTarget(int x, int y) {
        setPath(new WaveAlg().findBishopPath(
                getX(), getY(), x, y, getField().getCloneMap()));
    }
}
