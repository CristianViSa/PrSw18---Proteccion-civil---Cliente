package Vista;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

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

import Vista.MenuPlanesProteccion.AddPlanProteccion;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Dimension;


import Control.ProteccionCivil;
import Modelo.PlanProteccion;

import javax.swing.SpringLayout;

/**
 * 
 * @author MiguelYanes
 */
public class MenuAddPlan extends JFrame implements ActionListener, Observer{

	private JPanel contentPane;
	private JTextField txtProteccionCivil;
	private JTextField textField;
	private JTextField txtNombreForm;
	private JTextField txtVehiculosForm;
	private JTextField txtVoluntariosForm;
	private JTextField txtActuacionesNecesariasForm;
	private JTextField txtNombre;
	private JTextField txtVehculosNecesarios;
	private JTextField txtVoluntariosRequeridos;
	private JTextField txtActuacionesNecesarias_1;
	
	private final OyenteVista oyenteVista;
	
	private static final String BTN_VOLVER = "Volver";
	private static final String BTN_ADD_PLAN = "Add Plan";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAddPlan frame = new MenuAddPlan(new ProteccionCivil());
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
	public MenuAddPlan(OyenteVista oyenteVista) {
		this.oyenteVista = oyenteVista;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 732, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setPreferredSize(getMaximumSize());
		
		JPanel panel_gestion_planes = new JPanel();
		contentPane.add(panel_gestion_planes, BorderLayout.SOUTH);
		panel_gestion_planes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAadirPlan = new JButton("A\u00F1adir Plan");
		panel_gestion_planes.add(btnAadirPlan);
		btnAadirPlan.setActionCommand(BTN_ADD_PLAN);
		btnAadirPlan.addActionListener(this);
		
		JButton btnVolver = new JButton("Volver");
		panel_gestion_planes.add(btnVolver);
		btnVolver.setActionCommand(BTN_VOLVER);
		btnVolver.addActionListener(this);
		
		JPanel panel_principal = new JPanel();
		contentPane.add(panel_principal, BorderLayout.CENTER);
		panel_principal.setLayout(new BorderLayout(0, 0));
		
		JTextField txtProteccinCivil = new JTextField();
		txtProteccinCivil.setEditable(false);
		txtProteccinCivil.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtProteccinCivil.setHorizontalAlignment(SwingConstants.CENTER);
		txtProteccinCivil.setText("Protecci\u00F3n Civil - A\u00F1adir Planes de Protecci\u00F3n");
		panel_principal.add(txtProteccinCivil, BorderLayout.NORTH);
		txtProteccinCivil.setColumns(10);
		
		JPanel panel = new JPanel();
		panel_principal.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.setPreferredSize(new Dimension(200,200));
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setText("Nombre");
		panel_1.add(txtNombre, BorderLayout.NORTH);
		txtNombre.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		txtVehculosNecesarios = new JTextField();
		txtVehculosNecesarios.setEditable(false);
		txtVehculosNecesarios.setText("Veh\u00EDculos necesarios");
		panel_6.add(txtVehculosNecesarios, BorderLayout.NORTH);
		txtVehculosNecesarios.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		txtVoluntariosRequeridos = new JTextField();
		txtVoluntariosRequeridos.setEditable(false);
		txtVoluntariosRequeridos.setText("Voluntarios requeridos");
		panel_7.add(txtVoluntariosRequeridos, BorderLayout.NORTH);
		txtVoluntariosRequeridos.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		txtActuacionesNecesarias_1 = new JTextField();
		txtActuacionesNecesarias_1.setEditable(false);
		txtActuacionesNecesarias_1.setText("Actuaciones necesarias");
		panel_8.add(txtActuacionesNecesarias_1, BorderLayout.NORTH);
		txtActuacionesNecesarias_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		txtNombreForm = new JTextField();
		panel_2.add(txtNombreForm, BorderLayout.NORTH);
		txtNombreForm.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		txtVehiculosForm = new JTextField();
		panel_3.add(txtVehiculosForm, BorderLayout.NORTH);
		txtVehiculosForm.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		txtVoluntariosForm = new JTextField();
		panel_4.add(txtVoluntariosForm, BorderLayout.NORTH);
		txtVoluntariosForm.setColumns(10);
		
		txtActuacionesNecesariasForm = new JTextField();
		panel_4.add(txtActuacionesNecesariasForm, BorderLayout.CENTER);
		txtActuacionesNecesariasForm.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.EAST);
	
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case BTN_VOLVER:
			this.dispose();
			break;
		case BTN_ADD_PLAN:
			//try {
				String nombre = txtNombreForm.getText();
				int vehiculos = Integer.parseInt(txtVehiculosForm.getText());
				int voluntarios = Integer.parseInt(txtVoluntariosForm.getText());
				String actuaciones = txtActuacionesNecesariasForm.getText();
				PlanProteccion plan = new PlanProteccion(/*oyenteVista,*/ nombre, vehiculos, voluntarios, actuaciones);
                                oyenteVista.notificacion(OyenteVista.Evento.ADD_PLAN,plan);
				this.dispose();
			/*}catch(Exception e1) {
				VentanaAlertaGenerica vAlerta = new VentanaAlertaGenerica("--Datos no v�lidos--\n\nLos veh�culos y voluntarios\ndeben ser un n�mero");
				vAlerta.setVisible(true);
			}*/
			break;
		}
		
	}

}
