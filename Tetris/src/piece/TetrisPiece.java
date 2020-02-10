package piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class TetrisPiece {
    //not sure if we had to implement only the five figures given in the task or all of them

    private Point navigationPoint;
    private List<Block> blockList;
    private List<Integer> rotationCounter = new ArrayList<>();

    public TetrisPiece(Point navigationPoint, List<Block> blockList) {
        this.blockList = blockList;
        this.navigationPoint = navigationPoint;
        rotate();
    }

    public Point getNavigationPoint() {
        return navigationPoint;
    }

    public void setNavigationPoint(Point navigationPoint) {
        this.navigationPoint = navigationPoint;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<Block> blockList) {
        this.blockList = blockList;
    }


    public abstract void rotate();

    public void moveDown(){
        blockList.forEach(block -> block.setBlockPoint(
                new Point(block.getBlockPoint().x,
                        block.getBlockPoint().y+1)));

        navigationPoint = new Point(navigationPoint.x,navigationPoint.y+1);

    }

    public void move(boolean movement){
        if (!movement){
            blockList.forEach(block -> block.setBlockPoint(
                    new Point(block.getBlockPoint().x-1,
                            block.getBlockPoint().y)));

            navigationPoint = new Point(navigationPoint.x-1,navigationPoint.y);
        }
        if (movement){
            blockList.forEach(block -> block.setBlockPoint(
                    new Point(block.getBlockPoint().x+1,
                            block.getBlockPoint().y)));

            navigationPoint = new Point(navigationPoint.x+1,navigationPoint.y);
        }
    }

    public int getRotation(){
        rotationCounter.add(0);
        if (rotationCounter.size()== 5){
            rotationCounter = new ArrayList<>();
        }
        return rotationCounter.size();
    }
}
