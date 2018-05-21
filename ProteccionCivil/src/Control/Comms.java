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
    private static final int puerto = 5500;
    private static final String ipServidor = "localhost";
    
    private static final String ACTIVAR_PLAN = "ACTIVARPLAN";
    private static final String ALERTAS_MAPA = "ALERTASMAPA";
    private static final String HISTORIAL_ALERTAS = "HISTORIALALERTAS";

    
    List<Alerta> alertas;
    
    public Comms(int puerto){
    }


    /**
     * Solicita las alertas no gestionadas a la BD del servidor
     */
    public synchronized List<Alerta>  solicitarMapaAlertasNoGestionadas (){
        try {
            // TBD
            socket = new Socket(ipServidor, puerto);
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(new BufferedWriter(
                 new OutputStreamWriter(socket.getOutputStream())), true);

            alertas = new ArrayList<Alerta>();
            salida.println(ALERTAS_MAPA);
            alertas.clear();
            try {
                int cantidadAlertas = Integer.parseInt(entrada.readLine());
                for(int i = 0;i < cantidadAlertas; i++){
                    int id = Integer.parseInt(entrada.readLine());

                    String tipoEmergencia = entrada.readLine();
                    int nivelEmergencia = 
                            Integer.parseInt(entrada.readLine());
                    int x = Integer.parseInt(entrada.readLine());
                    int y = Integer.parseInt(entrada.readLine());
                    Coordenada coordenadas = new Coordenada(x, y);
                    int afectados = Integer.parseInt(entrada.readLine());
                    boolean activada = Boolean.parseBoolean(entrada.readLine());
                    int dia = Integer.parseInt(entrada.readLine());
                    int mes = Integer.parseInt(entrada.readLine());
                    int anio = Integer.parseInt(entrada.readLine());
                    Date fecha = new Date(anio, mes, dia);
                    Emergencia emergencia = new Emergencia(tipoEmergencia, nivelEmergencia);
                    Alerta alerta = new Alerta(coordenadas, emergencia, id, false, fecha,
                        activada, afectados);
                    alertas.add(alerta);
            }
            } catch (IOException ex) {
                Logger.getLogger(Comms.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Comms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alertas;
    }
    
    /**
     * Solicita activar un plan de proteccion para una alerta al servidor
     */
    public synchronized boolean solicitarActivarPlanDeProteccion(String id){
        try {
            
            socket = new Socket(ipServidor, puerto);
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            salida.println(ACTIVAR_PLAN);
            
            Boolean respuesta = Boolean.parseBoolean(entrada.readLine());
            return respuesta;
            
    } catch (IOException ex) {
        Logger.getLogger(Comms.class.getName()).log(Level.SEVERE, null, ex);
    }
        return false;
    }
    
    /**
     * Solicitar Historial de alertas a la BD del servidor
     */
    public synchronized List<Alerta> solicitarHistorialDeAlertas(){
        try {
            socket = new Socket(ipServidor, puerto);
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            alertas = new ArrayList<Alerta>();
            salida.println(HISTORIAL_ALERTAS);
            alertas.clear();
            
            try {
                int cantidadAlertas = Integer.parseInt(entrada.readLine());
                for(int i = 0;i < cantidadAlertas; i++){
                    int id = Integer.parseInt(entrada.readLine());
                    
                    String tipoEmergencia = entrada.readLine();
                    int nivelEmergencia =
                            Integer.parseInt(entrada.readLine());
                    int x = Integer.parseInt(entrada.readLine());
                    int y = Integer.parseInt(entrada.readLine());
                    Coordenada coordenadas = new Coordenada(x, y);
                    int afectados = Integer.parseInt(entrada.readLine());
                    boolean activada = Boolean.parseBoolean(entrada.readLine());
                    int dia = Integer.parseInt(entrada.readLine());
                    int mes = Integer.parseInt(entrada.readLine());
                    int anio = Integer.parseInt(entrada.readLine());
                    Date fecha = new Date(anio, mes, dia);
                    Emergencia emergencia = new Emergencia(tipoEmergencia, nivelEmergencia);
                    Alerta alerta = new Alerta(coordenadas, emergencia, id, false, fecha,
                            activada, afectados);
                    alertas.add(alerta);
                }
            } catch (IOException ex) {
                Logger.getLogger(Comms.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Comms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alertas;
    }
}
