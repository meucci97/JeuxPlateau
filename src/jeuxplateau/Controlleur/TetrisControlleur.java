package jeuxplateau.Controlleur;

import jeuxplateau.Modele.Case;
import jeuxplateau.Modele.Grille;
import jeuxplateau.Modele.Piece;
import jeuxplateau.Modele.Tetris;

import java.util.Vector;

public class TetrisControlleur {
    private Tetris monTetris;

    public TetrisControlleur(Tetris monTetris) {
        this.monTetris=monTetris;
    }

    public void setMonTetris(Tetris monTetris) {
        this.monTetris = monTetris;
    }

    public boolean clickLeftCheck(Piece piece){
        int [][] maPiece= piece.getMatricePiece();
        int width=monTetris.getGrille().getWidth()  ;
        int height=monTetris.getGrille().getHeight();
        int positionDansGrilleI=0;
        int positionDansGrilleJ=0;
        Grille maGrille=monTetris.getGrille();
        for(int i=0; i<maPiece.length;i++){
            for(int j=0;j<maPiece[i].length;j++){
                if(maPiece[i][j]!=0){
                    if(i<piece.getPointOrientation()[0]){
                       positionDansGrilleI=piece.getPositionX()-(i+1);
                    }else if(i==piece.getPointOrientation()[0]){
                        positionDansGrilleI=piece.getPositionX();
                    }
                    else{
                        positionDansGrilleI=piece.getPositionX()+(i+1);
                    }

                    if(j<piece.getPointOrientation()[1]){
                        positionDansGrilleJ=piece.getPositionY()-(j+1);
                    }else if(j==piece.getPointOrientation()[1]){
                        positionDansGrilleI=piece.getPositionY();
                    }else{
                        positionDansGrilleJ=piece.getPositionY()+(j+1);
                    }

                    if((positionDansGrilleJ-1)<0 || (positionDansGrilleJ-1)>=width){
                        return false;
                    }
                    if(!maGrille.getCase(positionDansGrilleI,positionDansGrilleJ-1).isIsEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean clickRightCheck(Piece piece){
        int [][] maPiece= piece.getMatricePiece();
        int width=monTetris.getGrille().getWidth()  ;
        int height=monTetris.getGrille().getHeight();
        int positionDansGrilleI=0;
        int positionDansGrilleJ=0;
        Grille maGrille=monTetris.getGrille();
        for(int i=0; i<maPiece.length;i++){
            for(int j=0;j<maPiece[i].length;j++){
                if(maPiece[i][j]!=0){
                    if(i<piece.getPointOrientation()[0]){
                        positionDansGrilleI=piece.getPositionX()-(i+1);
                    }else if(i==piece.getPointOrientation()[0]){
                        positionDansGrilleI=piece.getPositionX();
                    }
                    else{
                        positionDansGrilleI=piece.getPositionX()+(i+1);
                    }

                    if(j<piece.getPointOrientation()[1]){
                        positionDansGrilleJ=piece.getPositionY()-(j+1);
                    }else if(j==piece.getPointOrientation()[1]){
                        positionDansGrilleI=piece.getPositionY();
                    }else{
                        positionDansGrilleJ=piece.getPositionY()+(j+1);
                    }

                    if((positionDansGrilleJ+1)>=width || (positionDansGrilleJ+1)<0){
                        return false;
                    }
                    if(!maGrille.getCase(positionDansGrilleI,positionDansGrilleJ+1).isIsEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean clickDownCheck(Piece piece){
        int [][] maPiece= piece.getMatricePiece();
        int width=monTetris.getGrille().getWidth()  ;
        int height=monTetris.getGrille().getHeight();
        int positionDansGrilleI=0;
        int positionDansGrilleJ=0;
        Grille maGrille=monTetris.getGrille();
        for(int i=0; i<maPiece.length;i++){
            for(int j=0;j<maPiece[i].length;j++){
                if(maPiece[i][j]!=0){
                    if(i<piece.getPointOrientation()[0]){
                        positionDansGrilleI=piece.getPositionX()-(i+1);
                    }else if(i==piece.getPointOrientation()[0]){
                        positionDansGrilleI=piece.getPositionX();
                    }
                    else{
                        positionDansGrilleI=piece.getPositionX()+(i+1);
                    }

                    if(j<piece.getPointOrientation()[1]){
                        positionDansGrilleJ=piece.getPositionY()-(j+1);
                    }else if(j==piece.getPointOrientation()[1]){
                        positionDansGrilleI=piece.getPositionY();
                    }else{
                        positionDansGrilleJ=piece.getPositionY()+(j+1);
                    }

                    if((positionDansGrilleI+1)<0 || (positionDansGrilleI+1)>=height){
                        return false;
                    }
                    if(!maGrille.getCase(positionDansGrilleI+1,positionDansGrilleJ).isIsEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void borderCheck(Piece piece){

    }

    public boolean lineCheck(){

        return true;
    }
}
