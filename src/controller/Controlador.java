/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.HashMap;
import java.util.Iterator;
import model.Libro;
import model.LibroException;
import persistencia.Fichero;

/**
 *
 * @author Joan
 */
public class Controlador {
    
    private static HashMap<String, Libro> inventarioLibros = new HashMap<>();
    private static Fichero fichero = new Fichero();

    public Controlador() {
        inventarioLibros = fichero.cargar();
    }
    
    public void guardar(){
        fichero.guardar(inventarioLibros);
    }
    
    public void agregar(Libro libro) throws LibroException{
        Iterator it = inventarioLibros.keySet().iterator();
        while(it.hasNext()){
            String key = (String) it.next();
            if(libro.getIsbn().equals(inventarioLibros.get(key).getIsbn())){
                throw new LibroException("Ya existe este libro");
            }
        }
        inventarioLibros.put(libro.getIsbn(), libro);
    }
    
    public void actualizar(String titulo, double nuevoPrecio, int nuevaCantidad) throws LibroException{
        encontrar(titulo);
        Iterator it = inventarioLibros.keySet().iterator();
        while(it.hasNext()){
            String key = (String) it.next();
            if(inventarioLibros.get(key).getTitulo().equals(titulo)){
                inventarioLibros.get(key).setPrecio(nuevoPrecio);
                inventarioLibros.get(key).setCantidadEnInventario(nuevaCantidad);
            }
        }
        
    }
    
    public void eliminar(String titulo) throws LibroException{
        encontrar(titulo);
        Iterator it = inventarioLibros.keySet().iterator();
        while(it.hasNext()){
            String key = (String) it.next();
            if(inventarioLibros.get(key).getTitulo().equals(titulo)){
                inventarioLibros.remove(key);
            }
        }
    }
    
    public void encontrar(String titulo) throws LibroException{
        boolean encontrado = false;
        Iterator it = inventarioLibros.keySet().iterator();
        while(it.hasNext()){
            String key = (String) it.next();
            if(inventarioLibros.get(key).getTitulo().equals(titulo)){
                encontrado = true;
            }
        }
        if(!encontrado){
            throw new LibroException("El libro no existe");
        }
        
    }

    public HashMap<String, Libro> getInventarioLibros() {
        return inventarioLibros;
    }

    public Fichero getFichero() {
        return fichero;
    }
    
    
    
}
