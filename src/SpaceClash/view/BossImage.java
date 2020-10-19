package SpaceClash.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class BossImage {

    private BufferedImage boss;

    public BossImage(){
        setBossImage();
    }

    public void setBossImage(){
        try {
            boss= ImageIO.read(getClass().getResource("/images/boss.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public BufferedImage getBoss() {
        return boss;
    }

}
