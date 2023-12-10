import javax.swing.*;
import java.awt.*;

public class a {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Two JPanels Example");
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(3, 1));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.red);
        mainFrame.add(topPanel);

        JButton button1 = new JButton("Button 1");
        topPanel.add(button1);

        // Add a blank JPanel with some space between the top and bottom panels
        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(0, 20));
        mainFrame.add(spacerPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.blue);
        mainFrame.add(bottomPanel);

        JButton button2 = new JButton("Button 2");
        bottomPanel.add(button2);

        mainFrame.setVisible(true);
    }
}