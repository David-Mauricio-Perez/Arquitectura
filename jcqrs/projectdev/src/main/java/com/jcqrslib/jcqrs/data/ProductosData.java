package com.jcqrslib.jcqrs.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jcqrslib.jcqrs.application.Response.ProductosResponse;

/**
 * Almacén para el entorno de aplicación con persistencia en archivo JSON.
 * Mantiene un ArrayList estático en memoria sincronizado automáticamente con data/productos.json.
 */
public class ProductosData {

    public static ArrayList<ProductosResponse> listaProductos = new ArrayList<>();
    private static int nextId = 0;
    private static final String FILE_PATH = "data/productos.json";
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    static {
        loadData();
    }

    private static synchronized void loadData() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists() && file.length() > 0) {
                List<ProductosResponse> list = mapper.readValue(file, new TypeReference<List<ProductosResponse>>() {});
                listaProductos = new ArrayList<>(list);
                for (ProductosResponse p : listaProductos) {
                    if (p.getId() > nextId) {
                        nextId = p.getId();
                    }
                }
                System.out.println("💾 [ProductosData] " + listaProductos.size() + " productos cargados desde " + FILE_PATH);
            } else {
                listaProductos.add(new ProductosResponse(1, "Laptop ThinkPad T14", "Computación", 1250.00, 15, "Laptop empresarial Ryzen 7 32GB RAM"));
                listaProductos.add(new ProductosResponse(2, "Monitor Dell UltraSharp 27", "Monitores", 450.00, 20, "Monitor 4K IPS profesional USB-C"));
                listaProductos.add(new ProductosResponse(3, "Teclado Mecánico Keychron K2", "Periféricos", 95.00, 35, "Teclado inalámbrico switches Gateron Brown"));
                nextId = 3;
                saveData();
                System.out.println("💾 [ProductosData] Archivo " + FILE_PATH + " inicializado con productos por defecto.");
            }
        } catch (Exception e) {
            System.err.println("❌ [ProductosData] Error al cargar " + FILE_PATH + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static synchronized void saveData() {
        try {
            File file = new File(FILE_PATH);
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            mapper.writeValue(file, listaProductos);
            System.out.println("💾 [ProductosData] Datos persistidos exitosamente en " + file.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("❌ [ProductosData] Error al guardar en " + FILE_PATH + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static synchronized ArrayList<ProductosResponse> getAll() {
        return new ArrayList<>(listaProductos);
    }

    public static synchronized ProductosResponse getById(int id) {
        for (ProductosResponse p : listaProductos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public static synchronized int add(String nombre, String categoria, double precio, int stock, String descripcion) {
        nextId++;
        ProductosResponse nuevo = new ProductosResponse(nextId, nombre, categoria, precio, stock, descripcion);
        listaProductos.add(nuevo);
        saveData();
        return nextId;
    }

    public static synchronized int update(int id, String nombre, String categoria, double precio, int stock, String descripcion) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == id) {
                ProductosResponse actualizado = new ProductosResponse(id, nombre, categoria, precio, stock, descripcion);
                listaProductos.set(i, actualizado);
                saveData();
                return 1;
            }
        }
        return 0;
    }

    public static synchronized int delete(int id) {
        boolean removed = listaProductos.removeIf(p -> p.getId() == id);
        if (removed) {
            saveData();
            return 1;
        }
        return 0;
    }
}
