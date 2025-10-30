/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Enums.TipoPersona;
import com.mycompany.integrador.Service.ProfesorServiceImplement;
import java.time.LocalDate;
import com.mycompany.integrador.Models.Profesor;
import java.util.ArrayList;

/**
 *
 * @author exequiel
 */
public class Profesor_Controller {

    ProfesorServiceImplement profesorServiceImplement = new ProfesorServiceImplement();

    public void nuevoProfesor() {
        LocalDate fechaActual = LocalDate.now();
        Profesor profesor = new Profesor(1000000, true, "132123132", "juan", "otero", "jofre", 26, fechaActual, TipoPersona.PROFESOR);
        profesorServiceImplement.guardarProfesor(profesor);
    }

    public void buscarTodosProfesores() {
        ArrayList<Profesor> listaProfesores = profesorServiceImplement.buscarProfesor();

        for (int i = 0; i < listaProfesores.size(); i++) {
            System.out.println("Elemento " + i + ": " + listaProfesores.get(i));
        }
    }

    public void buscarProfesor() {
        Profesor profesor = profesorServiceImplement.buscarProfesor("132123132");
        System.out.println(profesor.toString());
    }

    public void modificarDatosProfesor() {
        String dniModificar = "43270183";
        String nombresModificado = "nombre modificado";
        int edadModificado = 0;
        String apellidoPaternoModificado = "apellido paterno modificado";
        String apellidoMaternoModificado = "apellido materno modificado";
        LocalDate fechaModificacion = LocalDate.now();
        int sueldoModificado = 1234;
        boolean presentismoModificado = true;

        Profesor profesorModificado = new Profesor(sueldoModificado,
                presentismoModificado,
                nombresModificado,
                apellidoMaternoModificado,
                apellidoPaternoModificado,
                edadModificado,
                fechaModificacion
        );
        profesorServiceImplement.modificarProfesor(dniModificar, profesorModificado);

    }

    public void eliminarProfesor() {
        profesorServiceImplement.eliminarProfesor("132123132");
    }
}
