import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.WindowEvent;

public class Game extends JFrame implements  Runnable{
    private int x=0;
    private int y=0;
    int x1 = 50;
    int y1 = 50;
    int col = 12;
    int row = 10;
    int left = 50;
    int top = 50;
    public BufferedImage img;
    private boolean select = false;
    public SpriteSheet sheet;
    ArrayList<Unit> units = new ArrayList<>();
    ArrayList<Equipment> equipment = new ArrayList<>();
    private boolean isWork = true;
    Field field = new Field();

    public static void main(String[] args) {
        Game frame = new Game();
        frame.setBounds(10, 10,800,600);
        frame.setDefaultCloseOperation(2);
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());
    }

    public Game() throws HeadlessException {
        sheet = new SpriteSheet("Dragon.png");
        try {
            img = ImageIO.read(new File("Diamond_shovel.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        new Thread(this).start();
        units.add(new RookFactory().create(2,3));
        units.add(new QueenFactory().create(1,1));
        units.add(new BishopFactory().create(5,6));
        units.add(new KnightFactory().create(7,2));
        for(Unit u: units){
            u.setField(field);
        }
        Showel showel = new Showel();
        showel.setX(1);
        showel.setY(2);
        Showel showel1 = new Showel();
        showel1.setX(1);
        showel1.setY(3);
        equipment.add(showel);
        equipment.add(showel1);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("@@"+e.getKeyChar()+","+e.getKeyCode());
                if(e.getKeyChar() == 'b' || e.getKeyChar() == 'и'){
                    for (int i = 0; i < units.size(); i++) {
                        Unit u = units.get(i);
                        if(u.isSelected() && u.getY()-1 < 0) {
                            System.out.println("Nope");
                        } else if (u.isSelected() && u.getShovel()) {
                            u.build();
                        }
                    }
                }else if(e.getKeyChar() == 'd' || e.getKeyChar() == 'в'){
                    for (int i = 0; i < units.size(); i++) {
                        Unit u = units.get(i);
                        if(u.isSelected() && u.getY()-1 < 0) {
                            System.out.println("Nope");
                        } else if (u.isSelected() && u.getShovel()) {
                            u.destroy();
                        }
                    }
                }
                else if(e.getKeyChar() == 'g' || e.getKeyChar() == 'п'){
                    for (int i = 0; i < units.size(); i++) {
                        Unit u = units.get(i);
                        if(u.isSelected() && u.getY()-1 < 0) {
                            System.out.println("Nope");
                        } else if (u.isSelected() && u.getShovel()) {
                            u.dropEquipment();
                        }
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
//                System.out.println("##"+e.getKeyChar()+","+e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x = (e.getX()-left)/x1;
                y = (e.getY()-top)/y1;
                if(e.getButton() == MouseEvent.BUTTON1){
                    for (int i = 0; i < units.size(); i++) {
                        Unit u = units.get(i);
                        if(x == u.getX() && y == u.getY()){
                            u.setSelected(!u.isSelected());
                        }
                    }
                }else{
                    for (int i = 0; i < units.size(); i++) {
                        Unit u = units.get(i);
                        if(u.isSelected() && y >= 0 && y < 10 && x >= 0 && x < 12) {
                            u.setTarget(x, y);
                        }
                    }
                }
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isWork = false;
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        this.createBufferStrategy(2);
        BufferStrategy bs = this.getBufferStrategy();
        g = bs.getDrawGraphics();
        super.paint(g);
        drawUnit(g);
        drawGrid(g);
        drawWall(g);drawEquipment(g);
        bs.show();
//        g.drawRect(100,100,300,200);
//        g.setColor(Color.RED);
//        g.fillOval(100,100,300,200);
//        g.setColor(new Color(67,255,120));
//        g.drawLine(30,40,400,300);
//        g.fillOval(x,y,50,50);
    }

    private void drawGrid(Graphics g) {
        //12/10
        g.setColor(Color.RED);
        for (int i = 0; i < col+1; i++) {
            g.drawLine(left+x1*i, top, left+x1*i, top+y1*row);
        }
        g.setColor(Color.BLUE);
        for (int i = 0; i < row+1; i++) {
            g.drawLine(left, top+y1*i, left+x1*col, top+y1*i);
        }
    }

    private void drawEquipment(Graphics g) {
        for (Equipment e: equipment) {
            int cx = e.getX()*x1+left;
            int cy = e.getY()*y1+top;
            g.drawImage(img, cx, cy, x1, y1, null);
        }
    }



    private void drawWall(Graphics g) {
        int [][] map = field.getMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 1) {
                    g.setColor(Color.BLUE);
                    g.fillRect(left + j*x1,top+i*y1, x1, y1);

                }
            }
        }
    }

    private void drawUnit(Graphics g){
        for (int i = 0; i < units.size(); i++) {
            Unit u = units.get(i);
            int cx = u.getX() * x1 + left;
            int cy = u.getY() * y1 + top;
            g.drawImage(sheet.getImage(u.getCourse()), cx, cy, x1, y1, null);

//            g.setColor(Color.YELLOW);
//            g.fillOval(cx, cy, 50, 50);
            g.setColor(Color.BLACK);
            g.drawString(u.getClass().getSimpleName(), cx+x1/5, cy+y1/2);
            g.setColor(Color.RED);
            g.fillRect(cx,cy+y1/15,50,10);
            g.setColor(Color.GREEN);
            g.fillRect(cx,cy+y1/15,u.getHp()/2,10);
            if (u.isSelected()) {
                g.setColor(Color.BLUE);
                g.drawOval(cx, cy, 50, 50);
            }
            if(u.getShovel()){
                try {
                    BufferedImage img = ImageIO.read(new File("Diamond_shovel.png"));
                    g.drawImage(img, cx, cy, x1, y1,null);

                }catch (IOException ex){
                    throw new RuntimeException(ex);
                }
            }
//            if(u.getLeyka()){
//                try {
//                    BufferedImage img = ImageIO.read(new File("waterdropper.png"));
//                    g.drawImage(img, cx, cy, x1, y1,null);
//
//                }catch (IOException ex){
//                    throw new RuntimeException(ex);
//                }
//            }
        }
    }
    @Override
    public void run() {
        while(isWork) {
            for(Equipment e: equipment){
                for(Unit u: units){
                    e.sendCoor(e.getX(),e.getY(),u);
                }
            }
            for (Unit u: units) {
                u.move();
//                u.changeHP();
            }
            repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}