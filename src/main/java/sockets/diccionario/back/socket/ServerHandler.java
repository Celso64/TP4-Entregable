package sockets.diccionario.back.socket;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import sockets.diccionario.back.constant.BodyConstant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Log
public class ServerHandler extends Thread {

    Map<String, Function<String, String>> funciones;
    Socket clientSocket;


    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] partes = inputLine.split(BodyConstant.SEPARADOR_COMANDO);

                String comando = partes[0].toLowerCase();
                String parametros = (partes.length == 2) ? partes[1] : " ";

                if (funciones.containsKey(comando)) {
                    var funcion = funciones.get(comando);
                    String resultado = funcion.apply(parametros);
                    out.println(resultado);
                } else {
                    out.println("ERROR");
                }
            }
            clientSocket.close();
        } catch (Exception e) {
            log.log(Level.SEVERE, Arrays.toString(e.getStackTrace()));
        }
    }
}
