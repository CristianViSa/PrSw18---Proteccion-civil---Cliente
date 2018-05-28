package Control;

import Modelo.Alerta;
import Modelo.Coordenada;
import Modelo.Emergencia;
import Modelo.Mensaje;
import Modelo.Operacion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristian
 */
public class Comms {
    private Socket socket;
    private int puerto;
    private String ip;
    private static final String ipServidor = "localhost";
    
    private static final String ACTIVAR_PLAN = "ACTIVARPLAN";
    private static final String ALERTAS_MAPA = "ALERTASMAPA";
    private static final String HISTORIAL_ALERTAS = "HISTORIALALERTAS";

    ObjectOutputStream salida;
    ObjectInputStream entrada;
    
    List<Alerta> alertas;
    
    public Comms(int puerto){
        socket = null;
        this.puerto = puerto;
        ip = "localhost";
    }


    /**
     * Solicita las alertas no gestionadas a la BD del servidor
     * @author Cristian
     */
    public synchronized List<Alerta>  solicitarMapaAlertasNoGestionadas () {
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
            int longitudParametros = 10;
            int posicion;
            for(int i = 0; i < numAlertas; i++){
                posicion = i*longitudParametros;
                int id = Integer.parseInt(tokens[1+posicion]);

                String tipoEmergencia = tokens[2+posicion];
                int nivelEmergencia = 
                        Integer.parseInt(tokens[3+posicion]);
                int x = Integer.parseInt(tokens[4+posicion]);
                int y = Integer.parseInt(tokens[5+posicion]);
                Coordenada coordenadas = new Coordenada(x, y);
                int afectados = Integer.parseInt(tokens[6+posicion]);
                boolean activada = Boolean.parseBoolean(tokens[7+posicion]);
                int dia = Integer.parseInt(tokens[8+posicion]);
                int mes = Integer.parseInt(tokens[9+posicion]);
                int anio = Integer.parseInt(tokens[10+posicion]);
                Date fecha = new Date(anio, mes, dia);
                Emergencia emergencia = new Emergencia(tipoEmergencia, nivelEmergencia);
                Alerta alerta = new Alerta(coordenadas, emergencia, id, false, fecha,
                    activada, afectados);
                alertas.add(alerta);    
            }
        socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
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
            salida.writeObject(mensajeTX);
            
            Mensaje mensajeRX = (Mensaje)entrada.readObject();
            
            String parametros = mensajeRX.verParametros();
            String delims = ",";
            String[] tokens = parametros.split(delims);
            if("true".equals(tokens[0])){
                socket.close();
                return true;
            }  
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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
            int longitudParametros = 10;
            int posicion;
            for(int i = 0; i < numAlertas; i++){
                posicion = i*longitudParametros;
                int id = Integer.parseInt(tokens[1+posicion]);

                String tipoEmergencia = tokens[2+posicion];
                int nivelEmergencia = 
                        Integer.parseInt(tokens[3+posicion]);
                int x = Integer.parseInt(tokens[4+posicion]);
                int y = Integer.parseInt(tokens[5+posicion]);
                Coordenada coordenadas = new Coordenada(x, y);
                int afectados = Integer.parseInt(tokens[6+posicion]);
                boolean activada = Boolean.parseBoolean(tokens[7+posicion]);
                int dia = Integer.parseInt(tokens[8+posicion]);
                int mes = Integer.parseInt(tokens[9+posicion]);
                int anio = Integer.parseInt(tokens[10+posicion]);
                Date fecha = new Date(anio, mes, dia);
                Emergencia emergencia = new Emergencia(tipoEmergencia, nivelEmergencia);
                Alerta alerta = new Alerta(coordenadas, emergencia, id, false, fecha,
                    activada, afectados);
                alertas.add(alerta);    
            }
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return alertas;
    }
}
