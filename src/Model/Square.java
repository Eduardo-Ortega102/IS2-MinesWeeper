package Model;

public class Square {

    private int adjacentMines;
    private boolean hasMine;

    public Square(boolean hasMine) {
        this.adjacentMines = 0;
        this.hasMine = hasMine;
    }
    
    public boolean hasMine() {
        return hasMine;
    }

    public void setMine(boolean mine) {
        this.hasMine = mine;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }
    
    public int getAdjacentMines() {
        return adjacentMines;
    }
    

}
