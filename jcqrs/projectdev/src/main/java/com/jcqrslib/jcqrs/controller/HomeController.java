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
            <title>JCQRS - Patrón CQRS con Terminal Web</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <style>
                body { background-color: #0f172a; color: #e2e8f0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
                .hero { background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%); padding: 1.75rem 1rem; border-bottom: 1px solid #334155; }
                .card { background-color: #1e293b; border: 1px solid #334155; border-radius: 0.75rem; color: #e2e8f0; }
                
                /* Terminal Styles */
                .terminal-window {
                    background-color: #000000;
                    border: 1px solid #334155;
                    border-radius: 0.5rem;
                    box-shadow: 0 8px 24px rgba(0,0,0,0.5);
                    overflow: hidden;
                    font-family: 'Consolas', 'Courier New', monospace;
                }
                .terminal-header {
                    background-color: #1e293b;
                    padding: 8px 14px;
                    display: flex;
                    align-items: center;
                    border-bottom: 1px solid #334155;
                }
                .terminal-btn { width: 12px; height: 12px; border-radius: 50%; display: inline-block; margin-right: 6px; }
                .btn-red { background-color: #ff5f56; }
                .btn-yellow { background-color: #ffbd2e; }
                .btn-green { background-color: #27c93f; }
                .terminal-title { margin-left: 10px; font-size: 0.85rem; color: #94a3b8; }
                
                .terminal-body {
                    padding: 14px;
                    height: 480px;
                    overflow-y: auto;
                    font-size: 0.92rem;
                    line-height: 1.5;
                }
                .term-prompt { color: #38bdf8; font-weight: bold; }
                .term-cmd { color: #f8fafc; font-weight: 600; }
                .term-out { color: #cbd5e1; margin-bottom: 8px; white-space: pre-wrap; word-break: break-all; }
                .term-success { color: #4ade80; }
                .term-error { color: #f87171; }
                .term-info { color: #38bdf8; }
                .term-warn { color: #facc15; }

                .terminal-input-line {
                    display: flex;
                    align-items: center;
                    margin-top: 4px;
                }
                .terminal-input {
                    background: transparent;
                    border: none;
                    outline: none;
                    color: #f8fafc;
                    font-family: inherit;
                    font-size: inherit;
                    flex-grow: 1;
                    margin-left: 8px;
                }
                .btn-action { font-size: 0.8rem; }
            </style>
        </head>
        <body>
            <div class="hero text-center">
                <h2 class="fw-bold text-white mb-1">🚀 JCQRS - Terminal Web & Consola Interactiva</h2>
                <p class="text-secondary mb-0 small">Ejecuta comandos CLI, peticiones <code>curl</code> y operaciones CQRS directamente desde la interfaz web</p>
            </div>

            <div class="container-fluid px-4 py-3">
                <div class="row g-3">
                    <!-- Columna de Botones / Atajos Rápidos -->
                    <div class="col-lg-4">
                        <!-- Card Productos -->
                        <div class="card p-3 mb-3">
                            <h5 class="text-info fw-bold mb-2">📦 Comandos Rápidos: Productos</h5>
                            <p class="text-secondary small mb-2">Haz clic para escribir y ejecutar el comando en la terminal:</p>
                            <div class="d-flex flex-column gap-2">
                                <button class="btn btn-outline-info btn-sm text-start btn-action" onclick="typeAndRun('curl http://localhost:9898/productos')">
                                    <code>curl http://localhost:9898/productos</code>
                                </button>
                                <button class="btn btn-outline-info btn-sm text-start btn-action" onclick="typeAndRun('curl http://localhost:9898/productos/1')">
                                    <code>curl http://localhost:9898/productos/1</code>
                                </button>
                                <button class="btn btn-outline-success btn-sm text-start btn-action" onclick="typeAndRun('curl -X POST http://localhost:9898/productos/add -d {\\\"nombre\\\":\\\"Mouse MX Master\\\",\\\"categoria\\\":\\\"Perifericos\\\",\\\"precio\\\":99.99,\\\"stock\\\":25,\\\"descripcion\\\":\\\"Mouse ergonomico\\\"}')">
                                    <code>curl -X POST /productos/add ...</code>
                                </button>
                                <button class="btn btn-outline-warning btn-sm text-start btn-action" onclick="typeAndRun('curl -X PUT http://localhost:9898/productos/update -d {\\\"id\\\":4,\\\"nombre\\\":\\\"Mouse MX Master Pro\\\",\\\"categoria\\\":\\\"Perifericos\\\",\\\"precio\\\":119.99,\\\"stock\\\":20,\\\"descripcion\\\":\\\"Edicion Pro\\\"}')">
                                    <code>curl -X PUT /productos/update ...</code>
                                </button>
                                <button class="btn btn-outline-danger btn-sm text-start btn-action" onclick="typeAndRun('curl -X DELETE http://localhost:9898/productos/delete/4')">
                                    <code>curl -X DELETE /productos/delete/4</code>
                                </button>
                            </div>
                        </div>

                        <!-- Card Clientes -->
                        <div class="card p-3 mb-3">
                            <h5 class="text-success fw-bold mb-2">👥 Comandos Rápidos: Clientes</h5>
                            <div class="d-flex flex-column gap-2">
                                <button class="btn btn-outline-success btn-sm text-start btn-action" onclick="typeAndRun('curl http://localhost:9898/clientes')">
                                    <code>curl http://localhost:9898/clientes</code>
                                </button>
                                <button class="btn btn-outline-success btn-sm text-start btn-action" onclick="typeAndRun('curl http://localhost:9898/clientes/1')">
                                    <code>curl http://localhost:9898/clientes/1</code>
                                </button>
                            </div>
                        </div>

                        <!-- Ayuda de Comandos -->
                        <div class="card p-3">
                            <h6 class="text-secondary fw-bold mb-2">💡 Comandos de la Terminal</h6>
                            <ul class="small text-secondary mb-0 ps-3">
                                <li><code>help</code>: Ver guía de comandos</li>
                                <li><code>curl &lt;url&gt;</code>: Ejecutar petición HTTP</li>
                                <li><code>clear</code>: Limpiar pantalla</li>
                                <li><code>status</code>: Estado del servidor</li>
                            </ul>
                        </div>
                    </div>

                    <!-- Columna de la Terminal Web Interactiva -->
                    <div class="col-lg-8">
                        <div class="terminal-window" onclick="document.getElementById('termInput').focus()">
                            <div class="terminal-header">
                                <span class="terminal-btn btn-red"></span>
                                <span class="terminal-btn btn-yellow"></span>
                                <span class="terminal-btn btn-green"></span>
                                <span class="terminal-title">jcqrs-bash -- 9898 (http)</span>
                            </div>
                            <div class="terminal-body" id="termBody">
                                <div class="term-out term-info">========================================================================
 💻 JCQRS Web Terminal v1.0 [Spring Boot 3.5.3 - Java 17]
 Escribe 'help' para ver los comandos soportados o usa 'curl'.
 Usa las flechas ARRIBA/ABAJO para navegar el historial de comandos.
========================================================================</div>
                                <div id="termHistory"></div>
                                <div class="terminal-input-line">
                                    <span class="term-prompt">jcqrs@server:~$</span>
                                    <input type="text" id="termInput" class="terminal-input" autofocus autocomplete="off" spellcheck="false">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <script>
                const termInput = document.getElementById('termInput');
                const termHistory = document.getElementById('termHistory');
                const termBody = document.getElementById('termBody');
                
                const commandHistory = [];
                let historyIndex = -1;

                termInput.addEventListener('keydown', function(e) {
                    if (e.key === 'Enter') {
                        const cmd = this.value.trim();
                        if (cmd) {
                            commandHistory.push(cmd);
                            historyIndex = commandHistory.length;
                            executeCommand(cmd);
                        } else {
                            printOutput('', '');
                        }
                        this.value = '';
                        termBody.scrollTop = termBody.scrollHeight;
                    } else if (e.key === 'ArrowUp') {
                        if (historyIndex > 0) {
                            historyIndex--;
                            this.value = commandHistory[historyIndex];
                        }
                        e.preventDefault();
                    } else if (e.key === 'ArrowDown') {
                        if (historyIndex < commandHistory.length - 1) {
                            historyIndex++;
                            this.value = commandHistory[historyIndex];
                        } else {
                            historyIndex = commandHistory.length;
                            this.value = '';
                        }
                        e.preventDefault();
                    }
                });

                function printOutput(cmd, outputHtml) {
                    const line = document.createElement('div');
                    line.innerHTML = `<div><span class="term-prompt">jcqrs@server:~$</span> <span class="term-cmd">${escapeHtml(cmd)}</span></div>${outputHtml}`;
                    termHistory.appendChild(line);
                    termBody.scrollTop = termBody.scrollHeight;
                }

                function escapeHtml(text) {
                    return text.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
                }

                function typeAndRun(cmd) {
                    termInput.value = cmd;
                    termInput.focus();
                    commandHistory.push(cmd);
                    historyIndex = commandHistory.length;
                    executeCommand(cmd);
                    termInput.value = '';
                }

                async function executeCommand(cmd) {
                    const trimmed = cmd.trim();
                    const parts = trimmed.split(/\\s+/);
                    const mainCmd = parts[0].toLowerCase();

                    if (mainCmd === 'clear') {
                        termHistory.innerHTML = '';
                        return;
                    }

                    if (mainCmd === 'help') {
                        const helpHtml = `<div class="term-out term-info">
Comandos disponibles:
  curl &lt;url&gt;                      Petición GET (ej: curl http://localhost:9898/productos)
  curl -X POST &lt;url&gt; -d &lt;json&gt;     Petición POST con cuerpo JSON
  curl -X PUT &lt;url&gt; -d &lt;json&gt;      Petición PUT con cuerpo JSON
  curl -X DELETE &lt;url&gt;             Petición DELETE
  status                            Verifica el estado del servicio y puertos
  clear                             Limpia la pantalla de la terminal
  help                              Muestra esta ayuda</div>`;
                        printOutput(cmd, helpHtml);
                        return;
                    }

                    if (mainCmd === 'status') {
                        const statusHtml = `<div class="term-out term-success">
● jcqrs-service.service - CQRS Spring Boot Server
     Loaded: loaded (/app/app.jar)
     Active: active (running)
     Port:   9898 (Tomcat)
     Java:   OpenJDK 17
     Capa:   com.jcqrslib.jcqrs.data (static ArrayList)</div>`;
                        printOutput(cmd, statusHtml);
                        return;
                    }

                    if (mainCmd === 'curl') {
                        await handleCurl(cmd);
                        return;
                    }

                    printOutput(cmd, `<div class="term-out term-error">Comando no reconocido: '${escapeHtml(mainCmd)}'. Escribe 'help' para ver los comandos soportados.</div>`);
                }

                async function handleCurl(cmd) {
                    let method = 'GET';
                    let url = '';
                    let bodyData = null;

                    // Parse basic curl command flags (-X, -d, URL)
                    const xIndex = cmd.indexOf('-X ');
                    if (xIndex !== -1) {
                        const afterX = cmd.substring(xIndex + 3).trim();
                        method = afterX.split(/\\s+/)[0].toUpperCase();
                    }

                    const dIndex = cmd.indexOf('-d ');
                    if (dIndex !== -1) {
                        let rest = cmd.substring(dIndex + 3).trim();
                        let rawJson = '';
                        if (rest.startsWith('{') || rest.startsWith('"{') || rest.startsWith("'{")) {
                            const startChar = rest.charAt(0);
                            if (startChar === '"' || startChar === "'") {
                                const endQuote = rest.lastIndexOf(startChar);
                                rawJson = rest.substring(1, endQuote);
                            } else {
                                rawJson = rest;
                            }
                        }
                        try {
                            // Limpiar escapes si existen
                            const unescaped = rawJson.replace(/\\\\"/g, '"');
                            bodyData = JSON.parse(unescaped);
                        } catch (e) {
                            printOutput(cmd, `<div class="term-out term-error">Error parseando JSON del parámetro -d: ${escapeHtml(e.message)}</div>`);
                            return;
                        }
                    }

                    // Extract URL
                    const tokens = cmd.split(/\\s+/);
                    for (let token of tokens) {
                        if (token.startsWith('http://') || token.startsWith('https://') || token.startsWith('/')) {
                            url = token.replace(/['"]/g, '');
                            break;
                        }
                    }

                    if (!url) {
                        printOutput(cmd, `<div class="term-out term-error">Uso: curl [-X METHOD] &lt;URL&gt; [-d '&lt;JSON&gt;']</div>`);
                        return;
                    }

                    try {
                        const options = { method: method };
                        if (bodyData) {
                            options.headers = { 'Content-Type': 'application/json' };
                            options.body = JSON.stringify(bodyData);
                        }

                        const startTime = performance.now();
                        const response = await fetch(url, options);
                        const duration = Math.round(performance.now() - startTime);

                        const statusText = `HTTP/1.1 ${response.status} ${response.statusText} (${duration} ms)`;
                        const statusClass = response.ok ? 'term-success' : 'term-error';

                        if (response.status === 204) {
                            printOutput(cmd, `<div class="term-out"><span class="${statusClass}">${statusText}</span>\\n// 204 No Content (Operación ejecutada con éxito)</div>`);
                            return;
                        }

                        const text = await response.text();
                        let formattedBody = text;
                        try {
                            const parsed = JSON.parse(text);
                            formattedBody = JSON.stringify(parsed, null, 2);
                        } catch (err) {}

                        printOutput(cmd, `<div class="term-out"><span class="${statusClass}">${statusText}</span>\\n<span class="text-secondary">&lt; Content-Type: ${response.headers.get('content-type') || 'application/json'}</span>\\n\\n${escapeHtml(formattedBody)}</div>`);
                    } catch (err) {
                        printOutput(cmd, `<div class="term-out term-error">curl: (7) Failed to connect: ${escapeHtml(err.message)}</div>`);
                    }
                }
            </script>
        </body>
        </html>
        """;
    }
}
