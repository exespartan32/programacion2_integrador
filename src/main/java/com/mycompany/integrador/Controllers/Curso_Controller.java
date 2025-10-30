/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Controllers;

import com.mycompany.integrador.Service.CursoServiceImplement;
import com.mycompany.integrador.Models.Curso;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author exequiel
 */
public class Curso_Controller {

    CursoServiceImplement cursoServiceImplement = new CursoServiceImplement();

    public void nuevoCurso() {
        LocalDate fechaActual = LocalDate.now();
        Curso curso = new Curso("maquillaje", 10, fechaActual, null, null);
        cursoServiceImplement.guardarCurso(curso);
    }

    public void buscarTodosLosCursos() {
        ArrayList<Curso> litaCursos = cursoServiceImplement.buscarCurso();
        for (int i = 0; i < litaCursos.size(); i++) {
            System.out.println("Elemento " + i + ": " + litaCursos.get(i).toString());
        }
    }

    public void buscarCurso() {
        Curso curso = cursoServiceImplement.buscarCurso("peluqueria");
        System.out.println("datos: " + curso.toString());
    }

    public void modificarCurso() {
        String nombreCurso = "maquillaje";
        Curso curso = new Curso("peluqueria 1", 6, LocalDate.now());
        cursoServiceImplement.modificarCurso(nombreCurso, curso);
    }

    public void eliminarCurso() {
        String nombreCurso = "peluqueria";
        cursoServiceImplement.eliminarCurso(nombreCurso);
    }

}
