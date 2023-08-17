package tu.daniloff;

import javax.swing.*;

import static java.lang.Math.cos;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}