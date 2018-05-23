/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuxplateau.Modele;

import jeuxplateau.Vue.Observateur;

import java.util.Vector;

/**
 *
 * @author Stefano
 */
public abstract class Jeu implements Observable{

       protected Grille maGrille;
       protected Vector<Joueur> mesJoueurs;
       protected Vector<Piece> mesPieces;
       protected boolean isDone;
       protected int score;

       protected Vector<Observateur> monObservateur;
       protected abstract void genererPieces();
       protected abstract void genererJoueur(String idJoueur);
       protected abstract void chargerGrille(Grille oldGrille);
       protected abstract void jouer();

    public Jeu(int x, int y) {
        maGrille = new Grille(x, y);
        mesJoueurs = new Vector<Joueur>();
        mesPieces=new Vector<Piece>();
        isDone= false;
        maGrille.viewGrille();
        score = 0;
        monObservateur= new Vector<Observateur>();
    }
    
}
