package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Pedido;
import com.erosduarte.Tienda.entity.Producto;

import java.util.List;

public interface PedidoService {
    List<Pedido> listar();
    Pedido crear(Pedido pedido);
    Pedido buscarPorId(Integer id);
    Pedido actualizar(Integer id, Pedido pedido);
    void eliminar(Integer id);
}
