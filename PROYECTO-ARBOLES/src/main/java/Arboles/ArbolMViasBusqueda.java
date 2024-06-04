/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arboles;

import Excepciones.OrdenInvalidoExcepcion;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author jairo
 */
public class ArbolMViasBusqueda <K extends Comparable<K>, V> implements IArbolBusqueda<K, V>{
    
    protected NodoMVias<K, V> raiz;
    protected int orden;
    protected static final int ORDEN_MINIMO = 3;
    protected static final int POSICION_INVALIDA = -1;

    public ArbolMViasBusqueda() {
        this.orden = ArbolMViasBusqueda.ORDEN_MINIMO;
    }
    
    public ArbolMViasBusqueda(int orden) throws OrdenInvalidoExcepcion {
        if (orden < ArbolMViasBusqueda.ORDEN_MINIMO) {
            throw new OrdenInvalidoExcepcion();

        }
        this.orden = orden;
    }

    @Override
    public void vaciar() {
        this.raiz = NodoMVias.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoMVias.esNodoVacio(raiz);
    }

    @Override
    public int size() {
        if (this.esArbolVacio()) {
            return 0;
        }    
            Queue<NodoMVias<K,V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            int contador =0;
            do {   
                NodoMVias<K,V> nodoActual = colaDeNodos.poll();
                contador ++; 
                for (int i = 0; i <nodoActual.nroDeClavesNoVacias(); i++) {

                    if (!nodoActual.esHijoVacio(i)) {
                        colaDeNodos.offer(nodoActual.getHijo(i));    
                    }
                }//Fin del for   
                if (!nodoActual.esHijoVacio(nodoActual.nroDeClavesNoVacias())) {
                        colaDeNodos.offer(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()));    
                }
                              
            } while (!colaDeNodos.isEmpty()); 
        
