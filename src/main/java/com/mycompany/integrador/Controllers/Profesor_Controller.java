/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Enums.TipoPersona;
import com.mycompany.integrador.Service.ProfesorServiceImplement;
import java.time.LocalDate;
import com.mycompany.integrador.Models.Profesor;

/**
 *
 * @author exequiel
 */
public class Profesor_Controller {

    ProfesorServiceImplement profesorServiceImplement = new ProfesorServiceImplement();

    public void nuevoProfesor() {
        LocalDate fechaActual = LocalDate.now();
        Profesor profesor = new Profesor(1000000, true, "22156987", "juan carlos", "lopez", "garcia", 52, fechaActual, TipoPersona.PROFESOR);
        profesorServiceImplement.guardarProfesor(profesor);
    }
}
