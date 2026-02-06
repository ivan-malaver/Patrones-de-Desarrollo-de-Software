package com.ues.comportamiento.observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * PATRÓN OBSERVER - Observador Concreto para Auditoría
 * 
 * Propósito: Registrar todos los eventos importantes del sistema para auditoría y trazabilidad.
 * Ventajas en UES:
 * - Mantiene un historial completo de todas las actividades
 * - Permite auditorías de cumplimiento
 * - Facilita la investigación de incidentes
 * - Proporciona trazabilidad para proyectos ICCIS
 * 
 * Responsabilidad: Registrar eventos del sistema para auditoría.
 */
public class RegistroAuditoria implements ObservadorProyecto {
    
    // Lista para almacenar el historial de eventos
    private List<EventoAuditoria> historial;
    
    // Configuración del sistema de auditoría
    private boolean auditoriaActiva;
    private int nivelLog; // 1=Error, 2=Warning, 3=Info, 4=Debug
    private int maxEventos; // Límite de eventos en memoria
    private String usuarioAuditor; // Usuario que realiza la auditoría
    
    /**
     * Constructor principal.
     * Inicializa el sistema de auditoría con configuración por defecto.
     */
    public RegistroAuditoria() {
        System.out.println("📊 Inicializando Sistema de Auditoría...");
        
        this.historial = new ArrayList<>();
        this.auditoriaActiva = true;
        this.nivelLog = 3; // Nivel Info por defecto
        this.maxEventos = 1000; // Máximo 1000 eventos en memoria
        this.usuarioAuditor = "SISTEMA";
        
        // Registrar evento de inicialización
        registrarEventoSistema("Sistema de auditoría inicializado", 3);
        
        System.out.println("✅ Sistema de Auditoría listo");
    }
    
    /**
     * Constructor con configuración personalizada.
     */
    public RegistroAuditoria(String usuarioAuditor, int nivelLog, int maxEventos) {
        this();
        this.usuarioAuditor = usuarioAuditor;
        this.nivelLog = nivelLog;
        this.maxEventos = maxEventos;
        
        registrarEventoSistema("Configuración personalizada aplicada - Usuario: " + 
                              usuarioAuditor + ", Nivel: " + nivelLog, 3);
    }
    
    /**
     * Método principal del Observer.
     * Se llama automáticamente cuando hay un evento en el sistema.
     */
    @Override
    public void actualizar(EventoProyecto evento) {
        if (!auditoriaActiva) {
            return; // Auditoría desactivada
        }
        
        // Determinar nivel de importancia del evento
        int nivelEvento = determinarNivelEvento(evento);
        
        // Solo registrar si el nivel del evento es menor o igual al nivel configurado
        if (nivelEvento <= nivelLog) {
            registrarEvento(evento, nivelEvento);
        }
        
        // Verificar límite de eventos
        verificarLimiteEventos();
    }
    
    // ========== MÉTODOS DE REGISTRO DE EVENTOS ==========
    
    /**
     * Registra un evento del sistema.
     */
    private void registrarEvento(EventoProyecto evento, int nivel) {
        EventoAuditoria eventoAuditoria = new EventoAuditoria(
            evento.getTipo().toString(),
            evento.getMensaje(),
            evento.getProyecto() != null ? evento.getProyecto().getId() : "SISTEMA",
            evento.getProyecto() != null ? evento.getProyecto().getNombre() : "Sistema UES",
            nivel,
            usuarioAuditor
        );
        
        historial.add(eventoAuditoria);
        
        // Mostrar en consola según nivel
        mostrarEnConsola(eventoAuditoria);
        
        // En una implementación real, aquí se guardaría en base de datos
        guardarEnPersistencia(eventoAuditoria);
    }
    
    /**
     * Registra un evento del sistema (no relacionado con proyectos).
     */
    public void registrarEventoSistema(String descripcion, int nivel) {
        if (nivel <= nivelLog) {
            EventoAuditoria evento = new EventoAuditoria(
                "SISTEMA",
                descripcion,
                "SISTEMA",
                "Sistema UES",
                nivel,
                usuarioAuditor
            );
            
            historial.add(evento);
            mostrarEnConsola(evento);
        }
    }
    
