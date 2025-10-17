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

    private Long idCurso;
    private String nombreCurso;
    private int mesesDuracion;
    private Long idProfesor ;
    private Long idAlumno;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private LocalDate fechaEliminacion;

    public Curso(String nombreCurso, int mesesDuracion, Long idProfesor, Long idAlumno, LocalDate fechaCreacion) {
        this.nombreCurso = nombreCurso;
        this.mesesDuracion = mesesDuracion;
        this.idProfesor = idProfesor;
        this.idAlumno = idAlumno;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = null;
        this.fechaEliminacion = null;
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

    public Long getIdCurso() {
        return idCurso;
    }

    public Long getIdProfesor() {
        return idProfesor;
    }

    public Long getIdAlumno() {
        return idAlumno;
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
