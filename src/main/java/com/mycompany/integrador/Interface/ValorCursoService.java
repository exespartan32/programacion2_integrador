/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.integrador.Interface;

import java.util.ArrayList;
import java.util.Optional;
import com.mycompany.integrador.Models.ValorCurso;

/**
 *
 * @author exequiel
 */
public interface ValorCursoService {

    public void guardarValorCurso(ValorCurso valorCurso);

    public void modificarValorCurso(Long id, ValorCurso valorCurso);

    public void eliminarValorCurso(Long id);

    public ArrayList<ValorCurso> buscarTodasLosUValoresCursos();

    public Optional<ValorCurso> buscarValorCurso(Long id);
}
