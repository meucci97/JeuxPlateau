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
public class Case {
    private boolean isEmpty;
    private Piece piece;
    private int intOccupe;
    private String couleur;

    public boolean isIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getIntOccupe() {
        return intOccupe;
    }

    public void setIntOccupe(int position) {
        this.intOccupe = position;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Case() {
        isEmpty=true;
        piece= null;
        intOccupe=0;
        couleur = "BLACK";
    }
    
}
