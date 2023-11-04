package com.gianpc.app.models.dao;

import com.gianpc.app.models.entity.Cliente;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ClienteDAO implements ICliente{

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
