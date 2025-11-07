/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.integrador.Interface;

import java.util.ArrayList;
import com.mycompany.integrador.Models.ValorCurso;

/**
 *
 * @author exequiel
 */
public interface ValorCursoService {

    public void asignarPrecio(ValorCurso valorCurso);

    public void modificarPrecio(int id, ValorCurso valorCurso);

    public void eliminarPrecio(String nombreCurso);

    public ArrayList<ValorCurso> buscarValorCursos();

    public ValorCurso buscarValorCursos(String nombreCurso);
    
    public ValorCurso buscarValorCursos(int id);
    
}
