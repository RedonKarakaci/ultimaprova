package SpaceClash.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinPanel extends JFrame{
    private JButton restartButton;
    private JButton exitButton;
    private JPanel panel;
    private int width=550;
    private int height=450;

    public WinPanel(){
        super();
        Container cont=this.getContentPane();
        cont.add(panel);
        this.setPreferredSize(new Dimension(width,height));

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRestartEvent();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExitEvent();
            }
        });
    }

    private void handleRestartEvent() {
        View.getInstance().handleRestartEvent();
        View.getInstance().closeWinForm();
    }
    private void handleExitEvent(){
        View.getInstance().handleExitEvent();
    }
}
