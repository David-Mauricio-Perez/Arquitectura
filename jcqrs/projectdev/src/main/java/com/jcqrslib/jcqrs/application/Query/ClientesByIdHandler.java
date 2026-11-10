package com.jcqrslib.jcqrs.application.Query;

import org.springframework.stereotype.Service;

import com.jcqrslib.jcqrs.application.Request.ClientesByIdRequest;
import com.jcqrslib.jcqrs.application.Response.ClientesResponse;
import com.jcqrslib.jcqrs.cqrs.IRequestHandler;
import com.jcqrslib.jcqrs.data.ClientesData;

@Service
public class ClientesByIdHandler implements IRequestHandler<ClientesByIdRequest, ClientesResponse> {

    @Override
    public ClientesResponse handle(ClientesByIdRequest request) {
        try {
            return ClientesData.getById(request.getId());
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Class<?> getRequestType() {
        return ClientesByIdRequest.class;
    }
}
