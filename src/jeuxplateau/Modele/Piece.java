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
    private int[] pointOrientation;
    private  String couleur;
    private int positionX;
    private int positionY;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Piece() {

    }

    public Piece(int orientation, int idPiece, int[][] matricePiece, int[] pointOrientation, String couleur) {
        this.orientation = orientation;
        this.idPiece = idPiece;
        this.matricePiece = matricePiece;
        this.pointOrientation = pointOrientation;
        this.couleur = couleur;
        this.positionX=-1;
        this.positionY=-1;
    }

    public Piece(Piece p){
        this.orientation = p.getOrientation();
        this.idPiece = p.getIdPiece();
        this.matricePiece = p.getMatricePiece();
        this.pointOrientation = p.getPointOrientation();
        this.couleur = p.getCouleur();
        this.positionX=p.getPositionX();
        this.positionY=p.getPositionY();
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

    public int[] getPointOrientation() {
        return pointOrientation;
    }

    public void setPointOrientation(int[] pointOrientation) {
        this.pointOrientation = pointOrientation;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void pivoter() {
        int nbLignes = this.matricePiece.length;
        int nbColonnes = this.matricePiece[0].length;

        int[][] matricePivotee = new int[nbLignes][nbColonnes];

        for (int i = 0; i < this.matricePiece.length; i++) {
            for (int j = 0; j < this.matricePiece[0].length; j++) {
                matricePivotee[j][ (nbColonnes-1)- i] = this.matricePiece[i][j];
            }
        }

        this.matricePiece = matricePivotee;
    }
}
