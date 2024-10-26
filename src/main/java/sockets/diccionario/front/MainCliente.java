package sockets.diccionario.front;

import sockets.diccionario.front.controller.ClienteController;
import sockets.diccionario.front.service.DiccionarioService;
import sockets.diccionario.front.config.ClienteConfig;
import java.util.Scanner;

public class MainCliente {
    public static void main(String[] args) {
        try (ClienteController controller = new ClienteController(ClienteConfig.HOST, ClienteConfig.PORT);
             Scanner scanner = new Scanner(System.in)) {

            DiccionarioService service = new DiccionarioService(controller);

            System.out.println("Cliente de Diccionario Distribuido. Ingrese un comando (listar, insertar, modificar, eliminar, obtener) o 'salir' para terminar:");

            while (true) {
                String comando = scanner.nextLine().trim().toLowerCase();
                if ("salir".equals(comando)) {
                    System.out.println("Finalizando conexión...");
                    break;
                }
                service.procesarComando(comando, scanner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

