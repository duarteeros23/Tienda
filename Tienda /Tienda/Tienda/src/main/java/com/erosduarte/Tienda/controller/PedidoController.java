package com.erosduarte.Tienda.controller;

import com.erosduarte.Tienda.entity.Pedido;
import com.erosduarte.Tienda.entity.Usuario;
import com.erosduarte.Tienda.service.PedidoService;
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
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public String listar(Model model){
            model.addAttribute("pedidos", pedidoService.listar());
            return "pedido";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("modoEdicion", false);
        return "pedido-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("pedido") Pedido pedido, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion", false);
            return "pedido-form";
        }
        pedidoService.crear(pedido);
        return "redirect:/pedidos";
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model){
        Pedido pedido = pedidoService.buscarPorId(id);
        model.addAttribute("pedido", pedido);
        model.addAttribute("modoEdicion", true);
        return  "pedido-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable("id") Integer id, @Valid @ModelAttribute("pedido") Pedido pedido, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion", true);
            return  "pedido-form";
        }
        pedido.setIdUsuario(id);
        pedidoService.actualizar(id, pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id){
        pedidoService.eliminar(id);
        return "redirect:/pedidos";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam(value = "idPedido", required = false) Integer id, Model model){
        if(id != null){
            Pedido pedido = pedidoService.buscarPorId(id);
            if(pedido != null){
                model .addAttribute("pedidos", List.of(pedido));
            }else{
                model.addAttribute("error ", "Pedido con ID: " + id + "no encontrado");
                model.addAttribute("pedidos", pedidoService.listar());
            }
        }else{
            return "redirect:/pedidos";
        }
        return "pedido";
    }
}
