package sockets.diccionario.back.api;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import sockets.diccionario.back.constant.BodyConstant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Log
public class MemoryServerApi implements ServerApi {

    ConcurrentMap<String, String> diccionario = new ConcurrentHashMap<>();

    static String ok = "OK";
    static String error = "ERROR";

    @Override
    public String listar(String a) {
        String res = String.join(BodyConstant.UNION_LISTA, diccionario.keySet());
        return res;
    }

    @Override
    public String insertar(String parametros) {
        String[] params = parametros.split(BodyConstant.SEPARADOR_PARAM);
        if (diccionario.containsKey(parametros) || params.length != 2) {
            return error;
        }
        diccionario.put(params[0], params[1]);
        return ok;
    }

    @Override
    public String modificar(String parametros) {
        String[] params = parametros.split(BodyConstant.SEPARADOR_PARAM);
        if (!diccionario.containsKey(parametros) || params.length != 2) {
            return error;
        }
        diccionario.put(params[0], params[1]);
        return ok;
    }

    @Override
    public String eliminar(String palabra) {
        if (diccionario.containsKey(palabra)) {
            diccionario.remove(palabra);
            return ok;
        }
        return error;
    }

    @Override
    public String obtener(String palabra) {
        if (diccionario.containsKey(palabra)) {
            return diccionario.get(palabra);
        }
        return error;
    }

    public Map<String, Function<String, String>> obtenerFunciones() {
        return Map.of(
                "listar", this::listar,
                "insertar", this::insertar,
                "modificar", this::modificar,
                "eliminar", this::eliminar,
                "obtener", this::obtener
        );
    }
}
