package org.example.Controlador;

import org.example.Persistencia.Model.Entidades.Contacto;
import org.example.Persistencia.Servicio.ContactoService;

import java.util.List;

public class AgendaControlador {
    ContactoService service=new ContactoService();

    public void save(String nombre,String apellido,String correo,String numero){
        Contacto contacto=new Contacto();
        contacto.setNombre(nombre);
        contacto.setApellido(apellido);
        contacto.setCorreo(correo);
        contacto.setNumeroDeTelefono(numero);
        service.save(contacto);
    }
    public void update(Long id,String nombre,String apellido,String correo,String numero){
        Contacto contacto=new Contacto();
        contacto.setNombre(nombre);
        contacto.setApellido(apellido);
        contacto.setCorreo(correo);
        contacto.setNumeroDeTelefono(numero);
        service.update(id,contacto);
    }
    public List<Contacto> getAll(){
        return service.findAll();
    }
    public void delete(Long id){
        service.delete(id);
    }
    public List<Contacto>getByNameOrLastName(String nombre){
        return service.findByNameOrLastName(nombre);
    }
}
