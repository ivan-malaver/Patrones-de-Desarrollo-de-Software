package com.ues.model;

import java.util.Date;
import java.util.List;

/**
 * Clase que representa un estudiante en el sistema universitario.
 * Contiene información personal y académica del estudiante.
 */
public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaNacimiento;
    private Pais pais;
    private List<Curso> cursosInscritos;
    private List<Proyecto> proyectosParticipantes;

    /**
     * Constructor por defecto
     */
    public Estudiante() {
    }

    /**
     * Constructor con parámetros básicos
     * @param id Identificador único del estudiante
     * @param nombre Nombre del estudiante
     * @param apellido Apellido del estudiante
     * @param email Correo electrónico del estudiante
     */
    public Estudiante(int id, String nombre, String apellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    /**
     * Método para inscribir al estudiante en un curso
     * @param curso Curso en el que se inscribirá el estudiante
     */
    public void inscribirCurso(Curso curso) {
        if (cursosInscritos != null && !cursosInscritos.contains(curso)) {
            cursosInscritos.add(curso);
            System.out.println("Estudiante " + nombre + " inscrito en el curso: " + curso.getNombre());
        }
    }

    /**
     * Método para asignar al estudiante a un proyecto
     * @param proyecto Proyecto al que se asignará el estudiante
     */
    public void asignarProyecto(Proyecto proyecto) {
        if (proyectosParticipantes != null && !proyectosParticipantes.contains(proyecto)) {
            proyectosParticipantes.add(proyecto);
            System.out.println("Estudiante " + nombre + " asignado al proyecto: " + proyecto.getNombre());
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Curso> getCursosInscritos() {
        return cursosInscritos;
    }

    public void setCursosInscritos(List<Curso> cursosInscritos) {
        this.cursosInscritos = cursosInscritos;
    }

    public List<Proyecto> getProyectosParticipantes() {
        return proyectosParticipantes;
    }

    public void setProyectosParticipantes(List<Proyecto> proyectosParticipantes) {
        this.proyectosParticipantes = proyectosParticipantes;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}