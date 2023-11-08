package com.gianpc.app.controllers;

import com.gianpc.app.models.dao.ICliente;
import com.gianpc.app.models.entity.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("cliente")
@SessionAttributes("cliente")
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
            model.addAttribute("info", "¡Cliente encontrado exitosamente!");
        }
        return "cliente/formulario";
    }

    @PostMapping("/formulario")
    public String guardar(@Valid Cliente cliente, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, SessionStatus sessionStatus){
        if(bindingResult.hasErrors()){
            model.addAttribute("error", "¡Debes completar todos los datos obligatorios!");
            model.addAttribute("titulo", "Formulario de Cliente: Registrar");
            return "cliente/formulario";
        }
        iCliente.guardar(cliente);
        sessionStatus.setComplete();
        redirectAttributes.addFlashAttribute("info", "¡Cliente guardado exitosamente!");
        return "redirect:listar";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        iCliente.eliminar(id);
        redirectAttributes.addFlashAttribute("info", "¡Cliente guardado exitosamente!");
        return "redirect:/cliente/listar";
    }

}
