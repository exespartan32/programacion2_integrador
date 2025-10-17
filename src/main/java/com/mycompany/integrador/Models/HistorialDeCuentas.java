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
    private Long idHistorialCuenta  ;
    private Long idAlumno;
    private Long idCurso;
    private Long idValorCurso;
    private int pagoActual;
    private int saldoAlumno;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private LocalDate fechaEliminacion;

    public HistorialDeCuentas(Long idAlumno, Long idCurso, Long idValorCurso, int pagoActual, int saldoAlumno, String descripcion, LocalDate fechaCreacion) {
        this.idAlumno = idAlumno;
        this.idCurso = idCurso;
        this.idValorCurso = idValorCurso;
        this.pagoActual = pagoActual;
        this.saldoAlumno = saldoAlumno;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = null;
        this.fechaEliminacion = null;
    }

    public int getPagoActual() {
        return pagoActual;
    }

    public void setPagoActual(int pagoActual) {
        this.pagoActual = pagoActual;
    }

    public int getSaldoAlumno() {
        return saldoAlumno;
    }

    public void setSaldoAlumno(int saldoAlumno) {
        this.saldoAlumno = saldoAlumno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdHistorialCuenta() {
        return idHistorialCuenta;
    }

    public Long getIdAlumno() {
        return idAlumno;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public Long getIdValorCurso() {
        return idValorCurso;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
    
    
}