    /**
     * Registra un acceso al sistema.
     */
    public void registrarAcceso(String usuario, String recurso, boolean exitoso) {
        String mensaje = "Acceso " + (exitoso ? "EXITOSO" : "FALLIDO") + 
                        " a " + recurso + " por " + usuario;
        
        EventoAuditoria evento = new EventoAuditoria(
            "ACCESO_" + (exitoso ? "OK" : "FALLIDO"),
            mensaje,
            "SISTEMA",
            recurso,
            exitoso ? 3 : 1, // Nivel diferente para accesos fallidos
            usuarioAuditor
        );
        
        historial.add(evento);
        System.out.println("🔐 AUDITORÍA ACCESO: " + mensaje);
    }
    
    /**
     * Registra una modificación de datos.
     */
    public void registrarModificacion(String entidad, String idEntidad, 
                                     String campo, String valorAnterior, 
                                     String valorNuevo, String usuario) {
        String mensaje = "Modificación en " + entidad + " " + idEntidad + 
                        ": " + campo + " cambiado de '" + valorAnterior + 
                        "' a '" + valorNuevo + "'";
        
        EventoAuditoria evento = new EventoAuditoria(
            "MODIFICACION",
            mensaje,
            idEntidad,
            entidad,
            3,
            usuario
        );
        
        historial.add(evento);
        System.out.println("✏️  AUDITORÍA MODIFICACIÓN: " + mensaje);
    }
    
    // ========== MÉTODOS DE CONSULTA Y REPORTES ==========
    
    /**
     * Obtiene el historial completo de auditoría.
     */
    public List<EventoAuditoria> getHistorialCompleto() {
        return new ArrayList<>(historial); // Retorna copia para evitar modificaciones
    }
    
    /**
     * Obtiene eventos por tipo.
     */
    public List<EventoAuditoria> getEventosPorTipo(String tipo) {
        List<EventoAuditoria> eventosFiltrados = new ArrayList<>();
        for (EventoAuditoria evento : historial) {
            if (evento.getTipo().contains(tipo)) {
                eventosFiltrados.add(evento);
            }
        }
        return eventosFiltrados;
    }
    
    /**
     * Obtiene eventos por nivel.
     */
    public List<EventoAuditoria> getEventosPorNivel(int nivel) {
        List<EventoAuditoria> eventosFiltrados = new ArrayList<>();
        for (EventoAuditoria evento : historial) {
            if (evento.getNivel() == nivel) {
                eventosFiltrados.add(evento);
            }
        }
        return eventosFiltrados;
    }
    
    /**
     * Obtiene eventos por proyecto.
     */
    public List<EventoAuditoria> getEventosPorProyecto(String idProyecto) {
        List<EventoAuditoria> eventosFiltrados = new ArrayList<>();
        for (EventoAuditoria evento : historial) {
            if (evento.getIdRecurso().equals(idProyecto)) {
                eventosFiltrados.add(evento);
            }
        }
        return eventosFiltrados;
    }
    
    /**
     * Obtiene eventos en un rango de fechas.
     */
    public List<EventoAuditoria> getEventosPorFecha(Date fechaInicio, Date fechaFin) {
        List<EventoAuditoria> eventosFiltrados = new ArrayList<>();
        for (EventoAuditoria evento : historial) {
            if (!evento.getFecha().before(fechaInicio) && 
                !evento.getFecha().after(fechaFin)) {
                eventosFiltrados.add(evento);
            }
        }
        return eventosFiltrados;
    }
    
    /**
     * Genera un reporte estadístico.
     */
    public ReporteAuditoria generarReporte() {
        ReporteAuditoria reporte = new ReporteAuditoria();
        
        reporte.setTotalEventos(historial.size());
        reporte.setFechaGeneracion(new Date());
        
        // Contar por nivel
        int[] conteoNiveles = new int[5]; // Índices 1-4
        for (EventoAuditoria evento : historial) {
            if (evento.getNivel() >= 1 && evento.getNivel() <= 4) {
                conteoNiveles[evento.getNivel()]++;
            }
        }
        
        reporte.setErrores(conteoNiveles[1]);
        reporte.setAdvertencias(conteoNiveles[2]);
        reporte.setInformaciones(conteoNiveles[3]);
        reporte.setDebugs(conteoNiveles[4]);
        
        return reporte;
    }
    
