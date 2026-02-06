package com.ues.estructural.proxy;

import com.ues.model.Proyecto;
import java.util.Arrays;
import java.util.List;

/**
 * PROXY DE CONTROL DE ACCESO
 */
public class ProxyAccesoProyecto implements AccesoProyecto {
    private AccesoProyectoReal accesoReal;
    private List<String> proyectosConfidenciales;
    
    public ProxyAccesoProyecto() {
        this.accesoReal = new AccesoProyectoReal();
        this.proyectosConfidenciales = Arrays.asList("ICCIS-001", "ICCIS-002");
    }
    
    @Override
    public void accederProyecto(String usuario, Proyecto proyecto) {
        // Control de acceso
        if (proyectosConfidenciales.contains(proyecto.getId()) && 
            !usuario.equals("ADMIN")) {
            System.out.println("🚫 Acceso denegado: Proyecto confidencial");
            return;
        }
        
        // Auditoría
        System.out.println("📝 Auditoría: " + usuario + 
                          " intentó acceder a " + proyecto.getNombre());
        
        // Delegar al objeto real
        accesoReal.accederProyecto(usuario, proyecto);
    }
    
    @Override
    public String verInformacionBasica(Proyecto proyecto) {
        return accesoReal.verInformacionBasica(proyecto);
    }
}