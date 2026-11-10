package com.jcqrslib.jcqrs.application.Command;

import org.springframework.stereotype.Service;

import com.jcqrslib.jcqrs.application.Request.ProductosDeleteRequest;
import com.jcqrslib.jcqrs.cqrs.IRequestHandler;
import com.jcqrslib.jcqrs.data.ProductosData;

@Service
public class ProductosDeleteHandler implements IRequestHandler<ProductosDeleteRequest, Integer> {

    @Override
    public Integer handle(ProductosDeleteRequest request) {
        try {
            System.out.println("📦 [Command] Eliminando de data/listaProductos ID: " + request.getId());
            return ProductosData.delete(request.getId());
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public Class<?> getRequestType() {
        return ProductosDeleteRequest.class;
    }
}
