package estructuras;

import modelo.Materia;
import modelo.NodoABB;

public class ABB {

    private NodoABB raiz;

    public ABB() {
        this.raiz = null;
    }

    //Insertar
    public void insertar(Materia materia) {
        raiz = insertarRec(raiz, materia);
    }

    private NodoABB insertarRec(NodoABB nodo, Materia materia) {
        if (nodo == null) {
            return new NodoABB(materia);
        }
        int comparacion = materia.getCodigo().compareTo(nodo.getMateria().getCodigo());
        if (comparacion < 0) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), materia));
        } else if (comparacion > 0) {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), materia));
        } else {
            System.out.println("La materia " + materia.getCodigo() + " ya existe.");
        }
        return nodo;
    }

    //Buscar
    public Materia buscar(String codigo) {
        return buscarRec(raiz, codigo);
    }

    private Materia buscarRec(NodoABB nodo, String codigo) {
        if (nodo == null) {
            return null;
        }
        int comparacion = codigo.compareTo(nodo.getMateria().getCodigo());
        if (comparacion == 0) {
            return nodo.getMateria();
        } else if (comparacion < 0) {
            return buscarRec(nodo.getIzquierdo(), codigo);
        } else {
            return buscarRec(nodo.getDerecho(), codigo);
        }
    }

    //Eliminar
    public void eliminar(String codigo) {
        raiz = eliminarRec(raiz, codigo);
    }

    private NodoABB eliminarRec(NodoABB nodo, String codigo) {
        if (nodo == null) {
            System.out.println("Materia no encontrada.");
            return null;
        }
        int comparacion = codigo.compareTo(nodo.getMateria().getCodigo());
        if (comparacion < 0) {
            nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), codigo));
        } else if (comparacion > 0) {
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), codigo));
        } else {
            // Caso 1: nodo hoja
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                return null;
            }
            // Caso 2: un solo hijo
            if (nodo.getIzquierdo() == null) return nodo.getDerecho();
            if (nodo.getDerecho() == null) return nodo.getIzquierdo();
            // Caso 3: dos hijos → buscar sucesor inorden
            Materia sucesor = minimoNodo(nodo.getDerecho());
            nodo.setMateria(sucesor);
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), sucesor.getCodigo()));
        }
        return nodo;
    }

    private Materia minimoNodo(NodoABB nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo.getMateria();
    }

    //Modificar
    public void modificar(String codigo, String nuevoNombre, int nuevosCreditos, String nuevaArea) {
        Materia materia = buscar(codigo);
        if (materia != null) {
            materia.setNombre(nuevoNombre);
            materia.setCreditos(nuevosCreditos);
            materia.setArea(nuevaArea);
            System.out.println("Materia modificada exitosamente.");
        } else {
            System.out.println("Materia no encontrada.");
        }
    }

    //INORDEN
    public void inorden() {
        System.out.println("\n- Listado de materias (orden alfabético) -");
        inordenRec(raiz);
    }

    private void inordenRec(NodoABB nodo) {
        if (nodo != null) {
            inordenRec(nodo.getIzquierdo());
            System.out.println(nodo.getMateria());
            inordenRec(nodo.getDerecho());
        }
    }

}