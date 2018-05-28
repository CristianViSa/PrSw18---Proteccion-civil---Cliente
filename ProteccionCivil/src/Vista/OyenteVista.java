package Vista;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @author Cristian
 */

/**
 *  Interfaz de oyente para recibir eventos de la interfaz de usuario
 * 
 */
public interface OyenteVista {
   public enum Evento { SALIR, HISTORIAL_ALERTAS, SELECCIONAR_ALERTA, MENU_ITEM_ALERTAS,
                        ACTIVAR_PLAN, ADD_PLAN, MOD_PLAN, ELIMINAR_PLAN, GET_ID_PLAN,
                        GET_ID_ALERTA, GET_PLAN_ID, GET_PLAN_NOMBRE, GET_EMERGENCIA,
                        MENU_PLANES, MENU_EMERGENCIAS, ADD_EMER, MENU_ZONAS}
  
   /**
    *  Llamado para notificar un evento de la interfaz de usuario
    * 
    */ 
   public void notificacion(Evento evento, Object obj);
}
 