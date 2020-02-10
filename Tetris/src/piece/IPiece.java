package piece;



import java.awt.*;
import java.util.List;

public class IPiece extends TetrisPiece {

    public IPiece(Point navigationPoint, List<Block> blockList) {
        super(navigationPoint,blockList);
    }

    @Override
    public void rotate() {
        int rotationNumber = getRotation();
        switch (rotationNumber){
            case 1:
                rotateLEFT();
                break;
            case 2:
                rotateDOWN();
                break;
            case 3:
                rotateRIGHT();
                break;
            case 0:
                rotateUP();
                break;
        }
    }
    private void rotateUP() {
        this.getBlockList().get(0).setBlockPoint(new Point(getNavigationPoint().x+1,getNavigationPoint().y));
        this.getBlockList().get(1).setBlockPoint(new Point(getNavigationPoint().x+1,getNavigationPoint().y+1));
        this.getBlockList().get(2).setBlockPoint(new Point(getNavigationPoint().x+1,getNavigationPoint().y+2));
        this.getBlockList().get(3).setBlockPoint(new Point(getNavigationPoint().x+1,getNavigationPoint().y+3));

    }

    private void rotateLEFT() {
        this.getBlockList().get(0).setBlockPoint(new Point(getNavigationPoint().x,getNavigationPoint().y+1));
        this.getBlockList().get(1).setBlockPoint(new Point(getNavigationPoint().x+1,getNavigationPoint().y+1));
        this.getBlockList().get(2).setBlockPoint(new Point(getNavigationPoint().x+2,getNavigationPoint().y+1));
        this.getBlockList().get(3).setBlockPoint(new Point(getNavigationPoint().x+3,getNavigationPoint().y+1));
    }

    private void rotateDOWN() {
        this.getBlockList().get(0).setBlockPoint(new Point(getNavigationPoint().x+2,getNavigationPoint().y+3));
        this.getBlockList().get(1).setBlockPoint(new Point(getNavigationPoint().x+2,getNavigationPoint().y+2));
        this.getBlockList().get(2).setBlockPoint(new Point(getNavigationPoint().x+2,getNavigationPoint().y+1));
        this.getBlockList().get(3).setBlockPoint(new Point(getNavigationPoint().x+2,getNavigationPoint().y));
    }

    private void rotateRIGHT() {
        this.getBlockList().get(0).setBlockPoint(new Point(getNavigationPoint().x,getNavigationPoint().y+2));
        this.getBlockList().get(1).setBlockPoint(new Point(getNavigationPoint().x+1,getNavigationPoint().y+2));
        this.getBlockList().get(2).setBlockPoint(new Point(getNavigationPoint().x+2,getNavigationPoint().y+2));
        this.getBlockList().get(3).setBlockPoint(new Point(getNavigationPoint().x+3,getNavigationPoint().y+2));
    }
}
