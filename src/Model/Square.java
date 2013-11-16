package Model;

public class Square {

    private int adjacentMines;
    private boolean mine;

    public Square(boolean isMine) {
        adjacentMines = 0;
        mine = isMine;
    }
    
    public Square(Square square){
        this.adjacentMines = square.adjacentMines;
        this.mine = square.mine;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }
    
    public int getAdjacentMines() {
        return adjacentMines;
    }
    

}
