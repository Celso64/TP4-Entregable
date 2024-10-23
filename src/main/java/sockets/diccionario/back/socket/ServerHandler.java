package sockets.diccionario.back.socket;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Log
public class ServerHandler implements Runnable {

    final Map<String, Function<String, String>> funciones;
    final Socket clientSocket;
    PrintWriter out;
    BufferedReader in;


    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                String[] partes = inputLine.split("-");

                if (funciones.containsKey(partes[0])) {
                    out.println(funciones.get(partes[0]).apply(partes[1]));
                } else {
                    out.println("ERROR");
                }
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }
}
