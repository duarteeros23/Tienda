package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listar();
    Categoria crear(Categoria categoria);
    Categoria actualizar(Integer id, Categoria categoria);
    Categoria buscarPorid(Integer id);
    void eliminar(Integer id);
}
