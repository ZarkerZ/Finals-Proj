import javax.swing.*;
import java.awt.*;

public class CommonItems {
    public JLabel makeOptionTitle(String name, int x, int y, int width, int height){
        JLabel label = new JLabel(name);
        label.setFont(new Font("Sans Serif", Font.BOLD, 20));
        label.setBounds(x, y, width, height);
        return label;
    }

    public JLabel makeFieldLabel(String name, int x, int y, int width, int height){
        JLabel label = new JLabel(name);
        label.setFont(new Font("Impact", Font.PLAIN, 15));
        label.setBounds(x, y, width, height);
        return label;
    }

    public JLabel makeSubLabel(String name, int x, int y, int width, int height){
        JLabel label = new JLabel(name);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setBounds(x, y, width, height);
        return label;
    }
    public JButton makeBackButton(String name, JPanel panelOne, JPanel panelTwo, int x, int y, int width, int height){
        JButton button = new JButton(name);
        button.setBounds(x, y, width, height);
        button.addActionListener(e ->{
            panelOne.setVisible(false);
            panelTwo.setVisible(true);
        });
        return button;
    }

    public JFrame makeDisplayFrame(String title, int height){
        JFrame disp = new JFrame(title);
        disp.setSize(750, height);
        disp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disp.setLocationRelativeTo(null);

        return disp;
    }

    public JFrame makeStudentDisplayFrame(String title, int height){
        JFrame disp = new JFrame(title);
        disp.setSize(975, height);
        disp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        disp.setLocationRelativeTo(null);

        return disp;
    }
}
