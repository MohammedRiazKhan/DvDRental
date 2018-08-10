import javax.swing.*;
import java.awt.*;

public class About {

    private JFrame aboutFrame = new JFrame();
    private JPanel panel = new JPanel();

    public About(){


        JLabel field1 = new JLabel("This Program was created by");
        JLabel field2 = new JLabel("Mohammed Riaz Khan & Clayton Petersen");

        JLabel field3 = new JLabel("for our Applications Development Practice 2 Project");


        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 5,0, 5);

        c.anchor = GridBagConstraints.LINE_START;
        panel.add(field1, c);

        c.gridy = 1;
        c.gridx = 0;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(field2, c);

        c.gridy = 2;
        c.gridx = 0;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(field3, c);

        aboutFrame.add(panel);

        createGUI();

    }

    public void createGUI(){

        aboutFrame.setTitle("About");
        aboutFrame.setSize(350, 100);
        aboutFrame.setVisible(true);
        aboutFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        aboutFrame.setLocationRelativeTo(null);
        // addFrame.setAlwaysOnTop(true);
        aboutFrame.setResizable(false);

    }


}
