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

    private Long idValorCurso;
    private Long idCurso;
    private String nombreMes;
    private int precioMes;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private LocalDate fechaEliminacion;

    public ValorCurso(Long idValorCurso, Long idCurso, String nombreMes, int precioMes, LocalDate fechaCreacion) {
        this.idValorCurso = idValorCurso;
        this.idCurso = idCurso;
        this.nombreMes = nombreMes;
        this.precioMes = precioMes;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = null;
        this.fechaEliminacion = null;
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

    public String getNombreMes() {
        return nombreMes;
    }

    public void setNombreMes(String nombreMes) {
        this.nombreMes = nombreMes;
    }

    public int getPrecioMes() {
        return precioMes;
    }

    public void setPrecioMes(int precioMes) {
        this.precioMes = precioMes;
    }

    public Long getIdValorCurso() {
        return idValorCurso;
    }

    public Long getIdCurso() {
        return idCurso;
    }
}
