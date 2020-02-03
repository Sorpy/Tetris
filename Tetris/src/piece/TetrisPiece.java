package piece;

import config.Position;

public abstract class TetrisPiece {
    //not sure if we had to implement only the five figures given in the task or all of them

    private String color; //Each Piece will have a different color (S piece will be different from I piece and so on)
    private Position possition; // points to bottom left square of the piece

    public TetrisPiece(String color, Position possition) {
        this.color = color;
        this.possition = possition;
    }

    private boolean canMove() {
    return true; // logic for getting out of board boundaries
    }


    public void rotate(boolean rotation){
        if (!rotation){
            //rotate counter clockwise
        }
        if (rotation){
            //rotate clockwise
        }
    }

    public void move(boolean movement){
        if (!movement){
            //move left
        }
        if (movement){
            //move right
        }
    }

    public void downPress(boolean downPressed){
        if (downPressed){
            //move piece faster down
        }
    }

    public void display(){

    }

    public Position getPossition() {
        return possition;
    }

    public void setPossition(Position possition) {
        this.possition = possition;
    }
}
