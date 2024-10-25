package sockets.diccionario.front;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import lombok.extern.java.Log;

import java.io.IOException;

@Log
public class ClientApp {
    public static void main(String[] args) throws IOException {
        try {
            // Crear terminal
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            TerminalScreen screen = new TerminalScreen(terminal);
            screen.startScreen();

            // Crear un GUI
            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

            // Crear una ventana
            BasicWindow window = new BasicWindow("Mi App Terminal");
            window.setComponent(new Label("¡Hola, mundo!"));

            // Añadir la ventana a la GUI
            gui.addWindow(window);

            // Mostrar GUI
            gui.setActiveWindow(window);
            screen.refresh();

            // Mantener la aplicación en ejecución
            while (true) {
                // Esperar a eventos
                gui.getScreen().readInput();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
