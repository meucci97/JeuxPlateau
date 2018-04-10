/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuxplateau.Modele;

import java.util.Vector;

/**
 *
 * @author Stefano
 */
public abstract class Jeu {
       protected Grille maGrille;
       protected Vector<Joueur> mesJoueurs;
       protected Vector<Piece> mesPieces;
       protected boolean isDone;
       protected abstract void genererPieces();
       protected abstract void genererJoueur();
       protected abstract void genererGrille();
       protected abstract void jouer();

    public Jeu(int x, int y) {
        maGrille = new Grille(x, y);
        mesJoueurs = new Vector<Joueur>();
        mesPieces=new Vector<Piece>();
        isDone= false;
        maGrille.viewGrille();
    }
}
