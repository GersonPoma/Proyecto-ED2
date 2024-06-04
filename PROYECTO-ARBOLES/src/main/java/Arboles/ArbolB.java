package Arboles;

import java.util.Stack;
import Excepciones.OrdenInvalidoExcepcion;

/**s
 *
 */
public class ArbolB<K extends Comparable<K>,V> 
        extends ArbolMViasBusqueda<K, V> {
    
    private final int nroMaxDeClaves;
    private final int nroMinDeClaves;
    private final int nroMinDeHijos;
    
    public ArbolB() {
        super();
        this.nroMaxDeClaves = 2;
        this.nroMinDeClaves = 1;
        this.nroMinDeHijos = 2;
    }
    
    public ArbolB(int orden) throws OrdenInvalidoExcepcion {
        super(orden);
        this.nroMaxDeClaves = super.orden - 1;
        this.nroMinDeClaves = this.nroMaxDeClaves / 2;
        this.nroMinDeHijos = this.nroMinDeClaves + 1;       
    }
    
    @Override
    public void insertar(K claveAInsertar, V valorAsociado) {
        if (claveAInsertar == null) {
            throw new IllegalArgumentException("Clave no puede ser nula" +
                    "vacia");
        }

        if (valorAsociado == null) {
            throw new IllegalArgumentException("Valor no puede ser nulo" + 
                    "vacio");
        }
        
        //Primer nodo insertado se convierte en la raiz
        if (this.esArbolVacio()) {
            this.raiz = new NodoMVias<>(this.orden + 1, claveAInsertar,
                    valorAsociado);
            return;
        }
        
        Stack<NodoMVias<K,V>> pilaDeAncestros = new Stack<>();
        NodoMVias<K, V> nodoAux = this.raiz;
        
        do {
            int posicionDeClaveAInsertar = super.buscarPosicionDeClave(claveAInsertar, nodoAux);
            if (posicionDeClaveAInsertar != POSICION_INVALIDA) { //Cuando la clave ya existe
                nodoAux.setValor(posicionDeClaveAInsertar, valorAsociado);
                nodoAux = NodoMVias.nodoVacio();
            } else if (nodoAux.esHoja()) {
                //El nodoAuxi es hoja y la clave no esta en el nodo
                super.insertarClaveYValorOrdenado(nodoAux, claveAInsertar, valorAsociado);
                if (nodoAux.nroDeClavesNoVacias() > this.nroMaxDeClaves) {
                    this.dividirNodo(nodoAux, pilaDeAncestros);
                }
                nodoAux = NodoMVias.nodoVacio();
            } else {
                //El nodoAuxi no es una hoja y ya sabemos que la clave no esta en el nodo
                int posicionPorDondeBajar = super.buscarPosicionPorDondeBajar(nodoAux,
                        claveAInsertar);
                pilaDeAncestros.push(nodoAux);
                nodoAux = nodoAux.getHijo(posicionPorDondeBajar);  
            }
        }while (!NodoMVias.esNodoVacio(nodoAux));
    }
    
    private void dividirNodo(NodoMVias<K,V> nodoActual,
            Stack<NodoMVias<K,V>> pila) {
//        do {
//
//            K claveASubir = nodoActual.getClave(this.nroMinDeClaves);
//            V valorASubir = nodoActual.getValor(this.nroMinDeClaves);
//
//            if (pila.isEmpty()) {
//                NodoMVias<K, V> nuevaRaiz = new NodoMVias<>(this.orden + 1, claveASubir,
//                        valorASubir);
//                NodoMVias<K, V> nuevoHijo1 = new NodoMVias<>(this.orden + 1);
//                NodoMVias<K, V> nuevoHijo2 = new NodoMVias<>(this.orden + 1);
//
//                for (int i = 0; i < this.nroMinDeClaves; i++) {
//                    nuevoHijo1.setClave(i, nodoActual.getClave(i));
//                    nuevoHijo1.setValor(i, nodoActual.getValor(i));
//                }
//
//                for (int i = this.nroMinDeClaves + 1;
//                        i < nodoActual.nroDeClavesNoVacias(); i++) {
//                    nuevoHijo2.setClave(i - this.nroMinDeClaves - 1,
//                            nodoActual.getClave(i));
//                    nuevoHijo2.setValor(i - this.nroMinDeClaves - 1,
//                            nodoActual.getValor(i));
//                }
//
//                nuevaRaiz.setHijo(0, nuevoHijo1);
//                nuevaRaiz.setHijo(1, nuevoHijo2);
//                this.raiz = nuevaRaiz;
//            } else {
//                NodoMVias<K,V> nodoPadre = pila.pop();
//                insertarClaveYValorOrdenado(nodoPadre, claveASubir, valorASubir);
//                int posicionDeLaClaveQueSubio = buscarPosicionDeClave(nodoPadre,
//                        claveASubir);
//                
//                for (int i = 0; i < posicionDeLaClaveQueSubio; i++) {
//                    if (nodoPadre)
//                }
//            }
//        } while (nodoActual.nroDeClavesNoVacias() > this.nroMaxDeClaves);

        do {
            K claveASubir = nodoActual.getClave(this.nroMinDeClaves);
            V valorASubir = nodoActual.getValor(this.nroMinDeClaves);
            
            NodoMVias<K,V> nuevoHijo1 = new NodoMVias<>(orden + 1);
            NodoMVias<K,V> nuevoHijo2 = new NodoMVias<>(orden + 1);
            
            for (int i = 0; i < this.nroMinDeClaves; i++) {
                nuevoHijo1.setClave(i, nodoActual.getClave(i));
                nuevoHijo1.setValor(i, nodoActual.getValor(i));
            }
            
            for (int i = this.nroMinDeClaves + 1;
                    i < nodoActual.nroDeClavesNoVacias(); i++) {
                nuevoHijo2.setClave(i - this.nroMinDeClaves - 1,
                        nodoActual.getClave(i));
                nuevoHijo2.setValor(i - this.nroMinDeClaves - 1,
                        nodoActual.getValor(i));
            }
            
            //falta para los hijos de los nuevos nodos hijos
            for (int i = 0; i < this.nroMinDeHijos; i++) {
                nuevoHijo1.setHijo(i, nodoActual.getHijo(i));
            }
            
            for (int i = this.nroMinDeHijos;
                    i <= nodoActual.nroDeClavesNoVacias(); i++) {
                nuevoHijo2.setHijo(i - this.nroMinDeHijos, nodoActual.getHijo(i));
            }
            
            if (pila.isEmpty()) {
                NodoMVias<K,V> nuevaRaiz = new NodoMVias<>(orden + 1, claveASubir,
                        valorASubir);
                
                nuevaRaiz.setHijo(0, nuevoHijo1);
                nuevaRaiz.setHijo(1, nuevoHijo2);
                this.raiz = nuevaRaiz;
                
                nodoActual = nuevaRaiz;
            } else {
                NodoMVias<K,V> nodoPadre = pila.pop();
                super.insertarClaveYValorOrdenado(nodoPadre, claveASubir, valorASubir);
                int posicionDeLaClaveQueSubio = super.buscarPosicionDeClave(
                         claveASubir,nodoPadre);
                
                for (int i = nodoPadre.nroDeClavesNoVacias();
                        i > posicionDeLaClaveQueSubio + 1; i--) {
                    nodoPadre.setHijo(i, nodoPadre.getHijo(i - 1));
                }
                
                nodoPadre.setHijo(posicionDeLaClaveQueSubio, nuevoHijo1);
                nodoPadre.setHijo(posicionDeLaClaveQueSubio + 1, nuevoHijo2);
                
                nodoActual = nodoPadre;
            }
            
        } while (nodoActual.nroDeClavesNoVacias() > this.nroMaxDeClaves);
    }
    
    @Override
    public V eliminar (K claveAEliminar){
        if (claveAEliminar == null) {
            throw new IllegalArgumentException("Clave no puede ser nula" +
                    "vacia");
        }
        
        Stack<NodoMVias<K,V>> pilaDeAncestros = new Stack<>();
        NodoMVias<K,V> nodoDeLaClaveAEliminar = this.buscarNodoDeLaClave(claveAEliminar,
                pilaDeAncestros);
        // buscarNodoDeLaClave --> metodo a implementar
        if (NodoMVias.esNodoVacio(nodoDeLaClaveAEliminar)) {
            return null;  
        }
        int posicionDeClaveAEliminar = super.buscarPosicionDeClave( //buscarPosicionDeLaClave
                claveAEliminar,nodoDeLaClaveAEliminar);
        V valorARetornar = nodoDeLaClaveAEliminar.getValor(posicionDeClaveAEliminar);
        if (nodoDeLaClaveAEliminar.esHoja()) {
            super.eliminarClaveDeNodoDePosicion(nodoDeLaClaveAEliminar,                 //eliminarClaveDeNodoDePosicion
                    posicionDeClaveAEliminar);
            if (nodoDeLaClaveAEliminar.nroDeClavesNoVacias() < this.nroMinDeClaves) {
                if (pilaDeAncestros.isEmpty()) {
                    if (nodoDeLaClaveAEliminar.nroDeClavesNoVacias() == 0) {
                        super.vaciar();
                    }
                }else{
                    prestarOFusionar(nodoDeLaClaveAEliminar,pilaDeAncestros); //implemetar este  metodo   
                }        
            }
        }else{
            pilaDeAncestros.push(nodoDeLaClaveAEliminar);
            NodoMVias<K,V> nodoDelPredecesor = this.obtenerNodoDelPredecesor(
                        pilaDeAncestros,nodoDeLaClaveAEliminar.getHijo(
                                posicionDeClaveAEliminar));
            K claveDelPredecesor = nodoDelPredecesor.getClave(
                    nodoDelPredecesor.nroDeClavesNoVacias()-1);
            V valorDelPredecesor = nodoDelPredecesor.getValor(
                    nodoDelPredecesor.nroDeClavesNoVacias()-1);
            super.eliminarClaveDeNodoDePosicion(nodoDelPredecesor,
                    nodoDelPredecesor.nroDeClavesNoVacias()-1);
            nodoDeLaClaveAEliminar.setClave(posicionDeClaveAEliminar,
                    claveDelPredecesor);
            nodoDeLaClaveAEliminar.setValor(posicionDeClaveAEliminar,
                    valorDelPredecesor);
            prestarOFusionar(nodoDelPredecesor, pilaDeAncestros);
            
            
        }
        return valorARetornar;    
    }
    
    private NodoMVias<K,V> buscarNodoDeLaClave(K claveAEliminar,
            Stack<NodoMVias<K,V>> pila) {
        NodoMVias<K,V> nodoActual = this.raiz;
        
        do {
            int posicionDeLaClave = super.buscarPosicionDeClave(
                    claveAEliminar,nodoActual);
            
            if (posicionDeLaClave != POSICION_INVALIDA)
                return nodoActual;
            
            int posicionPorDondeBajar = super.buscarPosicionPorDondeBajar(nodoActual,
                    claveAEliminar);
            pila.push(nodoActual);
            nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
        } while (!NodoMVias.esNodoVacio(nodoActual));
        
        return NodoMVias.nodoVacio();
    }
    
    private void prestarOFusionar(NodoMVias<K,V> nodoDeLaClaveAEliminar,
            Stack<NodoMVias<K,V>> pila) {
        while (nodoDeLaClaveAEliminar.nroDeClavesNoVacias() < this.nroMinDeClaves) {
            NodoMVias<K, V> nodoPadre = pila.pop();

            /*posición del nodoDeLaClaveAEliminar en la lista de hijos en el
            nodoPadre*/
            int posDelNodoAEliminar = super.buscarPosicionPorDondeBajar(nodoPadre,
                    nodoDeLaClaveAEliminar.getClave(0));
            NodoMVias<K, V> hermanoIzq, hermanoDer;

            if (posDelNodoAEliminar > 0) {
                hermanoIzq = nodoPadre.getHijo(posDelNodoAEliminar - 1);
            } else {
                hermanoIzq = NodoMVias.nodoVacio();
            }

            if (posDelNodoAEliminar < nodoPadre.nroDeClavesNoVacias()) {
                hermanoDer = nodoPadre.getHijo(posDelNodoAEliminar + 1);
            } else {
                hermanoDer = NodoMVias.nodoVacio();
            }

            if (!NodoMVias.esNodoVacio(hermanoIzq)
                    && hermanoIzq.nroDeClavesNoVacias() > this.nroMinDeClaves) {
                prestarDelHermanoIzquierdo(nodoPadre, nodoDeLaClaveAEliminar,
                        hermanoIzq, posDelNodoAEliminar);
                return;
            } else if (!NodoMVias.esNodoVacio(hermanoDer)
                    && hermanoDer.nroDeClavesNoVacias() > this.nroMinDeClaves) {
                prestarDelHermanoDerecho(nodoPadre, nodoDeLaClaveAEliminar,
                        hermanoDer, posDelNodoAEliminar);
                return;
            } else if (!NodoMVias.esNodoVacio(hermanoIzq)) {
                fusionarConElHermanoIzquierdo(nodoPadre, nodoDeLaClaveAEliminar,
                        hermanoIzq, posDelNodoAEliminar);
            } else {
                fusionarConElHermanoDerecho(nodoPadre, nodoDeLaClaveAEliminar,
                        hermanoDer, posDelNodoAEliminar);
            }
            
            if (nodoPadre.nroDeClavesNoVacias() == 0) {
                this.raiz = nodoDeLaClaveAEliminar;
                return;
            }
            
            if (nodoPadre == this.raiz)
                return;
            
            nodoDeLaClaveAEliminar = nodoPadre;
            
        }
    }
    
    private void prestarDelHermanoIzquierdo(NodoMVias<K,V> nodoPadre,
            NodoMVias<K,V> nodoAEliminar, NodoMVias<K,V> hermanoIzq,
            int posDelNodo) {
        //se obtiene la clave y valor del nodo padre de la posición del nodo
        K claveDelPadre = nodoPadre.getClave(posDelNodo - 1);
        V valorDelPadre = nodoPadre.getValor(posDelNodo - 1);
        
        //se inserta la clave padre en el nodo a eliminar
        super.insertarClaveYValorOrdenado(nodoAEliminar, claveDelPadre, valorDelPadre);
        
        //se obtiene la clave y valor del nodo hermano izquierdo que subirá al nodo padre
        K claveASubir = hermanoIzq.getClave(hermanoIzq.nroDeClavesNoVacias() - 1);
        V valorASubir = hermanoIzq.getValor(hermanoIzq.nroDeClavesNoVacias() - 1);
        
        //se inserta la clave y valor en el nodo padre
        nodoPadre.setClave(posDelNodo - 1, claveASubir);
        nodoPadre.setValor(posDelNodo - 1, valorASubir);
        
        //si el hermano izquierdo no es hoja, se pasa el hijo de la clave que nos
        //prestamos al nodo a eliminar y eliminamos el hijo en el hermano
        if (!hermanoIzq.esHoja()) {
            //como los las claves del hermano izquierdo son menores que las
            //claves del nodo a eliminar se tiene que recorrer los hijos del
            //del nodo a eliminar hacia la derecha
            for (int i = nodoAEliminar.nroDeClavesNoVacias(); i > 0; i--) {
                nodoAEliminar.setHijo(i, nodoAEliminar.getHijo(i - 1));
            }
            nodoAEliminar.setHijo(0,
                    hermanoIzq.getHijo(hermanoIzq.nroDeClavesNoVacias()));
            
            hermanoIzq.setHijo(hermanoIzq.nroDeClavesNoVacias(), NodoMVias.nodoVacio());
        }
        
        //se elimina la clave y valor del hermano izquierdo
        super.eliminarClaveDeNodoDePosicion(hermanoIzq, 
                hermanoIzq.nroDeClavesNoVacias() - 1);
    }
    
    private void prestarDelHermanoDerecho(NodoMVias<K,V> nodoPadre,
            NodoMVias<K,V> nodoAEliminar, NodoMVias<K,V> hermanoDer,
            int posDelNodo) {
        //se obtiene la clave y valor del nodo padre de la posición del nodo
        K clavePadre = nodoPadre.getClave(posDelNodo);
        V valorPadre = nodoPadre.getValor(posDelNodo);
        
        //se inserta la clave padre en el nodo a eliminar
        super.insertarClaveYValorOrdenado(nodoAEliminar, clavePadre, valorPadre);
        
        //se obtiene la clave y valor del nodo hermano derecho que subirá al nodo padre
        K claveASubir = hermanoDer.getClave(0);
        V valorASubir = hermanoDer.getValor(0);
        
        //se inserta la clave y valor en el nodo padre
        nodoPadre.setClave(posDelNodo, claveASubir);
        nodoPadre.setValor(posDelNodo, valorASubir);

        //si el hermano derecho no es hoja, se pasa el hijo de la clave que nos
        //prestamos al nodo a eliminar y eliminamos el hijo en el hermano
        if (!hermanoDer.esHoja()) {
            nodoAEliminar.setHijo(nodoAEliminar.nroDeClavesNoVacias(), 
                    hermanoDer.getHijo(0));
            for (int i = 0; i < hermanoDer.nroDeClavesNoVacias(); i++) {
                hermanoDer.setHijo(i, hermanoDer.getHijo(i + 1));
            }
            hermanoDer.setHijo(hermanoDer.nroDeClavesNoVacias(),
                    NodoMVias.nodoVacio());
        }
        
        //se elimina la clave y valor del hermano derecho
        super.eliminarClaveDeNodoDePosicion(hermanoDer, 0);
    }
    
    private void fusionarConElHermanoIzquierdo(NodoMVias<K,V> nodoPadre,
            NodoMVias<K,V> nodoAEliminar, NodoMVias<K,V> hermanoIzq,
            int posDelNodo) {
        //se obtiene la clave y valor del nodo padre de la posición del nodo
        K clavePadre = nodoPadre.getClave(posDelNodo - 1);
        V valorPadre = nodoPadre.getValor(posDelNodo - 1);
        
        //posición del ultimo hijo del nodo a eliminar antes de la fusion de
        //nodos
        int posDelUltimoHijoAntesDeLaFusion = nodoAEliminar.nroDeClavesNoVacias();
        
        //se inserta la clave y valor padre en el nodo a eliminar
        super.insertarClaveYValorOrdenado(nodoAEliminar, clavePadre, valorPadre);
        
        //se copia las claves del hermano derecho a la clave a eliminar
        for (int i = 0; i < hermanoIzq.nroDeClavesNoVacias(); i++) {
            super.insertarClaveYValorOrdenado(nodoAEliminar,
                    hermanoIzq.getClave(i), hermanoIzq.getValor(i));
        }

        //si el nodo a eliminar y el hermano derecho no son hoja
        //se copian los hijos del hermano al nodo a eliminar
        if (!hermanoIzq.esHoja()) {
            //como los las claves del hermano izquierdo son menores que las
            //claves del nodo a eliminar se tiene que recorrer los hijos del
            //nodo a eliminar hacia la derecha
            int i = nodoAEliminar.nroDeClavesNoVacias();
            while (posDelUltimoHijoAntesDeLaFusion >= 0) {
                nodoAEliminar.setHijo(i, nodoAEliminar.getHijo(posDelUltimoHijoAntesDeLaFusion));
                i--;
                posDelUltimoHijoAntesDeLaFusion--;
            }
            
            for (i = 0; i <= hermanoIzq.nroDeClavesNoVacias(); i++) {
                nodoAEliminar.setHijo(i, hermanoIzq.getHijo(i));
            }
        } 
        
        //se recorren los hijos del hijos del nodo padre desde la posicion del
        //nodo porque se tiene que eliminar la referencia del hijo que
        //acabamos de fusionar
        for (int i = posDelNodo; i < nodoPadre.nroDeClavesNoVacias(); i++) {
            nodoPadre.setHijo(i, nodoPadre.getHijo(i + 1));
        }
        nodoPadre.setHijo(nodoPadre.nroDeClavesNoVacias(), NodoMVias.nodoVacio()); 
        
        //se eliminar la clave y valor del nodo padre que bajamos al nodo a
        //eliminar
        super.eliminarClaveDeNodoDePosicion(nodoPadre, posDelNodo - 1);
        
        //se actualiza el nuevo hijo producto de la fusión en el nodo padre
        nodoPadre.setHijo(posDelNodo - 1, nodoAEliminar);
    }
    
    private void fusionarConElHermanoDerecho(NodoMVias<K,V> nodoPadre,
            NodoMVias<K,V> nodoAEliminar, NodoMVias<K,V> hermanoDer,
            int posDelNodo) {
        //se obtiene la clave y valor del nodo padre de la posición del nodo
        K clavePadre = nodoPadre.getClave(posDelNodo);
        V valorPadre = nodoPadre.getValor(posDelNodo); 
        
        //posición del ultimo hijo del nodo a eliminar antes de la fusion de
        //nodos
        int posDelUltimoHijoAntesDeLaFusion = nodoAEliminar.nroDeClavesNoVacias();          
        
        //se inserta la clave y valor padre en el nodo a eliminar
        super.insertarClaveYValorOrdenado(nodoAEliminar, clavePadre, valorPadre);
        
        //se copia las claves del hermano derecho a la clave a eliminar
        for (int i = 0; i < hermanoDer.nroDeClavesNoVacias(); i++) {
            super.insertarClaveYValorOrdenado(nodoAEliminar,
                    hermanoDer.getClave(i), hermanoDer.getValor(i));
        }
        
        //si el nodo a eliminar y el hermano derecho no son hoja
        //se copian los hijos del hermano al nodo a eliminar
        if (!hermanoDer.esHoja()) {
            for (int i = 0; i <= hermanoDer.nroDeClavesNoVacias(); i++) {
                nodoAEliminar.setHijo(posDelUltimoHijoAntesDeLaFusion + 1,
                        hermanoDer.getHijo(i));
                posDelUltimoHijoAntesDeLaFusion++;
            }
        }
        
        //se recorren los hijos del hijos del nodo padre desde la posicion del
        //nodo + 1 porque se tiene que eliminar la referencia del hijo que
        //acabamos de fusionar
        for (int i = posDelNodo + 1; i < nodoPadre.nroDeClavesNoVacias(); i++) {
            nodoPadre.setHijo(i, nodoPadre.getHijo(i + 1));
        }
        nodoPadre.setHijo(nodoPadre.nroDeClavesNoVacias(), NodoMVias.nodoVacio());
        
        //se eliminar la clave y valor del nodo padre que bajamos al nodo a
        //eliminar
        super.eliminarClaveDeNodoDePosicion(nodoPadre, posDelNodo);
        
        //se actualiza el nuevo hijo producto de la fusión en el nodo padre
        nodoPadre.setHijo(posDelNodo, nodoAEliminar);
    }
    
    private NodoMVias<K,V> obtenerNodoDelPredecesor(Stack<NodoMVias<K,V>> pila,
            NodoMVias<K,V> nodoAEliminar) {
        while (!NodoMVias.esNodoVacio(nodoAEliminar)) {
            pila.push(nodoAEliminar);
            nodoAEliminar = nodoAEliminar.getHijo(nodoAEliminar.nroDeClavesNoVacias());
        }
        
        return pila.pop();
    }
}
