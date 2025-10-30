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

    public void modificarALumno(String dni, Alumno alumno);

    public void borrarAlumno(String dni);

    public ArrayList<Alumno> buscarAlumno();

    public Alumno buscarAlumno(String dni);
}
