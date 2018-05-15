package jeuxplateau.Controlleur;

import jeuxplateau.Modele.Piece;
import jeuxplateau.Modele.Tetris;

public class TetrisControlleur {
    private Tetris monTetris;

    public TetrisControlleur(Tetris monTetris) {
        this.monTetris=monTetris;
    }

    public boolean clickLeft(Piece piece, int x, int y){
        return true;
    };

    public boolean clickRight(Piece piece, int x, int y){
        return true;
    };

    public boolean clickDown(Piece piece, int x, int y){
        return true;
    };

    public void borderCheck(Piece piece, int x, int y){

    };

    public boolean lineCheck(){

        return true;
    };
}
