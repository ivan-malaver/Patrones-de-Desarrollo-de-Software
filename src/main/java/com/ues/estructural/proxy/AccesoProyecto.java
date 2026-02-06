package com.ues.estructural.proxy;

import com.ues.model.Proyecto;

/**
 * INTERFAZ COMÚN PARA PROXY
 */
public interface AccesoProyecto {
    void accederProyecto(String usuario, Proyecto proyecto);
    String verInformacionBasica(Proyecto proyecto);
}