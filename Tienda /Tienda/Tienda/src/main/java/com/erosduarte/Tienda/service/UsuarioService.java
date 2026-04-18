package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UsuarioService {
    List<Usuario> listar();
    Usuario crear(Usuario usuario);
    Usuario actualizar(Integer id, Usuario usuario);
    Usuario buscarPorId(Integer id);
    void eliminar(Integer id);
}
