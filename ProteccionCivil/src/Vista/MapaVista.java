/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Alerta;
import Modelo.Coordenada;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Icon;

public class MapaVista extends JLabel{
    private static final String BASE = "http://maps.googleapis.com/maps/api/staticmap?";
    private static final String ZOOM = "zoom=7";//numero de 0 a 21. zoom=0, se ve bola de mundo
    private static final String SENSOR = "sensor=false";
    private static final String FORMATO = "format=PNG"; //posibles formatos: PNG, GIF, JPEG
    private static final String SIZE = "size=350x450"; //tamaño de la imagen
    private int contadorAlertas;
    private List<Alerta> alertasActivas;
    String opcs = "center=41.425520,-0.608294&scale=2&";
    String opcsInicial = "";
    
    public MapaVista() {
        opcsInicial = opcs;
        setVisible(true);
        contadorAlertas = 0;
        alertasActivas = new ArrayList<Alerta>();
        //opcs += "markers=label:A|41.6564561125247,-0.8787989616394043&markers=label:B|41.65710440168154,-0.8816099166870117&markers=label:C|41.656880958087804,-0.8819210529327393"; 
        visualizarMapa(opcs);
        //setPreferredSize(new Dimension(1000, 1000));
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
    public void introducirMarcadores(double x, double y, int marcador){
        contadorAlertas++;
        opcs += "markers=label:"+marcador + "|" + x + "," +y +"&";
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
            introducirMarcadores(alerta.getCoordenadas().verX()
                    , alerta.getCoordenadas().verY(),
                    alerta.getId());
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
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho - 1000, alto - 100, 0));
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
