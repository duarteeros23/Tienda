package com.erosduarte.Tienda.controller;

import com.erosduarte.Tienda.entity.Producto;
import com.erosduarte.Tienda.entity.Usuario;
import com.erosduarte.Tienda.service.ProductoService;
import com.erosduarte.Tienda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@Validated
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Método nuevo para listar usuarios
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        return "usuario";
    }

    // Método para cargar la nueva vista / formulario de registro de usuario
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("modoEdicion", false);
        return "usuario-form";
    }

    // Método para crear un nuevo usuario
    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("usuario") Usuario usuario,
                        BindingResult result,
                        Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "usuario-form";
        }
        usuarioService.crear(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model){
            Usuario usuario = usuarioService.buscarPorId(id);
            model.addAttribute("usuario", usuario);
            model.addAttribute("modoEdicion", true);
            return  "usuario-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable("id") Integer id, @Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion", true);
            return  "usuario-form";
        }
        usuario.setIdUsuario(id);
        usuarioService.actualizar(id, usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id){
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam(value = "idUsuario", required = false) Integer id, Model model){
        if(id != null){
            Usuario usuario = usuarioService.buscarPorId(id);
            if(usuario != null){
                model .addAttribute("usuarios", List.of(usuario));
            }else{
                model.addAttribute("error ", "Usuario con ID: " + id + "no encontrado");
                model.addAttribute("usuarios", usuarioService.listar());
            }

        }else{
            return "redirect:/usuarios";
        }
        return "usuario";
    }

}