        return contador;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }
    
    protected int altura(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaMayor = 0;
        for (int i = 0; i < orden; i++) {
            int alturaDeHijo = altura(nodoActual.getHijo(i));
            if (alturaDeHijo > alturaMayor) {
                alturaMayor = alturaDeHijo;
                
            }
            
        }
        return alturaMayor+1;
    }    
    

    @Override
    public int nivel() {
        return nivel(this.raiz)-1;
    }
    
    protected int nivel(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        int nivelMayor = 0;
        for (int i = 0; i < orden; i++) {
            int nivelDeHijo = nivel(nodoActual.getHijo(i));
            if (nivelDeHijo>nivelMayor) {
                nivelMayor = nivelDeHijo;
                
            }
            
        }
        return nivelMayor+1;
    }

    @Override
    public void insertar(K claveAInsertar, V valorAsociado) {
            if (claveAInsertar == null) {
            throw new IllegalArgumentException("Clave no puede ser nula" + "vacia");
        }

        if (valorAsociado == null) {
            throw new IllegalArgumentException("Valor no puede ser nulo" + "vacio");
        }

        if (this.esArbolVacio()) {
            this.raiz = new NodoMVias<>(this.orden, claveAInsertar, valorAsociado);
            return;
        }
        NodoMVias<K, V> nodoAuxi = this.raiz;
        do {
            int posicionDeClaveAInsertar = this.buscarPosicionDeClave(claveAInsertar,nodoAuxi);
            if (posicionDeClaveAInsertar != POSICION_INVALIDA) { //Cuando la clave ya existe
                nodoAuxi.setValor(posicionDeClaveAInsertar, valorAsociado);
                nodoAuxi = NodoMVias.nodoVacio();
            } else if (nodoAuxi.esHoja()) {
                    //El nodoAuxi es hoja y la clave no esta en el nodo
                    if (nodoAuxi.clavesLLenas()) {
                        int posicionPorDondeBajar = this.buscarPosicionPorDondeBajar(nodoAuxi, claveAInsertar);
                        NodoMVias<K, V> nodoNuevo = new NodoMVias<>(this.orden, claveAInsertar, valorAsociado);
                        nodoAuxi.setHijo(posicionPorDondeBajar, nodoNuevo);
                    } else {
                        this.insertarClaveYValorOrdenado(nodoAuxi, claveAInsertar, valorAsociado);
                    }
                    nodoAuxi = NodoMVias.nodoVacio();
            } else {
                    //El nodoAuxi no es una hoja y ya sabemos que la clave no esta en el nodo
                int posicionPorDondeBajar = this.buscarPosicionPorDondeBajar(nodoAuxi, claveAInsertar);
                    if (nodoAuxi.esHijoVacio(posicionPorDondeBajar)) {
                        NodoMVias<K, V> nodoNuevo = new NodoMVias<>(this.orden, claveAInsertar, valorAsociado);
                        nodoAuxi.setHijo(posicionPorDondeBajar, nodoNuevo);
                        nodoAuxi = NodoMVias.nodoVacio();
                    } else {
                        nodoAuxi = nodoAuxi.getHijo(posicionPorDondeBajar);
                    }
                
            }
        }while (!NodoMVias.esNodoVacio(nodoAuxi));
    }
    
    public int buscarPosicionDeClave(K claveAInsertar,NodoMVias<K, V> nodo) {
          for (int i = 0; i < nodo.nroDeClavesNoVacias(); i++) {
            if (claveAInsertar.compareTo(nodo.getClave(i)) == 0)
                return i;
        }
        return POSICION_INVALIDA;
    }

    public int buscarPosicionPorDondeBajar(NodoMVias<K, V> nodo, K claveAInsertar) {
        for (int i = 0; i < nodo.nroDeClavesNoVacias(); i++) {
            K claveNodo = nodo.getClave(i);
            
            if (claveAInsertar.compareTo(claveNodo) < 0)
                return i;
        }
        
        return nodo.nroDeClavesNoVacias();

    }

    public void insertarClaveYValorOrdenado(NodoMVias<K, V> nodo, K claveAInsertar, V valorAInsertar) {
        int i = nodo.nroDeClavesNoVacias() - 1;
    
        if (nodo.esHoja()) {
            while (i >= 0 && claveAInsertar.compareTo(nodo.getClave(i)) < 0) {
                nodo.setClave(i + 1, nodo.getClave(i));
                nodo.setValor(i + 1, nodo.getValor(i));
                i--;
            }
    
                // Inserta la nueva clave y el nuevo valor en la posición correcta
                nodo.setClave(i + 1, claveAInsertar);
                nodo.setValor(i + 1, valorAInsertar);
            
        }else{
            while (i >= 0 && claveAInsertar.compareTo(nodo.getClave(i)) < 0) {
                nodo.setClave(i + 1, nodo.getClave(i));
                nodo.setValor(i + 1, nodo.getValor(i));
                nodo.setHijo(i + 2, nodo.getHijo(i + 1)); // Mueve los hijos a la derecha
                i--;
            }
         
                // Inserta la nueva clave y el nuevo valor en la posición correcta
                nodo.setClave(i + 1, claveAInsertar);
                nodo.setValor(i + 1, valorAInsertar);

                // Asegura que el hijo derecho de la nueva clave esté bien posicionado
                nodo.setHijo(i + 2, nodo.getHijo(i + 1)); // Inicializa el hijo si no está ya asignado
    
        }
    }

    @Override
    public V eliminar(K clave) {
     if (clave == null) {
            throw new IllegalArgumentException("Clave a Eliminar no puede ser nula");
        }
        V valorARetornar = buscar(clave);
        if (valorARetornar == null) {
            throw new IllegalArgumentException("La clave no existe en el arbol");
        }

        this.raiz = eliminar(this.raiz, clave);

        return valorARetornar;
    }
    
    private NodoMVias<K,V> eliminar(NodoMVias<K,V> nodoActual,K claveAEliminar){
 
        for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveAEliminar.compareTo(claveActual)==0) {
                if (nodoActual.esHoja()) {
                    eliminarClaveDeNodoDePosicion(nodoActual,i);
                    if (nodoActual.nroDeClavesNoVacias()==0) {
                        return NodoMVias.nodoVacio();
                    }
                    return nodoActual;
                }
                // Si llego aca la clave esta en un nodo no hoja
                // CASO 2
                K claveReemplazo;
                if (hayHijosMasAdelante(nodoActual, i)) {
                    claveReemplazo = buscarSucesorInorden(nodoActual, claveAEliminar);
                // CASO 3    
                }else{
                    claveReemplazo = buscarPredecesorInorden(nodoActual, claveAEliminar);
                }
                // REEMPLAZANDO VALORES
                V valorReemplazo = buscar(claveReemplazo);
                nodoActual = eliminar(nodoActual, claveReemplazo);
                nodoActual.setClave(i, claveReemplazo);
                nodoActual.setValor(i, valorReemplazo);
                return nodoActual;
 
            }
            if (claveAEliminar.compareTo(claveActual)<0) {
                NodoMVias<K,V> supuestoNuevoHijo = eliminar(nodoActual.getHijo(i), claveAEliminar);
                nodoActual.setHijo(i, supuestoNuevoHijo);
                return nodoActual;
            }
        } //fin del for
        
        NodoMVias<K,V> supuestoNuevoHijo = eliminar(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()) ,
                                         claveAEliminar);
        nodoActual.setHijo(nodoActual.nroDeClavesNoVacias(), supuestoNuevoHijo);
        return nodoActual;
        
    }
    
    protected void eliminarClaveDeNodoDePosicion(NodoMVias<K,V> nodo,int posicionDeclaveAEliminar){
            for (int i = posicionDeclaveAEliminar; i < nodo.nroDeClavesNoVacias()-1; i++) {
                nodo.setClave(i, nodo.getClave(i+1));
                nodo.setValor(i, nodo.getValor(i+1));
              
            }
            nodo.setClave(nodo.nroDeClavesNoVacias()-1,(K)nodo.datoVacio());
            nodo.setValor(nodo.nroDeClavesNoVacias()-1,(V)nodo.datoVacio());
    }   
        
     public K buscarSucesorInorden(NodoMVias<K,V> nodoActual,K claveAEliminar){
        int posicionDeClaveAEliminar = buscarPosicionDeClave(claveAEliminar,nodoActual);
        int posSucesor = posicionDeClaveAEliminar + 1;
        if (nodoActual.esHijoVacio(posSucesor)) {
            return nodoActual.getClave(posSucesor) ;    
        }else{
            return nodoActual.getHijo(posSucesor).getClave(0);
        }

    }
    
    public boolean hayHijosMasAdelante(NodoMVias<K,V> nodoActual,int posicionDeclaveAEliminar){
        int posicionDeClave = posicionDeclaveAEliminar + 1;
        boolean existeHijoMasAdelante=false;
        for (int i = posicionDeClave; i < nodoActual.nroDeClavesNoVacias()-1; i++) {
            if (!nodoActual.esHijoVacio(i)) {
                existeHijoMasAdelante = true;
                return existeHijoMasAdelante;
            }
        }
        
        if (!nodoActual.esHijoVacio(nodoActual.nroDeClavesNoVacias())) {
                existeHijoMasAdelante = true;
                return existeHijoMasAdelante;
        }
        
        return existeHijoMasAdelante;
        
    }
    
    private K buscarPredecesorInorden(NodoMVias<K,V> nodoActual,K claveAEliminar){
        int posicionDeClaveAEliminar = buscarPosicionDeClave(claveAEliminar,nodoActual);
        int posPredecesor = posicionDeClaveAEliminar -1;
        if (!nodoActual.esHijoVacio(posicionDeClaveAEliminar)) {
            NodoMVias<K,V> nodoHijo = nodoActual.getHijo(posicionDeClaveAEliminar);
            return nodoHijo.getClave(nodoHijo.nroDeClavesNoVacias()-1) ;    
        }else{
            return nodoActual.getClave(posPredecesor);
        }
    }    

    @Override
    public boolean contine(K clave) {
        return this.buscar(clave) != null;
    }

    @Override
    public V buscar(K claveABuscar) {
         if (claveABuscar == null) {
            throw new IllegalArgumentException("No se puede buscar clave nula");
        }
        if (!this.esArbolVacio()) {
            NodoMVias<K, V> nodoActual = this.raiz;
            do {
                boolean cambieDeNodo = false;
                for (int i = 0; i < nodoActual.nroDeClavesNoVacias()
                        && !cambieDeNodo; i++) {
                    K claveActual = nodoActual.getClave(i);
                    if (claveABuscar.compareTo(claveActual) == 0) {
                        return nodoActual.getValor(i);
                    }

                    if (claveABuscar.compareTo(claveActual) < 0) {
                        cambieDeNodo = true;
                        nodoActual = nodoActual.getHijo(i);
                    }

                }//Necesita de este bucle para procesar un nodo
                if (!cambieDeNodo) {
                    nodoActual = nodoActual.getHijo(nodoActual.nroDeClavesNoVacias());
                }

            } while (!NodoMVias.esNodoVacio(nodoActual));

        }
        return (V) NodoMVias.datoVacio();
    }

    @Override
    public List<K> recorridoEnInOrden() {
        List<K> recorrido = new LinkedList<>();

        if (!esArbolVacio()) {
            recorridoEnInOrdenRec(this.raiz, recorrido);
        }
        return recorrido;
    }
    
    private void recorridoEnInOrdenRec(NodoMVias<K,V> nodoActual,List<K> recorrido){
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        
        for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
            recorridoEnInOrdenRec(nodoActual.getHijo(i), recorrido);
            recorrido.add(nodoActual.getClave(i));
        }     
        recorridoEnInOrdenRec(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()), recorrido);
    }

    @Override
    public List<K> recorridoEnPreOrden() {
         List<K> recorrido = new LinkedList<>();

        if (!esArbolVacio()) {
            recorridoEnPreOrdenRec(this.raiz, recorrido);
        }
        return recorrido;
    }
    
    private void recorridoEnPreOrdenRec(NodoMVias<K,V> nodoActual,List<K> recorrido){
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }

   
        for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
            recorrido.add(nodoActual.getClave(i));
            recorridoEnPreOrdenRec(nodoActual.getHijo(i), recorrido);
        }
        
        recorridoEnPreOrdenRec(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()), recorrido);
        
    }
        

    @Override
    public List<K> recorridoEnPosOrden() {
     List<K> recorrido = new LinkedList<>();

        if (!esArbolVacio()) {
            recorridoEnPosOrdenRec(this.raiz, recorrido);
        }
        return recorrido;
    }
    
    private void recorridoEnPosOrdenRec(NodoMVias<K,V> nodoActual,List<K> recorrido){
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnPosOrdenRec(nodoActual.getHijo(0), recorrido);
        for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
            recorridoEnPosOrdenRec(nodoActual.getHijo(i+1), recorrido);
            recorrido.add(nodoActual.getClave(i));
        }
        
    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        
        if (!this.esArbolVacio()) {
            Queue<NodoMVias<K,V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);

            do {   
                NodoMVias<K,V> nodoActual = colaDeNodos.poll();
                 
                for (int i = 0; i <nodoActual.nroDeClavesNoVacias(); i++) {
                    recorrido.add(nodoActual.getClave(i));

                    if (!nodoActual.esHijoVacio(i)) {
                        colaDeNodos.offer(nodoActual.getHijo(i));    
                    }
                }//Fin del for   
                if (!nodoActual.esHijoVacio(nodoActual.nroDeClavesNoVacias())) {
                        colaDeNodos.offer(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()));    
                }
                              
            } while (!colaDeNodos.isEmpty()); 
        }
        return recorrido;

    }
    
}
