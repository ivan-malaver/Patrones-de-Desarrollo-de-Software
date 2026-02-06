package com.ues.comportamiento.observer;

import com.ues.model.Proyecto;
import com.ues.model.Evaluacion;
import java.util.ArrayList;
import java.util.List;

/**
 * OBSERVABLE CONCRETO
 */
public class ProyectoObservable extends Proyecto implements ObservableProyecto {
    private List<ObservadorProyecto> observadores;
    
    public ProyectoObservable(String id, String nombre, String descripcion, 
                             com.ues.model.Profesor profesor) {
        super(id, nombre, descripcion, profesor);
        this.observadores = new ArrayList<>();
    }
    
    @Override
    public void agregarObservador(ObservadorProyecto observador) {
        observadores.add(observador);
    }
    
    @Override
    public void eliminarObservador(ObservadorProyecto observador) {
        observadores.remove(observador);
    }
    
    @Override
    public void notificarObservadores(EventoProyecto evento) {
        for (ObservadorProyecto observador : observadores) {
            observador.actualizar(evento);
        }
    }
    
    @Override
    public void agregarEvaluacion(Evaluacion evaluacion) {
        super.agregarEvaluacion(evaluacion);
        
        // Notificar sobre nueva evaluación
        EventoProyecto evento = new EventoProyecto(
            EventoProyecto.TipoEvento.EVALUACION_AGREGADA,
            this,
            "Nueva evaluación: " + evaluacion.getCalificacion()
        );
        notificarObservadores(evento);
        
        // Notificar si el proyecto se cierra
        if (!this.isActivo()) {
            EventoProyecto eventoCierre = new EventoProyecto(
                EventoProyecto.TipoEvento.PROYECTO_CERRADO,
                this,
                "Proyecto cerrado por bajas evaluaciones"
            );
            notificarObservadores(eventoCierre);
        }
    }
}