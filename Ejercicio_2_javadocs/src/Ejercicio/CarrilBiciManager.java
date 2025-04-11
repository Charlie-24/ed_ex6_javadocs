package Ejercicio;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/*
 * @author CádizTech
 * @version 2.4.0
 */

/*
 * Esta  clase sirve para gestionar un carril bici , los cuales tienen una longitud determinada y cuentan con tramos, estos se pueden ir añadiendo.
 * Tambien cuentan con estados distintos cada tramo. 
 * Se puede generar un informe de cada carril bici junto a sus tramos
 * 
 */

public class CarrilBiciManager {
	
	/*
	 * 
	 * Cada carril bici cuenta con atributos como lo tramos y sus estados
	 * 
	 */
	
	
    private final Map<String, Double> tramos; // nombre del tramo -> longitud en km
    private final Map<String, String> estadoTramos; // nombre del tramo -> estado

    
    /*
     * 
     * Metodo constructor del carril que lo crea con los atributos estanciados en 
     * la clase
     * 
     */
 
    public CarrilBiciManager() {
        this.tramos = new HashMap<>();
        this.estadoTramos = new HashMap<>();
    }
    
   
    /*
     * 
     * Este metodo añade un tramo a el carril bici creado con el que ees llamado,
     * recive dos parametros
     * 
     * @param {@code nombre} nombre del tramo
     * @param {@code longitud} longitud del tramo
     * @throws si el nombre es vacio lanza la excepcion IllegalArgumentException
     * @throws si la longitud del tramo es nula o menor que 0 lanza la excepcion IllegalArgumentException
     * @exception la excepcion IllegalArgumentException indica que no se puede 
     * dejar el nombre vacio o que la longitud debe de ser mayor que 0
     * 
     */

    public void añadirTramo(String nombre, double longitud) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del tramo no puede estar vacío");
        }
        if (longitud <= 0) {
            throw new IllegalArgumentException("La longitud debe ser mayor que cero");
        }
        tramos.put(nombre, longitud);
        estadoTramos.put(nombre, "En servicio");
    }
    
    /*
     * 
     * Este metodo revice dos parametros los cuales se encargan de actualizar el estado 
     * de el tramo indicado
     * 
     * @param {@code nombre} nombre del tramo
     * @param {@code nuevoEstado} nuevo nombre del estado
     * @throw si el nombre del tramo no correspode a ninguno, lanza la excepcion NoSuchElementException
     * @exception NoSuchElementException indica que el nombre introducido del tramo no existe
     * 
     */    
    

    public void actualizarEstado(String nombre, String nuevoEstado) {
        if (!tramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe: " + nombre);
        }
        estadoTramos.put(nombre, nuevoEstado);
    }
    
    public void cambiarEstado(String nombre, String estado) {
        actualizarEstado(nombre, estado);
    }
    
       public String consultarEstado(String nombre) {
        if (!estadoTramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe");
        }
        return estadoTramos.get(nombre);
    }

    public double longitudTotal() {
        return tramos.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public Map<String, Double> obtenerTramos() {
        return Collections.unmodifiableMap(tramos);
    }

    public String generarInforme() {
        StringBuilder sb = new StringBuilder("INFORME DE CARRILES BICI - Bahía de Cádiz\n");
        sb.append("===========================================\n");
        for (String nombre : tramos.keySet()) {
            sb.append("- ").append(nombre).append(" (")
              .append(tramos.get(nombre)).append(" km): ")
              .append(estadoTramos.get(nombre)).append("\n");
        }
        sb.append("Longitud total: ").append(longitudTotal()).append(" km\n");
        return sb.toString();
    }
}