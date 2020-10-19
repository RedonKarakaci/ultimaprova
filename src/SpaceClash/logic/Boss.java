package SpaceClash.logic;

public class Boss extends GameObject{

    public Boss(int xCoor, int yCoor){
        super(xCoor,yCoor,0,0, 110,127);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getxCoor(), this.getyCoor(), this.getWidth(), this.getHeight());
    }

}
