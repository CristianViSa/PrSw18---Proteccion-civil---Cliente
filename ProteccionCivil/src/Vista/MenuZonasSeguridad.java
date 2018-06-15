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
import Modelo.ZonaSeguridad;

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

public class MenuZonasSeguridad extends JPanel implements ActionListener, Observer, ListSelectionListener {
	
	//private JPanel contentPane;
	private JTextField txtTitulo;
	private OyenteVista oyenteVista;
	private List<ZonaSeguridad> zonas;
	private DefaultListModel<String> listaZonas;
	private JList lista;
	private JTextPane textPane;
	//private final OyenteVista pCivil;
	public ZonaSeguridad selectedZona;
	
        public static final String ELIMINAR_ZONA = "Eliminar Zona";
	
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
	public MenuZonasSeguridad(OyenteVista oyenteVista) {
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
		
		listaZonas = new DefaultListModel();
		//listaPlanes.addListSelectionListener(this);
		lista = new JList(listaZonas);
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
		
		JButton button_anadir_plan = new JButton("A\u00F1adir Zona de Seguridad");
		button_anadir_plan.setHorizontalAlignment(SwingConstants.LEFT);
		button_anadir_plan.addActionListener(new AddPlanProteccion());
		panel_gestion_planes.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 5));
		panel_gestion_planes.add(button_anadir_plan);
		
		JButton button_modificar_plan = new JButton("Modificar Zona de Seguridad");
		button_modificar_plan.addActionListener(new ModPlanProteccion());
		panel_gestion_planes.add(button_modificar_plan);
		
		JButton button_eliminar_plan = new JButton("Eliminar Zona de Seguridad");
		button_eliminar_plan.addActionListener(new EliminarPlanProteccion());
                button_eliminar_plan.setActionCommand(ELIMINAR_ZONA);
		panel_gestion_planes.add(button_eliminar_plan);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
                JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setHgap(80);
		panel_2.add(panel_3);
                
		txtTitulo = new JTextField();
		txtTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel_2.add(txtTitulo);
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo.setEditable(false);
		txtTitulo.setText("Zonas de Seguridad");
		txtTitulo.setColumns(15);
	
	}
	
	public void addZona() {
		MenuAddZona frame = new MenuAddZona(oyenteVista);
		frame.setVisible(true);
	}
	
	public void modZona() {
		MenuModZona frame = new MenuModZona(oyenteVista/*, selectedZona*/);
		frame.setVisible(true);
		//MenuAddPlan frame = new MenuAddPlan(pCivil);
		//frame.setVisible(true);
	}
	
	public void eliminarZona() {
		System.out.println("eliminarZona: " + selectedZona.toString());
		VentanaConfirmarEliminar jDialog = new VentanaConfirmarEliminar(oyenteVista, selectedZona);
		jDialog.setVisible(true);
		//MenuAddPlan frame = new MenuAddPlan(pCivil);
		//frame.setVisible(true);
	}
	
	public void addZonas(List zonas) {
		listaZonas.clear();
		this.zonas = zonas;
                if(!zonas.isEmpty()){
                    for(int i = 0; i<this.zonas.size();i++) {
                            ZonaSeguridad zona = this.zonas.get(i);
                            listaZonas.addElement(zona.getCoordenada().toString());
                    }
                    selectedZona = this.zonas.get(0);
                    textPane.setText(this.zonas.get(0).toString());
                }
	}
	
	class AddPlanProteccion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			addZona();
		}
	}
	
	class ModPlanProteccion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			modZona();
		}
	}
	
	class EliminarPlanProteccion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			eliminarZona();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
                case ELIMINAR_ZONA:
                        if(selectedZona==null)
                            selectedZona = zonas.get(0);                     
                        //oyenteVista.notificacion(OyenteVista.Evento.ELIMINAR_PLAN,selectedPlan);
			//this.dispose();
                    break;
                }
	}

	//@Override
	public void update(List zonas) {
		// TODO Auto-generated method stub
		listaZonas.clear();
		addZonas(zonas);
	}
	
	public void update(String txtZona) {
		// TODO Auto-generated method stub
		//textPane.
		textPane.setText(txtZona);
	}

	public void update() {
		this.update(zonas);
		this.update(zonas.get(0).toString());
	}
        
        public ZonaSeguridad getZona(int id){
            if(!zonas.isEmpty()){
                    for(int i = 0; i<this.zonas.size();i++) {
                        if(zonas.get(i).getId()==id){
                            return zonas.get(i);
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
                            //selectedZona = getZona(values.get(0).toString());
                            this.update(selectedZona.toString());
			}
		}
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
		
}
