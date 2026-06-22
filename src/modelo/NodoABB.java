package modelo;

public class NodoABB {

    private Materia materia;
    private NodoABB izquierdo;
    private NodoABB derecho;

    public NodoABB(Materia materia) {
        this.materia = materia;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Materia getMateria() {
        return materia;
    }
    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    public NodoABB getIzquierdo() {
        return izquierdo;
    }
    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }
    public NodoABB getDerecho() {
        return derecho;
    }
    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }
}