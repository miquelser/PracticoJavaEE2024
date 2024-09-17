package org.example.managed_bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.verificandoUy;
import org.example.models.HechosModel;
import org.example.models.Calificacion;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    // Getters and Setters
    public List<HechosModel> getHechosList() {
        return hechosList;
    }

    public void setHechosList(List<HechosModel> hechosList) {
        this.hechosList = hechosList;
    }

    public HechosModel getSelectedHecho() {
        return selectedHecho;
    }

    public void setSelectedHecho(HechosModel selectedHecho) {
        this.selectedHecho = selectedHecho;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Calificacion getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Calificacion calificacion) {
        this.calificacion = calificacion;
    }

    public String getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusqueda(String tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public String getValorBusqueda() {
        return valorBusqueda;
    }

    public void setValorBusqueda(String valorBusqueda) {
        this.valorBusqueda = valorBusqueda;
    }

    // hashCode, equals, and toString methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HechosBean that = (HechosBean) o;
        return Objects.equals(fecha, that.fecha) &&
               Objects.equals(descripcion, that.descripcion) &&
               Objects.equals(calificacion, that.calificacion) &&
               Objects.equals(tipoBusqueda, that.tipoBusqueda) &&
               Objects.equals(valorBusqueda, that.valorBusqueda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, descripcion, calificacion, tipoBusqueda, valorBusqueda);
    }

    @Override
    public String toString() {
        return "HechosBean{" +
               "fecha=" + fecha +
               ", descripcion='" + descripcion + '\'' +
               ", calificacion=" + calificacion +
               ", tipoBusqueda='" + tipoBusqueda + '\'' +
               ", valorBusqueda='" + valorBusqueda + '\'' +
               '}';
    }
}
