/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.Service;

import com.mycompany.integrador.Interface.AlumnoService;
import com.mycompany.integrador.Models.Alumno;
import java.util.ArrayList;
import java.util.Optional;
import com.mycompany.integrador.Configurations.ConexionDB;

/**
 *
 * @author exequiel
 */
public class AlumnoServiceImplement implements AlumnoService {

    ConexionDB conn = new ConexionDB();
    
    @Override
    public void guardarAlumno(Alumno alumno) {
        String sql = "insert into table Alumno";
    }

    @Override
    public void modificarALumno(Long id, Alumno alumno) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrarAlumno(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Alumno> buscarTodosLosAlumnos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Alumno> buscarAlumno(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
