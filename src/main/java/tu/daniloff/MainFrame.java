package tu.daniloff;

import tu.daniloff.draw.MapRectangle;
import tu.daniloff.draw.utils.MapRectangleLoader;
import tu.daniloff.panels.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class MainFrame extends JFrame {

    public static final int DEFAULT_WIDTH = 712;
    public static final int DEFAULT_HEIGHT = 200;

    private Engine engine;

    private MapPanel mapPanel;

    public MainFrame() {
        setTitle("test");
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setResizable(false);

        Toolkit kit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = kit.getScreenSize();
        setLocation(screenSize.width / 2 - DEFAULT_WIDTH / 2, screenSize.height / 2 - DEFAULT_HEIGHT / 2);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (x >= 100 && x <= 612 && y >= 50 && y <= 90) {
                    //System.out.println("Нажали на запуск");
                    engine = new Engine();
                    repaint();
                }

                if (x >= 100 && x <= 612 && y >= 100 && y <= 140) {
                    //System.out.println("Нажали на выход");
                    System.exit(0);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                //System.out.println("keyCode = " + keyCode);

                if (keyCode == KeyEvent.VK_ESCAPE)
                    System.exit(0);
                if (engine != null) {
                    if (keyCode == 37) {
                        engine.setAngle(- 0.05);
                        //System.out.println("ЛЕВО");
                    } else if (keyCode == 39) {
                        engine.setAngle(+ 0.05);
                        //System.out.println("ПРАВО");
                    } else if (keyCode == 38) {
                        engine.stepForward();
                        //System.out.println("вперед");
                    } else if (keyCode == 40) {
                        engine.stepBack();
                        //System.out.println("назад");
                    }

                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    private MapRectangleLoader mapRectangleLoader;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;

        if (engine == null) {
            g2.setPaint(Color.GRAY);
            g2.fill(new Rectangle2D.Double(0, 0, 712, 200));

            g2.setPaint(Color.WHITE);
            g2.fill(new Rectangle2D.Double(100, 50, 512, 40));
            g2.fill(new Rectangle2D.Double(100, 100, 512, 40));

            Font commonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
            g2.setFont(commonFont);
            g2.setPaint(Color.BLACK);
            g2.drawString("запуск", 350, 75);
            g2.drawString("выход", 350, 125);
        } else {
            if (mapRectangleLoader == null)
                mapRectangleLoader = new MapRectangleLoader(engine.getMap());

            paintGame(g2, mapRectangleLoader.getMapRectangles());
        }
    }

    private void paintGame(Graphics2D g2, List<MapRectangle> mapRectangles) {
        g2.setPaint(Color.GRAY);
        g2.fill(new Rectangle2D.Double(0, 0, 800, 640));

        for (MapRectangle mapRectangle : mapRectangles) {
            g2.setPaint(mapRectangle.color());

            g2.fill(new Rectangle2D.Double(mapRectangle.x(), mapRectangle.y(), mapRectangle.w(), mapRectangle.h()));
        }

        byte[][] map = engine.getMap();

        //нарисовали объект
        int playerX = engine.getPlayerX();
        int playerY = engine.getPlayerY();
        g2.setPaint(Color.YELLOW);
        g2.fillOval(playerX, playerY,1,1);

        //рисуем линию куда смотрит объект
        double playerAngele = engine.getAngle();

        int c = 0;
        for (; c < 200; c++) {
            int x = (int) (playerX + c*cos(playerAngele));
            int y = (int) (playerY + c*sin(playerAngele));
            if (map[y][x] != 0) break;
        }

        int visibleX = (int) (playerX + c * cos(playerAngele));
        int visibleY = (int) (playerY + c * sin(playerAngele));

        g2.setPaint(Color.MAGENTA);
        g2.drawLine(playerX, playerY, visibleX, visibleY);


        //рисуем основные линии видимости 512
        g2.setPaint(Color.CYAN);
        for (int i = 0; i < 512; i++) {
            double angle = playerAngele - 0.5 + i / (double) 512;
            c = 0;
            for (; c < 200; c++) {
                int x = (int) (playerX + c*cos(angle));
                int y = (int) (playerY + c*sin(angle));
                if (map[y][x] != 0) break;
            }

            visibleX = (int) (playerX + c * cos(angle));
            visibleY = (int) (playerY + c * sin(angle));

            g2.drawLine(playerX, playerY, visibleX, visibleY);

            int resulHeight = c - 200;

            int lineX = 200 + i;
            int lineYStart = 100 + resulHeight / 2;
            int lineYEnd = 100 - resulHeight / 2;

            g2.drawLine(lineX, lineYStart, lineX, lineYEnd);
        }
    }
}
