package tu.daniloff;

import java.io.*;

public class Engine {

    private int playerX;
    private int playerY;
    private double angle; //угол куда смотрит игрок от оси х в радианах
    private byte[][] map;

    public Engine() {
        playerX = 40;
        playerY = 40;

        angle = 0;

        map = new byte[200][200];

        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("D:\\работа\\projects\\raycasting\\src\\main\\resources\\map"));
            int i = 0;
            int j = 0;

            while (bufferedInputStream.available() > 0) {
                int readByte = bufferedInputStream.read();
                if (readByte == 49 || readByte == 48 && i != 200) {
                    if (j == 200) {
                        j = 0;
                        i++;
                    }

                    map[i][j] = readByte == 49 ? (byte) 1 : (byte) 0;
                    j++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[][] getMap() {
        return map;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    /**
     * двигаемся вперед по направлению угла куда смотрим
     * 5.6 .. 0.8 это по x +
     * 0.8 .. 2.4 это по у -
     * 2.4 .. 3.8 это по х -
     * 3.8 .. 5.6 это по у +
     * @return
     */
    public boolean stepForward() {
        if (angle > 5.6 || angle < 0.8)
            return stepIn(playerX + 1, playerY);
        else if (angle >= 0.8 && angle <= 2.4)
            return stepIn(playerX, playerY + 1);
        else if (angle > 2.4 && angle < 3.8)
            return stepIn(playerX - 1, playerY);
        else if (angle >= 3.8) //&& angle <= 5.6 не надо т.к. это проверяется в первом условии
            return stepIn(playerX, playerY - 1);
        else
            return false;
    }

    public boolean stepBack() {
        if (angle > 5.6 || angle < 0.8)
            return stepIn(playerX - 1, playerY);
        else if (angle >= 0.8 && angle <= 2.4)
            return stepIn(playerX, playerY - 1);
        else if (angle > 2.4 && angle < 3.8)
            return stepIn(playerX + 1, playerY);
        else if (angle >= 3.8) //&& angle <= 5.6 не надо т.к. это проверяется в первом условии
            return stepIn(playerX, playerY + 1);
        else
            return false;
    }

    private boolean stepIn(int x, int y) {
        if (map[x][y] == 0) {
            playerX = x;
            playerY = y;
            return true;
        }
        return false;
    }

    public double getAngle() {
        return angle;
    }

    /**
     * устанавить новый угл куда смотрит персонаж
     * @param changeAngle на сколько градусов изменился угл
     */
    public void setAngle(double changeAngle) {
        angle += changeAngle;

        if (angle > 6.3)
            angle -= 6.3;
        else if (angle < 0)
            angle += 6.3;
    }
}
