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
public class Curso {
    private String nombreCurso;
    private int mesesDuracion;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private LocalDate fechaEliminacion;
    private String dniProfesor;

    public Curso() {
    }

    public Curso(String nombreCurso, int mesesDuracion, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliminacion) {
        this.nombreCurso = nombreCurso;
        this.mesesDuracion = mesesDuracion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliminacion = fechaEliminacion;
    }

    public Curso(String nombreCurso, int mesesDuracion, LocalDate fechaModificacion) {
        this.nombreCurso = nombreCurso;
        this.mesesDuracion = mesesDuracion;
        this.fechaModificacion = fechaModificacion;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getMesesDuracion() {
        return mesesDuracion;
    }

    public void setMesesDuracion(int mesesDuracion) {
        this.mesesDuracion = mesesDuracion;
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
        return "Curso{" + "nombreCurso=" + nombreCurso + ", mesesDuracion=" + mesesDuracion + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", fechaEliminacion=" + fechaEliminacion + '}';
    }

    public String getDniProfesor() {
        return dniProfesor;
    }
    
    
}
