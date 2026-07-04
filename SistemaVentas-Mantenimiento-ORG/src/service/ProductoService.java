package service;

import model.Producto;
import repository.ProductoRepository;
import util.Console;
import util.Validaciones;

public class ProductoService {

    private ProductoRepository productoRepo;

    public ProductoService(ProductoRepository productoRepo) {
        this.productoRepo = productoRepo;
    }

    public void registrarProducto(int id, String nombre, double precio) {

        // Validación robusta añadida para corregir el bug intencional
        if (!Validaciones.validarPrecio(precio) || precio <= 0) {
            Console.error("Precio inválido");
            return;
        }

        Producto producto = new Producto(id, nombre, precio);
        productoRepo.guardar(producto);

        Console.info("Producto registrado: " + nombre);
    }

    public Producto buscarProducto(int id) {
        return productoRepo.buscarPorId(id);
    }

    public Producto buscarProductoPorNombre(String nombre) {
        return productoRepo.buscarPorNombre(nombre);
    }
}