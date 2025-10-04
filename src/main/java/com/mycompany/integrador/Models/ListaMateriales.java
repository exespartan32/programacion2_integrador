/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Models;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author exequiel
 */
public class ListaMateriales {

    private Long idLista;
    private Long idMaterial;
    private Long idAlumno;
    private boolean prestado;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private LocalDate fechaEliminacion;

    public ListaMateriales(Long idLista, Long idMaterial, LocalDate fechaCreacion) {
        this.idLista = idLista;
        this.idMaterial = idMaterial;
        this.idAlumno = null;
        this.prestado = false;
        this.fechaPrestamo = null;
        this.fechaDevolucion = null;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = null;
        this.fechaEliminacion = null;
    }

    public ListaMateriales(Long idLista, Long idMaterial, Long idAlumno, LocalDate fechaCreacion) {
        this.idLista = idLista;
        this.idMaterial = idMaterial;
        this.idAlumno = idAlumno;
        this.prestado = false;
        this.fechaPrestamo = null;
        this.fechaDevolucion = null;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = null;
        this.fechaEliminacion = null;
    }

    public Long getIdLista() {
        return idLista;
    }

    public Long getIdMaterial() {
        return idMaterial;
    }

    public Long getIdAlumno() {
        return idAlumno;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
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

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

}
