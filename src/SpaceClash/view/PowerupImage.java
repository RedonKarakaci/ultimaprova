package SpaceClash.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PowerupImage {

    private BufferedImage powerUp;
    private String nameOfPowerUp;

    public void setPowerUpImage(int random){
        try {
            if (random==0) {
                powerUp = ImageIO.read(getClass().getResource("/images/lightning.png"));
                nameOfPowerUp ="lightning";
            } else if (random==1) {
                powerUp = ImageIO.read(getClass().getResource("/images/shield.png"));
                nameOfPowerUp ="shield";
            } else if (random==2) {
                powerUp = ImageIO.read(getClass().getResource("/images/bulletplus.png"));
                nameOfPowerUp ="bulletplus";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public BufferedImage getPowerUp() {
        return powerUp;
    }

    public String getNameOfPowerUp() {
        return nameOfPowerUp;
    }
}
