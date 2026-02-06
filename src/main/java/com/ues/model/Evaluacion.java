package com.ues.model;

import java.util.Date;

/**
 * Clase que representa una evaluación en el sistema universitario.
 * Puede ser una evaluación de un curso o de un proyecto.
 */
public class Evaluacion {
    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaEvaluacion;
    private double notaMaxima;
    private double notaObtenida;
    private Estudiante estudianteEvaluado;
    private String tipo; // EXAMEN, PROYECTO, TAREA, etc.

    /**
     * Constructor por defecto
     */
    public Evaluacion() {
    }

    /**
     * Constructor con parámetros básicos
     * @param id Identificador único de la evaluación
     * @param nombre Nombre de la evaluación
     * @param descripcion Descripción de la evaluación
     * @param fechaEvaluacion Fecha en que se realizará la evaluación
     * @param notaMaxima Nota máxima posible en la evaluación
     * @param tipo Tipo de evaluación
     */
    public Evaluacion(int id, String nombre, String descripcion, Date fechaEvaluacion, double notaMaxima, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaEvaluacion = fechaEvaluacion;
        this.notaMaxima = notaMaxima;
        this.tipo = tipo;
        this.notaObtenida = -1; // Valor por defecto para indicar que no ha sido calificada aún
    }

    /**
     * Método para calificar la evaluación
     * @param notaObtenida Nota obtenida por el estudiante
     * @return true si la calificación fue exitosa, false si la nota está fuera de rango
     */
    public boolean calificar(double notaObtenida) {
        if (notaObtenida >= 0 && notaObtenida <= notaMaxima) {
            this.notaObtenida = notaObtenida;
            System.out.println("Evaluación '" + nombre + "' calificada con: " + notaObtenida + "/" + notaMaxima);
            return true;
        } else {
            System.out.println("Error: La nota " + notaObtenida + " está fuera del rango permitido (0-" + notaMaxima + ")");
            return false;
        }
    }

    /**
     * Método para verificar si la evaluación ya ha sido calificada
     * @return true si ya fue calificada, false en caso contrario
     */
    public boolean estaCalificada() {
        return notaObtenida >= 0;
    }

    /**
     * Método para obtener el porcentaje de aprobación
     * @return Porcentaje obtenido respecto a la nota máxima
     */
    public double getPorcentaje() {
        if (!estaCalificada()) {
            return -1; // Indica que no ha sido calificada
        }
        return (notaObtenida / notaMaxima) * 100;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(Date fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public double getNotaMaxima() {
        return notaMaxima;
    }

    public void setNotaMaxima(double notaMaxima) {
        this.notaMaxima = notaMaxima;
    }

    public double getNotaObtenida() {
        return notaObtenida;
    }

    public Estudiante getEstudianteEvaluado() {
        return estudianteEvaluado;
    }

    public void setEstudianteEvaluado(Estudiante estudianteEvaluado) {
        this.estudianteEvaluado = estudianteEvaluado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Evaluacion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", notaObtenida=" + (estaCalificada() ? notaObtenida : "No calificada") +
                ", notaMaxima=" + notaMaxima +
                '}';
    }
}