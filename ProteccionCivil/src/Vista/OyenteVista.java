package Vista;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  Interfaz de oyente para recibir eventos de la interfaz de usuario
 * 
 */
public interface OyenteVista {
   public enum Evento { SALIR, HISTORIAL, SELECCIONAR_ALERTA, MENU_ITEM_ALERTAS,
                        ACTIVAR_PLAN, ADD_PLAN, MOD_PLAN, ELIMINAR_PLAN, GET_ID_PLAN,
                        GET_ID_ALERTA, GET_PLAN_ID, GET_PLAN_NOMBRE, GET_EMERGENCIA}
  
   /**
    *  Llamado para notificar un evento de la interfaz de usuario
    * 
    */ 
   public void notificacion(Evento evento, Object obj);
}
 