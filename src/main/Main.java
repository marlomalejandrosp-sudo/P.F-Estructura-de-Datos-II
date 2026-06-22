package main;

import modelo.Materia;
import sistema.SistemaRecomendacion;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaRecomendacion sistema = new SistemaRecomendacion();
        cargarDatos(sistema);

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n======================================");
            System.out.println(" SISTEMA DE RECOMENDACIÓN DE MATERIAS");
            System.out.println("======================================");
            System.out.println("1. Buscar materia y ver recomendaciones");
            System.out.println("2. Listar todas las materias (alfabético)");
            System.out.println("3. Ver orden recomendado de cursado");
            System.out.println("4. Agregar nueva materia");
            System.out.println("5. Eliminar materia");
            System.out.println("6. Modificar materia");
            System.out.println("7. Agregar prerequisito");
            System.out.println("8. Ver relaciones de prerequisitos");
            System.out.println("0. Salir");
            System.out.println("======================================");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el código de la materia: ");
                    String codigo = scanner.nextLine().toUpperCase();
                    sistema.buscarYRecomendar(codigo);
                    break;

                case 2:
                    sistema.listarMaterias();
                    break;

                case 3:
                    sistema.mostrarOrdenRecomendado(sistema.getAbb());
                    break;

                case 4:
                    System.out.print("Código: ");
                    String cod = scanner.nextLine().toUpperCase();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Créditos: ");
                    int creditos = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Semestre (1-4): ");
                    int semestre = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Área: ");
                    String area = scanner.nextLine();
                    sistema.agregarMateria(new Materia(cod, nombre, creditos, semestre, area));
                    break;

                case 5:
                    System.out.print("Ingrese el código de la materia a eliminar: ");
                    String codEliminar = scanner.nextLine().toUpperCase();
                    sistema.eliminarMateria(codEliminar);
                    break;

                case 6:
                    System.out.print("Ingrese el código de la materia a modificar: ");
                    String codMod = scanner.nextLine().toUpperCase();
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Nuevos créditos: ");
                    int nuevosCreditos = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nueva área: ");
                    String nuevaArea = scanner.nextLine();
                    sistema.modificarMateria(codMod, nuevoNombre, nuevosCreditos, nuevaArea);
                    break;

                case 7:
                    System.out.print("Código de la materia: ");
                    String codMateria = scanner.nextLine().toUpperCase();
                    System.out.print("Código del prerequisito: ");
                    String codPrereq = scanner.nextLine().toUpperCase();
                    sistema.agregarPrerequisito(codMateria, codPrereq);
                    break;

                case 8:
                    sistema.mostrarGrafo();
                    break;

                case 0:
                    System.out.println("Gracias");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    //Datos iniciales
    private static void cargarDatos(SistemaRecomendacion sistema) {

        // Semestre 1
        sistema.agregarMateria(new Materia("CAL1", "Cálculo 1", 4, 1, "Matemáticas"));
        sistema.agregarMateria(new Materia("FIL", "Filosofía", 2, 1, "Humanidades"));
        sistema.agregarMateria(new Materia("MD1", "Matemática Discreta 1", 4, 1, "Matemáticas"));

        // Semestre 2
        sistema.agregarMateria(new Materia("CAL2", "Cálculo 2", 4, 2, "Matemáticas"));
        sistema.agregarMateria(new Materia("POO", "Programación Orientada a Objetos", 4, 2, "Programación"));
        sistema.agregarMateria(new Materia("MD2", "Matemática Discreta 2", 4, 2, "Matemáticas"));
        sistema.agregarMateria(new Materia("HIS", "Historia", 2, 2, "Humanidades"));

        // Semestre 3
        sistema.agregarMateria(new Materia("CAL3", "Cálculo 3", 4, 3, "Matemáticas"));
        sistema.agregarMateria(new Materia("ED1", "Estructura de Datos 1", 4, 3, "Programación"));
        sistema.agregarMateria(new Materia("BD1", "Base de Datos", 4, 3, "Bases de Datos"));

        // Semestre 4
        sistema.agregarMateria(new Materia("CAL4", "Cálculo 4", 4, 4, "Matemáticas"));
        sistema.agregarMateria(new Materia("ED2", "Estructura de Datos 2", 4, 4, "Programación"));
        sistema.agregarMateria(new Materia("BD2", "Base de Datos Avanzada", 4, 4, "Bases de Datos"));

        // Prerequisitos
        sistema.agregarPrerequisito("CAL2", "CAL1");
        sistema.agregarPrerequisito("CAL3", "CAL2");
        sistema.agregarPrerequisito("CAL4", "CAL3");
        sistema.agregarPrerequisito("MD2", "MD1");
        sistema.agregarPrerequisito("HIS", "FIL");
        sistema.agregarPrerequisito("ED1", "POO");
        sistema.agregarPrerequisito("BD1", "POO");
        sistema.agregarPrerequisito("ED2", "ED1");
        sistema.agregarPrerequisito("BD2", "BD1");
    }
}