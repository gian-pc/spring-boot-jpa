package com.gianpc.app.models.dao;


import com.gianpc.app.models.entity.Cliente;

import java.util.List;

public interface ICliente {
    // Listar todos los clientes
    public List<Cliente> listarTodo();

    // Listar un cliente
    public Cliente obtenerUno();

    // Guardar cliente
    public void guardar(Cliente client);

    // Eliminar un cliente
    public void eliminar(Integer id);

}
