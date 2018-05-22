package Control;


import Modelo.Coordenada;
import Modelo.Emergencia;
import Modelo.Alerta;
import Modelo.PlanProteccion;
import Vista.OyenteVista;
import Vista.AlertasVista;
import Vista.MenuPlanesProteccion;
import java.util.ArrayList;
import java.util.List;
import Vista.VentanPrincipal;
import Vista.VentanaPrincipal;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 * Trabajo Proteccion Civil
 * Proyecto Software
 * Aplicacion cliente, Gestion de emergencias y alertas
 * @author Cristian, 702364
 */
public class ProteccionCivil implements OyenteVista {
    private VentanaPrincipal ventanaPrincipal;
    private AlertasVista alertaVista;
    private List<Alerta> alertasActivas = new ArrayList<Alerta>(); 
    private Comms comunicaciones;
    private MenuPlanesProteccion menu;
    private List<PlanProteccion> planes;
    private List<Alerta> alertas;
    private List<Emergencia> emergencias;
    private List<Coordenada> coordenadas;

    public ProteccionCivil() {
        this.ventanaPrincipal = new VentanaPrincipal(this);
        comunicaciones = new Comms(5500);
        
    }
    /*
    public ProteccionCivil(){
        
        vista = AlertasVista.instancia(this);
        comunicaciones = new Comms(5500);
        alertas = comunicaciones.solicitarHistorialDeAlertas();
        alertasActivas = comunicaciones.solicitarMapaAlertasNoGestionadas();
        vista.introducirAlertasActivasALista(alertasActivas);
        planes = new ArrayList<PlanProteccion>();
	planes.add(new PlanProteccion(this,"plan antiincendios - riesgo 1", 10,7, "Recoger y sacar a la población afectada en la zona"));
	planes.add(new PlanProteccion(this,"plan antiincendios - riesgo 2", 30,15, "Recoger y sacar a la población afectada en la zona"));
	//emergencias.add(new Emergencia("antiincendios", 1, 3, planes.get(0)));
	menu.addPlanes(planes);
    }*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //new Comms(5500);
        new ProteccionCivil();
    }
    
     /**
     * Cierra el programa
     */      
    public void salir(){
        System.exit(0);    
    }
    
    /**
     * Busca las alertas en la BD del servidor
     */
    public void buscarAlertasEnBD(){
        // TBD
        alertas = comunicaciones.solicitarHistorialDeAlertas(); 
        
    }
    
      /**
     * Busca las alertas  activas y no gestionadas en la BD del servidor
     */
    public void buscarAlertasActivasEnBD(){
        // TBD
        alertasActivas = comunicaciones.solicitarMapaAlertasNoGestionadas();
    }
    
    
	public void addPlan(PlanProteccion plan) {
		System.out.println(plan.toString());
		planes.add(plan);
		menu.update(planes);
	}
	
	public void modPlan(PlanProteccion plan) {
		int idPlan = plan.getId() - 1;
		
		planes.get(idPlan).setNombre(plan.getNombre());
		planes.get(idPlan).setVehiculosNecesarios(plan.getVehiculosNecesarios());
		planes.get(idPlan).setVoluntariosNecesarios(plan.getVoluntariosNecesarios());
		planes.get(idPlan).setActuacionesNecesarias(plan.getActuacionesNecesarias());
		
		menu.addPlanes(planes);
		menu.update();
	}
	
	public void eliminarPlan(PlanProteccion plan) {
		int index = planes.indexOf(plan)+1;
		System.out.println("-----------index plan: " + index + ", planes ANTES borrar:" + planes.toString());
		planes.remove(index);
		System.out.println("-----------planes DESPUES borrar:" + planes.toString());
		System.out.println("-----------");
		menu.update();
	}
	
	public int getLastIdPlan(){
		int id=0;
		if(!planes.isEmpty()) {
			for(int i = 0; i<planes.size();i++) {
				if(planes.get(i).getId() > id)
					id = planes.get(i).getId();
			}
		}
		return id;
	}
	
	public int getLastIdAlerta(){
		int id=0;
		if(!alertas.isEmpty()) {
			for(int i = 0; i<alertas.size();i++) {
				if(alertas.get(i).getId() > id)
					id = alertas.get(i).getId();
			}
		}
		return id;
	}
	
	public PlanProteccion getPlan(int id) {
		if(!planes.isEmpty()) {
			for(int i = 0; i<planes.size();i++) {
				if(planes.get(i).getId() == id)
					return planes.get(i);
			}
		}
		return null;
	}
	
	public PlanProteccion getPlan(String nombre) {
		if(!planes.isEmpty()) {
			for(int i = 0; i<planes.size();i++) {
				if(planes.get(i).getNombre() == nombre)
					return planes.get(i);
			}
		}
		return null;
	}
	
    public Emergencia getEmergencia(int id) {
	if(!emergencias.isEmpty()) {
            for(int i = 0; i<emergencias.size();i++) {
		if(emergencias.get(i).getId() == id)
                    return emergencias.get(i);
            }
    	}
    	return null;
    }
    /**
     * Carga el panel
     */
    public void cargarPanel(JPanel panel){
        panel.setSize(ventanaPrincipal.getSize());
        ventanaPrincipal.getPanelCentral().removeAll();
        ventanaPrincipal.getPanelCentral().add(panel);
        ventanaPrincipal.revalidate();
    }
    
    /**
     * Sobreeescribe el metodo notificacion
     */
    @Override
    public void notificacion(Evento evento, Object obj) {
        switch(evento){
                case SALIR :
                    salir();
                    break;
                case HISTORIAL:
                    //TBD
                    buscarAlertasEnBD();
                    alertaVista.mostrarVentanaHistorial(alertas);
                    
                    break;
                case MENU_ITEM_ALERTAS:
                    //TBD
                    alertaVista = AlertasVista.instancia(this);
                    alertas = comunicaciones.solicitarHistorialDeAlertas();
                    alertasActivas = comunicaciones.solicitarMapaAlertasNoGestionadas();
                    alertaVista.introducirAlertasActivasALista(alertasActivas);

                    cargarPanel(alertaVista);
                    break;
                case ACTIVAR_PLAN:
                    // TBD
                    Alerta alerta = (Alerta)obj;
                    if(comunicaciones.
                            solicitarActivarPlanDeProteccion(String.valueOf(alerta.getId()))){
                        alertaVista.mensajeConfirmacionPlan(alerta);
                    }
                    break;
                case ADD_PLAN:
                    addPlan((PlanProteccion)obj);
                    break;
                case MOD_PLAN:
                    modPlan((PlanProteccion)obj);
                    break;
                case ELIMINAR_PLAN:
                    eliminarPlan((PlanProteccion)obj);
                    break;
                case GET_ID_PLAN:
                    getLastIdPlan();
                    break;
                case GET_ID_ALERTA:
                    getLastIdAlerta();
                    break;
                case GET_PLAN_ID:
                    getPlan((int)obj);
                    break;
                case GET_PLAN_NOMBRE:
                    getPlan((String)obj);
                    break;
                case GET_EMERGENCIA:
                    getEmergencia((int)obj);
                    break;
                    
        }
    }
    
}
