/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Enums.TipoPersona;
import com.mycompany.integrador.Models.Alumno;
import com.mycompany.integrador.Service.AlumnoServiceImplement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author exequiel
 */
public class Alumno_Controller {

    AlumnoServiceImplement alumnoServiceImplement = new AlumnoServiceImplement();

    public void nuevoAlumno() {
        LocalDate fechaActual = LocalDate.now();
        
        System.out.println("ingrese el año de ingreso del alumno ej:20XX");
        System.out.println("ingrese el mes de ingreso del alumno ej: enero,febrero,....");
        
        
        //Persona persona = new Persona("43270183", "daniel exxequiel", "ortiz", "mayorga", 24, LocalDate.MIN, TipoPersona.ALUMNO);
        Alumno alumno = new Alumno("2025", "octubre", "43270183", "daniel exequiel", "ortiz", "mayorga", 24, fechaActual, TipoPersona.ALUMNO);
        alumnoServiceImplement.guardarAlumno(alumno);
    }

    public void buscarTodosAlumnos() {
        ArrayList<Alumno> listaAlumnos = alumnoServiceImplement.buscarAlumno();
        for (int i = 0; i < listaAlumnos.size(); i++) {
            System.out.println("Elemento " + i + ": " + listaAlumnos.get(i));
        }
    }

    public void buscarAlumno() {
        Alumno alumno = alumnoServiceImplement.buscarAlumno("43270183");
        System.out.println("datos: " + alumno.toString());
    }

    public void modificarAlumno() {
        String dniAlumnoModificar = "43270183";
        String nombresModificado = "nombre modificado";
        int edadModificado = 0;
        String apellidoPaternoModificado = "apellido modificado";
        LocalDate fechaCreacionModificado = LocalDate.now();
        String anioIngresoModificado = "año modificado";
        String mesIngresoModificado = "mes modificado";

        Alumno alumnoModificado = new Alumno(anioIngresoModificado, mesIngresoModificado, nombresModificado, apellidoPaternoModificado, apellidoPaternoModificado, edadModificado, fechaCreacionModificado);
        alumnoServiceImplement.modificarALumno(dniAlumnoModificar, alumnoModificado);
    }
    
    public void eliminarAlumno(){
        alumnoServiceImplement.borrarAlumno("43270183");
    }

}
