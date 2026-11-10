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
            <title>JCQRS API - FastAPI Docs Style</title>
            <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&family=JetBrains+Mono:wght@400;500;600&display=swap" rel="stylesheet">
            <style>
                :root {
                    --bg-page: #0f172a;
                    --bg-card: #1e293b;
                    --bg-card-header: #334155;
                    --border-color: #334155;
                    --text-main: #f8fafc;
                    --text-muted: #94a3b8;
                    --accent-get: #0ea5e9;
                    --accent-post: #10b981;
                    --accent-put: #f59e0b;
                    --accent-del: #ef4444;
                    --code-bg: #0b1120;
                }
                * { box-sizing: border-box; margin: 0; padding: 0; }
                body {
                    background-color: var(--bg-page);
                    color: var(--text-main);
                    font-family: 'Inter', -apple-system, BlinkMacSystemFont, sans-serif;
                    line-height: 1.5;
                    padding-bottom: 60px;
                }
                header {
                    background: #111827;
                    border-bottom: 1px solid var(--border-color);
                    padding: 18px 30px;
                    display: flex;
                    align-items: center;
                    justify-content: space-between;
                }
                .brand {
                    display: flex;
                    align-items: center;
                    gap: 12px;
                }
                .brand-logo {
                    background: #009688;
                    color: white;
                    font-weight: 800;
                    padding: 6px 14px;
                    border-radius: 6px;
                    font-size: 1.1rem;
                    letter-spacing: 0.5px;
                }
                .brand h1 {
                    font-size: 1.35rem;
                    font-weight: 700;
                    margin: 0;
                }
                .badge-version {
                    background: #374151;
                    color: #d1d5db;
                    font-size: 0.75rem;
                    padding: 3px 8px;
                    border-radius: 9999px;
                    font-weight: 600;
                }
                .container {
                    max-width: 1100px;
                    margin: 30px auto;
                    padding: 0 20px;
                }
                .api-info {
                    background: var(--bg-card);
                    border: 1px solid var(--border-color);
                    border-radius: 10px;
                    padding: 20px;
                    margin-bottom: 25px;
                }
                .api-info p { color: var(--text-muted); font-size: 0.95rem; margin-top: 5px; }
                .base-url {
                    font-family: 'JetBrains Mono', monospace;
                    background: var(--code-bg);
                    padding: 4px 10px;
                    border-radius: 6px;
                    font-size: 0.85rem;
                    color: #38bdf8;
                    display: inline-block;
                    margin-top: 10px;
                }

                .tag-section {
                    margin-bottom: 30px;
                }
                .tag-title {
                    font-size: 1.3rem;
                    font-weight: 700;
                    color: #e2e8f0;
                    margin-bottom: 12px;
                    display: flex;
                    align-items: center;
                    gap: 10px;
                    border-bottom: 1px solid var(--border-color);
                    padding-bottom: 8px;
                }

                /* Endpoint Box */
                .op-block {
                    border-radius: 8px;
                    margin-bottom: 12px;
                    overflow: hidden;
                    border: 1px solid;
                    background: var(--bg-card);
                    transition: border-color 0.2s ease;
                }
                .op-get { border-color: rgba(14, 165, 233, 0.4); }
                .op-post { border-color: rgba(16, 185, 129, 0.4); }
                .op-put { border-color: rgba(245, 158, 11, 0.4); }
                .op-del { border-color: rgba(239, 68, 68, 0.4); }

                .op-header {
                    padding: 10px 16px;
                    display: flex;
                    align-items: center;
                    justify-content: space-between;
                    cursor: pointer;
                    user-select: none;
                }
                .op-get .op-header { background: rgba(14, 165, 233, 0.1); }
                .op-post .op-header { background: rgba(16, 185, 129, 0.1); }
                .op-put .op-header { background: rgba(245, 158, 11, 0.1); }
                .op-del .op-header { background: rgba(239, 68, 68, 0.1); }

                .op-summary {
                    display: flex;
                    align-items: center;
                    gap: 14px;
                    flex-grow: 1;
                }
                .method-badge {
                    font-weight: 700;
                    font-size: 0.8rem;
                    min-width: 70px;
                    text-align: center;
                    padding: 4px 8px;
                    border-radius: 5px;
                    color: #fff;
                    font-family: 'JetBrains Mono', monospace;
                }
                .bg-get { background: var(--accent-get); }
                .bg-post { background: var(--accent-post); }
                .bg-put { background: var(--accent-put); }
                .bg-del { background: var(--accent-del); }

                .op-path {
                    font-family: 'JetBrains Mono', monospace;
                    font-size: 0.95rem;
                    font-weight: 600;
                    color: #f1f5f9;
                }
                .op-desc {
                    color: var(--text-muted);
                    font-size: 0.85rem;
                    margin-left: auto;
                    margin-right: 15px;
                }

                .op-body {
                    display: none;
                    padding: 18px;
                    border-top: 1px solid var(--border-color);
                    background: #1e293b;
                }
                .op-body.active { display: block; }

                /* Form Inputs */
                .form-section {
                    margin-bottom: 16px;
                }
                .form-section-title {
                    font-size: 0.85rem;
                    text-transform: uppercase;
                    letter-spacing: 0.5px;
                    font-weight: 600;
                    color: #94a3b8;
                    margin-bottom: 8px;
                }
                .form-grid {
                    display: grid;
                    grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
                    gap: 12px;
                }
                .form-group {
                    display: flex;
                    flex-direction: column;
                    gap: 4px;
                }
                .form-group label {
                    font-size: 0.8rem;
                    color: #cbd5e1;
                    font-weight: 500;
                }
                .form-input {
                    background: var(--code-bg);
                    border: 1px solid var(--border-color);
                    border-radius: 6px;
                    color: #f8fafc;
                    padding: 8px 12px;
                    font-family: 'Inter', sans-serif;
                    font-size: 0.88rem;
                    outline: none;
                    transition: border-color 0.2s;
                }
                .form-input:focus {
                    border-color: #38bdf8;
                }
                .form-textarea {
                    width: 100%;
                    min-height: 110px;
                    background: var(--code-bg);
                    border: 1px solid var(--border-color);
                    border-radius: 6px;
                    color: #38bdf8;
                    font-family: 'JetBrains Mono', monospace;
                    padding: 10px 14px;
                    font-size: 0.85rem;
                    outline: none;
                    resize: vertical;
                }

                .btn-execute {
                    background: #2563eb;
                    color: white;
                    border: none;
                    border-radius: 6px;
                    padding: 9px 24px;
                    font-size: 0.9rem;
                    font-weight: 600;
                    cursor: pointer;
                    transition: background 0.2s;
                    display: inline-flex;
                    align-items: center;
                    gap: 8px;
                }
                .btn-execute:hover { background: #1d4ed8; }
                .btn-execute:active { transform: scale(0.98); }

                /* Response Area */
                .response-area {
                    margin-top: 18px;
                    display: none;
                }
                .response-header {
                    display: flex;
                    align-items: center;
                    justify-content: space-between;
                    margin-bottom: 6px;
                }
                .status-badge {
                    font-family: 'JetBrains Mono', monospace;
                    font-size: 0.8rem;
                    font-weight: 700;
                    padding: 3px 8px;
                    border-radius: 4px;
                }
                .status-2xx { background: rgba(16, 185, 129, 0.2); color: #34d399; border: 1px solid #059669; }
                .status-4xx { background: rgba(239, 68, 68, 0.2); color: #f87171; border: 1px solid #dc2626; }
                .status-loading { background: rgba(245, 158, 11, 0.2); color: #fbbf24; border: 1px solid #d97706; }

                .code-display {
                    background: var(--code-bg);
                    border: 1px solid var(--border-color);
                    border-radius: 6px;
                    padding: 14px;
                    font-family: 'JetBrains Mono', monospace;
                    font-size: 0.85rem;
                    color: #f1f5f9;
                    white-space: pre-wrap;
                    word-break: break-all;
                    max-height: 380px;
                    overflow-y: auto;
                }
                .curl-snippet {
                    background: #111827;
                    border: 1px solid #374151;
                    border-radius: 6px;
                    padding: 8px 12px;
                    font-family: 'JetBrains Mono', monospace;
                    font-size: 0.78rem;
                    color: #9ca3af;
                    margin-bottom: 8px;
                    word-break: break-all;
                }
            </style>
        </head>
        <body>
            <header>
                <div class="brand">
                    <span class="brand-logo">FASTAPI UI</span>
                    <div>
                        <h1>JCQRS Architecture API</h1>
                    </div>
                </div>
                <div>
                    <span class="badge-version">CQRS + Mediator</span>
                    <span class="badge-version" style="background:#065f46; color:#a7f3d0; margin-left:6px;">Persistencia JSON</span>
                </div>
            </header>

            <div class="container">
                <div class="api-info">
                    <h2 style="font-size: 1.15rem; font-weight: 700;">Consola Interactiva de Entidades (Estilo FastAPI / Swagger)</h2>
                    <p>Ingresa los datos netos en los formularios de cada endpoint para enviar peticiones HTTP y recibir la respuesta en formato JSON en tiempo real.</p>
                    <span class="base-url">Servidor Activo: http://localhost:9898</span>
                </div>

                <!-- SECCIÓN PRODUCTOS -->
                <div class="tag-section">
                    <div class="tag-title">
                        <span>📦 Productos</span>
                        <span style="font-size: 0.85rem; font-weight: 400; color: var(--text-muted);">(Operaciones CQRS para Productos con persistencia en <code>data/productos.json</code>)</span>
                    </div>

                    <!-- GET /productos -->
                    <div class="op-block op-get">
                        <div class="op-header" onclick="toggleBlock('prod-all')">
                            <div class="op-summary">
                                <span class="method-badge bg-get">GET</span>
                                <span class="op-path">/productos</span>
                                <span class="op-desc">Listar todos los productos (Query)</span>
                            </div>
                        </div>
                        <div class="op-body" id="prod-all">
                            <p style="color:#94a3b8; font-size:0.85rem; margin-bottom:12px;">Obtiene la lista completa de productos desde el almacenamiento.</p>
                            <button class="btn-execute" onclick="executeCall('/productos', 'GET', null, 'prod-all-resp')">Ejecutar Petición</button>
                            <div class="response-area" id="prod-all-resp"></div>
                        </div>
                    </div>

                    <!-- GET /productos/{id} -->
                    <div class="op-block op-get">
                        <div class="op-header" onclick="toggleBlock('prod-id')">
                            <div class="op-summary">
                                <span class="method-badge bg-get">GET</span>
                                <span class="op-path">/productos/{id}</span>
                                <span class="op-desc">Obtener producto por ID (Query)</span>
                            </div>
                        </div>
                        <div class="op-body" id="prod-id">
                            <div class="form-section">
                                <div class="form-section-title">Parámetros de Ruta</div>
                                <div class="form-group" style="max-width: 200px;">
                                    <label>id (int)*</label>
                                    <input type="number" id="prod-id-input" class="form-input" value="1">
                                </div>
                            </div>
                            <button class="btn-execute" onclick="getProductoById()">Ejecutar Petición</button>
                            <div class="response-area" id="prod-id-resp"></div>
                        </div>
                    </div>

                    <!-- POST /productos/add -->
                    <div class="op-block op-post">
                        <div class="op-header" onclick="toggleBlock('prod-add')">
                            <div class="op-summary">
                                <span class="method-badge bg-post">POST</span>
                                <span class="op-path">/productos/add</span>
                                <span class="op-desc">Crear nuevo producto (Command)</span>
                            </div>
                        </div>
                        <div class="op-body" id="prod-add">
                            <div class="form-section">
                                <div class="form-section-title">Datos Netos del Producto (Request Body)</div>
                                <div class="form-grid">
                                    <div class="form-group">
                                        <label>Nombre*</label>
                                        <input type="text" id="add-prod-nombre" class="form-input" value="Auriculares Bluetooth Sony" placeholder="Ej: Laptop...">
                                    </div>
                                    <div class="form-group">
                                        <label>Categoría*</label>
                                        <input type="text" id="add-prod-cat" class="form-input" value="Audio" placeholder="Ej: Periféricos...">
                                    </div>
                                    <div class="form-group">
                                        <label>Precio ($)*</label>
                                        <input type="number" step="0.01" id="add-prod-precio" class="form-input" value="199.99">
                                    </div>
                                    <div class="form-group">
                                        <label>Stock*</label>
                                        <input type="number" id="add-prod-stock" class="form-input" value="25">
                                    </div>
                                    <div class="form-group" style="grid-column: 1 / -1;">
                                        <label>Descripción</label>
                                        <input type="text" id="add-prod-desc" class="form-input" value="Cancelación activa de ruido con batería de 30 horas">
                                    </div>
                                </div>
                            </div>
                            <button class="btn-execute" onclick="addProductoSubmit()">Ejecutar (Crear Producto)</button>
                            <div class="response-area" id="prod-add-resp"></div>
                        </div>
                    </div>

                    <!-- PUT /productos/update -->
                    <div class="op-block op-put">
                        <div class="op-header" onclick="toggleBlock('prod-upd')">
                            <div class="op-summary">
                                <span class="method-badge bg-put">PUT</span>
                                <span class="op-path">/productos/update</span>
                                <span class="op-desc">Actualizar producto existente (Command)</span>
                            </div>
                        </div>
                        <div class="op-body" id="prod-upd">
                            <div class="form-section">
                                <div class="form-section-title">Datos a Actualizar (Request Body)</div>
                                <div class="form-grid">
                                    <div class="form-group">
                                        <label>ID a modificar*</label>
                                        <input type="number" id="upd-prod-id" class="form-input" value="1">
                                    </div>
                                    <div class="form-group">
                                        <label>Nuevo Nombre*</label>
                                        <input type="text" id="upd-prod-nombre" class="form-input" value="Laptop ThinkPad T14 Gen 4">
                                    </div>
                                    <div class="form-group">
                                        <label>Categoría*</label>
                                        <input type="text" id="upd-prod-cat" class="form-input" value="Computación">
                                    </div>
                                    <div class="form-group">
                                        <label>Precio ($)*</label>
                                        <input type="number" step="0.01" id="upd-prod-precio" class="form-input" value="1350.00">
                                    </div>
                                    <div class="form-group">
                                        <label>Stock*</label>
                                        <input type="number" id="upd-prod-stock" class="form-input" value="12">
                                    </div>
                                    <div class="form-group" style="grid-column: 1 / -1;">
                                        <label>Descripción</label>
                                        <input type="text" id="upd-prod-desc" class="form-input" value="Laptop actualizada con pantalla OLED">
                                    </div>
                                </div>
                            </div>
                            <button class="btn-execute" onclick="updateProductoSubmit()">Ejecutar (Actualizar Producto)</button>
                            <div class="response-area" id="prod-upd-resp"></div>
                        </div>
                    </div>

                    <!-- DELETE /productos/delete/{id} -->
                    <div class="op-block op-del">
                        <div class="op-header" onclick="toggleBlock('prod-del')">
                            <div class="op-summary">
                                <span class="method-badge bg-del">DELETE</span>
                                <span class="op-path">/productos/delete/{id}</span>
                                <span class="op-desc">Eliminar producto (Command)</span>
                            </div>
                        </div>
                        <div class="op-body" id="prod-del">
                            <div class="form-section">
                                <div class="form-section-title">ID del Producto a Eliminar</div>
                                <div class="form-group" style="max-width: 200px;">
                                    <label>id (int)*</label>
                                    <input type="number" id="del-prod-id" class="form-input" value="4">
                                </div>
                            </div>
                            <button class="btn-execute" style="background:#dc2626;" onclick="deleteProductoSubmit()">Ejecutar (Eliminar)</button>
                            <div class="response-area" id="prod-del-resp"></div>
                        </div>
                    </div>
                </div>


                <!-- SECCIÓN CLIENTES -->
                <div class="tag-section">
                    <div class="tag-title">
                        <span>👥 Clientes</span>
                        <span style="font-size: 0.85rem; font-weight: 400; color: var(--text-muted);">(Operaciones CQRS para Clientes con persistencia en <code>data/clientes.json</code>)</span>
                    </div>

                    <!-- GET /clientes -->
                    <div class="op-block op-get">
                        <div class="op-header" onclick="toggleBlock('cli-all')">
                            <div class="op-summary">
                                <span class="method-badge bg-get">GET</span>
                                <span class="op-path">/clientes</span>
                                <span class="op-desc">Listar todos los clientes (Query)</span>
                            </div>
                        </div>
                        <div class="op-body" id="cli-all">
                            <p style="color:#94a3b8; font-size:0.85rem; margin-bottom:12px;">Obtiene la lista completa de clientes registrados.</p>
                            <button class="btn-execute" onclick="executeCall('/clientes', 'GET', null, 'cli-all-resp')">Ejecutar Petición</button>
                            <div class="response-area" id="cli-all-resp"></div>
                        </div>
                    </div>

                    <!-- GET /clientes/{id} -->
                    <div class="op-block op-get">
                        <div class="op-header" onclick="toggleBlock('cli-id')">
                            <div class="op-summary">
                                <span class="method-badge bg-get">GET</span>
                                <span class="op-path">/clientes/{id}</span>
                                <span class="op-desc">Obtener cliente por ID (Query)</span>
                            </div>
                        </div>
                        <div class="op-body" id="cli-id">
                            <div class="form-section">
                                <div class="form-section-title">Parámetros de Ruta</div>
                                <div class="form-group" style="max-width: 200px;">
                                    <label>id (int)*</label>
                                    <input type="number" id="cli-id-input" class="form-input" value="1">
                                </div>
                            </div>
                            <button class="btn-execute" onclick="getClienteById()">Ejecutar Petición</button>
                            <div class="response-area" id="cli-id-resp"></div>
                        </div>
                    </div>

                    <!-- POST /clientes/add -->
                    <div class="op-block op-post">
                        <div class="op-header" onclick="toggleBlock('cli-add')">
                            <div class="op-summary">
                                <span class="method-badge bg-post">POST</span>
                                <span class="op-path">/clientes/add</span>
                                <span class="op-desc">Crear nuevo cliente (Command)</span>
                            </div>
                        </div>
                        <div class="op-body" id="cli-add">
                            <div class="form-section">
                                <div class="form-section-title">Datos Netos del Cliente (Request Body)</div>
                                <div class="form-grid">
                                    <div class="form-group">
                                        <label>Razón Social*</label>
                                        <input type="text" id="add-cli-razon" class="form-input" value="TECH SOLUTIONS CORP" placeholder="Razón social...">
                                    </div>
                                    <div class="form-group">
                                        <label>Dirección*</label>
                                        <input type="text" id="add-cli-dir" class="form-input" value="Calle 100 # 15-20" placeholder="Dirección...">
                                    </div>
                                    <div class="form-group">
                                        <label>Email*</label>
                                        <input type="email" id="add-cli-email" class="form-input" value="contacto@techsolutions.com" placeholder="Email...">
                                    </div>
                                    <div class="form-group">
                                        <label>Móvil*</label>
                                        <input type="text" id="add-cli-movil" class="form-input" value="300-123-4567" placeholder="Teléfono...">
                                    </div>
                                </div>
                            </div>
                            <button class="btn-execute" onclick="addClienteSubmit()">Ejecutar (Crear Cliente)</button>
                            <div class="response-area" id="cli-add-resp"></div>
                        </div>
                    </div>

                    <!-- PUT /clientes/update -->
                    <div class="op-block op-put">
                        <div class="op-header" onclick="toggleBlock('cli-upd')">
                            <div class="op-summary">
                                <span class="method-badge bg-put">PUT</span>
                                <span class="op-path">/clientes/update</span>
                                <span class="op-desc">Actualizar cliente existente (Command)</span>
                            </div>
                        </div>
                        <div class="op-body" id="cli-upd">
                            <div class="form-section">
                                <div class="form-section-title">Datos a Actualizar (Request Body)</div>
                                <div class="form-grid">
                                    <div class="form-group">
                                        <label>ID a modificar*</label>
                                        <input type="number" id="upd-cli-id" class="form-input" value="1">
                                    </div>
                                    <div class="form-group">
                                        <label>Razón Social*</label>
                                        <input type="text" id="upd-cli-razon" class="form-input" value="SOLITUX INTERNACIONAL SAS">
                                    </div>
                                    <div class="form-group">
                                        <label>Dirección*</label>
                                        <input type="text" id="upd-cli-dir" class="form-input" value="Av. San Juan 5555">
                                    </div>
                                    <div class="form-group">
                                        <label>Email*</label>
                                        <input type="email" id="upd-cli-email" class="form-input" value="soporte@solitux.com">
                                    </div>
                                    <div class="form-group">
                                        <label>Móvil*</label>
                                        <input type="text" id="upd-cli-movil" class="form-input" value="11-9988-7766">
                                    </div>
                                </div>
                            </div>
                            <button class="btn-execute" onclick="updateClienteSubmit()">Ejecutar (Actualizar Cliente)</button>
                            <div class="response-area" id="cli-upd-resp"></div>
                        </div>
                    </div>

                    <!-- DELETE /clientes/delete/{id} -->
                    <div class="op-block op-del">
                        <div class="op-header" onclick="toggleBlock('cli-del')">
                            <div class="op-summary">
                                <span class="method-badge bg-del">DELETE</span>
                                <span class="op-path">/clientes/delete/{id}</span>
                                <span class="op-desc">Eliminar cliente (Command)</span>
                            </div>
                        </div>
                        <div class="op-body" id="cli-del">
                            <div class="form-section">
                                <div class="form-section-title">ID del Cliente a Eliminar</div>
                                <div class="form-group" style="max-width: 200px;">
                                    <label>id (int)*</label>
                                    <input type="number" id="del-cli-id" class="form-input" value="3">
                                </div>
                            </div>
                            <button class="btn-execute" style="background:#dc2626;" onclick="deleteClienteSubmit()">Ejecutar (Eliminar)</button>
                            <div class="response-area" id="cli-del-resp"></div>
                        </div>
                    </div>
                </div>
            </div>

            <script>
                function toggleBlock(id) {
                    const block = document.getElementById(id);
                    block.classList.toggle('active');
                }

                async function executeCall(url, method, body, respContainerId) {
                    const container = document.getElementById(respContainerId);
                    container.style.display = 'block';
                    container.innerHTML = '<span class="status-badge status-loading">⏳ Enviando petición...</span>';

                    const startTime = performance.now();
                    try {
                        const options = { method: method };
                        if (body) {
                            options.headers = { 'Content-Type': 'application/json' };
                            options.body = JSON.stringify(body);
                        }

                        // Generar curl equivalente
                        let curlCmd = `curl -X '${method}' '${window.location.origin}${url}'`;
                        if (body) {
                            curlCmd += ` -H 'Content-Type: application/json' -d '${JSON.stringify(body)}'`;
                        }

                        const res = await fetch(url, options);
                        const duration = Math.round(performance.now() - startTime);
                        const statusClass = res.ok ? 'status-2xx' : 'status-4xx';

                        let bodyText = '';
                        if (res.status === 204) {
                            bodyText = '// 204 No Content - Operación exitosa sin cuerpo';
                        } else {
                            const raw = await res.text();
                            try {
                                const parsed = JSON.parse(raw);
                                bodyText = JSON.stringify(parsed, null, 2);
                            } catch (e) {
                                bodyText = raw;
                            }
                        }

                        container.innerHTML = `
                            <div class="curl-snippet"><strong>cURL:</strong> ${curlCmd}</div>
                            <div class="response-header">
                                <span class="status-badge ${statusClass}">HTTP ${res.status} ${res.statusText}</span>
                                <span style="font-size:0.78rem; color:#94a3b8;">${duration} ms</span>
                            </div>
                            <pre class="code-display">${escapeHtml(bodyText)}</pre>
                        `;
                    } catch (err) {
                        container.innerHTML = `
                            <div class="response-header">
                                <span class="status-badge status-4xx">Error de Red</span>
                            </div>
                            <pre class="code-display">${escapeHtml(err.toString())}</pre>
                        `;
                    }
                }

                function escapeHtml(str) {
                    return str.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
                }

                // PRODUCTOS HELPERS
                function getProductoById() {
                    const id = document.getElementById('prod-id-input').value;
                    executeCall(`/productos/${id}`, 'GET', null, 'prod-id-resp');
                }

                function addProductoSubmit() {
                    const body = {
                        nombre: document.getElementById('add-prod-nombre').value,
                        categoria: document.getElementById('add-prod-cat').value,
                        precio: parseFloat(document.getElementById('add-prod-precio').value) || 0,
                        stock: parseInt(document.getElementById('add-prod-stock').value) || 0,
                        descripcion: document.getElementById('add-prod-desc').value
                    };
                    executeCall('/productos/add', 'POST', body, 'prod-add-resp');
                }

                function updateProductoSubmit() {
                    const body = {
                        id: parseInt(document.getElementById('upd-prod-id').value) || 0,
                        nombre: document.getElementById('upd-prod-nombre').value,
                        categoria: document.getElementById('upd-prod-cat').value,
                        precio: parseFloat(document.getElementById('upd-prod-precio').value) || 0,
                        stock: parseInt(document.getElementById('upd-prod-stock').value) || 0,
                        descripcion: document.getElementById('upd-prod-desc').value
                    };
                    executeCall('/productos/update', 'PUT', body, 'prod-upd-resp');
                }

                function deleteProductoSubmit() {
                    const id = document.getElementById('del-prod-id').value;
                    executeCall(`/productos/delete/${id}`, 'DELETE', null, 'prod-del-resp');
                }

                // CLIENTES HELPERS
                function getClienteById() {
                    const id = document.getElementById('cli-id-input').value;
                    executeCall(`/clientes/${id}`, 'GET', null, 'cli-id-resp');
                }

                function addClienteSubmit() {
                    const body = {
                        razon: document.getElementById('add-cli-razon').value,
                        direccion: document.getElementById('add-cli-dir').value,
                        email: document.getElementById('add-cli-email').value,
                        movil: document.getElementById('add-cli-movil').value
                    };
                    executeCall('/clientes/add', 'POST', body, 'cli-add-resp');
                }

                function updateClienteSubmit() {
                    const body = {
                        id: parseInt(document.getElementById('upd-cli-id').value) || 0,
                        razon: document.getElementById('upd-cli-razon').value,
                        direccion: document.getElementById('upd-cli-dir').value,
                        email: document.getElementById('upd-cli-email').value,
                        movil: document.getElementById('upd-cli-movil').value
                    };
                    executeCall('/clientes/update', 'PUT', body, 'cli-upd-resp');
                }

                function deleteClienteSubmit() {
                    const id = document.getElementById('del-cli-id').value;
                    executeCall(`/clientes/delete/${id}`, 'DELETE', null, 'cli-del-resp');
                }
            </script>
        </body>
        </html>
        """;
    }
}
