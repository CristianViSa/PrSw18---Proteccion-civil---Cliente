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
public class Emergencia {
    private String tipo;
    private int id;
    private int nivel;
    private PlanProteccion plan;
    
    /**
     * 
     * Constructor
     */
    public Emergencia(String tipo, int id, int nivel, PlanProteccion plan){
        this.tipo = tipo;
        this.id = id;
        this.nivel = nivel;
    }
    
    /**
     * 
     * Constructor
     */
    public Emergencia(String tipo, int nivel){
        this.tipo = tipo;
        this.id = 0;
        this.nivel = nivel;
    }
    
    /**
     * 
     * Getter
     */
    public String getTipo(){
        return tipo;
    }
    
    /**
     * Getter
     */
    public int getId(){
        return id;
    }
    
    /**
     * Getter
     */
    public int getNivel(){
        return nivel;
    }
    
    public String toString(){
        String cadena = "Emergencia de tipo: " + tipo;
        cadena += "\n\tCódigo emergencia: " + id;
        cadena += "\n\tNivel: " + nivel;
        if(plan != null){
            cadena += "\n\tPlan protección: código " + plan.getId();
            cadena += "\n\t\t" + plan.toString();
        } else {
            cadena += "\n\tPlan Proteccion: -";
        }
        return cadena;
    }
    
}
