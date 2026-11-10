package com.jcqrslib.jcqrs.application.Request;

import java.util.ArrayList;
import com.jcqrslib.jcqrs.application.Response.ProductosResponse;
import com.jcqrslib.jcqrs.cqrs.IRequest;

public class ProductosAllRequest implements IRequest<ArrayList<ProductosResponse>> {
    public ProductosAllRequest() {
    }
}
