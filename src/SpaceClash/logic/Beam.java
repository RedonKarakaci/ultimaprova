package SpaceClash.logic;

public class Beam extends GameObject {

    public Beam(int xCor, int yCor) {
        super(xCor,yCor,0,0, 7,15);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getxCoor(), this.getyCoor(), this.getWidth(), this.getHeight());
    }
}
