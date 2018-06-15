/*//package Vista;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//import javax.swing.BoxLayout;
//import javax.swing.DefaultListModel;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JList;
//import javax.swing.JPanel;
//import javax.swing.JSplitPane;
//import javax.swing.JTextField;
//import javax.swing.JTextPane;
//import javax.swing.JToolBar;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//
//import Control.ProteccionCivil;
//import Modelo.Emergencia;
//
//public class MenuEmergenciasAlertas extends JPanel implements ActionListener, ListSelectionListener{
//
//	private JTextField txtProteccinCivil;
//	private OyenteVista oyenteVista;
//	private List<Emergencia> emergencias;
//	private DefaultListModel<String> listaEmergencias;
//	private Emergencia selectedEmergencia;
//	private JList lista;
//	private JTextPane textPane;
//	
////	public static final String BTN_MAIN_MENU = "Menu Principal";
////	public static final String BTN_ADD_ALERTA = "Añadir Alerta";
//	public static final String BTN_ADD_EMER = "Añadir Emergencias";
//	public static final String BTN_MOD_EMER = "Modificar Emergencias";
//	public static final String BTN_DEL_EMER = "Eliminar Emergencias";
//	**
//	 * Launch the application.
//	 *
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MenuEmergenciasAlertas frame = new MenuEmergenciasAlertas(new ProteccionCivil());
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	**
//	 * Create the frame.
//	 *
//	public MenuEmergenciasAlertas(OyenteVista oyenteVista) {
////		this.oyenteVista = oyenteVista;
////		setBounds(100, 100, 732, 498);
////		this.setBorder(new EmptyBorder(5, 5, 5, 5));
////		this.setLayout(new BorderLayout(0, 0));
////		
////		JPanel panel_principal = new JPanel();
////		this.add(panel_principal, BorderLayout.CENTER);
////		panel_principal.setLayout(new BorderLayout(0, 0));
////		
////		listaEmergencias = new DefaultListModel();
////		
////		txtProteccinCivil = new JTextField();
////		txtProteccinCivil.setEditable(false);
////		txtProteccinCivil.setFont(new Font("Tahoma", Font.PLAIN, 26));
////		txtProteccinCivil.setHorizontalAlignment(SwingConstants.CENTER);
////		txtProteccinCivil.setText("Gesti\u00F3n de Emergencias");
////		panel_principal.add(txtProteccinCivil, BorderLayout.NORTH);
////		txtProteccinCivil.setColumns(10);
////		
////		JPanel panel = new JPanel();
////		panel_principal.add(panel, BorderLayout.CENTER);
////		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//////		
//////		JButton btnAnadirAlerta = new JButton("A\u00F1adir Alerta");
//////		btnAnadirAlerta.setFont(new Font("Tahoma", Font.BOLD, 11));
//////		btnAnadirAlerta.setActionCommand(BTN_ADD_ALERTA);
//////		btnAnadirAlerta.addActionListener(this);
//////		panel.add(btnAnadirAlerta);
//////		
////		JButton btnGestionarEmergencias = new JButton("Gestionar Emergencias");
////		btnGestionarEmergencias.setToolTipText("");
////		btnGestionarEmergencias.setActionCommand(BTN_GESTION_EMER);
////		btnGestionarEmergencias.addActionListener(this);
////		panel.add(btnGestionarEmergencias);
//
//                this.oyenteVista = oyenteVista;
//		setBounds(100, 100, 732, 498);
//		this.setBorder(new EmptyBorder(5, 5, 5, 5));
//		this.setLayout(new BorderLayout(0, 0));
//		
//		JPanel panel_gestion_emergencias = new JPanel();
//		this.add(panel_gestion_emergencias, BorderLayout.SOUTH);
//		panel_gestion_emergencias.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//		
//		JButton button_anadir_emergencia = new JButton("A\u00F1adir Emergencia");
//		button_anadir_emergencia.addActionListener(this);
//                button_anadir_emergencia.setActionCommand(BTN_ADD_EMER);
//		panel_gestion_emergencias.add(button_anadir_emergencia);
//		
//		JButton button_modificar_emergencia = new JButton("Modificar Emergencia");
//		button_modificar_emergencia.addActionListener(this);
//                button_modificar_emergencia.setActionCommand(BTN_MOD_EMER);
//		panel_gestion_emergencias.add(button_modificar_emergencia);
//		
//		JButton button_eliminar_emergencia = new JButton("Eliminar Emergencia");
//		button_eliminar_emergencia.addActionListener(this);
//                button_eliminar_emergencia.setActionCommand(BTN_DEL_EMER);
//		panel_gestion_emergencias.add(button_eliminar_emergencia);
//		
//		JPanel panel_principal = new JPanel();
//		this.add(panel_principal, BorderLayout.CENTER);
//		panel_principal.setLayout(new BorderLayout(0, 0));
//		
//		JSplitPane splitPane = new JSplitPane();
//		panel_principal.add(splitPane, BorderLayout.CENTER);
//                splitPane.setResizeWeight(0.1);
//                splitPane.setEnabled(false);
//		
//		JPanel panel_lista_emergencias = new JPanel();
//		splitPane.setLeftComponent(panel_lista_emergencias);
//		panel_lista_emergencias.setLayout(new BoxLayout(panel_lista_emergencias, BoxLayout.Y_AXIS));
//		
//		listaEmergencias = new DefaultListModel();
//		//listaPlanes.addListSelectionListener(this);
//		lista = new JList(listaEmergencias);
//		lista.setValueIsAdjusting(true);
//		lista.addListSelectionListener(this);
//		panel_lista_emergencias.add(lista);
//		
//		JPanel panel = new JPanel();
//		splitPane.setRightComponent(panel);
//		
//		textPane = new JTextPane();
//		textPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		textPane.setEditable(false);
//		panel.add(textPane);
//		
//		txtProteccinCivil = new JTextField();
//		txtProteccinCivil.setEditable(false);
//		txtProteccinCivil.setFont(new Font("Tahoma", Font.PLAIN, 26));
//		txtProteccinCivil.setHorizontalAlignment(SwingConstants.CENTER);
//		txtProteccinCivil.setText("Gestión de Emergencias");
//		panel_principal.add(txtProteccinCivil, BorderLayout.NORTH);
//		txtProteccinCivil.setColumns(10);
//	}
//
//        public void mostrarEmergencias(){
//            listaEmergencias.clear();
//            for(int i = 0; i<this.emergencias.size();i++){
//                Emergencia emergencia = emergencias.get(i);
//                String infoEmer = emergencia.getTipo() + " - Nivel " + emergencia.getNivel();
//                listaEmergencias.addElement(infoEmer);
//            }
//            textPane.setText(emergencias.get(0).toString());
//        }
//        
//        public void addEmergencias(List emergencias){
//            this.emergencias = emergencias;
//            selectedEmergencia = (Emergencia)emergencias.get(0);
//            mostrarEmergencias();
//        }
//        
//        public void addEmergencia(Emergencia emergencia){
//            this.emergencias.add(emergencia);
//            mostrarEmergencias();
//        }
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		switch(e.getActionCommand()) {
////		case BTN_ADD_ALERTA:
////			System.out.println("añadir alerta");
////			MenuAddAlerta menuAlerta = new MenuAddAlerta(oyenteVista);
////			menuAlerta.setVisible(true);
////			break;
//		case BTN_ADD_EMER:
//                    new MenuAddEmergencia(oyenteVista).setVisible(true);
//                    System.out.println("add emergencias");
//                    break;
//                case BTN_MOD_EMER:
//                    System.out.println("mod emergencias");
//                    break;
//                case BTN_DEL_EMER:
//                    System.out.println("del emergencias");
//                    break;
//                }
//	}
//
//	@Override
//	public void valueChanged(ListSelectionEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//        public void update(List emergencias){
//            this.emergencias = emergencias;
//            mostrarEmergencias();
//        }
//}*/

