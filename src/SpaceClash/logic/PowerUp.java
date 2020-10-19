package SpaceClash.logic;

public class PowerUp extends GameObject{

    public PowerUp(int xCoor, int yCoor){
        super(xCoor,yCoor,0,0, 50,50);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getxCoor(), this.getyCoor(), 50, 50);
    }
}