    /**
     * Genera un reporte de actividad por usuario.
     */
    public Map<String, Integer> generarReporteActividadUsuarios() {
        Map<String, Integer> actividad = new HashMap<>();
        
        for (EventoAuditoria evento : historial) {
            String usuario = evento.getUsuario();
            actividad.put(usuario, actividad.getOrDefault(usuario, 0) + 1);
        }
        
        return actividad;
    }
    
    // ========== MÉTODOS DE CONFIGURACIÓN ==========
    
    /**
     * Activa/desactiva el sistema de auditoría.
     */
    public void setAuditoriaActiva(boolean activa) {
        this.auditoriaActiva = activa;
        registrarEventoSistema("Auditoría " + (activa ? "activada" : "desactivada"), 3);
    }
    
    /**
     * Cambia el nivel de logging.
     */
    public void setNivelLog(int nivel) {
        this.nivelLog = nivel;
        registrarEventoSistema("Nivel de log cambiado a " + nivel, 3);
    }
    
    /**
     * Cambia el usuario auditor.
     */
    public void setUsuarioAuditor(String usuario) {
        this.usuarioAuditor = usuario;
        registrarEventoSistema("Usuario auditor cambiado a " + usuario, 3);
    }
    
    /**
     * Limpia el historial en memoria (no afecta persistencia).
     */
    public void limpiarHistorial() {
        registrarEventoSistema("Historial de auditoría limpiado - " + 
                              historial.size() + " eventos eliminados", 3);
        historial.clear();
    }
    
    /**
     * Exporta el historial a un archivo.
     */
    public void exportarHistorial(String rutaArchivo) {
        try {
            // En una implementación real, se escribiría a archivo
            System.out.println("💾 Exportando historial de auditoría a: " + rutaArchivo);
            registrarEventoSistema("Historial exportado a " + rutaArchivo, 3);
        } catch (Exception e) {
            registrarEventoSistema("Error exportando historial: " + e.getMessage(), 1);
        }
    }
    
    // ========== MÉTODOS PRIVADOS DE UTILIDAD ==========
    
    /**
     * Determina el nivel de importancia de un evento.
     */
    private int determinarNivelEvento(EventoProyecto evento) {
        switch (evento.getTipo()) {
            case PROYECTO_CERRADO:
                return 1; // Error - Proyecto cerrado es crítico
                
            case EVALUACION_AGREGADA:
                // Si la evaluación es baja, es más importante
                String mensaje = evento.getMensaje().toLowerCase();
                if (mensaje.contains("baja") || mensaje.contains("<70")) {
                    return 2; // Warning
                }
                return 3; // Info
                
            case PROYECTO_CREADO:
                return 3; // Info
                
            case ESTUDIANTE_AGREGADO:
                return 4; // Debug
                
            default:
                return 3; // Info por defecto
        }
    }
    
    /**
     * Muestra el evento en consola según su nivel.
     */
    private void mostrarEnConsola(EventoAuditoria evento) {
        String icono;
        String color;
        
        switch (evento.getNivel()) {
            case 1: // ERROR
                icono = "❌";
                color = "\u001B[31m"; // Rojo
                break;
                
            case 2: // WARNING
                icono = "⚠️";
                color = "\u001B[33m"; // Amarillo
                break;
                
            case 3: // INFO
                icono = "ℹ️";
                color = "\u001B[36m"; // Cyan
                break;
                
            case 4: // DEBUG
                icono = "🔍";
                color = "\u001B[90m"; // Gris
                break;
                
            default:
                icono = "📝";
                color = "\u001B[0m"; // Reset
        }
        
        System.out.println(color + icono + " AUDITORÍA [" + 
                          evento.getTipo() + "] " + 
                          evento.getDescripcion() + "\u001B[0m");
    }
    
