package tu.daniloff.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MapPanel extends JPanel {

    private final byte[][] map;

    public MapPanel(byte[][] map) {
        this.map = map;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1)
                    g2.setPaint(Color.BLACK);
                else
                    g2.setPaint(Color.GRAY);

                g2.fill(new Rectangle2D.Double(j, i, 1, 1));
            }
        }
    }
}
