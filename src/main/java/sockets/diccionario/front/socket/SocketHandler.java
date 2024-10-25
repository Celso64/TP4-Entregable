package sockets.diccionario.front.socket;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Log
public class SocketHandler implements AutoCloseable {

    Integer puerto;
    Socket socket;
    PrintWriter out;
    BufferedReader in;

    public SocketHandler() {
        this.puerto = Integer.parseInt(System.getenv("puerto"));
        try {
            this.socket = new Socket("localhost", puerto);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            log.severe(e.getMessage());
        }

    }

    public String operar(String request) {
        try {
            out.println(request);
            String response = in.readLine();
            log.info(response);

            return response;
        } catch (IOException e) {
            log.severe(e.getMessage());
            return "Error";
        }
    }

    @Override
    public void close() throws Exception {
        socket.close();
        out.close();
        in.close();
    }
}


