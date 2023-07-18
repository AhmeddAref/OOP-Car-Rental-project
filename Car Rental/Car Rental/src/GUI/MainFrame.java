package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame {

    private static JFrame mainFrame;

    public MainFrame() {
        mainFrame = new JFrame("Car Rental");
        mainFrame.setSize(1366, 730);
        mainFrame.setVisible(true);
                
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }

}

