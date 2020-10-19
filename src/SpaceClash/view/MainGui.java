package SpaceClash.view;
import SpaceClash.utils.Sound;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainGui extends JFrame {

    //---------------------------------------------------------------
    // STATIC CONSTANTS
    //---------------------------------------------------------------
    private final static String PAUSE_BUTTON_LABEL = "Pause";
    private final static String PLAY_BUTTON_LABEL = "Play";

    private final static String PLAY_SOUNDTRACK_LABEL = "Sound ON";
    private final static String STOP_SOUNDTRACK_LABEL = "Sound OFF";

    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
    //---------------------------------------------------------------

    private JButton startPauseBut;
    private JLabel playerNameLab;
    private JLabel playerNamePrefixLab;
    private JLabel playerScoreLab;
    private JLabel playerScorePrefixLab;
    private JLabel playerLivesLab;
    private JLabel playerLivesPrefixLab;
    private JLabel highscoreLab;
    private JLabel highscorePrefixLab;
    private JLabel levelLab;
    private JLabel levelPrefixLab;
    private Boolean soundON=true;
    private JButton soundButton;
    private JProgressBar healthBoss;
    private JLabel healthBossLabel;

    private JPanel rightPanel;
    private GamePanel gamePanel;

    private boolean isGameRunning; // a started game can be running or in pause

    public MainGui(){
        super("Space-Clash");
        this.createGUI();
        this.isGameRunning = true;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation ( ( screenSize.width / 2 ) - ( this.getWidth ( ) / 2 ), 0);
    }

    //---------------------------------------------------------------
    // PRIVATE INSTANCE METHODS
    //---------------------------------------------------------------
    private void createGUI() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// <- don't close window
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                View.getInstance().handleExitEvent();
            }
        });
        this.gamePanel=new GamePanel();
        this.setRightPanel();
        Container contPane = this.getContentPane();
        contPane.setLayout(new BorderLayout());
        contPane.add(this.gamePanel, BorderLayout.CENTER);
        contPane.add(this.rightPanel, BorderLayout.EAST);
        this.setResizable(false);
        this.pack();
        Sound.getInstance().playSoundtrack();
    }

    private void setRightPanel(){
        this.rightPanel =new JPanel();
        this.rightPanel.setPreferredSize(new Dimension(140,700));
        this.rightPanel.setBackground(ColorBackgroundAndImagesSettings.getInstance().getColorRightPanelBackground());
        this.rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        this.rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        this.playerNamePrefixLab = new JLabel("Player Name");
        this.playerNameLab = new JLabel(View.getInstance().getPlayerName());
        this.playerScorePrefixLab = new JLabel("Score");
        this.playerScoreLab = new JLabel(String.valueOf(View.getInstance().getScore()));
        this.playerLivesPrefixLab=new JLabel("Lives");
        this.playerLivesLab=new JLabel(String.valueOf(View.getInstance().getLives()));
        this.highscorePrefixLab=new JLabel("Highscore");
        this.highscoreLab=new JLabel(String.valueOf(View.getInstance().getHighscore()));
        this.levelPrefixLab=new JLabel("Level");
        this.levelLab=new JLabel(String.valueOf(View.getInstance().getLevel()));
        this.soundButton=new JButton(STOP_SOUNDTRACK_LABEL);
        this.soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundONorOFF();
            }
        });


        this.startPauseBut = new JButton(PAUSE_BUTTON_LABEL);
        this.startPauseBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startPauseEvent();
            }
        });

        healthBoss=new JProgressBar();
        healthBoss.setString(View.getInstance().getCounterHealthBoss()+"/"+30);
        healthBoss.setBorderPainted(true);
        healthBoss.setValue(0);
        healthBoss.setMaximum(30);
        healthBoss.setBackground(Color.green);
        healthBossLabel=new JLabel("Boss Health");
        healthBossLabel.setVisible(false);
        healthBoss.setVisible(false);

        Border margin=new EmptyBorder(0,0,60,0);
        Border margin2=new EmptyBorder(50,0,0,0);

        this.playerNamePrefixLab.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.playerNamePrefixLab.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.playerNamePrefixLab.setForeground(Color.red);

        this.playerNameLab.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.playerNameLab.setBorder(margin);
        this.playerNameLab.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.playerNameLab.setForeground(Color.GREEN);
        this.playerNameLab.setBorder(margin);

        this.playerScorePrefixLab.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.playerScorePrefixLab.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.playerScorePrefixLab.setForeground(Color.red);

        this.playerScoreLab.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.playerScoreLab.setBorder(margin);
        this.playerScoreLab.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.playerScoreLab.setForeground(Color.white);
        this.playerScoreLab.setBorder(margin);

        this.playerLivesPrefixLab.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.playerLivesPrefixLab.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.playerLivesPrefixLab.setForeground(Color.red);

        this.playerLivesLab.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.playerLivesLab.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.playerLivesLab.setBorder(margin);
        this.playerLivesLab.setForeground(Color.white);

        this.highscorePrefixLab.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.highscorePrefixLab.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.highscorePrefixLab.setForeground(Color.red);
        this.highscorePrefixLab.setBorder(margin2);

        this.highscoreLab.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.highscoreLab.setBorder(margin);
        this.highscoreLab.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.highscoreLab.setForeground(Color.white);

        this.levelPrefixLab.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.levelPrefixLab.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.levelPrefixLab.setForeground(Color.red);

        this.levelLab.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.levelLab.setBorder(margin);
        this.levelLab.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.levelLab.setForeground(Color.white);

        this.soundButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.soundButton.setBackground(Color.GREEN);
        this.soundButton.setForeground(Color.white);

        this.startPauseBut.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.startPauseBut.setBackground(Color.GREEN);
        this.startPauseBut.setForeground(Color.white);

        this.healthBossLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.healthBossLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.healthBossLabel.setForeground(Color.RED);
        this.healthBoss.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.rightPanel.add(playerNamePrefixLab);
        this.rightPanel.add(this.playerNameLab);
        this.rightPanel.add(Box.createVerticalGlue());
        this.rightPanel.add(playerScorePrefixLab);
        this.rightPanel.add(this.playerScoreLab);
        this.rightPanel.add(Box.createVerticalGlue());
        this.rightPanel.add(playerLivesPrefixLab);
        this.rightPanel.add(this.playerLivesLab);
        this.rightPanel.add(Box.createVerticalGlue());
        this.rightPanel.add(healthBossLabel);
        this.rightPanel.add(this.healthBoss);
        this.rightPanel.add(Box.createVerticalGlue());
        this.rightPanel.add(highscorePrefixLab);
        this.rightPanel.add(this.highscoreLab);
        this.rightPanel.add(Box.createVerticalGlue());
        this.rightPanel.add(levelPrefixLab);
        this.rightPanel.add(this.levelLab);
        this.rightPanel.add(Box.createVerticalGlue());
        this.rightPanel.add(this.soundButton);
        this.rightPanel.add(Box.createVerticalGlue());
        this.rightPanel.add(this.startPauseBut);
    }

    public void updateHighScore(){
        this.highscoreLab.setText(String.valueOf(View.getInstance().getHighscore()));
    }

    public void updateScoreLabel(int score) {
        this.playerScoreLab.setText(String.valueOf(score));
    }

    public void updateLevelLabel(int level) {
        this.levelLab.setText(String.valueOf(level));
    }

    public void updateLivesLabel(int lives) {
        this.playerLivesLab.setText(String.valueOf(lives));
    }

    public void startPauseEvent() {
        if (!this.isGameRunning) {
            this.isGameRunning = true;
            this.gamePanel.requestFocusInWindow();
            this.gamePanel.start();
            this.soundButton.setText(STOP_SOUNDTRACK_LABEL);
            this.startPauseBut.setText(PAUSE_BUTTON_LABEL);
            Sound.getInstance().startAllSound();
        }
        else {
            this.isGameRunning = false;
            this.gamePanel.stop();
            this.soundButton.setText(PLAY_SOUNDTRACK_LABEL);
            this.startPauseBut.setText(PLAY_BUTTON_LABEL);
            Sound.getInstance().stopAllSound();
        }
    }

    public void showProgressBar(){
        healthBossLabel.setVisible(true);
        healthBoss.setVisible(true);
    }

    public void hideProgressBar(){
        healthBoss.setVisible(false);
        healthBossLabel.setVisible(false);
    }

    public void updateProgressBar(int increment){
        healthBoss.setValue(increment);
    }

    public void soundONorOFF(){
        if(!soundON){
            Sound.getInstance().startAllSound();
            this.soundButton.setText(STOP_SOUNDTRACK_LABEL);
            this.gamePanel.requestFocusInWindow();
            this.soundON=true;
        }
        else{
            Sound.getInstance().stopAllSound();
            this.soundButton.setText(PLAY_SOUNDTRACK_LABEL);
            this.gamePanel.requestFocusInWindow();
            this.soundON=false;
        }
    }

    public void disableAllButton(){
        soundButton.setEnabled(false);
        startPauseBut.setEnabled(false);
    }

    public void enableAllButton(){
        soundButton.setEnabled(true);
        startPauseBut.setEnabled(true);
    }

}
