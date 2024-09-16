package org.example.singleton;

import jakarta.ejb.Singleton;
import org.example.models.HechosModel;
import org.example.models.Calificacion;
import org.example.models.Estado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Singleton
public class HechosSingleton implements HechosLocal, HechosRemote {
    private List<HechosModel> hechosList = new ArrayList<>();
    private AtomicInteger nextNumero = new AtomicInteger(1); // Iniciar el número de hecho en 1

    @Override
    public void agregarHecho(Date fecha, String descripcion, Calificacion calificacion) {
        int numero = nextNumero.getAndIncrement(); // Obtener el siguiente número y luego incrementar
        HechosModel hecho = new HechosModel(numero, fecha, descripcion, calificacion, Estado.NUEVO); // Estado inicial es NUEVO
        hechosList.add(hecho);
    }

    @Override
    public List<HechosModel> getHechos() {
        return hechosList;
    }

    @Override
    public List<HechosModel> buscarHechos(String tipo, String buscar) {
        switch (tipo) {
            case "ID":
                int numero = Integer.parseInt(buscar);
                return hechosList.stream().filter(hecho -> hecho.getNumero() == numero).collect(Collectors.toList());
            case "Descripcion":
                return hechosList.stream().filter(hecho -> hecho.getDescripcion().equals(buscar)).collect(Collectors.toList());
            case "Fecha":
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaBuscada = formatter.parse(buscar);
                    return hechosList.stream().filter(hecho -> hecho.getFecha().equals(fechaBuscada)).collect(Collectors.toList());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            default:
                throw new RuntimeException("Tipo de búsqueda no válido.");
        }
    }

    @Override
    public boolean eliminarHecho(int numero) {
        Optional<HechosModel> optionalHecho = hechosList.stream().filter(hecho -> hecho.getNumero() == numero).findFirst();
        if (optionalHecho.isEmpty()) {
            return false;
        }
        hechosList.remove(optionalHecho.get());
        return true;
    }
}
