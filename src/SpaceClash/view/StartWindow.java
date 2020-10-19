package SpaceClash.view;

import SpaceClash.utils.Config;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame{
    private JPanel panel;
    private JButton start;
    private JButton rules;
    private JButton ranking;
    private int width=650;
    private int height=484;

    public StartWindow(){
        super();
        Container cont=this.getContentPane();
        cont.add(panel);
        this.setPreferredSize(new Dimension(width,height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        Config.getInstance().ReadXml();

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNewGameEvent();
            }
        });
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRulesEvent();
            }
        });
        ranking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlerankingEvent();
            }
        });
    }

    private void handleNewGameEvent() {
        View.getInstance().openOptionsPanel();
        View.getInstance().closeStartWindow();
    }

    private void handleRulesEvent() {
        View.getInstance().openRulesWindow();
        View.getInstance().closeStartWindow();
    }

    private void handlerankingEvent() {
        View.getInstance().openRankingWindow();
        View.getInstance().closeStartWindow();
    }

}
