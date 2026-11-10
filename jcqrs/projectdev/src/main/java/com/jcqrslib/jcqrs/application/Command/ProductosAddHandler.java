package com.jcqrslib.jcqrs.application.Command;

import org.springframework.stereotype.Service;

import com.jcqrslib.jcqrs.application.Request.ProductosAddRequest;
import com.jcqrslib.jcqrs.cqrs.IRequestHandler;
import com.jcqrslib.jcqrs.data.ProductosData;

@Service
public class ProductosAddHandler implements IRequestHandler<ProductosAddRequest, Integer> {

    @Override
    public Integer handle(ProductosAddRequest request) {
        try {
            System.out.println("📦 [Command] Agregando a data/listaProductos: " + request.getNombre());
            return ProductosData.add(
                request.getNombre(),
                request.getCategoria(),
                request.getPrecio(),
                request.getStock(),
                request.getDescripcion()
            );
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public Class<?> getRequestType() {
        return ProductosAddRequest.class;
    }
}
