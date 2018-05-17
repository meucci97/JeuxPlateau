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
public class Blocus extends Jeu {

    public Blocus(int x, int y) {
        super(x, y);
        genererJoueur("J1");
        genererJoueur("J2");
        genererJoueur("J3");
        genererJoueur("J4");
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
