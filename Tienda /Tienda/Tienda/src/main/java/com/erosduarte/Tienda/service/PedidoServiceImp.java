package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Pedido;
import com.erosduarte.Tienda.exception.ResourceNotFoundException;
import com.erosduarte.Tienda.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImp implements PedidoService{
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImp(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido crear(Pedido pedido) {
        pedido.setIdPedido(null);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido buscarPorId(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ID de pedido no encontrado: " + id));
    }

    @Override
    public Pedido actualizar(Integer id, Pedido pedido) {
       Pedido existente = buscarPorId(id);
       existente.setFechaPedido(pedido.getFechaPedido());
       existente.setTotalPedido(pedido.getTotalPedido());
       existente.setIdUsuario(pedido.getIdUsuario());

       return pedidoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if(!pedidoRepository.existsById(id)){
            throw new ResourceNotFoundException("El id del pedido no existe: " + id);
        }
        pedidoRepository.deleteById(id);
    }
}
