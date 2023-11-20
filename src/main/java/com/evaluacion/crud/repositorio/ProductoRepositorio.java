package com.evaluacion.crud.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluacion.crud.entidad.Producto;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

}
