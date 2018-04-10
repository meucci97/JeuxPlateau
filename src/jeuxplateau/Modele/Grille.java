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
    private Vector<Vector<Case>> tableau;

    public Grille(int x, int y) {
        
        tableau= new Vector<>();
        for(int i= 0;i<x;i++){
            tableau.add(new Vector<Case>());
        }
        
        tableau.forEach(row -> {
            for(int j=0;j<y;j++){
                row.add(new Case());
            }
        });
    }
    
    public void viewGrille(){
        tableau.forEach(row->{
            row.forEach(cellule->{
                System.out.print(cellule.getPosition()+" ");
            });
            System.out.println(" ");
        });
    }
}
