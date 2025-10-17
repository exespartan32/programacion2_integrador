/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Service.CursoServiceImplement;
import com.mycompany.integrador.Models.Curso;
import java.time.LocalDate;

/**
 *
 * @author exequiel
 */
public class Curso_Controller {

    CursoServiceImplement cursoServiceImplement = new CursoServiceImplement();
    
    public void nuevoCurso(){
        LocalDate fechaActual = LocalDate.now();
//        Curso curso = new Curso(nombreCurso, 0, Long.MIN_VALUE, Long.MIN_VALUE, fechaActual);
    }
}
