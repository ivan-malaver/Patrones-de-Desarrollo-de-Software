package com.ues.model;

/**
 * Clase que representa un país.
 * Esta clase es una entidad básica del modelo de dominio.
 */
public class Pais {
    private int id;
    private String nombre;
    private String codigoISO;

    /**
     * Constructor por defecto
     */
    public Pais() {
    }

    /**
     * Constructor con parámetros
     * @param id Identificador único del país
     * @param nombre Nombre del país
     * @param codigoISO Código ISO del país
     */
    public Pais(int id, String nombre, String codigoISO) {
        this.id = id;
        this.nombre = nombre;
        this.codigoISO = codigoISO;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoISO() {
        return codigoISO;
    }

    public void setCodigoISO(String codigoISO) {
        this.codigoISO = codigoISO;
    }

    /**
     * Método toString para representar el objeto como cadena
     * @return Representación en texto del objeto Pais
     */
    @Override
    public String toString() {
        return "Pais{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", codigoISO='" + codigoISO + '\'' +
                '}';
    }
}