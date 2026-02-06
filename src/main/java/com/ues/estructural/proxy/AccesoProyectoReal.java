package com.ues.estructural.proxy;

import com.ues.model.Proyecto;

/**
 * IMPLEMENTACIÓN REAL
 */
public class AccesoProyectoReal implements AccesoProyecto {
    
    @Override
    public void accederProyecto(String usuario, Proyecto proyecto) {
        System.out.println("🔓 " + usuario + " accedió al proyecto: " + 
                          proyecto.getNombre());
    }
    
    @Override
    public String verInformacionBasica(Proyecto proyecto) {
        return "Proyecto: " + proyecto.getNombre() + 
               " | Estado: " + (proyecto.isActivo() ? "ACTIVO" : "CERRADO");
    }
}