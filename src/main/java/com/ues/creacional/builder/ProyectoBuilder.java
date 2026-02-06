package com.ues.creacional.builder;

import com.ues.model.Proyecto;
import com.ues.model.Profesor;
import java.util.ArrayList;
import java.util.List;

/**
 * PATRÓN BUILDER
 * Propósito: Construir objetos complejos paso a paso.
 */
public class ProyectoBuilder {
    private String id;
    private String nombre;
    private String descripcion;
    private Profesor profesor;
    private List<String> objetivos = new ArrayList<>();
    private double presupuesto;
    private int duracionMeses;
    
    public ProyectoBuilder setId(String id) {
        this.id = id;
        return this;
    }
    
    public ProyectoBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    
    public ProyectoBuilder setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }
    
    public ProyectoBuilder setProfesor(Profesor profesor) {
        this.profesor = profesor;
        return this;
    }
    
    public ProyectoBuilder agregarObjetivo(String objetivo) {
        this.objetivos.add(objetivo);
        return this;
    }
    
    public ProyectoBuilder setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
        return this;
    }
    
    public ProyectoBuilder setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
        return this;
    }
    
    public Proyecto build() {
        System.out.println("🛠️  Builder construyendo proyecto...");
        
        if (id == null || nombre == null || profesor == null) {
            throw new IllegalStateException("Faltan parámetros requeridos");
        }
        
        Proyecto proyecto = new Proyecto(id, nombre, descripcion, profesor);
        
        System.out.println("   • ID: " + id);
        System.out.println("   • Nombre: " + nombre);
        System.out.println("   • Profesor: " + profesor.getNombre());
        System.out.println("   • Objetivos: " + objetivos.size());
        
        return proyecto;
    }
}