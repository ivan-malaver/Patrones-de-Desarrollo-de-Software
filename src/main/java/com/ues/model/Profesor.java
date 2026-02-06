package com.ues.model;

import java.util.List;

/**
 * Clase que representa un profesor en el sistema universitario.
 * Contiene información personal y profesional del profesor.
 */
public class Profesor {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String especialidad;
    private List<Curso> cursosImpartidos;
    private List<Proyecto> proyectosSupervisados;

    /**
     * Constructor por defecto
     */
    public Profesor() {
    }

    /**
     * Constructor con parámetros básicos
     * @param id Identificador único del profesor
     * @param nombre Nombre del profesor
     * @param apellido Apellido del profesor
     * @param email Correo electrónico del profesor
     * @param especialidad Especialidad del profesor
     */
    public Profesor(int id, String nombre, String apellido, String email, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.especialidad = especialidad;
    }

    /**
     * Método para asignar un curso al profesor
     * @param curso Curso que será impartido por el profesor
     */
    public void asignarCurso(Curso curso) {
        if (cursosImpartidos != null && !cursosImpartidos.contains(curso)) {
            cursosImpartidos.add(curso);
            System.out.println("Profesor " + nombre + " asignado al curso: " + curso.getNombre());
        }
    }

    /**
     * Método para asignar un proyecto al profesor como supervisor
     * @param proyecto Proyecto que será supervisado por el profesor
     */
    public void supervisarProyecto(Proyecto proyecto) {
        if (proyectosSupervisados != null && !proyectosSupervisados.contains(proyecto)) {
            proyectosSupervisados.add(proyecto);
            System.out.println("Profesor " + nombre + " supervisando el proyecto: " + proyecto.getNombre());
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Curso> getCursosImpartidos() {
        return cursosImpartidos;
    }

    public void setCursosImpartidos(List<Curso> cursosImpartidos) {
        this.cursosImpartidos = cursosImpartidos;
    }

    public List<Proyecto> getProyectosSupervisados() {
        return proyectosSupervisados;
    }

    public void setProyectosSupervisados(List<Proyecto> proyectosSupervisados) {
        this.proyectosSupervisados = proyectosSupervisados;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}