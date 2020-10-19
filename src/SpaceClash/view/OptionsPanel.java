package SpaceClash.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OptionsPanel extends JFrame{
    private JPanel panel;
    private JTextField playerName;
    private JRadioButton spaceShip1;
    private JRadioButton spaceShip2;
    private JRadioButton spaceShip3;
    private JLabel maxLetters;
    private JRadioButton background1;
    private JRadioButton background2;
    private JRadioButton background3;
    private JButton start;
    private JLabel errorMessage;
    private JButton backButton;
    private ButtonGroup bgBackground;
    private ButtonGroup bgShip;
    private int width=650;
    private int height=484;

    public OptionsPanel(){
        super();
        Container cont=this.getContentPane();
        cont.add(panel);
        this.setPreferredSize(new Dimension(width,height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bgShip=new ButtonGroup();
        spaceShip1.setActionCommand("SpaceShip1");
        spaceShip2.setActionCommand("SpaceShip2");
        spaceShip3.setActionCommand("SpaceShip3");
        bgShip.add(spaceShip1);
        bgShip.add(spaceShip2);
        bgShip.add(spaceShip3);

        bgBackground=new ButtonGroup();
        background1.setActionCommand("Background1");
        background2.setActionCommand("Background2");
        background3.setActionCommand("Background3");
        bgBackground.add(background1);
        bgBackground.add(background2);
        bgBackground.add(background3);

        spaceShip1.setSelected(true);
        background1.setSelected(true);

        playerName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(playerName.getText().length()>=8){
                    e.consume();
                    maxLetters.setText("8 lettere MAX");
                } else {
                    maxLetters.setText("");
                }
            }
        });
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!playerName.getText().equals("")) {
                    handleStartGameEvent(playerName.getText(), bgShip.getSelection().getActionCommand(), bgBackground.getSelection().getActionCommand());
                    errorMessage.setText("");
                    playerName.setText("");
                }
                else {
                    errorMessage.setText("you must enter the name");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBackEvent();
            }
        });

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void handleStartGameEvent(String playerName, String spaceShipSelected, String backgroundSelected) {
        View.getInstance().handleStartGameEvent(playerName,spaceShipSelected,backgroundSelected);
    }

    private void handleBackEvent(){
        View.getInstance().handleBackEvent();
    }

}
