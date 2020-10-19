package SpaceClash.logic;

import java.util.ArrayList;

public interface ILogic {

    public void openStartWindow();

    public String getPlayerName();

    public void setPlayerName(String playerName);

    public String getspaceShipSelected();

    public void setspaceShipSelected(String spaceShip);

    public String getbackgroundSelected();

    public void setbackgroundSelected(String background);

    public int getScore();

    public void setScore(int score);

    public void incrementScore(int increment);

    public void decrementLives(int decrement);

    public int getLives();

    public void setLives(int lives);

    public int getHighscore();

    public void setHighscore(int highscore);

    public int getLevel();

    public void setLevel(int level);

    public void moveSpaceship();

    public void clearBeamList();

    public void clearEnemyList();

    public void moveBullet();

    public void theBulletWasLaunched();

    public ArrayList<Bullet> getBulletList();

    public void addShields();

    public ArrayList<Shield> getShieldList();

    public void checksCollisionsWithShieldAndBullets();

    public void checksCollisionsWithEnemiesAndBullets();

    public ArrayList<Beam> getBeamList();

    public void addEnemies();

    public ArrayList<Enemy> getEnemyList();

    public void moveEnemiesHorizontalAndVertical();

    public void checksCollisionsWithEnemiesAndShields();

    public void theBeamWasLaunched();

    public void moveBeam();

    public void checksCollisionsWithBeamsAndShields();

    public void checksCollisionsWithBeamsAndBullet();

    public void checksCollisionsWithBeamsAndSpaceship();

    public PowerUp getPowerUp();

    public void setPowerUp();

    public void checksCollisionsWithBulletAndPowerUp();

    public  void checksCollisionsWithBeamsAndPowerUp();

    public void checksCollisionsWithBulletAndBoss();

    public boolean checksCollisionsWithEnemiesAndSpaceship();

    public boolean checkCollisionWithTheBottom();

    public boolean checkCollisionWithBossAndSpaceship();

    public void checkCollisionWithSpecialEnemyAndSpaceship();

    public void checkCollisionWithBulletAndSpecialEnemy();

    public void checkCollisionWithShieldsAndSpecialEnemy();

    public void checkCollisionWithBulletPlusAndEnemies();

    public void checkCollisionWithBulletPlusAndSpecialEnemy();

    public void checksCollisionsWithBulletPlusAndBoss();

    public void checkCollisionWithBulletPlusAndBeams();

    public void movePlusBullet();

    public boolean isShield();

    public void setupGame();

    public void next();

    public SpaceShip getSpaceShip();

    public void resetPowerUp();

    public void addBoss();

    public void moveBossIn8Shape();

    public Boss getBoss();

    public void deleteBoss();

    public int getCounterHealthBoss();

    public void resetCounterHealthBoss();

    public void resetSpecialEnemy();

    public Enemy getSpecialEnemy();

    public Bullet getPlusBullet();

    public void setKeyStatus(int keycode, boolean result);

    public void resetController();

    public boolean getKeyStatus(int keyCode);

    public void handleRestartEvent();

    public void handleOkButton();

    public void handleStartGameEvent(String playerName, String spaceShipSelected, String backgroundSelected);


}
