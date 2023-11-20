package com.evaluacion.crud.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.evaluacion.crud.entidad.Producto;
import com.evaluacion.crud.entidad.Venta;
import com.evaluacion.crud.repositorio.ProductoRepositorio;
import com.evaluacion.crud.servicio.VentaServicio;

@Controller
public class VentaControlador {

	@Autowired
	private VentaServicio servicio;

	@Autowired
	private ProductoRepositorio productoRepositorio;

	@GetMapping("/")
	public RedirectView redirigirVentas() {
		return new RedirectView("/ventas");
	}

	// Muestra la lista de ventas en la página principal y en la ruta /ventas
	@GetMapping({ "/ventas" })
	public String listarVentas(Model modelo) {
		modelo.addAttribute("ventas", servicio.listarVentas());

		// Devuelve la vista ventas.html
		return "ventas";
	}

	// Muestra el formulario para registrar una nueva venta
	@GetMapping("/ventas/nuevo")
	public String registrarVenta(Model modelo) {

		// Obteniene la lista de productos desde la base de datos
		List<Producto> listaProductos = productoRepositorio.findAll();
		modelo.addAttribute("listaProductos", listaProductos);

		// Crea una nueva instancia de Venta y la agrega al modelo
		Venta venta = new Venta();
		modelo.addAttribute("venta", venta);

		// Devuelve la vista crear_venta.html
		return "crear_venta";
	}

	// Guarda la venta mediante el formulario
	@PostMapping("/ventas")
	public String guardarVenta(@ModelAttribute("venta") Venta venta) {
		servicio.guardarVenta(venta);
		// Redirige a la lista de ventas
		return "redirect:/ventas";
	}

	// Muestra el formulario para editar una venta específica
	@GetMapping("/ventas/editar/{id}")
	public String editarVenta(@PathVariable Long id, Model modelo) {
		List<Producto> listaProductos = productoRepositorio.findAll();
		modelo.addAttribute("listaProductos", listaProductos);

		modelo.addAttribute("venta", servicio.obtenerVentaId(id));
		// Devuelve la vista editar_venta.html
		return "editar_venta";
	}

	// Actualiza la venta
	@PostMapping("/ventas/{id}")
	public String actualizarVenta(@PathVariable Long id, @ModelAttribute("venta") Venta venta) {
		Venta ventaExistente = servicio.obtenerVentaId(id);
		ventaExistente.setProducto(venta.getProducto());
		ventaExistente.setCantidad(venta.getCantidad());
		ventaExistente.setPrecioTotal(venta.getPrecioTotal());
		ventaExistente.setFechaActualizacion(venta.getFechaActualizacion());
		// Guarda la venta actualizada
		servicio.actualizarVenta(ventaExistente);
		// Redirige a la lista de ventas
		return "redirect:/ventas";
	}

	// Elimina la venta
	@GetMapping("/ventas/{id}")
	public String eliminarVenta(@PathVariable Long id) {
		servicio.eliminarVenta(id);
		// Redirige a la lista de ventas
		return "redirect:/ventas";
	}
}
