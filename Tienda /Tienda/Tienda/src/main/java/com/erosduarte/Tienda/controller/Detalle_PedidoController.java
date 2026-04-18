package com.erosduarte.Tienda.controller;

import com.erosduarte.Tienda.entity.Detalle_Pedido;
import com.erosduarte.Tienda.entity.Usuario;
import com.erosduarte.Tienda.service.Detalle_PedidoService;
import jakarta.validation.Valid;
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
@RequestMapping("/detallePedidos")
public class Detalle_PedidoController {
    private final Detalle_PedidoService detallePedidoService;

    public Detalle_PedidoController(Detalle_PedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public String  listar(Model model){
        model.addAttribute("detallePedidos", detallePedidoService.listar());
        return "detalle_pedido";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("detalle_pedido", new Detalle_Pedido());
        model.addAttribute("modoEdicion", false);
        return "detalle_pedido-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("detalle_pedido") Detalle_Pedido detallePedido,
                        BindingResult result,
                        Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "detalle_pedido-form";
        }
        detallePedidoService.crear(detallePedido);
        return "redirect:/detallePedidos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model){
        Detalle_Pedido detallePedido = detallePedidoService.buscarPorId(id);
        model.addAttribute("detalle_pedido", detallePedido);
        model.addAttribute("modoEdicion", true);
        return  "detalle_pedido-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable("id") Integer id, @Valid @ModelAttribute("detalle_pedido") Detalle_Pedido detallePedido, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion", true);
            return  "detalle_pedido-form";
        }
        detallePedido.setIdDetalle(id);
        detallePedidoService.actualizar(id, detallePedido);
        return "redirect:/detallePedidos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id){
        detallePedidoService.eliminar(id);
        return "redirect:/detallePedidos";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam(value = "idDetalle", required = false) Integer id, Model model){
        if(id != null){
            Detalle_Pedido detallePedido = detallePedidoService.buscarPorId(id);
            if( detallePedido != null){
                model .addAttribute("detallePedidos", List.of(detallePedido));
            }else{
                model.addAttribute("error ", "Detalle de pedido con ID: " + id + "no encontrado");
                model.addAttribute("detallePedidos", detallePedidoService.listar());
            }

        }else{
            return "redirect:/detallePedidos";
        }
        return "detalle_pedido";
    }
}
