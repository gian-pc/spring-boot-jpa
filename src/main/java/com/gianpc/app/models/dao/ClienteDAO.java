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
    public Cliente obtenerUno() {
        return null;
    }

    @Override
    public void guardar(Cliente client) {

    }

    @Override
    public void eliminar(Integer id) {

    }
}
