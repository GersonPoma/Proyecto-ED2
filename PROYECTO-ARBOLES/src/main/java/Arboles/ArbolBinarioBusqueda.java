/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arboles;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author jairo
 */
public class ArbolBinarioBusqueda <K extends Comparable<K>, V> implements
        IArbolBusqueda<K, V> {
    
    protected NodoBinario<K, V> raiz;
    
    public ArbolBinarioBusqueda() {
        //*
    }

    public ArbolBinarioBusqueda(List<K> clavesInOrden, List<V> valoresInOrden, List<K> clavesNoInOrden, List<V> valoresNoInOrden, boolean esPostOrden) {
        if (clavesInOrden.size() != clavesNoInOrden.size() || valoresInOrden.size() != valoresNoInOrden.size() || clavesInOrden.size() != valoresInOrden.size() || clavesNoInOrden.size() != valoresNoInOrden.size()) {
            throw new IllegalArgumentException("Las listas deben ser del mismo tamaño.");
        }
        if (esPostOrden) {
            this.raiz = construirConPostOrden(clavesInOrden, valoresInOrden, clavesNoInOrden, valoresNoInOrden, 0, clavesInOrden.size() - 1, 0, clavesNoInOrden.size() - 1);
        } else {
            this.raiz = construirConPreOrden(clavesInOrden, valoresInOrden, clavesNoInOrden, valoresNoInOrden, 0, clavesInOrden.size() - 1, 0, clavesNoInOrden.size() - 1);
        }
    }

    private NodoBinario<K, V> construirConPostOrden(List<K> clavesInOrder, List<V> valoresInOrder,
            List<K> clavesNoInOrder, List<V> valoresNoInOrder,
            int inOrderInicio, int inOrderFin,
            int postOrderInicio, int postOrderFin) {
        if (inOrderInicio > inOrderFin || postOrderInicio > postOrderFin) {
            return null;
        }
// Crear la raíz a partir del último elemento del recorrido PostOrden: 
        K raizClave = clavesNoInOrder.get(postOrderFin);
        V raizValor = valoresNoInOrder.get(postOrderFin);
        NodoBinario<K, V> raizNodo = new NodoBinario<>(raizClave, raizValor);
// Buscar el índice de la raíz en el recorrido InOrden: 
        int raizIndexInOrden = clavesInOrder.indexOf(raizClave);
// Construir las ramas usando recursión: 
        raizNodo.setHijoIzquierdo(construirConPostOrden(clavesInOrder, valoresInOrder,
                clavesNoInOrder, valoresNoInOrder, inOrderInicio, raizIndexInOrden - 1,
                postOrderInicio, postOrderInicio + (raizIndexInOrden - inOrderInicio) - 1));
        raizNodo.setHijoDerecho(construirConPostOrden(clavesInOrder, valoresInOrder,
                clavesNoInOrder, valoresNoInOrder, raizIndexInOrden + 1, inOrderFin,
                postOrderInicio + (raizIndexInOrden - inOrderInicio), postOrderFin - 1));
        return raizNodo;
    }

    private NodoBinario<K, V> construirConPreOrden(List<K> clavesInOrden, List<V> valoresInOrden,
            List<K> clavesPreOrden, List<V> valoresPreOrden,
            int inOrderInicio, int inOrderFin,
            int preOrderInicio, int preOrderFin) {
        if (inOrderInicio > inOrderFin || preOrderInicio > preOrderFin) {
            return null;
        }
// Crear la raíz a partir del primer elemento del recorrido en PreOrden 
        K raizClave = clavesPreOrden.get(preOrderInicio);
        V raizValor = valoresPreOrden.get(preOrderInicio);
        NodoBinario<K, V> raizNodo = new NodoBinario<>(raizClave, raizValor);
// Encontrar el índice de la raíz en el recorrido InOrden 
        int raizIndexInOrden = clavesInOrden.indexOf(raizClave);
// Calcular el número de nodos restantes en la rama izquierda 
        int leftSubtreeSize = raizIndexInOrden - inOrderInicio;
// Construir las ramas izquierda y derecha con recursión 
        raizNodo.setHijoIzquierdo(construirConPreOrden(clavesInOrden, valoresInOrden, clavesPreOrden, valoresPreOrden,
                inOrderInicio, raizIndexInOrden - 1,
                preOrderInicio + 1, preOrderInicio + leftSubtreeSize));
        raizNodo.setHijoDerecho(construirConPreOrden(clavesInOrden, valoresInOrden, clavesPreOrden, valoresPreOrden,
                raizIndexInOrden + 1, inOrderFin,
                preOrderInicio + leftSubtreeSize + 1, preOrderFin));
        return raizNodo;
    }
    
    @Override
    public void vaciar() {
        this.raiz = (NodoBinario<K, V>) NodoBinario.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }

    @Override
    public int size() {
        int cantidad = 0;

        if (!esArbolVacio()) {
            Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
            pilaDeNodos.push(this.raiz);

            do {
                NodoBinario<K, V> puntero = pilaDeNodos.pop();
                cantidad++;

                if (!puntero.esVacioHijoDerecho()) {
                    pilaDeNodos.push(puntero.getHijoDerecho());
                }
                if (!puntero.esVacioHijoIzquierdo()) {
                    pilaDeNodos.push(puntero.getHijoIzquierdo());
                }

            } while (!pilaDeNodos.isEmpty());
        }

        return cantidad;
    }

    @Override
    public int altura() {
            return altura(this.raiz);
    }
    
    protected int altura(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }

        int alturaIzq = altura(nodoActual.getHijoIzquierdo());
        int alturaDer = altura(nodoActual.getHijoDerecho());

        return alturaIzq > alturaDer ? alturaIzq + 1 : alturaDer + 1;
    }

    @Override
    public int nivel() {
        return nivel(this.raiz);
    }
    
    protected int nivel(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return -1;
        }

        int alturaIzq = nivel(nodoActual.getHijoIzquierdo());
        int alturaDer = nivel(nodoActual.getHijoDerecho());

        return alturaIzq > alturaDer ? alturaIzq + 1 : alturaDer + 1;
    }

    @Override
    public void insertar(K claveAInsertar, V valorAsociado) {
        if (claveAInsertar == null) {
            throw new IllegalArgumentException("Clave no puede ser nula" + "vacia");
        }

        if (valorAsociado == null) {
            throw new IllegalArgumentException("Valor no puede ser nulo" + "vacio");
        }
        //Primer nodo insertado se convierte en la raiz
        if (this.esArbolVacio()) {
            this.raiz = new NodoBinario<>(claveAInsertar, valorAsociado);
            return;
        }

        NodoBinario<K, V> nodoAnterior = NodoBinario.nodoVacio(); //Crea un nodo vacio (un  null)
        NodoBinario<K, V> nodoActual = this.raiz;

        // En esta parte UBICA donde va a insertar
        // si es menor a la clave va por Izquierda
        // si es mayor a la clave va por Derecha
        do {
            K claveDelNodoActual = nodoActual.getClave(); // clave =10
            int comparacion = claveAInsertar.compareTo(claveDelNodoActual); // 9 compareTo(10) == negativo
            nodoAnterior = nodoActual; // nodoanterior= (clave10)
            if (comparacion < 0) { // negativo<0 ?
                nodoActual = nodoActual.getHijoIzquierdo();
            } else if (comparacion > 0) { //positivo >0 ?
                nodoActual = nodoActual.getHijoDerecho();
            } else { // cuando es == 0
                nodoActual.setValor(valorAsociado);
                return;
            }

        } while (!NodoBinario.esNodoVacio(nodoActual));

        //En esta parte inserta
        //Si la clave es menor inserta por Izquierda
        //Si la clave es mayor inserta por Derecha 
        NodoBinario<K, V> nodoNuevo = new NodoBinario<>(claveAInsertar, valorAsociado);
        if (claveAInsertar.compareTo(nodoAnterior.getClave()) < 0) { // negativo<0 ?
            nodoAnterior.setHijoIzquierdo(nodoNuevo);
        } else {
            nodoAnterior.setHijoDerecho(nodoNuevo);
        }
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
            return nodoActual;
        }

        //cuando toca ir por la Derecha 
        if (claveAEliminar.compareTo(claveDelNodoActual) > 0) {
            NodoBinario<K, V> supuestoNuevoHijoDerecho = eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
            nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
            return nodoActual;
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

    protected NodoBinario<K, V> obtenerNodoDelSucesor(NodoBinario<K, V> nodo) {
        NodoBinario<K, V> nodoAnterior = nodo;
        while (!NodoBinario.esNodoVacio(nodo)) {
            nodoAnterior = nodo;
            nodo = nodo.getHijoIzquierdo();

        }
        return nodoAnterior;
    }

    @Override
    public boolean contine(K clave) {
        return buscar(clave) != null;
    }

    @Override
    public V buscar(K clave) {
        if (esArbolVacio()) {
            return null;
        }

        if (clave.compareTo(this.raiz.getClave()) == 0) {
            return this.raiz.getValor();
        }

        NodoBinario<K, V> nodoActual = this.raiz;

        do {
            K claveActual = nodoActual.getClave();
            int comparacion = clave.compareTo(claveActual);

            if (comparacion < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else if (comparacion > 0) {
                nodoActual = nodoActual.getHijoDerecho();
            } else {
                return nodoActual.getValor();
            }
        } while (!NodoBinario.esNodoVacio(nodoActual));
        /*
        Si llego a este punto es que no existe la clave que estamos buscando,
        por lo tanto se devuelve null
         */
        return null;
    }

    @Override
    public List<K> recorridoEnInOrden() {
       List<K> recorrido = new LinkedList<>();
        if (!esArbolVacio()) {
            Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
            NodoBinario<K, V> nodoActual = this.raiz;

            do {
                if (nodoActual != NodoBinario.nodoVacio()) {
                    //Mete a la pila todos los hijos izquierdo    
                    pilaDeNodos.push(nodoActual);
                    nodoActual = nodoActual.getHijoIzquierdo();
                } else {
                    nodoActual = pilaDeNodos.pop();
                    recorrido.add(nodoActual.getClave());
                    nodoActual = nodoActual.getHijoDerecho();
                }
            } while ((!pilaDeNodos.empty())
                    || (!NodoBinario.esNodoVacio(nodoActual)));
        }

        return recorrido;

    }

    @Override
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new LinkedList<>();
        if (!this.esArbolVacio()) {
            Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
            pilaDeNodos.push(this.raiz);
            do {
                // System.out.println(pilaDeNodos.size());
                NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
                recorrido.add(nodoActual.getClave());

                if (!nodoActual.esVacioHijoDerecho()) {
                    pilaDeNodos.push(nodoActual.getHijoDerecho());
                }
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    pilaDeNodos.push(nodoActual.getHijoIzquierdo());
                }

            } while (!pilaDeNodos.isEmpty());
        }
        return recorrido;
    }

    @Override
    public List<K> recorridoEnPosOrden() {
        List<K> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Stack<NodoBinario<K, V>> pila = new Stack<>();
            this.meterALaPilaParaPosOrden(pila, this.raiz);
            do {
                NodoBinario<K, V> nodoActual = pila.pop();
                recorrido.add(nodoActual.getClave());
                if (!pila.isEmpty()) {
                    NodoBinario<K, V> nodoDelTope = pila.peek();
                    if (nodoDelTope.getHijoIzquierdo() == nodoActual) {
                        meterALaPilaParaPosOrden(pila, nodoDelTope.getHijoDerecho());
                    }
                }
            } while (!pila.isEmpty());
        }
        return recorrido;
    }
    
    private void meterALaPilaParaPosOrden(Stack<NodoBinario<K, V>> pila, NodoBinario<K, V> nodoActual) {
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            pila.push(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo()) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            do {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                recorrido.add(nodoActual.getClave());
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
            } while (!colaDeNodos.isEmpty());
        }
        return recorrido;
    }
    
}
