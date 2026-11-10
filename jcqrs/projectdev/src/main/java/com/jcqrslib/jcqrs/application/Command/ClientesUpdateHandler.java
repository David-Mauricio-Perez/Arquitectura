package com.jcqrslib.jcqrs.application.Command;

import org.springframework.stereotype.Service;

import com.jcqrslib.jcqrs.application.Request.ClientesUpdateRequest;
import com.jcqrslib.jcqrs.cqrs.IRequestHandler;
import com.jcqrslib.jcqrs.data.ClientesData;

@Service
public class ClientesUpdateHandler implements IRequestHandler<ClientesUpdateRequest, Integer> {

    @Override
    public Integer handle(ClientesUpdateRequest request) {
        try {
            System.out.println("👥 [Command] Actualizando en data/listaClientes ID: " + request.getId());
            return ClientesData.update(
                request.getId(),
                request.getRazon(),
                request.getDireccion(),
                request.getEmail(),
                request.getMovil()
            );
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public Class<?> getRequestType() {
        return ClientesUpdateRequest.class;
    }
}
