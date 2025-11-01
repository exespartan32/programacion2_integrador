/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Models;

import java.time.LocalDate;

/**
 *
 * @author exequiel
 */
public class HistorialDeCuentas {

    private int idHistorialCuenta;
    private String dniAlumno;
    private String nombreCurso;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private LocalDate fechaEliminacion;
    private boolean pagado;
    private int monto;
    private int pago;
    private int saldoAlumno;
    private String descripcionPago;

    public HistorialDeCuentas() {
    }

    public HistorialDeCuentas(int idHistorialCuenta, String dniAlumno, String nombreCurso, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliminacion, boolean pagado, int monto, int pago, int saldoAlumno, String descripcionPago) {
        this.idHistorialCuenta = idHistorialCuenta;
        this.dniAlumno = dniAlumno;
        this.nombreCurso = nombreCurso;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliminacion = fechaEliminacion;
        this.pagado = pagado;
        this.monto = monto;
        this.pago = pago;
        this.saldoAlumno = saldoAlumno;
        this.descripcionPago = descripcionPago;
    }

    public HistorialDeCuentas(String dniAlumno, String nombreCurso, boolean pagado, int monto, int pago, int saldoAlumno, String descripcionPago) {
        this.dniAlumno = dniAlumno;
        this.nombreCurso = nombreCurso;
        this.pagado = pagado;
        this.monto = monto;
        this.pago = pago;
        this.saldoAlumno = saldoAlumno;
        this.descripcionPago = descripcionPago;
    }

    public HistorialDeCuentas(String dniAlumno, String nombreCurso, LocalDate fechaCreacion, boolean pagado, int monto, int pago, int saldoAlumno, String descripcionPago) {
        this.dniAlumno = dniAlumno;
        this.nombreCurso = nombreCurso;
        this.fechaCreacion = fechaCreacion;
        this.pagado = pagado;
        this.monto = monto;
        this.pago = pago;
        this.saldoAlumno = saldoAlumno;
        this.descripcionPago = descripcionPago;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public LocalDate getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(LocalDate fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public int getSaldo() {
        return saldoAlumno;
    }

    public void setSaldo(int saldoAlumno) {
        this.saldoAlumno = saldoAlumno;
    }

    public int getIdHistorialCuenta() {
        return idHistorialCuenta;
    }

    public String getDniAlumno() {
        return dniAlumno;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public int getSaldoAlumno() {
        return saldoAlumno;
    }

    public void setSaldoAlumno(int saldoAlumno) {
        this.saldoAlumno = saldoAlumno;
    }

    public String getDescripcionPago() {
        return descripcionPago;
    }

    public void setDescripcionPago(String descripcionPago) {
        this.descripcionPago = descripcionPago;
    }

    @Override
    public String toString() {
        return "HistorialDeCuentas{" + "idHistorialCuenta=" + idHistorialCuenta + ", dniAlumno=" + dniAlumno + ", nombreCurso=" + nombreCurso + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", fechaEliminacion=" + fechaEliminacion + ", pagado=" + pagado + ", monto=" + monto + ", pago=" + pago + ", saldoAlumno=" + saldoAlumno + ", descripcionPago=" + descripcionPago + '}';
    }

}
