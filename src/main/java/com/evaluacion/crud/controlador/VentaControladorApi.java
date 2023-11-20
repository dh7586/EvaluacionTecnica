package com.evaluacion.crud.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.evaluacion.crud.entidad.Venta;
import com.evaluacion.crud.servicio.VentaServicio;

@RestController
@RequestMapping("/api/ventas")
public class VentaControladorApi {

	@Autowired
	private VentaServicio servicio;

	// Obtiene la lista de ventas
	@GetMapping
	public ResponseEntity<Iterable<Venta>> listarVentas() {
		Iterable<Venta> ventas = servicio.listarVentas();

		return new ResponseEntity<>(ventas, HttpStatus.OK);
	}

	// Guarda una nueva venta
	@PostMapping
	public ResponseEntity<String> guardarVenta(@RequestBody Venta venta) {
		servicio.guardarVenta(venta);

		String mensaje = "Venta nueva añadido correctamente.";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}

	// Obtiene una venta por su ID
	@GetMapping("/{id}")
	public ResponseEntity<Venta> obtenerVentaId(@PathVariable Long id) {
		Venta venta = servicio.obtenerVentaId(id);
		if (venta != null) {
			return new ResponseEntity<>(venta, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Actualiza una venta por su ID
	@PutMapping("/{id}")
	public ResponseEntity<String> actualizarVenta(@PathVariable Long id, @RequestBody Venta venta) {
		Venta ventaExistente = servicio.obtenerVentaId(id);

		if (ventaExistente != null) {
			ventaExistente.setProducto(venta.getProducto());
			ventaExistente.setCantidad(venta.getCantidad());
			ventaExistente.setPrecioTotal(venta.getPrecioTotal());

			servicio.actualizarVenta(ventaExistente);

			String mensaje = "Venta con ID " + id + " actualizado exitosamente.";

			return new ResponseEntity<>(mensaje, HttpStatus.OK);
		} else {
			String mensaje = "No se encontró una venta con ID " + id + ".";

			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
	}

	// Elimina una venta por su ID
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarVenta(@PathVariable Long id) {
		if (servicio.existeVenta(id)) {
			servicio.eliminarVenta(id);
			String mensaje = "Venta con ID " + id + " eliminada exitosamente.";

			return new ResponseEntity<>(mensaje, HttpStatus.OK);
		} else {
			String mensaje = "No se encontró una venta con ID " + id + ".";

			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
	}
}
