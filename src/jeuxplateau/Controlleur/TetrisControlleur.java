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
    public void clickLeft(Piece piece){
        if(clickLeftCheck(piece)){
            this.monTetris.getMesPieces().firstElement().setPositionY(this.monTetris.getMesPieces().firstElement().getPositionY()-1);
            monTetris.notifyObsevateur();
        }
    }

    public void clickRight(Piece piece){
        if(clickRightCheck(piece)){
            this.monTetris.getMesPieces().firstElement().setPositionY(this.monTetris.getMesPieces().firstElement().getPositionY()+1);
            monTetris.notifyObsevateur();
        }
    }

    public void clickDown(Piece piece){
        if(clickDownCheck(piece)){
            this.monTetris.getMesPieces().firstElement().setPositionX(this.monTetris.getMesPieces().firstElement().getPositionX()+1);
            monTetris.notifyObsevateur();
        }else{
            // controle pour suppr les lignes
            // SI toutes cases d'une ligne complete -> suppression
            // Descendre les cases du dessus
            // scoring
        }

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
        //
        return true;
    }

    public boolean checkClearedBoard() {
        return false;
    }

    public boolean checkPiecePosee() {
        return false;
    }

    public int nbLignesEffacees() {
        return 0;
    }

    public void changeScore() {

        int newScore = monTetris.getScore();
        int levelIncrement = monTetris.getNiveau() + 1;

        if(checkClearedBoard()) {
            newScore += 2000 * levelIncrement;
            monTetris.setScore(newScore);
            return;
        }

        /*
        Points are scored using level + 1 so that points are still scored at level 0.
        1 Line = 50*(level + 1) points
        2 Lines = 150*(level + 1) points
        3 Lines = 350*(level + 1) points
        4 Lines = 1000*(level + 1) points (aka a Tetris)
        Clear the board = 2000*(level + 1)
        Every piece = 10*(level + 1) points
         */
    }
}
