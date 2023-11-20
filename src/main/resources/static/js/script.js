// PINTAR LA SECCION ACTUAL
const navLinks = document.querySelectorAll('.nav__a');
const urlBase = "http://localhost:8080";
const urlActual = window.location.href;

const linkVenta = urlBase + "/ventas";
const linkProducto = urlBase + "/productos";

if (urlActual.includes(linkVenta)) {
	navLinks[0].classList.add('nav__a--selected');
} else if (urlActual.includes(linkProducto)) {
	navLinks[1].classList.add('nav__a--selected');
}