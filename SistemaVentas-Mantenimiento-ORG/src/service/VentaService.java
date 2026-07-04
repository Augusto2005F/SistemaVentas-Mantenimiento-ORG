package service;

import model.Cliente;
import model.Producto;
import model.Venta;
import repository.VentaRepository;
import util.Console;
import util.Validaciones;

public class VentaService {

    private ClienteService clienteService;
    private ProductoService productoService;
    private VentaRepository ventaRepo;

    private Venta ventaActual;

    public VentaService(ClienteService clienteService, ProductoService productoService, VentaRepository ventaRepo) {
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.ventaRepo = ventaRepo;
    }

    public void crearVenta(String dniCliente) {

        Cliente cliente = clienteService.buscarCliente(dniCliente);

        if (cliente == null) {
            Console.error("Cliente no existe");
            return;
        }

        ventaActual = new Venta(cliente);
        Console.info("Venta creada para: " + cliente.getNombre());
    }

    // Método privado reutilizable para eliminar el code smell de duplicación
    private boolean esVentaActiva() {
        if (ventaActual == null) {
            Console.error("No hay venta activa");
            return false;
        }
        return true;
    }

    public void agregarProductoVenta(int idProducto, int cantidad) {

        if (!esVentaActiva()) {
            return;
        }

        Producto producto = productoService.buscarProducto(idProducto);

        if (producto == null) {
            Console.error("Producto no encontrado");
            return;
        }

        if (!Validaciones.validarCantidad(cantidad)) {
            Console.error("Cantidad inválida");
            return;
        }

        ventaActual.agregarDetalle(producto, cantidad);
        Console.info("Producto agregado: " + producto.getNombre() + " x" + cantidad);
    }

    public void finalizarVenta() {

        if (!esVentaActiva()) {
            return;
        }

        if (ventaActual.getDetalles().isEmpty()) {
            Console.error("La venta no puede finalizarse sin productos");
            return;
        }

        ventaActual.finalizar();
        ventaRepo.guardar(ventaActual);

        Console.info("Venta finalizada. Total: " + ventaActual.calcularTotal());
        ventaActual = null;
    }

    public Venta obtenerVentaActual() {
        return ventaActual;
    }
}