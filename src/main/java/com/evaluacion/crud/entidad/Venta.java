package com.evaluacion.crud.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "tbl_venta")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVenta")
	private Long idVenta;

	@ManyToOne
	@JoinColumn(name = "idProducto", nullable = false)
	private Producto producto;

	@Column(name = "cantidad", nullable = false)
	private int cantidad;

	@Column(name = "precioTotal", nullable = false)
	private double precioTotal;

	@Column(name = "fechaCreacion", columnDefinition = "DATETIME")
	private LocalDateTime fechaCreacion;

	@Column(name = "fechaActualizacion", columnDefinition = "DATETIME")
	private LocalDateTime fechaActualizacion;

	@PrePersist
	protected void onCreate() {
		fechaCreacion = LocalDateTime.now();
		fechaActualizacion = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		fechaActualizacion = LocalDateTime.now();
	}

	// Constructores

	public Venta() {
	}

	public Venta(Long idVenta, Producto producto, int cantidad, double precioTotal, LocalDateTime fechaCreacion,
			LocalDateTime fechaActualizacion) {
		this.idVenta = idVenta;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioTotal = precioTotal;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}

	public Venta(Producto producto, int cantidad, double precioTotal, LocalDateTime fechaCreacion,
			LocalDateTime fechaActualizacion) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioTotal = precioTotal;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}

	// Getters y setters

	public Long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getFechaCreacionFormateada() {
		return fechaCreacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}

	public String getFechaActualizacionFormateada() {
		return fechaActualizacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}

}
