package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Categoria;
import com.erosduarte.Tienda.exception.ResourceNotFoundException;
import com.erosduarte.Tienda.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImp implements  CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImp(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria crear(Categoria categoria) {
        categoria.setIdCategoria(null);
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria buscarPorid(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id de categoria no encontado: " + id));
    }

    @Override
    public Categoria actualizar(Integer id, Categoria categoria) {
        Categoria existente = buscarPorid(id);

        existente.setNombreCategoria(categoria.getNombreCategoria());
        existente.setDescripcionCategoria(categoria.getDescripcionCategoria());

        return categoriaRepository.save(existente);
    }



    @Override
    public void eliminar(Integer id) {
        if(!categoriaRepository.existsById(id)){
            throw new ResourceNotFoundException(("Categoria con id no encontrado : " + id));
        }
        categoriaRepository.deleteById(id);
    }
}
