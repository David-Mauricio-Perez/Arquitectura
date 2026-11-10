package com.jcqrslib.jcqrs.data;

import java.util.ArrayList;
import com.jcqrslib.jcqrs.application.Response.ProductosResponse;

/**
 * Almacén en memoria para el entorno de aplicación.
 * Utiliza un ArrayList estático para almacenar las entidades Productos sin requerir persistencia externa ni permisos.
 */
public class ProductosData {

    // ArrayList estático para el entorno de aplicación
    public static ArrayList<ProductosResponse> listaProductos = new ArrayList<>();
    private static int nextId = 3;

    static {
        // Datos iniciales de prueba cargados en el ArrayList estático
        listaProductos.add(new ProductosResponse(1, "Laptop ThinkPad T14", "Computación", 1250.00, 15, "Laptop empresarial Ryzen 7 32GB RAM"));
        listaProductos.add(new ProductosResponse(2, "Monitor Dell UltraSharp 27", "Monitores", 450.00, 20, "Monitor 4K IPS profesional USB-C"));
        listaProductos.add(new ProductosResponse(3, "Teclado Mecánico Keychron K2", "Periféricos", 95.00, 35, "Teclado inalámbrico switches Gateron Brown"));
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
        return nextId;
    }

    public static synchronized int update(int id, String nombre, String categoria, double precio, int stock, String descripcion) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == id) {
                ProductosResponse actualizado = new ProductosResponse(id, nombre, categoria, precio, stock, descripcion);
                listaProductos.set(i, actualizado);
                return 1;
            }
        }
        return 0;
    }

    public static synchronized int delete(int id) {
        return listaProductos.removeIf(p -> p.getId() == id) ? 1 : 0;
    }
}
