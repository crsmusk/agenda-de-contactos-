package org.example;

import org.example.GUI.Ventanas.VentanaPrincipal;
import org.example.Persistencia.DAO.ContactoDAO;
import org.example.Persistencia.Excepciones.ContactoNoEncontrado;
import org.example.Persistencia.Excepciones.ErrorAlEstablecerLaConexion;
import org.example.Persistencia.Excepciones.ErrorEnLaConsulta;
import org.example.Persistencia.Excepciones.ErrorEnLaPersistenciaDeDatos;
import org.example.Persistencia.Model.Entidades.Contacto;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        VentanaPrincipal ventana= new VentanaPrincipal();
        ventana.setVisible(true);
    }
}