package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Producto;
import com.erosduarte.Tienda.exception.ResourceNotFoundException;
import com.erosduarte.Tienda.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImp implements  ProductoService{
    private final ProductoRepository productoRepository;

    public ProductoServiceImp(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto crear(Producto producto) {
        producto.setIdProducto(null);
        return productoRepository.save(producto);
    }

    @Override
    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Producto con id no encontrado: " + id));
    }

    @Override
    public Producto actualizar(Integer id, Producto producto) {
        Producto existente = buscarPorId(id);

        existente.setNombreProducto(producto.getNombreProducto());
        existente.setPrecioProducto(producto.getPrecioProducto());
        existente.setStockk(producto.getStockk());
        existente.setIdCategoria(producto.getIdCategoria());

        return productoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if(!productoRepository.existsById(id)){
            throw new ResourceNotFoundException(("Id de producto no encontrado: " + id));
        }
        productoRepository.deleteById(id);
    }
}
