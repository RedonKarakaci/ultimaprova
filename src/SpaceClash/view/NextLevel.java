package SpaceClash.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NextLevel extends JFrame{
    private JPanel panel;
    private JButton OKButton;
    private JLabel message;
    private int width=400;
    private int height=150;

    public NextLevel(){
        super();
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                View.getInstance().handleExitEvent();
            }
        });
        Container cont=this.getContentPane();
        cont.add(panel);
        this.setPreferredSize(new Dimension(width,height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        message.setText("Press OK to start next level");

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOkButton();
            }
        });
    }

    private void handleOkButton(){
        View.getInstance().handleOkButton();
        View.getInstance().closeLevelUp();
    }

}
