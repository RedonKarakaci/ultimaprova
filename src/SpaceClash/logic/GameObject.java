package SpaceClash.logic;

public abstract class GameObject extends Rectangle{

    private int xCor;
    private int yCor;
    private int speed;
    private int xspeed;
    private int width;
    private int height;

    public GameObject(){}

    public GameObject(int xPosition, int yPosition, int speed, int xVelocity, int width, int height) {
        this.xCor = xPosition;
        this.yCor = yPosition;
        this.speed=speed;
        this.xspeed=xVelocity;
        this.width=width;
        this.height=height;
    }

    public abstract Rectangle getBounds();

    public int getxCoor() {
        return xCor;
    }

    public int getyCoor() {
        return yCor;
    }

    public void setxCoor(int xPosition) {
        this.xCor = xPosition;
    }

    public void setyCoor(int yPosition) {
        this.yCor = yPosition;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getXspeed() {
        return xspeed;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Checks if the hitboxes of any two objects are intersecting
    public boolean isColliding(GameObject other) {
        return other.getBounds().intersects(this.getBounds());
    }
}
