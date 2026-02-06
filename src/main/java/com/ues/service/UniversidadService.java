package com.ues.service;

import com.ues.creacional.factory.*;
import com.ues.creacional.builder.*;
import com.ues.creacional.singleton.*;
import com.ues.estructural.proxy.*;
import com.ues.estructural.decorator.*;
import com.ues.comportamiento.observer.*;
import com.ues.comportamiento.strategy.*;
import com.ues.model.*;
import java.util.*;

/**
 * SERVICIO PRINCIPAL QUE INTEGRA TODOS LOS PATRONES
 */
public class UniversidadService {
    // Factories
    private EstudianteFactory estudianteFactory;
    private ProfesorFactory profesorFactory;
    private ProyectoFactory proyectoFactory;
    
    // Singleton
    private SistemaEvaluacionSingleton sistemaEvaluacion;
    
    // Proxy
    private ProxyAccesoProyecto proxyAcceso;
    
    // Repositorios
    private Map<String, Estudiante> estudiantes;
    private Map<String, Profesor> profesores;
    private Map<String, Curso> cursos;
    private Map<String, Proyecto> proyectos;
    private Map<String, Pais> paises;
    
    public UniversidadService() {
        System.out.println("🎓 INICIALIZANDO SISTEMA UES-ICCIS");
        
        // Inicializar factories
        estudianteFactory = new EstudianteFactory();
        profesorFactory = new ProfesorFactory();
        proyectoFactory = new ProyectoFactory();
        
        // Inicializar Singleton
        sistemaEvaluacion = SistemaEvaluacionSingleton.getInstancia();
        
        // Inicializar Proxy
        proxyAcceso = new ProxyAccesoProyecto();
        
        // Inicializar repositorios
        estudiantes = new HashMap<>();
        profesores = new HashMap<>();
        cursos = new HashMap<>();
        proyectos = new HashMap<>();
        paises = new HashMap<>();
        
        inicializarPaises();
    }
    
    private void inicializarPaises() {
        String[][] datosPaises = {
            {"PA1", "Colombia"}, {"PA2", "Brasil"}, {"PA3", "Perú"},
            {"PA4", "Argentina"}, {"PA5", "Chile"}, {"PA6", "Ecuador"},
            {"PA7", "Venezuela"}, {"PA8", "Bolivia"}, {"PA9", "Paraguay"},
            {"PA10", "Uruguay"}
        };
        
        for (String[] datos : datosPaises) {
            paises.put(datos[0], new Pais(datos[0], datos[1]));
        }
    }
    
    // ========== MÉTODOS CON FACTORY METHOD ==========
    
    public Estudiante crearEstudiante(String id, String nombre, String idPais) {
        Pais pais = paises.get(idPais);
        if (pais == null) {
            throw new IllegalArgumentException("País no encontrado: " + idPais);
        }
        
        Estudiante estudiante = estudianteFactory.crear(id, nombre, pais);
        estudiantes.put(id, estudiante);
        return estudiante;
    }
    
    public Profesor crearProfesor(String id, String nombre) {
        Profesor profesor = profesorFactory.crear(id, nombre);
        profesores.put(id, profesor);
        return profesor;
    }
    
    // ========== MÉTODOS CON BUILDER ==========
    
    public Proyecto crearProyectoComplejo(String id, String nombre, String descripcion, 
                                         String idProfesor) {
        Profesor profesor = profesores.get(idProfesor);
        if (profesor == null) {
            throw new IllegalArgumentException("Profesor no encontrado");
        }
        
        Proyecto proyecto = new ProyectoBuilder()
            .setId(id)
            .setNombre(nombre)
            .setDescripcion(descripcion)
            .setProfesor(profesor)
            .setPresupuesto(10000.0)
            .setDuracionMeses(6)
            .agregarObjetivo("Investigar en salud")
            .agregarObjetivo("Publicar resultados")
            .build();
        
        proyectos.put(id, proyecto);
        profesor.setProyecto(proyecto);
        
        return proyecto;
    }
    
    // ========== MÉTODOS CON DECORATOR ==========
    
    public Proyecto crearProyectoAmbiental(String id, String nombre, String descripcion,
                                          String idProfesor) {
        Profesor profesor = profesores.get(idProfesor);
        if (profesor == null) {
            throw new IllegalArgumentException("Profesor no encontrado");
        }
        
        Proyecto proyectoBase = new Proyecto(id, nombre, descripcion, profesor);
        Proyecto proyectoDecorado = new SeguimientoAmbientalDecorator(proyectoBase);
        
        proyectos.put(id, proyectoDecorado);
        profesor.setProyecto(proyectoDecorado);
        
        return proyectoDecorado;
    }
    
    // ========== MÉTODOS CON PROXY ==========
    
    public void accederProyecto(String usuario, String idProyecto) {
        Proyecto proyecto = proyectos.get(idProyecto);
        if (proyecto == null) {
            throw new IllegalArgumentException("Proyecto no encontrado");
        }
        
        proxyAcceso.accederProyecto(usuario, proyecto);
    }
    
    // ========== MÉTODOS CON OBSERVER ==========
    
    public ProyectoObservable crearProyectoObservable(String id, String nombre, 
                                                     String descripcion, String idProfesor) {
        Profesor profesor = profesores.get(idProfesor);
        if (profesor == null) {
            throw new IllegalArgumentException("Profesor no encontrado");
        }
        
        ProyectoObservable proyecto = new ProyectoObservable(id, nombre, descripcion, profesor);
        
        // Agregar observadores
        proyecto.agregarObservador(new NotificadorEmail());
        
        proyectos.put(id, proyecto);
        profesor.setProyecto(proyecto);
        
        return proyecto;
    }
    
    // ========== MÉTODOS CON STRATEGY ==========
    
    public boolean evaluarProyectoConEstrategia(String idProyecto, 
                                               EstrategiaEvaluacion estrategia) {
        Proyecto proyecto = proyectos.get(idProyecto);
        if (proyecto == null) {
            throw new IllegalArgumentException("Proyecto no encontrado");
        }
        
        return estrategia.evaluarProyecto(proyecto);
    }
    
    // ========== MÉTODOS DE NEGOCIO ==========
    
    public void inscribirEstudianteEnCurso(String idEstudiante, String idCurso) {
        Estudiante estudiante = estudiantes.get(idEstudiante);
        Curso curso = cursos.get(idCurso);
        
        if (estudiante == null || curso == null) {
            throw new IllegalArgumentException("Estudiante o curso no encontrado");
        }
        
        curso.agregarEstudiante(estudiante);
    }
    
    public void asignarEstudianteAProyecto(String idEstudiante, String idProyecto) {
        Estudiante estudiante = estudiantes.get(idEstudi