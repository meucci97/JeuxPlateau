package jeuxplateau.Controlleur;

import jeuxplateau.Modele.Piece;
import jeuxplateau.Modele.Tetris;

public class TetrisControlleur {
    private Tetris monTetris;

    public TetrisControlleur(Tetris monTetris) {
        this.monTetris=monTetris;
    }

    public void setMonTetris(Tetris monTetris) {
        this.monTetris = monTetris;
    }

    public boolean clickLeft(Piece piece){
        int [][] maPiece= piece.getMatricePiece();
        int width=monTetris.getGrille().getWidth()  ;
        int height=monTetris.getGrille().getHeight();
        int tmp;
        for(int i=0; i<maPiece.length;i++){
            for(int j=0;j<=2;j++){
                if(maPiece[i][j]!=0){
                    tmp=piece.getPositionX()+(-2+j);
                    if(tmp<0){
                        return false;
                    }
                }
            }
        }
        return true;
    };

    public boolean clickRight(Piece piece){
        int [][] maPiece= piece.getMatricePiece();
        int width=monTetris.getGrille().getWidth() ;
        int height=monTetris.getGrille().getHeight();
        int tmp;
        for(int i=0; i<maPiece.length;i++){
            for(int j=2;j<maPiece[i].length;j++){
                if(maPiece[i][j]!=0){
                    tmp=height-(piece.getPositionX()+(j-piece.getPositionX()));
                    if(tmp<0){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean clickDown(Piece piece){
        return true;
    };

    public void borderCheck(Piece piece){

    };

    public boolean lineCheck(){

        return true;
    };
}