    /**
     * Guarda el evento en persistencia (simulado).
     */
    private void guardarEnPersistencia(EventoAuditoria evento) {
        // En una implementación real, aquí se guardaría en:
        // 1. Base de datos
        // 2. Archivo de log
        // 3. Sistema de monitoreo externo
        
        // Simulación de guardado
        if (evento.getNivel() <= 2) { // Solo errores y warnings
            // Guardar en "base de datos" crítica
        }
    }
    
    /**
     * Verifica y gestiona el límite de eventos en memoria.
     */
    private void verificarLimiteEventos() {
        if (historial.size() > maxEventos) {
            // Eliminar los eventos más antiguos (los primeros 100)
            int eventosAEliminar = Math.min(100, historial.size() - maxEventos/2);
            List<EventoAuditoria> eventosEliminados = new ArrayList<>(
                historial.subList(0, eventosAEliminar)
            );
            historial.subList(0, eventosAEliminar).clear();
            
            registrarEventoSistema("Límite de eventos alcanzado. Eliminados " + 
                                  eventosAEliminar + " eventos antiguos", 3);
        }
    }
    
    // ========== CLASES INTERNAS ==========
    
    /**
     * Clase que representa un evento de auditoría.
     */
    public static class EventoAuditoria {
        private String tipo;
        private String descripcion;
        private String idRecurso;
        private String nombreRecurso;
        private int nivel;
        private String usuario;
        private Date fecha;
        
        public EventoAuditoria(String tipo, String descripcion, String idRecurso,
                              String nombreRecurso, int nivel, String usuario) {
            this.tipo = tipo;
            this.descripcion = descripcion;
            this.idRecurso = idRecurso;
            this.nombreRecurso = nombreRecurso;
            this.nivel = nivel;
            this.usuario = usuario;
            this.fecha = new Date();
        }
        
        // Getters
        public String getTipo() { return tipo; }
        public String getDescripcion() { return descripcion; }
        public String getIdRecurso() { return idRecurso; }
        public String getNombreRecurso() { return nombreRecurso; }
        public int getNivel() { return nivel; }
        public String getUsuario() { return usuario; }
        public Date getFecha() { return fecha; }
        
        @Override
        public String toString() {
            return String.format("[%s] %s | %s | %s | %s", 
                fecha, tipo, descripcion, nombreRecurso, usuario);
        }
    }
    
    /**
     * Clase para reportes de auditoría.
     */
    public static class ReporteAuditoria {
        private Date fechaGeneracion;
        private int totalEventos;
        private int errores;
        private int advertencias;
        private int informaciones;
        private int debugs;
        
        // Setters y Getters
        public void setFechaGeneracion(Date fecha) { this.fechaGeneracion = fecha; }
        public void setTotalEventos(int total) { this.totalEventos = total; }
        public void setErrores(int errores) { this.errores = errores; }
        public void setAdvertencias(int advertencias) { this.advertencias = advertencias; }
        public void setInformaciones(int informaciones) { this.informaciones = informaciones; }
        public void setDebugs(int debugs) { this.debugs = debugs; }
        
        public Date getFechaGeneracion() { return fechaGeneracion; }
        public int getTotalEventos() { return totalEventos; }
        public int getErrores() { return errores; }
        public int getAdvertencias() { return advertencias; }
        public int getInformaciones() { return informaciones; }
        public int getDebugs() { return debugs; }
        
        @Override
        public String toString() {
            return String.format(
                "Reporte de Auditoría UES\n" +
                "Fecha: %s\n" +
                "Total eventos: %d\n" +
                "Errores: %d\n" +
                "Advertencias: %d\n" +
                "Informaciones: %d\n" +
                "Debugs: %d",
                fechaGeneracion, totalEventos, errores, advertencias, informaciones, debugs
            );
        }
    }
}

