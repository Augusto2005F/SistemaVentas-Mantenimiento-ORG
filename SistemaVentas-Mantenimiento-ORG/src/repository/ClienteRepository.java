package repository;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private List<Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    public void guardar(Cliente cliente) {
        clientes.add(cliente);
    }

    // BUG intencional: comparación de DNI con == en vez de equals
    public Cliente buscarPorDni(String dni) {
        if (dni == null) {
            return null;
        }
        for (Cliente c : clientes) {
            if (dni.equals(c.getDni())) {
                return c;
            }
        }
        return null;
    }

    public List<Cliente> listar() {
        return clientes;
    }
}
