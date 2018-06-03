package jeuxplateau.Controlleur;

import jeuxplateau.Modele.Puzzle;

public class PuzzleControlleur {
    private Puzzle monPuzzle;

    public PuzzleControlleur(Puzzle monPuzzle) {
        this.monPuzzle = monPuzzle;
    }

    public void setMonPuzzle(Puzzle monPuzzle) {
        this.monPuzzle = monPuzzle;
    }

    public void clickSelectionPiece(int valeurCase){
        if(valeurCase>0){
            monPuzzle.setSelectedPiece(valeurCase);
            setMovmentOrientation(valeurCase);
            System.out.println(monPuzzle.isUpDown());
            monPuzzle.notifyObsevateur();
        }
    }

    public void setMovmentOrientation(int valeur){
        for(int i=0;i<monPuzzle.getGrille().getHeight();i++){
            for(int j=0;j<monPuzzle.getGrille().getHeight();j++){
                if(monPuzzle.getGrille().getCase(i,j).getIntOccupe()==valeur){
                    if(i>0){
                        if(monPuzzle.getGrille().getCase((i-1),j).getIntOccupe()==valeur){
                            monPuzzle.setUpDown(true);
                            return;
                        }
                    }
                    if(i<monPuzzle.getGrille().getHeight()-1){
                        if(monPuzzle.getGrille().getCase((i+1),j).getIntOccupe()==valeur){
                            monPuzzle.setUpDown(true);
                            return;
                        }
                    }

                    if(j>0){
                        if(monPuzzle.getGrille().getCase(i,(j-1)).getIntOccupe()==valeur){
                            monPuzzle.setUpDown(false);
                            return;
                        }
                    }
                    if(i<monPuzzle.getGrille().getWidth()-1){
                        if(monPuzzle.getGrille().getCase(i,(j+1)).getIntOccupe()==valeur){
                            monPuzzle.setUpDown(false);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void clickDown(){
        if(monPuzzle.isUpDown()){
            if(clickDownCheck()){
                System.out.println("yes");
                monPuzzle.moveDown(monPuzzle.getSelectedPiece());
                monPuzzle.notifyObsevateur();
            }
        }else{
            System.out.println("no");
        }


    }
    public void clickRight(){
        if(!monPuzzle.isUpDown()){
            if(clickRightCheck()){
                System.out.println("yes");
                monPuzzle.moveRight(monPuzzle.getSelectedPiece());
                monPuzzle.notifyObsevateur();
            }
        }else{
            System.out.println("no");
        }
    }
    public void clickLeft(){
        if(!monPuzzle.isUpDown()){
            if(clickLeftCheck()){
                System.out.println("yes");
                monPuzzle.moveLeft(monPuzzle.getSelectedPiece());
                monPuzzle.notifyObsevateur();
            }
        }else{
            System.out.println("no");
        }
    }
    public void clickUp(){
        if(monPuzzle.isUpDown()){
            if(clickUpCheck()){
                System.out.println("yes");
                monPuzzle.moveUp(monPuzzle.getSelectedPiece());
                monPuzzle.notifyObsevateur();
            }
        }else{
            System.out.println("no");
        }
    }

    public boolean clickDownCheck(){
        int tmp;
        for(int i=(monPuzzle.getGrille().getHeight()-1);i>=0;i--){
            for(int j=0;j<monPuzzle.getGrille().getWidth();j++){
                if(monPuzzle.getGrille().getCase(i,j).getIntOccupe()==monPuzzle.getSelectedPiece()){
                    if(monPuzzle.getGrille().getCase((i+1),j).getIntOccupe()!=0){
                        return false;
                    }else{
                        return true;
                    }
                }
            }
        }
        return true;
    }
    public boolean clickRightCheck(){
        int tmp;
        for(int i=0;i<monPuzzle.getGrille().getHeight();i++){
            for(int j=monPuzzle.getGrille().getWidth();j>=0;j--){
                if(monPuzzle.getGrille().getCase(i,j).getIntOccupe()==monPuzzle.getSelectedPiece()){
                    if(monPuzzle.getGrille().getCase(i,j).getIntOccupe()==monPuzzle.getSelectedPiece()){
                        if(monPuzzle.getGrille().getCase(i,(j+1)).getIntOccupe()!=0){
                            return false;
                        }else{
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }
    public boolean clickLeftCheck(){
        int tmp;
        for(int i=0;i<monPuzzle.getGrille().getHeight();i++){
            for(int j=0;j<monPuzzle.getGrille().getWidth();j++){
                if(monPuzzle.getGrille().getCase(i,j).getIntOccupe()==monPuzzle.getSelectedPiece()){
                    if(monPuzzle.getGrille().getCase(i,j).getIntOccupe()==monPuzzle.getSelectedPiece()){
                        if(monPuzzle.getGrille().getCase(i,(j-1)).getIntOccupe()!=0){
                            return false;
                        }else{
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }
    public boolean clickUpCheck(){
        int tmp;
        for(int i=0;i<monPuzzle.getGrille().getHeight();i++){
            for(int j=0;j<monPuzzle.getGrille().getWidth();j++){
                if(monPuzzle.getGrille().getCase(i,j).getIntOccupe()==monPuzzle.getSelectedPiece()){
                    if(monPuzzle.getGrille().getCase(i,j).getIntOccupe()==monPuzzle.getSelectedPiece()){
                        if(monPuzzle.getGrille().getCase((i-1),j).getIntOccupe()!=0){
                            return false;
                        }else{
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }

}
