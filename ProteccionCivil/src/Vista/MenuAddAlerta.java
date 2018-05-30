package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Control.ProteccionCivil;
import Modelo.Alerta;
import Modelo.Coordenada;
import Modelo.Emergencia;
import Modelo.PlanProteccion;

import javax.swing.JCheckBox;
import javax.swing.JSplitPane;

public class MenuAddAlerta extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtProteccionCivil;
	private JTextField textField;
	
	private final OyenteVista pCivil;
	
	private static final String BTN_VOLVER = "Volver";
	private static final String BTN_ADD_ALERTA = "Add Alerta";
	private JTextField textCoordXForm;
	private JTextField txtCoordenada;
	private JTextField txtCoordenadaY;
	private JTextField textCoordYForm;
	private JTextField txtEmergencia;
	private JTextField textIDEmerForm;
	private JTextField txtEstado;
	private JTextField txtAfectados;
	private JTextField textAfectadosForm;
	private JCheckBox chckbxAlertaActiva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAddAlerta frame = new MenuAddAlerta(new ProteccionCivil());
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
	public MenuAddAlerta(OyenteVista pCivil) {
		this.pCivil = pCivil;
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
		
		JButton btnAadirPlan = new JButton("A\u00F1adir Alerta");
		panel_gestion_planes.add(btnAadirPlan);
		btnAadirPlan.setActionCommand(BTN_ADD_ALERTA);
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
		txtProteccinCivil.setText("Protecci\u00F3n Civil - A\u00F1adir Alerta");
		panel_principal.add(txtProteccinCivil, BorderLayout.NORTH);
		txtProteccinCivil.setColumns(10);
		
		JPanel panel = new JPanel();
		panel_principal.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		txtCoordenada = new JTextField();
		txtCoordenada.setEditable(false);
		txtCoordenada.setText("Coordenada X");
		panel_1.add(txtCoordenada, BorderLayout.WEST);
		txtCoordenada.setColumns(10);
		
		textCoordXForm = new JTextField();
		panel_1.add(textCoordXForm);
		textCoordXForm.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		txtCoordenadaY = new JTextField();
		txtCoordenadaY.setEditable(false);
		txtCoordenadaY.setText("Coordenada Y");
		panel_3.add(txtCoordenadaY, BorderLayout.WEST);
		txtCoordenadaY.setColumns(10);
		
		textCoordYForm = new JTextField();
		panel_3.add(textCoordYForm, BorderLayout.CENTER);
		textCoordYForm.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		txtEmergencia = new JTextField();
		txtEmergencia.setEditable(false);
		txtEmergencia.setText("id Emergencia");
		panel_5.add(txtEmergencia, BorderLayout.WEST);
		txtEmergencia.setColumns(10);
		
		textIDEmerForm = new JTextField();
		panel_5.add(textIDEmerForm, BorderLayout.CENTER);
		textIDEmerForm.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setText("Estado");
		panel_7.add(txtEstado, BorderLayout.WEST);
		txtEstado.setColumns(10);
		
		chckbxAlertaActiva = new JCheckBox("Alerta Activa");
		chckbxAlertaActiva.setFont(new Font("Tahoma", Font.ITALIC, 11));
		panel_7.add(chckbxAlertaActiva, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.NORTH);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		txtAfectados = new JTextField();
		txtAfectados.setEditable(false);
		txtAfectados.setText("Afectados");
		panel_9.add(txtAfectados, BorderLayout.WEST);
		txtAfectados.setColumns(10);
		
		textAfectadosForm = new JTextField();
		panel_9.add(textAfectadosForm, BorderLayout.CENTER);
		textAfectadosForm.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()) {
		case BTN_VOLVER:
			this.dispose();
			break;
		case BTN_ADD_ALERTA:
			//try {
				Coordenada coordenada = new Coordenada(Float.parseFloat(textCoordXForm.getText()), Float.parseFloat(textCoordYForm.getText()));
				Emergencia emergencia = null; //pCivil.notificacion(OyenteVista.Evento.GET_EMERGENCIA,Integer.parseInt(textIDEmerForm.getText()));
				int id = 0; //pCivil.notificacion(OyenteVista.Evento.GET_ID_ALERTA,null);
				boolean activa = chckbxAlertaActiva.isSelected();
				int afectados = Integer.parseInt(textAfectadosForm.getText());
				Alerta alerta = new Alerta(coordenada, emergencia, id, activa, afectados);
				System.out.println("add alerta: "+alerta.toString());
			/*}catch(Exception e1) {
				VentanaAlertaGenerica vAlerta = new VentanaAlertaGenerica("--Datos no válidos--\n\nLos vehículos y voluntarios\ndeben ser un número");
				vAlerta.setVisible(true);
			}*/
			break;
		}
	}

}
