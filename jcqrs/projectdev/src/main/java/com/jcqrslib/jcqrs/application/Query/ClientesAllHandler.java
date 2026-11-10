package com.jcqrslib.jcqrs.application.Query;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.jcqrslib.jcqrs.application.Request.ClientesAllRequest;
import com.jcqrslib.jcqrs.application.Response.ClientesResponse;
import com.jcqrslib.jcqrs.cqrs.IRequestHandler;
import com.jcqrslib.jcqrs.data.ClientesData;

@Service
public class ClientesAllHandler implements IRequestHandler<ClientesAllRequest, ArrayList<ClientesResponse>> {

    @Override
    public ArrayList<ClientesResponse> handle(ClientesAllRequest request) {
        try {
            return ClientesData.getAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Class<?> getRequestType() {
        return ClientesAllRequest.class;
    }
}
