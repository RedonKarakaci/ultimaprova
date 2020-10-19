package SpaceClash.logic;


import SpaceClash.utils.Config;
import SpaceClash.utils.Sound;
import SpaceClash.view.View;
import java.util.ArrayList;
import java.util.Random;

public class Logic implements ILogic {

    //---------------------------------------------------------------
    // STATIC FIELDS
    //---------------------------------------------------------------
    private static Logic instance = null;

    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
    //---------------------------------------------------------------
    private String playerName;
    private String spaceShipSelected;
    private String backgroundSelected;
    private String direction="RIGHT";

    private int score;
    private int lives;
    private int hightscore;
    private int level;

    private boolean isLightning=false;
    private boolean isShields =false;
    private boolean isBulletPlus=false;
    private boolean theBeamCanBeLaunched=true;
    private boolean[] keyStatus;

    private int counterTimeLightning =0;
    private int counterDelayBullet=0;
    private int counterPowerUp=0;
    private int typeOfEnemy=0;
    private int counterHealthBoss =0;
    private int trajectoryBossCounter =0;
    private int trajectoryEnemyCounter=0;
    private int counterTimeSpecialEnemy =500;

    private Enemy specialEnemy;
    private PowerUp powerUp;
    private Bullet plusBullet;
    private final Trajectories trajectories;
    private SpaceShip spaceShip;
    private Boss boss;

    private final ArrayList<Beam> beamList;
    private final ArrayList<Bullet> bulletList;
    private ArrayList<Shield> shieldList;
    private ArrayList<Enemy> enemyList;
    private ArrayList<Point> trajectoryPointList;
    private final ArrayList<Point> bossPointlist;

    private Logic() {
        this.keyStatus = new boolean[256];
        this.trajectories=new Trajectories();
        this.bossPointlist=trajectories.getTrajectoryLevelThree();
        this.beamList=new ArrayList<>();
        this.bulletList=new ArrayList<>();
    }

    //---------------------------------------------------------------
    // PUBLIC INSTANCE METHODS
    //---------------------------------------------------------------
    @Override
    public void openStartWindow() {
        View.getInstance().openStartWindow();
    }

    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String getspaceShipSelected() {
        return spaceShipSelected;
    }

    @Override
    public void setspaceShipSelected(String spaceShip) {
        this.spaceShipSelected=spaceShip;
    }

    @Override
    public String getbackgroundSelected() {
        return backgroundSelected;
    }

    @Override
    public void setbackgroundSelected(String background) {
        this.backgroundSelected=background;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void setScore(int score) {
        this.score=score;
    }

    @Override
    public void incrementScore(int increment) {
        this.score += increment;
        View.getInstance().updateScoreLabel(score);
    }

    @Override
    public void decrementLives(int decrement){
        this.lives-=decrement;
        View.getInstance().updateLivesLabel(lives);
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public void setLives(int lives) {
        this.lives=lives;
    }

    @Override
    public int getHighscore() {
        return hightscore;
    }

    @Override
    public void setHighscore(int highscore) {
        this.hightscore=highscore;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level=level;

    }

    @Override
    public void moveSpaceship() {
        if(isLightning){
            spaceShip.setSpeed(10);
            counterTimeLightning++;
        }
        if(counterTimeLightning >500){
            isLightning=false;
            spaceShip.setSpeed(Config.getInstance().getSpaceshipVelocity());
        }
        // Left arrow key press
        if (spaceShip.getxCoor() > 0) {
            if (this.getKeyStatus(37)) {
                spaceShip.setxCoor(spaceShip.getxCoor() - spaceShip.getSpeed());
            }
        }
        // Right arrow key press
        if (spaceShip.getxCoor() < 528) {
            if (this.getKeyStatus(39)) {
                spaceShip.setxCoor(spaceShip.getxCoor() + spaceShip.getSpeed());
            }
        }
        // Top arrow key press
        if (spaceShip.getyCoor() > 500) {
            if (this.getKeyStatus(38)) {
                spaceShip.setyCoor(spaceShip.getyCoor() - spaceShip.getSpeed());
            }
        }
        // Down arrow key press
        if (spaceShip.getyCoor() < 634) {
            if (this.getKeyStatus(40)) {
                spaceShip.setyCoor(spaceShip.getyCoor() + spaceShip.getSpeed());
            }
        }
    }

    @Override
    public ArrayList<Beam> getBeamList() {
        return beamList;
    }

    @Override
    public void clearBeamList(){
        beamList.clear();
    }

    @Override
    public void clearEnemyList(){
        enemyList.clear();
    }

    @Override
    public ArrayList<Bullet> getBulletList(){
        return bulletList;
    }

    @Override
    public void moveBullet() {
        for(int i=0; i<bulletList.size(); i++){
            bulletList.get(i).setyCoor(bulletList.get(i).getyCoor()-15);
            if (bulletList.get(i).getyCoor() < 0) {
                bulletList.remove(i);
            }
        }
    }

    @Override
    public void theBulletWasLaunched() {
        counterDelayBullet++;
        if(counterDelayBullet>40){
            if (this.getKeyStatus(88)) {
                if(isBulletPlus){
                    plusBullet=new Bullet(spaceShip.getxCoor() + 30, spaceShip.getyCoor());
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playShotBullet();
                    }
                    counterDelayBullet = 0;
                    isBulletPlus=false;
                }else {
                    Bullet bullet = new Bullet(spaceShip.getxCoor() + 30, spaceShip.getyCoor());
                    bulletList.add(bullet);
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playShotBullet();
                    }
                    counterDelayBullet = 0;
                }
            }
        }
    }

