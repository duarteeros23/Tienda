package com.erosduarte.Tienda.controller;

import com.erosduarte.Tienda.entity.Categoria;
import com.erosduarte.Tienda.entity.Usuario;
import com.erosduarte.Tienda.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@Controller
@Validated
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        return "categoria";
    }


    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("modoEdicion", false);
        return "categoria-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("categoria") Categoria categoria,
                        BindingResult result,
                        Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion", false);
            return "categoria-form";
        }
        categoriaService.crear(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model){
            Categoria categoria = categoriaService.buscarPorid(id);
            model.addAttribute("categoria", categoria);
            model.addAttribute("modoEdicion", true);
            return "categoria-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualiaar(@PathVariable("id") Integer id, @Valid @ModelAttribute("categoria") Categoria categoria, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion", true);
            return "categoria-form";
        }
        categoria.setIdCategoria(id);
        categoriaService.actualizar(id, categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id){
        categoriaService.eliminar(id);
        return "redirect:/categorias";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(value = "idCategoria", required = false) Integer id, Model model){
        if(id !=null){
            Categoria categoria = categoriaService.buscarPorid(id);
            if(categoria  !=null){
                model.addAttribute("categorias", List.of(categoria));
            }else{
                model.addAttribute("error ", "Categoria con id:" + id + "No encontrado");
                model.addAttribute("categorias", categoriaService.listar());
            }
        }else{
            return "redirect:/categorias";
        }
        return "categoria";
    }



}
