package SpaceClash.view;

import SpaceClash.logic.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface IView {

    public void openStartWindow();

    public void closeStartWindow();

    public void openOptionsPanel();

    public void closeOptionsPanel();

    public void openMainGUI();

    public void closeMainGui();

    public void openRulesWindow();

    public void closeRulesWindow();

    public void openRankingWindow();

    public void closeRankingWindow();

    public void openEscPressForm();

    public void closeEscPressForm();

    public void openLevelUp();

    public void closeLevelUp();

    public void updateScoreLabel(int score);

    public void updateLevelLabel(int level);

    public void updateLivesLabel(int lives);

    public void gameOverDialog();

    public void openGameOverDialogWindow();

    public void closeGameOverDialogWindow();

    public void openWinForm();

    public void closeWinForm();

    public void handleExitEvent();

    public void handleRestartEvent();

    public void handleOkButton();

    public void handleStartGameEvent(String playerName, String spaceShipSelected, String backgroundSelected);

    public void handleBackEvent();

    public void setPowerupImage(int random);

    public BufferedImage getPowerupImage();

    public String getNameOfPowerUp();

    public void setSpaceshipImage(String spaceshipSelected);

    public BufferedImage getSpaceshipImage();

    public BufferedImage getBossImage();

    public void updateProgressBar(int increment);

    public void showProgressBar();

    public void hideProgressBar();

    public void updateHighScore();

    public void handleStartPauseEvent();

    public void handleContinueEvent();

    public void setKeyStatus(int keycode, boolean result);

    public void setupGame();

    public void next();

    public Enemy getSpecialEnemy();

    public Boss getBoss();

    public boolean isShield();

    public SpaceShip getSpaceShip();

    public PowerUp getPowerUp();

    public ArrayList<Beam> getBeamList();

    public ArrayList<Enemy> getEnemyList();

    public ArrayList<Shield> getShieldList();

    public ArrayList<Bullet> getBulletList();

    public Bullet getPlusBullet();

    public String getPlayerName();

    public int getScore();

    public int getLives();

    public int getHighscore();

    public int getLevel();

    public int getCounterHealthBoss();

    public String getbackgroundSelected();
}
