package org.example.Persistencia.Excepciones;

import org.example.GUI.PaneException.ERROR;

public class ContactoNoEncontrado extends RuntimeException{
    public ContactoNoEncontrado(){
        ERROR.mostrarError("No se encontraron resultados");
    }
}
