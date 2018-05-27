package Vista;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Modelo.Alerta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author Cristian
 */
public class AlertasVista extends JPanel implements ActionListener, Observer{
    
    public static AlertasVista instancia = null;
    
    private MapaVista mapaVista;
    private OyenteVista oyenteVista;
    
    private List<Alerta> historialAlertas;
    private List<Alerta> alertasActivas;
    
    private JList listaAlertas;
    private DefaultListModel lista;
    
    private JList listaAlertasActivas;
    private DefaultListModel listaActivas;
    
    private JLabel infoHistorial;
    private JLabel etiquetaLista;
    private JLabel titulo;
    private JLabel infoAlertas;

    private JScrollPane barra;
    private JScrollPane barraActivas;
    
    private JPanel panelSur;
    private JPanel panelListaHistorial;
    private JPanel panelBotonHistorial;
    private JPanel panelRegistro;
    private JPanel panelLista;
    private JPanel panelBotonesAlertas;
    ////
    private JPanel panelMapa;
    private JPanel panelAlertas;
    
    private JPanel aplicacionAlertas;
    private JPanel aplicacionHistorial;
    
    private JButton menuPrincipal;
    private JButton historial;
    private JButton planes;
    private JButton recursos;
    private JButton alertas;
    private JButton volverAtras;
    private JButton seleccionarAlerta;

    
    private static final String TITULO = "Proteccion Civil";
    
    private static final String MENU_PRINCIPAL = "Menu principal";
    private static final String VOLVER_ATRAS = "Volver atras";

    public static final String BOTON_LOGIN = "Iniciar sesion";
    public static final String BOTON_REGISTRO = "Registrarse";
    public static final String BOTON_HISTORIAL = "Historial de alertas";
    public static final String MENU_ITEM_PLANES = "Planes de proteccion";
    public static final String MENU_ITEM_RECURSOS = "Gestion de recursos";
    public static final String MENU_ITEM_ALERTAS = "Gestion de alertas";
    public static final String TEXTO_ALERTAS = "Alertas activas no gestionadas";
    public static final String MENU_ITEM_SELECCIONAR_ALERTA = 
                                                        "Seleccionar alerta";
    public static final String MENU_ITEM_SALIR = "Salir";
    protected AlertasVista(OyenteVista oyenteVista){
        this.setLayout(new BorderLayout());
        this.oyenteVista = oyenteVista;
        
        aplicacionAlertas = new JPanel(new BorderLayout());
        aplicacionHistorial = new JPanel(new FlowLayout());
        
        panelMapa = new JPanel(new FlowLayout());
        panelAlertas = new JPanel(new BorderLayout());
        panelSur = new JPanel(new FlowLayout());

        panelListaHistorial = new JPanel(new BorderLayout());
        panelBotonHistorial = new JPanel(new FlowLayout());
     
        panelLista = new JPanel(new FlowLayout());
        panelBotonesAlertas = new JPanel(new BorderLayout());
        
        creaBotones();
        creaListas();
 
        mapaVista = new MapaVista();
        panelMapa.add(mapaVista);
        
        aplicacionAlertas.add(panelMapa, BorderLayout.WEST);
        aplicacionAlertas.add(panelAlertas, BorderLayout.CENTER);
        
        aplicacionHistorial.add(panelListaHistorial);
        
        
        add(aplicacionAlertas);
        setVisible(true);  
    }
    public static synchronized AlertasVista instancia(OyenteVista oyenteVista){
    if (instancia == null)
            instancia = new AlertasVista(oyenteVista);    
        return instancia;
    }
    
