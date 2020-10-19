package SpaceClash.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesWindow extends JFrame{
    private JTextArea textArea1;
    private JButton backButton;
    private JPanel panel;
    private JButton startGameButton;
    private JTextArea textArea2;
    private int width=650;
    private int height=484;

    public RulesWindow(){
        super();
        Container cont = this.getContentPane();
        cont.add(panel);
        this.setPreferredSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        textArea2.setText("You have to use the directional arrows keys to move the spaceship, while if you want to shoot bullets you have to press the x button.");
        textArea2.setEnabled(false);
        textArea2.setLineWrap(true);
        textArea2.setDisabledTextColor(Color.BLACK);

        textArea1.setText("Aliens will moove from top to bottom moving sideways while they shoot bullets downward, some\n"
                +"of them can also detach themselves from the line and attack the player crossing wavy trajectories.\n"
                +"The player will and can protect himself have a spaceship to moove in any direction and fight\n"
                +"Aliens, with this spaceship player can shot bullets to kill aliens using defensive barriers. Those\n"
                +"barriers can be damaged by both the player's bullets and enemy bullets. Player will have \n"
                +"3 powers-up that will help him defeat enemies:\n"
                +"1) The lighning allows the spaceship to move faster.\n"
                +"2) The bullet-plus powers-up the next spaceship's shot.\n"
                +"3) The shield create a barrier over the spaceship.\n"
                +"Remember to protect your spaceship behind the defensive barriers to dodge enemy bullets\n"
                +"Beat enemy boss in the final level and save the universe.\n");
        textArea1.setLineWrap(true);
        textArea1.setEnabled(false);
        textArea1.setDisabledTextColor(Color.BLACK);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBackEvent();
            }
        });

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleStartGameEvent();
            }
        });
    }

    private void handleBackEvent(){
        View.getInstance().openStartWindow();
        View.getInstance().closeRulesWindow();
    }

    private void handleStartGameEvent(){
        View.getInstance().openOptionsPanel();
        View.getInstance().closeRulesWindow();
    }
}
