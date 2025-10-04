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
public class Alumno extends Persona {
    private long idCurso;
    private String anioIngreso;
    private String mesIngreso;

    public Alumno(long idCurso, String anioIngreso, String mesIngreso, String DNI, String nombres, String apellidoMaterno, String apellidoPaterno, int edad, LocalDate fechaCreacion, TipoPersona tipoPersona) {
        super(DNI, nombres, apellidoMaterno, apellidoPaterno, edad, fechaCreacion, TipoPersona.ALUMNO);
        this.idCurso = idCurso;
        this.anioIngreso = anioIngreso;
        this.mesIngreso = mesIngreso;
    }

    public String getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(String anioIngreso) {
        this.anioIngreso = anioIngreso;
    }

    public String getMesIngreso() {
        return mesIngreso;
    }

    public void setMesIngreso(String mesIngreso) {
        this.mesIngreso = mesIngreso;
    }

    public long getIdCurso() {
        return idCurso;
    }
}
