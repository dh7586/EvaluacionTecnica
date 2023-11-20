package com.evaluacion.crud.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "tbl_producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProducto")
	private Long idProducto;

	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	@Column(name = "descripcion", nullable = false, length = 100)
	private String descripcion;

	@Column(name = "precio")
	private double precio;

	@Column(name = "stock")
	private int stock;

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

	public Producto() {

	}

	public Producto(Long idProducto, String nombre, String descripcion, double precio, int stock,
			LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}

	public Producto(String nombre, String descripcion, double precio, int stock, LocalDateTime fechaCreacion,
			LocalDateTime fechaActualizacion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}

	// Getters y Setters

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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
