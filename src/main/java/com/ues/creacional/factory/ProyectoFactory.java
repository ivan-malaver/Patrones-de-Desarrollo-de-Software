package com.ues.creacional.factory;

import com.ues.model.Proyecto;
import com.ues.model.Profesor;

/**
 * Factory concreta para proyectos.
 */
public class ProyectoFactory implements EntityFactory<Proyecto> {
    
    @Override
    public Proyecto crear(Object... params) {
        String id = (String) params[0];
        String nombre = (String) params[1];
        String descripcion = (String) params[2];
        Profesor profesor = (Profesor) params[3];
        
        System.out.println("🏭 Factory creando proyecto: " + nombre);
        return new Proyecto(id, nombre, descripcion, profesor);
    }
}