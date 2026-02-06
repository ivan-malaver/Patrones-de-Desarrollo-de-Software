package com.ues.comportamiento.observer;

/**
 * OBSERVADOR CONCRETO - Notificador por Email
 */
public class NotificadorEmail implements ObservadorProyecto {
    
    @Override
    public void actualizar(EventoProyecto evento) {
        switch (evento.getTipo()) {
            case PROYECTO_CERRADO:
                System.out.println("📧 EMAIL URGENTE: " + evento.getMensaje());
                System.out.println("   Proyecto: " + evento.getProyecto().getNombre());
                break;
                
            case EVALUACION_AGREGADA:
                System.out.println("📧 EMAIL INFORMATIVO: " + evento.getMensaje());
                break;
        }
    }
}