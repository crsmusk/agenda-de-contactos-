package org.example.Persistencia.Excepciones;

import org.example.GUI.PaneException.ERROR;

public class ErrorEnLaConsulta extends RuntimeException{
    public ErrorEnLaConsulta(){
        ERROR.mostrarError("No se pudo realizar la consulta");
    }
}
