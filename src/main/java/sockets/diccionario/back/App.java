package sockets.diccionario.back;

import lombok.extern.java.Log;
import sockets.diccionario.back.api.MemoryServerApi;
import sockets.diccionario.back.api.ServerApi;
import sockets.diccionario.back.constant.EnvConstants;
import sockets.diccionario.back.socket.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log
public class App {
    public static void main(String[] args) {
        ServerApi api = new MemoryServerApi();

        ExecutorService poolHilos = null;
        try (ServerSocket serverSocket = new ServerSocket(EnvConstants.PUERTO, EnvConstants.MAXIMAS_CONEXIONES_ACEPTADAS)) {

            serverSocket.setSoTimeout(Duration.ofMinutes(EnvConstants.TIMEOUT_MINS).toMillisPart());
            poolHilos = Executors.newFixedThreadPool(EnvConstants.HILOS_MAXIMOS);

            while (true) {
                poolHilos.execute(new ServerHandler(api.obtenerFunciones(), serverSocket.accept()));
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

