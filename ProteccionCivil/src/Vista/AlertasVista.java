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
    
    private JPanel panelNorte;
    private JPanel panelSur;
    private JPanel panelCentral;
    private JPanel panelHistorial;
    private JPanel panelBotonHistorial;
    private JPanel panelRegistro;
    private JPanel panelLista;
    private JPanel panelBotonesAlertas;
    
    
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
//        super(TITULO);
        
        this.oyenteVista = oyenteVista;
        
        panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        add(panelNorte, BorderLayout.NORTH);
        creaBarraMenu(panelNorte);
        
        panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        
        panelCentral.setVisible(true);
        add(panelCentral, BorderLayout.CENTER);

        panelHistorial = new JPanel();
        panelHistorial.setLayout(new BorderLayout());
        panelHistorial.setVisible(false);
        
        panelBotonHistorial = new JPanel();
        panelBotonHistorial.setLayout(new FlowLayout());
        
        
        panelSur = new JPanel();
        panelSur.setLayout(new BorderLayout());
        add(panelSur, BorderLayout.SOUTH);
        
        panelRegistro = new JPanel();
        panelRegistro.setLayout(new BorderLayout());

        panelLista = new JPanel();
        panelLista.setLayout(new BorderLayout());
        
        panelBotonesAlertas = new JPanel();
        panelBotonesAlertas.setLayout(new FlowLayout());
        
        titulo = new JLabel(TITULO);
        titulo.setFont(new java.awt.Font("Arial Black", 0, 32));
        titulo.setPreferredSize(new Dimension(400, 100));
        

        panelRegistro.add(titulo, BorderLayout.WEST);
        
        /**
         * Crea los botones
         */
        creaBotones();
        /**
         * Crea las listas
         */
        creaListas();
        
/*        addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            oyenteVista.notificacion(OyenteVista.Evento.SALIR, null);
            }
        });*/

        panelCentral.add(panelRegistro, BorderLayout.NORTH);

        //panelCentral.add(titulo, BorderLayout.NORTH);
        
        //Crea el mapa
        mapaVista = new MapaVista();
        panelCentral.add(mapaVista, BorderLayout.CENTER);
        
//        pack();
     //   setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);  
       // setLocationRelativeTo(null);
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
        barraActivas.setPreferredSize(new Dimension(700, 800));
        
        infoAlertas = new JLabel(TEXTO_ALERTAS);
        
        panelLista.add(infoAlertas, BorderLayout.NORTH);
        panelLista.add(barraActivas, BorderLayout.CENTER);
        panelLista.add(panelBotonesAlertas, BorderLayout.SOUTH);
        
        panelCentral.add(panelLista, BorderLayout.EAST);
        
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
        barra.setPreferredSize(new Dimension(1000, 1000));
        panelHistorial.add(barra, BorderLayout.NORTH);
    }
    
    /**
     * Metodo empleado para cambiar el boton del panel sur
     */
    protected void cambiarBotonAlerta(){
        //panelSur.removeAll();
        panelBotonHistorial.remove(historial);
        panelBotonHistorial.add(volverAtras);
        panelSur.add(panelBotonHistorial);
        repaint();
    }
    
     /**
     * Metodo empleado para cambiar el boton del panel sur
     */
    protected void cambiarBotonVolver(){
        panelBotonHistorial.remove(volverAtras);
        panelBotonesAlertas.add(historial);
        repaint();
    }
    
    /**
     * Crea la barra de herramientas
     */
    protected void creaBarraMenu(JPanel panel){
        JToolBar barra = new JToolBar();
        barra.setFloatable(false);

        menuPrincipal = new JButton(MENU_PRINCIPAL);
        menuPrincipal.setToolTipText(MENU_PRINCIPAL);
        menuPrincipal.setActionCommand(MENU_PRINCIPAL);
        menuPrincipal.addActionListener(this);
        menuPrincipal.setEnabled(true);
        
        planes = new JButton(MENU_ITEM_PLANES);
        planes.setToolTipText(MENU_ITEM_PLANES);
        planes.setActionCommand(MENU_ITEM_PLANES);
        planes.addActionListener(this);
        planes.setEnabled(true);
        
        
        recursos = new JButton(MENU_ITEM_RECURSOS);
        recursos.setToolTipText(MENU_ITEM_RECURSOS);
        recursos.setActionCommand(MENU_ITEM_RECURSOS);
        recursos.addActionListener(this);
        recursos.setEnabled(true);

        alertas = new JButton(MENU_ITEM_ALERTAS);
        alertas.setToolTipText(MENU_ITEM_ALERTAS);
        alertas.setActionCommand(MENU_ITEM_ALERTAS);
        alertas.addActionListener(this);
        alertas.setEnabled(true);
        
        barra.add(menuPrincipal);
        barra.add(planes);
        barra.add(recursos);
        barra.add(alertas);
        
        panel.add(barra);
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
     * Busca una alerta en la lista de alertas activas
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
                panelCentral.setVisible(false);
                this.remove(panelCentral);
                panelHistorial.setVisible(true);
                add(panelHistorial);
                cambiarBotonAlerta();
                oyenteVista.notificacion(OyenteVista.Evento.HISTORIAL, null);
                break;
            case VOLVER_ATRAS :
                panelHistorial.setVisible(false);
                this.remove(panelHistorial);
                panelCentral.setVisible(true);
                add(panelCentral);
                cambiarBotonVolver();
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
                
                /*oyenteVista.notificacion(
                                OyenteVista.Evento.SELECCIONAR_ALERTA, null);*/
                break;
        }        
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
