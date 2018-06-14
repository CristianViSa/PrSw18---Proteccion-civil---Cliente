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
    private String id;
    private PlanProteccion plan;
    private String tipo;
    private int nivel;
    
    /**
     * 
     * Constructor
     */
    public Emergencia(String id, PlanProteccion plan, String tipo, int nivel){
        this.id = id;
        this.plan = plan;
        this.tipo = tipo;
        this.nivel = nivel;
    }
    
    /**
     * 
     * Constructor
     */
    public Emergencia(String tipo, int nivel){
        this.tipo = tipo;
        this.id = "0";
        this.nivel = nivel;
        this.plan = null;
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
    public String getId(){
        return id;
    }
    
    /**
     * Getter
     */
    public PlanProteccion getPlan(){
        return plan;
    }
    
    /**
     * Getter
     */
    public int getNivel(){
        return nivel;
    }
    
    public String mostrar(){
        String cadena = "Emergencia de tipo: " + tipo;
        cadena += "\n\tCódigo emergencia: " + id;
        cadena += "\n\tNivel: " + nivel;
        if(plan != null){
            cadena += "\n\nPlan protección: \t" + plan.mostrar();
        } else {
            cadena += "\n\tPlan Proteccion: -";
        }
        return cadena;
    }
    
    public String toString(){
        return getId() + "," + /*getPlan().toString()+ "," +*/getTipo() +
                "," + getNivel();
    }
    
}
