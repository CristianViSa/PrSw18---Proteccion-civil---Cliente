package Control;

import Modelo.Albergue;
import Modelo.Alerta;
import Modelo.Almacen;
import Modelo.Coordenada;
import Modelo.Emergencia;
import Modelo.Mensaje;
import Modelo.Operacion;
import Modelo.Vehiculo;
import Modelo.Voluntario;
import Modelo.PlanProteccion;
import Modelo.ZonaSeguridad;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristian, Alejandro y Miguel
 */
public class Comms {
    private Socket socket;
    private int puerto;
    private String ip;
    private static final String ipServidor = "155.210.68.154";
    
    private static final String ACTIVAR_PLAN = "ACTIVARPLAN";
    private static final String ALERTAS_MAPA = "ALERTASMAPA";
    private static final String HISTORIAL_ALERTAS = "HISTORIALALERTAS";

    ObjectOutputStream salida;
    ObjectInputStream entrada;
    
    List<Alerta> alertas;
    List<PlanProteccion> planes;
    List<Emergencia> emergencias;
    List<ZonaSeguridad> zonas;
    
    
    public Comms(int puerto){
        socket = null;
        this.puerto = puerto;
        ip = ipServidor;
    }


    /**
     * Solicita las alertas no gestionadas a la BD del servidor
     * @author Cristian
     */
    public synchronized List<Alerta>  solicitarMapaAlertasActivas () {
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
 
            alertas = new ArrayList<Alerta>();
            alertas.clear();
            
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.ALERTAS_MAPA); 
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String parametros = mensajeRX.verParametros();
            String delims = ",";
            String[] tokens = parametros.split(delims);
            int numAlertas = Integer.parseInt(tokens[0]);
            int longitudParametros = 11;
            int posicion;
            for(int i = 0; i < numAlertas; i++){
                posicion = i*longitudParametros;
                int id = Integer.parseInt(tokens[1+posicion]);

                String tipoEmergencia = tokens[2+posicion];
                int nivelEmergencia = 
                        Integer.parseInt(tokens[3+posicion]);
                float x = Float.parseFloat(tokens[4+posicion]);
                float y = Float.parseFloat(tokens[5+posicion]);
                Coordenada coordenadas = new Coordenada(x, y);
                int afectados = Integer.parseInt(tokens[6+posicion]);
                boolean activada = Boolean.parseBoolean(tokens[7+posicion]);
                int dia = Integer.parseInt(tokens[8+posicion]);
                int mes = Integer.parseInt(tokens[9+posicion]);
                int anio = Integer.parseInt(tokens[10+posicion]);
                boolean gestionada = Boolean.parseBoolean(tokens[11+posicion]);
                Date fecha = new Date(anio, mes, dia);
                Emergencia emergencia = new Emergencia(tipoEmergencia, nivelEmergencia);
                Alerta alerta = new Alerta(coordenadas, emergencia, id, gestionada, fecha,
                    activada, afectados);
                alertas.add(alerta);    
            }
        socket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return alertas;
    }
    
    /**
     * Solicita activar un plan de proteccion para una alerta al servidor
     *  @author Cristian
     */
    public synchronized boolean solicitarActivarPlanDeProteccion(String id){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            alertas = new ArrayList<Alerta>();
            alertas.clear();

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.ACTIVAR_PLAN); 
            mensajeTX.ponerParametros(id); 
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String parametros = mensajeRX.verParametros();
            String delims = ",";
            String[] tokens = parametros.split(delims);
            if("true".equals(tokens[0])){
                socket.close();
                return true;
            }  
            else{
                JOptionPane.showMessageDialog(null, tokens[0], "Error", JOptionPane.ERROR_MESSAGE);    
            }
            socket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /**
     * Solicita activar un plan de proteccion para una alerta al servidor
     *  @author Cristian
     */
    public synchronized boolean solicitarDesactivarAlerta(String id){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            alertas = new ArrayList<Alerta>();
            alertas.clear();

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.DESACTIVAR_ALERTA); 
            mensajeTX.ponerParametros(id); 
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String parametros = mensajeRX.verParametros();
            String delims = ",";
            String[] tokens = parametros.split(delims);
            if("true".equals(tokens[0])){
                socket.close();
                return true;
            }  
            else{
                JOptionPane.showMessageDialog(null, tokens[0], "Error", JOptionPane.ERROR_MESSAGE);
            }
            socket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /**
     * Solicitar Historial de alertas a la BD del servidor
     * @author Cristian
     */
    public synchronized List<Alerta> solicitarHistorialDeAlertas(){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            alertas = new ArrayList<Alerta>();
            alertas.clear();

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.HISTORIAL_ALERTAS); 
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String parametros = mensajeRX.verParametros();
            String delims = ",";
            String[] tokens = parametros.split(delims);
            int numAlertas = Integer.parseInt(tokens[0]);
            int longitudParametros = 11;
            int posicion;
            for(int i = 0; i < numAlertas; i++){
                posicion = i*longitudParametros;
                int id = Integer.parseInt(tokens[1+posicion]);

                String tipoEmergencia = tokens[2+posicion];
                int nivelEmergencia = 
                        Integer.parseInt(tokens[3+posicion]);
                float x = Float.parseFloat(tokens[4+posicion]);
                float y = Float.parseFloat(tokens[5+posicion]);
                Coordenada coordenadas = new Coordenada(x, y);
                int afectados = Integer.parseInt(tokens[6+posicion]);
                boolean activada = Boolean.parseBoolean(tokens[7+posicion]);
                int dia = Integer.parseInt(tokens[8+posicion]);
                int mes = Integer.parseInt(tokens[9+posicion]);
                int anio = Integer.parseInt(tokens[10+posicion]);
                boolean gestionada = Boolean.parseBoolean(tokens[11+posicion]);
                Date fecha = new Date(anio, mes, dia);
                Emergencia emergencia = new Emergencia(tipoEmergencia, nivelEmergencia);
                Alerta alerta = new Alerta(coordenadas, emergencia, id, gestionada, fecha,
                    activada, afectados);
                alertas.add(alerta);    
            }
            socket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return alertas;
    }
    
       /**
     * @author Alejandro Cencerrado
     * 
     * Conecta con el servidor y solicita la lista de voluntarios.
     * 
     * Recibe el mensaje y lo parsea creando una lista de voluntarios que devolverá.
     */
    public synchronized ArrayList<Voluntario> solicitarObtenerVoluntarios(){
        
        ArrayList<Voluntario> listaVoluntarios = new ArrayList<Voluntario>();
        
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
            
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.OBTENER_LISTA_VOLUNTARIOS); 
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje) entrada.readObject();
            
            String parametros = mensajeRX.verParametros();

            String delims = ",";
            String[] tokens = parametros.split(delims);
            
            int numVoluntarios = Integer.parseInt(tokens[0]);
            int cantidadParametros = 8;
            int posicion;
            
            for(int i = 0 ; i < numVoluntarios ; i++){
                posicion = i * cantidadParametros;
     
                String id = tokens[1 + posicion];
                String nombre = tokens[2 + posicion];
                String telefono = tokens[3 + posicion];
                String correo = tokens[4 + posicion];

                float x = Float.parseFloat(tokens[5 + posicion]);
                float y = Float.parseFloat(tokens[6 + posicion]);                
                Coordenada coordenadas = new Coordenada(x, y);
                
                boolean esConductor = Boolean.parseBoolean(tokens[7 + posicion]);
                boolean disponible = Boolean.parseBoolean(tokens[8 + posicion]);
                
                Voluntario voluntario = new Voluntario(id, nombre, telefono, 
                                            correo, coordenadas, esConductor, disponible);
                listaVoluntarios.add(voluntario);  
            }
        socket.close();  
        return listaVoluntarios;
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
        return null;
    }
    
    /**
     * @author Alejandro Cencerrado
     * 
     * Conecta con el servidor y solicita la lista de Vehículos.
     * 
     * Recibe el mensaje y lo parsea creando una lista de vehículos que devolverá.
     * @return 
     */
    public synchronized ArrayList<Vehiculo> solicitarObtenerVehiculos(){
        
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
        
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
            
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.OBTENER_LISTA_VEHICULOS); 
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String parametros = mensajeRX.verParametros();
            String delims = ",";
            String[] tokens = parametros.split(delims);
            
            int cantidad = Integer.parseInt(tokens[0]);
            int cantidadParametros = 6;
            int posicion;
            
            for(int i = 0 ; i < cantidad ; i++){
                
                posicion = i * cantidadParametros;
                
                String id = tokens[1 + posicion];
                String modelo = tokens[2 + posicion];
                int plazas = Integer.parseInt(tokens[3 + posicion]);

                float x = Float.parseFloat(tokens[4 + posicion]);
                float y = Float.parseFloat(tokens[5 + posicion]);                
                Coordenada coordenadas = new Coordenada(x, y);
                
                boolean disponible = Boolean.parseBoolean(tokens[6 + posicion]);
                
                Vehiculo vehiculo = new Vehiculo(id, modelo, plazas, coordenadas, disponible);
                listaVehiculos.add(vehiculo);  
                
            }
        socket.close();  
        return listaVehiculos;
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
        return null;
    }

    /**
     * @author Alejandro Cencerrado
     * 
     * Conecta con el servidor y solicita la lista de Almacenes.
     * 
     * Recibe el mensaje y lo parsea creando una lista de Almacenes que devolverá.
     * @return 
     */
    public synchronized ArrayList<Almacen> solicitarObtenerAlmacenes(){
        
        ArrayList<Almacen> listaAlmacenes = new ArrayList<Almacen>();
        
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
            
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.OBTENER_LISTA_ALMACENES); 
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String parametros = mensajeRX.verParametros();
            String delims = ",";
            String[] tokens = parametros.split(delims);
            
            int cantidad = Integer.parseInt(tokens[0]);
            int cantidadParametros = 7;
            int posicion;
            
            for(int i = 0 ; i < cantidad ; i++){
                posicion = i * cantidadParametros;             

                String id = tokens[1 + posicion];
                int cantidadMantas = Integer.parseInt(tokens[2 + posicion]);
                int cantidadComida = Integer.parseInt(tokens[3 + posicion]);
                int cantidadAgua = Integer.parseInt(tokens[4 + posicion]);
                
                float x = Float.parseFloat(tokens[5 + posicion]);
                float y = Float.parseFloat(tokens[6 + posicion]);                
                Coordenada coordenadas = new Coordenada(x, y);
                
                int capacidad = Integer.parseInt(tokens[7 + posicion]);

                Almacen almacen = new Almacen(id, cantidadMantas, cantidadComida, cantidadAgua, coordenadas, capacidad);
                listaAlmacenes.add(almacen);  
                
            }
        socket.close();  
        return listaAlmacenes;
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
        return null;
    }
    
    
    /**
     * @author Alejandro Cencerrado
     * 
     * Conecta con el servidor y solicita la lista de Albergues.
     * 
     * Recibe el mensaje y lo parsea creando una lista de Albergues que devolverá.
     * @return 
     */
    public synchronized ArrayList<Albergue> solicitarObtenerAlbergues(){
        
        ArrayList<Albergue> listaAlbergues = new ArrayList<Albergue>();
        
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
            
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.OBTENER_LISTA_ALBERGUES); 
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String parametros = mensajeRX.verParametros();
            String delims = ",";
            String[] tokens = parametros.split(delims);
            
            int cantidad = Integer.parseInt(tokens[0]);
            int cantidadParametros = 5;
            int posicion;
            
            for(int i = 0 ; i < cantidad ; i++){
                
                posicion = i * cantidadParametros;
                
                String id = tokens[1 + posicion];
                int capacidad = Integer.parseInt(tokens[2 + posicion]);
                
                float x = Float.parseFloat(tokens[3 + posicion]);
                float y = Float.parseFloat(tokens[4 + posicion]);                
                Coordenada coordenadas = new Coordenada(x, y);
                
                int ocupacion = Integer.parseInt(tokens[5 + posicion]);

                Albergue albergue = new Albergue(id, capacidad, coordenadas,ocupacion);
                listaAlbergues.add(albergue);  
                
            }
        socket.close();  
        return listaAlbergues;
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
        return null;
    }

    /**
     * @author Alejandro Cencerrado
     * Solicita la eliminación de un voluntario y recibe el resultado.
     * @param id
     * @return 
     */
    public synchronized boolean solicitarEliminarVoluntario(String id){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.ELIMINAR_VOLUNTARIO); 
            mensajeTX.ponerParametros(id);
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String resultado = mensajeRX.verParametros();
            socket.close();
            
            if(resultado.equals("true")){  
                return true;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita la eliminación de un albergue y recibe el resultado.
     * @param id
     * @return 
     */
    public synchronized boolean solicitarEliminarAlbergue(String id){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.ELIMINAR_ALBERGUE); 
            mensajeTX.ponerParametros(id);
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String resultado = mensajeRX.verParametros();
            socket.close();
            
            if(resultado.equals("true")){    
                return true;
            }  
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita la eliminación de un vehiculo y recibe el resultado.
     * @param id
     * @return 
     */
    public synchronized boolean solicitarEliminarVehiculo(String id){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.ELIMINAR_VEHICULO); 
            mensajeTX.ponerParametros(id);
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String resultado = mensajeRX.verParametros();
            socket.close();
            
            if(resultado.equals("true")){
                return true;
            }  
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
        /**
     * @author Alejandro Cencerrado
     * Solicita la eliminación de un almacen y recibe el resultado.
     * @param id
     * @return 
     */
    public synchronized boolean solicitarEliminarAlmacen(String id){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.ELIMINAR_ALMACEN); 
            mensajeTX.ponerParametros(id);
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String resultado = mensajeRX.verParametros();
            socket.close();
            
            if(resultado.equals("true")){
                return true;
            }  
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita la inserción del vehículo pasado por parámetro.
     * @param vehiculo
     * @return 
     */
    public synchronized boolean solicitarInsertarVehiculo(Vehiculo vehiculo){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.INSERTAR_VEHICULO);     
            mensajeTX.ponerParametros(vehiculo.toString());
            
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String resultado = mensajeRX.verParametros();
            socket.close();
            
            if(resultado.equals("true")){
                return true;
            }  
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita la inserción del Voluntario pasado por parámetro.
     * @param voluntario
     * @return 
     */
    public synchronized boolean solicitarInsertarVoluntario(Voluntario voluntario){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.INSERTAR_VOLUNTARIO);     
            mensajeTX.ponerParametros(voluntario.toString());
            
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String resultado = mensajeRX.verParametros();  
            socket.close();
            
            if(resultado.equals("true")){
                return true;
            }  
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
        /**
     * @author Alejandro Cencerrado
     * Solicita la inserción del Almacen pasado por parámetro.
     */
    public synchronized boolean solicitarInsertarAlmacen(Almacen almacen){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.INSERTAR_ALMACEN);     
            mensajeTX.ponerParametros(almacen.toString());
            
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String resultado = mensajeRX.verParametros();
            socket.close();
            
            if(resultado.equals("true")){
                return true;
            }  
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
        /**
     * @author Alejandro Cencerrado
     * Solicita la inserción del Albergue pasado por parámetro.
     * @param albergue
     * @return 
     */
    public synchronized boolean solicitarInsertarAlbergue(Albergue albergue){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.INSERTAR_ALBERGUE);     
            mensajeTX.ponerParametros(albergue.toString());
            
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String resultado = mensajeRX.verParametros();
            socket.close();
            
            if(resultado.equals("true")){   
                return true;
            }  
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
     /**
     * @author Alejandro Cencerrado
     * Solicita la modificación del vehículo pasado por parámetro.
     * @param vehiculo
     * @return 
     */
    public synchronized boolean solicitarModificarVehiculo(Vehiculo vehiculo){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.MODIFICAR_VEHICULO);     
            mensajeTX.ponerParametros(vehiculo.toString());
            
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String resultado = mensajeRX.verParametros();
            socket.close();
            if(resultado.equals("true")){
                return true;
            }  
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita la modificación del Voluntario pasado por parámetro.
     * @param voluntario
     * @return 
     */
    public synchronized boolean solicitarModificarVoluntario(Voluntario voluntario){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.MODIFICAR_VOLUNTARIO);     
            mensajeTX.ponerParametros(voluntario.toString());
            
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String resultado = mensajeRX.verParametros();
            socket.close();
            if(resultado.equals("true")){
                
                return true;
            }  
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
        /**
     * @author Alejandro Cencerrado
     * Solicita la modificación del Almacen pasado por parámetro.
     */
    public synchronized boolean solicitarModificarAlmacen(Almacen almacen){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.MODIFICAR_ALMACEN);     
            mensajeTX.ponerParametros(almacen.toString());
            
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String resultado = mensajeRX.verParametros();
            socket.close();
            if(resultado.equals("true")){     
                return true;
            }  
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
     /**
     * @author Alejandro Cencerrado
     * Solicita la modificación del Albergue pasado por parámetro.
     * @param albergue
     * @return 
     */
    public synchronized boolean solicitarModificarAlbergue(Albergue albergue){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.MODIFICAR_ALBERGUE);     
            mensajeTX.ponerParametros(albergue.toString());
            
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String resultado = mensajeRX.verParametros();
            socket.close();
            if(resultado.equals("true")){
                return true;
            }  
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita la búsqueda de un Voluntario en la bd.
     * @param id
     * @return 
     */
    public synchronized Voluntario solicitarBuscarVoluntario(String id){
        Voluntario voluntario = null;
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.BUSCAR_VOLUNTARIO);     
            mensajeTX.ponerParametros(id);
            
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();            
            
            String parametros = mensajeRX.verParametros();

            String delims = ",";
            String[] tokens = parametros.split(delims);
            
            if(!tokens[0].equals("null")){
                String idVoluntario = tokens[0];
                String nombre = tokens[1];
                String telefono = tokens[2];
                String correo = tokens[3];

                float x = Float.parseFloat(tokens[4]);
                float y = Float.parseFloat(tokens[5]);                
                Coordenada coordenadas = new Coordenada(x, y);

                boolean esConductor = Boolean.parseBoolean(tokens[6]);
                boolean disponible = Boolean.parseBoolean(tokens[7]);

                voluntario = new Voluntario(idVoluntario, nombre, telefono, 
                                            correo, coordenadas, esConductor, disponible);   
            }
                    
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return voluntario;
    }
    
    
    /**
     * @author Alejandro Cencerrado
     * Solicita la búsqueda de un Vehiculo en la bd.
     * @return 
     */
    public synchronized Vehiculo solicitarBuscarVehiculo(String id){
        Vehiculo vehiculo = null;
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.BUSCAR_VOLUNTARIO);     
            mensajeTX.ponerParametros(id);
            
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();            
            
            String parametros = mensajeRX.verParametros();

            String delims = ",";
            String[] tokens = parametros.split(delims);
            
            if(!tokens[0].equals("null")){
                String idVehiculo = tokens[0];
                String modelo = tokens[1];
                int plazas = Integer.parseInt(tokens[2]);

                float x = Float.parseFloat(tokens[3]);
                float y = Float.parseFloat(tokens[4]);                
                Coordenada coordenadas = new Coordenada(x, y);
                
                boolean disponible = Boolean.parseBoolean(tokens[5]);
                
                vehiculo = new Vehiculo(idVehiculo, modelo, plazas, coordenadas, disponible);
                 
            }
                    
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return vehiculo;
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita la búsqueda de un Almacen en la bd.
     * @param id
     * @return 
     */
    public synchronized Almacen solicitarBuscarAlmacen(String id){
        Almacen almacen = null;
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.BUSCAR_VOLUNTARIO);     
            mensajeTX.ponerParametros(id);
            
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();            
            
            String parametros = mensajeRX.verParametros();

            String delims = ",";
            String[] tokens = parametros.split(delims);
            
            if(!tokens[0].equals("null")){
                String idAlmacen = tokens[0];
                int cantidadMantas = Integer.parseInt(tokens[1]);
                int cantidadComida = Integer.parseInt(tokens[2]);
                int cantidadAgua = Integer.parseInt(tokens[3]);
                
                float x = Float.parseFloat(tokens[4]);
                float y = Float.parseFloat(tokens[5]);                
                Coordenada coordenadas = new Coordenada(x, y);
                
                int capacidad = Integer.parseInt(tokens[6]);

                almacen = new Almacen(idAlmacen, cantidadMantas, cantidadComida, cantidadAgua, coordenadas, capacidad);
                 
            }
                    
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return almacen;
    }
    
    /**
     * @author Alejandro Cencerrado
     * Solicita la búsqueda de un Voluntario en la bd.
     * @param id
     * @return 
     */
    public synchronized Albergue solicitarBuscarAlbergue(String id){
        Albergue albergue = null;
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.BUSCAR_VOLUNTARIO);     
            mensajeTX.ponerParametros(id);
            
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();            
            
            String parametros = mensajeRX.verParametros();

            String delims = ",";
            String[] tokens = parametros.split(delims);
            
            if(!tokens[0].equals("null")){
                
                String idAlbergue = tokens[0];
                int capacidad = Integer.parseInt(tokens[1]);
                
                float x = Float.parseFloat(tokens[2]);
                float y = Float.parseFloat(tokens[3]);                
                Coordenada coordenadas = new Coordenada(x, y);
                
                int ocupacion = Integer.parseInt(tokens[4]);

                albergue = new Albergue(idAlbergue, capacidad, coordenadas,ocupacion);
                 
            }
                    
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE); 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return albergue;
    }
    
    //------------------------
    //--Planes de Proteccion--
    //------------------------
    public synchronized List<PlanProteccion>  solicitarPlanesProteccion () {
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
 
            planes = new ArrayList<PlanProteccion>();
            planes.clear();
            
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.LISTAR_PLANES); 
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String parametros = mensajeRX.verParametros();
            String delims = ",";
            String[] tokens = parametros.split(delims);
            int numPlanes = Integer.parseInt(tokens[0]);
            int longitudParametros = 5;
            int posicion;
            for(int i = 0; i < numPlanes; i++){
                posicion = i*longitudParametros;
                System.out.println("--PLAN RECIBIDO:\n\t"+tokens[1+posicion]);
                String idPlan = tokens[1+posicion];
                System.out.println("\t"+tokens[2+posicion]);
                String nombrePlan = tokens[2+posicion];
                int vehiculos = Integer.parseInt(tokens[3+posicion]);
                int voluntarios = Integer.parseInt(tokens[4+posicion]);
                String actuaciones = tokens[5+posicion];
                
                PlanProteccion plan = new PlanProteccion(idPlan, nombrePlan, 
                        vehiculos, voluntarios, actuaciones);
                planes.add(plan);  
            }
        socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return planes;
    }
    
    public synchronized boolean addPlan(PlanProteccion plan){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
             System.out.println("comm add plan: " + plan.toString());
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.ADD_PLAN); 
            mensajeTX.ponerParametros(plan.toString());
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public synchronized boolean modPlan(PlanProteccion plan){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
            System.out.println("comms modPlan: "+plan.toString());
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.MOD_PLAN); 
            mensajeTX.ponerParametros(plan.toString());
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public synchronized boolean eliminarPlan(PlanProteccion plan){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            System.out.println("PLAN COMMS Eliminar: " + plan.getId());
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.ELIMINAR_PLAN); 
            mensajeTX.ponerParametros(plan.getId());
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    //------------------------
    //------Emergencias-------
    //------------------------
    public synchronized List<Emergencia>  solicitarEmergencias () {
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
 
            emergencias = new ArrayList<Emergencia>();
            emergencias.clear();
            
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.LISTAR_EMERGENCIAS); 
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String parametros = mensajeRX.verParametros();
            String delims = ",";
            String[] tokens = parametros.split(delims);
            int numPlanes = Integer.parseInt(tokens[0]);
            int longitudParametros = 8;
            int posicion;
            for(int i = 0; i < numPlanes; i++){
                posicion = i*longitudParametros;
                System.out.println("--EMERGENCIA RECIBIDA:\n\tid: "+tokens[1+posicion] 
                        + "\n\tidPlan: " + tokens[2+posicion]
                        + "\n\tnombrePlan: " + tokens[3+posicion]
                        + "\n\tvehiculosPlan: " + tokens[4+posicion]
                        + "\n\tvoluntariosPlan: " + tokens[5+posicion]
                        + "\n\tactuacionesPlan: " + tokens[6+posicion]
                        + "\n\ttipo: " + tokens[7+posicion]
                        + "\n\tnivel: " + tokens[8+posicion]);
                        
                String idEmergencia = tokens[1+posicion];
                        
                String idPlan = tokens[2+posicion];
                String nombrePlan = tokens[3+posicion];
                int vehiculos = Integer.parseInt(tokens[4+posicion]);
                int voluntarios = Integer.parseInt(tokens[5+posicion]);
                String actuaciones = tokens[6+posicion];

                PlanProteccion plan = new PlanProteccion(idPlan, nombrePlan, 
                                    vehiculos, voluntarios, actuaciones);
                            
                String tipo = tokens[7];
                int nivel = Integer.parseInt(tokens[8]);
                Emergencia emergencia = new Emergencia(idEmergencia, plan, tipo, nivel);
                emergencias.add(emergencia);
            }
        socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return emergencias;
    }
    
    public synchronized boolean addEmergencia(Emergencia emergencia){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
             System.out.println("comm add emer: " + emergencia.toString());
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.ADD_EMERGENCIA); 
            mensajeTX.ponerParametros(emergencia.toString());
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public synchronized boolean modEmergencia(Emergencia emergencia){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
            System.out.println("comms modEmergencia: "+emergencia.toString());
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.MOD_EMERGENCIA); 
            mensajeTX.ponerParametros(emergencia.toString());
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public synchronized boolean eliminarEmergencia(Emergencia emergencia){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            System.out.println("PLAN COMMS Eliminar emer: " + emergencia.getId());
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.ELIMINAR_EMERGENCIA); 
            mensajeTX.ponerParametros(emergencia.getId());
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public synchronized boolean addAlerta(Alerta alerta){
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
            //System.out.println("comm add emer: " + emergencia.toString());
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.ADD_ALERTA); 
            mensajeTX.ponerParametros(alerta.toString());
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            socket.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    //------------------------
    //----Zonas Seguridad-----
    //------------------------
    public synchronized List<ZonaSeguridad>  solicitarZonas () {
        try {
            socket = new Socket(ip, puerto);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
 
            zonas = new ArrayList<ZonaSeguridad>();
            zonas.clear();
            
            Mensaje mensajeTX = new Mensaje();
            mensajeTX.ponerOperacion(Operacion.LISTAR_ZONAS); 
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String parametros = mensajeRX.verParametros();
            String delims = ",";
            String[] tokens = parametros.split(delims);
            int numPlanes = Integer.parseInt(tokens[0]);
            int longitudParametros = 8;
            int posicion;
            for(int i = 0; i < numPlanes; i++){
                posicion = i*longitudParametros;
                float coordX = Float.parseFloat(tokens[1+posicion]);
                float coordY = Float.parseFloat(tokens[2+posicion]);
                Coordenada coordenada = new Coordenada(coordX, coordY);
                int id = Integer.parseInt(tokens[3+posicion]);
                
                ZonaSeguridad zona = new ZonaSeguridad(coordenada, id, null, null);
                
                zonas.add(zona);
            }
        socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return zonas;
    }
}
