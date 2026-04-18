package com.erosduarte.Tienda.repository;

import com.erosduarte.Tienda.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
