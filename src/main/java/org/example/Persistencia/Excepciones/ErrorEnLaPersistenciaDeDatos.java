package org.example.Persistencia.Excepciones;

import org.example.GUI.PaneException.ERROR;

public class ErrorEnLaPersistenciaDeDatos extends RuntimeException{
    public ErrorEnLaPersistenciaDeDatos(){
        ERROR.mostrarError("No se pudieron persistir los datos");
    }
}
