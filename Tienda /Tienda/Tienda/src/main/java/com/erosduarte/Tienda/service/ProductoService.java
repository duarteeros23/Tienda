package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> listar();
    Producto crear(Producto producto);
    Producto buscarPorId(Integer id );
    Producto actualizar(Integer id, Producto producto);
    void eliminar(Integer id);
}
