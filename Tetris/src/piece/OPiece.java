package piece;



import java.awt.*;
import java.util.List;

public class OPiece extends TetrisPiece {

    public OPiece(Point navigationPoint, List<Block> blockList) {
        super(navigationPoint, blockList);
    }
    @Override
    public void rotate() {
        int rotationNumber = getRotation();
        switch (rotationNumber){
            case 0:
            case 1:
            case 2:
            case 3:
                initPosition();
                break;
        }
    }
    private void initPosition() {
        this.getBlockList().get(0).setBlockPoint(new Point(getNavigationPoint().x + 1, getNavigationPoint().y+1));
        this.getBlockList().get(1).setBlockPoint(new Point(getNavigationPoint().x + 2, getNavigationPoint().y + 1));
        this.getBlockList().get(2).setBlockPoint(new Point(getNavigationPoint().x + 2, getNavigationPoint().y + 2));
        this.getBlockList().get(3).setBlockPoint(new Point(getNavigationPoint().x + 1, getNavigationPoint().y + 2));
    }

}
