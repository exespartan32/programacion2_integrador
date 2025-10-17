/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Enums.TipoPersona;
import com.mycompany.integrador.Models.Alumno;
import com.mycompany.integrador.Service.AlumnoServiceImplement;
import java.time.LocalDate;

/**
 *
 * @author exequiel
 */
public class Alumno_Controller {

    AlumnoServiceImplement alumnoServiceImplement = new AlumnoServiceImplement();

    public void nuevoAlumno() {
        LocalDate fechaActual = LocalDate.now();
        //Persona persona = new Persona("43270183", "daniel exxequiel", "ortiz", "mayorga", 24, LocalDate.MIN, TipoPersona.ALUMNO);
        Alumno alumno = new Alumno("2025", "octubre", "43270183","daniel exequiel", "ortiz", "mayorga", 24, fechaActual, TipoPersona.ALUMNO);
        alumnoServiceImplement.guardarAlumno(alumno);
    }

}
