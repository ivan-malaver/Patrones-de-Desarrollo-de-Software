package com.ues.creacional.singleton;

import com.ues.model.Evaluacion;
import com.ues.model.Proyecto;
import java.util.HashMap;
import java.util.Map;

/**
 * PATRÓN SINGLETON
 * Propósito: Garantizar una única instancia global.
 */
public class SistemaEvaluacionSingleton {
    private static volatile SistemaEvaluacionSingleton instancia;
    
    private Map<String, Double> promediosProyectos;
    private int totalEvaluaciones;
    
    private SistemaEvaluacionSingleton() {
        System.out.println("⚙️  Inicializando Sistema de Evaluaciones (Singleton)...");
        promediosProyectos = new HashMap<>();
        totalEvaluaciones = 0;
    }
    
    public static SistemaEvaluacionSingleton getInstancia() {
        if (instancia == null) {
            synchronized (SistemaEvaluacionSingleton.class) {
                if (instancia == null) {
                    instancia = new SistemaEvaluacionSingleton();
                }
            }
        }
        return instancia;
    }
    
    public void registrarEvaluacion(Evaluacion evaluacion) {
        totalEvaluaciones++;
        String proyectoId = evaluacion.getProyecto().getId();
        
        System.out.println("📊 Singleton registró evaluación: " + 
                          proyectoId + " - " + evaluacion.getCalificacion());
    }
    
    public void mostrarEstadisticas() {
        System.out.println("\n📈 ESTADÍSTICAS (Singleton):");
        System.out.println("Total evaluaciones: " + totalEvaluaciones);
        System.out.println("Proyectos monitoreados: " + promediosProyectos.size());
    }
}