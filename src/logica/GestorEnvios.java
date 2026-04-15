package logica;

import modelo.Envio;
import java.util.ArrayList;
import java.util.List;

public class GestorEnvios {

    private List<Envio> lista = new ArrayList<>();

    public void agregar(Envio e) {
        lista.add(e);
    }

    public void eliminar(int codigo) {
        lista.removeIf(e -> e.getCodigo() == codigo);
    }

    public List<Envio> getLista() {
        return lista;
    }
}