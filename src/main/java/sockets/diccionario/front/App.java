package sockets.diccionario.front;

import sockets.diccionario.front.api.ClienteApi;
import sockets.diccionario.front.api.SocketClienteApi;

import java.net.Socket;

public class App {

    public static void main(String[] args) {

        ClienteApi api = new SocketClienteApi(new Socket());
    }
}
