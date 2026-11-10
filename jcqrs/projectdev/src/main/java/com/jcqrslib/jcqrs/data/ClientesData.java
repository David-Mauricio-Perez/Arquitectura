package com.jcqrslib.jcqrs.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jcqrslib.jcqrs.application.Response.ClientesResponse;

/**
 * Almacén para el entorno de aplicación con persistencia en archivo JSON.
 * Mantiene un ArrayList estático en memoria sincronizado automáticamente con data/clientes.json.
 */
public class ClientesData {

    public static ArrayList<ClientesResponse> listaClientes = new ArrayList<>();
    private static int nextId = 0;
    private static final String FILE_PATH = "data/clientes.json";
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    static {
        loadData();
    }

    private static synchronized void loadData() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists() && file.length() > 0) {
                List<ClientesResponse> list = mapper.readValue(file, new TypeReference<List<ClientesResponse>>() {});
                listaClientes = new ArrayList<>(list);
                for (ClientesResponse c : listaClientes) {
                    if (c.getId() > nextId) {
                        nextId = (int) c.getId();
                    }
                }
                System.out.println("💾 [ClientesData] " + listaClientes.size() + " clientes cargados desde " + FILE_PATH);
            } else {
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

                nextId = 3;
                saveData();
                System.out.println("💾 [ClientesData] Archivo " + FILE_PATH + " inicializado con clientes por defecto.");
            }
        } catch (Exception e) {
            System.err.println("❌ [ClientesData] Error al cargar " + FILE_PATH + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static synchronized void saveData() {
        try {
            File file = new File(FILE_PATH);
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            mapper.writeValue(file, listaClientes);
            System.out.println("💾 [ClientesData] Datos persistidos exitosamente en " + file.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("❌ [ClientesData] Error al guardar en " + FILE_PATH + ": " + e.getMessage());
            e.printStackTrace();
        }
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
        saveData();
        return nextId;
    }

    public static synchronized int update(long id, String razon, String direccion, String email, String movil) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getId() == id) {
                var actual = listaClientes.get(i);
                actual.setRazon(razon);
                actual.setDireccion(direccion);
                actual.setEmail(email);
                actual.setMovil(movil);
                saveData();
                return 1;
            }
        }
        return 0;
    }

    public static synchronized int delete(int id) {
        boolean removed = listaClientes.removeIf(c -> c.getId() == id);
        if (removed) {
            saveData();
            return 1;
        }
        return 0;
    }
}
