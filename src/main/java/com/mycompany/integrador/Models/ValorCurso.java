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
public class ValorCurso {

    private int idValorCurso;
    private String nombreCurso;
    private int precioCurso;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private LocalDate fechaEliminacion;

    public ValorCurso(int idValorCurso, String nombreCurso, int precioCurso, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliminacion) {
        this.idValorCurso = idValorCurso;
        this.nombreCurso = nombreCurso;
        this.precioCurso = precioCurso;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliminacion = fechaEliminacion;
    }

    public ValorCurso(String nombreCurso, int precioCurso, LocalDate fechaModificacion) {
        this.nombreCurso = nombreCurso;
        this.precioCurso = precioCurso;
        this.fechaModificacion = fechaModificacion;
    }

    public ValorCurso(String nombreCurso, int precioCurso, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliminacion) {
        this.nombreCurso = nombreCurso;
        this.precioCurso = precioCurso;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliminacion = fechaEliminacion;
    }

    public int getIdValorCurso() {
        return idValorCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public int getPrecioCurso() {
        return precioCurso;
    }

    public void setPrecioCurso(int precioCurso) {
        this.precioCurso = precioCurso;
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

    @Override
    public String toString() {
        return "ValorCurso{" + "idValorCurso=" + idValorCurso + ", nombreCurso=" + nombreCurso + ", precioCurso=" + precioCurso + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", fechaEliminacion=" + fechaEliminacion + '}';
    }

}
