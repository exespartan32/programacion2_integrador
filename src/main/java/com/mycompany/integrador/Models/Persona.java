/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Models;

import com.mycompany.integrador.Enums.TipoPersona;
import java.time.LocalDate;

/**
 *
 * @author exequiel
 */
public class Persona {

    private String DNI; // llave primaria
    private String nombres;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private int edad;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private LocalDate fechaEliminacion;
    private TipoPersona tipoPersona;

    public Persona() {
    }

    public Persona(String DNI, String nombres, String apellidoMaterno, String apellidoPaterno, int edad, LocalDate fechaCreacion, TipoPersona tipoPersona) {
        this.nombres = nombres;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.edad = edad;
        this.DNI = DNI;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = null;
        this.fechaEliminacion = null;
        this.tipoPersona = tipoPersona;
    }

    public Persona(String DNI, String nombres, String apellidoMaterno, String apellidoPaterno, int edad, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliminacion, TipoPersona tipoPersona) {
        this.DNI = DNI;
        this.nombres = nombres;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.edad = edad;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaEliminacion = fechaEliminacion;
        this.tipoPersona = tipoPersona;
    }

    public Persona(String nombres, String apellidoMaterno, String apellidoPaterno, int edad, LocalDate fechaModificacion) {
        this.nombres = nombres;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.edad = edad;
        this.fechaModificacion = fechaModificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
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
        return "Persona{" + "nombres=" + nombres + ", apellidoMaterno=" + apellidoMaterno + ", apellidoPaterno=" + apellidoPaterno + ", edad=" + edad + ", DNI=" + DNI + ", fechaCreacion=" + fechaCreacion + '}';
    }
}
