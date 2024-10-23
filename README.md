# TP 4 - Sockets

## Como usarlo

### Requirimientos

- Java 17 o superior
- Maven

### Pasos

1. Abrir el terminal y situarse en el folder del proyecto.
2. Ejecutar `mvn compile` para compilar.
3. Ejecutar primero el servidor.
4. Ejecutar luego el cliente.
5. Usar la interfaz del cliente para hacer operaciones.

## ¿Qué es esto?

El TP4 de Sistemas Operativos.

Un servicio de diccionario, que se almacena en memoria. Con una parte
Servidor y otra Cliente, que se comunican por Sockets.

Soporta 4 operaciones:

### Listar

Lista todas las palabras en el diccionario.

### Insertar

Agrega una palabra y su significado al diccionario. Da error si ya existia.

### Modificar

Le modifica su significado a una palabra. Da error si la palabra no existe.

### Eliminar

Elimina una palabra y su significado. Da error si la palabra no existe.

### Obtener

Recibe una palabra y devuelve su significado. Da error si la palabra no existe.

## Partes

### Server

Un servidor TCP que escucha un puerto y
atiende varios clientes a la vez, cada
uno con su respectivo hilo.

#### Cosas que use

- ExecutorService: Para manejar varios hilos.
- ServerSockect: Para crear los sockects para atender varios clientes a la vez.

### Cliente

Un cliente que muestra un menu por terminal
para las operaciones y manda peticiones por
TCP al server.

#### Cosas que use

- Lanterna: Para la interfaz por terminal.

### Cosas que use en ambos

- Lombok: Para escribir clases menos verbosas.