/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  @author Cristian
 */
package Vista;

import Modelo.Alerta;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Icon;

public class MapaVista extends JLabel{
    private static final String BASE = "http://maps.googleapis.com/maps/api/staticmap?";
    private static final String ZOOM = "zoom=7";//numero de 0 a 21. zoom=0, se ve bola de mundo
    private static final String SENSOR = "sensor=false";
    private static final String FORMATO = "format=PNG"; //posibles formatos: PNG, GIF, JPEG
    private static final String SIZE = "size=350x450"; //tamaño de la imagen
    private final String ICONOGESTIONADAS = "size:mid%7Ccolor:0x4fd926";
    private final String ICONOSINGESTIONAR = "size:mid%7Ccolor:0xffff00";
    private int contadorAlertas;
    private List<Alerta> alertasActivas;
    String opcs = "center=41.425520,-0.608294&scale=2&";
    String opcsInicial = "";
    
    public MapaVista() {
        opcsInicial = opcs;
        setVisible(true);
        contadorAlertas = 0;
        alertasActivas = new ArrayList<Alerta>(); 
        visualizarMapa(opcs);

    }
    
    /**
     * Refresa el mapa
     */
    public void refrescar(){
        visualizarMapa(opcs);
    }
    
    /**
     * Introduce una alerta al mapa(coleccion)
     */
    public void introducirAlerta(Alerta alerta){
        if(buscarAlerta(alerta) == null){
            alertasActivas.add(alerta);
       };
    }
    
    /**
     * Busca una alerta en el Map
     */
    public Alerta buscarAlerta(Alerta alerta){
        Alerta resultado = null;
        for (Alerta aux : alertasActivas) {
            if (aux.informacion().equals(alerta.informacion())) {
                resultado = aux;
                break;
            }
    }
    return resultado;
    }
    /**
     * Pone un marcador en el mapa
     */
    public void introducirMarcadores(double x, double y, int marcador,
            boolean gestionada){
        contadorAlertas++;
        if (gestionada){
            opcs += "markers="+ ICONOGESTIONADAS+ "|label:"+marcador + "|" + x + "," +y +"&";
        }
        else{
            opcs += "markers="+ ICONOSINGESTIONAR+ "|label:"+marcador + "|" + x + "," +y +"&";
          
        }
    }
    
    /**
     * Elimina una alerta del mapa
     */
    public void eliminarAlerta(Alerta alerta){
        alertasActivas.remove(buscarAlerta(alerta));
        actualizarMarcadores();
    }
    
    /*
    ** Actualiza los marcadores con la lista de alertas
    */
    public void actualizarMarcadores(){
        opcs = opcsInicial;
        for (Alerta alerta : alertasActivas) {
            introducirMarcadores(alerta.getCoordenadas().getX()
                    , alerta.getCoordenadas().getY(),
                    alerta.getId(), alerta.estaGestionada());
        }
        visualizarMapa(opcs);
    }
    /*
    ** Visualiza el mapa
    */
    public void visualizarMapa(String opciones) {
        String url = crearUrl(opciones);
        byte bytes[] = null;
        boolean conexion = true;
        try {
            URLConnection con = new URL(url).openConnection();
            InputStream is = con.getInputStream();
            bytes = new byte[con.getContentLength()];       
            int b;  
            int i = 0;
            while ((b=is.read()) != -1) {
                bytes[i] = (byte)b;
                i++;
            }
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            //Vuelve a intentar la conexion
            conexion = false;
            visualizarMapa(opciones);
        }
        
        if(conexion){
            ImageIcon icon = new ImageIcon(bytes);
            int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
            int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(600, 600, 0));
            setIcon(icono);  
        }
    }
    
    private static String crearUrl(String opciones) {
        String res = BASE + "&" + SIZE + "&" + ZOOM + "&" + opciones + "&" + SENSOR + "&" + FORMATO;
        return res;
    }
    
    public Icon verIcono(){
        return this.getIcon();
    }
}
