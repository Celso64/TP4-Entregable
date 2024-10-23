package sockets.diccionario.back;

import lombok.extern.java.Log;
import sockets.diccionario.back.api.MemoryServerApi;
import sockets.diccionario.back.api.ServerApi;
import sockets.diccionario.back.socket.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log
public class App {
    public static void main(String[] args) {
        Integer puerto = 8090;
        Integer maximasConexionesAceptadas = 5;
        Integer hilosMaximos = 5;
        Integer minutosTimeout = 2;

        ServerApi api = new MemoryServerApi();

        ExecutorService poolHilos = null;
        try (ServerSocket serverSocket = new ServerSocket(puerto, maximasConexionesAceptadas)) {

            serverSocket.setSoTimeout(Duration.ofMinutes(minutosTimeout).toMillisPart());

            poolHilos = Executors.newFixedThreadPool(hilosMaximos);

            while (true) {
                Socket socket = serverSocket.accept();
                poolHilos.execute(new ServerHandler(api.obtenerFunciones(), socket));
            }
        } catch (SocketTimeoutException timeoutException) {
            log.info(timeoutException.getMessage());
        } catch (IOException e) {
            log.severe(e.getMessage());
        } finally {
            Objects.requireNonNull(poolHilos).shutdown();
        }
    }
}

