package com.gianpc.app.controllers;

import com.gianpc.app.models.dao.ClienteDAO;
import com.gianpc.app.models.dao.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ICliente iCliente;

    @GetMapping("/listar")
    public String listarTodo(Model model){
        model.addAttribute("titulo", "Lista de Clientes");
        model.addAttribute("clientes",iCliente.listarTodo());
        return "cliente/listar";
    }

}
