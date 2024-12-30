package org.example.Persistencia.Servicio;

import org.example.Persistencia.DAO.ContactoDAO;
import org.example.Persistencia.Excepciones.ContactoNoEncontrado;
import org.example.Persistencia.Model.Entidades.Contacto;

import java.util.List;

public class ContactoService {
    ContactoDAO dao=new ContactoDAO();

    public void save(Contacto contacto){
        dao.save(contacto);
    }

    public List<Contacto> findByNameOrLastName(String nombre){
        if (dao.findByNameOrlastName(nombre).isEmpty()){
            throw new ContactoNoEncontrado();
        }else{
            return dao.findByNameOrlastName(nombre);
        }
    }

    public void delete(Long id){
        dao.delete(id);
    }

    public void update(Long id,Contacto contacto){
        dao.update(id,contacto);
    }

    public List<Contacto>findAll(){
      return dao.getAll();
    }

}
