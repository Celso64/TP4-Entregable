package sockets.diccionario.front.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Vista {

    Terminal terminal;
    TerminalScreen screen;
    TextColor fore, back;

    public Vista() throws IOException {
        try {
            this.terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.fore = TextColor.ANSI.WHITE;
            this.back = TextColor.ANSI.BLACK;

            this.screen.startScreen();

            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

            // Crear una ventana
            BasicWindow window = new BasicWindow("Mi App Terminal");
            window.setComponent(new Label("¡Hola, mundo!"));

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
