package SpaceClash.logic;

import SpaceClash.utils.Config;

public class SpaceShip extends GameObject{

    public SpaceShip(int xCor, int yCor) {
        super(xCor,yCor, Config.getInstance().getSpaceshipVelocity(),0, 60,60);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getxCoor(), this.getyCoor(), this.getWidth(), this.getHeight());
    }

}




