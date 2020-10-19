package SpaceClash.logic;

public class Shield extends GameObject {

    private String color;

    // Constructor for Shield objects
    public Shield(int xPosition, int yPosition, String color) {
        super(xPosition, yPosition,0,0, 110,10);
        this.color=color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getxCoor(), this.getyCoor(), this.getWidth(), this.getHeight());
    }
}
