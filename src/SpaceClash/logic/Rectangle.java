package SpaceClash.logic;

public class Rectangle {

    private int xCor;
    private int yCor;
    private int width;
    private int height;

    public Rectangle(){ }

    public Rectangle(int xCor, int yCor, int width, int height){
        this.xCor=xCor;
        this.yCor=yCor;
        this.width=width;
        this.height=height;
    }

    //Given the two rectangles R1 and R2, aligned with the axes, you can check the intersection as a verification of the intersection of the projections of the two rectangles on the axes. Which translates into a check of interesection between two pairs of segments in a one-dimensional space:
    public boolean intersects(Rectangle bounds) {
        boolean xint=(this.xCor>=bounds.xCor && this.xCor<bounds.xCor+bounds.width) || (bounds.xCor>= this.xCor && bounds.xCor< this.xCor+this.width);
        boolean yint=(this.yCor>=bounds.yCor && this.yCor<bounds.yCor+bounds.height) || (bounds.yCor>= this.yCor && bounds.yCor< this.yCor+this.height);
        return xint && yint;
    }
}
