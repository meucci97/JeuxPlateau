/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuxplateau.Modele;

/**
 *
 * @author Stefano
 */
public class Tetris extends Jeu {
    private int niveau;

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    public Tetris(int x, int y) {
        super(x, y);
        genererJoueur("J1");
        
    }

    @Override
    public void genererPieces() {

        // json
        // appelle une méthode qui créer une matrice (i, j)

        //mesPieces.add();
    }

    @Override
    public void genererJoueur(String idJoueur) {
       Joueur monJoueur= new Joueur(idJoueur);
       mesJoueurs.add(monJoueur);
    }

    @Override
    protected void chargerGrille(Grille oldGrille) {
        maGrille=oldGrille;
    }

    @Override
    protected void jouer() {

    }
}
