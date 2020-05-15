
package utilities;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.Lock;

import GUI.*;
import components.Driving;

/**
 * class main.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 */

public class GameDriver{
    public static void main(String[] args) {
            mainFrame main = new mainFrame("Road system");
            main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            main.pack();
            main.setSize(new Dimension(900, 700));
            main.setLocationRelativeTo(null);
            main.setVisible(true);
        }
    }


