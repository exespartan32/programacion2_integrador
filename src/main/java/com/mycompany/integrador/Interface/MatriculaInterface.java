/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.integrador.Interface;

import com.mycompany.integrador.Models.Alumno;
import com.mycompany.integrador.Models.Matricula;
import com.mycompany.integrador.Models.Curso;
import java.util.ArrayList;

/**
 *
 * @author exequiel
 */
public interface MatriculaInterface {

    public void matricularAlumno(String DNIAlumno);

    public void desvincularAlumno(String DNIAlumno, String nombreCurso);

    /// buscar todas las matriculas
    public ArrayList<Matricula> buscarMatriculaCurso();

    /// buscar las matriculas de un curso
    public ArrayList<Matricula> buscarMatriculaCurso(String nombreCurso);

    /// buscar todas las matriculas de un alumno
    public ArrayList<Matricula> buscarMatriculaCurso(Alumno alumno);

    // buscar la matricula del alumno en un curso en especifico
    public Matricula buscarMatriculaCurso(Alumno alumno, Curso curso);
}
