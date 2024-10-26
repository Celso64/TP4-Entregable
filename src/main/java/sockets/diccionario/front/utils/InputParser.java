package sockets.diccionario.front.utils;

import sockets.diccionario.front.config.ClienteConfig;

import java.util.Scanner;

public class InputParser {
    public static String generarComando(String comando, Scanner scanner) {
        String inputLine = null;
        switch (comando) {
            case "listar":
                inputLine = "listar-";
                break;
            case "insertar":
                System.out.println("Ingrese la palabra y el significado, separados por un espacio:");
                String palabraYSignificado = scanner.nextLine();
                inputLine = "insertar-" + palabraYSignificado.replace(" ", ClienteConfig.SEPARADOR_PARAM);
                break;
            case "modificar":
                System.out.println("Ingrese la palabra y el nuevo significado, separados por un espacio:");
                String palabraYNuevoSignificado = scanner.nextLine();
                inputLine = "modificar-" + palabraYNuevoSignificado.replace(" ", ClienteConfig.SEPARADOR_PARAM);
                break;
            case "eliminar":
                System.out.println("Ingrese la palabra a eliminar:");
                String palabraEliminar = scanner.nextLine();
                inputLine = "eliminar-" + palabraEliminar;
                break;
            case "obtener":
                System.out.println("Ingrese la palabra para obtener el significado:");
                String palabraObtener = scanner.nextLine();
                inputLine = "obtener-" + palabraObtener;
                break;
            default:
                System.out.println("Comando no reconocido.");
        }
        return inputLine;
    }
}

