package Modelo;


import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristian
 */
public class Alerta {
    private Coordenada coordenadas;
    private Emergencia emergencia;
    private int id;
    private boolean gestionada;
    private Date fecha;
    private boolean activa;
    private int afectados;
    
    public Alerta(Coordenada coordenada, Emergencia emergencia, int id, boolean activa, int afectados) {
    	this.coordenadas = coordenada;
    	this.emergencia = emergencia;
    	this.id = id;
    	this.gestionada = false;
    	this.fecha = new Date();
    	this.activa = activa;
    	this.afectados = afectados;
    }
    
    public Alerta(Coordenada coordenadas, Emergencia emergencia, int id,
            boolean gestionada, Date fecha, boolean activa, int afectados){
        this.coordenadas = coordenadas;
        this.emergencia = emergencia;
        this.id = id;
        this.gestionada = gestionada;
        this.fecha = fecha;
        this.activa = activa;
        this.afectados = afectados;
    }
    
    public Coordenada verCoordenadas(){
        return coordenadas;
    }
    
    public Emergencia verEmergencia(){
        return emergencia;
    }
    
    public int getId(){
        return id;
    }
    
    public boolean estaGestionada(){
        return gestionada;
    }
    
    public Date verFecha(){
        return fecha;
    }
    
    public boolean estaActiva(){
        return activa;
    }
    
    public int verAfectados(){
        return afectados;
    }
    
    public String informacion(){
        return " Coordenadas:(" + coordenadas.verX() + ", " + coordenadas.verY() 
                + ")\n" + " Emergencia: "+ emergencia.verTipo() +
                " Nivel " + emergencia.verNivel() + " " + "\n" +
                " Fecha: " + verFecha() + "\n" +
                " Afectados: " + verAfectados()+ "\n" +
                " Id: " + getId();
    }
    
    @Override
    public String toString(){
        return "Coordenadas:(" + coordenadas.verX() + ", " + coordenadas.verY()+
                ")\n" + " Emergencia: "+ emergencia.verTipo() + 
                " Nivel " + emergencia.verNivel() + " " + "\n" +
                " Gestionada: " + estaGestionada() + "\n" +
                " Fecha: " + verFecha() + "\n" +
                " Activa: " + estaActiva() + " \n" +
                " Afectados: " + verAfectados();
    }
    
}
