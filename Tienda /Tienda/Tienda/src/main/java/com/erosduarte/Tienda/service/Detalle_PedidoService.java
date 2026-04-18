package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Detalle_Pedido;

import java.util.List;

public interface Detalle_PedidoService {
    List<Detalle_Pedido> listar();
    Detalle_Pedido crear(Detalle_Pedido detallePedido);
    Detalle_Pedido buscarPorId(Integer id);
    Detalle_Pedido actualizar(Integer id, Detalle_Pedido detallePedido);
    void eliminar(Integer id);
}
