/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arboles;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jairo
 */
public class NodoMVias <K extends Comparable<K>, V> {
    
    private List<K> listaDeClaves;
    private List<V> listaDeValores;
    private List<NodoMVias<K, V>> listaDeHijos;

    public static NodoMVias nodoVacio() {
        return null;
    }

    public static Object datoVacio() {
        return null;
    }

    public NodoMVias(int orden) {
        this.listaDeClaves = new ArrayList<>();
        this.listaDeValores = new ArrayList<>();
        this.listaDeHijos = new ArrayList<>();
        for (int i = 0; i < (orden - 1); i++) {
            listaDeClaves.add((K) NodoMVias.datoVacio());
            listaDeValores.add((V) NodoMVias.datoVacio());
            listaDeHijos.add(NodoMVias.nodoVacio());
        }
        this.listaDeHijos.add(NodoMVias.nodoVacio());
    }

    public NodoMVias(int orden, K primerClave, V primerValor) {
        this(orden);
        this.listaDeClaves.set(0, primerClave);
        this.listaDeValores.set(0, primerValor);
    }

    public K getClave(int posicion) {
        return this.listaDeClaves.get(posicion);
    }

    public void setClave(int posicion, K unaClave) {
        this.listaDeClaves.set(posicion, unaClave);
    }

    public V getValor(int posicion) {
        return this.listaDeValores.get(posicion);
    }

    public void setValor(int posicion, V unValor) {
        this.listaDeValores.set(posicion, unValor);
    }

    public NodoMVias<K, V> getHijo(int posicion) {
        return this.listaDeHijos.get(posicion);
    }

    public void setHijo(int posicion, NodoMVias<K, V> unHijo) {
        this.listaDeHijos.set(posicion, unHijo);
    }

    public static boolean esNodoVacio(NodoMVias nodo) {
        return nodo == NodoMVias.nodoVacio();
    }

    public boolean esClaveVacia(int posicion) {
        return this.listaDeClaves.get(posicion) == NodoMVias.datoVacio();
    }

    public boolean esHijoVacio(int posicion) {
        return NodoMVias.esNodoVacio(this.getHijo(posicion));
    }

    public boolean esDatoVacio(int posicion) {
        return this.getValor(posicion) == NodoMVias.datoVacio();
    }

    public boolean esHoja() {
        for (int i = 0; i < this.listaDeHijos.size(); i++) {
            if (!this.esHijoVacio(i)) {
                return false;
            }
        }
        return true;
    }  
        
    public int nroDeClavesNoVacias() {
        int cantidad = 0;
        for (int i = 0; i < this.listaDeClaves.size(); i++) {
            if (!this.esDatoVacio(i)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public boolean hayClavesNoVacias() {
        return this.nroDeClavesNoVacias() != 0;
    }

    public boolean clavesLLenas() {
        return this.nroDeClavesNoVacias() == this.listaDeClaves.size();

    }
    
    public String show() {
        String str = "[";
        for (int i = 0; i < listaDeClaves.size(); i++) {
            if (!esClaveVacia(i)) {
                str += listaDeClaves.get(i) + "|" + listaDeValores.get(i);
            } else {
                str += "null";
            }
            if (i < listaDeClaves.size() - 1) str += ",  ";
        }
        return str + "]";
    }
    
}
