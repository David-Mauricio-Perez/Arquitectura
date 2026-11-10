package com.jcqrslib.jcqrs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = "text/html;charset=UTF-8")
    public String index() {
        return """
        <!DOCTYPE html>
        <html lang="es">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>JCQRS - Patrón CQRS en Spring Boot</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <style>
                body { background-color: #f4f6f9; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
                .hero { background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%); color: white; padding: 2.5rem 1rem; border-radius: 0 0 1rem 1rem; margin-bottom: 2rem; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
                .card { border-radius: 0.75rem; border: none; box-shadow: 0 2px 10px rgba(0,0,0,0.05); margin-bottom: 1.5rem; }
                .badge-query { background-color: #0d6efd; }
                .badge-command { background-color: #198754; }
                pre { background: #212529; color: #f8f9fa; padding: 1rem; border-radius: 0.5rem; max-height: 400px; overflow-y: auto; }
            </style>
        </head>
        <body>
            <div class="hero text-center">
                <h1 class="fw-bold">🚀 JCQRS - Servicio CQRS Activo</h1>
                <p class="lead mb-0">Demostración del patrón <strong>Command Query Responsibility Segregation (CQRS)</strong> con Mediator en Java Spring Boot</p>
            </div>

            <div class="container pb-5">
                <div class="row">
                    <!-- Panel de Controles / Endpoints -->
                    <div class="col-lg-6">
                        <!-- Entidad Productos -->
                        <div class="card p-3">
                            <h4 class="text-primary fw-bold">📦 Entidad: Productos (Nueva Entidad)</h4>
                            <p class="text-muted small">Operaciones segregadas entre consultas (lectura) y comandos (escritura):</p>
                            
                            <div class="d-flex flex-wrap gap-2 mb-3">
                                <button class="btn btn-outline-primary btn-sm" onclick="runRequest('/productos', 'GET')">
                                    <span class="badge badge-query">Query</span> GET Todos
                                </button>
                                <button class="btn btn-outline-primary btn-sm" onclick="runRequest('/productos/1', 'GET')">
                                    <span class="badge badge-query">Query</span> GET ID: 1
                                </button>
                                <button class="btn btn-outline-success btn-sm" onclick="crearProductoDemo()">
                                    <span class="badge badge-command">Command</span> POST Agregar
                                </button>
                                <button class="btn btn-outline-warning btn-sm" onclick="actualizarProductoDemo()">
                                    <span class="badge badge-command">Command</span> PUT Actualizar
                                </button>
                                <button class="btn btn-outline-danger btn-sm" onclick="runRequest('/productos/delete/4', 'DELETE')">
                                    <span class="badge badge-command">Command</span> DELETE ID: 4
                                </button>
                            </div>
                            <div class="small">
                                <strong>Enlaces directos:</strong> 
                                <a href="/productos" target="_blank" class="ms-1">/productos</a> | 
                                <a href="/productos/1" target="_blank" class="ms-1">/productos/1</a>
                            </div>
                        </div>

                        <!-- Entidad Clientes -->
                        <div class="card p-3">
                            <h4 class="text-success fw-bold">👥 Entidad: Clientes (Entidad Base)</h4>
                            <p class="text-muted small">Implementación del patrón CQRS para clientes:</p>
                            
                            <div class="d-flex flex-wrap gap-2 mb-3">
                                <button class="btn btn-outline-primary btn-sm" onclick="runRequest('/clientes', 'GET')">
                                    <span class="badge badge-query">Query</span> GET Todos
                                </button>
                                <button class="btn btn-outline-primary btn-sm" onclick="runRequest('/clientes/1', 'GET')">
                                    <span class="badge badge-query">Query</span> GET ID: 1
                                </button>
                            </div>
                            <div class="small">
                                <strong>Enlaces directos:</strong> 
                                <a href="/clientes" target="_blank" class="ms-1">/clientes</a> | 
                                <a href="/clientes/1" target="_blank" class="ms-1">/clientes/1</a>
                            </div>
                        </div>
                    </div>

                    <!-- Panel de Respuestas en Vivo -->
                    <div class="col-lg-6">
                        <div class="card p-3 h-100">
                            <div class="d-flex justify-content-between align-items-center mb-2">
                                <h5 class="fw-bold mb-0">Consola de Respuesta HTTP</h5>
                                <span id="statusBadge" class="badge bg-secondary">Esperando solicitud...</span>
                            </div>
                            <div class="text-muted small mb-2" id="requestInfo">Haz clic en cualquier botón de la izquierda para probar el flujo.</div>
                            <pre id="responseJson">// Aquí se mostrará la respuesta en formato JSON</pre>
                        </div>
                    </div>
                </div>
            </div>

            <script>
                async function runRequest(url, method, body = null) {
                    const statusBadge = document.getElementById('statusBadge');
                    const requestInfo = document.getElementById('requestInfo');
                    const responseJson = document.getElementById('responseJson');

                    statusBadge.className = 'badge bg-warning text-dark';
                    statusBadge.innerText = 'Enviando...';
                    requestInfo.innerText = `${method} ${url}`;
                    responseJson.innerText = 'Cargando datos...';

                    try {
                        const options = { method: method };
                        if (body) {
                            options.headers = { 'Content-Type': 'application/json' };
                            options.body = JSON.stringify(body);
                        }
                        const res = await fetch(url, options);
                        const status = res.status;
                        
                        statusBadge.className = res.ok ? 'badge bg-success' : 'badge bg-danger';
                        statusBadge.innerText = `HTTP ${status} ${res.statusText}`;

                        if (status === 204) {
                            responseJson.innerText = '// 204 No Content (Operación exitosa sin cuerpo de retorno)';
                            return;
                        }

                        const text = await res.text();
                        try {
                            const json = JSON.parse(text);
                            responseJson.innerText = JSON.stringify(json, null, 2);
                        } catch (e) {
                            responseJson.innerText = text;
                        }
                    } catch (err) {
                        statusBadge.className = 'badge bg-danger';
                        statusBadge.innerText = 'Error de conexión';
                        responseJson.innerText = err.toString();
                    }
                }

                function crearProductoDemo() {
                    runRequest('/productos/add', 'POST', {
                        nombre: "Mouse Logitech MX Master 3S",
                        categoria: "Perifericos",
                        precio: 99.99,
                        stock: 50,
                        descripcion: "Mouse ergonomico inalambrico sensor 8K DPI"
                    });
                }

                function actualizarProductoDemo() {
                    runRequest('/productos/update', 'PUT', {
                        id: 4,
                        nombre: "Mouse Logitech MX Master 3S - Edicion Especial",
                        categoria: "Perifericos",
                        precio: 119.99,
                        stock: 45,
                        descripcion: "Mouse ergonomico actualizado con soporte multi-dispositivo"
                    });
                }
            </script>
        </body>
        </html>
        """;
    }
}
