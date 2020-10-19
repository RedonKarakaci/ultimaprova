package SpaceClash.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SpaceShipImage {

    private BufferedImage spaceship;

    public SpaceShipImage(){
    }

    public void setSpaceshipImage(String spaceshipSelected){
        try {
            switch (spaceshipSelected) {
                case "SpaceShip1":
                    spaceship = ImageIO.read(getClass().getResource("/images/spaceship1.png"));
                    break;
                case "SpaceShip2":
                    spaceship = ImageIO.read(getClass().getResource("/images/spaceship2.png"));
                    break;
                case "SpaceShip3":
                    spaceship = ImageIO.read(getClass().getResource("/images/spaceship3.png"));
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public BufferedImage getSpaceshipImage() {
        return spaceship;
    }


}
