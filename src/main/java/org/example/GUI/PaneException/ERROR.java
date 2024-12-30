package org.example.GUI.PaneException;

import javax.swing.*;

public class ERROR  {
    public static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                null,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
