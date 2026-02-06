package com.ues.estructural.decorator;

import com.ues.model.Proyecto;
import java.util.Date;

/**
 * DECORADOR CONCRETO - Seguimiento Ambiental
 */
public class SeguimientoAmbientalDecorator extends ProyectoDecorator {
    private String reporteAmbiental;
    
    public SeguimientoAmbientalDecorator(Proyecto proyecto) {
        super(proyecto);
        this.reporteAmbiental = "Pendiente";
    }
    
    public void generarReporteAmbiental() {
        this.reporteAmbiental = "Reporte generado el " + new Date();
        System.out.println("🌿 Reporte ambiental generado para: " + 
                          proyectoDecorado.getNombre());
    }
    
    @Override
    public void agregarEvaluacion(com.ues.model.Evaluacion evaluacion) {
        System.out.println("📝 Agregando evaluación con auditoría ambiental...");
        super.agregarEvaluacion(evaluacion);
    }
}