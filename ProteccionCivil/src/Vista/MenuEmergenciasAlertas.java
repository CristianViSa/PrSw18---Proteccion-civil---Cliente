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
import Modelo.Emergencia;

public class MenuEmergenciasAlertas extends JPanel implements ActionListener, ListSelectionListener{

	private JTextField txtProteccinCivil;
	private OyenteVista oyenteVista;
	private List<Emergencia> emergencias;
	private DefaultListModel<String> listaEmergencias;
	private Emergencia selectedEmergencia;
	private JList lista;
	private JTextPane textPane;
	
//	public static final String BTN_MAIN_MENU = "Menu Principal";
//	public static final String BTN_ADD_ALERTA = "Añadir Alerta";
	public static final String BTN_ADD_EMER = "Añadir Emergencias";
	public static final String BTN_MOD_EMER = "Modificar Emergencias";
	public static final String BTN_DEL_EMER = "Eliminar Emergencias";
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
	public MenuEmergenciasAlertas(OyenteVista oyenteVista) {
//		this.oyenteVista = oyenteVista;
//		setBounds(100, 100, 732, 498);
//		this.setBorder(new EmptyBorder(5, 5, 5, 5));
//		this.setLayout(new BorderLayout(0, 0));
//		
//		JPanel panel_principal = new JPanel();
//		this.add(panel_principal, BorderLayout.CENTER);
//		panel_principal.setLayout(new BorderLayout(0, 0));
//		
//		listaEmergencias = new DefaultListModel();
//		
//		txtProteccinCivil = new JTextField();
//		txtProteccinCivil.setEditable(false);
//		txtProteccinCivil.setFont(new Font("Tahoma", Font.PLAIN, 26));
//		txtProteccinCivil.setHorizontalAlignment(SwingConstants.CENTER);
//		txtProteccinCivil.setText("Gesti\u00F3n de Emergencias");
//		panel_principal.add(txtProteccinCivil, BorderLayout.NORTH);
//		txtProteccinCivil.setColumns(10);
//		
//		JPanel panel = new JPanel();
//		panel_principal.add(panel, BorderLayout.CENTER);
//		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
////		
////		JButton btnAnadirAlerta = new JButton("A\u00F1adir Alerta");
////		btnAnadirAlerta.setFont(new Font("Tahoma", Font.BOLD, 11));
////		btnAnadirAlerta.setActionCommand(BTN_ADD_ALERTA);
////		btnAnadirAlerta.addActionListener(this);
////		panel.add(btnAnadirAlerta);
////		
//		JButton btnGestionarEmergencias = new JButton("Gestionar Emergencias");
//		btnGestionarEmergencias.setToolTipText("");
//		btnGestionarEmergencias.setActionCommand(BTN_GESTION_EMER);
//		btnGestionarEmergencias.addActionListener(this);
//		panel.add(btnGestionarEmergencias);

                this.oyenteVista = oyenteVista;
		setBounds(100, 100, 732, 498);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_gestion_emergencias = new JPanel();
		this.add(panel_gestion_emergencias, BorderLayout.SOUTH);
		panel_gestion_emergencias.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton button_anadir_emergencia = new JButton("A\u00F1adir Emergencia");
		button_anadir_emergencia.addActionListener(this);
                button_anadir_emergencia.setActionCommand(BTN_ADD_EMER);
		panel_gestion_emergencias.add(button_anadir_emergencia);
		
		JButton button_modificar_emergencia = new JButton("Modificar Emergencia");
		button_modificar_emergencia.addActionListener(this);
                button_modificar_emergencia.setActionCommand(BTN_MOD_EMER);
		panel_gestion_emergencias.add(button_modificar_emergencia);
		
		JButton button_eliminar_emergencia = new JButton("Eliminar Emergencia");
		button_eliminar_emergencia.addActionListener(this);
                button_eliminar_emergencia.setActionCommand(BTN_DEL_EMER);
		panel_gestion_emergencias.add(button_eliminar_emergencia);
		
		JPanel panel_principal = new JPanel();
		this.add(panel_principal, BorderLayout.CENTER);
		panel_principal.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		panel_principal.add(splitPane, BorderLayout.CENTER);
                splitPane.setResizeWeight(0.1);
                splitPane.setEnabled(false);
		
		JPanel panel_lista_emergencias = new JPanel();
		splitPane.setLeftComponent(panel_lista_emergencias);
		panel_lista_emergencias.setLayout(new BoxLayout(panel_lista_emergencias, BoxLayout.Y_AXIS));
		
		listaEmergencias = new DefaultListModel();
		//listaPlanes.addListSelectionListener(this);
		lista = new JList(listaEmergencias);
		lista.setValueIsAdjusting(true);
		lista.addListSelectionListener(this);
		panel_lista_emergencias.add(lista);
		
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
		txtProteccinCivil.setText("Gestión de Emergencias");
		panel_principal.add(txtProteccinCivil, BorderLayout.NORTH);
		txtProteccinCivil.setColumns(10);
	}

        public void mostrarEmergencias(){
            listaEmergencias.clear();
            for(int i = 0; i<this.emergencias.size();i++){
                Emergencia emergencia = emergencias.get(i);
                String infoEmer = emergencia.getTipo() + " - Nivel " + emergencia.getNivel();
                listaEmergencias.addElement(infoEmer);
            }
            textPane.setText(emergencias.get(0).toString());
        }
        
        public void addEmergencias(List emergencias){
            this.emergencias = emergencias;
            mostrarEmergencias();
        }
        
        public void addEmergencia(Emergencia emergencia){
            this.emergencias.add(emergencia);
            mostrarEmergencias();
        }
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
//		case BTN_ADD_ALERTA:
//			System.out.println("añadir alerta");
//			MenuAddAlerta menuAlerta = new MenuAddAlerta(oyenteVista);
//			menuAlerta.setVisible(true);
//			break;
		case BTN_ADD_EMER:
                    new MenuAddEmergencia(oyenteVista).setVisible(true);
                    System.out.println("add emergencias");
                    break;
                case BTN_MOD_EMER:
                    System.out.println("mod emergencias");
                    break;
                case BTN_DEL_EMER:
                    System.out.println("del emergencias");
                    break;
                }
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

        public void update(List emergencias){
            this.emergencias = emergencias;
            mostrarEmergencias();
        }
}
