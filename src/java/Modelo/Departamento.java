package Modelo;

public class Departamento {
    private int idDepartamento;
    private int piso;
    private char letra;
    private int capacidad;
    private int precioId;

    public Departamento() {
    }

    public Departamento(int idDepartamento, int piso, char letra, int capacidad, int precioId) {
        this.idDepartamento = idDepartamento;
        this.piso = piso;
        this.letra = letra;
        this.capacidad = capacidad;
        this.precioId = precioId;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getPrecioId() {
        return precioId;
    }

    public void setPrecioId(int precioId) {
        this.precioId = precioId;
    }
    
    
}
