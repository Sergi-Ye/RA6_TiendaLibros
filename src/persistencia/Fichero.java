/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import model.Libro;

/**
 *
 * @author Joan
 */
public class Fichero {

    private static final String RUTA_ARCHIVO = "data/archivo.txt";
    
    public Fichero() {
    try{
            File carpeta = new File("data");
            File archivo = new File(RUTA_ARCHIVO);
            if(!carpeta.exists()){
                carpeta.mkdir();
            }
            if(!archivo.exists()){
                archivo.createNewFile();
            }
        }catch(IOException e){
            System.err.println(e);
        }   
    }
    
    public void guardar(HashMap<String, Libro> libros){
        try{
            FileWriter fw = new FileWriter(RUTA_ARCHIVO, false);
            BufferedWriter bw = new BufferedWriter(fw);
            
            Iterator it = libros.keySet().iterator();
            while(it.hasNext()){
                String key = (String) it.next();
                bw.write(libros.get(key).toString());
            }
            bw.close();
        }catch(IOException e){
            System.err.println(e);
        }
    }
    
    public HashMap<String, Libro> cargar(){
        HashMap<String, Libro> libros = new HashMap<>();
        
        try{
            FileReader fr = new FileReader(RUTA_ARCHIVO);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            while((linea = br.readLine()) != null){
                ArrayList<String> autores = new ArrayList<>();
                
                String[] datos = linea.split(";");
                String[] datosAutor = datos[2].split("&");
                for (String autor : datosAutor) {
                    if (!autor.isEmpty()) autores.add(autor);
                }
                Libro libro = new Libro(datos[0], datos[1], autores, Double.parseDouble(datos[3]), Integer.parseInt(datos[4]));
                libros.put(datos[0], libro);
            }
            br.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return libros;
    }
    
    
}
