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
 * @version P01 - 15/4/18
 */

public class MenuPlanesProteccion extends JPanel implements ActionListener, Observer, ListSelectionListener {
	
	//private JPanel contentPane;
	private JTextField txtProteccinCivil;
	private OyenteVista oyenteVista;
	private List<PlanProteccion> planes;
	private DefaultListModel<String> listaPlanes;
	private JList lista;
	private JTextPane textPane;
	//private final OyenteVista pCivil;
	private PlanProteccion selectedPlan;
	
	public static final String BTN_MENU_PLANES= "Menu Planes";
	public static final String BTN_MENU_EMERGENCIAS = "Menu Emergencias";
	
	/**
	 * Inicia la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPlanesProteccion frame = new MenuPlanesProteccion(new ProteccionCivil());
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
	public MenuPlanesProteccion(OyenteVista oyenteVista) {
		this.oyenteVista = oyenteVista;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 498);
		//this = new JPanel();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(this);
		this.setLayout(new BorderLayout(0, 0));
		
//		JToolBar toolbar_subsistemas = new JToolBar();
//		toolbar_subsistemas.setFloatable(false);
//		this.add(toolbar_subsistemas, BorderLayout.NORTH);
//		
//		JButton button_menu_principal = new JButton(" Menu Principal ");
//		button_menu_principal.setActionCommand(BTN_MENU_PLANES);
//		button_menu_principal.addActionListener(this);
//		
//		toolbar_subsistemas.add(button_menu_principal);
//		
//		JButton button_planes_proteccion = new JButton(" Planes de Protecci\u00F3n ");
//		button_planes_proteccion.setEnabled(false);
//		toolbar_subsistemas.add(button_planes_proteccion);
//		
//		JButton button_emergencias_alertas = new JButton(" Gesti\u00F3n de emergencias y alertas ");
//		button_emergencias_alertas.setActionCommand(BTN_MENU_EMERGENCIAS);
//		button_emergencias_alertas.addActionListener(this);
//		toolbar_subsistemas.add(button_emergencias_alertas);
//		
//		JButton button_reursos_medios = new JButton(" Recursos y medios ");
//		button_reursos_medios.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});
//		toolbar_subsistemas.add(button_reursos_medios);
//		
		JPanel panel_gestion_planes = new JPanel();
		this.add(panel_gestion_planes, BorderLayout.SOUTH);
		panel_gestion_planes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton button_anadir_plan = new JButton("A\u00F1adir Plan protecci\u00F3n");
		button_anadir_plan.addActionListener(new AddPlanProteccion());
		panel_gestion_planes.add(button_anadir_plan);
		
		JButton button_modificar_plan = new JButton("Modificar Plan protecci\u00F3n");
		button_modificar_plan.addActionListener(new ModPlanProteccion());
		panel_gestion_planes.add(button_modificar_plan);
		
		JButton button_eliminar_plan = new JButton("Eliminar Plan Protecci\u00F3n");
		button_eliminar_plan.addActionListener(new EliminarPlanProteccion());
		panel_gestion_planes.add(button_eliminar_plan);
		
		JPanel panel_principal = new JPanel();
		this.add(panel_principal, BorderLayout.CENTER);
		panel_principal.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		panel_principal.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel_lista_planes = new JPanel();
		splitPane.setLeftComponent(panel_lista_planes);
		panel_lista_planes.setLayout(new BoxLayout(panel_lista_planes, BoxLayout.Y_AXIS));
		
		listaPlanes = new DefaultListModel();
		//listaPlanes.addListSelectionListener(this);
		lista = new JList(listaPlanes);
		lista.setValueIsAdjusting(true);
		lista.addListSelectionListener(this);
		panel_lista_planes.add(lista);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPane.setEditable(false);
		panel.add(textPane);
		
		txtProteccinCivil = new JTextField();
		txtProteccinCivil.setEditable(false);
		txtProteccinCivil.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtProteccinCivil.setHorizontalAlignment(SwingConstants.CENTER);
		txtProteccinCivil.setText("Protecci\u00F3n Civil - Planes de Protecci\u00F3n");
		panel_principal.add(txtProteccinCivil, BorderLayout.NORTH);
		txtProteccinCivil.setColumns(10);
		
	}
	
	public void addPlan() {
		MenuAddPlan frame = new MenuAddPlan(oyenteVista);
		frame.setVisible(true);
	}
	
	public void modPlan() {
		MenuModPlan frame = new MenuModPlan(oyenteVista, selectedPlan);
		frame.setVisible(true);
		//MenuAddPlan frame = new MenuAddPlan(pCivil);
		//frame.setVisible(true);
	}
	
	public void eliminarPlan() {
		System.out.println("eliminarPlan");
		VentanaConfirmarEliminar jDialog = new VentanaConfirmarEliminar(oyenteVista, selectedPlan);
		jDialog.setVisible(true);
		//MenuAddPlan frame = new MenuAddPlan(pCivil);
		//frame.setVisible(true);
	}
	
	public void addPlanes(List planes) {
		listaPlanes.clear();
		this.planes = planes;
		for(int i = 0; i<this.planes.size();i++) {
			PlanProteccion plan = this.planes.get(i);
			listaPlanes.addElement(plan.getNombre());
		}
		selectedPlan = this.planes.get(0);
		textPane.setText(this.planes.get(0).toString());
	}
	
	class AddPlanProteccion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			addPlan();
		}
	}
	
	class ModPlanProteccion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			modPlan();
		}
	}
	
	class EliminarPlanProteccion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			eliminarPlan();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case BTN_MENU_PLANES:
			System.out.println("menu planes");
			break;
		case BTN_MENU_EMERGENCIAS:
			System.out.println("menu emergencias");
                        AlertasVista menuAlertas = new AlertasVista(oyenteVista);
			//MenuEmergenciasAlertas menuEmer = new MenuEmergenciasAlertas(pCivil);
			//menuEmer.setVisible(true);
			//oyenteVista.notificacion(OyenteVista.Evento.BTN_MAIN_MENU, null);
			//this.dispose();
			break;
		}
	}

	//@Override
	public void update(List planes) {
		// TODO Auto-generated method stub
		listaPlanes.clear();
		addPlanes(planes);
	}
	
	public void update(String txtPlan) {
		// TODO Auto-generated method stub
		//textPane.
		textPane.setText(txtPlan);
	}

	public void update() {
		this.update(planes);
		this.update(planes.get(0).getNombre());
		
	}

	@Override
    public void valueChanged(ListSelectionEvent evt) {
		if(!evt.getValueIsAdjusting()) { //only execute once
			List values = lista.getSelectedValuesList();
			if(!values.isEmpty()) {
				System.out.println("Valores lista: " + values.toString());
				selectedPlan = null;//pCivil.notificacion(OyenteVista.Evento.GET_PLAN_NOMBRE, values.get(0).toString());
				System.out.println("Plan: " + selectedPlan.toString());
		        this.update(selectedPlan.toString());
			}
		}
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
		
}
