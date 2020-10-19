package SpaceClash.logic;

public class Enemy extends GameObject{

    protected Enemy ENEMY_PIECE;
    protected int[][] enemyArray;
    protected String enemyName;

    public Enemy(int xPosition, int yPosition, int xVelocity, int yVelocity, String enemyName){
        super(xPosition, yPosition, 0, xVelocity, 4,4);
        this.enemyName=enemyName;
    }

    public Enemy() {

    }

    public void insertEnemy1(){
        ENEMY_PIECE = new Enemy1();
    }

    public void insertEnemy2(){
        ENEMY_PIECE = new Enemy2();
    }

    public void insertEnemy3(){
        ENEMY_PIECE = new Enemy3();
    }

    public void insertEnemy4(){
        ENEMY_PIECE=new Enemy4();
    }

    public void insertEnemy5(){
        ENEMY_PIECE=new Enemy5();
    }

    public String getEnemyName() {
        return enemyName;
    }

    public int[][] getEnemyArray() {
        return enemyArray;
    }

    public void moveRight() {
        this.setxCoor(this.getxCoor()+this.getXspeed());
    }

    public void moveLeft() {
        this.setxCoor(this.getxCoor()-this.getXspeed());
    }

    public Enemy getENEMY_PIECE() {
        return ENEMY_PIECE;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.getxCoor(), this.getyCoor(), 45, 40);
    }

}
