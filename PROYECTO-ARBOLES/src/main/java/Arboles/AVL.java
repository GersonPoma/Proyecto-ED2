/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arboles;

import java.util.List;

/**
 *
 * @author jairo
 * @param <K>
 * @param <V>
 */
public class AVL <K extends Comparable<K>,V> extends ArbolBinarioBusqueda<K,V>{
    private static final byte RANGO_SUPERIOR = 1;
    private static final byte RANGO_INFERIOR = -1;
    
    public AVL() {
        
    }
    
    public AVL(List<K> clavesInOrden, List<V> valoresInOrden,
            List<K> clavesNoInOrden, List<V> valoresNoInOrden,
            boolean esPostOrden) {
        super(clavesInOrden, valoresInOrden,
                clavesNoInOrden, valoresNoInOrden, esPostOrden);
    }

    
    
    @Override
     public void insertar(K claveAInsertar,V valorAsociado){
        if (claveAInsertar == null) {
            throw new IllegalArgumentException("Clave no puede ser nula" + "vacia");
        }
        
        if (valorAsociado == null) {
            throw new IllegalArgumentException("Valor no puede ser nulo" + "vacio");
        }
        //Primer nodo insertado se convierte en la raiz
        
        super.raiz = this.insertar(this.raiz,claveAInsertar, valorAsociado);
        }

    private NodoBinario<K, V> insertar(NodoBinario<K, V> nodoActual, K claveAInsertar, V valorAsociado) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            NodoBinario<K,V> nodoNuevo = new NodoBinario<>(claveAInsertar, valorAsociado);
            return nodoNuevo;   
        }
        K claveDelNodoActual = nodoActual.getClave();
        if (claveAInsertar.compareTo(claveDelNodoActual)<0) {
                NodoBinario<K,V> supuestoNuevoHI = insertar
                (nodoActual.getHijoIzquierdo(), claveAInsertar, valorAsociado);
                nodoActual.setHijoIzquierdo(supuestoNuevoHI);
                return this.balancear (nodoActual);
        }
        
        if (claveAInsertar.compareTo(claveDelNodoActual)>0) {
                NodoBinario<K,V> supuestoNuevoHD = insertar
                (nodoActual.getHijoDerecho(), claveAInsertar, valorAsociado);
                nodoActual.setHijoDerecho(supuestoNuevoHD);
                return this.balancear (nodoActual);
        }
        // Si llega aqui quiere decir que en el nodo actual esta la clave a insertar
        nodoActual.setValor(valorAsociado);
        return nodoActual;
    }
    
    @Override
    public V eliminar(K clave) {
          //        RECURSIVO
        V valorARetornar = buscar(clave);

        if (valorARetornar == null) {
            return null;
        }

        this.raiz = eliminar(this.raiz, clave);

        return valorARetornar;
    }
    
    private NodoBinario<K, V> eliminar(NodoBinario<K, V> nodoActual, K claveAEliminar) {
        //solo para souts
        K claveDelNodoActual = nodoActual.getClave();
        //cuando toca ir por la Izquierda
        if (claveAEliminar.compareTo(claveDelNodoActual) < 0) {
            NodoBinario<K, V> supuestoHijoIzquierdo = eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
            nodoActual.setHijoIzquierdo(supuestoHijoIzquierdo);
            return this.balancear(nodoActual);
        }

        //cuando toca ir por la Derecha 
        if (claveAEliminar.compareTo(claveDelNodoActual) > 0) {
            NodoBinario<K, V> supuestoNuevoHijoDerecho = eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
            nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
            return this.balancear(nodoActual);
        }

        //Caso 1
        if (nodoActual.esHoja()) {
            return NodoBinario.nodoVacio();
        }

        //Caso 2.1
        if (!nodoActual.esVacioHijoIzquierdo()
                && nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoIzquierdo();

        }
        //Caso 2.2
        if (nodoActual.esVacioHijoIzquierdo()
                && !nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoDerecho();
        }
        //Caso 3
        NodoBinario<K, V> nodoDelSucesor = this.obtenerNodoDelSucesor(
                nodoActual.getHijoDerecho());
       
        
        NodoBinario<K, V> supuestoHijoDerecho
                = eliminar(nodoActual.getHijoDerecho(), nodoDelSucesor.getClave());
        nodoActual.setHijoDerecho(supuestoHijoDerecho);
        nodoActual.setClave(nodoDelSucesor.getClave());
        nodoActual.setValor(nodoDelSucesor.getValor());

        
        return nodoActual;

    }

    private NodoBinario<K, V> balancear(NodoBinario<K, V> nodoActual) {
        int alturaPorIzquierda = super.altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha = super.altura(nodoActual.getHijoDerecho());
        int diferenciaDeAltura = alturaPorIzquierda - alturaPorDerecha;
        
        if (diferenciaDeAltura > AVL.RANGO_SUPERIOR) { //Para saber si es rotacion a la izquierda o derecha
            //SI HAY QUE BALANCEAR POR DERECHA
            NodoBinario<K,V> hijoIzquierdoDelAct = nodoActual.getHijoIzquierdo();
            alturaPorIzquierda = super.altura(hijoIzquierdoDelAct.getHijoIzquierdo());
            alturaPorDerecha = super.altura(hijoIzquierdoDelAct.getHijoDerecho());
            if (alturaPorDerecha > alturaPorIzquierda) { // Para saber si es simple o doble
                return rotacionDobleADerecha(nodoActual);  
            }
            return rotacionSimpleADerecha(nodoActual);
        }else if (diferenciaDeAltura < AVL.RANGO_INFERIOR) {
            //SI HAY QUE BALANCEAR POR IZQUIERDA
            NodoBinario<K,V> hijoDerechoDelAct = nodoActual.getHijoDerecho();
            alturaPorIzquierda = super.altura(hijoDerechoDelAct.getHijoIzquierdo());
            alturaPorDerecha = super.altura(hijoDerechoDelAct.getHijoDerecho());
            if (alturaPorDerecha < alturaPorIzquierda) {
                return rotacionDobleAIzquierda(nodoActual);  
            }
            return rotacionSimpleAIzquierda(nodoActual);

        }
        return nodoActual;
    }

    protected NodoBinario<K, V> rotacionSimpleADerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K,V> nodoARetornar = nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoARetornar.getHijoDerecho());
        nodoARetornar.setHijoDerecho(nodoActual);
        
        return nodoARetornar;
    }
    
    protected NodoBinario<K, V> rotacionDobleADerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K,V> nodoQueRotaIzquierda = rotacionSimpleAIzquierda(nodoActual.getHijoIzquierdo());
        nodoActual.setHijoIzquierdo(nodoQueRotaIzquierda);

        return this.rotacionSimpleADerecha(nodoActual);
    }
    
    protected NodoBinario<K, V> rotacionSimpleAIzquierda(NodoBinario<K, V> nodoActual) {
        NodoBinario<K,V> nodoARetornar = nodoActual.getHijoDerecho();
        nodoActual.setHijoDerecho(nodoARetornar.getHijoIzquierdo());
        nodoARetornar.setHijoIzquierdo(nodoActual);
        
        return nodoARetornar;
    }
    
    protected NodoBinario<K, V> rotacionDobleAIzquierda(NodoBinario<K, V> nodoActual) {
        NodoBinario<K,V> nodoQueRotaDerecha = rotacionSimpleADerecha(nodoActual.getHijoDerecho());
        nodoActual.setHijoIzquierdo(nodoQueRotaDerecha);

        return this.rotacionSimpleAIzquierda(nodoActual);
    }
    
    
    
    
}
