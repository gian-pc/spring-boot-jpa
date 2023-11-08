package com.gianpc.app.controllers;

import com.gianpc.app.models.dao.ClienteDAO;
import com.gianpc.app.models.dao.ICliente;
import com.gianpc.app.models.entity.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping({"/formulario", "/formulario/{id}"})
    public String formulario(@PathVariable(required = false) Integer id, Model model){
        if(id==null){
            model.addAttribute("titulo","Formulario de Cliente: Registrar");
            model.addAttribute("cliente", new Cliente());
        }else{
            model.addAttribute("titulo", "Formulario de Cliente: Actualizar");
            model.addAttribute("cliente", iCliente.obtenerUno(id));
        }
        return "cliente/formulario";
    }

    @PostMapping("/formulario")
    public String guardar(@Valid Cliente cliente, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("titulo", "Formulario de Cliente: Registrar");
            return "cliente/formulario";
        }
        iCliente.guardar(cliente);
        return "redirect:listar";
    }
}
