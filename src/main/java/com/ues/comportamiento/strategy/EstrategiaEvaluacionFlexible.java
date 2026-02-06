package com.ues.comportamiento.strategy;

import com.ues.model.Proyecto;

/**
 * STRATEGY CONCRETA - Evaluación Flexible
 */
public class EstrategiaEvaluacionFlexible implements EstrategiaEvaluacion {
    
    @Override
    public boolean evaluarProyecto(Proyecto proyecto) {
        // Regla flexible: 60% o más de evaluaciones <70
        long evaluacionesBajas = proyecto.getEvaluaciones().stream()
            .filter(e -> e.getCalificacion() < 70)
            .count();
        
        double porcentaje = (double) evaluacionesBajas / proyecto.getEvaluaciones().size();
        return porcentaje >= 0.6; // Más flexible
    }
}