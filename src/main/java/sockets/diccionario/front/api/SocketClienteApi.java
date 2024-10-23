package sockets.diccionario.front.api;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.net.Socket;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SocketClienteApi implements ClienteApi {

    Socket socket;

    @Override
    public List<String> listar() {
        return List.of();
    }

    @Override
    public String insertar(String palabra, String significado) {
        return "0";
    }

    @Override
    public String modificar(String palabra, String significado) {
        return "0";
    }

    @Override
    public String eliminar(String palabra) {
        return "0";
    }

    @Override
    public String obtener(String palabra) {
        return "";
    }
}
