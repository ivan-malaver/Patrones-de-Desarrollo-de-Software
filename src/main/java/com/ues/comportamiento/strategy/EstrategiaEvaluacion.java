package com.ues.comportamiento.strategy;

import com.ues.model.Proyecto;

/**
 * INTERFAZ STRATEGY
 */
public interface EstrategiaEvaluacion {
    boolean evaluarProyecto(Proyecto proyecto);
}