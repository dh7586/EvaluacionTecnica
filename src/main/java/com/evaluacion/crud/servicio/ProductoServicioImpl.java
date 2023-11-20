package com.evaluacion.crud.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluacion.crud.entidad.Producto;
import com.evaluacion.crud.repositorio.ProductoRepositorio;

@Service
public class ProductoServicioImpl implements ProductoServicio {

	@Autowired
	private ProductoRepositorio repositorio;

	@Override
	public List<Producto> listarProductos() {
		return repositorio.findAll();
	}

	@Override
	public Producto guardarProducto(Producto producto) {
		return repositorio.save(producto);
	}

	@Override
	public Producto obtenerProductoId(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public Producto actualizarProducto(Producto producto) {
		return repositorio.save(producto);
	}

	@Override
	public void eliminarProducto(Long id) {
		repositorio.deleteById(id);
	}

	@Override
	public boolean existeProducto(Long id) {
		return repositorio.existsById(id);
	}

}
