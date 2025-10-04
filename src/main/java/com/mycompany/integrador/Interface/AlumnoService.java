/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.integrador.Interface;

import java.util.ArrayList;
import java.util.Optional;
import com.mycompany.integrador.Models.Alumno;

/**
 *
 * @author exequiel
 */
public interface AlumnoService {

    public void guardarAlumno(Alumno alumno);

    public void modificarALumno(Long id, Alumno alumno);

    public void borrarAlumno(Long id);

    public ArrayList<Alumno> buscarTodosLosAlumnos();

    public Optional<Alumno> buscarAlumno(Long id);
}
