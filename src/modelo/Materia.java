package modelo;

public class Materia {
    private String codigo;
    private String nombre;
    private int creditos;
    private int semestre;
    private String area;

    public Materia(String codigo, String nombre, int creditos, int semestre, String area) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.semestre = semestre;
        this.area = area;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCreditos() {
        return creditos;
    }
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    public int getSemestre() {
        return semestre;
    }
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }


    @Override
    public String toString() {
        return "[" + codigo + "] " + nombre + " | Semestre: " + semestre + " | Créditos: " + creditos + " | Área: " + area;
    }
}