/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.integrador.Interface;

import java.util.ArrayList;
import com.mycompany.integrador.Models.Curso;

/**
 *
 * @author exequiel
 */
public interface CursoService {

    public void guardarCurso(Curso curso);

    public void modificarCurso(String nombreCurso, Curso curso);

    public void eliminarCurso(String nombreCurso);

    public ArrayList<Curso> buscarCurso();

    public Curso buscarCurso(String nombreCurso);
    
    public Curso buscarCurso(String dniProfesor, int estado);

    public boolean existeCurso(String nombreCurso);

}
