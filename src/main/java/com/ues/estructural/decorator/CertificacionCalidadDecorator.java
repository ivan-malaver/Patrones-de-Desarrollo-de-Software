package com.ues.estructural.decorator;

import com.ues.model.Proyecto;

/**
 * DECORADOR CONCRETO - Certificación de Calidad
 */
public class CertificacionCalidadDecorator extends ProyectoDecorator {
    private boolean certificado;
    
    public CertificacionCalidadDecorator(Proyecto proyecto) {
        super(proyecto);
        this.certificado = false;
    }
    
    public void otorgarCertificado() {
        if (proyectoDecorado.isActivo()) {
            this.certificado = true;
            System.out.println("🏅 Certificado otorgado a: " + 
                              proyectoDecorado.getNombre());
        } else {
            System.out.println("❌ No se puede certificar un proyecto cerrado");
        }
    }
    
    public boolean isCertificado() {
        return certificado;
    }
}