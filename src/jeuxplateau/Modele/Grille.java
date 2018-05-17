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
        return tableau.get(i).get(j);
    }
    
    public void viewGrille(){
        tableau.forEach(row->{
            row.forEach(cellule->{
                System.out.print(cellule.getPosition()+" ");
            });
            System.out.println(" ");
        });
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
