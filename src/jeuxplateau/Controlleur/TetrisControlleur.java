package jeuxplateau.Controlleur;

import jeuxplateau.Modele.Case;
import jeuxplateau.Modele.Grille;
import jeuxplateau.Modele.Piece;
import jeuxplateau.Modele.Tetris;

import java.util.TimerTask;
import java.util.Vector;

public class TetrisControlleur {

    private Tetris monTetris;

    public TetrisControlleur(Tetris monTetris) {
        this.monTetris=monTetris;
    }

    public void setMonTetris(Tetris monTetris) {
        this.monTetris = monTetris;
    }

    public void startGame(){
        monTetris.genererPieces();
        monTetris.getMesPieces().firstElement().setPositionX(-3);
        monTetris.getMesPieces().firstElement().setPositionY(5);
        monTetris.notifyObsevateur();
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
            if(!clickDownCheck(piece)){
                setNewGrilleValue(piece);
                monTetris.removeFirstPiece();
                monTetris.genererPieces();
                lineCheck();
                monTetris.getMesPieces().firstElement().setPositionX(-3);
                monTetris.getMesPieces().firstElement().setPositionY(5);
            }
            monTetris.notifyObsevateur();
        }else{
            // controle pour suppr les lignes
            // SI toutes cases d'une ligne complete -> suppression
            // Descendre les cases du dessus
            // scoring
            setNewGrilleValue(piece);
            monTetris.removeFirstPiece();
            monTetris.genererPieces();
            lineCheck();
            monTetris.getMesPieces().firstElement().setPositionX(-3);
            monTetris.getMesPieces().firstElement().setPositionY(5);
            monTetris.notifyObsevateur();
        }

    }

    public void setNewGrilleValue(Piece piece){
        int [][] maPiece= piece.getMatricePiece();
        int width=monTetris.getGrille().getWidth()  ;
        int height=monTetris.getGrille().getHeight();
        int positionDansGrilleI=0;
        int positionDansGrilleJ=0;
        Grille maGrille=monTetris.getGrille();
        for(int i=0; i<maPiece.length;i++){
            for(int j=0;j<maPiece[i].length;j++){
                if(maPiece[i][j]!=0){
                    positionDansGrilleI=getPositionDansGrilleI(piece,i);
                    positionDansGrilleJ=getPositionDansGrilleJ(piece,j);
                    monTetris.getGrille().getCase(positionDansGrilleI,positionDansGrilleJ).setCase(piece);
                }
            }
        }
    }
    public void clickEspace(Piece piece){
        if(pivotCheck(piece)){
            this.monTetris.getMesPieces().firstElement().pivoter();
            monTetris.notifyObsevateur();
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
                    positionDansGrilleI=getPositionDansGrilleI(piece,i);
                    positionDansGrilleJ=getPositionDansGrilleJ(piece,j);

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
                    positionDansGrilleI=getPositionDansGrilleI(piece,i);
                    positionDansGrilleJ=getPositionDansGrilleJ(piece,j);

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
                    positionDansGrilleI=getPositionDansGrilleI(piece,i);
                    positionDansGrilleJ=getPositionDansGrilleJ(piece,j);
                    if((positionDansGrilleI+1)>=height){
                        return false;
                    }
                    if(positionDansGrilleI>=0){
                        if(!maGrille.getCase(positionDansGrilleI+1,positionDansGrilleJ).isIsEmpty()){
                           return false;
                        }
                    }

                }
            }
        }
        return true;
    }

    public int getPositionDansGrilleI(Piece piece, int row){
        int positionDansGrilleI;
        if(row<piece.getPointOrientation()[0]){
            positionDansGrilleI=piece.getPositionX()-(piece.getPointOrientation()[0]-row);
        }else if(row==piece.getPointOrientation()[0]){
            positionDansGrilleI=piece.getPositionX();
        }
        else{
            positionDansGrilleI=piece.getPositionX()+(row- piece.getPointOrientation()[0]);
        }
        return positionDansGrilleI;
    }

    public int getPositionDansGrilleJ(Piece piece, int column){
        int positionDansGrilleJ;
        if(column<piece.getPointOrientation()[1]){
            positionDansGrilleJ=piece.getPositionY()-(piece.getPointOrientation()[1]-column);
        }else if(column==piece.getPointOrientation()[1]){
            positionDansGrilleJ=piece.getPositionY();
        }else{
            positionDansGrilleJ=piece.getPositionY()+ (column- piece.getPointOrientation()[1]);
        }
        return positionDansGrilleJ;
    }

    public boolean pivotCheck(Piece piece){
        Piece tmp =new Piece(piece);
        tmp.pivoter();
        int [][] maPiece= tmp.getMatricePiece();
        int width=monTetris.getGrille().getWidth()  ;
        int height=monTetris.getGrille().getHeight();
        int positionDansGrilleI=0;
        int positionDansGrilleJ=0;
        Grille maGrille=monTetris.getGrille();
        for(int i=0; i<maPiece.length;i++) {
            for (int j = 0; j < maPiece[i].length; j++) {
                if(maPiece[i][j]!=0){
                    positionDansGrilleI=getPositionDansGrilleI(tmp,i);
                    positionDansGrilleJ=getPositionDansGrilleJ(tmp,j);
                    if(positionDansGrilleI>=height || positionDansGrilleI<0 || positionDansGrilleJ>=width || positionDansGrilleJ<0){
                        return false;
                    }else{
                        if(!maGrille.getCase(positionDansGrilleI,positionDansGrilleJ).isIsEmpty()){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public int lineCheck(){
        int nbLigne=0;
        int width=monTetris.getGrille().getWidth();
        int height=monTetris.getGrille().getHeight();
        for(int i=(height-1); i>=0;i--){
            for(int j=0; j<width;j++){
                if(monTetris.getGrille().getCase(i,j).getIntOccupe()==0){
                    break;
                }
                if(j==(width-1)){
                    nbLigne++;
                    monTetris.getGrille().removeLine(i);
                    monTetris.getGrille().addNFirstRow(i);
                   i++;
                }
            }
        }
        return 0;
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
        monTetris.setScore(10 * (monTetris.getNiveau() + 1));
    }
}
