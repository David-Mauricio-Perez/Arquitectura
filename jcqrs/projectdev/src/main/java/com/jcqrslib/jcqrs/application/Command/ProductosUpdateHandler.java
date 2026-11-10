package com.jcqrslib.jcqrs.application.Command;

import org.springframework.stereotype.Service;

import com.jcqrslib.jcqrs.application.Request.ProductosUpdateRequest;
import com.jcqrslib.jcqrs.cqrs.IRequestHandler;
import com.jcqrslib.jcqrs.data.ProductosData;

@Service
public class ProductosUpdateHandler implements IRequestHandler<ProductosUpdateRequest, Integer> {

    @Override
    public Integer handle(ProductosUpdateRequest request) {
        try {
            System.out.println("📦 [Command] Actualizando en data/listaProductos ID: " + request.getId());
            return ProductosData.update(
                request.getId(),
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
        return ProductosUpdateRequest.class;
    }
}
