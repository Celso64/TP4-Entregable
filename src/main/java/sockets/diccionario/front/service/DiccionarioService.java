package sockets.diccionario.front.service;

import sockets.diccionario.front.config.ClienteConfig;
import sockets.diccionario.front.controller.ClienteController;
import sockets.diccionario.front.utils.InputParser;

import java.io.IOException;
import java.util.Scanner;

public class DiccionarioService {
    private final ClienteController controller;

    public DiccionarioService(ClienteController controller) {
        this.controller = controller;
    }

    public void procesarComando(String comando, Scanner scanner) {
        try {
            String inputLine = InputParser.generarComando(comando, scanner);
            if (inputLine == null) {
                System.out.println("Comando no válido. Inténtelo de nuevo.");
                return;
            }

            String respuesta = controller.enviarComando(inputLine);
            System.out.println("Respuesta del servidor: " + respuesta);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
