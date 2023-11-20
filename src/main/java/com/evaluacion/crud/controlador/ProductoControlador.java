package com.evaluacion.crud.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.evaluacion.crud.entidad.Producto;
import com.evaluacion.crud.servicio.ProductoServicio;

@Controller
public class ProductoControlador {

	@Autowired
	private ProductoServicio servicio;

	// Muestra la lista de productos en la ruta /productos
	@GetMapping("/productos")
	public String listarProductos(Model modelo) {
		modelo.addAttribute("productos", servicio.listarProductos());

		// Devuelve la vista productos.html
		return "productos";
	}

	// Muestra el formulario para registrar un nuevo producto
	@GetMapping("/productos/nuevo")
	public String registrarProducto(Model modelo) {

		// Crea una nueva instancia de Producto y la agrega al modelo
		Producto producto = new Producto();
		modelo.addAttribute("producto", producto);

		// Devuelve la vista crear_producto.html
		return "crear_producto";
	}

	// Guarda el producto mediante el formulario
	@PostMapping("/productos")
	public String guardarProducto(@ModelAttribute("producto") Producto producto) {
		servicio.guardarProducto(producto);

		// Redirige a la lista de productos
		return "redirect:/productos";
	}

	// Muestra el formulario para editar un producto específico
	@GetMapping("/productos/editar/{id}")
	public String editarProducto(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("producto", servicio.obtenerProductoId(id));

		// Devuelve la vista editar_producto.html
		return "editar_producto";
	}

	// Actualiza el producto
	@PostMapping("/productos/{id}")
	public String actualizarProducto(@PathVariable Long id, @ModelAttribute("producto") Producto producto,
			Model modelo) {
		Producto productoExistente = servicio.obtenerProductoId(id);

		// Actualiza los datos del producto existente con los nuevos datos del
		// formulario
		productoExistente.setNombre(producto.getNombre());
		productoExistente.setDescripcion(producto.getDescripcion());
		productoExistente.setPrecio(producto.getPrecio());
		productoExistente.setStock(producto.getStock());
		productoExistente.setFechaActualizacion(producto.getFechaActualizacion());

		// Guarda el producto actualizado
		servicio.actualizarProducto(productoExistente);

		// Redirige a la lista de productos
		return "redirect:/productos";
	}

	// Elimina el producto
	@GetMapping("/productos/{id}")
	public String eliminarProducto(@PathVariable Long id) {
		servicio.eliminarProducto(id);

		// Redirige a la lista de productos
		return "redirect:/productos";
	}

}
