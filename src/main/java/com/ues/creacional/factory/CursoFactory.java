package com.ues.creacional.factory;

import com.ues.model.Curso;

/**
 * Fábrica concreta para la creación de objetos Curso.
 * Implementa la interfaz EntityFactory para el tipo Curso.
 */
public class CursoFactory implements EntityFactory<Curso> {
    private int id;
    private String nombre;
    private String codigo;
    private String descripcion;

    /**
     * Constructor con parámetros para la fábrica
     * @param id Identificador único del curso
     * @param nombre Nombre del curso
     * @param codigo Código del curso
     * @param descripcion Descripción del curso
     */
    public CursoFactory(int id, String nombre, String codigo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    /**
     * Método para crear una instancia de Curso con los parámetros definidos
     * @return Nueva instancia de Curso
     */
    @Override
    public Curso createEntity() {
        return new Curso(id, nombre, codigo, descripcion);
    }
}