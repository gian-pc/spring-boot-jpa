package com.gianpc.app.models.dao;

import com.gianpc.app.models.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ClienteDAO implements ICliente{

    @PersistenceContext // Con esto le decimos al entityManager que pueda manipular lo que tenga que ver con el contexto de persistencia es decir con la creación de las entidades por el mismo
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> listarTodo() {
        return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente obtenerUno(Integer id) {
        return entityManager.find(Cliente.class, id);
    }

    @Override
    @Transactional
    public void guardar(Cliente cliente) {
        if(cliente.getId()==null){
            entityManager.persist(cliente);
        }else {
            entityManager.merge(cliente);
        }
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        entityManager.remove(obtenerUno(id));
    }
}