package Vista;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Control.ProteccionCivil;
import Modelo.Emergencia;
import Modelo.PlanProteccion;

import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JList;
import java.awt.ScrollPane;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 
 * @author MiguelYanes
 */

public class MenuEmergencias extends JPanel implements ActionListener, Observer, ListSelectionListener {
	
	private JTextField txtProteccinCivil;
	private OyenteVista oyenteVista;
	private List<Emergencia> emergencias;
	private DefaultListModel<String> listaEmergencias;
	private JList lista;
	private JTextPane textPane;
	public Emergencia selectedEmergencia;
	
        public static final String ELIMINAR_EMERGENCIA = "Eliminar Emergencia";
	
	/**
	 * Inicia la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuEmergencias frame = new MenuEmergencias(new ProteccionCivil());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea el frame
	 */
	public MenuEmergencias(OyenteVista oyenteVista) {
		this.oyenteVista = oyenteVista;
		setBounds(100, 100, 732, 498);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0, 0));
                
		JPanel panel_principal = new JPanel();
		this.add(panel_principal, BorderLayout.CENTER);
		panel_principal.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		panel_principal.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel_lista_planes = new JPanel();
		splitPane.setLeftComponent(panel_lista_planes);
		panel_lista_planes.setLayout(new BoxLayout(panel_lista_planes, BoxLayout.Y_AXIS));
		
		listaEmergencias = new DefaultListModel();
		//listaPlanes.addListSelectionListener(this);
		lista = new JList(listaEmergencias);
		lista.setValueIsAdjusting(true);
		lista.addListSelectionListener(this);
		panel_lista_planes.add(lista);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPane.setEditable(false);
		panel.add(textPane);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		this.add(panel_1, BorderLayout.NORTH);
		
		JPanel panel_gestion_planes = new JPanel();
		panel_1.add(panel_gestion_planes);
		
		JButton button_anadir_plan = new JButton("A\u00F1adir Emergencia");
		button_anadir_plan.setHorizontalAlignment(SwingConstants.LEFT);
		button_anadir_plan.addActionListener(new AddPlanProteccion());
		panel_gestion_planes.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 5));
		panel_gestion_planes.add(button_anadir_plan);
		
		JButton button_modificar_plan = new JButton("Modificar Emergencia");
		button_modificar_plan.addActionListener(new ModPlanProteccion());
		panel_gestion_planes.add(button_modificar_plan);
		
		JButton button_eliminar_plan = new JButton("Eliminar Emergencia");
		button_eliminar_plan.addActionListener(new EliminarPlanProteccion());
                button_eliminar_plan.setActionCommand(ELIMINAR_EMERGENCIA);
		panel_gestion_planes.add(button_eliminar_plan);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
                JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setHgap(80);
		panel_2.add(panel_3);
                
		txtProteccinCivil = new JTextField();
		txtProteccinCivil.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel_2.add(txtProteccinCivil);
		txtProteccinCivil.setHorizontalAlignment(SwingConstants.CENTER);
		txtProteccinCivil.setEditable(false);
		txtProteccinCivil.setText("Emergencias");
		txtProteccinCivil.setColumns(15);
	
	}
	
	public void addEmergencia() {
		MenuAddEmergencia frame = new MenuAddEmergencia(oyenteVista);
		frame.setVisible(true);
	}
	
	public void modEmergencia() {
		MenuModEmergencia frame = new MenuModEmergencia(oyenteVista, selectedEmergencia);
		frame.setVisible(true);
		//MenuAddPlan frame = new MenuAddPlan(pCivil);
		//frame.setVisible(true);
	}
	
	public void eliminarEmergencia() {
		VentanaConfirmarEliminar jDialog = new VentanaConfirmarEliminar(oyenteVista, selectedEmergencia);
		jDialog.setVisible(true);
		//MenuAddPlan frame = new MenuAddPlan(pCivil);
		//frame.setVisible(true);
	}
	
	public void addEmergencias(List emergencias) {
		listaEmergencias.clear();
		this.emergencias = emergencias;
                if(!emergencias.isEmpty()){
                    for(int i = 0; i<this.emergencias.size();i++) {
                            Emergencia emergencia = this.emergencias.get(i);
                            listaEmergencias.addElement(emergencia.getTipo() + "-" + emergencia.getNivel());
                    }
                    selectedEmergencia = this.emergencias.get(0);
                    textPane.setText(this.emergencias.get(0).mostrar());
                }
	}

    @Override
    public void update(Observable o, java.lang.Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	class AddPlanProteccion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			addEmergencia();
		}
	}
	
	class ModPlanProteccion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			modEmergencia();
		}
	}
	
	class EliminarPlanProteccion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			eliminarEmergencia();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
                case ELIMINAR_EMERGENCIA:
                        if(selectedEmergencia==null)
                            selectedEmergencia = emergencias.get(0);                     
                        oyenteVista.notificacion(OyenteVista.Evento.ELIMINAR_PLAN,selectedEmergencia);
			//this.dispose();
                    break;
                }
	}

	//@Override
	public void update(List emergencias) {
		// TODO Auto-generated method stub
		listaEmergencias.clear();
		addEmergencias(emergencias);
	}
	
	public void update(String txtEmergencia) {
		// TODO Auto-generated method stub
		//textPane.
		textPane.setText(txtEmergencia);
	}

	public void update() {
		this.update(emergencias);
		this.update(emergencias.get(0).getTipo()+"-"+emergencias.get(0).getNivel());
	}
        
        public Emergencia getEmergencia(String id){
            if(!emergencias.isEmpty()){
                    for(int i = 0; i<this.emergencias.size();i++) {
                        if(emergencias.get(i).getId()==id){
                            return emergencias.get(i);
                        }
                    }
                }
            return null;
        }

	@Override
    public void valueChanged(ListSelectionEvent evt) {
		if(!evt.getValueIsAdjusting()) { //only execute once
			List values = lista.getSelectedValuesList();
			if(!values.isEmpty()) {
                            selectedEmergencia = getEmergencia(values.get(0).toString());
                            this.update(selectedEmergencia.mostrar());
			}
		}
    }
		
}