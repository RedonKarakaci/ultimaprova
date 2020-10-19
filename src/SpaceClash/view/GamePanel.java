package SpaceClash.view;
import SpaceClash.utils.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {

    //---------------------------------------------------------------
    // STATIC CONSTANTS
    //---------------------------------------------------------------
    private final static int CELL_SIZE = 5; // number of pixels
    private final static int numColumnsOfBoard=120;
    private final static int numRowsOfBoard=140;
    private final static Dimension PREFERRED_SIZE = new Dimension(CELL_SIZE * numColumnsOfBoard, CELL_SIZE * numRowsOfBoard);

    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
    //---------------------------------------------------------------
    private Timer gameTimer;

    public GamePanel(){
        super();
        this.addKeyListener(this);
        this.setupGame();
        this.setFocusable(true);
        this.requestFocusInWindow();
        start();
    }

    //---------------------------------------------------------------
    // DRAW GAME PANEL
    //---------------------------------------------------------------
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ColorBackgroundAndImagesSettings.getInstance().getImageBackgroundBoard(),0,-100,null);
        drawSpaceship(g);
        drawBullet(g);
        drawShield(g);
        drawEnemies(g);
        drawBeam(g);
        drawPowerUp(g);
        drawShieldPowerUp(g);
        drawBoss(g);
        drawSpecialEnemy(g);
        drawBulletPlus(g);
    }

    //---------------------------------------------------------------
    // DRAW OBJECT
    //---------------------------------------------------------------

    public void drawSpaceship(Graphics g){
        g.drawImage(View.getInstance().getSpaceshipImage(), View.getInstance().getSpaceShip().getxCoor(), View.getInstance().getSpaceShip().getyCoor(),null);
    }

    public void drawBulletPlus(Graphics g){
        if(View.getInstance().getPlusBullet()!=null) {
            g.setColor(Color.magenta);
            g.fillOval(View.getInstance().getPlusBullet().getxCoor(), View.getInstance().getPlusBullet().getyCoor(), 18, 18);
        }
    }

    public void drawBullet(Graphics g){
        for (int i = 0; i < View.getInstance().getBulletList().size(); i++) {
            g.setColor(Color.YELLOW);
            g.fillRect(View.getInstance().getBulletList().get(i).getxCoor(), View.getInstance().getBulletList().get(i).getyCoor(), View.getInstance().getBulletList().get(i).getWidth(), View.getInstance().getBulletList().get(i).getHeight());
        }
    }

    public void drawShield(Graphics g){
        for (int index = 0; index < View.getInstance().getShieldList().size(); index++) {
            if(View.getInstance().getShieldList().get(index).getColor().equals("gray")) {
                g.setColor(Color.gray);
                g.fillRect(View.getInstance().getShieldList().get(index).getxCoor(), View.getInstance().getShieldList().get(index).getyCoor(), View.getInstance().getShieldList().get(index).getWidth(), View.getInstance().getShieldList().get(index).getHeight());
            }
            if(View.getInstance().getShieldList().get(index).getColor().equals("yellow")){
                g.setColor(Color.yellow);
                g.fillRect(View.getInstance().getShieldList().get(index).getxCoor(), View.getInstance().getShieldList().get(index).getyCoor(), View.getInstance().getShieldList().get(index).getWidth(), View.getInstance().getShieldList().get(index).getHeight());
            }
            if(View.getInstance().getShieldList().get(index).getColor().equals("orange")){
                g.setColor(Color.orange);
                g.fillRect(View.getInstance().getShieldList().get(index).getxCoor(), View.getInstance().getShieldList().get(index).getyCoor(), View.getInstance().getShieldList().get(index).getWidth(), View.getInstance().getShieldList().get(index).getHeight());
            }
            if(View.getInstance().getShieldList().get(index).getColor().equals("red")){
                g.setColor(Color.red);
                g.fillRect(View.getInstance().getShieldList().get(index).getxCoor(), View.getInstance().getShieldList().get(index).getyCoor(), View.getInstance().getShieldList().get(index).getWidth(), View.getInstance().getShieldList().get(index).getHeight());
            }
        }
    }

    public void drawEnemies(Graphics g){
        for(int k = 0; k< View.getInstance().getEnemyList().size(); k++) {
            for (int i = 0; i < View.getInstance().getEnemyList().get(k).getENEMY_PIECE().getEnemyArray().length; i++) {
                for (int j = 0; j < View.getInstance().getEnemyList().get(k).getENEMY_PIECE().getEnemyArray()[i].length; j++) {
                    if (View.getInstance().getEnemyList().get(k).getENEMY_PIECE().getEnemyArray()[i][j] == 1) {
                        if(View.getInstance().getEnemyList().get(k).getEnemyName().equals("enemy0")){
                            g.setColor(Color.magenta);
                        }else if(View.getInstance().getEnemyList().get(k).getEnemyName().equals("enemy1")){
                            g.setColor(Color.orange);
                        }else if(View.getInstance().getEnemyList().get(k).getEnemyName().equals("enemy2")){
                            g.setColor(Color.CYAN);
                        }else if(View.getInstance().getEnemyList().get(k).getEnemyName().equals("enemy3")){
                            g.setColor(Color.GREEN);
                        }else if(View.getInstance().getEnemyList().get(k).getEnemyName().equals("enemy4")){
                            g.setColor(Color.yellow);
                        }
                        g.fillRect(View.getInstance().getEnemyList().get(k).getxCoor() + (j * 4), View.getInstance().getEnemyList().get(k).getyCoor() + (i * 4), View.getInstance().getEnemyList().get(k).getWidth(), View.getInstance().getEnemyList().get(k).getHeight());
                    }
                }
            }
        }
    }

    public void drawBeam(Graphics g){
        for (int i = 0; i < View.getInstance().getBeamList().size(); i++) {
            g.setColor(Color.RED);
            g.fillRect(View.getInstance().getBeamList().get(i).getxCoor(), View.getInstance().getBeamList().get(i).getyCoor(), View.getInstance().getBeamList().get(i).getWidth(), View.getInstance().getBeamList().get(i).getHeight());
        }
    }

    public void drawPowerUp(Graphics g){
        if(View.getInstance().getPowerUp()!=null) {
            g.drawImage(View.getInstance().getPowerupImage(), View.getInstance().getPowerUp().getxCoor(), View.getInstance().getPowerUp().getyCoor(), null);
        }
    }

    public void drawShieldPowerUp(Graphics g){
        if(View.getInstance().isShield()) {
            g.setColor(Color.magenta);
            g.fillRect(View.getInstance().getSpaceShip().getxCoor(), View.getInstance().getSpaceShip().getyCoor() - 10, 66, 5);
        }
    }

    public void drawBoss(Graphics g){
        if(View.getInstance().getBoss()!=null){
            g.drawImage(View.getInstance().getBossImage(), View.getInstance().getBoss().getxCoor(), View.getInstance().getBoss().getyCoor(), null);
        }
    }


    public void drawSpecialEnemy(Graphics g){
        if(View.getInstance().getSpecialEnemy()!=null){
            if(View.getInstance().getSpecialEnemy().getEnemyName().equals("enemy0")){
                g.setColor(Color.magenta);
            }else if(View.getInstance().getSpecialEnemy().getEnemyName().equals("enemy1")){
                g.setColor(Color.orange);
            }else if(View.getInstance().getSpecialEnemy().getEnemyName().equals("enemy2")){
                g.setColor(Color.CYAN);
            }else if(View.getInstance().getSpecialEnemy().getEnemyName().equals("enemy3")){
                g.setColor(Color.GREEN);
            }else if(View.getInstance().getSpecialEnemy().getEnemyName().equals("enemy4")){
                g.setColor(Color.yellow);
            }
            for(int i=0; i<View.getInstance().getSpecialEnemy().getENEMY_PIECE().getEnemyArray().length; i++){
                for(int j=0; j<View.getInstance().getSpecialEnemy().getENEMY_PIECE().getEnemyArray()[i].length; j++){
                    if (View.getInstance().getSpecialEnemy().getENEMY_PIECE().getEnemyArray()[i][j] == 1) {
                        g.fillRect(View.getInstance().getSpecialEnemy().getxCoor() + (j * 4), View.getInstance().getSpecialEnemy().getyCoor() + (i * 4), View.getInstance().getSpecialEnemy().getWidth(), View.getInstance().getSpecialEnemy().getHeight());
                    }
                }
            }
        }
    }

    //---------------------------------------------------------------
    // SETUP GAME
    //---------------------------------------------------------------
    public final void setupGame(){
        View.getInstance().setupGame();
    }

    //---------------------------------------------------------------
    // UPDATE GAME
    //---------------------------------------------------------------
    public void updateGameState(){
        View.getInstance().next();
    }

    //---------------------------------------------------------------
    // START GAME
    //---------------------------------------------------------------
    //Method to start the Timer that drives the animation for the game.
    public void start(){
        gameTimer=new Timer(Config.getInstance().getDelayTime(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGameState();
                repaint();
            }
        });
        gameTimer.setRepeats(true);
        gameTimer.start();
    }

    //---------------------------------------------------------------
    // PAUSE GAME
    //---------------------------------------------------------------
    public void stop(){
        gameTimer.stop();
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }

    //-------------------------------------------------------------------------
    // To implement the interface java.awt.event.KeyListener
    //-------------------------------------------------------------------------

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        View.getInstance().setKeyStatus(ke.getKeyCode(),true);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        View.getInstance().setKeyStatus(ke.getKeyCode(),false);
    }

}
