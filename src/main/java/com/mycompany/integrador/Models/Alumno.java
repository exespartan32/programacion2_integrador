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

    private String anioIngreso;
    private String mesIngreso;

    public Alumno(String anioIngreso, String mesIngreso, String DNI, String nombres, String apellidoMaterno, String apellidoPaterno, int edad, LocalDate fechaCreacion, TipoPersona tipoPersona) {
        super(DNI, nombres, apellidoMaterno, apellidoPaterno, edad, fechaCreacion, TipoPersona.ALUMNO);
        this.anioIngreso = anioIngreso;
        this.mesIngreso = mesIngreso;
    }

    public Alumno(String anioIngreso, String mesIngreso, String nombres, String apellidoMaterno, String apellidoPaterno, int edad, LocalDate fechaModificacion) {
        super(nombres, apellidoMaterno, apellidoPaterno, edad, fechaModificacion);
        this.anioIngreso = anioIngreso;
        this.mesIngreso = mesIngreso;
    }

    public Alumno(String anioIngreso, String mesIngreso, String DNI, String nombres, String apellidoMaterno, String apellidoPaterno, int edad, LocalDate fechaCreacion, LocalDate fechaModificacion, LocalDate fechaEliminacion, TipoPersona tipoPersona) {
        super(DNI, nombres, apellidoMaterno, apellidoPaterno, edad, fechaCreacion, fechaModificacion, fechaEliminacion, tipoPersona);
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

    @Override
    public String toString() {
        return super.toString() + "\n Alumno{" + ", anioIngreso=" + anioIngreso + ", mesIngreso=" + mesIngreso + '}';
    }

}
