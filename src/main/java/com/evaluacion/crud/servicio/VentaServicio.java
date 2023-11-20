package com.evaluacion.crud.servicio;

import java.util.List;

import com.evaluacion.crud.entidad.Venta;

public interface VentaServicio {

	public List<Venta> listarVentas();

	public Venta guardarVenta(Venta venta);

	public Venta obtenerVentaId(Long id);

	public Venta actualizarVenta(Venta venta);

	public void eliminarVenta(Long id);

	boolean existeVenta(Long id);
}
