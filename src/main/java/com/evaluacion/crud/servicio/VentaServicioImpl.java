package com.evaluacion.crud.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluacion.crud.entidad.Venta;
import com.evaluacion.crud.repositorio.VentaRepositorio;

@Service
public class VentaServicioImpl implements VentaServicio {

	@Autowired
	private VentaRepositorio repositorio;

	@Override
	public List<Venta> listarVentas() {
		return repositorio.findAll();
	}

	@Override
	public Venta guardarVenta(Venta venta) {
		return repositorio.save(venta);
	}

	@Override
	public Venta obtenerVentaId(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public Venta actualizarVenta(Venta venta) {
		return repositorio.save(venta);
	}

	@Override
	public void eliminarVenta(Long id) {
		repositorio.deleteById(id);
	}

	@Override
	public boolean existeVenta(Long id) {
		return repositorio.existsById(id);
	}

}
