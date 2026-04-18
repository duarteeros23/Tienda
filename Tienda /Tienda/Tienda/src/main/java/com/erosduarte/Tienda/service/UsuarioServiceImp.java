package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Usuario;
import com.erosduarte.Tienda.exception.ResourceNotFoundException;
import com.erosduarte.Tienda.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario crear(Usuario usuario) {
        usuario.setIdUsuario(null);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario con ID no encontrado: " + id));
    }

    @Override
    public Usuario actualizar(Integer id, Usuario usuario) {
        Usuario existente = buscarPorId(id);

        existente.setNombreUsuario(usuario.getNombreUsuario());
        existente.setApellidoUsuario(usuario.getApellidoUsuario());
        existente.setEdadUsuario(usuario.getEdadUsuario());

        return usuarioRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if(!usuarioRepository.existsById(id)){
            throw new ResourceNotFoundException(("Usuario no encontrado con ID:" + id));
        }
        usuarioRepository.deleteById(id);
    }
}
