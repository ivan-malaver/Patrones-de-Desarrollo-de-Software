package com.ues.creacional.factory;

import com.ues.model.Profesor;

/**
 * Fábrica concreta para la creación de objetos Profesor.
 * Implementa la interfaz EntityFactory para el tipo Profesor.
 */
public class ProfesorFactory implements EntityFactory<Profesor> {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String especialidad;

    /**
     * Constructor con parámetros para la fábrica
     * @param id Identificador único del profesor
     * @param nombre Nombre del profesor
     * @param apellido Apellido del profesor
     * @param email Correo electrónico del profesor
     * @param especialidad Especialidad del profesor
     */
    public ProfesorFactory(int id, String nombre, String apellido, String email, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.especialidad = especialidad;
    }

    /**
     * Método para crear una instancia de Profesor con los parámetros definidos
     * @return Nueva instancia de Profesor
     */
    @Override
    public Profesor createEntity() {
        return new Profesor(id, nombre, apellido, email, especialidad);
    }
}