package org.example;

public class Shampoo {
    private int id;
    private String nombre;
    private double precio;
    private int stock; //por unidad, no por caja (una caja 6 shampoo?)
    private int estado; // 1 = habilitado, 0 = deshabilitado (eliminado lógicamente)

    //Constructor para setear los valores
    public Shampoo(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.estado = 1; // Por defecto, habilitado
    }

    //Constructor con estado
    public Shampoo(int id, String nombre, double precio, int stock, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }

    // Constructor vacío necesario para deserialización JSON
    public Shampoo() {}

    //Getters para obtener los valores
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public int getEstado() { return estado; }

    //Setters para modificar los valores
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }
    public void setEstado(int estado) { this.estado = estado; }

    //que no use el toString de java, sino que use lo que defino acá
    @Override
    public String toString() {
        return "Shampoo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
