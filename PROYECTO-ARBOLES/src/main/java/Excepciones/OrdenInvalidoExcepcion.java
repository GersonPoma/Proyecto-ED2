/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author jairo
 */
public class OrdenInvalidoExcepcion extends Exception{
    public OrdenInvalidoExcepcion() {
        super("Orden del arbol debe ser almenos 3");
    }
    

    public OrdenInvalidoExcepcion(String message) {
        super(message);
    }
    
}
