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

public class MenuGestionGenerico extends JPanel implements ActionListener, Observer, ListSelectionListener {
	
	//private JPanel contentPane;
	private JTextField txtTitulo;
	private OyenteVista oyenteVista;
	private List<Object> objects;
	private DefaultListModel<String> listaObjects;
	private JList lista;
	private JTextPane textPane;
	//private final OyenteVista pCivil;
	public Object selectedObj;
	
        public static final String ELIMINAR = "Eliminar";
	
	/**
	 * Inicia la aplicación
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGestionGenerico frame = new MenuGestionGenerico(new ProteccionCivil());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Crea el frame
	 */
	public MenuGestionGenerico(OyenteVista oyenteVista, String tipoDato) {
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
		
		listaObjects = new DefaultListModel();
		//listaPlanes.addListSelectionListener(this);
		lista = new JList(listaObjects);
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
		
		JButton button_anadir_plan = new JButton("A\u00F1adir "+tipoDato);
		button_anadir_plan.setHorizontalAlignment(SwingConstants.LEFT);
		button_anadir_plan.addActionListener(new AddPlanProteccion());
		panel_gestion_planes.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 5));
		panel_gestion_planes.add(button_anadir_plan);
		
		JButton button_modificar_plan = new JButton("Modificar "+tipoDato);
		button_modificar_plan.addActionListener(new ModPlanProteccion());
		panel_gestion_planes.add(button_modificar_plan);
		
		JButton button_eliminar_plan = new JButton("Eliminar "+tipoDato);
		button_eliminar_plan.addActionListener(new EliminarPlanProteccion());
                button_eliminar_plan.setActionCommand(ELIMINAR);
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
		txtTitulo.setText(tipoDato);
		txtTitulo.setColumns(15);
	
	}
	
	public void addObj() {
		MenuAddPlan frame = new MenuAddPlan(oyenteVista);
		frame.setVisible(true);
	}
	
	public void modObj() {
		MenuModPlan frame = new MenuModPlan(oyenteVista, selectedPlan);
		frame.setVisible(true);
		//MenuAddPlan frame = new MenuAddPlan(pCivil);
		//frame.setVisible(true);
	}
	
	public void eliminarObj() {
		System.out.println("eliminar: " + selectedObj.toString());
		VentanaConfirmarEliminar jDialog = new VentanaConfirmarEliminar(oyenteVista, selectedPlan);
		jDialog.setVisible(true);
		//MenuAddPlan frame = new MenuAddPlan(pCivil);
		//frame.setVisible(true);
	}
	
	public void addObjs(List planes) {
		listaObjects.clear();
		this.objects = planes;
                if(!planes.isEmpty()){
                    for(int i = 0; i<this.objects.size();i++) {
                            Object obj = this.objects.get(i);
                            listaObjects.addElement(obj.toString());
                    }
                    selectedObj = this.objects.get(0);
                    textPane.setText(this.objects.get(0).toString());
                }
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
                        PanelAlerta menuAlertas = new PanelAlerta(oyenteVista);
			//MenuEmergenciasAlertas menuEmer = new MenuEmergenciasAlertas(pCivil);
			//menuEmer.setVisible(true);
			//oyenteVista.notificacion(OyenteVista.Evento.BTN_MAIN_MENU, null);
			//this.dispose();
			break;
                case ELIMINAR_PLAN:
                        if(selectedPlan==null)
                            selectedPlan = planes.get(0);                     
                        System.out.println("Plan menu planes: "+selectedPlan.mostrar());
                        //oyenteVista.notificacion(OyenteVista.Evento.ELIMINAR_PLAN,selectedPlan);
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
        
        public PlanProteccion getPlan(String nombre){
            if(!planes.isEmpty()){
                    for(int i = 0; i<this.planes.size();i++) {
                        if(planes.get(i).getNombre()==nombre){
                            return planes.get(i);
                        }
                    }
                }
            System.out.println("no existe");
            return null;
        }

	@Override
    public void valueChanged(ListSelectionEvent evt) {
		if(!evt.getValueIsAdjusting()) { //only execute once
			List values = lista.getSelectedValuesList();
			if(!values.isEmpty()) {
                            selectedPlan = getPlan(values.get(0).toString());
                            this.update(selectedPlan.mostrar());
			}
		}
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
		
}
