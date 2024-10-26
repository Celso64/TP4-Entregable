package sockets.diccionario.front.controller;

import java.io.*;
import java.net.Socket;

public class ClienteController implements Closeable {
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;

    public ClienteController(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String enviarComando(String comando) throws IOException {
        out.println(comando);
        return in.readLine();
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}

