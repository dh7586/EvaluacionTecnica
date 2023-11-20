package com.evaluacion.crud.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.evaluacion.crud.entidad.Producto;
import com.evaluacion.crud.servicio.ProductoServicio;

@RestController
@RequestMapping("/api/productos")
public class ProductoControladorApi {

	@Autowired
	private ProductoServicio servicio;

	// Obtiene la lista de productos
	@GetMapping
	public ResponseEntity<Iterable<Producto>> listarProductos() {
		Iterable<Producto> productos = servicio.listarProductos();

		return new ResponseEntity<>(productos, HttpStatus.OK);
	}

	// Guarda un nuevo producto
	@PostMapping
	public ResponseEntity<String> guardarProducto(@RequestBody Producto producto) {
		servicio.guardarProducto(producto);

		String mensaje = "Producto nuevo añadido correctamente.";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}

	// Obtiene un producto por su ID
	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtenerProductoId(@PathVariable Long id) {
		Producto producto = servicio.obtenerProductoId(id);
		if (producto != null) {
			return new ResponseEntity<>(producto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Actualiza un producto por su ID
	@PutMapping("/{id}")
	public ResponseEntity<String> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
		Producto productoExistente = servicio.obtenerProductoId(id);

		if (productoExistente != null) {
			productoExistente.setNombre(producto.getNombre());
			productoExistente.setDescripcion(producto.getDescripcion());
			productoExistente.setPrecio(producto.getPrecio());
			productoExistente.setStock(producto.getStock());

			servicio.actualizarProducto(productoExistente);

			String mensaje = "Producto con ID " + id + " actualizado exitosamente.";

			return new ResponseEntity<>(mensaje, HttpStatus.OK);
		} else {
			String mensaje = "No se encontró un producto con ID " + id + ".";

			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
	}

	// Elimina un producto por su ID
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
		if (servicio.existeProducto(id)) {
			servicio.eliminarProducto(id);
			String mensaje = "Producto con ID " + id + " eliminado exitosamente.";

			return new ResponseEntity<>(mensaje, HttpStatus.OK);
		} else {
			String mensaje = "No se encontró un producto con ID " + id + ".";

			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
	}
}
