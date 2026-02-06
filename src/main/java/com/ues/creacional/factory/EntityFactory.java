package com.ues.creacional.factory;

/**
 * Interfaz genérica para la creación de entidades.
 * Implementa el patrón Factory para centralizar la creación de objetos.
 */
public interface EntityFactory<T> {
    /**
     * Método para crear una entidad
     * @return Nueva instancia de la entidad
     */
    T createEntity();
}