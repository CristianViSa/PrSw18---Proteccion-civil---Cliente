package Modelo;

import Control.ProteccionCivil;
import Vista.OyenteVista;

/**
 * 
 * @author MiguelYanes
 */

public class PlanProteccion {
	private String id_plan;
	private String nombre;
	private int vehiculosNecesarios;
	private int voluntariosNecesarios;
	private String actuacionesNecesarias;
	
	//para nuevos planes
	public PlanProteccion(String nombre, int vehiculosNecesarios, int voluntariosNecesarios, String actuacionesNecesarias){
		this.id_plan = "0";//pCivil.notificacion(OyenteVista.Evento.GET_ID_PLAN,null);
		this.nombre = nombre;
		this.vehiculosNecesarios = vehiculosNecesarios;
		this.voluntariosNecesarios = voluntariosNecesarios;
		this.actuacionesNecesarias = actuacionesNecesarias;
	}
	
	//para copias de planes existentes
	public PlanProteccion(String id, String nombre, int vehiculosNecesarios, int voluntariosNecesarios, String actuacionesNecesarias){
		this.id_plan = id;
		this.nombre = nombre;
		this.vehiculosNecesarios = vehiculosNecesarios;
		this.voluntariosNecesarios = voluntariosNecesarios;
		this.actuacionesNecesarias = actuacionesNecesarias;
	}
	
	public void commit(){
		//to do
	}

	public int getVehiculosNecesarios() {
		return vehiculosNecesarios;
	}

	public void setVehiculosNecesarios(int vehiculosNecesarios) {
		this.vehiculosNecesarios = vehiculosNecesarios;
	}

	public int getVoluntariosNecesarios() {
		return voluntariosNecesarios;
	}

	public void setVoluntariosNecesarios(int voluntariosNecesarios) {
		this.voluntariosNecesarios = voluntariosNecesarios;
	}

	public String getActuacionesNecesarias() {
		return actuacionesNecesarias;
	}

	public void setActuacionesNecesarias(String actuacionesNecesarias) {
		this.actuacionesNecesarias = actuacionesNecesarias;
	}

	public String getId() {
		return id_plan;
	}

	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/*public String toString(){
		String cadena = "\n" + getNombre();
		cadena += "\n\nCódigo del plan: " + getId();
		cadena += "\nVehículos necesarios: " + getVehiculosNecesarios();
		cadena += "\nVoluntarios necesarios: " + getVoluntariosNecesarios();
		cadena += "\n\n\t" + getActuacionesNecesarias();
		cadena += "\n";
		return cadena;
	}*/
	
        public String toString(){
            return getId() + "," + getNombre()+ "," +getVehiculosNecesarios() +
                "," + getVoluntariosNecesarios() + "," + getActuacionesNecesarias();
    }
}
