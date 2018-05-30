package Modelo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.Icon;

/**
 * 
 * @author Alejandro Cencerrado
 */
public class Mapa {
    
    private static final String BASE = "https://maps.googleapis.com/maps/api/staticmap?";
    private static final String ZOOM = "zoom=7";//numero de 0 a 21. zoom=0, se ve bola de mundo
    private static final String SENSOR = "sensor=false";
    private static final String FORMATO = "format=PNG"; //posibles formatos: PNG, GIF, JPEG
    private static final String SIZE = "size=640x640"; //tamaño de la imagen
    private static final String OPCIONES = "center=41.425520,-0.608294";
    private static String marcadores;
    
    private Icon icono;
    
    private List<Albergue> listaAlbergues;
    private List<Almacen> listaAlmacenes;
    private List<Vehiculo> listaVehiculos;
    private List<Voluntario> listaVoluntarios;
       
    private final String ICONOALBERGUE= "size:tiny%7Ccolor:0x2874A6";
    private final String ICONOVEHICULO = "size:tiny%7Ccolor:0xE74C3C";
    private final String ICONOALMACEN = "size:tiny%7Ccolor:0x196F3D";
    private final String ICONOVOLUNTARIO = "size:tiny%7Ccolor:0xE67E22";
    
    
    /**
     * 
     */
    public Mapa() {
        this(null,null,null,null);
    }
    
    public Mapa(List listaVoluntarios, List listaVehiculos, List listaAlmacenes, List listaAlbergues){
        marcadores = "";
        System.out.println(marcadores);
        
        this.listaVoluntarios = listaVoluntarios;
        this.listaVehiculos = listaVehiculos;
        this.listaAlmacenes = listaAlmacenes;
        this.listaAlbergues = listaAlbergues;
        
        
        introducirMarcadores();
        generarMapa();
    }
    
    
    /**
     * Añade marcador al mapa
     */
    private void introducirMarcador(Coordenada coordenada, String icono){
        marcadores += "&markers="+icono + "%7C" + coordenada.getX() + "," + coordenada.getY();
    }
    
    /**
     * 
     */
    private void introducirMarcadores(){
        if(listaVoluntarios  != null)
            for(Voluntario voluntario : listaVoluntarios){
                introducirMarcador(voluntario.getPosicion(),ICONOVOLUNTARIO);
            }
        if(listaVehiculos  != null)
            for(Vehiculo vehiculo : listaVehiculos){
                introducirMarcador(vehiculo.getPosicion(),ICONOVEHICULO);
            }
        
        
        if(listaAlmacenes  != null)
            for(Almacen almacen : listaAlmacenes){
                introducirMarcador(almacen.getPosicion(),ICONOALMACEN);
            }
        
        if(listaAlbergues  != null)
            for(Albergue albergue : listaAlbergues){
                introducirMarcador(albergue.getPosicion(),ICONOALBERGUE);
            }
    }
        
    /*
    ** Construye el mapa
    */
    private void generarMapa() {
        
        String url = crearUrl();
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
            generarMapa();
        }
        
        if(conexion){
            ImageIcon icon = new ImageIcon(bytes);
            this.icono = icon;
            
        }
    }
    
    public String crearUrl() {
        return BASE + "&" + SIZE + "&" + ZOOM + "&" + OPCIONES + "&" + SENSOR + "&" + FORMATO + "&" + marcadores ;
    }
    
    public Icon verMapa (){
        return icono;
    }  
}
