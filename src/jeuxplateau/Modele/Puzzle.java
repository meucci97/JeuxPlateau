/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuxplateau.Modele;

import jeuxplateau.Vue.Observateur;

/**
 *
 * @author Stefano
 */
public class Puzzle extends Jeu {
    private int niveau;
    private int selectedPiece;
    private boolean upDown;

    public boolean isUpDown() {
        return upDown;
    }

    public void setUpDown(boolean upDown) {
        this.upDown = upDown;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getSelectedPiece() {
        return selectedPiece;
    }

    public String getSelecedPieceColor(){
        return super.maGrille.getCouleur(selectedPiece);
    }

    public Puzzle(int [][] plateau, int niveau) {
        super(plateau.length,plateau.length);
        this.niveau=niveau;
        this.selectedPiece=0;
        genererJoueur("J1");
        for (int i=0;i<plateau.length;i++){
            for(int j=0;j<plateau[i].length;j++){
                super.maGrille.setCase(plateau[i][j],i,j);
                System.out.print(plateau[i][j]+" ");
            }
            System.out.println("");
        }

    }

    public Grille getGrille(){
        return super.maGrille;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void setSelectedPiece(int selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public void moveUp(int selectedPiece){
        int tmp;
        for(int i=0;i<maGrille.getHeight();i++){
            for(int j=0;j<maGrille.getWidth();j++){
                if(maGrille.getCase(i,j).getIntOccupe()==selectedPiece){
                    tmp=maGrille.getCase((i-1),j).getIntOccupe();
                    maGrille.setCase(selectedPiece,(i-1),j);
                    maGrille.setCase(tmp,i,j);
                }
            }
        }
    }
    public void moveDown(int selectedPiece){
        int tmp;
        for(int i=(maGrille.getHeight()-1);i>=0;i--){
            for(int j=0;j<maGrille.getWidth();j++){
                if(maGrille.getCase(i,j).getIntOccupe()==selectedPiece){
                    tmp=maGrille.getCase((i+1),j).getIntOccupe();
                    maGrille.setCase(selectedPiece,(i+1),j);
                    maGrille.setCase(tmp,i,j);
                }
            }
        }
    }
    public void moveLeft(int selectedPiece){
        int tmp;
        for(int i=0;i<maGrille.getHeight();i++){
            for(int j=0;j<maGrille.getWidth();j++){
                if(maGrille.getCase(i,j).getIntOccupe()==selectedPiece){
                    tmp=maGrille.getCase(i,(j-1)).getIntOccupe();
                    maGrille.setCase(selectedPiece,(i),(j-1));
                    maGrille.setCase(tmp,i,j);
                }
            }
        }
    }
    public void moveRight(int selectedPiece){
        int tmp;
        for(int i=0;i<maGrille.getHeight();i++){
            for(int j=maGrille.getWidth();j>=0;j--){
                if(maGrille.getCase(i,j).getIntOccupe()==selectedPiece){
                    tmp=maGrille.getCase(i,(j+1)).getIntOccupe();
                    maGrille.setCase(selectedPiece,(i),(j+1));
                    maGrille.setCase(tmp,i,j);
                }
            }
        }
    }


    @Override
    protected void genererPieces() {

    }

    @Override
    protected void genererJoueur(String idJoueur) {
        Joueur monJoueur = new Joueur(idJoueur);
        mesJoueurs.add(monJoueur);
    }

    @Override
    protected void chargerGrille(Grille oldGrille) {
        maGrille = oldGrille;
    }

    @Override
    protected void jouer() {
    }

    @Override
    public void addObservateur(Observateur o) {
        super.monObservateur.add(o);
    }

    @Override
    public void notifyObsevateur() {
        super.monObservateur.forEach(observateur -> observateur.update());

    }
}