    @Override
    public void movePlusBullet() {
        if(plusBullet!=null){
            plusBullet.setyCoor(plusBullet.getyCoor()-15);
            if(plusBullet.getyCoor()<0){
                plusBullet=null;
            }
        }
    }

    @Override
    public Bullet getPlusBullet(){
        return plusBullet;
    }

    @Override
    public void setKeyStatus(int keycode, boolean result) {
        keyStatus[keycode]=result;
    }

    @Override
    public void resetController() {
        keyStatus = new boolean[256];
    }

    @Override
    public boolean getKeyStatus(int keyCode) {
        if(keyCode < 0 || keyCode > 255)
        {
            return false;
        }
        else
        {
            return keyStatus[keyCode];
        }
    }

    @Override
    public void handleRestartEvent() {
        this.setLevel(1);
        this.setScore(0);
        this.setLives(3);
        View.getInstance().updateLivesLabel(3);
        View.getInstance().updateScoreLabel(0);
        View.getInstance().updateLevelLabel(1);
        View.getInstance().updateHighScore();
        this.clearBeamList();
        this.clearEnemyList();
        this.resetController();
        this.resetPowerUp();
        this.deleteBoss();
        this.resetCounterHealthBoss();
        this.resetSpecialEnemy();
        View.getInstance().hideProgressBar();
        Config.getInstance().WriteXml();
        this.setupGame();
    }

    @Override
    public void handleOkButton() {
        this.setLevel(Logic.getInstance().getLevel()+1);
        this.setLives(3);
        View.getInstance().updateLivesLabel(3);
        View.getInstance().updateHighScore();
        View.getInstance().updateLevelLabel(Logic.getInstance().getLevel()+1);
        this.clearBeamList();
        this.clearEnemyList();
        this.resetController();
        this.resetPowerUp();
        this.resetSpecialEnemy();
        this.setupGame();
        if(this.getLevel()==3){
            View.getInstance().showProgressBar();
        }
    }

    @Override
    public void handleStartGameEvent(String playerName, String spaceShipSelected, String backgroundSelected) {
        this.setPlayerName(playerName);
        this.setspaceShipSelected(spaceShipSelected);
        this.setbackgroundSelected(backgroundSelected);
        this.setScore(0);
        this.setHighscore(Config.getInstance().getHisghscore());
        this.setLevel(1);
        this.setLives(3);
        View.getInstance().closeOptionsPanel();
        View.getInstance().openMainGUI();
        Config.getInstance().setPlayer(playerName, 0);
        Config.getInstance().WriteXml();
    }

