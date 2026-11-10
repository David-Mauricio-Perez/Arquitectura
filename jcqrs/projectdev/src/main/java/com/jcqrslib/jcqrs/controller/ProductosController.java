package com.jcqrslib.jcqrs.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcqrslib.jcqrs.application.Request.ProductosAddRequest;
import com.jcqrslib.jcqrs.application.Request.ProductosAllRequest;
import com.jcqrslib.jcqrs.application.Request.ProductosByIdRequest;
import com.jcqrslib.jcqrs.application.Request.ProductosDeleteRequest;
import com.jcqrslib.jcqrs.application.Request.ProductosUpdateRequest;
import com.jcqrslib.jcqrs.application.Response.ProductosResponse;
import com.jcqrslib.jcqrs.cqrs.Mediator;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private Mediator _mediator;

    @GetMapping
    public ResponseEntity<ArrayList<ProductosResponse>> getAll() {
        var response = _mediator.send(new ProductosAllRequest());
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosResponse> getById(@PathVariable("id") int id) {
        var response = _mediator.send(new ProductosByIdRequest(id));
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> add(@RequestBody ProductosAddRequest entity) {
        var response = _mediator.send(entity);
        if (response == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Integer> update(@RequestBody ProductosUpdateRequest entity) {
        var response = _mediator.send(entity);
        if (response == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        var response = _mediator.send(new ProductosDeleteRequest(id));
        if (response == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
