/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Cristian
 */
public class Mensaje {
    private String operacion;
    private String parametros;
    
    public Mensaje(String operacion, String parametros){    
        ponerParametros(parametros);
        ponerOperacion(operacion);
    }
    
    public Mensaje(){
        this.operacion = null;
        this.parametros = "";
    }
    /*
    ** Setter
    */
    public void ponerOperacion(String operacion){
        this.operacion = operacion;
    }
    
    /*
    ** Setter
    */
    public void ponerParametros(String parametros){
        this.parametros = parametros;
    }
    
     
    /**
     * Getter
     */
    public String verParametros(){
        return parametros;
    }
    
    
    /**
     * Getter
     */
    public String verOperacion(){
        return operacion;
    }
}
