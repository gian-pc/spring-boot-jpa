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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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
    public String guardar(@Valid Cliente cliente, BindingResult bindingResult, Model model, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes, SessionStatus sessionStatus){
        if(bindingResult.hasErrors()){
            model.addAttribute("error", "¡Debes completar todos los datos obligatorios!");
            model.addAttribute("titulo", "Formulario de Cliente: Registrar");
            return "cliente/formulario";
        }
        if(!file.isEmpty()){
            String nombreUnico = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path carpeta = Paths.get("uploads").resolve(nombreUnico);
            Path raiz = carpeta.toAbsolutePath();
            try {
                Files.copy(file.getInputStream(), raiz);
                cliente.setFoto(nombreUnico);
                redirectAttributes.addFlashAttribute("info", "¡Se ha subido una imagen!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        iCliente.guardar(cliente);
        sessionStatus.setComplete();
        redirectAttributes.addFlashAttribute("success", "¡Cliente guardado exitosamente!");
        return "redirect:listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        iCliente.eliminar(id);
        redirectAttributes.addFlashAttribute("warning", "¡Cliente eliminado exitosamente!");
        return "redirect:/cliente/listar";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("titulo","Detalle del Cliente");
        model.addAttribute("cliente", iCliente.obtenerUno(id));
        return "cliente/detalle";
    }
}
