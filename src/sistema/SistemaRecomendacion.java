package sistema;

import estructuras.ABB;
import estructuras.Grafo;
import modelo.Materia;
import java.util.List;

public class SistemaRecomendacion {
    private ABB abb;
    private Grafo grafo;

    public SistemaRecomendacion() {
        this.abb = new ABB();
        this.grafo = new Grafo();
    }

    //Agregar materia
    public void agregarMateria(Materia materia) {
        abb.insertar(materia);
        grafo.agregarMateria(materia.getCodigo());
        System.out.println("Materia " + materia.getCodigo() + " agregada exitosamente.");
    }

    //Eliminar materia
    public void eliminarMateria(String codigo) {
        Materia materia = abb.buscar(codigo);
        if (materia == null) {
            System.out.println("Materia no encontrada.");
            return;
        }
        abb.eliminar(codigo);
        grafo.eliminarMateria(codigo);
        System.out.println("Materia " + codigo + " eliminada del sistema.");
    }

    //Buscar y recomendar
    public void buscarYRecomendar(String codigo) {
        Materia materia = abb.buscar(codigo);
        if (materia == null) {
            System.out.println("Materia no encontrada.");
            return;
        }

        System.out.println("\n- Información de la materia -");
        System.out.println(materia);

        List<String> prerequisitos = grafo.getPrerequisitos(codigo);
        if (prerequisitos.isEmpty()) {
            System.out.println("Prerequisitos: ninguno");
        } else {
            System.out.println("Prerequisitos: " + prerequisitos);
        }

        List<String> desbloquea = grafo.getDesbloquea(codigo);
        if (desbloquea.isEmpty()) {
            System.out.println("Materias que desbloquea: ninguna");
        } else {
            System.out.println("Materias que desbloquea: " + desbloquea);
        }
    }

    //Modificar materia
    public void modificarMateria(String codigo, String nuevoNombre, int nuevosCreditos, String nuevaArea) {
        abb.modificar(codigo, nuevoNombre, nuevosCreditos, nuevaArea);
    }

    //Agregar prerequisito
    public void agregarPrerequisito(String codigoMateria, String codigoPrerequisito) {
        grafo.agregarPrerequisito(codigoMateria, codigoPrerequisito);
    }

    //Orden recomendado
    public void mostrarOrdenRecomendado(ABB abb) {
        System.out.println("\n- Orden recomendado para cursar las materias -");
        List<String> orden = grafo.ordenTopologico();
        if (orden.isEmpty()) {
            System.out.println("No se pudo calcular el orden.");
            return;
        }
        int i = 1;
        for (String codigo : orden) {
            Materia materia = abb.buscar(codigo);
            System.out.println(i + ". " + materia);
            i++;
        }
    }

    //Listar materias
    public void listarMaterias() {
        abb.inorden();
    }

    //Mostrar grafo
    public void mostrarGrafo() {
        grafo.mostrarGrafo();
    }

    public ABB getAbb() {
        return abb;
    }
    public Grafo getGrafo() {
        return grafo;
    }
}