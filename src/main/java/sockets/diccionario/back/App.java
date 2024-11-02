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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Log
public class App {

    private static volatile boolean running = true;

    public static void main(String[] args) {

        ServerApi api = new MemoryServerApi();
        ExecutorService poolHilos = Executors.newFixedThreadPool(EnvConstants.HILOS_MAXIMOS);

        try (ServerSocket serverSocket = new ServerSocket(EnvConstants.PUERTO, EnvConstants.MAXIMAS_CONEXIONES_ACEPTADAS)) {

            serverSocket.setSoTimeout(Duration.ofMinutes(EnvConstants.TIMEOUT_MINS).toMillisPart());

            Thread cierre = new Thread(() -> {
                log.info("Iniciando cierre de la App");
                running = false;

                try {
                    serverSocket.close();
                    log.info("Servicio de Sockets cerrado.");
                } catch (IOException e) {
                    log.severe("ERROR: " + e.getMessage());
                }

                poolHilos.shutdown();
                try {
                    if (!poolHilos.awaitTermination(30, TimeUnit.SECONDS)) {
                        poolHilos.shutdownNow();
                    }
                    log.info("Servicio de Hilos cerrado.");
                } catch (InterruptedException e) {
                    poolHilos.shutdownNow();
                    Thread.currentThread().interrupt();
                }

                log.info("App cerrada.");
            });

            Runtime.getRuntime().addShutdownHook(cierre);

            log.info("App Iniciada");
            while (running) {
                poolHilos.execute(new ServerHandler(api.obtenerFunciones(), serverSocket.accept()));
            }
        } catch (SocketTimeoutException timeoutException) {
            log.info(timeoutException.getMessage());
        } catch (IOException e) {
            log.severe(e.getMessage());
        }
    }
}