    @Override
    public void theBeamWasLaunched() {
        Random randomBeam = new Random();
        Beam beam;
        for (int index = 0; index < enemyList.size(); index++) {
            if (randomBeam.nextInt(18) == index) {
                if(theBeamCanBeLaunched) {
                    beam = new Beam(enemyList.get(index).getxCoor() + 18, enemyList.get(index).getyCoor() + 30);
                    beamList.add(beam);
                }
            }
            if(beamList.size()>3){
                theBeamCanBeLaunched=false;
            }
            else {
                theBeamCanBeLaunched=true;
            }
        }
        if(level==3){
            if(beamList.size()>=3){
                theBeamCanBeLaunched=false;
            }else {
                theBeamCanBeLaunched=true;
            }
            if(theBeamCanBeLaunched) {
                beam = new Beam(getBoss().getxCoor() + 18, getBoss().getyCoor() + 127);
                Beam beam1 = new Beam(getBoss().getxCoor() + 48, getBoss().getyCoor() + 127);
                Beam beam2 = new Beam(getBoss().getxCoor() + 98, getBoss().getyCoor() + 127);
                beamList.add(beam);
                beamList.add(beam1);
                beamList.add(beam2);
            }
        }
    }

    @Override
    public void moveBeam(){
        for(int i=0; i<beamList.size(); i++){
            beamList.get(i).setyCoor(beamList.get(i).getyCoor()+5);
            if (beamList.get(i).getyCoor() >800) {
                beamList.remove(i);
            }
        }
    }

    @Override
    public void addShields() {
        shieldList=new ArrayList<>();
        if(this.level==1) {
            for (int row = 0; row < 2; row++) {
                for (int column = 0; column < 2; column++) {
                    Shield shield = new Shield(130 + (column * 250), 475 - (row * 10), "gray");
                    shieldList.add(shield);
                }
            }
        }
    }

    @Override
    public ArrayList<Shield> getShieldList() {
        return shieldList;
    }

    @Override
    public void addEnemies() {
        enemyList=new ArrayList<>();
        if(level<=2) {
            // three rows
            for (int row = 0; row < 5; row++) {
                // six columns
                for (int column = 0; column < 8; column++) {
                    Enemy enemy = new Enemy((20 + (column * 55)), (20 + (row * 60)), level, 0, "enemy" + String.valueOf(typeOfEnemy)); // Enemy speed will increase each level
                    if(typeOfEnemy==0) {
                        enemy.insertEnemy1();
                    }else if(typeOfEnemy==1){
                        enemy.insertEnemy2();
                    }else if(typeOfEnemy==2){
                        enemy.insertEnemy3();
                    }else if(typeOfEnemy==3){
                        enemy.insertEnemy4();
                    }else if(typeOfEnemy==4){
                        enemy.insertEnemy5();
                    }
                    enemyList.add(enemy);
                }
                typeOfEnemy++;
            }
            typeOfEnemy=0;
        }
    }

