package com.ues.creacional.factory;

import com.ues.model.Estudiante;

/**
 * Fábrica concreta para la creación de objetos Estudiante.
 * Implementa la interfaz EntityFactory para el tipo Estudiante.
 */
public class EstudianteFactory implements EntityFactory<Estudiante> {
    private int id;
    private String nombre;
    private String apellido;
    private String email;

    /**
     * Constructor con parámetros para la fábrica
     * @param id Identificador único del estudiante
     * @param nombre Nombre del estudiante
     * @param apellido Apellido del estudiante
     * @param email Correo electrónico del estudiante
     */
    public EstudianteFactory(int id, String nombre, String apellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    /**
     * Método para crear una instancia de Estudiante con los parámetros definidos
     * @return Nueva instancia de Estudiante
     */
    @Override
    public Estudiante createEntity() {
        return new Estudiante(id, nombre, apellido, email);
    }
}