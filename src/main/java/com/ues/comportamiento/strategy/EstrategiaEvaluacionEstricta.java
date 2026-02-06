package com.ues.comportamiento.strategy;

import com.ues.model.Proyecto;

/**
 * STRATEGY CONCRETA - Evaluación Estricta
 */
public class EstrategiaEvaluacionEstricta implements EstrategiaEvaluacion {
    
    @Override
    public boolean evaluarProyecto(Proyecto proyecto) {
        // Regla estricta: 40% o más de evaluaciones <70
        long evaluacionesBajas = proyecto.getEvaluaciones().stream()
            .filter(e -> e.getCalificacion() < 70)
            .count();
        
        double porcentaje = (double) evaluacionesBajas / proyecto.getEvaluaciones().size();
        return porcentaje >= 0.4; // Más estricto que el requerido
    }
}