package org.example.Persistencia.Excepciones;

import org.example.GUI.PaneException.ERROR;

public class ErrorAlEstablecerLaConexion extends RuntimeException{
    public ErrorAlEstablecerLaConexion(){
        ERROR.mostrarError("Hubo un error en la conexion ");
    }
}
