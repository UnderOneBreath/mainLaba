import java.util.ArrayList;
import java.util.Collections;

public class WaveAlg {
    public static void main(String[] args) {
        int[][] map = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
        };
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = -1*map[i][j]-1;
            }
        }
        //new WaveAlg().print(map);
        ArrayList<Point> path = new WaveAlg().findQueenPath(2,1,9,9,map);
    }

    private void print(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }
    }

    private ArrayList findPath(int x, int y, int tx, int ty, int[][] map, int[] dx, int[] dy) {
        ArrayList<Point> wave = new ArrayList();
        if(map[ty][tx] != -1) {
//            System.out.println("Unit on the wall");
            return wave;
        }
        //wave way
        ArrayList<Point> oldWave = new ArrayList();
        int count = 0;
        map[y][x] = count;
        wave.add(new Point(x,y));
//        int[] dx = new int[]{1, -1, 0, 0, -1, 1, -1, 1};
//        int[] dy = new int[]{0, 0, -1, 1, 1, -1, -1, 1};
        boolean isExist = false;
        out:
        while(!wave.isEmpty()){
            count++;
            for(Point p: wave) {
                for (int i = 0; i < dx.length; i++) {
                    int cx = p.getX() + dx[i];
                    int cy = p.getY() + dy[i];
                    if(cx >= 0 && cy >= 0 && cy<map.length && cx<map[i].length) {
                        if (map[cy][cx] == -1) {
                            map[cy][cx] = count;
                            oldWave.add(new Point(cx, cy));
                            if(cx == tx && cy == ty){
                                isExist = true;
                                break out;
                            }
                        }
                    }
                }
            }
            wave = new ArrayList<>(oldWave);
            oldWave.clear();
        }
//        print(map);
        //rewave
        wave.clear();
        if(!isExist){
            return wave;
        }
        wave.add(new Point(tx,ty));
        while (map[ty][tx] != 0){
            for (int i = 0; i < dx.length; i++) {
                int cx = tx + dx[i];
                int cy = ty + dy[i];
                if (cx >= 0 && cy >= 0 && cy < map.length && cx < map[i].length) {
                    if(map[cy][cx] == map[ty][tx]-1){
                        tx = cx;
                        ty = cy;
                        if(tx != x && ty != y) {
                            wave.add(new Point(tx, ty));
                        }
                        break;
                    }
                }
            }
        }
        Collections.reverse(wave);
        for (Point p: wave){
            System.out.println(p.getY()+","+p.getX());
            map[p.getY()][p.getX()] = 0;
        }
//        print(map);
        return wave;
    }

    public ArrayList<Point> findQueenPath(int x, int y, int tx, int ty, int[][] cloneMap) {
        int[] dx = new int[]{1, -1, 0, 0, 1, 1, -1, -1};
        int[] dy = new int[]{0, 0, -1, 1, -1, 1, 1, -1};
        return findPath(x, y, tx, ty, cloneMap, dx, dy);
    }
    public ArrayList<Point> findRookPath(int x, int y, int tx, int ty, int[][] cloneMap) {
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        return findPath(x, y, tx, ty, cloneMap, dx, dy);
    }
    public ArrayList<Point> findBishopPath(int x, int y, int tx, int ty, int[][] cloneMap) {
        int[] dx = new int[]{0, 0, 0, 0, 1, 1, -1, -1};
        int[] dy = new int[]{0, 0, 0, 0, -1, 1, 1, -1};
        return findPath(x, y, tx, ty, cloneMap, dx, dy);
    }
    public ArrayList<Point> findKnightPath(int x, int y, int tx, int ty, int[][] cloneMap) {
        int[] dx = new int[]{1, -1, 1, -1, 2, 2, -2, -2};
        int[] dy = new int[]{2, 2, -2, -2, 1, -1, 1, -1};
        return findPath(x, y, tx, ty, cloneMap, dx, dy);
    }
}