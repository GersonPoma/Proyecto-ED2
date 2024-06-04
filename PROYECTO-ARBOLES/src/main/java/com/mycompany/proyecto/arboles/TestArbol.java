/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyecto.arboles;

import Arboles.AVL;
import Arboles.ArbolB;
import Arboles.ArbolBinarioBusqueda;
import Arboles.ArbolMViasBusqueda;
import Arboles.IArbolBusqueda;
import Excepciones.OrdenInvalidoExcepcion;
import java.util.Scanner;



/**
 *
 * @author jairo
 */
public class TestArbol {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws OrdenInvalidoExcepcion{ 
        IArbolBusqueda <Integer,String> arbolPrueba;
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("Elija el tipo de Arbol(ABB,AVL,AMB,AB):");
        
        String tipoArbol = entrada.next();
        
        switch (tipoArbol) {
            case "ABB":
                arbolPrueba = new ArbolBinarioBusqueda<Integer, String>();
                break;
            case "AVL":
                arbolPrueba = new AVL<>();
                break; 
            case "AMB":
                arbolPrueba = new ArbolMViasBusqueda<Integer, String>(4);
                break; 
            case "AB":
                arbolPrueba = new ArbolB<Integer, String>(4);
                break;     
            default:
                System.out.println("Tipo de Arbol invalido. usando AVL");
                arbolPrueba = new AVL<>();
                break;
        }
        
        arbolPrueba.insertar(20, "azul");
        arbolPrueba.insertar(18, "rojo");
        arbolPrueba.insertar(25, "amarillo");       
        arbolPrueba.insertar(10, "blanco");
        arbolPrueba.insertar(19, "negro");
        arbolPrueba.insertar(22, "22");
        arbolPrueba.insertar(30, "verde");
        arbolPrueba.insertar(9, "Jairo");
        arbolPrueba.insertar(12, "Jairo");
        arbolPrueba.insertar(24, "dorado");
        arbolPrueba.insertar(29, "negro");
        arbolPrueba.insertar(33, "negro");      
        arbolPrueba.insertar(32, "negro");
        arbolPrueba.insertar(60, "negro");
        arbolPrueba.insertar(65, "65");

        System.out.println("RECORRIDO POR NIVELES: " + arbolPrueba.recorridoEnInOrden());
        System.out.println("altura = " + arbolPrueba.altura());
    
    }
    
}
