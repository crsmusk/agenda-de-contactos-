package org.example.Persistencia.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.Persistencia.Conector.conector;
import org.example.Persistencia.Excepciones.ContactoNoEncontrado;
import org.example.Persistencia.Excepciones.ErrorAlEstablecerLaConexion;
import org.example.Persistencia.Excepciones.ErrorEnLaConsulta;
import org.example.Persistencia.Model.Entidades.Contacto;

import java.util.List;

public class ContactoDAO {

    public Contacto save(Contacto contacto){
        EntityManager dao= conector.getEntityManager();
        try {
            dao.getTransaction().begin();
            dao.persist(contacto);
            dao.getTransaction().commit();
        }catch (ErrorAlEstablecerLaConexion e){
            e.printStackTrace();
        }finally {
            dao.close();
        }
        return contacto;
    }
    public List<Contacto> findByNameOrlastName(String nombre){
        EntityManager dao= conector.getEntityManager();
        List<Contacto>lista=null;
        try {
            Query query=dao.createQuery("SELECT c FROM Contacto c WHERE c.nombre LIKE:nombre or c.apellido LIKE:apellido",Contacto.class);
            query.setParameter("nombre","%"+nombre+"%");
            query.setParameter("apellido","%"+nombre+"%");
           lista=query.getResultList();
        }catch (ContactoNoEncontrado e){
            e.printStackTrace();
        }finally {
            dao.close();
        }
        return lista;
    }

    public List<Contacto>getAll(){
        EntityManager dao=conector.getEntityManager();
        List<Contacto>lista=null;
        try {
            Query query=dao.createQuery("SELECT c FROM Contacto c");
            lista=query.getResultList();
        }catch (ContactoNoEncontrado e){
            e.printStackTrace();
        }finally {
            dao.close();
        }
        return lista;
    }

    public void delete(Long id){
        EntityManager dao=conector.getEntityManager();
        try {
            dao.getTransaction().begin();
            Query query=dao.createNativeQuery("DELETE FROM contactos WHERE id=?");
            query.setParameter(1,id);
            query.executeUpdate();
            dao.getTransaction().commit();
        }catch (ErrorEnLaConsulta e){
            e.printStackTrace();
        }finally {
            dao.close();
        }
    }

    public Contacto update(Long id,Contacto contacto){
        EntityManager dao=conector.getEntityManager();
        Contacto contacto1=null;
        try {
            dao.getTransaction().begin();
            contacto1=dao.find(Contacto.class,id);
            contacto1.setNumeroDeTelefono(contacto.getNumeroDeTelefono());
            contacto1.setCorreo(contacto.getCorreo());
            contacto1.setApellido(contacto.getApellido());
            contacto1.setNombre(contacto.getNombre());
            dao.merge(contacto1);
            dao.getTransaction().commit();
        }catch (ErrorEnLaConsulta e){
            e.printStackTrace();
        }finally {
            dao.close();
        }
        return contacto1;
    }


}
