package tu.daniloff.draw.utils;

import tu.daniloff.draw.MapRectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapRectangleLoader {

    private List<MapRectangle> mapRectangles = new ArrayList<>();

    private static final Color BLACK = Color.BLACK;
    private static final Color GRAY = Color.GRAY;

    public MapRectangleLoader (byte[][] map) {
        mapRectangles.add(new MapRectangle(0, 0, 200, 200, GRAY));

        mapRectangles.add(new MapRectangle(0, 0, 200, 5, BLACK));
        mapRectangles.add(new MapRectangle(0, 195, 200, 5, BLACK));

        mapRectangles.add(new MapRectangle(0, 5, 5, 195, BLACK));
        mapRectangles.add(new MapRectangle(195, 5, 5, 195, BLACK));

        mapRectangles.add(new MapRectangle(45, 10, 113, 5, BLACK));
        mapRectangles.add(new MapRectangle(129, 15, 29, 24, BLACK));

        mapRectangles.add(new MapRectangle(45, 45, 113, 5, BLACK));

        mapRectangles.add(new MapRectangle(45, 55, 29, 39, BLACK));

        mapRectangles.add(new MapRectangle(120, 95, 48, 28, BLACK));
        mapRectangles.add(new MapRectangle(79, 118, 41, 5, BLACK));
        mapRectangles.add(new MapRectangle(79, 123, 36, 5, BLACK));
        mapRectangles.add(new MapRectangle(25, 128, 170, 5, BLACK));

        mapRectangles.add(new MapRectangle(5, 138, 117, 5, BLACK));
        mapRectangles.add(new MapRectangle(84, 143, 38, 22, BLACK));
        mapRectangles.add(new MapRectangle(45, 165, 113, 5, BLACK));
    }

    public List<MapRectangle> getMapRectangles() {
        return mapRectangles;
    }
}
