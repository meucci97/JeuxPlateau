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
    public Puzzle(int [][] plateau,int niveau) {
        super(plateau.length,plateau.length);
        this.niveau=niveau;
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

    }

    @Override
    public void notifyObsevateur() {

    }
}
