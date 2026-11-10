package com.jcqrslib.jcqrs.application.Query;

import org.springframework.stereotype.Service;

import com.jcqrslib.jcqrs.application.Request.ProductosByIdRequest;
import com.jcqrslib.jcqrs.application.Response.ProductosResponse;
import com.jcqrslib.jcqrs.cqrs.IRequestHandler;
import com.jcqrslib.jcqrs.data.ProductosData;

@Service
public class ProductosByIdHandler implements IRequestHandler<ProductosByIdRequest, ProductosResponse> {

    @Override
    public ProductosResponse handle(ProductosByIdRequest request) {
        try {
            return ProductosData.getById(request.getId());
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Class<?> getRequestType() {
        return ProductosByIdRequest.class;
    }
}
