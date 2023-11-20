package com.evaluacion.crud.servicio;

import java.util.List;

import com.evaluacion.crud.entidad.Producto;

public interface ProductoServicio {

	public List<Producto> listarProductos();

	public Producto guardarProducto(Producto producto);

	public Producto obtenerProductoId(Long id);

	public Producto actualizarProducto(Producto producto);

	public void eliminarProducto(Long id);

	boolean existeProducto(Long id);
}
