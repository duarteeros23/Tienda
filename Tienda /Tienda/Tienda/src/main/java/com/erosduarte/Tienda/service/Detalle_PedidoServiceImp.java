package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Detalle_Pedido;
import com.erosduarte.Tienda.exception.ResourceNotFoundException;
import com.erosduarte.Tienda.repository.Detalle_PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Detalle_PedidoServiceImp implements  Detalle_PedidoService{
    private final Detalle_PedidoRepository detallePedidoRepository;

    public Detalle_PedidoServiceImp(Detalle_PedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    @Override
    public List<Detalle_Pedido> listar() {
        return detallePedidoRepository.findAll();
    }

    @Override
    public Detalle_Pedido crear(Detalle_Pedido detallePedido) {
        detallePedido.setIdDetalle(null);
        return detallePedidoRepository.save(detallePedido);
    }

    @Override
    public Detalle_Pedido buscarPorId(Integer id) {
        return detallePedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El id del detalle del pedido no se encontro:" + id));
    }

    @Override
    public Detalle_Pedido actualizar(Integer id, Detalle_Pedido detallePedido) {
        Detalle_Pedido existente = buscarPorId(id);
        existente.setCantidadDetalle(detallePedido.getCantidadDetalle());
        existente.setPrecioUnitario(detallePedido.getPrecioUnitario());
        existente.setIdPedido(detallePedido.getIdPedido());
        existente.setIdProducto(detallePedido.getIdProducto());
        return detallePedidoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if(!detallePedidoRepository.existsById(id)){
            throw new ResourceNotFoundException("El id del detalle del pedido no se encontro: " + id);
        }
        detallePedidoRepository.deleteById(id);
    }
}
