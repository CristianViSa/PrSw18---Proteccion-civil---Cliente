package Control;

import Modelo.Albergue;
import Modelo.Coordenada;
import Modelo.Emergencia;
import Modelo.Alerta;
import Modelo.Almacen;
import Modelo.Mapa;
import Modelo.PlanProteccion;
import Modelo.Vehiculo;
import Modelo.Voluntario;
import Vista.OyenteVista;
import Vista.MenuPlanesProteccion;
import Vista.MenuEmergenciasAlertas;
import Vista.MenuZonasSeguridad;
import Vista.PanelAlerta;
import Vista.PanelHistorial;
import java.util.ArrayList;
import java.util.List;
import Vista.VentanaPrincipal;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import Vista.PanelAlbergues;
import Vista.PanelAlmacenes;
import Vista.PanelMapa;
import Vista.PanelVehiculos;
import Vista.PanelVoluntarios;


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
    
    
    PanelAlbergues panelAlbergues;
    PanelAlmacenes panelAlmacenes;
    PanelVehiculos panelVehiculos;
    PanelVoluntarios panelVoluntarios;
    PanelMapa panelMapa;

    public ProteccionCivil() {
        this.ventanaPrincipal = new VentanaPrincipal(this);
        comunicaciones = new Comms(5500);
        
        //PRUEBA
//        planes = new ArrayList<PlanProteccion>();
//	planes.add(new PlanProteccion(/*this,*/"plan antiincendios - riesgo 1", 10,7, "Recoger y sacar a la población afectada en la zona"));
//	planes.add(new PlanProteccion(/*this,*/"plan antiincendios - riesgo 2", 30,15, "Recoger y sacar a la población afectada en la zona"));
//        emergencias = new ArrayList<Emergencia>();
//        emergencias.add(new Emergencia("antiincendios", 1, 4, planes.get(1)));
    }

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
		String idPlan = plan.getId();
                PlanProteccion viejoPlan = getPlan(plan.getId());
                viejoPlan.setNombre(plan.getNombre());
                viejoPlan.setVehiculosNecesarios(plan.getVehiculosNecesarios());
                viejoPlan.setVoluntariosNecesarios(plan.getVoluntariosNecesarios());
                viejoPlan.setActuacionesNecesarias(plan.getActuacionesNecesarias());
		/*
		planes.get(idPlan).setNombre(plan.getNombre());
		planes.get(idPlan).setVehiculosNecesarios(plan.getVehiculosNecesarios());
		planes.get(idPlan).setVoluntariosNecesarios(plan.getVoluntariosNecesarios());
		planes.get(idPlan).setActuacionesNecesarias(plan.getActuacionesNecesarias());
		*/
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
		/*int id=0;
		if(!planes.isEmpty()) {
			for(int i = 0; i<planes.size();i++) {
				if(planes.get(i).getId() > id)
					id = planes.get(i).getId();
			}
		}*/
		return 1;
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
				if(planes.get(i).getId().equals(id))
					return planes.get(i);
			}
		}
		return null;
	}
	
	public PlanProteccion getPlan(String nombre) {
		//if(!planes.isEmpty()) {
			for(int i = 0; i<planes.size();i++) {
				if(planes.get(i).getNombre() == nombre)
					return planes.get(i);
			}
		//}
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
     * @author Alejandro Cencerrado
     * @param tabla 
     * 
     * Rellena la tabla de albergues con los datos solicitados.
     */
    public void rellenarTablaAlbergues(JTable tabla){
        DefaultTableModel modeloT = new DefaultTableModel();
        tabla.setModel(modeloT);
        
        modeloT.addColumn("ID");
        modeloT.addColumn("Capacidad");
        modeloT.addColumn("Coordenada X");
        modeloT.addColumn("Coordenada Y");
        modeloT.addColumn("Ocupación");
        
        Object[] fila = new Object[5];
        
        ArrayList<Albergue> listaAlbergues = comunicaciones.solicitarObtenerAlbergues();

        for(int i=0; i<listaAlbergues.size(); i++) {
            fila[0] = listaAlbergues.get(i).getId();
            fila[1] = listaAlbergues.get(i).getCapacidad();
            fila[2] = listaAlbergues.get(i).getPosicion().getX();
            fila[3] = listaAlbergues.get(i).getPosicion().getY();
            fila[4] = listaAlbergues.get(i).getOcupacion();
            modeloT.addRow(fila);
        }   
       
        //AÑADO FILA VACIA PARA CLICAR Y RELLENAR EN LA NUEVA INSERCIÓN
        fila[0]="";
        fila[1]="";
        fila[2]="";
        fila[3]="";
        fila[4]="";
        modeloT.addRow(fila);
        
    }
    
    /**
     * @author Alejandro Cencerrado
     * @param tabla 
     * 
     * Rellena la tabla de Almacenes con los datos solicitados.
     */
    public void rellenarTablaAlmacenes(JTable tabla){
        DefaultTableModel modeloT = new DefaultTableModel();
        tabla.setModel(modeloT);
        
        modeloT.addColumn("ID");
        modeloT.addColumn("Cantidad Mantas");
        modeloT.addColumn("Cantidad Comida");
        modeloT.addColumn("Cantidad Agua");
        modeloT.addColumn("Coordenada X");
        modeloT.addColumn("Coordenada Y");
        modeloT.addColumn("Capacidad");
        
        Object[] fila = new Object[7];
        
        ArrayList<Almacen> listaAlmacenes = comunicaciones.solicitarObtenerAlmacenes();

        for(int i=0; i< listaAlmacenes.size(); i++) {
            fila[0] = listaAlmacenes.get(i).getId();
            fila[1] = listaAlmacenes.get(i).getCantidadMantas();
            fila[2] = listaAlmacenes.get(i).getCantidadComida();
            fila[3] = listaAlmacenes.get(i).getCantidadAgua();    
            fila[4] = listaAlmacenes.get(i).getPosicion().getX();
            fila[5] = listaAlmacenes.get(i).getPosicion().getY();
            fila[6] = listaAlmacenes.get(i).getCapacidad();
            modeloT.addRow(fila);
        }   
       
        //AÑADO FILA VACIA PARA CLICAR Y RELLENAR EN LA NUEVA INSERCIÓN
        fila[0]="";
        fila[1]="";
        fila[2]="";
        fila[3]="";
        fila[4]="";
        fila[5]="";
        fila[6]="";
        
        modeloT.addRow(fila);
        
    }
    
    /**
     * @author Alejandro Cencerrado
     * @param tabla 
     * 
     * Rellena la tabla de vehiculos con los datos solicitados.
     */
    public void rellenarTablaVehiculos(JTable tabla){
        DefaultTableModel modeloT = new DefaultTableModel();
        tabla.setModel(modeloT);
        
        modeloT.addColumn("ID");
        modeloT.addColumn("Modelo");
        modeloT.addColumn("Plazas");
        modeloT.addColumn("Coordenada X");
        modeloT.addColumn("Coordenada Y");
        modeloT.addColumn("Esta Disponible");
       
        Object[] fila = new Object[6];
        
        ArrayList<Vehiculo> listaVehiculos = comunicaciones.solicitarObtenerVehiculos();

        for(int i=0; i< listaVehiculos.size(); i++) {
            fila[0] = listaVehiculos.get(i).getId();
            fila[1] = listaVehiculos.get(i).getModelo();
            fila[2] = listaVehiculos.get(i).getPlazas();    
            fila[3] = listaVehiculos.get(i).getPosicion().getX();
            fila[4] = listaVehiculos.get(i).getPosicion().getY();
            fila[5] = listaVehiculos.get(i).isDisponible();
            modeloT.addRow(fila);
        }   
       
        //AÑADO FILA VACIA PARA CLICAR Y RELLENAR EN LA NUEVA INSERCIÓN
        fila[0]="";
        fila[1]="";
        fila[2]="";
        fila[3]="";
        fila[4]="";
        fila[5]="";
        
        modeloT.addRow(fila);
        
    }
    
    /**
     * @author Alejandro Cencerrado
     * @param tabla 
     * 
     * Rellena la tabla de voluntarios con los datos solicitados.
     */
    public void rellenarTablaVoluntarios(JTable tabla){
        DefaultTableModel modeloT = new DefaultTableModel();
        tabla.setModel(modeloT);
        
        modeloT.addColumn("ID");
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Teléfono");
        modeloT.addColumn("Correo");
        modeloT.addColumn("Coordenada X");
        modeloT.addColumn("Coordenada Y");
        modeloT.addColumn("Es Conductor");
        modeloT.addColumn("Esta Disponible");
       
        Object[] fila = new Object[8];
        
        ArrayList<Voluntario> listaVoluntarios = 
                                    comunicaciones.solicitarObtenerVoluntarios();

        for(int i=0; i< listaVoluntarios.size(); i++) {
            fila[0] = listaVoluntarios.get(i).getId();
            fila[1] = listaVoluntarios.get(i).getNombre();
            fila[2] = listaVoluntarios.get(i).getTelefono();
            fila[3] = listaVoluntarios.get(i).getCorreo();
            fila[4] = listaVoluntarios.get(i).getPosicion().getX();
            fila[5] = listaVoluntarios.get(i).getPosicion().getY();
            fila[6] = listaVoluntarios.get(i).getEsConductor();
            fila[7] = listaVoluntarios.get(i).getDisponible();
            modeloT.addRow(fila);
        }   
       
        //AÑADO FILA VACIA PARA CLICAR Y RELLENAR EN LA NUEVA INSERCIÓN
        fila[0]="";
        fila[1]="";
        fila[2]="";
        fila[3]="";
        fila[4]="";
        fila[5]="";
        fila[6]="";
        fila[7]="";
        
        modeloT.addRow(fila);       
    }
    
    /**
     * @author Alejandro Cencerrado
     * @param panel 
     */
    public void cargarPanel(JPanel panel){ 
       panel.setSize(ventanaPrincipal.getSize());       
       ventanaPrincipal.setContentPane(panel);
       ventanaPrincipal.revalidate();
    }
    
    /**
     * @Author Alejandro Cencerrado
     */
    public void recargarPanelAlbergues(){
        panelAlbergues = new PanelAlbergues(this);
        rellenarTablaAlbergues(panelAlbergues.jTableDatos);
        cargarPanel(panelAlbergues);    
    }
    
    /**
     * @Author Alejandro Cencerrado
     */
    public void recargarPanelAlmacenes(){
        panelAlmacenes = new PanelAlmacenes(this);
        rellenarTablaAlmacenes(panelAlmacenes.jTableDatos);
        cargarPanel(panelAlmacenes);    
    }
    
    /**
     * @Author Alejandro Cencerrado
     */
    public void recargarPanelVehiculos(){
        panelVehiculos = new PanelVehiculos(this);
        rellenarTablaVehiculos(panelVehiculos.jTableDatos);
        cargarPanel(panelVehiculos);    
    }
    
    /**
     * @Author Alejandro Cencerrado
     */
    public void recargarPanelVoluntarios(){
        panelVoluntarios = new PanelVoluntarios(this);
        rellenarTablaVoluntarios(panelVoluntarios.jTableDatos);
        cargarPanel(panelVoluntarios);    
    }
    
    /**
     * @Author Alejandro Cencerrado
     * 
     * Carga el mapa con todos los recursos.
     */
    public void cargarMapa(){
        panelMapa = new PanelMapa(this);
        
        ArrayList<Albergue> listaAlbergues = comunicaciones.solicitarObtenerAlbergues();
        ArrayList<Almacen> listaAlmacenes = comunicaciones.solicitarObtenerAlmacenes();
        ArrayList<Vehiculo> listaVehiculos = comunicaciones.solicitarObtenerVehiculos();
        ArrayList<Voluntario> listaVoluntarios = comunicaciones.solicitarObtenerVoluntarios();
        
        //crear nuevo mapa con lista de recursos seleccionados
        Mapa mapa= new Mapa(listaVoluntarios,listaVehiculos,listaAlmacenes,listaAlbergues); 
        //actualizar icono
        panelMapa.jLabelMapa.setIcon(mapa.verMapa());
        panelMapa.repaint();
        
        cargarPanel(panelMapa);        
    }
    
    /**
     * @Author Alejandro Cencerrado
     * Carga el mapa mostrando los recursos seleccionados en los checkbox
     */
    public void recargarMapaRecursos(){
        ArrayList<Albergue> listaAlbergues = new ArrayList<>();
        ArrayList<Almacen> listaAlmacenes = new ArrayList<>();
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        ArrayList<Voluntario> listaVoluntarios = new ArrayList<>();
        
        if(panelMapa.jCheckBoxAlbergues.isSelected()){ 
            listaAlbergues = comunicaciones.solicitarObtenerAlbergues();
        }

        if(panelMapa.jCheckBoxAlmacenes.isSelected()){ 
            listaAlmacenes = comunicaciones.solicitarObtenerAlmacenes();
        }

        if(panelMapa.jCheckBoxVehiculos.isSelected()){ 
            listaVehiculos = comunicaciones.solicitarObtenerVehiculos();
        }

        if(panelMapa.jCheckBoxVoluntarios.isSelected()){ 
            listaVoluntarios = comunicaciones.solicitarObtenerVoluntarios();
        }
        
        //crear nuevo mapa con lista de recursos seleccionados
        Mapa mapa= new Mapa(listaVoluntarios,listaVehiculos,listaAlmacenes,listaAlbergues); 
        //actualizar icono
        panelMapa.jLabelMapa.setIcon(mapa.verMapa());
        panelMapa.repaint();
        cargarPanel(panelMapa);
    }
    
    /**
     * @return 
     * @Author Alejandro Cencerrado
     * 
     * Devuelve un objeto de la clase Albergue con el 
     * contenido de  los Jtext del panel de albergues.
     */
    public Albergue leerJTextAlbergue(){
        try {
            
            String idAlbergue = panelAlbergues.jTextFieldId.getText();
     
            Coordenada coordenada = new Coordenada(
                Float.parseFloat(panelAlbergues.jTextFieldCoordenadaX.getText()),
                Float.parseFloat(panelAlbergues.jTextFieldCoordenadaY.getText())
            );

            int capacidad = Integer.parseInt(panelAlbergues.jTextFieldCapacidad.getText());
            int ocupacion = Integer.parseInt(panelAlbergues.jTextFieldOcupacion.getText());
            
            return new Albergue(idAlbergue, capacidad, coordenada, ocupacion);
             
        }
        
        catch (NumberFormatException e){
                JOptionPane.showMessageDialog(panelAlbergues, "Error! Formato incorrecto en alguno de los campos."); 
            }
        return null;      
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita al servidor la inserción de un nuevo albergue.
     */
    public void insertarAlbergue(){       
        Albergue albergue = leerJTextAlbergue();
        
        if (albergue != null){
            //Comprueba que no exista otro Albergue con misma Id.
            if (comunicaciones.solicitarBuscarAlbergue(albergue.getId()) != null){
                JOptionPane.showMessageDialog(panelAlbergues, "Ya existe un Albergue con la Id "+albergue.getId());  
            }

            else {
                if (comunicaciones.solicitarInsertarAlbergue(albergue))
                    recargarPanelAlbergues();     
            }
        }       
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita al servidor la eliminación de un nuevo albergue con la id deseada.
     */
    public void eliminarAlbergue(){
        String idAlbergue = panelAlbergues.jTextFieldId.getText();
                          
        Object[] opciones = {"Si", "No!"};
        int n = JOptionPane.showOptionDialog(ventanaPrincipal,
                "¿Esta seguro de que desea eliminar el albergue con Id: "+idAlbergue +"?",
                "Confirmación de Eliminación",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null, opciones,opciones[0]);

        if (n==0) {
            comunicaciones.solicitarEliminarAlbergue(idAlbergue);
            recargarPanelAlbergues();
        }
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita al servidor la modificación de un albergue.
     */
    public void modificarAlbergue(){
        Albergue albergue = leerJTextAlbergue();
        if (albergue != null){  
            if (comunicaciones.solicitarModificarAlbergue(albergue))         
                recargarPanelAlbergues();      
        }  
    }
    
     /**
     * @return 
     * @Author Alejandro Cencerrado
     * 
     * Devuelve un objeto de la clase Almacen con el 
     * contenido de  los Jtext del panel de almacenes.
     */
    public Almacen leerJTextAlmacen(){
        try {
            
            String idAlmacen = panelAlmacenes.jTextFieldId.getText();
            
            int cantidadMantas = Integer.parseInt(panelAlmacenes.jTextFieldCantidadMantas.getText());
            int cantidadComida = Integer.parseInt(panelAlmacenes.jTextFieldCantidadComida.getText());
            int cantidadAgua = Integer.parseInt(panelAlmacenes.jTextFieldCantidadAgua.getText());
     
            Coordenada coordenada = new Coordenada(
                Float.parseFloat(panelAlmacenes.jTextFieldCoordenadaX.getText()),
                Float.parseFloat(panelAlmacenes.jTextFieldCoordenadaY.getText())
            );

            int capacidad = Integer.parseInt(panelAlmacenes.jTextFieldCapacidad.getText());
            
            return new Almacen(idAlmacen, cantidadMantas, cantidadComida, cantidadAgua, coordenada, capacidad);           
             
        }
        
        catch (NumberFormatException e){
                JOptionPane.showMessageDialog(panelAlmacenes, "Error! Formato incorrecto en alguno de los campos."); 
            }
        return null;      
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita al servidor la inserción de un nuevo almacen.
     */
    public void insertarAlmacen(){       
        Almacen almacen = leerJTextAlmacen();
        
        if (almacen != null){
            //Comprueba que no exista otro Almacen con misma Id.
            if (comunicaciones.solicitarBuscarAlmacen(almacen.getId()) != null){
                JOptionPane.showMessageDialog(panelAlbergues, "Ya existe un Almacen con la Id "+almacen.getId());  
            }

            else {
                if(comunicaciones.solicitarInsertarAlmacen(almacen))
                    recargarPanelAlmacenes();     
            }
            
        } 
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita al servidor la eliminación de un almacen con la id deseada.
     */
    public void eliminarAlmacen(){
        String id = panelAlmacenes.jTextFieldId.getText();
                          
        Object[] opciones = {"Si", "No!"};
        int n = JOptionPane.showOptionDialog(ventanaPrincipal,
                "¿Esta seguro de que desea eliminar el almacen con Id: "+id +"?",
                "Confirmación de Eliminación",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null, opciones,opciones[0]);

        if (n==0) {
            comunicaciones.solicitarEliminarAlmacen(id);
            recargarPanelAlmacenes();
        }
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita al servidor la modificación de un  almacen.
     */
    public void modificarAlmacen(){
        Almacen almacen = leerJTextAlmacen();     
        if (almacen != null){
            if (comunicaciones.solicitarModificarAlmacen(almacen))
                recargarPanelAlmacenes();  
        }
    }
    
     /**
     * @return 
     * @Author Alejandro Cencerrado
     * 
     * Devuelve un objeto de la clase Albergue con el 
     * contenido de  los Jtext del panel de albergues.
     */
    public Vehiculo leerJTextVehiculo(){
        try {
            
            String idVehiculo = panelVehiculos.jTextFieldId.getText();            
            String modelo = panelVehiculos.jTextFieldModelo.getText();
            
            Coordenada coordenada = new Coordenada(
                Float.parseFloat(panelVehiculos.jTextFieldCoordenadaX.getText()),
                Float.parseFloat(panelVehiculos.jTextFieldCoordenadaY.getText())
            );

            int plazas = Integer.parseInt(panelVehiculos.jTextFieldPlazas.getText());           
            boolean disponible = Boolean.parseBoolean(panelVehiculos.jTextFieldDisponible.getText());
            
            return new Vehiculo(idVehiculo, modelo, plazas, coordenada, disponible);           
             
        }
        
        catch (NumberFormatException e){
                JOptionPane.showMessageDialog(panelVehiculos, "Error! Formato incorrecto en alguno de los campos."); 
            }
        return null;      
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita al servidor la inserción de un nuevo vehículo.
     */
    public void insertarVehiculo(){       
        Vehiculo vehiculo = leerJTextVehiculo();
        
        if (vehiculo != null){
            //Comprueba que no exista otro con misma Id.
            if (comunicaciones.solicitarBuscarVehiculo(vehiculo.getId()) != null){
                JOptionPane.showMessageDialog(panelVehiculos, "Ya existe un Vehículo con la Id "+vehiculo.getId());  
            }

            else {
                if (comunicaciones.solicitarInsertarVehiculo(vehiculo))
                    recargarPanelVehiculos();    
            }         
        } 
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita al servidor la eliminación de un vehiculo con id introducida.
     */
    public void eliminarVehiculo(){
        String id = panelVehiculos.jTextFieldId.getText();
                          
        Object[] opciones = {"Si", "No!"};
        int n = JOptionPane.showOptionDialog(ventanaPrincipal,
                "¿Esta seguro de que desea eliminar el vehículo con Id: "+id +"?",
                "Confirmación de Eliminación",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null, opciones,opciones[0]);

        if (n==0) {
            comunicaciones.solicitarEliminarVehiculo(id);
            recargarPanelVehiculos();
        }
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita al servidor la modificación de un vehículo.
     */
    public void modificarVehiculo(){
        Vehiculo vehiculo = leerJTextVehiculo();     
        if (vehiculo != null){
            if (comunicaciones.solicitarModificarVehiculo(vehiculo))
                recargarPanelVehiculos(); 
        }
    }
    
    
    /**
     * @return 
     * @Author Alejandro Cencerrado
     * 
     * Devuelve un objeto de la clase Voluntario con el 
     * contenido de  los Jtext del panel de Voluntarios.
     */
    public Voluntario leerJTextVoluntario(){
        try {
            
            String id = panelVoluntarios.jTextFieldId.getText();
            String nombre = panelVoluntarios.jTextFieldNombre.getText();
            String telefono = panelVoluntarios.jTextFieldTelefono.getText();
            String correo = panelVoluntarios.jTextFieldCorreo.getText();
     
            Coordenada coordenada = new Coordenada(
                Float.parseFloat(panelVoluntarios.jTextFieldCoordenadaX.getText()),
                Float.parseFloat(panelVoluntarios.jTextFieldCoordenadaY.getText())
            );

            boolean esConductor = Boolean.parseBoolean(panelVoluntarios.jTextFieldEsConductor.getText());
            boolean disponible = Boolean.parseBoolean(panelVoluntarios.jTextFieldEstaDisponible.getText());
            
            return new Voluntario(id, nombre, telefono, correo, coordenada, esConductor, disponible);
             
        }
        
        catch (NumberFormatException e){
                JOptionPane.showMessageDialog(panelAlbergues, "Error! Formato incorrecto en alguno de los campos."); 
            }
        return null;      
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita al servidor la inserción de un nuevo voluntario.
     */
    public void insertarVoluntario(){       
        Voluntario voluntario = leerJTextVoluntario();
        
        if (voluntario != null){
            //Comprueba que no exista otro con misma Id.
            if (comunicaciones.solicitarBuscarVoluntario(voluntario.getId()) != null){
                JOptionPane.showMessageDialog(panelVoluntarios, "Ya existe un Voluntario con la Id "+voluntario.getId());  
            }

            else {
                if (comunicaciones.solicitarInsertarVoluntario(voluntario))
                    recargarPanelVoluntarios();     
            }
        }       
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita al servidor la eliminación de un voluntario.
     */
    public void eliminarVoluntario(){
        String id = panelVoluntarios.jTextFieldId.getText();
                          
        Object[] opciones = {"Si", "No!"};
        int n = JOptionPane.showOptionDialog(ventanaPrincipal,
                "¿Esta seguro de que desea eliminar el voluntario con Id: "+id +"?",
                "Confirmación de Eliminación",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null, opciones,opciones[0]);

        if (n==0) {
            comunicaciones.solicitarEliminarVoluntario(id);
            recargarPanelVoluntarios();
        }
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita al servidor la modificación de un voluntario.
     */
    public void modificarVoluntario(){
        Voluntario voluntario = leerJTextVoluntario();
        if (voluntario != null){  
            if (comunicaciones.solicitarModificarVoluntario(voluntario))         
                recargarPanelVoluntarios();      
        }  
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
                List historialAlertas = comunicaciones.solicitarHistorialDeAlertas();  
                historialVista = new PanelHistorial(this);
                historialVista.introducirAlertasALista(historialAlertas);
                cargarPanel(historialVista);
                break;
            //@author Cristian
            case MENU_ITEM_ALERTAS:
                alertaVista = new PanelAlerta(this);
                List alertasActivas = comunicaciones.solicitarMapaAlertasActivas();
                alertaVista.introducirAlertasActivasALista(alertasActivas);

                cargarPanel(alertaVista);
                break;
            //@author Cristian
            case ACTIVAR_PLAN:
                Alerta alerta = (Alerta)obj;
                if(comunicaciones.
                    solicitarActivarPlanDeProteccion(String.valueOf(alerta.getId()))){
                    alertaVista.planActivado(alerta);
                    alertaVista.mensajeConfirmacionPlan(alerta);
                    
                }
                break;
                //@author Cristian
            case DESACTIVAR_ALERTA:
                Alerta alertaDesactivar = (Alerta)obj;
                if(comunicaciones.
                    solicitarDesactivarAlerta(
                            String.valueOf(alertaDesactivar.getId()))){
                    alertaVista.alertaEliminada(alertaDesactivar);
                    alertaVista.mensajeConfirmacionDesactivarAlerta(alertaDesactivar);
                }
                break;
                
            //Miguel
            case LISTAR_PLANES:
                
                break;
            case ADD_PLAN:
                addPlan((PlanProteccion)obj);
                break;
            case MOD_PLAN:
                comunicaciones.modPlan((PlanProteccion) obj);
                modPlan((PlanProteccion)obj);
                break;
            case ELIMINAR_PLAN:
                System.out.println("PLAN - protec civil: "+menuPlanes.selectedPlan.toString());
                comunicaciones.eliminarPlan((PlanProteccion) obj);
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
                menuPlanes.addPlanes(comunicaciones.solicitarPlanesProteccion());
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
                break;
                
                
            //ALEJANDRO
            // ----  ALBERGUES ----    //
            //@author Alejandro    
            case VER_PANEL_ALBERGUES:
                recargarPanelAlbergues();
                break;
            //@author Alejandro 
            case INSERTAR_ALBERGUE:
                insertarAlbergue();
                break;
            //@author Alejandro 
            case ELIMINAR_ALBERGUE:    
                eliminarAlbergue();
                break;
            //@author Alejandro 
            case MODIFICAR_ALBERGUE:
                modificarAlbergue();
                break;

            // ----  ALMACENES ----    //
             //@author Alejandro 
            case VER_PANEL_ALMACENES:
                recargarPanelAlmacenes();
                break;
            //@author Alejandro 
            case INSERTAR_ALMACEN:
                insertarAlmacen();
                break;
            //@author Alejandro 
            case ELIMINAR_ALMACEN:    
                eliminarAlmacen();
                break;
            //@author Alejandro 
            case MODIFICAR_ALMACEN:
                modificarAlmacen();
                break;


            // ---- VEHICULOS ---- //
            //@author Alejandro 
            case VER_PANEL_VEHICULOS:
                recargarPanelVehiculos();
                break;
            //@author Alejandro 
            case INSERTAR_VEHICULO:
                insertarVehiculo();
                break;
            //@author Alejandro 
            case ELIMINAR_VEHICULO:    
                eliminarVehiculo();
                break;
            //@author Alejandro 
            case MODIFICAR_VEHICULO:
                modificarVehiculo();
                break;

            // ---- VOLUNTARIOS ---- //
            //@author Alejandro 
            case VER_PANEL_VOLUNTARIOS:
                recargarPanelVoluntarios();
                break;
            //@author Alejandro 
            case INSERTAR_VOLUNTARIO:
                insertarVoluntario();
                break;
            //@author Alejandro 
            case ELIMINAR_VOLUNTARIO:    
                eliminarVoluntario();
                break;
            //@author Alejandro 
            case MODIFICAR_VOLUNTARIO:
                modificarVoluntario();
                break;

            // ---- MAPA RECURSOS ---- //
            //@author Alejandro 
            case VER_PANEL_MAPA_RECURSOS:     
                cargarMapa();
                break;
            //@author Alejandro  
            case RECARGAR_MAPA_RECURSOS:
                recargarMapaRecursos();
                break;
        }
    }
}  
