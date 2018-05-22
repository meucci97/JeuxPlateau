/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuxplateau.Modele;

import jeuxplateau.Vue.Observateur;
import jeuxplateau.utilitaires.JsonParsing;

import java.util.Vector;

/**
 *
 * @author Stefano
 */
public class Tetris extends Jeu {
    private int niveau = 0;
    private int lignes = 0;
    private int nbPiecesPosees = 0;

    public int getNbPiecesPosees() {
        return nbPiecesPosees;
    }

    public void setNbPiecesPosees(int nbPiecesPosees) {
        this.nbPiecesPosees = nbPiecesPosees;
    }

    public int getLignes() {
        return lignes;
    }

    public void setLignes(int lignes) {
        this.lignes = lignes;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getScore() {
        return super.score;
    }

    public void setScore(int score) {
        super.score = score;
    }

    public Tetris(int x, int y) {
        super(x, y);
        genererJoueur("J1");
        genererPieces();
    }

    public Grille getGrille(){
        return super.maGrille;
    }

    public Vector<Piece> getMesPieces(){
        return super.mesPieces;
    }

    public void removeFirstPiece(){
        super.mesPieces.removeElementAt(0);
    }

    public Vector<Joueur> getMesJoueurs(){
        return super.mesJoueurs;
    }

    public void setIsGameOver(){
        super.isDone=true;
    }

    public boolean getGameOver(){
        return super.isDone;
    }

    @Override
    public void genererPieces() {
        Piece piece = JsonParsing.genererPieceAleatoire("JSONTetris.json");

        mesPieces.add(piece);
    }

    @Override
    public void genererJoueur(String idJoueur) {
       Joueur monJoueur = new Joueur(idJoueur);
       mesJoueurs.add(monJoueur);
    }

    @Override
    protected void chargerGrille(Grille oldGrille) {
        maGrille=oldGrille;
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
