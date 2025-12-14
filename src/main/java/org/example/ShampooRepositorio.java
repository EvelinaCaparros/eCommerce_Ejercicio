package org.example;

import java.io.*;
import java.util.*;
import java.util.function.ToDoubleBiFunction;
import org.springframework.stereotype.Repository;

@Repository
public class ShampooRepositorio {
    private final Map<Integer, Shampoo> shampoos = new HashMap<>(); //en vez de lista, para poder guardar por ID como en una BD relacional
    private int nextId = 1;
    private final String archivo = "lista de shampoos";

    public ShampooRepositorio() {
        cargarDesdeArchivo();
    }

    public Shampoo guardar (Shampoo shampoo) {
        if (shampoo.getId() == 0) {
            shampoo.setId(nextId++);
        }
        shampoos.put(shampoo.getId(), shampoo);
        guardarEnArchivo();
        return shampoo;
    }

    /*
    TODO (en proceso)
     Tengo que cambiar para que lo tome todo del archivo y no persista todo en la variable, sino que cada vez que haga una operacion lea del archivo.
     De esta forma, si se modifica el archivo externo, los cambios se reflejan en la app sin tener que reiniciarla.
    En vez de eliminar, no deberia deshabilitar el shampoo? O sea, agregar un campo "activo" o "disponible" o algo asi.
    De esta forma, no se pierde la info historica...
    Despues tengo que sacar el eliminar to-do jaja

    debo sacar lo de eliminar por ID y que sea interno que maneje por ID, pero muestre nombre al usuario... uso Java Swing??? mmmmmm
     */

    public Shampoo buscarPorID(int id) {
        return shampoos.get(id);
    }

    public List<Shampoo> buscarTodos() {
        List<Shampoo> activos = new ArrayList<>();
        for (Shampoo s : shampoos.values()) {
            if (s.getEstado() == 1) {
                activos.add(s);
            }
        }
        return activos;
    }

    public boolean borrarPorID(int id) {
        Shampoo shampoo = shampoos.get(id);
        if (shampoo != null && shampoo.getEstado() == 1) {
            shampoo.setEstado(0);
            guardarEnArchivo();
            return true;
        }
        return false;
    }

    public void borrarTodos() {
        for (Shampoo s : shampoos.values()) {
            s.setEstado(0);
        }
        guardarEnArchivo();
    }

    public List<Shampoo> buscarEliminados() {
        List<Shampoo> eliminados = new ArrayList<>();
        for (Shampoo s : shampoos.values()) {
            if (s.getEstado() == 0) {
                eliminados.add(s);
            }
        }
        return eliminados;
    }

    private void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Shampoo s : shampoos.values()) {
                pw.println(s.getId() + "," + s.getNombre() + "," + s.getPrecio() + "," + s.getStock() + "," + s.getEstado());
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private void cargarDesdeArchivo() {
        File f = new File(archivo);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 5) {
                    // Nuevo formato con estado
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double precio = Double.parseDouble(partes[2]);
                    int stock = Integer.parseInt(partes[3]);
                    int estado = Integer.parseInt(partes[4]);
                    shampoos.put(id, new Shampoo(id, nombre, precio, stock, estado));
                    if (id >= nextId) nextId = id + 1;
                } else if (partes.length == 4) {
                    // Formato antiguo sin estado (para retrocompatibilidad)
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double precio = Double.parseDouble(partes[2]);
                    int stock = Integer.parseInt(partes[3]);
                    shampoos.put(id, new Shampoo(id, nombre, precio, stock, 1)); // Estado por defecto = 1
                    if (id >= nextId) nextId = id + 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
