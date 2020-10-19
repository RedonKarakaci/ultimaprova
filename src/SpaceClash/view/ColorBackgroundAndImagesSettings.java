package SpaceClash.view;
import SpaceClash.utils.Config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

public class ColorBackgroundAndImagesSettings {

    //---------------------------------------------------------------
    // STATIC ATTRIBUTE
    //---------------------------------------------------------------
    private static ColorBackgroundAndImagesSettings instance = null;

    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTE
    //---------------------------------------------------------------
    private BufferedImage backgroundImage;
    private Color colorRightPanelBackground;

    private ColorBackgroundAndImagesSettings(){
        try {
            this.setImageBackgroundBoard();
            this.setColorRightPanelBackground();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------------
    // PRIVATE INSTANCE METHODS
    //---------------------------------------------------------------
    private void setImageBackgroundBoard() throws Exception {
        switch (View.getInstance().getbackgroundSelected()) {
            case "Background1":
                backgroundImage = ImageIO.read(getClass().getResource("/images/background1.jpeg"));
                break;
            case "Background2":
                backgroundImage = ImageIO.read(getClass().getResource("/images/background2.jpg"));
                break;
            case "Background3":
                backgroundImage = ImageIO.read(getClass().getResource("/images/background3.jpg"));
                break;
        }
    }

    private void setColorRightPanelBackground() throws NoSuchFieldException, IllegalAccessException {
        Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorBackgroundRightPanel());
        this.colorRightPanelBackground = (Color)fieldObjectByString.get(null);
    }
    //---------------------------------------------------------------
    // PUBLIC INSTANCE METHODS
    //---------------------------------------------------------------

    public BufferedImage getImageBackgroundBoard(){
        return this.backgroundImage;
    }

    public Color getColorRightPanelBackground(){
        return this.colorRightPanelBackground;
    }


    //---------------------------------------------------------------
    // STATIC METHODS
    //---------------------------------------------------------------
    public static ColorBackgroundAndImagesSettings getInstance() {
        if (instance == null)
            instance = new ColorBackgroundAndImagesSettings();
        return instance;
    }
}
