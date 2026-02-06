package com.ues.comportamiento.observer;

import com.ues.model.Proyecto;
import java.util.Date;

/**
 * CLASE DE EVENTO
 */
public class EventoProyecto {
    public enum TipoEvento {
        PROYECTO_CREADO,
        PROYECTO_CERRADO,
        EVALUACION_AGREGADA
    }
    
    private TipoEvento tipo;
    private Proyecto proyecto;
    private String mensaje;
    private Date fecha;
    
    public EventoProyecto(TipoEvento tipo, Proyecto proyecto, String mensaje) {
        this.tipo = tipo;
        this.proyecto = proyecto;
        this.mensaje = mensaje;
        this.fecha = new Date();
    }
    
    public TipoEvento getTipo() { return tipo; }
    public Proyecto getProyecto() { return proyecto; }
    public String getMensaje() { return mensaje; }
    public Date getFecha() { return fecha; }
}