package sockets.diccionario.front.api;

import java.util.List;

public interface ClienteApi {

    List<String> listar();

    String insertar(String palabra, String significado);

    String modificar(String palabra, String significado);

    String eliminar(String palabra);

    String obtener(String palabra);
}
