package org.example;

public class Shampoo {
    private int id;
    private String nombre;
    private double precio;
    private int stock; //por unidad, no por caja (una caja 6 shampoo?)

    //Constructor para setear los valores
    public Shampoo(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    //Getters para obtener los valores
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    //Setters para modificar los valores
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }

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

