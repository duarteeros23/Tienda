package com.erosduarte.Tienda.controller;

import com.erosduarte.Tienda.entity.Producto;
import com.erosduarte.Tienda.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.FileAttribute;
import java.util.List;

@Controller
@Validated
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("productos", productoService.listar());
        return "producto";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("producto", new Producto());
        model.addAttribute("modoEdicion", false);
        return "producto-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("producto") Producto producto,
                        BindingResult result,
                        Model model){
        if(result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "producto-form";
        }
        productoService.crear(producto);
        return "redirect:/productos";
    }

   @GetMapping("/editar/{id}")
   public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model){
        Producto producto = productoService.buscarPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("modoEdicion", true);
        return "producto-form";
   }

   @PostMapping("/actualizar/{id}")
   public String actualizar(@PathVariable("id") Integer id, @Valid @ModelAttribute("producto") Producto producto,
                            BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion", true);
            return "producto-form";
        }
        producto.setIdProducto(id);
        productoService.actualizar(id, producto);
        return "redirect:/productos";
   }

    @GetMapping("/eliminar/{id}")
    public  String eliminar(@PathVariable("id") Integer id){
        productoService.eliminar(id);
        return "redirect:/productos";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam(value = "idProducto", required = false) Integer id, Model model){
        if(id != null){
            Producto producto = productoService.buscarPorId(id);
            if(producto != null){
                model.addAttribute("productos", List.of(producto));
            }else{
                model.addAttribute("error: ", "Producto con id: " + id + " no encontrado");
                model.addAttribute("productos", productoService.listar());
            }

        }else{
            return "redirect:/productos";
        }
        return "producto";
    }

}
