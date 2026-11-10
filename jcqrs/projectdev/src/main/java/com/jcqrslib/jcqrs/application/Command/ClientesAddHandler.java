package com.jcqrslib.jcqrs.application.Command;

import org.springframework.stereotype.Service;

import com.jcqrslib.jcqrs.application.Request.ClientesAddRequest;
import com.jcqrslib.jcqrs.cqrs.IRequestHandler;
import com.jcqrslib.jcqrs.data.ClientesData;

@Service
public class ClientesAddHandler implements IRequestHandler<ClientesAddRequest, Integer> {

    @Override
    public Integer handle(ClientesAddRequest request) {
        try {
            System.out.println("👥 [Command] Agregando a data/listaClientes: " + request.getRazon());
            return ClientesData.add(
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
        return ClientesAddRequest.class;
    }    
}