// ========== EJEMPLO DE USO EN Main.java ==========
/*
public class Main {
    public static void main(String[] args) {
        System.out.println("🔍 DEMOSTRACIÓN DEL SISTEMA DE AUDITORÍA\n");
        
        // 1. Crear el sistema de auditoría
        System.out.println("1. 📊 INICIALIZANDO SISTEMA DE AUDITORÍA:");
        RegistroAuditoria auditoria = new RegistroAuditoria("ADMIN_UES", 3, 500);
        
        // 2. Registrar eventos del sistema
        System.out.println("\n2. 📝 REGISTRANDO EVENTOS DEL SISTEMA:");
        auditoria.registrarEventoSistema("Sistema UES iniciado", 3);
        auditoria.registrarEventoSistema("Base de datos conectada", 3);
        auditoria.registrarEventoSistema("Error de conexión con ICCIS", 1);
        
        // 3. Registrar accesos
        System.out.println("\n3. 🔐 REGISTRANDO ACCESOS:");
        auditoria.registrarAcceso("carlos.ruiz", "/proyectos/iccIS-001", true);
        auditoria.registrarAcceso("usuario.desconocido", "/admin/usuarios", false);
        
        // 4. Registrar modificaciones
        System.out.println("\n4. ✏️ REGISTRANDO MODIFICACIONES:");
        auditoria.registrarModificacion("Estudiante", "E001", "calificacion", 
                                       "75.5", "80.0", "profesor.garcia");
        
        // 5. Simular eventos de Observer
        System.out.println("\n5. 🔄 SIMULANDO EVENTOS OBSERVER:");
        
        // Crear un proyecto de prueba
        Proyecto proyecto = new Proyecto("PR001", "Investigación Amazonía", 
                                        "Proyecto de investigación", null);
        
        // Simular eventos
        EventoProyecto evento1 = new EventoProyecto(
            EventoProyecto.TipoEvento.PROYECTO_CREADO,
            proyecto,
            "Proyecto creado por sistema"
        );
        
        EventoProyecto evento2 = new EventoProyecto(
            EventoProyecto.TipoEvento.EVALUACION_AGREGADA,
            proyecto,
            "Evaluación agregada: 45 puntos (BAJA)"
        );
        
        EventoProyecto evento3 = new EventoProyecto(
            EventoProyecto.TipoEvento.PROYECTO_CERRADO,
            proyecto,
            "Proyecto cerrado automáticamente"
        );
        
        // El Observer se actualiza automáticamente
        auditoria.actualizar(evento1);
        auditoria.actualizar(evento2);
        auditoria.actualizar(evento3);
        
        // 6. Generar reportes
        System.out.println("\n6. 📈 GENERANDO REPORTES:");
        
        // Reporte general
        RegistroAuditoria.ReporteAuditoria reporte = auditoria.generarReporte();
        System.out.println(reporte.toString());
        
        // Eventos por proyecto
        System.out.println("\n📋 EVENTOS DEL PROYECTO PR001:");
        List<RegistroAuditoria.EventoAuditoria> eventosProyecto = 
            auditoria.getEventosPorProyecto("PR001");
        
        for (RegistroAuditoria.EventoAuditoria evento : eventosProyecto) {
            System.out.println("  • " + evento.getDescripcion());
        }
        
        // 7. Consultas especializadas
        System.out.println("\n7. 🔍 CONSULTAS ESPECIALIZADAS:");
        
        // Eventos de error
        List<RegistroAuditoria.EventoAuditoria> eventosError = 
            auditoria.getEventosPorNivel(1);
        System.out.println("Eventos de ERROR: " + eventosError.size());
        
        // Actividad por usuario
        Map<String, Integer> actividad = auditoria.generarReporteActividadUsuarios();
        System.out.println("Actividad por usuario:");
        for (Map.Entry<String, Integer> entry : actividad.entrySet()) {
            System.out.println("  • " + entry.getKey() + ": " + entry.getValue() + " eventos");
        }
        
        // 8. Configuración del sistema
        System.out.println("\n8. ⚙️ CONFIGURACIÓN DEL SISTEMA:");
        auditoria.setNivelLog(4); // Cambiar a nivel DEBUG
        auditoria.registrarEventoSistema("Mensaje de debug de prueba", 4);
        
        // 9. Exportar historial
        System.out.println("\n9. 💾 EXPORTANDO HISTORIAL:");
        auditoria.exportarHistorial("C:/auditoria/ues_auditoria_" + 
                                   new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".log");
        
        System.out.println("\n✅ DEMOSTRACIÓN DE AUDITORÍA COMPLETADA");
        System.out.println("Total eventos registrados: " + auditoria.getHistorialCompleto().size());
    }
}
*/