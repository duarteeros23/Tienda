package com.erosduarte.Tienda.repository;

import com.erosduarte.Tienda.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
