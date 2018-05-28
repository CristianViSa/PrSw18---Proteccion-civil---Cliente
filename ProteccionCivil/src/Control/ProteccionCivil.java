package Control;

import Modelo.Mensaje;
import Modelo.Coordenada;
import Modelo.Emergencia;
import Modelo.Alerta;
import Modelo.PlanProteccion;
import Vista.OyenteVista;
import Vista.MenuPlanesProteccion;
import Vista.MenuEmergenciasAlertas;
import Vista.MenuZonasSeguridad;
import Vista.PanelAlerta;
import Vista.PanelHistorial;
import java.util.ArrayList;
import java.util.List;
import Vista.VentanaPrincipal;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.synth.SynthLookAndFeel;

/**
 * Trabajo Proteccion Civil
 * Proyecto Software
 * Aplicacion cliente, Gestion de emergencias y alertas
 * @author Cristian
 */
public class ProteccionCivil implements OyenteVista {
    private VentanaPrincipal ventanaPrincipal;
    private PanelAlerta alertaVista;
    private PanelHistorial historialVista;
    
    private Comms comunicaciones;
    private MenuPlanesProteccion menuPlanes;
    private MenuEmergenciasAlertas menuEmer;
    private MenuZonasSeguridad menuZonas;
    private List<PlanProteccion> planes;
    private List<Alerta> alertas;
    private List<Emergencia> emergencias;
    private List<Coordenada> coordenadas;

    public ProteccionCivil() {
        this.ventanaPrincipal = new VentanaPrincipal(this);
        comunicaciones = new Comms(5500);
        
        //PRUEBA
        planes = new ArrayList<PlanProteccion>();
	planes.add(new PlanProteccion(this,"plan antiincendios - riesgo 1", 10,7, "Recoger y sacar a la población afectada en la zona"));
	planes.add(new PlanProteccion(this,"plan antiincendios - riesgo 2", 30,15, "Recoger y sacar a la población afectada en la zona"));
        emergencias = new ArrayList<Emergencia>();
        emergencias.add(new Emergencia("antiincendios", 1, 4, planes.get(1)));
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
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                //L&F disponibles: Metal, Nimbus, CDE/Motif, Windows, Windows Classic
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        new ProteccionCivil();
    }
    
     /**
     * Cierra el programa
     * @author Cristian
     */      
    public void salir(){
        System.exit(0);    
    }
    
    /**
     * Busca las alertas en la BD del servidor
     * @author Cristian
     */
    public List<Alerta> buscarAlertasEnBD(){
        // TBD
        return comunicaciones.solicitarHistorialDeAlertas();  
    }
    
      /**
     * Busca las alertas  activas y no gestionadas en la BD del servidor
     * @author Cristian
     */
    public List<Alerta> buscarAlertasActivasEnBD(){
        // TBD
        return comunicaciones.solicitarMapaAlertasNoGestionadas();
    }
    
    
	public void addPlan(PlanProteccion plan) {
		System.out.println(plan.toString());
		planes.add(plan);
		menuPlanes.update(planes);
	}
	
        public void addEmer(Emergencia emer){
            emergencias.add(emer);
            menuEmer.update(emergencias);
        }
        
	public void modPlan(PlanProteccion plan) {
		int idPlan = plan.getId() - 1;
		
		planes.get(idPlan).setNombre(plan.getNombre());
		planes.get(idPlan).setVehiculosNecesarios(plan.getVehiculosNecesarios());
		planes.get(idPlan).setVoluntariosNecesarios(plan.getVoluntariosNecesarios());
		planes.get(idPlan).setActuacionesNecesarias(plan.getActuacionesNecesarias());
		
		menuPlanes.addPlanes(planes);
		menuPlanes.update();
	}
	
	public void eliminarPlan(PlanProteccion plan) {
		int index = planes.indexOf(plan)+1;
		System.out.println("-----------index plan: " + index + ", planes ANTES borrar:" + planes.toString());
		planes.remove(index);
		System.out.println("-----------planes DESPUES borrar:" + planes.toString());
		System.out.println("-----------");
		menuPlanes.update();
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
        ventanaPrincipal.setContentPane(panel);
        ventanaPrincipal.revalidate();
    }
    
    /**
     * Sobreeescribe el metodo notificacion
     */
    @Override
    public void notificacion(Evento evento, Object obj) {
        switch(evento){
            //@author Cristian
            case SALIR :
                salir();
                break;
            //@author Cristian
            case HISTORIAL_ALERTAS:
                List historialAlertas = buscarAlertasEnBD();
                historialVista = new PanelHistorial(this);
                historialVista.introducirAlertasALista(historialAlertas);
                cargarPanel(historialVista);
                break;
            //@author Cristian
            case MENU_ITEM_ALERTAS:
                alertaVista = new PanelAlerta(this);//AlertasVista.instancia(this);
                alertas = comunicaciones.solicitarHistorialDeAlertas();
                List alertasActivas = buscarAlertasActivasEnBD();
                alertaVista.introducirAlertasActivasALista(alertasActivas);

                cargarPanel(alertaVista);
                break;
            //@author Cristian
            case ACTIVAR_PLAN:
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
            case MENU_PLANES:
                menuPlanes = new MenuPlanesProteccion(this);
                //ventanaPrincipal.cambiarPanelCentral(menu);
                menuPlanes.addPlanes(planes);
                cargarPanel(menuPlanes);
                //menu.setVisible(true);
                break;
            case MENU_EMERGENCIAS:
                menuEmer = new MenuEmergenciasAlertas(this);
                menuEmer.addEmergencias(emergencias);
                cargarPanel(menuEmer);
                break;
            case ADD_EMER:
                addEmer((Emergencia)obj);
                break;
            case MENU_ZONAS:
                menuZonas = new MenuZonasSeguridad(this);
                cargarPanel(menuZonas);
        }
    }
}  
