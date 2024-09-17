package org.example.managed_bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.example.verificandoUy;
import org.example.models.HechosModel;
import org.example.models.Calificacion;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@Named
@SessionScoped
public class HechosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private verificandoUy verificandoUy;

    private List<HechosModel> hechosList;
    private HechosModel selectedHecho;
    private Date fecha;
    private String descripcion;
    private Calificacion calificacion;
    private String tipoBusqueda;
    private String valorBusqueda;

    @PostConstruct
    public void init() {
        this.hechosList = verificandoUy.getHechos();
    }

    public void agregarHecho() {
        try {
            verificandoUy.agregarHecho(fecha, descripcion, calificacion);
            this.hechosList = verificandoUy.getHechos();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void eliminarHecho(int numero) {
        if (verificandoUy.eliminarHecho(numero)) {
            this.hechosList = verificandoUy.getHechos();
        }
    }

    public void buscarHechos() {
        this.hechosList = verificandoUy.buscarHechos(tipoBusqueda, valorBusqueda);
    }

    public String formatFecha(Date fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(fecha);
    }
}
