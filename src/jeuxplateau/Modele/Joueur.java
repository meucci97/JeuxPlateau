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
public class Joueur {
    private String idJoueur;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public Joueur(String idJoueur) {
        this.idJoueur = idJoueur;
        this.score=0;
    }

    public Joueur(String idJoueur, int score) {
        this.idJoueur = idJoueur;
        this.score = score;
    }
    

    public String getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(String idJoueur) {
        this.idJoueur = idJoueur;
    }
    
}
