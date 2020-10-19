package SpaceClash.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EscPressForm extends JFrame{
    private JPanel panel;
    private JButton continueButton;
    private JButton quitButton;
    private JButton restartButton;
    private int width=400;
    private int height=150;

    public EscPressForm(){
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
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleContinueEvent();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExitEvent();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRestartEvent();
            }
        });
    }

    private void handleRestartEvent() {
        View.getInstance().closeEscPressForm();
        View.getInstance().handleRestartEvent();
        View.getInstance().handleContinueEvent();
    }

    private void handleExitEvent(){
        View.getInstance().handleExitEvent();
    }

    private void handleContinueEvent(){
        View.getInstance().closeEscPressForm();
        View.getInstance().handleContinueEvent();
    }


}
