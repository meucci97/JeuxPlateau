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
public class Grille {

    protected Vector<Vector<Case>> tableau;
    private int width;
    private int height;

    public Grille(int x, int y) {
        this.height=y;
        this.width=x;
        tableau= new Vector<>();
        for(int i= 0;i<y;i++){
            tableau.add(new Vector<Case>());
        }
        
        tableau.forEach(row -> {
            for(int j=0;j<x;j++){
                row.add(new Case());
            }
        });
    }

    public Case getCase(int i, int j){
        Case maCase;
        try{
            maCase= tableau.get(i).get(j);
        }catch (Exception e){
            maCase=new Case();
            maCase.setIsEmpty(false);
        }
        return maCase;
    }
    
    public void viewGrille(){
        tableau.forEach(row->{
            row.forEach(cellule->{
                System.out.print(cellule.getIntOccupe()+" ");
            });
            System.out.println(" ");
        });
    }

    public void setCase(Piece p, int i, int j){
        tableau.get(i).get(j).setCase(p);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void removeLine(int i){
        tableau.remove(i);
    }

    public void addNFirstRow(int i){
        Vector<Case> newRow=new Vector<>();
        for(int j=0;j<getWidth();j++){
            newRow.add(new Case());
        }
        tableau.add(0,newRow);
    }
}
