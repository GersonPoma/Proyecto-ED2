/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Arboles;

import java.util.List;

/**
 *
 * @author jairo
 */
public interface IArbolBusqueda <K extends Comparable<K>,V> {
    void vaciar();
    boolean esArbolVacio();
    int size();
    int altura();
    int nivel();
    void insertar (K clave,V valor);
    V eliminar(K clave);
    boolean contine(K clave);
    V buscar(K clave);
    List<K> recorridoEnInOrden();
    List<K> recorridoEnPreOrden();
    List<K> recorridoEnPosOrden();
    List<K> recorridoPorNiveles();
    
}
