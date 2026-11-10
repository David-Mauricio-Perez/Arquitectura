package com.jcqrslib.jcqrs.application.Query;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.jcqrslib.jcqrs.application.Request.ProductosAllRequest;
import com.jcqrslib.jcqrs.application.Response.ProductosResponse;
import com.jcqrslib.jcqrs.cqrs.IRequestHandler;
import com.jcqrslib.jcqrs.data.ProductosData;

@Service
public class ProductosAllHandler implements IRequestHandler<ProductosAllRequest, ArrayList<ProductosResponse>> {

    @Override
    public ArrayList<ProductosResponse> handle(ProductosAllRequest request) {
        try {
            return ProductosData.getAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Class<?> getRequestType() {
        return ProductosAllRequest.class;
    }
}
