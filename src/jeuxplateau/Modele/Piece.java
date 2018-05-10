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
public class Piece {
    private int orientation;
    private int idPiece;
    private int[][] matricePiece;
    private Joueur joueur = null;
    private int pointOrientation;

    public Piece() {

    }

    public Piece(int orientation, int idPiece, int[][] matricePiece, int pointOrientation) {
        this.orientation = orientation;
        this.idPiece = idPiece;
        this.matricePiece = matricePiece;
        this.pointOrientation = pointOrientation;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getIdPiece() {
        return idPiece;
    }

    public void setIdPiece(int idPiece) {
        this.idPiece = idPiece;
    }

    public int[][] getMatricePiece() {
        return matricePiece;
    }

    public void setMatricePiece(int[][] matricePiece) {
        this.matricePiece = matricePiece;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public int getPointOrientation() {
        return pointOrientation;
    }

    public void setPointOrientation(int pointOrientation) {
        this.pointOrientation = pointOrientation;
    }

    public void pivoter() {
        
    }
}
