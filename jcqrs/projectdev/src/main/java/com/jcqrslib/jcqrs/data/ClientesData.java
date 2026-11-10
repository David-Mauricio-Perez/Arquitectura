package com.jcqrslib.jcqrs.data;

import java.util.ArrayList;
import com.jcqrslib.jcqrs.application.Response.ClientesResponse;

/**
 * Almacén en memoria para el entorno de aplicación.
 * Utiliza un ArrayList estático para almacenar las entidades Clientes sin requerir base de datos externa ni permisos.
 */
public class ClientesData {

    // ArrayList estático para el entorno de aplicación
    public static ArrayList<ClientesResponse> listaClientes = new ArrayList<>();
    private static int nextId = 3;

    static {
        var clie1 = new ClientesResponse();
        clie1.setId(1);
        clie1.setRazon("SOLITUX SRL");
        clie1.setDireccion("Av. San Juan 1234");
        clie1.setEmail("ventas@solutixsrl.com.ar");
        clie1.setMovil("11-4422-7755");
        listaClientes.add(clie1);

        var clie2 = new ClientesResponse();
        clie2.setId(2);
        clie2.setRazon("GRUPO TEXTIL SA");
        clie2.setDireccion("Rosario 7788");
        clie2.setEmail("info@grupo-textil.com.ar");
        clie2.setMovil("11-3344-9911");
        listaClientes.add(clie2);

        var clie3 = new ClientesResponse();
        clie3.setId(3);
        clie3.setRazon("ALUMINIUN METAL CO. SACIF");
        clie3.setDireccion("Triunvirato 8293");
        clie3.setEmail("info@aluminiummetal.com.ar");
        clie3.setMovil("11-3322-4275");
        listaClientes.add(clie3);
    }

    public static synchronized ArrayList<ClientesResponse> getAll() {
        return new ArrayList<>(listaClientes);
    }

    public static synchronized ClientesResponse getById(int id) {
        for (ClientesResponse c : listaClientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public static synchronized int add(String razon, String direccion, String email, String movil) {
        nextId++;
        var nuevo = new ClientesResponse();
        nuevo.setId(nextId);
        nuevo.setRazon(razon);
        nuevo.setDireccion(direccion);
        nuevo.setEmail(email);
        nuevo.setMovil(movil);
        listaClientes.add(nuevo);
        return nextId;
    }

    public static synchronized int update(long id, String razon, String direccion, String email, String movil) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getId() == (int) id) {
                var act = new ClientesResponse();
                act.setId((int) id);
                act.setRazon(razon);
                act.setDireccion(direccion);
                act.setEmail(email);
                act.setMovil(movil);
                listaClientes.set(i, act);
                return 1;
            }
        }
        return 0;
    }

    public static synchronized int delete(int id) {
        return listaClientes.removeIf(c -> c.getId() == id) ? 1 : 0;
    }
}
