package com.ues;

import com.ues.creacional.factory.*;
import com.ues.creacional.builder.ProyectoBuilder;
import com.ues.creacional.singleton.ConfiguracionGlobal;
import com.ues.estructural.decorator.*;
import com.ues.estructural.proxy.ProxyAccesoProyecto;
import com.ues.comportamiento.observer.NotificadorEmail;
import com.ues.dominio.*;
import com.ues.service.UniversidadService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   SISTEMA UES - INTEGRACIÓN DE PATRONES DE DISEÑO");
        System.out.println("==================================================");
        
        // Configuración Singleton
        ConfiguracionGlobal config = ConfiguracionGlobal.getInstance();
        System.out.println("\n📊 CONFIGURACIÓN DEL SISTEMA:");
        System.out.println("   Nota mínima aprobatoria: " + config.getNotaMinimaAprobacion() + "/100");
        System.out.println("   Porcentaje para cierre de proyectos: " + config.getPorcentajeCierreProyecto() + "%");
        System.out.println("   Países participantes: " + String.join(", ", config.getPaisesSuramerica()));
        
        // Crear servicio principal
        UniversidadService servicio = new UniversidadService();
        
        // Crear países
        String[] paisesSuramerica = {
            "Colombia", "Venezuela", "Ecuador", "Perú", "Brasil",
            "Bolivia", "Paraguay", "Chile", "Argentina", "Uruguay"
        };
        
        // Crear estudiantes usando Factory
        System.out.println("\n👨‍🎓 CREANDO ESTUDIANTES (Patrón Factory):");
        for (int i = 0; i < 5; i++) {
            String id = "E00" + (i + 1);
            String nombre = "Estudiante " + (i + 1);
            String pais = paisesSuramerica[i % paisesSuramerica.length];
            servicio.crearEstudiante(id, nombre, pais);
            System.out.println("   ✅ " + nombre + " de " + pais);
        }
        
        // Crear profesores
        System.out.println("\n👨‍🏫 CREANDO PROFESORES:");
        servicio.crearProfesor("P001", "Dr. Juan Pérez");
        servicio.crearProfesor("P002", "Dra. María González");
        System.out.println("   ✅ 2 profesores creados");
        
        // Crear cursos
        System.out.println("\n📚 CREANDO CURSOS:");
        servicio.crearCurso("C001", "Investigación Científica", "P001");
        servicio.crearCurso("C002", "Desarrollo Sostenible", "P002");
        System.out.println("   ✅ 2 cursos creados");
        
        // Crear proyectos usando Builder
        System.out.println("\n🏗️ CREANDO PROYECTOS (Patrón Builder):");
        
        // Proyecto 1 - Básico
        ProyectoBuilder builder1 = new ProyectoBuilder();
        Proyecto proyecto1 = builder1
            .setId("PR001")
            .setNombre("Proyecto Amazonía")
            .setDescripcion("Investigación sobre biodiversidad amazónica")
            .setProfesor(servicio.getProfesores().get("P001"))
            .agregarEstudiante(servicio.getEstudiantes().get("E001"))
            .agregarEstudiante(servicio.getEstudiantes().get("E002"))
            .build();
        
        // Decorar el proyecto con seguimiento ambiental
        Proyecto proyecto1Decorado = new SeguimientoAmbientalDecorator(proyecto1);
        ((SeguimientoAmbientalDecorator) proyecto1Decorado).generarReporteAmbiental();
        
        // Proyecto 2 - Con certificación
        ProyectoBuilder builder2 = new ProyectoBuilder();
        Proyecto proyecto2 = builder2
            .setId("PR002")
            .setNombre("Proyecto Andes")
            .setDescripcion("Estudio climático de la cordillera andina")
            .setProfesor(servicio.getProfesores().get("P002"))
            .agregarEstudiante(servicio.getEstudiantes().get("E003"))
            .agregarEstudiante(servicio.getEstudiantes().get("E004"))
            .build();
        
        // Decorar el proyecto con certificación de calidad
        Proyecto proyecto2Decorado = new CertificacionCalidadDecorator(proyecto2);
        ((CertificacionCalidadDecorator) proyecto2Decorado).otorgarCertificado();
        
        System.out.println("   ✅ 2 proyectos creados con decoradores aplicados");
        
        // Simular evaluaciones
        System.out.println("\n📝 SIMULANDO EVALUACIONES:");
        System.out.println("   Proyecto 1 - Evaluaciones:");
        System.out.println("     Estudiante 1: 85 puntos ✅");
        System.out.println("     Estudiante 2: 45 puntos ❌");
        
        Evaluacion ev1 = new Evaluacion("EV001", 
            servicio.getEstudiantes().get("E001"), 
            proyecto1, 85);
        Evaluacion ev2 = new Evaluacion("EV002", 
            servicio.getEstudiantes().get("E002"), 
            proyecto1, 45);
        
        proyecto1.agregarEvaluacion(ev1);
        proyecto1.agregarEvaluacion(ev2);
        
        System.out.println("\n   Proyecto 2 - Evaluaciones:");
        System.out.println("     Estudiante 3: 92 puntos ✅");
        System.out.println("     Estudiante 4: 88 points ✅");
        
        Evaluacion ev3 = new Evaluacion("EV003", 
            servicio.getEstudiantes().get("E003"), 
            proyecto2, 92);
        Evaluacion ev4 = new Evaluacion("EV004", 
            servicio.getEstudiantes().get("E004"), 
            proyecto2, 88);
        
        proyecto2.agregarEvaluacion(ev3);
        proyecto2.agregarEvaluacion(ev4);
        
        // Verificar estado de proyectos
        System.out.println("\n🔍 VERIFICANDO ESTADO DE PROYECTOS:");
        System.out.println("   Proyecto 1 - Estado: " + 
            (proyecto1.isActivo() ? "✅ ACTIVO" : "❌ CERRADO (regla del 50%)"));
        System.out.println("   Proyecto 2 - Estado: " + 
            (proyecto2.isActivo() ? "✅ ACTIVO" : "❌ CERRADO (regla del 50%)"));
        
        // Configurar Observer para notificaciones
        System.out.println("\n🔔 CONFIGURANDO SISTEMA DE NOTIFICACIONES (Patrón Observer):");
        NotificadorEmail notificador = new NotificadorEmail();
        proyecto1.attach(notificador);
        proyecto2.attach(notificador);
        System.out.println("   ✅ Notificador de email configurado para ambos proyectos");
        
        // Probar Proxy de acceso
        System.out.println("\n🔐 CONTROL DE ACCESO (Patrón Proxy):");
        ProxyAccesoProyecto proxy = new ProxyAccesoProyecto();
        
        // Crear usuarios de prueba
        Usuario admin = new Usuario("admin001", "ADMIN");
        Usuario profesor = new Usuario("prof001", "PROFESOR");
        Usuario estudiante = new Usuario("est001", "ESTUDIANTE");
        
        System.out.println("\n   Acceso al Proyecto 1:");
        proxy.accederProyecto(admin, proyecto1);
        proxy.accederProyecto(profesor, proyecto1);
        proxy.accederProyecto(estudiante, proyecto1);
        
        // Demostración de Factory
        System.out.println("\n🏭 DEMOSTRACIÓN DE FACTORY METHOD:");
        EstudianteFactory factory = new EstudianteFactory();
        
        // Crear estudiante investigador
        System.out.println("   Creando estudiante investigador:");
        Estudiante investigador = (Estudiante) factory.createEntity("E006", "Carlos López", "PA1");
        System.out.println("   ✅ " + investigador.getNombre() + " - Tipo: " + investigador.getTipo());
        
        // Estadísticas finales
        System.out.println("\n📊 ESTADÍSTICAS FINALES DEL SISTEMA:");
        System.out.println("   Total estudiantes: " + servicio.getEstudiantes().size());
        System.out.println("   Total profesores: " + servicio.getProfesores().size());
        System.out.println("   Total cursos: " + servicio.getCursos().size());
        System.out.println("   Total proyectos: 2");
        
        System.out.println("\n🎯 PATRONES IMPLEMENTADOS:");
        System.out.println("   ✅ Factory Method (Creacional)");
        System.out.println("   ✅ Builder (Creacional)");
        System.out.println("   ✅ Singleton (Creacional)");
        System.out.println("   ✅ Decorator (Estructural)");
        System.out.println("   ✅ Proxy (Estructural)");
        System.out.println("   ✅ Observer (Comportamiento)");
        
        System.out.println("\n✅ SISTEMA UES INTEGRADO EXITOSAMENTE!");
        System.out.println("==================================================");
    }
}