    /**
     * Crea los botones
     */
    protected void creaBotones(){
        
        /* 
        ** Crea el boton para volver atras desde el historial
        */
        volverAtras = new JButton(VOLVER_ATRAS);
        volverAtras.setToolTipText(VOLVER_ATRAS);
        volverAtras.setActionCommand(VOLVER_ATRAS);
        volverAtras.addActionListener(this);
        volverAtras.setEnabled(true);

        /* 
        ** Crea el boton para mostrar el historial de alertas
        */
        historial = new JButton(BOTON_HISTORIAL);
        historial.setToolTipText(BOTON_HISTORIAL);
        historial.setActionCommand(BOTON_HISTORIAL);
        historial.addActionListener(this);
        historial.setEnabled(true); 
        
         /* 
        ** Crea el boton para seleccionar una alerta
        */
        seleccionarAlerta = new JButton(MENU_ITEM_SELECCIONAR_ALERTA);
        seleccionarAlerta.setToolTipText(MENU_ITEM_SELECCIONAR_ALERTA);
        seleccionarAlerta.setActionCommand(MENU_ITEM_SELECCIONAR_ALERTA);
        seleccionarAlerta.addActionListener(this);
        seleccionarAlerta.setEnabled(true); 
        
        panelBotonesAlertas.add(seleccionarAlerta, BorderLayout.WEST);
        panelBotonesAlertas.add(historial, BorderLayout.EAST);
        
        panelBotonHistorial.add(volverAtras);
        panelListaHistorial.add(panelBotonHistorial, BorderLayout.SOUTH);

    }
    /**
     * Crea las listas
     */
    protected void creaListas(){
         /**
         * Crea la lista de alertas activas, sin gestionar
         */
        listaActivas = new DefaultListModel();

        listaAlertasActivas = new JList(listaActivas);
        listaAlertasActivas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaAlertasActivas.setLayoutOrientation(JList.VERTICAL);
        listaAlertasActivas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt){
                // No hace nada
            }
        } );
        
        barraActivas = new JScrollPane(listaAlertasActivas);
        barraActivas.setPreferredSize(new Dimension(690, 550));
        
        infoAlertas = new JLabel(TEXTO_ALERTAS);
       
        panelLista.add(infoAlertas, BorderLayout.NORTH);
        panelLista.add(barraActivas, BorderLayout.CENTER);
        panelLista.add(panelBotonesAlertas, BorderLayout.SOUTH);
        panelAlertas.add(panelLista);
        
        /**
         * Crea la lista de alertas 
         */
        lista = new DefaultListModel();

        listaAlertas = new JList(lista);
        listaAlertas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaAlertas.setLayoutOrientation(JList.VERTICAL);
        listaAlertas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt){
                // No hace nada
            }
        } );
        barra = new JScrollPane(listaAlertas);
        barra.setPreferredSize(new Dimension(900, 620));
        panelListaHistorial.add(barra, BorderLayout.CENTER);
    }
    
    /**
     * Rellena la lista(Coleccion) con el historial y rellena la lista(JList)
     */
    public void mostrarVentanaHistorial(List listado){
        historialAlertas = listado;
        introducirAlertasALista();
    }
    
    /**
     * Introduce las alertas activas a la lista                                 
     */
    public void introducirAlertasActivasALista(List listado){
        alertasActivas = listado;
        listaActivas.clear();
        for(int indice = 0; indice<alertasActivas.size();indice++){
            Alerta alerta = alertasActivas.get(indice);
            String info = alerta.informacion();
            listaActivas.addElement(info);
            mapaVista.introducirMarcadores(alerta.getCoordenadas().verX(),
                alerta.getCoordenadas().verY(), alerta.getId());
            mapaVista.introducirAlerta(alerta);
        }
        mapaVista.refrescar();
    }
    
     /**
     * Introduce un elemento a la lista de alertas
     */
    public void introducirAlertasALista() {
        lista.clear();
        for(int indice = 0; indice<historialAlertas.size();indice++){
            Alerta alerta = historialAlertas.get(indice);
            String info = alerta.toString();
            lista.addElement(info);
        }
    }
    
    /**
     * Busca una alerta en el historial de alertas
     */
    public Alerta buscarAlertaLista(String id){
        for(int indice = 0; indice<historialAlertas.size();indice++){
            Alerta alerta = historialAlertas.get(indice);
            if (id.equals(alerta.getId())){
                return alerta;
            }
        } 
        return null;
    }
    
    /**
     * Bucase MENU_ITEM_SALIR :
                oyenteVista.notificacion(OyenteVista.Evento.SALIR, null);
                break;
            case BOTON_HISTORIAL :
                remove(aplicacionAlertas);
                add(aplicacionHistorial);
                revalidate();
                oyenteVista.notificacion(OyenteVista.Evento.HISTORIAL, null);
                break;
            case VOLVER_ATRAS :
                remove(aplicacionHistorial);
                add(aplicacionAlertas);
                repaint();
                oyenteVista.notificacion(
                                OyenteVista.Evento.MENU_ITEM_ALERTAS, null);
                break;
            case MENU_ITEM_SELECCIONAR_ALERTA :
                String id = (String)listaAlertasActivas.getSelectedValue();
                Alerta alerta = buscarAlertaActivaLista(id);
                int seleccion = JOptionPane.showConfirmDialog
                        (null, 
                        "¿Activar el plan de proteccion para esta alerta?",
                        "Activar plan",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (seleccion == JOptionPane.YES_OPTION){
                    oyenteVista.notificacion(
                                OyenteVista.Evento.ACTIVAR_PLAN, alerta);
                }
                mapaVista.eliminarAlerta(alerta);
                alertasActivas.remove(alerta);
                introducirAlertasActivasALista(alertasActivas);
                mapaVista.actualizarMarcadores();

                break;
        }        
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
sca una alerta en la lista de alertas activas
     */
      public Alerta buscarAlertaActivaLista(String id){
        for(int indice = 0; indice<alertasActivas.size();indice++){
            Alerta alerta = alertasActivas.get(indice);
            if (id.equals(alerta.informacion())){
                return alerta;
            }
        } 
        return null;
    }
      
    /**
     * Pone el panel con el mapa y la lista de alertas
     */  
    public void ponerPanelAlertas(){
        removeAll();
        add(aplicacionAlertas);
    }
        
    
    /**
     * Muestra un mensaje de confirmacion, resultado de activar un plan
     */
    public void mensajeConfirmacionPlan(Alerta alerta){
        String texto = "Plan activado correctamente para la alerta :\n"
                +alerta.informacion();
        JOptionPane.showMessageDialog(null, texto);   
    }
    /**
     * Sobreescribe actionPerformed 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case MENU_ITEM_PLANES :
                MenuPlanesProteccion menuPlanes = new MenuPlanesProteccion(oyenteVista);
                menuPlanes.setVisible(true);
//                this.dispose();
                break;
            case MENU_ITEM_RECURSOS :
                break;    
            case MENU_ITEM_ALERTAS :
                oyenteVista.notificacion(
                                    OyenteVista.Evento.MENU_ITEM_ALERTAS, null);
                break;
            case MENU_ITEM_SALIR :
                oyenteVista.notificacion(OyenteVista.Evento.SALIR, null);
                break;
            case BOTON_HISTORIAL :
                remove(aplicacionAlertas);
                add(aplicacionHistorial);
                revalidate();
                oyenteVista.notificacion(OyenteVista.Evento.HISTORIAL, null);
                break;
            case VOLVER_ATRAS :
                remove(aplicacionHistorial);
                add(aplicacionAlertas);
                repaint();
                oyenteVista.notificacion(
                                OyenteVista.Evento.MENU_ITEM_ALERTAS, null);
                break;
            case MENU_ITEM_SELECCIONAR_ALERTA :
                String id = (String)listaAlertasActivas.getSelectedValue();
                Alerta alerta = buscarAlertaActivaLista(id);
                int seleccion = JOptionPane.showConfirmDialog
                        (null, 
                        "¿Activar el plan de proteccion para esta alerta?",
                        "Activar plan",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (seleccion == JOptionPane.YES_OPTION){
                    oyenteVista.notificacion(
                                OyenteVista.Evento.ACTIVAR_PLAN, alerta);
                }
                mapaVista.eliminarAlerta(alerta);
                alertasActivas.remove(alerta);
                introducirAlertasActivasALista(alertasActivas);
                mapaVista.actualizarMarcadores();

                break;
        }        
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
