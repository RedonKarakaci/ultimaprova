package SpaceClash.view;

import SpaceClash.logic.*;
import SpaceClash.utils.Config;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class View implements IView {

    //---------------------------------------------------------------
    // STATIC FIELDS
    //---------------------------------------------------------------
    private static View instance = null;

    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
    //---------------------------------------------------------------
    protected StartWindow startWindow = null;
    protected OptionsPanel optionsPanel = null;
    protected MainGui mainGui = null;
    protected RankingWindow ranking = null;
    protected RulesWindow rules = null;
    protected GameOverDialog gameOverDialog=null;
    protected EscPressForm escPressForm=null;
    protected NextLevel levelUp=null;
    protected WinPanel winForm=null;
    private PowerupImage powerupImage;
    private SpaceShipImage spaceShipImage;

    private View(){
    }

    //---------------------------------------------------------------
    // INSTANCE METHODS
    //---------------------------------------------------------------
    @Override
    public void openStartWindow() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (startWindow == null)
                    startWindow = new StartWindow();
                startWindow.setVisible(true);
            }
        });
    }

    @Override
    public void closeStartWindow() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (startWindow != null)
                    startWindow.dispose();
            }
        });
    }

    @Override
    public void openOptionsPanel() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (optionsPanel == null)
                    optionsPanel = new OptionsPanel();
                optionsPanel.setVisible(true);
            }
        });
    }

    @Override
    public void closeOptionsPanel() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (optionsPanel != null)
                    optionsPanel.dispose();
            }
        });
    }

    @Override
    public void openMainGUI() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (mainGui == null)
                    mainGui = new MainGui();
                mainGui.setVisible(true);
            }
        });
    }

    @Override
    public void closeMainGui() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (mainGui != null)
                    mainGui.dispose();
            }
        });
    }

    @Override
    public void openRulesWindow() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (rules == null)
                    rules = new RulesWindow();
                rules.setVisible(true);
            }
        });
    }

    @Override
    public void closeRulesWindow() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (rules != null)
                    rules.dispose();
            }
        });
    }

    @Override
    public void openRankingWindow() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (ranking == null)
                    ranking = new RankingWindow();
                ranking.setVisible(true);
            }
        });
    }

    @Override
    public void closeRankingWindow() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (ranking != null)
                    ranking.dispose();
            }
        });
    }

    @Override
    public void openEscPressForm() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (escPressForm == null)
                    escPressForm = new EscPressForm();
                escPressForm.setVisible(true);
                mainGui.disableAllButton();
                handleStartPauseEvent();
            }
        });
    }

    @Override
    public void closeEscPressForm() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (escPressForm != null)
                    escPressForm.dispose();
                mainGui.enableAllButton();
            }
        });
    }

    @Override
    public void openGameOverDialogWindow() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (gameOverDialog == null)
                    gameOverDialog = new GameOverDialog();
                gameOverDialog.setVisible(true);
                mainGui.disableAllButton();
            }
        });
    }

    @Override
    public void closeGameOverDialogWindow() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (gameOverDialog != null)
                    gameOverDialog.dispose();
                mainGui.enableAllButton();
            }
        });
    }

    @Override
    public void openLevelUp() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (levelUp == null)
                    levelUp = new NextLevel();
                levelUp.setVisible(true);
                mainGui.disableAllButton();
            }
        });
    }

    @Override
    public void closeLevelUp() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (levelUp != null)
                    levelUp.dispose();
                mainGui.enableAllButton();
            }
        });
    }

    @Override
    public void openWinForm() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (winForm == null)
                    winForm = new WinPanel();
                winForm.setVisible(true);
                mainGui.disableAllButton();
            }
        });
    }

    @Override
    public void closeWinForm() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (winForm != null)
                    winForm.dispose();
                mainGui.enableAllButton();
            }
        });
    }

    @Override
    public void handleExitEvent() {
        Config.getInstance().updateScoreInTheXmlFile();
        System.exit(0);
    }

    @Override
    public void handleRestartEvent() {
        Logic.getInstance().handleRestartEvent();
    }

    @Override
    public void handleOkButton() {
        Logic.getInstance().handleOkButton();
    }

    @Override
    public void handleStartGameEvent(String playerName, String spaceShipSelected, String backgroundSelected) {
        Logic.getInstance().handleStartGameEvent(playerName,spaceShipSelected,backgroundSelected);
    }

    @Override
    public void handleBackEvent() {
        this.openStartWindow();
        this.closeOptionsPanel();
    }

    @Override
    public void handleStartPauseEvent() {
        mainGui.startPauseEvent();
    }

    @Override
    public void handleContinueEvent() {
        handleStartPauseEvent();
    }

    @Override
    public void setKeyStatus(int keycode, boolean result) {
        Logic.getInstance().setKeyStatus(keycode,result);
    }

    @Override
    public void setupGame() {
        Logic.getInstance().setupGame();
    }

    @Override
    public void next() {
        Logic.getInstance().next();
    }

    @Override
    public Enemy getSpecialEnemy() {
        return Logic.getInstance().getSpecialEnemy();
    }

    @Override
    public Boss getBoss() {
        return Logic.getInstance().getBoss();
    }

    @Override
    public boolean isShield() {
        return Logic.getInstance().isShield();
    }

    @Override
    public SpaceShip getSpaceShip() {
        return Logic.getInstance().getSpaceShip();
    }

    @Override
    public PowerUp getPowerUp() {
        return Logic.getInstance().getPowerUp();
    }

    @Override
    public ArrayList<Beam> getBeamList() {
        return Logic.getInstance().getBeamList();
    }

    @Override
    public ArrayList<Enemy> getEnemyList() {
        return Logic.getInstance().getEnemyList();
    }

    @Override
    public ArrayList<Shield> getShieldList() {
        return Logic.getInstance().getShieldList();
    }

    @Override
    public ArrayList<Bullet> getBulletList() {
        return Logic.getInstance().getBulletList();
    }

    @Override
    public Bullet getPlusBullet() {
        return Logic.getInstance().getPlusBullet();
    }

    @Override
    public String getPlayerName() {
        return Logic.getInstance().getPlayerName();
    }

    @Override
    public int getScore() {
        return Logic.getInstance().getScore();
    }

    @Override
    public int getLives() {
        return Logic.getInstance().getLives();
    }

    @Override
    public int getHighscore() {
        return Logic.getInstance().getHighscore();
    }

    @Override
    public int getLevel() {
        return Logic.getInstance().getLevel();
    }

    @Override
    public int getCounterHealthBoss() {
        return Logic.getInstance().getCounterHealthBoss();
    }

    @Override
    public String getbackgroundSelected() {
        return Logic.getInstance().getbackgroundSelected();
    }

    @Override
    public void setPowerupImage(int random) {
        powerupImage=new PowerupImage();
        powerupImage.setPowerUpImage(random);
    }

    @Override
    public BufferedImage getPowerupImage() {
        return powerupImage.getPowerUp();
    }

    @Override
    public String getNameOfPowerUp() {
        return powerupImage.getNameOfPowerUp();
    }

    @Override
    public void setSpaceshipImage(String spaceshipSelected) {
        spaceShipImage=new SpaceShipImage();
        spaceShipImage.setSpaceshipImage(spaceshipSelected);
    }

    @Override
    public BufferedImage getSpaceshipImage() {
        return spaceShipImage.getSpaceshipImage();
    }

    @Override
    public BufferedImage getBossImage() {
        BossImage bossImage = new BossImage();
        return bossImage.getBoss();
    }

    @Override
    public void updateProgressBar(int increment) {
        mainGui.updateProgressBar(increment);
    }

    @Override
    public void showProgressBar() {
        mainGui.showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        mainGui.hideProgressBar();
    }

    @Override
    public void updateHighScore() {
        mainGui.updateHighScore();
    }

    @Override
    public void updateScoreLabel(int score) {
        this.mainGui.updateScoreLabel(score);
    }

    @Override
    public void updateLevelLabel(int level) {
        this.mainGui.updateLevelLabel(level);
    }

    @Override
    public void updateLivesLabel(int lives) {
        this.mainGui.updateLivesLabel(lives);
    }

    @Override
    public void gameOverDialog() {
        this.openGameOverDialogWindow();
        if(gameOverDialog!=null) {
            gameOverDialog.handleShowRankingEvent();
        }
    }

    //---------------------------------------------------------------
    // STATIC METHODS
    //---------------------------------------------------------------
    public static IView getInstance() {
        if (instance == null)
            instance = new View();
        return instance;
    }
}
