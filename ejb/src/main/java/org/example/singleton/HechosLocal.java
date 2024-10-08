package org.example.singleton;

import jakarta.ejb.Local;
import org.example.models.HechosModel;
import org.example.models.Calificacion;

import java.util.Date;
import java.util.List;

@Local
public interface HechosLocal {
    void agregarHecho(Date fecha, String descripcion, Calificacion calificacion);
    List<HechosModel> getHechos();
    List<HechosModel> buscarHechos(String tipo, String buscar);
    boolean eliminarHecho(int numero);
}
