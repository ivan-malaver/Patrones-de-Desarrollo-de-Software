package com.ues.comportamiento.observer;

/**
 * INTERFAZ OBSERVABLE (SUBJECT)
 */
public interface ObservableProyecto {
    void agregarObservador(ObservadorProyecto observador);
    void eliminarObservador(ObservadorProyecto observador);
    void notificarObservadores(EventoProyecto evento);
}