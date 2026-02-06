package com.ues.estructural.decorator;

import com.ues.model.Proyecto;

/**
 * DECORADOR BASE
 * Propósito: Añadir funcionalidad sin modificar la clase base.
 */
public abstract class ProyectoDecorator extends Proyecto {
    protected Proyecto proyectoDecorado;
    
    public ProyectoDecorator(Proyecto proyectoDecorado) {
        super(proyectoDecorado.getId(), 
              proyectoDecorado.getNombre(), 
              proyectoDecorado.getDescripcion(), 
              proyectoDecorado.getProfesor());
        this.proyectoDecorado = proyectoDecorado;
    }
    
    @Override
    public void agregarEvaluacion(com.ues.model.Evaluacion evaluacion) {
        proyectoDecorado.agregarEvaluacion(evaluacion);
    }
    
    @Override
    public boolean isActivo() {
        return proyectoDecorado.isActivo();
    }
}