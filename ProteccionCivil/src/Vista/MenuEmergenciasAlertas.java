package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Control.ProteccionCivil;
import Modelo.PlanProteccion;
import Vista.MenuPlanesProteccion.AddPlanProteccion;
import Vista.MenuPlanesProteccion.EliminarPlanProteccion;
import Vista.MenuPlanesProteccion.ModPlanProteccion;

public class MenuEmergenciasAlertas extends JFrame implements ActionListener, ListSelectionListener{

	private JPanel contentPane;
	private JTextField txtProteccinCivil;
	private OyenteVista oyenteVista;
	private List<PlanProteccion> planes;
	private DefaultListModel<String> listaPlanes;
	private final OyenteVista pCivil;
	private PlanProteccion selectedPlan;
	
	public static final String BTN_MAIN_MENU = "Menu Principal";
	public static final String BTN_ADD_ALERTA = "Añadir Alerta";
	public static final String BTN_GESTION_EMER = "Gestionar Emergencias";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuEmergenciasAlertas frame = new MenuEmergenciasAlertas(new ProteccionCivil());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuEmergenciasAlertas(OyenteVista pCivil) {
		this.pCivil = pCivil;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolbar_subsistemas = new JToolBar();
		toolbar_subsistemas.setFloatable(false);
		contentPane.add(toolbar_subsistemas, BorderLayout.NORTH);
		
		JButton button_menu_principal = new JButton(" Menu Principal ");
		button_menu_principal.setActionCommand(BTN_MAIN_MENU);
		button_menu_principal.addActionListener(this);
		
		toolbar_subsistemas.add(button_menu_principal);
		
		JButton button_planes_proteccion = new JButton(" Planes de Protecci\u00F3n ");
		toolbar_subsistemas.add(button_planes_proteccion);
		
		JButton button_emergencias_alertas = new JButton(" Gesti\u00F3n de emergencias y alertas ");
		button_emergencias_alertas.setEnabled(false);
		toolbar_subsistemas.add(button_emergencias_alertas);
		
		JButton button_reursos_medios = new JButton(" Recursos y medios ");
		button_reursos_medios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		toolbar_subsistemas.add(button_reursos_medios);
		
		JPanel panel_principal = new JPanel();
		contentPane.add(panel_principal, BorderLayout.CENTER);
		panel_principal.setLayout(new BorderLayout(0, 0));
		
		listaPlanes = new DefaultListModel();
		
		txtProteccinCivil = new JTextField();
		txtProteccinCivil.setEditable(false);
		txtProteccinCivil.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtProteccinCivil.setHorizontalAlignment(SwingConstants.CENTER);
		txtProteccinCivil.setText("Protecci\u00F3n Civil - Gesti\u00F3n de Emergencias y Alertas");
		panel_principal.add(txtProteccinCivil, BorderLayout.NORTH);
		txtProteccinCivil.setColumns(10);
		
		JPanel panel = new JPanel();
		panel_principal.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAnadirAlerta = new JButton("A\u00F1adir Alerta");
		btnAnadirAlerta.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAnadirAlerta.setActionCommand(BTN_ADD_ALERTA);
		btnAnadirAlerta.addActionListener(this);
		panel.add(btnAnadirAlerta);
		
		JButton btnGestionarEmergencias = new JButton("Gestionar Emergencias");
		btnGestionarEmergencias.setToolTipText("");
		btnGestionarEmergencias.setActionCommand(BTN_GESTION_EMER);
		btnGestionarEmergencias.addActionListener(this);
		panel.add(btnGestionarEmergencias);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case BTN_ADD_ALERTA:
			System.out.println("añadir alerta");
			MenuAddAlerta menuAlerta = new MenuAddAlerta(pCivil);
			menuAlerta.setVisible(true);
			break;
		case BTN_GESTION_EMER:
			System.out.println("gestionar emergencias");
			break;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
