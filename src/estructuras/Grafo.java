package estructuras;

import modelo.Materia;
import java.util.*;

public class Grafo {

    private Map<String, List<String>> adyacencia;

    public Grafo() {
        this.adyacencia = new HashMap<>();
    }

    //Agregar materia
    public void agregarMateria(String codigo) {
        if (!adyacencia.containsKey(codigo)) {
            adyacencia.put(codigo, new ArrayList<>());
        } else {
            System.out.println("La materia " + codigo + " ya existe en el grafo.");
        }
    }

    //Eliminar materia
    public void eliminarMateria(String codigo) {
        if (!adyacencia.containsKey(codigo)) {
            System.out.println("La materia " + codigo + " no existe en el grafo.");
            return;
        }
        // Eliminar el nodo
        adyacencia.remove(codigo);
        // Eliminar todas las aristas que apuntaban a este nodo
        for (List<String> vecinos : adyacencia.values()) {
            vecinos.remove(codigo);
        }
        System.out.println("Materia " + codigo + " eliminada del grafo.");
    }

    //Agregar prerequisito
    public void agregarPrerequisito(String codigoMateria, String codigoPrerequisito) {
        if (!adyacencia.containsKey(codigoMateria)) {
            System.out.println("La materia " + codigoMateria + " no existe.");
            return;
        }
        if (!adyacencia.containsKey(codigoPrerequisito)) {
            System.out.println("El prerequisito " + codigoPrerequisito + " no existe.");
            return;
        }
        if (!adyacencia.get(codigoPrerequisito).contains(codigoMateria)) {
            adyacencia.get(codigoPrerequisito).add(codigoMateria);
        } else {
            System.out.println("Ese prerequisito ya existe.");
        }
    }

    //Get prerequisitos
    // Las materias que necesito para cursar esta materia
    public List<String> getPrerequisitos(String codigo) {
        List<String> prerequisitos = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : adyacencia.entrySet()) {
            if (entry.getValue().contains(codigo)) {
                prerequisitos.add(entry.getKey());
            }
        }
        return prerequisitos;
    }

    //Get desbloquea
    // Las materias que desbloqueo al aprobar esta materia
    public List<String> getDesbloquea(String codigo) {
        if (!adyacencia.containsKey(codigo)) {
            return new ArrayList<>();
        }
        return adyacencia.get(codigo);
    }

    //Orden topologico
    public List<String> ordenTopologico() {
        Map<String, Integer> gradoEntrada = new HashMap<>();
        for (String nodo : adyacencia.keySet()) {
            gradoEntrada.put(nodo, 0);
        }
        // Calcular grado de entrada de cada nodo
        for (List<String> vecinos : adyacencia.values()) {
            for (String vecino : vecinos) {
                gradoEntrada.put(vecino, gradoEntrada.get(vecino) + 1);
            }
        }
        // Cola con nodos de grado de entrada 0
        Queue<String> cola = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : gradoEntrada.entrySet()) {
            if (entry.getValue() == 0) {
                cola.add(entry.getKey());
            }
        }
        // Procesar
        List<String> resultado = new ArrayList<>();
        while (!cola.isEmpty()) {
            String actual = cola.poll();
            resultado.add(actual);
            for (String vecino : adyacencia.get(actual)) {
                gradoEntrada.put(vecino, gradoEntrada.get(vecino) - 1);
                if (gradoEntrada.get(vecino) == 0) {
                    cola.add(vecino);
                }
            }
        }
        if (resultado.size() != adyacencia.size()) {
            System.out.println("Se detectó un ciclo en el grafo.");
            return new ArrayList<>();
        }return resultado;
    }

    //Mostrar grafo
    public void mostrarGrafo() {
        System.out.println("\n- Relaciones de prerequisitos -");
        for (Map.Entry<String, List<String>> entry : adyacencia.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }

}