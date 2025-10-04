/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.integrador.Interface;

import java.util.ArrayList;
import java.util.Optional;
import com.mycompany.integrador.Models.Curso;

/**
 *
 * @author exequiel
 */
public interface CursoService {

    public void guardarCurso(Curso curso);

    public void modificarCurso(Long id, Curso curso);

    public void eliminarCurso(Long id);

    public ArrayList<Curso> buscarTodosLosCursos();

    public Optional<Curso> buscarCurso(Long id);
}
