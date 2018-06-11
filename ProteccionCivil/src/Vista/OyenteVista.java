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
                        MENU_PLANES, MENU_EMERGENCIAS, ADD_EMER, MENU_ZONAS, LISTAR_PLANES,
                        
                        
       //ALEJANDRO
       VER_PANEL_ALBERGUES, 
       INSERTAR_ALBERGUE,
       ELIMINAR_ALBERGUE,
       MODIFICAR_ALBERGUE,
       
       VER_PANEL_ALMACENES,
       INSERTAR_ALMACEN,
       ELIMINAR_ALMACEN,
       MODIFICAR_ALMACEN,
       
       VER_PANEL_VOLUNTARIOS,
       INSERTAR_VOLUNTARIO,
       ELIMINAR_VOLUNTARIO,
       MODIFICAR_VOLUNTARIO,
       
       VER_PANEL_VEHICULOS,
       INSERTAR_VEHICULO,
       ELIMINAR_VEHICULO,
       MODIFICAR_VEHICULO,
       
       VER_PANEL_MAPA_RECURSOS,
       RECARGAR_MAPA_RECURSOS   
   }
  
   /**
    *  Llamado para notificar un evento de la interfaz de usuario
    * 
    */ 
   public void notificacion(Evento evento, Object obj);
}
 