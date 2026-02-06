package com.ues.model;

import java.util.List;

/**
 * Clase que representa un curso en el sistema universitario.
 * Contiene información sobre el curso y sus participantes.
 */
public class Curso {
    private int id;
    private String nombre;
    private String codigo;
    private String descripcion;
    private Profesor profesor;
    private List<Estudiante> estudiantesInscritos;

    /**
     * Constructor por defecto
     */
    public Curso() {
    }

    /**
     * Constructor con parámetros básicos
     * @param id Identificador único del curso
     * @param nombre Nombre del curso
     * @param codigo Código del curso
     * @param descripcion Descripción del curso
     */
    public Curso(int id, String nombre, String codigo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    /**
     * Método para asignar un profesor al curso
     * @param profesor Profesor que impartirá el curso
     */
    public void asignarProfesor(Profesor profesor) {
        this.profesor = profesor;
        profesor.asignarCurso(this);
        System.out.println("Profesor " + profesor.getNombre() + " asignado al curso: " + nombre);
    }

    /**
     * Método para inscribir un estudiante en el curso
     * @param estudiante Estudiante que se inscribirá en el curso
     */
    public void inscribirEstudiante(Estudiante estudiante) {
        if (estudiantesInscritos != null && !estudiantesInscritos.contains(estudiante)) {
            estudiantesInscritos.add(estudiante);
            estudiante.inscribirCurso(this);
            System.out.println("Estudiante " + estudiante.getNombre() + " inscrito en el curso: " + nombre);
        }
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Estudiante> getEstudiantesInscritos() {
        return estudiantesInscritos;
    }

    public void setEstudiantesInscritos(List<Estudiante> estudiantesInscritos) {
        this.estudiantesInscritos = estudiantesInscritos;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}