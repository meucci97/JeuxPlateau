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

    //fonction pour le tetris
    public void setCase(Piece p, int i, int j){
        tableau.get(i).get(j).setCase(p);
    }

    //Fonction pour le puzzle
    public void setCase(int value, int i, int j){
        tableau.get(i).get(j).setIntOccupe(value);
        if(value==-2) {
            tableau.get(i).get(j).setCouleur("LIMEGREEN");
        }else if(value==-1) {
            tableau.get(i).get(j).setCouleur("BLACK");
        }else if(value==0){
            tableau.get(i).get(j).setCouleur("DARKGRAY");
        }else if(value==1){
            tableau.get(i).get(j).setCouleur("GREEN");
        }else if(value==2){
            tableau.get(i).get(j).setCouleur("MEDIUMORCHID");
        }else if(value==3){
            tableau.get(i).get(j).setCouleur("YELLOW");
        }else if(value==4){
            tableau.get(i).get(j).setCouleur("SALMON");
        }else if(value==5){
            tableau.get(i).get(j).setCouleur("FIREBRICK");
        }else if(value==6){
            tableau.get(i).get(j).setCouleur("TURQUOISE");
        }else if(value==7){
            tableau.get(i).get(j).setCouleur("ORANGE");
        }else if(value==8){
            tableau.get(i).get(j).setCouleur("POWDERBLUE");
        }else if(value==9){
            tableau.get(i).get(j).setCouleur("BEIGE");
        }else{
            tableau.get(i).get(j).setCouleur("BLACK");
        }

    }
    public String getCouleur(int value){
        if(value==-2) {
            return "LIMEGREEN";
        }else if(value==-1) {
            return "BLACK";
        }else if(value==0){
            return "DARKGRAY";
        }else if(value==1){
            return "GREEN";
        }else if(value==2){
            return "MEDIUMORCHID";
        }else if(value==3){
            return "YELLOW";
        }else if(value==4){
            return "SALMON";
        }else if(value==5){
            return "FIREBRICK";
        }else if(value==6){
            return "TURQUOISE";
        }else if(value==7){
            return "ORANGE";
        }else if(value==8){
            return "POWDERBLUE";
        }else if(value==9){
            return "BEIGE";
        }else{
            return "BLACK";
        }
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
