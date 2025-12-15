package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "shampoos")
public class Shampoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "stock", nullable = false)
    private int stock; //por unidad, no por caja (una caja 6 shampoo?)

    @Column(name = "estado", nullable = false)
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

    // Constructor vacío necesario para JPA y deserialización JSON
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