    @Override
    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }

    @Override
    public void moveEnemiesHorizontalAndVertical() {
        if(enemyList.size()>0) {
            for(int i=0; i<enemyList.size(); i++){
                if (direction.equals("RIGHT")) {
                    enemyList.get(i).moveRight();
                }
                if (direction.equals("LEFT")) {
                    enemyList.get(i).moveLeft();
                }
            }
            String changeDirection="N";
            for (Enemy enemy : enemyList) {
                if (enemy.getxCoor() + (enemy.getWidth() * 9) > 600) {
                    direction = "LEFT";
                    changeDirection = "Y";
                }
                if (enemy.getxCoor() < 0) {
                    direction = "RIGHT";
                    changeDirection = "Y";
                }
            }
           if (changeDirection.equals("Y")){
               for (Enemy enemy : enemyList) {
                   enemy.setyCoor(enemy.getyCoor() + 10);
               }
           }
        }
    }

    @Override
    public void checksCollisionsWithShieldAndBullets() {
        for(int i=0; i<bulletList.size(); i++) {
            for (int index = 0; index < shieldList.size(); index++) {
                if (bulletList.get(i).isColliding(shieldList.get(index))) {
                    if(!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playExplosionBullet();
                    }
                    // Each if statement changes color of the shield, indicating "strength"
                    // STRONG
                    if (shieldList.get(index).getColor().equals("gray")) {
                        shieldList.get(index).setColor("yellow");
                        bulletList.get(i).setyCoor(-10);
                        bulletList.get(i).setxCoor(-10);
                        // GOOD
                    }else if (shieldList.get(index).getColor().equals("yellow")) {
                        shieldList.get(index).setColor("orange");
                        bulletList.get(i).setyCoor(-10);
                        bulletList.get(i).setxCoor(-10);
                        // WEAK, BREAKS ON HIT
                    }else if (shieldList.get(index).getColor().equals("orange")) {
                        shieldList.get(index).setColor("red");
                        bulletList.get(i).setyCoor(-10);
                        bulletList.get(i).setxCoor(-10);
                        // OKAY
                    }else if (shieldList.get(index).getColor().equals("red")) {
                        bulletList.get(i).setyCoor(-10);
                        bulletList.get(i).setxCoor(-10);
                        shieldList.remove(index);
                        // WEAK, BREAKS ON HIT
                    }
                }
            }
        }
    }

    @Override
    public void checkCollisionWithBulletPlusAndEnemies(){
        if(plusBullet!=null) {
            for (int i = 0; i < enemyList.size(); i++) {
                if (plusBullet.isColliding(enemyList.get(i))) {
                    enemyList.remove(i);
                    this.incrementScore(100);
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playExplosionBullet();
                    }
                }
            }
        }
        if(hightscore<score){
            this.setHighscore(score);
        }
    }

    @Override
    public void checksCollisionsWithEnemiesAndBullets(){
        for(int i=0; i<bulletList.size(); i++) {
            for (int j=0; j<enemyList.size(); j++) {
                if(bulletList.get(i).isColliding(enemyList.get(j))) {
                    bulletList.get(i).setxCoor(-10);
                    bulletList.get(i).setxCoor(-10);
                    enemyList.remove(j);
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playExplosionBullet();
                    }
                    this.incrementScore(100);
                }
            }
        }
        if(hightscore<score){
            this.setHighscore(score);
        }
    }

    @Override
    public void checksCollisionsWithBeamsAndShields(){
        for(int i=0; i<beamList.size(); i++) {
            for (int index = 0; index < shieldList.size(); index++) {
                if (beamList.get(i).isColliding(shieldList.get(index))) {
                    // Each if statement changes color of the shield, indicating "strength"
                    // STRONG
                    if (shieldList.get(index).getColor().equals("gray")) {
                        shieldList.get(index).setColor("yellow");
                        beamList.get(i).setyCoor(-10);
                        beamList.get(i).setxCoor(-10);
                        // GOOD
                    }else if (shieldList.get(index).getColor().equals("yellow")) {
                        shieldList.get(index).setColor("orange");
                        beamList.get(i).setyCoor(-10);
                        beamList.get(i).setxCoor(-10);
                        // WEAK, BREAKS ON HIT
                    }else if (shieldList.get(index).getColor().equals("orange")) {
                        shieldList.get(index).setColor("red");
                        beamList.get(i).setyCoor(-10);
                        beamList.get(i).setxCoor(-10);
                        // OKAY
                    }else if (shieldList.get(index).getColor().equals("red")) {
                        beamList.get(i).setyCoor(-10);
                        beamList.get(i).setxCoor(-10);
                        shieldList.remove(index);
                        // WEAK, BREAKS ON HIT
                    }
                }
            }
        }
    }

    @Override
    public void checksCollisionsWithBeamsAndBullet(){
        for (Bullet bullet : bulletList) {
            for (Beam beam : beamList) {
                if (bullet.isColliding(beam)) {
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playExplosionBullet();
                    }
                    beam.setyCoor(-10);
                    beam.setxCoor(-10);
                    bullet.setyCoor(-10);
                    bullet.setxCoor(-10);
                }
            }
        }
    }

    @Override
    public void checksCollisionsWithBeamsAndSpaceship(){
        for (Beam beam : beamList) {
            if (spaceShip.isColliding(beam)) {
                if (isShields) {
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playExplosionBeam();
                    }
                    beam.setyCoor(-10);
                    beam.setxCoor(-10);
                    isShields = false;
                } else {
                    this.decrementLives(1);
                    beam.setyCoor(-10);
                    beam.setxCoor(-10);
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playExplosionBeam();
                    }
                }
            }
        }
    }

    @Override
    public void checksCollisionsWithEnemiesAndShields() {
        for (Enemy enemy : enemyList) {
            for (int j = 0; j < shieldList.size(); j++) {
                if (enemy.isColliding(shieldList.get(j))) {
                    shieldList.remove(j);
                }
            }
        }
    }

    @Override
    public void checksCollisionsWithBulletAndPowerUp(){
        if(powerUp!=null) {
            if (spaceShip.isColliding(powerUp)) {
                if(!Sound.getInstance().isStopAll()) {
                    Sound.getInstance().playExplosionBullet();
                }
                switch (View.getInstance().getNameOfPowerUp()) {
                    case "lightning":
                        isLightning = true;
                        powerUp = null;
                        break;
                    case "shield":
                        isShields = true;
                        powerUp = null;
                        break;
                    case "bulletplus":
                        isBulletPlus = true;
                        powerUp = null;
                        break;
                }
            }
        }
    }

    @Override
    public void checksCollisionsWithBeamsAndPowerUp() {
        for (int i = 0; i < beamList.size(); i++) {
            if (powerUp!=null) {
                if (beamList.get(i).isColliding(powerUp)) {
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playExplosionBeam();
                    }
                    switch (View.getInstance().getNameOfPowerUp()) {
                        case "lightning":
                            isLightning = false;
                            powerUp = null;
                            break;
                        case "shield":
                            isShields = false;
                            powerUp = null;
                            break;
                        case "bulletplus":
                            isBulletPlus = false;
                            powerUp = null;
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void checksCollisionsWithBulletAndBoss(){
        for (Bullet bullet : bulletList) {
            if (this.getBoss() != null) {
                if (bullet.isColliding(this.getBoss())) {
                    bullet.setxCoor(-10);
                    counterHealthBoss++;
                    View.getInstance().updateProgressBar(counterHealthBoss);
                    this.incrementScore(300);
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playExplosionBullet();
                    }
                    if(hightscore<score){
                        this.setHighscore(score);
                    }
                }
            }
        }
    }

    @Override
    public boolean checksCollisionsWithEnemiesAndSpaceship(){
        boolean iscolliding=false;
        for (Enemy enemy : enemyList) {
            if (enemy.isColliding(spaceShip)) {
                iscolliding = true;
            }
        }
        return iscolliding;
    }

    @Override
    public boolean checkCollisionWithTheBottom(){
        boolean iscolliding=false;
        for (Enemy enemy : enemyList) {
            if (enemy.getyCoor() + 40 > 700) {
                iscolliding = true;
            }
        }
        return iscolliding;
    }

    @Override
    public boolean checkCollisionWithBossAndSpaceship() {
        boolean iscolliding=false;
        if(getBoss()!=null) {
            if (spaceShip.isColliding(getBoss())) {
                iscolliding = true;
                if (!Sound.getInstance().isStopAll()) {
                    Sound.getInstance().playExplosionBullet();
                }
            }
        }
        return iscolliding;
    }

    @Override
    public void checkCollisionWithSpecialEnemyAndSpaceship() {
        if(specialEnemy !=null) {
            if (specialEnemy.isColliding(spaceShip)) {
                if (isShields) {
                    counterTimeSpecialEnemy = 500;
                    specialEnemy = null;
                    trajectoryEnemyCounter = 0;
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playExplosionBullet();
                    }
                    isShields=false;
                } else {
                    this.decrementLives(1);
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playExplosionBullet();
                    }
                    counterTimeSpecialEnemy = 500;
                    specialEnemy = null;
                    trajectoryEnemyCounter = 0;
                }
            }
        }
    }

    @Override
    public void checkCollisionWithBulletAndSpecialEnemy() {
        if(specialEnemy !=null) {
            for (Bullet bullet : bulletList) {
                if (bullet.isColliding(specialEnemy)) {
                    bullet.setyCoor(-10);
                    bullet.setxCoor(-10);
                    specialEnemy = null;
                    counterTimeSpecialEnemy = 500;
                    trajectoryEnemyCounter = 0;
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playExplosionBullet();
                    }
                }
            }
        }
    }

    @Override
    public void checkCollisionWithShieldsAndSpecialEnemy() {
        for (int index = 0; index < shieldList.size(); index++) {
            if(specialEnemy !=null) {
                if (specialEnemy.isColliding(shieldList.get(index))) {
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playExplosionBeam();
                    }
                    // Each if statement changes color of the shield, indicating "strength"
                    // STRONG
                    if (shieldList.get(index).getColor().equals("gray")) {
                        shieldList.get(index).setColor("yellow");
                        specialEnemy = null;
                        counterTimeSpecialEnemy = 500;
                        trajectoryEnemyCounter = 0;
                        // GOOD
                    } else if (shieldList.get(index).getColor().equals("yellow")) {
                        shieldList.get(index).setColor("orange");
                        specialEnemy = null;
                        counterTimeSpecialEnemy = 500;
                        trajectoryEnemyCounter = 0;
                        // WEAK, BREAKS ON HIT
                    } else if (shieldList.get(index).getColor().equals("orange")) {
                        shieldList.get(index).setColor("red");
                        specialEnemy = null;
                        counterTimeSpecialEnemy = 500;
                        trajectoryEnemyCounter = 0;
                        // OKAY
                    } else if (shieldList.get(index).getColor().equals("red")) {
                        specialEnemy = null;
                        counterTimeSpecialEnemy = 500;
                        trajectoryEnemyCounter = 0;
                        shieldList.remove(index);
                        // WEAK, BREAKS ON HIT
                    }
                }
            }
        }
    }

    @Override
    public void checkCollisionWithBulletPlusAndSpecialEnemy(){
        if(plusBullet!=null && specialEnemy !=null) {
            if (plusBullet.isColliding(specialEnemy)) {
                specialEnemy = null;
                counterTimeSpecialEnemy = 500;
                trajectoryEnemyCounter = 0;
                if (!Sound.getInstance().isStopAll()) {
                    Sound.getInstance().playExplosionBullet();
                }
            }
        }
    }

    @Override
    public void checksCollisionsWithBulletPlusAndBoss() {
        if(plusBullet!=null && this.getBoss()!=null) {
            if (plusBullet.isColliding(this.getBoss())) {
                plusBullet.setxCoor(-10);
                counterHealthBoss += 5;
                View.getInstance().updateProgressBar(counterHealthBoss);
                this.incrementScore(600);
                if (!Sound.getInstance().isStopAll()) {
                    Sound.getInstance().playExplosionBullet();
                }
                if (hightscore < score) {
                    this.setHighscore(score);
                }
            }
        }
    }

    @Override
    public void checkCollisionWithBulletPlusAndBeams() {
        if(plusBullet!=null) {
            for (int i = 0; i < beamList.size(); i++) {
                if (plusBullet.isColliding(beamList.get(i))) {
                    if (!Sound.getInstance().isStopAll()) {
                        Sound.getInstance().playExplosionBullet();
                    }
                    beamList.get(i).setyCoor(-10);
                    beamList.get(i).setxCoor(-10);
                }
            }
        }
    }


    @Override
    public void setPowerUp(){
        Random c=new Random();
        Random randomX = new Random();
        Random randomY = new Random();
        int a= randomX.nextInt(550);
        int b= randomY.nextInt(122 + 1) + 528;
        if(this.level==1) {
            if (this.getScore() > 2000 && counterPowerUp == 0 && a != spaceShip.getxCoor() && b != spaceShip.getyCoor()) {
                powerUp = new PowerUp(a, b);
                View.getInstance().setPowerupImage(c.nextInt(3));
                counterPowerUp++;
            }
        }else if(this.level==2) {
            if (this.getScore() > 6000 && counterPowerUp == 0 && a != spaceShip.getxCoor() && b != spaceShip.getyCoor()) {
                powerUp = new PowerUp(a, b);
                View.getInstance().setPowerupImage(c.nextInt(3));
                counterPowerUp++;
            }
        }else if(this.level==3) {
            if (this.getScore() > 10000 && counterPowerUp == 0 && a != spaceShip.getxCoor() && b != spaceShip.getyCoor()) {
                powerUp = new PowerUp(a, b);
                View.getInstance().setPowerupImage(c.nextInt(3));
                counterPowerUp++;
            }
        }
    }

    @Override
    public PowerUp getPowerUp(){
        return powerUp;
    }

    @Override
    public boolean isShield() {
        return isShields;
    }

    @Override
    public void setupGame() {
        View.getInstance().setSpaceshipImage(Logic.getInstance().getspaceShipSelected());
        spaceShip=new SpaceShip(300,634);
        this.addShields();
        this.addEnemies();
        this.addBoss();
    }

    @Override
    public void next() {
        if(this.isGameOver()){
            View.getInstance().gameOverDialog();
        }else if(this.isLevelUp()) {
            View.getInstance().openLevelUp();
        }else if(this.itIsVictory()){
            View.getInstance().openWinForm();
        }
        else{
            // Move SpaceShip
            this.moveSpaceship();
            // Check if the bullet has been launched
            this.theBulletWasLaunched();
            // Move Bullet
            this.moveBullet();
            // Makes enemies move and change direction at borders
            this.moveEnemiesHorizontalAndVertical();
            // Check if the beam has been launched
            this.theBeamWasLaunched();
            // Move Beam
            this.moveBeam();
            //set the power up object
            this.setPowerUp();
            //move boss enemy in the 8 shape
            this.moveBossIn8Shape();
            //move big Bullet
            this.movePlusBullet();
            //assign aliens other trajectories
            this.moveEnemyOnTheOndulatoryTrajectory();
            // Checks for collisions with shield and bullets
            this.checksCollisionsWithShieldAndBullets();
            // Checks for collisions with enemies and bullets
            this.checksCollisionsWithEnemiesAndBullets();
            // Checks for collisions with enemies and bullets
            this.checksCollisionsWithEnemiesAndShields();
            // Checks for collisions with enemies and bullets
            this.checksCollisionsWithBeamsAndShields();
            // Checks for collisions with beams and bullets
            this.checksCollisionsWithBeamsAndBullet();
            // Checks for collisions with beams and Spaceship
            this.checksCollisionsWithBeamsAndSpaceship();
            //check collision with bullets and boss
            this.checksCollisionsWithBulletAndBoss();
            // Checks for collisions with bullet and power up
            this.checksCollisionsWithBulletAndPowerUp();
            // Checks for collisions with beams and power up
            this.checksCollisionsWithBeamsAndPowerUp();
            //check collision with special enemy and spaceship
            this.checkCollisionWithSpecialEnemyAndSpaceship();
            //check collision with bullet and special enemy
            this.checkCollisionWithBulletAndSpecialEnemy();
            //check collision with shield and special enemy
            this.checkCollisionWithShieldsAndSpecialEnemy();
            //check collision with Big Bullet and enemies
            this.checkCollisionWithBulletPlusAndEnemies();
            //check collision with Big Bullet and special enemies
            this.checkCollisionWithBulletPlusAndSpecialEnemy();
            //check collision with Big Bullet and boss
            this.checksCollisionsWithBulletPlusAndBoss();
            //check collision with Big Bullet and beams
            this.checkCollisionWithBulletPlusAndBeams();
            //check esc click
            this.clickEsc();
        }
    }

    public void clickEsc(){
        if(this.getKeyStatus(27)){
            View.getInstance().openEscPressForm();
            this.resetController();
        }
    }

    @Override
    public SpaceShip getSpaceShip() {
        return spaceShip;
    }

    @Override
    public void resetPowerUp(){
        counterDelayBullet=0;
        counterPowerUp=0;
        counterTimeLightning=0;
        powerUp=null;
        isBulletPlus=false;
        plusBullet=null;
        isShields =false;
        isLightning=false;
    }

    @Override
    public void addBoss() {
        if(this.getLevel()==3){
            boss = new Boss(355,305);
        }
    }

    @Override
    public void moveBossIn8Shape() {
        if(this.getBoss()!=null) {
            boss.setxCoor(bossPointlist.get(trajectoryBossCounter).getX());
            boss.setyCoor(bossPointlist.get(trajectoryBossCounter).getY());
            trajectoryBossCounter +=2;
            if (trajectoryBossCounter > bossPointlist.size()-1) {
                trajectoryBossCounter = 0;
            }
        }
    }

    public void moveEnemyOnTheOndulatoryTrajectory(){
        Random randomEnemy = new Random();
        Random randomTrajectory = new Random();
        if(this.getLevel()==1) {
            if (counterTimeSpecialEnemy >= 1000 && specialEnemy ==null) {
                int random = randomEnemy.nextInt(enemyList.size());
                trajectoryPointList = trajectories.getFirstTrajectory(enemyList.get(random).getxCoor(), enemyList.get(random).getyCoor());
                if(trajectories.getXMax(trajectoryPointList)<555 && trajectories.getXMin(trajectoryPointList)>0) {
                    specialEnemy = enemyList.get(random);
                    enemyList.remove(random);
                    counterTimeSpecialEnemy = 0;
                }
            }
            if (specialEnemy != null) {
                if (trajectoryEnemyCounter < trajectoryPointList.size()) {
                    specialEnemy.setxCoor(trajectoryPointList.get(trajectoryEnemyCounter).getX());
                    specialEnemy.setyCoor(trajectoryPointList.get(trajectoryEnemyCounter).getY());
                    trajectoryEnemyCounter+=2;
                    if(specialEnemy.getyCoor()>700){
                        specialEnemy = null;
                        trajectoryEnemyCounter = 0;
                    }
                }
            }
            counterTimeSpecialEnemy++;
        }else if(this.getLevel()==2){
            if (counterTimeSpecialEnemy >= 900 && specialEnemy ==null) {
                int random = randomEnemy.nextInt(enemyList.size());
                int randomTrajectorySelected= randomTrajectory.nextInt(3);
                trajectoryPointList = trajectories.getRandomTrajectory(randomTrajectorySelected,enemyList.get(random).getxCoor(), enemyList.get(random).getyCoor());
                if(trajectories.getXMax(trajectoryPointList)<555 && trajectories.getXMin(trajectoryPointList)>0) {
                    specialEnemy = enemyList.get(random);
                    enemyList.remove(random);
                    counterTimeSpecialEnemy = 0;
                }
            }
            if (specialEnemy != null) {
                if (trajectoryEnemyCounter < trajectoryPointList.size()) {
                    specialEnemy.setxCoor(trajectoryPointList.get(trajectoryEnemyCounter).getX());
                    specialEnemy.setyCoor(trajectoryPointList.get(trajectoryEnemyCounter).getY());
                    trajectoryEnemyCounter+=2;
                    if(specialEnemy.getyCoor()>700){
                        specialEnemy = null;
                        trajectoryEnemyCounter = 0;
                    }
                }
            }
            counterTimeSpecialEnemy++;
        }
    }

    @Override
    public Boss getBoss() {
        return boss;
    }

    @Override
    public void deleteBoss(){
        boss=null;
    }

    @Override
    public int getCounterHealthBoss(){
        return counterHealthBoss;
    }

    @Override
    public void resetCounterHealthBoss() {
        counterHealthBoss=0;
    }

    @Override
    public void resetSpecialEnemy() {
        counterTimeSpecialEnemy =500;
        specialEnemy =null;
        trajectoryEnemyCounter=0;
    }

    @Override
    public Enemy getSpecialEnemy() {
        return specialEnemy;
    }

    //---------------------------------------------------------------
    // PRIVATE ISTANCE METHODS
    //---------------------------------------------------------------

    private boolean isGameOver() {
        boolean gameOver = false;
        if (this.getLives()<=0 || this.checksCollisionsWithEnemiesAndSpaceship() || this.checkCollisionWithTheBottom() || this.checkCollisionWithBossAndSpaceship()) {
            gameOver = true;
        }
        return gameOver;
    }

    private boolean isLevelUp() {
        boolean levelup = false;
        if(this.level<=2) {
            if (this.enemyList.size() == 0 && this.specialEnemy ==null) {
                levelup = true;
            }
        }
        return levelup;
    }

    private boolean itIsVictory(){
        boolean youWon=false;
        if(counterHealthBoss >=30){
            youWon=true;
        }
        return youWon;
    }

    //---------------------------------------------------------------
    // STATIC METHODS
    //---------------------------------------------------------------
    public static ILogic getInstance() {
        if (instance == null)
            instance = new Logic();
        return instance;
    }
}