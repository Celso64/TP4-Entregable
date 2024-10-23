package sockets.diccionario.back.api;

import java.util.Map;
import java.util.function.Function;

public interface ServerApi {

    String listar(String a);

    String insertar(String parametros);

    String modificar(String parametros);

    String eliminar(String palabra);

    String obtener(String palabra);

    Map<String, Function<String, String>> obtenerFunciones();
}
