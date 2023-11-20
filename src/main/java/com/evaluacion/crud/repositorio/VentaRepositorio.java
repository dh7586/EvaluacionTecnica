package com.evaluacion.crud.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluacion.crud.entidad.Venta;

@Repository
public interface VentaRepositorio extends JpaRepository<Venta, Long> {

}
