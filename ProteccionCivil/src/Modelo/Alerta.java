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
    
    public Coordenada getCoordenadas(){
        return coordenadas;
    }
    
    public Emergencia getEmergencia(){
        return emergencia;
    }
    
    public int getId(){
        return id;
    }
    
    public boolean estaGestionada(){
        return gestionada;
    }
    
    public Date getFecha(){
        return fecha;
    }
    
    public boolean estaActiva(){
        return activa;
    }
    
    public int getAfectados(){
        return afectados;
    }
    
    public void desactivar(){
        activa = false;
    }
    
    public void activarPlan(){
        gestionada = true;
    }
    public String informacion(){
        return " Coordenadas:(" + coordenadas.getX()+ ", " + coordenadas.getY()
                + ")\n" + " Emergencia: "+ emergencia.getTipo() +
                " Nivel " + emergencia.getNivel() + " " + "\n" +
                " Fecha: " + getFecha() + "\n" +
                " Afectados: " + getAfectados()+ "\n" +
                " Id: " + getId();
    }
    
    @Override
    /**
     * @author Miguel
     */
    public String toString(){
        if(emergencia==null)
            System.out.println("no hay emergencia");
        return getCoordenadas().getX() + "," + getCoordenadas().getY() + "," +
                getEmergencia().toString() + "," + 
                getId() + "," + 
                estaGestionada() 
                + "," + getFecha() + "," + estaActiva() + "," + getAfectados();
        /*return "Coordenadas:(" + coordenadas.getX()+ ", " + coordenadas.getY()+
                ")\n" + " Emergencia: "+ emergencia.getTipo() + 
                " Nivel " + emergencia.getNivel() + " " + "\n" +
                " Gestionada: " + estaGestionada() + "\n" +
                " Fecha: " + getFecha() + "\n" +
                " Activa: " + estaActiva() + " \n" +
                " Afectados: " + getAfectados();*/
    }
    
}
