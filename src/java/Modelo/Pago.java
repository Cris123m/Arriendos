package Modelo;

import java.sql.*;

public class Pago {
    private int idPago;
    private Date fechaPago;
    private double abonado;
    private String estado;
    private String cedula;

    public Pago() {
    }

    public Pago(int idPago, Date fechaPago, double abonado, String estado, String cedula) {
        this.idPago = idPago;
        this.fechaPago = fechaPago;
        this.abonado = abonado;
        this.estado = estado;
        this.cedula = cedula;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getAbonado() {
        return abonado;
    }

    public void setAbonado(double abonado) {
        this.abonado = abonado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    
}
