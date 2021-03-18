package Modelo;

public class Precio {
    private int idPrecio;
    private double valorArriendo;
    private double valorGarantia;

    public Precio() {
    }

    public Precio(int idPrecio, double valorArriendo, double valorGarantia) {
        this.idPrecio = idPrecio;
        this.valorArriendo = valorArriendo;
        this.valorGarantia = valorGarantia;
    }

    public int getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(int idPrecio) {
        this.idPrecio = idPrecio;
    }

    public double getValorArriendo() {
        return valorArriendo;
    }

    public void setValorArriendo(double valorArriendo) {
        this.valorArriendo = valorArriendo;
    }

    public double getValorGarantia() {
        return valorGarantia;
    }

    public void setValorGarantia(double valorGarantia) {
        this.valorGarantia = valorGarantia;
    }
    
    
}
