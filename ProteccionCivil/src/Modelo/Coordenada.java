package Modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristian
 */
public class Coordenada {
    private double x;
    private double y;
    
    public Coordenada(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double verX(){
        return x;
    }
    
    
    public double verY(){
        return y;
    }
    
    public boolean equals(Coordenada coordenada){
        if(coordenada.verX() == x & coordenada.verY() == y){
            return true;
        }
        return false;
    }
}
